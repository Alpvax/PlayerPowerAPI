package alpvax.powerproviders.api.target;

import java.util.ArrayList;
import java.util.List;

import alpvax.powerproviders.api.effect.IPowerEffect;
import net.minecraft.entity.Entity;
import scala.actors.threadpool.Arrays;

public class EffectTemplate
{
	private final IPowerEffect effect;
	private List<PowerTarget> targets;
	
	@SuppressWarnings("unchecked")
	public EffectTemplate(IPowerEffect effect, PowerTarget... targets)
	{
		this(effect, Arrays.asList(targets));
	}
	public EffectTemplate(IPowerEffect effect, List<PowerTarget> targets)
	{
		this.effect = effect;
		this.targets = targets;
	}
	
	public IPowerEffect getEffect()
	{
		return effect;
	}
	
	public List<Entity> getTargets(Entity powered)
	{
		List<Entity> list = new ArrayList<Entity>();
		for(PowerTarget pt : targets)
		{
			List<Entity> l = pt.getTargets(powered);
			if(l != null)
			{
				list.addAll(l);
			}
		}
		return list;
	}
	
	public void trigger(Entity powered)
	{
		for(Entity target : getTargets(powered))
		{
			effect.trigger(powered, target);
		}
	}
	
	public void reset(Entity powered)
	{
		for(Entity target : getTargets(powered))
		{
			effect.reset(powered, target);
		}
	}
	
	public void tick(Entity powered)
	{
		for(Entity target : getTargets(powered))
		{
			effect.tick(powered, target);
		}
	}
}
