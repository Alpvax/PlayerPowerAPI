package alpvax.playerpowers.api.power;

import org.apache.logging.log4j.Level;

import com.google.common.base.Predicate;

import alpvax.common.util.EntityHelper;
import alpvax.playerpowers.api.IPowerPlayerInstance;
import alpvax.playerpowers.api.util.TickingVariable;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.FMLLog;
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
			activate();
		}
	}
	
	private void activate()
	{
		MovingObjectPosition mop = EntityHelper.getLookingAt(attatchedPlayerInstance.getPlayer(), getMaxTargetRange(), getValidTargets());
		getPower().activate(attatchedPlayerInstance.getPlayer(), mop.entityHit, mop.hitVec);
	}

	private boolean exists(TickingVariable var)
	{
		return var != null && var.maximum() > 0;
	}

	public static class Builder
	{
		private IPower power;
		private Class<? extends PowerInstance> instanceClass;
		private int cooldownActivate = 0;
		private int cooldownDeactivate = -1;
		private int duration = -1;
		private boolean active = false;
		
		public Builder(IPower power, Class<? extends PowerInstance> clazz)
		{
			this.power = power;
			this.instanceClass = clazz;
		}

		public Builder setActivationCooldown(int i)
		{
			cooldownActivate = i;
			return this;
		}
		public Builder setDectivationCooldown(int i)
		{
			cooldownDeactivate = i;
			return this;
		}
		public Builder setDuration(int i)
		{
			duration = i;
			return this;
		}
		public Builder startActive(boolean flag)
		{
			active = flag;
			return this;
		}
		
		public PowerInstance build(IPowerPlayerInstance ppi)
		{
			try
			{
				PowerInstance pi = instanceClass.newInstance();
				pi.attatchedPlayerInstance = ppi;
				pi.power = power;
				pi.cooldownActivate = new TickingVariable(cooldownActivate);
				pi.cooldownDeactivate = new TickingVariable(cooldownDeactivate);
				pi.duration = new TickingVariable(duration);
				if(active)
				{
					pi.activate();
				}
				return pi;
			}
			catch(Exception e)
			{
				FMLLog.log(Level.ERROR, e, "Unable to create new PowerInstance!");
			}
			return null;
		}
	}
}
