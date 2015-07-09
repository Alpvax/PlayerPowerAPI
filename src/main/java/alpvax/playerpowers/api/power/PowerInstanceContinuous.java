package alpvax.playerpowers.api.power;

public class PowerInstanceContinuous extends PowerInstance
{
	private int activeTicks;
	
	@Override
	public boolean isActive()
	{
		return true;
	}

	@Override
	public boolean hasCooldown()
	{
		return false;
	}

	@Override
	public int getCooldown()
	{
		return 0;
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

	@Override
	public boolean canTrigger()
	{
		return false;
	}
}
