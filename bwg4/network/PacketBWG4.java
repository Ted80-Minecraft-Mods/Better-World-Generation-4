package bwg4.network;

import bwg4.generatortype.GeneratorType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PacketBWG4 
{
    /* Encode the packet data into the ByteBuf stream. Complex data sets may need specific data handlers */
    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    /* Decode the packet data from the ByteBuf stream. Complex data sets may need specific data handlers */
    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    /* Handle a packet on the client side. Note this occurs after decoding has completed. */
    public abstract void handleClientSide(EntityPlayer player);

    /* Handle a packet on the server side. Note this occurs after decoding has completed. */
    public abstract void handleServerSide(EntityPlayer player);
}