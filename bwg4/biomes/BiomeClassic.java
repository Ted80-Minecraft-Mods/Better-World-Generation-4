package bwg4.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeClassic extends BiomeGenBase
{
    public BiomeClassic(int par1)
    {
        super(par1);
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 2, 1, 1));
    }
    
    @SideOnly(Side.CLIENT)
    public int func_150558_b(int a, int b, int c)
    {
        return 0xABFF67;
    }
    
    @SideOnly(Side.CLIENT)
    public int func_150571_c(int a, int b, int c)
    {
        return 0x4FFF2B;
    }  
}
