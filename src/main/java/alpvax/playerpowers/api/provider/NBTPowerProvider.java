package alpvax.playerpowers.api.provider;

import java.util.HashMap;
import java.util.Map;

import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.power.IPower;
import alpvax.playerpowers.api.power.NBTPower;
import net.minecraft.entity.player.EntityPlayer;
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
	private boolean persists = false;

	@Override
	public Map<String, IPower> getPowers()
	{
		return /*new HashMap<String, IPower>*/(powers);
	}

	@Override
	public IPowerProvider readFromNBT(NBTTagCompound compound)
	{
		NBTTagList list = compound.getTagList(PlayerPowersConstants.TAG_POWERS, NBT.TAG_COMPOUND);
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt = list.getCompoundTagAt(i);
			String id = nbt.getString(PlayerPowersConstants.TAG_POWER_ID);
			NBTPower p = NBTPower.fromNBT(nbt);
			powers.put(id, p);
		}
		persists = compound.hasKey(PlayerPowersConstants.TAG_PROVIDER_PERSISTS) && compound.getBoolean(PlayerPowersConstants.TAG_PROVIDER_PERSISTS);
		return null;
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
		// TODO Auto-generated method stub
	}

}
