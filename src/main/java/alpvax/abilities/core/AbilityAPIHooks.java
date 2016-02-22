package alpvax.abilities.core;

import alpvax.abilities.capabilities.AbilityCapabilityProvider;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AbilityAPIHooks
{
	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event)
	{
		if(event.wasDeath)
		{
			//TODO:Copy data to new EntityPlayer
		}
	}
	
	@SubscribeEvent
	public void attachCapabilities(AttachCapabilitiesEvent.Entity event)
	{
		event.addCapability(AbilityModConstants.ABILITY_CAPABILITY_PROVIDER, new AbilityCapabilityProvider<Entity>());
	}
}
