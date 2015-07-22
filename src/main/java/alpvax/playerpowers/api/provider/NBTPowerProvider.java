package alpvax.playerpowers.api.provider;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;
import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.power.NBTPower;


/**
 * Power Provider used to load powers from nbt data. To make it easy for mapmakers to create custom powerproviders
 *
 * @author Alpvax
 */
public class NBTPowerProvider extends DummyPowerProvider
{
	@Override
	public IPowerProvider readFromNBT(NBTTagCompound compound)
	{
		NBTTagList list = compound.getTagList(PlayerPowersConstants.TAG_POWERS, NBT.TAG_COMPOUND);
		for(int i = 0; i < list.tagCount(); i++)
		{
			NBTTagCompound nbt = list.getCompoundTagAt(i);
			String id = nbt.getString(PlayerPowersConstants.TAG_POWER_ID);
			NBTPower p = NBTPower.fromNBT(nbt);
			addPower(id, p);
		}
		setPersistant(compound.hasKey(PlayerPowersConstants.TAG_PROVIDER_PERSISTS) && compound.getBoolean(PlayerPowersConstants.TAG_PROVIDER_PERSISTS));
		return null;
	}
}
