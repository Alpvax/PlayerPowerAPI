package alpvax.powers.api.target;

import java.util.EnumSet;

public enum EnumTarget
{
	SELF;

	public static final EnumSet<EnumTarget> NONE = EnumSet.noneOf(EnumTarget.class);
	public static final EnumSet<EnumTarget> ALL = EnumSet.allOf(EnumTarget.class);
}
