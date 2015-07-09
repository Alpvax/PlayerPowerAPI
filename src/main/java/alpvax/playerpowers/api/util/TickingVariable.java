package alpvax.playerpowers.api.util;

/**A variable that counts down to 0 before resetting */
public class TickingVariable
{
	private boolean running = false;
	private int max;
	private int value = 0;
	
	public TickingVariable(int max)
	{
		this.max = max;
	}

	/**
	 * Decrements value by 1
	 * @return true if the counter reaches the limit and resets (stops running)
	 */
	public boolean tick()
	{
		if(running && --value <= 0)
		{
			value += max;
			stop();
			return true;
		}
		return false;
	}

	public int value()
	{
		return value;
	}

	public int maximum()
	{
		return max;
	}
	
	public boolean running()
	{
		return running;
	}

	public void start()
	{
		running = true;
	}
	
	public int stop()
	{
		running = false;
		return value;
	}

	@Override
	public String toString()
	{
		return value + " (" + max + " to 0)";
	}
}
