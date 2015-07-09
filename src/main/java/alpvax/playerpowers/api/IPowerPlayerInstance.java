package alpvax.playerpowers.api;

import java.util.List;

import alpvax.playerpowers.api.power.IPower;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

public interface IPowerPlayerInstance
{
	public EntityPlayer getPlayer();
	
	public List<IPower> getPowers();
	
	public void triggerPower(KeyBinding key);//TODO: generalise
}
