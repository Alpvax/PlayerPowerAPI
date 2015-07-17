package alpvax.playerpowers.api.player;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.FMLLog;

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
			try
			{
				@SuppressWarnings("unchecked")
				IPowerProvider p = ((Class<? extends IPowerProvider>)Class.forName(nbt.getString(TAG_CLASS))).newInstance();
				providers.put(id, p.readFromNBT(nbt));
			}
			catch(Exception e)
			{
				//TODO: Fail safe (skip) and display error message
				FMLLog.log("PoweredPlayer", Level.WARN, e, "Unable to load PowerProvider registered with id %s", id);
			}
		}
	}

	@Override
	public void init(Entity entity, World world)
	{
		player = (EntityPlayer)entity;//No checks for debugging purposes, Fail Hard. TODO:Add instanceof EntityPlayer checks

	}

}
