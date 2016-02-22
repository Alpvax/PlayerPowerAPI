package alpvax.abilities.core;

import alpvax.abilities.capabilities.CapabilityAbilityHandler;
import alpvax.common.network.AlpPacketManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = AbilityModConstants.MOD_ID, name = AbilityModConstants.MOD_NAME)
public class AbilityAPI
{
	@Mod.Instance(AbilityModConstants.MOD_ID)
	public AbilityAPI instance;

	public static AlpPacketManager packetHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{

		packetHandler = new AlpPacketManager(AbilityModConstants.MOD_ID);
		initPackets();

		CapabilityAbilityHandler.register();
		MinecraftForge.EVENT_BUS.register(new AbilityAPIHooks());
	}

	private void initPackets()
	{
		//TODO: packetHandler.registerMessage(PoweredPlayerPacket.Handler.class, PoweredPlayerPacket.class, Side.CLIENT);
	}
}
