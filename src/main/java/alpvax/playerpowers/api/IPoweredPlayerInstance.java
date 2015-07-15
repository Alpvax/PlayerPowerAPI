package alpvax.playerpowers.api;

import java.util.ArrayList;
import java.util.List;

import alpvax.playerpowers.api.power.IPower;
import alpvax.playerpowers.api.power.PowerInstance;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

public interface IPoweredPlayerInstance
{
	public EntityPlayer getPlayer();
	
	public List<PowerInstance> getPowers();
	
	public List<PowerInstance> getPowers(Class<? extends IPower> powerclass);
	
	public void triggerPower(KeyBinding key);//TODO: generalise
}
