package alpvax.abilities.api.provider;

import java.util.List;

import alpvax.abilities.template.AbilityEntry;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public interface IAPTemplate<T>
{	
	public List<AbilityEntry<T>> abilities();
	public String displayName();
	public boolean hasPermissionForGUI(EntityPlayer setter, T target);
	public boolean hasPermissionForCmd(ICommandSender setter, T target);
}
