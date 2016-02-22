package alpvax.powerproviders.api.target;

import java.util.ArrayList;
import java.util.List;

import alpvax.powerproviders.api.entity.EntityPowerTarget;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import scala.actors.threadpool.Arrays;

public abstract class PowerTarget
{
	public static final PowerTarget SELF = new PowerTarget()
	{
		@SuppressWarnings("unchecked")
		@Override
		public List<Entity> getTargets(Entity powered)
		{
			return Arrays.asList(new Entity[]{powered});
		}
	};
	
	public abstract List<Entity> getTargets(Entity powered);

	public static abstract class PointTarget extends PowerTarget
	{
		@Override
		public List<Entity> getTargets(Entity powered)
		{
			List<Entity> list = new ArrayList<Entity>();
			List<Vec3> points = getPoints(powered);
			if(points != null)
			{
				for(Vec3 point : points)
				{
					list.add(new EntityPowerTarget(powered.worldObj, point.xCoord, point.yCoord, point.zCoord));
				}
			}
			return list; 
		}

		public abstract List<Vec3> getPoints(Entity powered);
	}
}
