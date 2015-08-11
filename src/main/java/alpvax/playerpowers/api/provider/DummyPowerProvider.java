package alpvax.playerpowers.api.provider;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;
import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.power.IPower;


public class DummyPowerProvider implements IPowerProvider
{
	private Map<String, IPower> powers = new HashMap<String, IPower>();
	private boolean persists = false;

	/**
	 * ALL IPowerProvider Classes MUST define an empty constructor and use it to initialise all the powers
	 */
	public DummyPowerProvider()
	{
	}

	@Override
	public Map<String, IPower> getPowers()
	{
		return powers;
	}

	@Override
	public IPowerProvider readFromNBT(NBTTagCompound compound)
	{
		NBTTagList list = compound.getTagList(PlayerPowersConstants.TAG_POWERS, NBT.TAG_COMPOUND);
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt = list.getCompoundTagAt(i);
			String id = nbt.getString(PlayerPowersConstants.TAG_POWER_ID);
			if(powers.containsKey(id))
			{
				powers.get(id).readFromNBT(nbt);
			}
		}
		persists = compound.hasKey(PlayerPowersConstants.TAG_PROVIDER_PERSISTS) && compound.getBoolean(PlayerPowersConstants.TAG_PROVIDER_PERSISTS);
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		NBTTagList list = new NBTTagList();
		for(String id : powers.keySet())
		{
			NBTTagCompound nbt = new NBTTagCompound();
			powers.get(id).writeToNBT(nbt);
			nbt.setString(PlayerPowersConstants.TAG_POWER_ID, id);
		}
		if(!list.hasNoTags())
		{
			compound.setTag(PlayerPowersConstants.TAG_POWERS, list);
		}
		if(persists)
		{
			compound.setBoolean(PlayerPowersConstants.TAG_PROVIDER_PERSISTS, true);
		}
		return compound;
	}

	@Override
	public boolean persistAcrossDeath()
	{
		return persists;
	}

	@Override
	public void onAttach(EntityPlayer player)
	{
	}

	protected IPowerProvider setPersistant(boolean flag)
	{
		persists = flag;
		return this;
	}

	protected void addPower(String key, IPower power)
	{
		powers.put(key, power);
	}

	@Override
	public IPowerProvider onDeath()
	{
		/* Persists check is already done, so doesn't need re-doing, cooldowns etc. aren't reset,
		 * nothing needs to be changed, so just return this.
		 */
		return this;
	}
}
