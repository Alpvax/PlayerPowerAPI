package alpvax.playerpowers.api.power;

import net.minecraft.nbt.NBTTagCompound;

public class NBTPower extends Power
{
	@Override
	public String displayName()
	{
		return "";
	}
	
	public static NBTPower fromNBT(NBTTagCompound nbt)
	{
		NBTPower p = new NBTPower();
		//TODO: Initialise power
		return p;
	}
}
