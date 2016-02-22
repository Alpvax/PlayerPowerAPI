package alpvax.abilities.api.effect;

import java.util.List;

import alpvax.abilities.api.IAbilityEffect;
import net.minecraft.entity.Entity;

public class EffectMoveSpeed implements IAbilityEffect<Entity>
{

	@Override
	public void trigger(Entity provider, List<Entity> targets)
	{
		//TODO:Add movespeed AttributeModifier
		
	}

	@Override
	public void reset(Entity provider, List<Entity> targets)
	{
		//TODO:Remove movespeed AttributeModifier
	}

	@Override
	public void tick(Entity provider, List<Entity> targets)
	{
	}

	@Override
	public boolean shouldTick(int ticksSinceLast)
	{
		return false;
	}
}
