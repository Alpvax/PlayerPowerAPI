package alpvax.powerproviders.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Predicate;

import alpvax.powerproviders.api.effect.IPowerEffect;
import alpvax.powerproviders.api.power.PowerInstance;
import alpvax.powerproviders.core.IPowerProvider;

public class PowerProvider implements IPowerProvider
{
	private List<PowerInstance> powers = new ArrayList<>();

	@Override
	public List<PowerInstance> getAllPowers()
	{
		return powers;
	}

	@Override
	public List<PowerInstance> getActivePowers()
	{
		return getActivePowers(null);
	}

	@Override
	public List<PowerInstance> getActivePowers(Predicate<IPowerEffect> filter)
	{
		boolean flag = filter != null;
		List<PowerInstance> list = new ArrayList<>(powers);
		Iterator<PowerInstance> i = list.iterator();
		while(i.hasNext())
		{
			PowerInstance pi = i.next();
			if(!pi.isActive() || (flag && !pi.hasEffect(filter)))
			{
				i.remove();
			}
		}
		return list;
	}

	@Override
	public int getNumPowers()
	{
		return powers.size();
	}

	@Override
	public PowerInstance getPowerInSlot(int i)
	{
		return powers.get(i);
	}

	@Override
	public void setPowerInSlot(int i, PowerInstance pi)
	{
		if(i < powers.size())
		{
			powers.set(i, pi);
		}
		else
		{
			powers.add(pi);
		}
	}
}
