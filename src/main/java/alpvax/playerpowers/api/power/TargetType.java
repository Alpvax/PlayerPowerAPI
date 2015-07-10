package alpvax.playerpowers.api.power;

import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class TargetType
{
	public static enum TriggerTarget
	{
		SELF, POINT, ENTITY;
	}
	public static enum EntitiesEffected
	{
		NONE, SINGLE, AOE, CHAIN;
	}
	public static enum AimType
	{
		EXACT, NEAREST;
	}

	private TriggerTarget t;
	private EntitiesEffected e;
	private AimType a;
	
	public TargetType(TriggerTarget t, EntitiesEffected e, AimType a)
	{
		this.t = t;
		this.e = e;
		this.a = a;
	}
	//TODO "find" methods

	public Entity findEntity(EntityPlayer player, double radius, Predicate<Entity> filter)
	{
		return null;
	}
	public List<Entity> findEntities(EntityPlayer player, double radius, Predicate<Entity> filter)
	{
		return null;
	}
	
	public Vec3 findPosition(EntityPlayer player)
	{
		return null;
	}
}