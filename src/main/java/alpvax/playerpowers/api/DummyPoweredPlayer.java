package alpvax.playerpowers.api;

import java.util.ArrayList;
import java.util.List;

import alpvax.playerpowers.api.power.IPower;
import alpvax.playerpowers.api.power.PowerInstance;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

public class DummyPoweredPlayer implements IPoweredPlayerInstance
{
	private List<PowerInstance> powers = new ArrayList<PowerInstance>();
	private final EntityPlayer attachedPlayer;
	
	public DummyPoweredPlayer(EntityPlayer player)
	{
		attachedPlayer = player;
	}
	
	@Override
	public List<PowerInstance> getPowers()
	{
		return powers;
	}
	
	public List<PowerInstance> getPowers(Class<? extends IPower> powerclass)
	{
		List<PowerInstance> list = new ArrayList<PowerInstance>();
		for(PowerInstance p : getPowers())
		{
			if(powerclass == null || powerclass.isAssignableFrom(p.getPower().getClass()))
			{
				list.add(p);
			}
		}
		return list;
	}

	@Override
	public void triggerPower(KeyBinding key)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityPlayer getPlayer()
	{
		return attachedPlayer;
	}
}
