package alpvax.playerpowers.api.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.power.IPower;
import alpvax.playerpowers.api.provider.IPowerProvider;
import alpvax.playerpowers.api.provider.NBTPowerProvider;

import com.google.common.collect.ImmutableList;


public class PoweredPlayer implements IExtendedEntityProperties
{
	private static final String TAG_ID = "ProviderID";
	private static final String TAG_CLASS = "ProviderClass";

	private EntityPlayer player;
	private Map<String, IPowerProvider> providers = new HashMap<String, IPowerProvider>();

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagList list = new NBTTagList();
		for(String nbtKey : providers.keySet())
		{
			IPowerProvider p = providers.get(nbtKey);
			NBTTagCompound nbt = new NBTTagCompound();
			p.writeToNBT(nbt);
			nbt.setString(TAG_ID, nbtKey);
			nbt.setString(TAG_CLASS, p.getClass().getName());
			list.appendTag(nbt);
		}
		compound.setTag(PoweredPlayer.class.getName(), list);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagList list = compound.getTagList(PoweredPlayer.class.getName(), NBT.TAG_COMPOUND);
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt = list.getCompoundTagAt(i);
			String id = nbt.getString(TAG_ID);
			String c = nbt.hasKey(TAG_CLASS, NBT.TAG_STRING) ? nbt.getString(TAG_CLASS) : NBTPowerProvider.class.getName();
			try
			{
				@SuppressWarnings("unchecked")
				IPowerProvider p = ((Class<? extends IPowerProvider>)Class.forName(c)).newInstance();
				providers.put(id, p.readFromNBT(nbt));
			}
			catch(Exception e)
			{
				//TODO: Fail safe (skip) and display error message
				FMLLog.log(PlayerPowersConstants.LOG_TAG, Level.WARN, e, "Unable to load PowerProvider (Class: %s)registered with id %s", c, id);
			}
		}
	}

	@Override
	public void init(Entity entity, World world)
	{
		player = (EntityPlayer)entity;//No checks for debugging purposes, Fail Hard. TODO:Add instanceof EntityPlayer checks

	}

	public void addProvider(String key, IPowerProvider provider)
	{
		if(providers.containsKey(key))
		{
			FMLLog.log(PlayerPowersConstants.LOG_TAG, Level.WARN, "Power Provider already registered with key %s. Unable to register %s", key, provider);
			return;
		}
		if(key == null || key.length() < 1)
		{
			FMLLog.log(PlayerPowersConstants.LOG_TAG, Level.WARN, "Unable to register Power Provider with null key.");
			return;
		}
		if(provider == null)
		{
			FMLLog.log(PlayerPowersConstants.LOG_TAG, Level.WARN, "Unable to register null Power Provider.");
			return;
		}
		try
		{
			Class<? extends IPowerProvider> c = provider.getClass();
			c.newInstance();
		}
		catch(Exception e)
		{
			FMLLog.log(PlayerPowersConstants.LOG_TAG, Level.WARN, "Power Provider %s cannot be instantiated.");
			return;
		}
		providers.put(key, provider);
		provider.onAttach(player);
	}

	/**
	 * Removes the provider registered with the given key, stopping all powers beforehand.
	 */
	public void removeProvider(String key)
	{
		IPowerProvider pp = providers.remove(key);
		if(pp != null)
		{
			for(IPower p : pp.getPowers().values())
			{
				if(p.isActive())
				{
					p.deactivate(player);
				}
			}
		}
	}

	public boolean hasProvider(String key)
	{
		return providers.containsKey(key);
	}

	public IPowerProvider getProvider(String key)
	{
		return providers.get(key);
	}

	public int numProviders()
	{
		return providers.size();
	}

	/**
	 * @return an ImmuteableList of the provider keys
	 */
	public List<String> listProviders()
	{
		return ImmutableList.copyOf(providers.keySet());
	}

	public boolean triggerPower(String providerKey, String powerKey)
	{
		IPowerProvider p = getProvider(providerKey);
		if(p != null)
		{
			IPower pow = p.getPowers().get(powerKey);
			if(pow.isActive())
			{
				if(pow.canActivate())
				{
					pow.activate(player);
					pow.triggerActiveCooldown();
					return true;
				}
			}
			else if(pow.canDeactivate())
			{
				pow.deactivate(player);
				pow.triggerDeactiveCooldown();
				return true;
			}
		}
		return false;
	}

	/**
	 * @param powerclass the class the powers must extend to be returned. Passing null returns all powers
	 * @return a list of powers extending or implementing the passed in class
	 */
	public List<IPower> getPowers(Class<? extends IPower> powerclass)
	{
		boolean flag = powerclass == null;
		List<IPower> list = new ArrayList<IPower>();
		for(IPowerProvider pp : providers.values())
		{
			for(IPower p : pp.getPowers().values())
			{
				if(flag || powerclass.isAssignableFrom(p.getClass()))
				{
					list.add(p);
				}
			}
		}
		return list;
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PlayerPowersConstants.TAG_EXTENDED_DATA, new PoweredPlayer());
	}

	public static final PoweredPlayer get(EntityPlayer player)
	{
		return (PoweredPlayer)player.getExtendedProperties(PlayerPowersConstants.TAG_EXTENDED_DATA);
	}

	public void cloneOnDeath(PoweredPlayer oldPoweredPlayer)
	{
		for(String key : oldPoweredPlayer.providers.keySet())
		{
			IPowerProvider pp = oldPoweredPlayer.providers.get(key);
			IPowerProvider ppn = pp.onDeath();
			if(pp.persistAcrossDeath() && ppn != null)
			{
				addProvider(key, ppn);
			}
		}
	}

	//TODO Correct javadoc
	/** If you have any non-DataWatcher fields in your extended properties that
	 * need to be synced to the client, you must send a packet each time the
	 * player joins the world; this takes care of dying, changing dimensions, etc.
	 */
	public Map<String, Object> getDataForClient()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		for(String key : providers.keySet())
		{
			Map<String, IPower> powers = providers.get(key).getPowers();
			for(String pkey : powers.keySet())
			{
				//TODO: map.addPowerData(key, pkey, powers.get(pkey));
			}
		}
		return map;
	}
}
