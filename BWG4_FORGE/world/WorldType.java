package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldType
{
    public static final BiomeGenBase[] base11Biomes = new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga};
    public static final BiomeGenBase[] base12Biomes = ObjectArrays.concat(base11Biomes, BiomeGenBase.jungle);

	//WORLDTYPES LIST
    public static final WorldType[] worldTypes = new WorldType[32];
	
	//VANILLA WORLDTYPES
    public static final WorldType DEFAULT = (new WorldType(0, "default", 1)).setVersioned();
    public static final WorldType FLAT = new WorldType(1, "flat");
    public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
    public static final WorldType DEFAULT_1_1 = (new WorldType(8, "default_1_1", 0)).setCanBeCreated(false);

	//BWG4 BIG WORLDTYPES
	public static final WorldType BWG4DEFAULT = (new WorldType(10, "BetterDefault")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4LARGE = (new WorldType(11, "LargeDefault")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4BETA1 = new WorldType(12, "Beta"); 
	public static final WorldType BWG4BETA2 = (new WorldType(13, "BetaDefault")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4BETA3 = (new WorldType(14, "BetaGold")).setCanBeCreated(false); 
	public static final WorldType BWG4ALPHA = new WorldType(15, "Alpha"); 
	public static final WorldType BWG4INFDEV = new WorldType(16, "Infdev"); 
	public static final WorldType BWG4INDEV1 = new WorldType(17, "Indev");
	public static final WorldType BWG4INDEV2 = (new WorldType(18, "Indev_floating")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4GOLD = new WorldType(19, "Gold"); 
	
	//BWG4 SURVIVAL WORLDTYPES	
    public static final WorldType BWG4ISLAND = new WorldType(21, "Survival Island");
    public static final WorldType BWG4SKYLAND = new WorldType(22, "Survival Skyland");
    public static final WorldType BWG4SKYBLOCK = new WorldType(23, "Skyblock");
	
	//BWG4 FUN/DIMENSION WORLDTYPES
    public static final WorldType BWG4SKY1 = new WorldType(25, "Sky Dimension"); 
    public static final WorldType BWG4SKY2 = (new WorldType(26, "SkyDimensionBeta")).setCanBeCreated(false); //SMP
    public static final WorldType BWG4SKY3 = (new WorldType(27, "SkyDimensionGold")).setCanBeCreated(false); 
    public static final WorldType BWG4CAVE = (new WorldType(28, "Cave Dimension")).setCanBeCreated(false);  
    public static final WorldType BWG4HARD = (new WorldType(29, "Hardcore")).setCanBeCreated(false);  
    public static final WorldType BWG4WASTE = (new WorldType(30, "WasteLand")).setCanBeCreated(false);  
    
    private final int worldTypeId;
    private final String worldType;
    private final int generatorVersion;
    private boolean canBeCreated;
    private boolean isWorldTypeVersioned;

    protected BiomeGenBase[] biomesForWorldType;

    public WorldType(int par1, String par2Str)
    {
        this(par1, par2Str, 0);
    }

    public WorldType(int par1, String par2Str, int par3)
    {
        this.worldType = par2Str;
        this.generatorVersion = par3;
        this.canBeCreated = true;
        this.worldTypeId = par1;
        worldTypes[par1] = this;

        switch (par1)
        {
            case 8:
                biomesForWorldType = base11Biomes;
                break;
            default:
                biomesForWorldType = base12Biomes;
        }
    }

    public String getWorldTypeName()
    {
        return this.worldType;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
		if(worldType == "default" || worldType == "flat" || worldType == "largeBiomes" || worldType == "default_1_1")
		{
			return "generator." + this.worldType;
		}
		else
		{
			return this.worldType;
		}
    }

    /**
     * Returns generatorVersion.
     */
    public int getGeneratorVersion()
    {
        return this.generatorVersion;
    }

    public WorldType getWorldTypeForGeneratorVersion(int par1)
    {
        return this == DEFAULT && par1 == 0 ? DEFAULT_1_1 : this;
    }

    /**
     * Sets canBeCreated to the provided value, and returns this.
     */
    private WorldType setCanBeCreated(boolean par1)
    {
        this.canBeCreated = par1;
        return this;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets whether this WorldType can be used to generate a new world.
     */
    public boolean getCanBeCreated()
    {
        return this.canBeCreated;
    }

    /**
     * Flags this world type as having an associated version.
     */
    private WorldType setVersioned()
    {
        this.isWorldTypeVersioned = true;
        return this;
    }

    /**
     * Returns true if this world Type has a version associated with it.
     */
    public boolean isVersioned()
    {
        return this.isWorldTypeVersioned;
    }

    public static WorldType parseWorldType(String par0Str)
    {
        for (int i = 0; i < worldTypes.length; ++i)
        {
            if (worldTypes[i] != null && worldTypes[i].worldType.equalsIgnoreCase(par0Str))
            {
                return worldTypes[i];
            }
        }

        return null;
    }

    public int getWorldTypeID()
    {
        return this.worldTypeId;
    }

    public WorldChunkManager getChunkManager(World world)
    {
        if (this == FLAT)
        {
            FlatGeneratorInfo flatgeneratorinfo = FlatGeneratorInfo.createFlatGeneratorFromString(world.getWorldInfo().getGeneratorOptions());
            return new WorldChunkManagerHell(BiomeGenBase.biomeList[flatgeneratorinfo.getBiome()], 0.5F, 0.5F);
        }
        else
        {
            return new WorldChunkManager(world);
        }
    }

    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return (this == FLAT ? new ChunkProviderFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions) : new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled()));
    }

    public int getMinimumSpawnHeight(World world)
    {
        return this == FLAT ? 4 : 64;
    }

    public double getHorizon(World world)
    {
        return this == FLAT ? 0.0D : 63.0D;
    }

    public boolean hasVoidParticles(boolean flag)
    {
        return this != FLAT && !flag;
    }

    public double voidFadeMagnitude()
    {
        return this == FLAT ? 1.0D : 0.03125D;
    }

    public BiomeGenBase[] getBiomesForWorldType() {
        return biomesForWorldType;
    }

    public void addNewBiome(BiomeGenBase biome)
    {
        Set<BiomeGenBase> newBiomesForWorld = Sets.newLinkedHashSet(Arrays.asList(biomesForWorldType));
        newBiomesForWorld.add(biome);
        biomesForWorldType = newBiomesForWorld.toArray(new BiomeGenBase[0]);
    }

    public void removeBiome(BiomeGenBase biome)
    {
        Set<BiomeGenBase> newBiomesForWorld = Sets.newLinkedHashSet(Arrays.asList(biomesForWorldType));
        newBiomesForWorld.remove(biome);
        biomesForWorldType = newBiomesForWorld.toArray(new BiomeGenBase[0]);
    }

    public boolean handleSlimeSpawnReduction(Random random, World world)
    {
        return this == FLAT ? random.nextInt(4) != 1 : false;
    }

    /**
     * Called when 'Create New World' button is pressed before starting game
     */
    public void onGUICreateWorldPress() { }

    /**
     * Gets the spawn fuzz for players who join the world.
     * Useful for void world types.
     * @return Fuzz for entity initial spawn in blocks.
     */
    public int getSpawnFuzz()
    {
        return 20;
    }

    /**
     * Called when the 'Customize' button is pressed on world creation GUI
     * @param instance The minecraft instance
     * @param guiCreateWorld the createworld GUI
     */
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
    {
        if (this == FLAT)
        {
            instance.displayGuiScreen(new GuiCreateFlatWorld(guiCreateWorld, guiCreateWorld.generatorOptionsToUse));
        }
    }

    /*
     * Should world creation GUI show 'Customize' button for this world type?
     * @return if this world type has customization parameters
     */
    public boolean isCustomizable()
    {
        return this == FLAT;
    }
}
