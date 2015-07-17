package alpvax.playerpowers.api.player;

import java.util.List;

import alpvax.playerpowers.api.power.IPower;
import net.minecraft.nbt.NBTTagCompound;

/**
 * <b>ALL CLASSES WHICH IMPLEMENT THIS INTERFACE MUST DECLARE A CONSTRUCTOR WITH NO ARGUMENTS!!</b><p/>
 * 
 * @author Alpvax
 */
public interface IPowerProvider
{
	public List<IPower> getPowers();

	/**
	 * @return this for ease of use
	 */
	public IPowerProvider readFromNBT(NBTTagCompound compound);
	/**
	 * @return the passed in compound for ease of use.<br><i>Example:</i><br>{@code nbt = writeToNBT(new NBTTagCompound)}
	 */
	public NBTTagCompound writeToNBT(NBTTagCompound compound);
}
