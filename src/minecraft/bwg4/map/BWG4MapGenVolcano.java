package bwg4.map;

import java.util.Random;

import bwg4.util.Coords;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BWG4MapGenVolcano extends BWG4MapGenBase
{
	public static boolean randompos;
	public static int posX;
	public static int posY;
	public static int posZ;
	
	public BWG4MapGenVolcano()
	{
		randompos = true;
	}
	
    protected void generateVolcano(long par1, int chunkX, int chunkY, byte[] blockarray, double centerX, double centerY, double centerZ)
    {
        Random random = new Random(par1);
        int x = chunkX * 16;
        int y = chunkY * 16;
        
        PerlinNoise noise = new PerlinNoise(par1);
        
        int jj = 0;
        for(int i = x; i < x + 16; i++)
        {
        	for(int k = y; k < y + 16; k++)
        	{
        		double distance = Coords.getDistance(i, k, centerX, centerZ) - 8;
        		
        		if(distance + 8 < 30)
        		{
	        		int heigthground;
	        		int heigthlava;
	        		
	        		if(distance > 1)
	        		{
	        			heigthground = (int) Math.floor(18D / (distance / 30D));
	        			heigthlava = (int) Math.floor(18D / (distance / 25D));
	        		}
	        		else
	        		{
	        			heigthground = (int) Math.floor(18D / (1D / 30D));
	        			heigthlava = (int) Math.floor(18D / (1D / 25D));
	        		}
	        		
	        		if(heigthground + 50 > 128)
	        		{
	        			heigthground = 128 - 50;
	        		}
	        		
	        		if(heigthground > 60)
	        		{
	        			heigthground -= rand.nextInt(8);
	        		}
	        		
	        		jj+=128;
	        		for(int j = 127; j > -1; j--)
	        		{
	        			jj--;
	
						if(j < heigthground + 50 && j > 59)
						{
							if(j < heigthlava)
							{
		        				if(blockarray[jj] == 0 || blockarray[jj] == 3)
								{
		        					if(j > 10)
		        					{
			        					blockarray[jj] = 0;
		        					}
		        					else
		        					{
			        					blockarray[jj] = (byte)Block.lavaStill.blockID;
		        					}
								}
							}
							else
							{
		        				if(blockarray[jj] == 0 || blockarray[jj] == 3)
								{
		        					blockarray[jj] = (byte)Block.obsidian.blockID;
								}
							}
						}
	        		}
	        		jj+=128;
        		}
        		else
        		{
	        		jj+=128;
        		}
        	}
        }
    }
    
    public void recursiveGenerate(World world, int bx, int by, int chunkX, int chunkY, byte[] blockarray)
    {
    	if (randompos)
    	{
	        if (this.rand.nextInt(140) == 0)
	        {
	            double centerX = (double)(bx * 16 + rand.nextInt(16));
	            double centerY = 64D;
	            double centerZ = (double)(by * 16 + rand.nextInt(16));
	            
	            generateVolcano(this.rand.nextLong(), chunkX, chunkY, blockarray, centerX, centerY, centerZ);
	        }
    	}
    	else
    	{
    		if(chunkX > bx - 8 && chunkX < bx + 8 && chunkY > by - 8 && chunkY < by + 8)
    		{
	            double centerX = (double) posX;
	            double centerY = (double) posY;
	            double centerZ = (double) posZ;
	            
	            generateVolcano(this.rand.nextLong(), chunkX, chunkY, blockarray, centerX, centerY, centerZ);
    		}
    	}
    }
}
