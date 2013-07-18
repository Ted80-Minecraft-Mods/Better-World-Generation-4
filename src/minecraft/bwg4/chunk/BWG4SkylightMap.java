package bwg4.chunk;

import java.lang.reflect.Field;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class BWG4SkylightMap 
{
	public static boolean ready = false;
	
	public static void load()
	{
		ready = true;
		/*
		try
		{
			Field field1 = Chunk.class.getDeclaredField("isGapLightingUpdated");
	        field1.setAccessible(true);
	        
			Field field2 = Chunk.class.getDeclaredField("storageArrays");
	        field2.setAccessible(true);
		}
		catch(Exception e)
		{
			System.out.println("[BWG4] FAILED TO LOAD SKYLIGHTMAP GENERATOR!");
			ready = false;
		}
		*/
	}
	
	public static Chunk generate(Chunk chunk)
	{
		/*
		if(!ready)
		{
			chunk.generateSkylightMap();
		}
		else
		{ 
			
		}
		*/
		return chunk;
	}
	
	public static Chunk generate162(Chunk chunk)
	{
		/*
		if(!ready)
		{
			chunk.generateSkylightMap();
		}
		else
		{
	        int i = chunk.getTopFilledSegment();
	        chunk.heightMapMinimum = Integer.MAX_VALUE;
	        int j;
	        int k;
	
	        for (j = 0; j < 16; ++j)
	        {
	            k = 0;
	
	            while (k < 16)
	            {
	            	chunk.precipitationHeightMap[j + (k << 4)] = -999;
	                int l = i + 16 - 1;
	
	                while (true)
	                {
	                    if (l > 0)
	                    {
	                        if (chunk.getBlockLightOpacity(j, l - 1, k) == 0)
	                        {
	                            --l;
	                            continue;
	                        }
	
	                        chunk.heightMap[k << 4 | j] = l;
	
	                        if (l < chunk.heightMapMinimum)
	                        {
	                        	chunk.heightMapMinimum = l;
	                        }
	                    }
	
	                    if (!chunk.worldObj.provider.hasNoSky)
	                    {
	                        l = 15;
	                        int i1 = i + 16 - 1;
	
	                        do
	                        {
	                            l -= chunk.getBlockLightOpacity(j, i1, k);
	
	                            if (l > 0)
	                            {
	                                ExtendedBlockStorage extendedblockstorage = chunk.storageArrays[i1 >> 4];
	
	                                if (extendedblockstorage != null)
	                                {
	                                    extendedblockstorage.setExtSkylightValue(j, i1 & 15, k, l);
	                                    chunk.worldObj.markBlockForRenderUpdate((chunk.xPosition << 4) + j, i1, (chunk.zPosition << 4) + k);
	                                }
	                            }
	
	                            --i1;
	                        }
	                        while (i1 > 0 && l > 0);
	                    }
	
	                    ++k;
	                    break;
	                }
	            }
	        }
	
	        chunk.isModified = true;
	
	        for (j = 0; j < 16; ++j)
	        {
	            for (k = 0; k < 16; ++k)
	            {
	            	chunk.updateSkylightColumns[j + k * 16] = true;
	            	chunk.isGapLightingUpdated = true;
	            }
	        }
		}
		*/
		return chunk;
	}
}
