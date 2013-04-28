package ted80.bwg4.gen.chunkproviders;

import java.util.List;
import java.util.Random;

import ted80.bwg4.deco.BWG4decoSurvival;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class BWG4ChunkProviderSkyBlock implements IChunkProvider
{
    private Random endRNG;
    private World endWorld;
    private double[] densities;
    private BiomeGenBase[] biomesForGeneration;
    int[][] field_73203_h = new int[32][32];
	int getSize = 1;
	private boolean isNether = false;
	private boolean themeClassic = false;
	private boolean themeExtended = false;
	private boolean themeEndless = false;

    public BWG4ChunkProviderSkyBlock(World par1World, long par2, boolean nether, int theme)
    {
        this.endWorld = par1World;
        this.endRNG = new Random(par2);
        isNether = nether;
        	
        if(theme == 1) { themeClassic = true; }
        else if(theme == 2) { themeExtended = true; }
        else if(theme == 3) { themeEndless = true; }
        else { themeClassic = true; }
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.endRNG.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);

        byte[] var3 = new byte[32768];
        Chunk var4 = new Chunk(this.endWorld, var3, par1, par2);
		
        byte[] var5 = var4.getBiomeArray();
		this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }
		
        var4.generateSkylightMap();
        return var4;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = this.endWorld.getBiomeGenForCoords(var4 + 16, var5 + 16);
        var6.decorate(this.endWorld, this.endWorld.rand, var4, var5);
		
        if(themeEndless)
        {
        	if(par2 == 0 && par3 == 0)
        	{
        		(new BWG4decoSurvival(6)).generate(endWorld, endRNG, 0, 70, 0);
        	}
        	else
        	{
        		if(endRNG.nextInt(8) == 0) { (new BWG4decoSurvival(20 + endRNG.nextInt(2))).generate(endWorld, endRNG, var4 + endRNG.nextInt(16), 5 + endRNG.nextInt(240), var5 + endRNG.nextInt(16)); }
        		if(endRNG.nextInt(15) == 0) { (new BWG4decoSurvival(20 + endRNG.nextInt(4))).generate(endWorld, endRNG, var4 + endRNG.nextInt(16), 5 + endRNG.nextInt(60), var5 + endRNG.nextInt(16)); }
        		if(endRNG.nextInt(30) == 0) { (new BWG4decoSurvival(24 + endRNG.nextInt(3))).generate(endWorld, endRNG, var4 + endRNG.nextInt(16), 5 + endRNG.nextInt(40), var5 + endRNG.nextInt(16)); }
        	}
        }
        else if(par2 == 0 && par3 == 0)
		{
			if(isNether)
			{
				if(themeClassic)
				{
					(new BWG4decoSurvival(8)).generate(endWorld, endRNG, 10, 80, 0);
				}	
				if(themeExtended)
				{
					
				}
			}
			else
			{
				if(themeClassic)
				{
					(new BWG4decoSurvival(6)).generate(endWorld, endRNG, 0, 70, 0);
					(new BWG4decoSurvival(7)).generate(endWorld, endRNG, 0, 80, 60);
				}	
				if(themeExtended)
				{
					(new BWG4decoSurvival(6)).generate(endWorld, endRNG, 0, 70, 0);
				}
			}
		}	
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.endWorld.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2) {}
}
