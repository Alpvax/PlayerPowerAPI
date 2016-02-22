package alpvax.abilities.api.ability;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAbilityDisplay
{
	@SideOnly(Side.CLIENT)
	public String displayName();
}
