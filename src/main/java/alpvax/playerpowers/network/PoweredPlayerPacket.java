package alpvax.playerpowers.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import alpvax.playerpowers.api.power.IPower;


public class PoweredPlayerPacket implements IMessage
{
	public class Handler implements IMessageHandler<PoweredPlayerPacket, IMessage>
	{
		@Override
		public IMessage onMessage(PoweredPlayerPacket message, MessageContext ctx)
		{
			return null;// TODO Set data for player from map
		}
	}

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
