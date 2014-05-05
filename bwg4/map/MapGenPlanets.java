package bwg4.map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.util.TerrainMath;
import bwg4.data.Planets;
import bwg4.data.PlanetData;

public class MapGenPlanets extends MapGenBWG4
{	
	public int dimension;
	
	public MapGenPlanets(int d)
	{
		range = 4;
		dimension = d;
	}
	
	public void generatePlanet(long par1, PlanetData data, int size, int chunkX, int chunkY, Block[] blockarray, int centerX, int centerY, int centerZ)
	{
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				int s = 0;
				for(int k = centerZ - size - 2; k < centerZ + size + 2; k++)
				{
					double dis = TerrainMath.dis3(chunkX * 16 + i, chunkY * 16 + j, k, centerX, centerY, centerZ);
					if(dis < size - 2)
					{
						s = 1;
						blockarray[i * 16 * 128 + j * 128 + k] = data.inside;
					}					
					else if(dis < size)
					{
						s = 1;
						blockarray[i * 16 * 128 + j * 128 + k] = data.outside;
					}
					else if(s == 1)
					{
						s = 0;
						blockarray[i * 16 * 128 + j * 128 + k - 1] = data.top;
						if(data.snow)
						{
							blockarray[i * 16 * 128 + j * 128 + k] = Blocks.snow_layer;
						}
					}
				}
			}
		}
	}
	
    public void recursiveGenerate(World world, int bx, int by, int chunkX, int chunkY, Block[] blockarray)
    {
        if (bx % 2 == 0 && by % 2 == 0 )
        {
        	PlanetData data = Planets.getRandomPlanet(rand, dimension);
        	int size = rand.nextInt(data.max - data.min);
            generatePlanet(rand.nextLong(), data, size + data.min, chunkX, chunkY, blockarray, bx * 16 + rand.nextInt(10), by * 16 + rand.nextInt(10), (size + data.min) + 2 + rand.nextInt(127 - ((size + data.min) * 2 + 4)));
        }
    }    
}
