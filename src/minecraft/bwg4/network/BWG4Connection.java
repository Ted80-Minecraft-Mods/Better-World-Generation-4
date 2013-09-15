package bwg4.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import bwg4.generatordata.BWG4GeneratorType;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class BWG4Connection implements IPacketHandler, IConnectionHandler 
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
	}
	
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) 
	{
		if(BWG4GeneratorType.currentGenerator != null)
		{
			EntityPlayerMP p = (EntityPlayerMP) player;
			if(!p.worldObj.isRemote)
			{
				ByteArrayOutputStream bytearray = new ByteArrayOutputStream(8);
				DataOutputStream outputStream = new DataOutputStream(bytearray);
				
				try 
				{
					outputStream.writeInt(BWG4GeneratorType.currentGenerator.GetID());
				} 
				catch (Exception ex) 
				{
				}
				
		        Packet250CustomPayload packet = new Packet250CustomPayload();
		        packet.channel = "BWG4channel";
		        packet.data = bytearray.toByteArray();
		        packet.length = bytearray.size();
	
		        ((NetServerHandler)netHandler).sendPacketToPlayer(packet);
			}
		}
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) 
	{
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) 
	{
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) 
	{
	}

	@Override
	public void connectionClosed(INetworkManager manager) 
	{
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) 
	{
	}
}
