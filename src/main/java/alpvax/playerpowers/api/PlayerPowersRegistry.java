package alpvax.playerpowers.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alpvax.playerpowers.api.power.IPower;

public class PlayerPowersRegistry
{
	private static List<IPoweredPlayer> poweredPlayers = new ArrayList<IPoweredPlayer>();
	
	public static void registerPoweredPlayerType(IPoweredPlayer ppt)
	{
		if(!poweredPlayers.contains(ppt))
		{
			poweredPlayers.add(ppt);
		}
	}
	
	private static Map<String, IPower> powers = new HashMap<String, IPower>();
	
	public static void registerPower(IPower power)
	{
		if(!powers.containsKey(power.getID()))
		{
			powers.put(power.getID(), power);
		}
	}

	public static IPower getPower(String id)
	{
		return powers.get(id);
	}
}
