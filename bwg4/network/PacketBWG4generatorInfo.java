package bwg4.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import bwg4.api.gen.GeneratorType;

public class PacketBWG4generatorInfo extends PacketBWG4
{
	private long worldseed;
	private String genSettings;
	
	public PacketBWG4generatorInfo(long seed, String settings)
	{
		super();
		
		//System.out.println("CONSTRUCTOR PACKET ===============================================================");
		
		worldseed = seed;
		genSettings = settings;
	}
	
    /* Encode the packet data into the ByteBuf stream. Complex data sets may need specific data handlers */
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
    	//System.out.println("CONSTRUCTING PACKET ===============================================================");
    	
    	byte[] b = genSettings.getBytes();
    	
    	buffer.writeLong(worldseed);
    	buffer.writeBytes(b);
    	
    	System.out.println("");
    }

    /* Decode the packet data from the ByteBuf stream. Complex data sets may need specific data handlers */
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
    	//System.out.println("READ PACKET ===============================================================");
    	
    	byte[] b = new byte[0];
    	
    	worldseed = buffer.readLong();
    	buffer.readBytes(b);
    	
    	genSettings = b.toString();
    }

    /* Handle a packet on the client side. Note this occurs after decoding has completed. */
    public void handleClientSide(EntityPlayer player)
    {
		//System.out.println("HANDLE CLIENT ===============================================================");
		
    	GeneratorType.seed = worldseed;
    	
    	if(genSettings != null)
    	{
    		GeneratorType.genString = genSettings;
        	//System.out.println("[BWG4]: seed=" + worldseed + " genString=" + genSettings + " ============================================================");
    	}
    }

    /* Handle a packet on the server side. Note this occurs after decoding has completed. */
    public void handleServerSide(EntityPlayer player)
    {
		//System.out.println("HANDLE SERVER ===============================================================");
    }
}
