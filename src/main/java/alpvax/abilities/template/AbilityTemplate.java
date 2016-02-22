package alpvax.abilities.template;

import alpvax.abilities.api.IAbilityEffect;
import alpvax.abilities.api.ability.IAbilityHandler;

public class AbilityTemplate<P>
{
	private final IAbilityEffect<P> effect;
	private IAbilityHandler handler;
	
	public AbilityTemplate(IAbilityEffect<P> effect)
	{
		this.effect = effect;
	}
	
	public AbilityTemplate<P> setPassiveHandler(IAbilityHandler abilityhandler)
	{
		handler = abilityhandler;
		return this;
	}
}
