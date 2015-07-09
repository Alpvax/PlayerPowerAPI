package alpvax.playerpowers.api.power;

import alpvax.playerpowers.api.util.TickingVariable;

public class PowerInstanceToggled extends PowerInstance
{
	private TickingVariable cooldownActivate;
	private TickingVariable cooldownDeactivate;
	private int activeTicks;
	
	@Override
	public boolean isActive()
	{
		return activeTicks > 0;
	}

	@Override
	public boolean hasCooldown()
	{
		return isActive() ? cooldownDeactivate != null : cooldownActivate != null;
	}

	@Override
	public int getCooldown()
	{
		return isActive() ? cooldownDeactivate.value() : cooldownActivate.value();
	}
	
	@Override
	public int getRemainingDuration()
	{
		return -1;
	}

	@Override
	public int getTimeActive()
	{
		return activeTicks;
	}
}
