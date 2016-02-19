package alpvax.powers.api;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import alpvax.powers.api.target.EnumTarget;

public class PowerProvider
{
	@SuppressWarnings("unchecked")
	public void registerPower(IPowerEffect effect, Object... targetTypes)
	{
		List<EnumTarget> list = new ArrayList<EnumTarget>();
		for(Object o : targetTypes)
		{
			if(o instanceof EnumTarget)
			{
				list.add((EnumTarget)o);
			}
			else if(o instanceof EnumSet<?>)
			{
				list.addAll((EnumSet<EnumTarget>)o);
				/*for(EnumTarget t : (EnumSet<EnumTarget>)o)
				{
					list.add(t);
				}*/
			}
			else
			{
				throw new IllegalArgumentException(o + " is neither an EnumSet or an EnumTarget");
			}
		}
		registerPower(effect, EnumSet.copyOf(list));
	}
	
	public void registerPower(IPowerEffect effect, EnumSet<EnumTarget> targetTypes)
	{
		//TODO: implement registry
	}
}
