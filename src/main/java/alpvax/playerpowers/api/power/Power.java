package alpvax.playerpowers.api.power;


import static alpvax.playerpowers.api.PlayerPowersConstants.*;
import alpvax.playerpowers.api.util.TickingVariable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public /*abstract*/ class Power implements IPower
{
	protected TickingVariable cooldownActivate;
	protected TickingVariable cooldownDeactivate;
	protected TickingVariable duration;
	private int activeTicks = -1;
	
	public Power()
	{
	}

	@Override
	public IPower readFromNBT(NBTTagCompound compound)
	{
		if(compound.hasKey(TAG_POWER_ACTIVE, NBT.TAG_INT))
		{
			activeTicks = compound.getInteger(TAG_POWER_ACTIVE);
		}
		if(compound.hasKey(TAG_POWER_COOLDOWN_A, NBT.TAG_COMPOUND))
		{
			cooldownActivate = TickingVariable.fromNBT(compound.getCompoundTag(TAG_POWER_COOLDOWN_A));
		}
		if(compound.hasKey(TAG_POWER_COOLDOWN_D, NBT.TAG_COMPOUND))
		{
			cooldownDeactivate = TickingVariable.fromNBT(compound.getCompoundTag(TAG_POWER_COOLDOWN_D));
		}
		if(compound.hasKey(TAG_POWER_DURATION, NBT.TAG_COMPOUND))
		{
			duration = TickingVariable.fromNBT(compound.getCompoundTag(TAG_POWER_DURATION));
		}
		return this;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		if(TickingVariable.exists(cooldownActivate))
		{
			compound.setTag(TAG_POWER_COOLDOWN_A, cooldownActivate.toNBT(new NBTTagCompound()));
		}
		if(TickingVariable.exists(cooldownDeactivate))
		{
			compound.setTag(TAG_POWER_COOLDOWN_D, cooldownDeactivate.toNBT(new NBTTagCompound()));
		}
		if(TickingVariable.exists(duration))
		{
			compound.setTag(TAG_POWER_DURATION, duration.toNBT(new NBTTagCompound()));
		}
		return compound;
	}

	@Override
	public boolean isActive()
	{
		return activeTicks >= 0;
	}
	
	public boolean hasCooldown()
	{
		return isActive() ? TickingVariable.exists(cooldownDeactivate) : TickingVariable.exists(cooldownActivate);
	}
	
	public int getCooldown()
	{
		return isActive() ? cooldownDeactivate.value() : cooldownActivate.value();
	}
	
	/**
	 * @return the highest cooldown
	 */
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

	@Override
	public void onActiveTick(EntityPlayer user)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void activate(EntityPlayer user)
	{
		activeTicks = 0;
	}

	@Override
	public void deactivate(EntityPlayer user)
	{
		activeTicks = -1;
	}

	@Override
	public boolean canActivate()
	{
		return !TickingVariable.exists(cooldownActivate) || cooldownActivate.value() <= 0;
	}

	@Override
	public boolean canDeactivate()
	{
		return !TickingVariable.exists(cooldownDeactivate) || cooldownDeactivate.value() <= 0;
	}

	@Override
	public void triggerActiveCooldown()
	{
		cooldownActivate.start();
	}

	@Override
	public void triggerDeactiveCooldown()
	{
		cooldownDeactivate.start();
	}

	@Override
	public String getID() {
		// TODO Remove from abstract
		return null;
	}

	@Override
	public String displayName() {
		// TODO Remove from abstract
		return null;
	}

}
