package alpvax.abilities.api.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;

import alpvax.abilities.ability.AbilityInstance;
import alpvax.abilities.api.ability.IAbilityProvider;
import alpvax.abilities.template.AbilityEntry;

public class SimpleAbilityProvider<P> implements IAbilityProvider<P>
{
	private List<AbilityInstance<P>> abilities = new ArrayList<>();

	@Override
	public List<AbilityInstance<P>> getAbilities(Predicate<AbilityInstance<P>> filter)
	{
		List<AbilityInstance<P>> list = new ArrayList<>(abilities);
		Iterator<AbilityInstance<P>> i = list.iterator();
		while(i.hasNext())
		{
			if(!filter.apply(i.next()))
			{
				i.remove();
			}
		}
		return list;
	}

	@Override
	public List<AbilityInstance<P>> getActiveAbilities(final Predicate<AbilityInstance<P>> filter)
	{
		return getAbilities(new Predicate<AbilityInstance<P>>()
		{
			@Override
			public boolean apply(AbilityInstance<P> input)
			{
				return input.isActive() && (filter == null || filter.apply(input));
			}
		});
	}

	@Override
	public int getNumAbilities()
	{
		return abilities.size();
	}
	
	public void addAbility(AbilityEntry<P> template)
	{
		abilities.add(template.newAbilityInstance());
	}
}
