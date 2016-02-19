package alpvax.powers.api;

import alpvax.powers.api.target.PowerTarget;

public interface IPowerEffect
{
	public boolean trigger(PowerTarget... targets);
	public boolean tick(PowerTarget... targets);
	public boolean reset(PowerTarget... targets);
	public int tickrate();
}
