package alpvax.playerpowers.api;

import java.util.ArrayList;
import java.util.List;

import alpvax.playerpowers.api.power.IPower;
import net.minecraft.client.settings.KeyBinding;

public class DummyPoweredPlayer implements IPowerPlayerInstance
{
	private List<IPower> powers = new ArrayList<IPower>();
	
	@Override
	public List<IPower> getPowers()
	{
		return powers;
	}

	@Override
	public void triggerPower(KeyBinding key)
	{
		// TODO Auto-generated method stub
		
	}
}
