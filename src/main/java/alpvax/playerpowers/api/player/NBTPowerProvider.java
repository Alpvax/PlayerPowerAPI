package alpvax.playerpowers.api.player;

import java.util.HashMap;
import java.util.Map;

import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.power.IPower;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;


/**
 * Power Provider used to load powers from nbt data. To make it easy for mapmakers to create custom powerproviders
 *
 * @author Alpvax
 */
public class NBTPowerProvider implements IPowerProvider
{
	private Map<String, IPower> powers = new HashMap<String, IPower>();

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
			//TODO: instantiate IPower
		}
		return null;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		return null;// TODO Auto-generated method stub
	}

}
