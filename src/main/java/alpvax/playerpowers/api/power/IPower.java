package alpvax.playerpowers.api.power;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;


public interface IPower
{
	public String getID();

	/**
	 * @return this for ease of use
	 */
	public IPower readFromNBT(NBTTagCompound compound);

	/**
	 * @return the passed in compound for ease of use.<br><i>Example:</i><br>{@code nbt = writeToNBT(new NBTTagCompound)}
	 */
	public NBTTagCompound writeToNBT(NBTTagCompound compound);

	public String displayName();

	public void onActiveTick(EntityPlayer user, Entity targetEntity, Vec3 targetPos);

	public void activate(EntityPlayer user, Entity targetEntity, Vec3 targetPos);

	public void deactivate(EntityPlayer user, Entity targetEntity, Vec3 targetPos);
}
