package bwg4.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import bwg4.generatordata.GeneratorType;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class BWG4Packet implements IPacketHandler 
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
		if (packet.channel.equals("BWG4channel")) 
		{
			bwg4packet(packet);
		}
	}

    private void bwg4packet(Packet250CustomPayload packet) 
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int type = 0;
        
        try 
        {
        	type = inputStream.readInt();
        	GeneratorType.Current = GeneratorType.generatortypes[type];
        }
        catch (Exception e) 
        { 
        }

        System.out.println(type);
    }	
}