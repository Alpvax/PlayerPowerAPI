package alpvax.powerproviders.api.target;

import java.util.List;

import alpvax.powerproviders.api.effect.IPowerEffect;
import scala.actors.threadpool.Arrays;

public class TargetedEffect
{
	private final IPowerEffect effect;
	private List<TargetType<?>> targets;
	
	@SuppressWarnings("unchecked")
	public TargetedEffect(IPowerEffect effect, TargetType<?>... targets)
	{
		this(effect, Arrays.asList(targets));
	}
	public TargetedEffect(IPowerEffect effect, List<TargetType<?>> targets)
	{
		this.effect = effect;
		this.targets = targets;
	}
	
	public IPowerEffect getEffect()
	{
		return effect;
	}
	
	public List<TargetType<?>> getTargets()
	{
		return targets;
	}
}
