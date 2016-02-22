package alpvax.abilities.api.ability;

import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.ability.AbilityInstance;

public interface IAbilityProvider<P>
{
	public List<AbilityInstance<P>> getAbilities(Predicate<AbilityInstance<P>> filter);
	public List<AbilityInstance<P>> getActiveAbilities(Predicate<AbilityInstance<P>> filter);
	public int getNumAbilities();
}
