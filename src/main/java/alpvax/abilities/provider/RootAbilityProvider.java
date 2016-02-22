package alpvax.abilities.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;

import alpvax.abilities.ability.AbilityInstance;
import alpvax.abilities.api.ability.IAbilityProvider;
import alpvax.abilities.api.provider.SimpleAbilityProvider;

public class RootAbilityProvider<P> extends SimpleAbilityProvider<P>
{
	private Map<String, IAbilityProvider<P>> children = new HashMap<>();

	@Override
	public List<AbilityInstance<P>> getAbilities(Predicate<AbilityInstance<P>> filter)
	{
		List<AbilityInstance<P>> list = super.getAbilities(filter);
		for(IAbilityProvider<P> provider : children.values())
		{
			list.addAll(provider.getAbilities(filter));
		}
		return list;
	}

	@Override
	public int getNumAbilities()
	{
		int num = super.getNumAbilities();
		for(IAbilityProvider<P> provider : children.values())
		{
			num += provider.getNumAbilities();
		}
		return num;
	}
}
