package alpvax.playerpowers.network;

import alpvax.playerpowers.api.power.IPower;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PoweredPlayerPacket implements IMessage
{
	public PoweredPlayerPacket()
	{
	}
	
	public void addPowerData(String providerKey, String powerKey, IPower power)
	{
		//TODO:add data to map
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		// TODO Fill map with data
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		// TODO Write map to data
	}

}
