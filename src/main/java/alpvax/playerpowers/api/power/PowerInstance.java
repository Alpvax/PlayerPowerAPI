package alpvax.playerpowers.api.power;

import com.google.common.base.Predicate;

import alpvax.common.util.EntityHelper;
import alpvax.playerpowers.api.IPowerPlayerInstance;
import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.PlayerPowersRegistry;
import alpvax.playerpowers.api.power.IPower.EnumPowerType;
import alpvax.playerpowers.api.util.TickingVariable;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class PowerInstance
{
	private IPowerPlayerInstance attatchedPlayerInstance;
	private IPower power;
	
	private TickingVariable cooldownActivate;
	private TickingVariable cooldownDeactivate;
	private TickingVariable duration;
	private int activeTicks = 0;
	
	private TargetType target;
	
	public IPower getPower()
	{
		return power;
	}
	
	public boolean isActive()
	{
		return activeTicks > 0;
	}
	
	public boolean hasCooldown()
	{
		return isActive() ? exists(cooldownDeactivate) : exists(cooldownActivate);
	}
	
	public int getCooldown()
	{
		return isActive() ? cooldownDeactivate.value() : cooldownActivate.value();
	}
	
	@SideOnly(Side.CLIENT)
	public int getCooldownForDisplay()
	{
		return Math.max(cooldownDeactivate.value(), cooldownActivate.value());
	}
	
	public int getRemainingDuration()
	{
		return duration.value();
	}
	
	public int getTimeActive()
	{
		return activeTicks;
	}
	
	/**
	 * @return the maximum range for targeting
	 */
	public double getMaxTargetRange()
	{
		return 64D;
	}
	
	/**
	 * Return something different from this if you want to only effect certain entities
	 */
	public Predicate<Entity> getValidTargets()
	{
		return EntityHelper.EntitySelector.ALL;
	}

	public boolean canTrigger()
	{
		return !hasCooldown() || getCooldown() <= 0;
	}
	
	public void triggerPower()
	{
		if(canTrigger())
		{
			MovingObjectPosition mop = EntityHelper.getLookingAt(attatchedPlayerInstance.getPlayer(), getMaxTargetRange(), getValidTargets());
			getPower().activate(attatchedPlayerInstance.getPlayer(), mop.entityHit, mop.hitVec);
		}
	}
	
	private boolean exists(TickingVariable var)
	{
		return var != null && var.maximum() > 0;
	}

	public static class Builder
	{
		private IPower power;
		private Class<? extends PowerInstance> instanceClass;
		private TickingVariable cooldown;
		private TickingVariable duration;
		private TickingVariable active;
		
		public Builder(IPower power, Class<? extends PowerInstance> clazz)
		{
			this.power = power;
			this.instanceClass = clazz;
		}

		public Builder setCooldown(int i)
		{
			cooldown = new TickingVariable(i);
			return this;
		}
		
		/**
		 * Set how the power works.
		 * @param duration ignored for every type apart from timed, sets the active duration
		 */
		public Builder setType(EnumPowerType type, int duration)
		{
			switch(type)
			{
			case INSTANT:
				this.duration = null;
				active = null;
				break;
			case TIMED:
				this.duration = new TickingVariable(duration).setLimit(0);
				active = new TickingVariable(0).countUp();
				break;
			case TOGGLED:
				this.duration = null;
				active = new TickingVariable(0).countUp();
				break;
			case CONTINUOUS:
				this.duration = null;
				active = new TickingVariable(0).countUp();
				active.start();
				break;
			}
			return this;
		}
		
		public PowerInstance build(IPowerPlayerInstance ppi)
		{
			PowerInstance pi = instanceClass.newInstance();
			pi.attatchedPlayerInstance = ppi;
			pi.power = power;
			pi.cooldown = cooldown;
			pi.duration = duration;
			pi.active = active;
			return pi;
		}
	}
}
