package alpvax.powerproviders.core;

import alpvax.common.network.AlpPacketManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = PowersConstants.MOD_ID, name = PowersConstants.MOD_NAME)
public class PowerAPI
{
	@Mod.Instance(PowersConstants.MOD_ID)
	public PowerAPI instance;

	public static AlpPacketManager packetHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{

		packetHandler = new AlpPacketManager(PowersConstants.MOD_ID);
		initPackets();

		PowerAPIHooks hooks = new PowerAPIHooks();
		MinecraftForge.EVENT_BUS.register(hooks);
		FMLCommonHandler.instance().bus().register(hooks);
	}

	private void initPackets()
	{
		//TODO: packetHandler.registerMessage(PoweredPlayerPacket.Handler.class, PoweredPlayerPacket.class, Side.CLIENT);
	}
}
