package alpvax.playerpowers.core;

import alpvax.playerpowers.api.PlayerPowersConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PlayerPowersConstants.MOD_ID, name = PlayerPowersConstants.MOD_NAME)
public class PlayerPowersMod
{
	@Mod.Instance(PlayerPowersConstants.MOD_ID)
	public PlayerPowersMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		PowerAPIHooks hooks = new PowerAPIHooks();
		MinecraftForge.EVENT_BUS.register(hooks);
		FMLCommonHandler.instance().bus().register(hooks);
	}
}
