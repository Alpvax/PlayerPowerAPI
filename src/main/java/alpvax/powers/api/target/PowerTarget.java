package alpvax.powers.api.target;

import java.util.List;

public class PowerTarget<T>
{
	public final EnumTarget type;
	public final List<T> targets;
	
	public PowerTarget(EnumTarget type, List<T> targets)
	{
		this.type = type;
		this.targets = targets;
	}
}
