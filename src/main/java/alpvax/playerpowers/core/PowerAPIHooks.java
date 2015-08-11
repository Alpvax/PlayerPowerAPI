package alpvax.playerpowers.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import org.apache.logging.log4j.Level;

import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.api.item.IItemPowerProvider;
import alpvax.playerpowers.api.player.PoweredPlayer;
import alpvax.playerpowers.api.power.IPower;


public class PowerAPIHooks
{
	@SubscribeEvent
	public void onLogIn(PlayerLoggedInEvent e)
	{
		PoweredPlayer p = PoweredPlayer.get(e.player);
		if(p != null)
		{
			FMLLog.log(PlayerPowersConstants.LOG_TAG, Level.INFO, "Player %s has logged in with %d power providers: %s", e.player.getDisplayNameString(), p.numProviders(), p.listProviders());
		}
	}

	@SubscribeEvent
	public void onCreate(EntityConstructing e)
	{
		if(e.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)e.entity;
			if(PoweredPlayer.get(player) == null)
			{
				PoweredPlayer.register(player);
			}
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone e)
	{
		if(e.wasDeath)
		{
			PoweredPlayer.get(e.entityPlayer).cloneOnDeath(PoweredPlayer.get(e.original));
		}
	}

	@SubscribeEvent
	public void onJoinWorld(EntityJoinWorldEvent e)
	{
		if(e.entity instanceof EntityPlayerMP)
		{
			/*TODO:create packet and send to player: sendTo(msg, player)
			PoweredPlayer.get((EntityPlayer)e.entity).getDataForClient();*/
		}
	}

	@SubscribeEvent
	public void onPickup(ItemPickupEvent e)
	{
		ItemStack stack = e.pickedUp.getEntityItem();
		if(stack.getItem() instanceof IItemPowerProvider)
		{
			IItemPowerProvider item = (IItemPowerProvider)stack.getItem();
			IItemPowerProvider.Provider pp = item.getProviderFromStack(stack);
			pp.readPowersFromNBT();
			PoweredPlayer.get(e.player).addProvider(item.itemKey(), pp);
		}
	}

	@SubscribeEvent
	public void onDrop(ItemTossEvent e)
	{
		ItemStack stack = e.entityItem.getEntityItem();
		if(stack.getItem() instanceof IItemPowerProvider)
		{
			IItemPowerProvider item = (IItemPowerProvider)stack.getItem();
			item.getProviderFromStack(stack).writePowersToNBT();
			PoweredPlayer.get(e.player).removeProvider(item.itemKey());
		}
	}

	@SubscribeEvent
	public void onTick(LivingUpdateEvent e)
	{
		if(e.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)e.entityLiving;
			PoweredPlayer pp = PoweredPlayer.get(player);
			if(pp != null)
			{
				for(IPower p : pp.getPowers(null))
				{
					p.tick();
					if(p.isActive())
					{
						p.onActiveTick(player);
					}
				}
			}
		}
	}
}
