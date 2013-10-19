package bwg4.map;

import java.util.Random;

import bwg4.biomes.BWG4Biomes;
import bwg4.deco.BWG4decoWasteland;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenVines;

public class BWG4MapGenPocket extends BWG4MapGenBase
{
	private byte ceiling;
	private byte floorTop;
	private byte floorFiller;
	
	private int liquidheight;
	private boolean lava;
	private boolean water;
	
    protected void generateRavine(long par1, int chunkX, int chunkY, byte[] blockarray, double centerX, double centerY, double centerZ)
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
        		double h = 20;
        		h -= Math.floor((Math.sqrt((centerX-i)*(centerX-i) + (centerZ-k)*(centerZ-k)) / 3D) + (noise.turbulence2(i / 80F, k / 80F, 4F) * 20F));
        		
        		if(h > 0)
        		{
        			int h1 = (int) Math.floor(centerY + h);
        			int h2 = (int) Math.floor(centerY - h);
        			
	        		for(int j = 0; j < 128; j++)
	        		{
	        			jj++;
	        			if(j == h1 + 1)
	        			{
	        				if(blockarray[jj] != 0 && rand.nextInt(2) == 0)
	        				{
	        					blockarray[jj] = ceiling;
	        				}
	        			}
	        			if(j == h1)
	        			{
	        				if(blockarray[jj] != 0)
	        				{
	        					blockarray[jj] = ceiling;
	        				}
	        			}
	        			if(j < h1 && j > h2)
	        			{
	        				blockarray[jj] = 0;
	        			}
	        			if(j == h2)
	        			{
	        				if(blockarray[jj] != 0)
	        				{
	        					blockarray[jj] = floorTop;
	        				}
	        			}
	        			if(j == h2 - 1)
	        			{
	        				if(blockarray[jj] != 0 && rand.nextInt(2) == 0)
	        				{
	        					blockarray[jj] = floorFiller;
	        				}
	        			}
	        		}
        		}
        		else
        		{
        			jj += 128;
        		}
        	}
        }
    }

    public void populateRavine(long par1, int chunkX, int chunkY, double centerY, BiomeGenBase biome)
    {
    	int k = chunkX * 16;
    	int l = chunkY * 16; 	

        Random random = new Random(par1);
        random.setSeed(this.worldObj.getSeed());
        long i1 = random.nextLong() / 2L * 2L + 1L;
        long j1 = random.nextLong() / 2L * 2L + 1L;
        random.setSeed((long)chunkX * i1 + (long)chunkY * j1 ^ this.worldObj.getSeed());
    	
        if(rand.nextInt(4) == 0)
        {
            int dx1 = k + random.nextInt(16) + 8;
            int dy1 = (int) Math.floor(centerY);
            int dz1 = l + random.nextInt(16) + 8;
        	(new BWG4decoWasteland(1)).generate(worldObj, random, dx1, dy1, dz1);
        }
        
        for (int j = 0; j < 50; ++j)
        {
            int h1 = k + random.nextInt(16) + 8;
            byte b0 = 5;
            int g1 = l + random.nextInt(16) + 8;
            (new WorldGenVines()).generate(worldObj, random, h1, b0, g1);
        }
    }
    
    public void recursiveGenerate(World world, int bx, int by, int chunkX, int chunkY, byte[] blockarray)
    {
        if (this.rand.nextInt(50) == 0)
        {
            double centerX = (double)(bx * 16 + rand.nextInt(16));
            double centerY = (double)(rand.nextInt(40) + 20);
            double centerZ = (double)(by * 16 + rand.nextInt(16));
            
            setFloorAndCeilBlocks(worldObj.getBiomeGenForCoords((int) Math.floor(centerX), (int) Math.floor(centerZ)));
            
            this.generateRavine(this.rand.nextLong(), chunkX, chunkY, blockarray, centerX, centerY, centerZ);
        }
    }
    
    public double[] find(World world, int bx, int by)
    {
        if (this.rand.nextInt(50) == 0)
        {
            double centerX = (double)(bx * 16 + rand.nextInt(16));
            double centerY = (double)(rand.nextInt(40) + 20);
            double centerZ = (double)(by * 16 + rand.nextInt(16));
            
            return new double[]{centerX,centerY,centerZ};
        }
        
        return null;
    }

	public void populator(World world, double centerX, double centerY, double centerZ, int chunkX, int chunkY)
    {
		double h = 20 - (Math.sqrt((centerX - (chunkX * 16 + 8))*(centerX - (chunkX * 16 + 8)) + (centerZ - (chunkY * 16 + 8))*(centerZ - (chunkY * 16 + 8))) / 3D);
		if(h > 0)
		{
			populateRavine(this.rand.nextLong(), chunkX, chunkY, centerY, worldObj.getBiomeGenForCoords((int) Math.floor(centerX), (int) Math.floor(centerZ)));
		}
    }
	
	public void setFloorAndCeilBlocks(BiomeGenBase biome)
	{
		ceiling = (byte) Block.stone.blockID;
		floorTop = (byte) Block.stone.blockID;
		floorFiller = (byte) Block.stone.blockID;
	}
}
