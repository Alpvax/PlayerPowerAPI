package alpvax.playerpowers.api.power;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;


public interface IPower
{
	/**
	 * @return this for ease of use
	 */
	public IPower readFromNBT(NBTTagCompound compound);

	/**
	 * @return the passed in compound for ease of use.<br><i>Example:</i><br>{@code nbt = writeToNBT(new NBTTagCompound)}
	 */
	public NBTTagCompound writeToNBT(NBTTagCompound compound);

	/**
	 * @return the name of the power for use in any GUI elements. (Or death messages etc.)
	 */
	public String displayName();

	/**
	 * @return whether the power is active or not.
	 * Will always return true for constant-effect powers, will always return false for instant-effect powers.
	 */
	public boolean isActive();

	/**
	 * Called every tick. Use it to update cooldowns etc.<br>
	 * Does <strong>NOT</strong> need to call onActiveTick
	 */
	public void tick();

	/**
	 * Called every tick the power is active.
	 * @param user the {@link EntityPlayer} who used the power
	 */
	public void onActiveTick(EntityPlayer user);

	/**
	 * Activates the power.
	 * @param user the {@link EntityPlayer} who used the power
	 */
	public void activate(EntityPlayer user);

	/**
	 * Deactivate the power.
	 * @param user the {@link EntityPlayer} who used the power
	 */
	public void deactivate(EntityPlayer user);

	boolean canActivate();

	boolean canDeactivate();

	/**
	 * Used to actually trigger the cooldown when the power is activated
	 */
	public void triggerActiveCooldown();

	/**
	 * Used to actually trigger the cooldown when the power is deactivated
	 */
	public void triggerDeactiveCooldown();
}
