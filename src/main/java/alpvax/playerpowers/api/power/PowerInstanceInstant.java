package alpvax.playerpowers.api.power;

import alpvax.playerpowers.api.util.TickingVariable;

public class PowerInstanceInstant extends PowerInstance
{
	private TickingVariable cooldown;
	
	@Override
	public boolean isActive()
	{
		return false;
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
		return -1;
	}

	@Override
	public int getTimeActive()
	{
		return 0;
	}
}
