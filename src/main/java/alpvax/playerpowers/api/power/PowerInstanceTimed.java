package alpvax.playerpowers.api.power;

import alpvax.playerpowers.api.util.TickingVariable;

public class PowerInstanceTimed extends PowerInstance
{
	private TickingVariable cooldown;
	private TickingVariable duration;
	private int activeTicks;
	
	@Override
	public boolean isActive()
	{
		return activeTicks > 0;
	}

	@Override
	public boolean hasCooldown()
	{
		return cooldown != null;
	}

	@Override
	public int getCooldown()
	{
		return cooldown.value();
	}
	
	@Override
	public int getRemainingDuration()
	{
		return duration.value();
	}

	@Override
	public int getTimeActive()
	{
		return activeTicks;
	}
}
