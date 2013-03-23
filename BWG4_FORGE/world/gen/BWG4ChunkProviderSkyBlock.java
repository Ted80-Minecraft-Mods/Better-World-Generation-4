package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.MapGenScatteredFeature;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.feature.*;

public class BWG4ChunkProviderSkyBlock implements IChunkProvider
{
    private Random endRNG;
    private World endWorld;
    private double[] densities;
    private BiomeGenBase[] biomesForGeneration;
    int[][] field_73203_h = new int[32][32];
	int getSize = 1;
	boolean isNether = false;

    public BWG4ChunkProviderSkyBlock(World par1World, long par2, boolean nether)
    {
        this.endWorld = par1World;
        this.endRNG = new Random(par2);
		isNether = nether;
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
		
		if(par2 == 0 && par3 == 0)
		{
			if(isNether)
			{
				(new BWG4decoSurvival(8)).generate(endWorld, endRNG, 10, 80, 0);
			}
			else
			{
				(new BWG4decoSurvival(6)).generate(endWorld, endRNG, 0, 70, 0);
				(new BWG4decoSurvival(7)).generate(endWorld, endRNG, 0, 80, 60);
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
