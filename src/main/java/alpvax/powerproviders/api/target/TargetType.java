package alpvax.powerproviders.api.target;

import java.util.List;

import net.minecraft.entity.Entity;
import scala.actors.threadpool.Arrays;

public abstract class TargetType<T>
{
	public static final TargetType<Entity> SELF = new TargetType<Entity>()
	{
		@SuppressWarnings("unchecked")
		@Override
		public List<Entity> getTargets(Entity powered)
		{
			return Arrays.asList(new Entity[]{powered});
		}
	};
	
	public abstract List<T> getTargets(Entity powered);
}
