package alpvax.playerpowers.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import alpvax.common.network.AlpPacketManager;
import alpvax.playerpowers.api.PlayerPowersConstants;
import alpvax.playerpowers.network.PoweredPlayerPacket;


@Mod(modid = PlayerPowersConstants.MOD_ID, name = PlayerPowersConstants.MOD_NAME)
public class PlayerPowersMod
{
	@Mod.Instance(PlayerPowersConstants.MOD_ID)
	public PlayerPowersMod instance;

	public static AlpPacketManager packetHandler;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{

		packetHandler = new AlpPacketManager(PlayerPowersConstants.MOD_ID);
		initPackets();

		PowerAPIHooks hooks = new PowerAPIHooks();
		MinecraftForge.EVENT_BUS.register(hooks);
		FMLCommonHandler.instance().bus().register(hooks);
	}

	private void initPackets()
	{
		packetHandler.registerMessage(PoweredPlayerPacket.Handler.class, PoweredPlayerPacket.class, Side.CLIENT);
	}
}
