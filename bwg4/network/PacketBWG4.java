package bwg4.network;

import bwg4.api.gen.GeneratorType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBWG4 
{
	private long worldseed;
	private String genSettings;
	
	public PacketBWG4(long seed, String settings)
	{
		worldseed = seed;
		genSettings = settings;
	}
	
    /* Encode the packet data into the ByteBuf stream. Complex data sets may need specific data handlers */
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
    	byte[] b = genSettings.getBytes();
    	
    	buffer.writeLong(worldseed);
    	buffer.writeBytes(b);
    }

    /* Decode the packet data from the ByteBuf stream. Complex data sets may need specific data handlers */
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
    	byte[] b = new byte[0];
    	
    	worldseed = buffer.readLong();
    	buffer.readBytes(b);
    	
    	genSettings = b.toString();
    }

    /* Handle a packet on the client side. Note this occurs after decoding has completed. */
    public void handleClientSide(EntityPlayer player)
    {
    	System.out.println("===============================================================");
    	
    	GeneratorType.seed = worldseed;
    	
    	if(genSettings != null)
    	{
    		GeneratorType.genString = genSettings;
        	System.out.println("[BWG4]: seed=" + worldseed + " genString=" + genSettings + " ============================================================");
    	}
    }

    /* Handle a packet on the server side. Note this occurs after decoding has completed. */
    public void handleServerSide(EntityPlayer player)
    {
    	
    }
}