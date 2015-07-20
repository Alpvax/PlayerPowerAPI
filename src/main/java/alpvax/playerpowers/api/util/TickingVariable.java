package alpvax.playerpowers.api.util;

import net.minecraft.nbt.NBTTagCompound;

/**A variable that counts down to 0 before resetting */
public class TickingVariable
{
	private static final String TAG_MAX = "Max";
	private static final String TAG_VALUE = "Value";
	private static final String TAG_RUN = "Running";
	
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
	
	public NBTTagCompound toNBT(NBTTagCompound nbt)
	{
		nbt.setInteger(TAG_MAX, max);
		nbt.setInteger(TAG_VALUE, value);
		nbt.setBoolean(TAG_RUN, running);
		return nbt;
	}
	
	public static TickingVariable fromNBT(NBTTagCompound nbt)
	{
		TickingVariable var = new TickingVariable(nbt.getInteger(TAG_MAX));
		var.value = nbt.getInteger(TAG_VALUE);
		var.running = nbt.getBoolean(TAG_RUN);
		return var;
	}

	public static boolean exists(TickingVariable var)
	{
		return var != null && var.maximum() > 0;
	}
}
