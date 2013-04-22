package ted80.bwg4.layer;

import java.util.ArrayList;

import ted80.api.DefaultBiomeList;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4LayerBiome extends BWG4Layer
{
    public static BiomeGenBase[] Biomes;
	public static ArrayList<BiomeGenBase> allowedBiomes = new ArrayList<BiomeGenBase>();
	public int worldID;

    public BWG4LayerBiome(long par1, BWG4Layer par3GenLayer, WorldType par4WorldType, String[] Settings, int world, boolean remote)
    {
        super(par1);
		worldID = world;
		parent = par3GenLayer;
		
		//DEFAULT
		Biomes = (new BiomeGenBase[] { });
		boolean ocean = false;
		if(world == 0)
		{
			if(remote)
			{
				int count = 0;
				allowedBiomes.clear();
				
				ArrayList<BiomeGenBase> biomesinput = DefaultBiomeList.getBiomeList();
				String[] biomesettingsinput = DefaultBiomeList.getStringList();
				int[] biomeTypes = DefaultBiomeList.getTypesList();
				
				for(int i = 0; i < Settings.length; i++)
				{
					for(int b = 0; b < biomesettingsinput.length; b++)
					{
						if(biomesettingsinput[b] != null)
						{
							if(Settings[i].equals(biomesettingsinput[b]) && biomeTypes[b] > 0 && biomeTypes[b] < 5) 
							{ 
								allowedBiomes.add(biomesinput.get(b));
								count++;
								break;
							}
							else if(Settings[i].equals("Ocean=true") && biomeTypes[b] == 0)
							{
								ocean = true;
							}
						}
						else
						{
							break;
						}
					}
				}
				
				if(count == 0) 
				{ 
					if(ocean == true)
					{
						allowedBiomes.add(BiomeGenBase.BDocean); 
					}
					else
					{
						allowedBiomes.add(BiomeGenBase.BDplains); 
					}	
				}
				Biomes = allowedBiomes.toArray(new BiomeGenBase[0]);
			}
			else 
			{
				allowedBiomes.clear();
				allowedBiomes.add(BiomeGenBase.plains);
				Biomes = allowedBiomes.toArray(new BiomeGenBase[0]);
			}
		}	
	} 
	
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] var5 = this.parent.getInts(par1, par2, par3, par4);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + var7 * par3];

				if (var9 == 0)
				{
					var6[var8 + var7 * par3] = BiomeGenBase.BDocean.biomeID;
				}
				else if (var9 == BiomeGenBase.mushroomIsland.biomeID)
				{
					var6[var8 + var7 * par3] = BiomeGenBase.mushroomIsland.biomeID;
				}
				else 
				{
					var6[var8 + var7 * par3] = this.Biomes[this.nextInt(this.Biomes.length)].biomeID;
				}
            }
        }

        return var6;
    }
}
