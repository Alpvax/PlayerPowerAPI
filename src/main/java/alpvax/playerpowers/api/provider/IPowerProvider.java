package alpvax.playerpowers.api.provider;

import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import alpvax.playerpowers.api.power.IPower;


/**
 * <b>ALL CLASSES WHICH IMPLEMENT THIS INTERFACE MUST DEFINE A CONSTRUCTOR WITH NO ARGUMENTS!!</b><p/>
 * This constructor must initialise all the powers provided by this class
 *
 * @author Alpvax
 */
public interface IPowerProvider
{
	public Map<String, IPower> getPowers();
	
	/**
	 * @return whether or not this provider should remain attached when the player dies
	 */
	public boolean persistAcrossDeath();

	/**
	 * @return this for ease of use
	 */
	public IPowerProvider readFromNBT(NBTTagCompound compound);

	/**
	 * @return the passed in compound for ease of use.<br><i>Example:</i><br>{@code nbt = writeToNBT(new NBTTagCompound)}
	 */
	public NBTTagCompound writeToNBT(NBTTagCompound compound);

	/**
	 * Use to start continuous powers
	 */
	public void onAttach(EntityPlayer player);
}
