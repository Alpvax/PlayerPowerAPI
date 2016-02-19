package alpvax.powerproviders.api.effect;

import java.util.List;

import net.minecraft.block.Block;

public interface IPowerBlockEffect
{
	/**
	 * Called to start the power.<br>
	 * Should be used to add attribute modifiers etc.
	 * @param targets The entities affected by the power
	 */
	public void trigger(List<Block> targets);
	/**
	 * Called to stop the power. Must revert any changes from {@link #trigger(PowerTarget...)}<br>
	 * Should be used to remove attribute modifiers etc.<br>
	 * Also called if the powers are removed from the provider,
	 * so the affected targets should be left in a state as though they didn't have powers.
	 * @param targets The entities affected by the power
	 */
	public void reset(List<Block> targets);
	/**
	 * Called every time the effect ticks (as determined by the {@linkplain #tickrate() tickrate}).<br>
	 * Should be used for e.g. powers that have an effect that does something every x seconds
	 * @param targets The entities affected by the power
	 */
	public void tick(List<Block> targets);
	/**
	 * Determine the number of game ticks required for the power to tick.<br>
	 * There are 20 ticks per second, so a power that ticks once per second should return 20.<br><br>
	 * For a randomly ticking power, leave this as a low number and implement a random check inside the {@link #tick(PowerTarget...)} method.<br>
	 * A value of less than 1 will cause the {@link #tick(PowerTarget...)} method to never be called.
	 * @return
	 */
	public int tickrate();
}