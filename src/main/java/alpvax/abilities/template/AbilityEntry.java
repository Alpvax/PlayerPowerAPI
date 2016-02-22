package alpvax.abilities.template;

import java.util.ArrayList;
import java.util.List;

import alpvax.abilities.ability.AbilityInstance;

public class AbilityEntry<P>
{
	private final AbilityTemplate<P>[] effects;
	
	private AbilityEntry(AbilityTemplate<P>[] array)
	{
		effects = array;
	}

	
	public static class Builder<P>
	{
		private List<AbilityTemplate<P>> templates = new ArrayList<>();
		
		public Builder<P> addAbility(AbilityTemplate<P> template)
		{
			templates.add(template);
			return this;
		}
		
		@SuppressWarnings("unchecked")
		public AbilityEntry<P> build()
		{
			return new AbilityEntry<P>((AbilityTemplate<P>[])templates.toArray());
		}
	}


	public AbilityInstance<P> newAbilityInstance()
	{
		return new AbilityInstance<P>();
	}
}
