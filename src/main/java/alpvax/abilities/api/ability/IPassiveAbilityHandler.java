package alpvax.abilities.api.ability;

public interface IPassiveAbilityHandler extends IAbilityHandler
{
	public boolean shouldTrigger();
	public boolean shouldReset(int ticksSinceTriggered);
	public boolean isActive();
}
