package alpvax.playerpowers.api;

import java.util.List;

import alpvax.playerpowers.api.power.IPower;
import alpvax.playerpowers.api.power.PowerInstance;

public interface IPoweredPlayer
{
	/**
	 * @return a unique id to reference this class. Uses the form <group>.<id>
	 */
	public String getID();

	public String getDisplayName();
	
	public List<PowerInstance.Builder> getPowers();
}
