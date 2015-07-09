package alpvax.playerpowers.api.power;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public interface IPower
{
	public String getID();

	public String displayName();
	
	public void onActiveTick(EntityPlayer user, Entity targetEntity, Vec3 targetPos);
	
	public void activate(EntityPlayer user, Entity targetEntity, Vec3 targetPos);
	
	public void deactivate(EntityPlayer user, Entity targetEntity, Vec3 targetPos);


	
	public static enum EnumPowerType
	{
		/**Does something when triggered, but doesn't remain active*/
		INSTANT,
		/**Once activated, remains active for a specified duration before automatically deactivating*/
		TIMED,
		/**Can be toggled on and off, has an unlimited duration*/
		TOGGLED,
		/**Always active*/
		CONTINUOUS;
	}
}
