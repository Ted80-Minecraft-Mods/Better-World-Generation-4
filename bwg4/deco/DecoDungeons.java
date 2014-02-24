package bwg4.deco;

import java.util.Random;

import bwg4.data.DungeonLoot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

public class DecoDungeons extends WorldGenerator
{
	public boolean checkpos = true;
	public boolean isSkyDungeon = false;
	public boolean isEndDungeon = false;
	public boolean randomChest = false;
	public int chestID = 0;
	
    public DecoDungeons(int id, boolean random, boolean check, boolean sky, boolean end)
    {
		checkpos = check;
		isSkyDungeon = sky;
		isEndDungeon = end;
		chestID = id;
		
		if(random) // 9 10
		{
			randomChest = true;
		}
    }
    
    public DecoDungeons(int id, boolean random)
    {
    	this(0, random, false, false, false);
    }
    
    public DecoDungeons(int id)
    {
    	this(id, false, true, false, false);
    }
    
    public DecoDungeons()
    {
    	this(0, false, true, false, false);
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
		if(isSkyDungeon || isEndDungeon)
		{
			boolean chain1 = false, chain2 = false, chain3 = false, chain4 = false;
		
			//Check for chains
			for(int ch1 = par4; ch1 < 60; ch1++) { if(!par1World.func_147437_c(par3 + 4, ch1 + 5, par5 + 4)) { chain1 = true; break; } }
			for(int ch2 = par4; ch2 < 60; ch2++) { if(!par1World.func_147437_c(par3 + 4, ch2 + 5, par5 - 4)) { chain2 = true; break; } }
			for(int ch3 = par4; ch3 < 60; ch3++) { if(!par1World.func_147437_c(par3 - 4, ch3 + 5, par5 + 4)) { chain3 = true; break; } }
			for(int ch4 = par4; ch4 < 60; ch4++) { if(!par1World.func_147437_c(par3 - 4, ch4 + 5, par5 - 4)) { chain4 = true; break; } }
			
			if(chain1 == false || chain2 == false || chain3 == false || chain4 == false )
			{
				return false;
			}
			
			//Build Chains
			for(int chy1 = par4 + 5; chy1 < 70 ; chy1++) { if(!par1World.func_147437_c(par3 + 4, chy1, par5 + 4)) { break; } par1World.func_147449_b(par3 + 4, chy1, par5 + 4, Blocks.iron_bars); }		
			for(int chy2 = par4 + 5; chy2 < 70 ; chy2++) { if(!par1World.func_147437_c(par3 + 4, chy2, par5 - 4)) { break; } par1World.func_147449_b(par3 + 4, chy2, par5 - 4, Blocks.iron_bars); }		
			for(int chy3 = par4 + 5; chy3 < 70 ; chy3++) { if(!par1World.func_147437_c(par3 - 4, chy3, par5 + 4)) { break; } par1World.func_147449_b(par3 - 4, chy3, par5 + 4, Blocks.iron_bars); }		
			for(int chy4 = par4 + 5; chy4 < 70 ; chy4++) { if(!par1World.func_147437_c(par3 - 4, chy4, par5 - 4)) { break; } par1World.func_147449_b(par3 - 4, chy4, par5 - 4, Blocks.iron_bars); }	
			if(checkpos)
			{	
				par1World.func_147449_b(par3 + 4, par4 + 6, par5 + 4, Blocks.nether_brick);
				par1World.func_147449_b(par3 + 4, par4 + 6, par5 - 4, Blocks.nether_brick);
				par1World.func_147449_b(par3 - 4, par4 + 6, par5 + 4, Blocks.nether_brick);
				par1World.func_147449_b(par3 - 4, par4 + 6, par5 - 4, Blocks.nether_brick);
			}
			else
			{
				par1World.func_147449_b(par3 + 4, par4 + 6, par5 + 4, Blocks.mossy_cobblestone);
				par1World.func_147449_b(par3 + 4, par4 + 6, par5 - 4, Blocks.mossy_cobblestone);
				par1World.func_147449_b(par3 - 4, par4 + 6, par5 + 4, Blocks.mossy_cobblestone);
				par1World.func_147449_b(par3 - 4, par4 + 6, par5 - 4, Blocks.mossy_cobblestone);
			}	
			
			//Build SkyDungeon
			for(int x1 = par3 - 4; x1 < par3 + 5 ; x1++)
			{
				for(int y1 = par4; y1 < par4 + 6 ; y1++)
				{
					for(int z1 = par5 - 4; z1 < par5 + 5 ; z1++)
					{
						if (par2Random.nextInt(10) == 0)
						{
							par1World.func_147449_b(x1, y1, z1, Blocks.air);
						}
						else
						{
							if(checkpos)
							{
								par1World.func_147449_b(x1, y1, z1, Blocks.nether_brick);
							}
							else
							{
								if (par2Random.nextInt(2) != 0)
								{
									par1World.func_147449_b(x1, y1, z1, Blocks.mossy_cobblestone);
								}
								else
								{
									par1World.func_147449_b(x1, y1, z1, Blocks.cobblestone);
								}
							}
						}
					}
				}
			}
			
			//Fill with Air
			for(int x2 = par3 - 3; x2 < par3 + 4 ; x2++)
			{
				for(int y2 = par4 + 1; y2 < par4 + 5 ; y2++)
				{
					for(int z2 = par5 - 3; z2 < par5 + 4 ; z2++)
					{
						par1World.func_147449_b(x2, y2, z2, Blocks.air);
					}
				}
			}
			
			//Chests and Spawners
			if(isEndDungeon)
			{
				par1World.func_147465_d(par3 + 2, par4 + 1, par5 - 1, Blocks.end_portal_frame, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 + 2, par4 + 1, par5 + 0, Blocks.end_portal_frame, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 + 2, par4 + 1, par5 + 1, Blocks.end_portal_frame, 1 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				
				par1World.func_147465_d(par3 - 1, par4 + 1, par5 + 2, Blocks.end_portal_frame, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 + 0, par4 + 1, par5 + 2, Blocks.end_portal_frame, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 + 1, par4 + 1, par5 + 2, Blocks.end_portal_frame, 2 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				
				par1World.func_147465_d(par3 - 2, par4 + 1, par5 - 1, Blocks.end_portal_frame, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 - 2, par4 + 1, par5 + 0, Blocks.end_portal_frame, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 - 2, par4 + 1, par5 + 1, Blocks.end_portal_frame, 3 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				
				par1World.func_147465_d(par3 - 1, par4 + 1, par5 - 2, Blocks.end_portal_frame, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 + 0, par4 + 1, par5 - 2, Blocks.end_portal_frame, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);
				par1World.func_147465_d(par3 + 1, par4 + 1, par5 - 2, Blocks.end_portal_frame, 0 + (par2Random.nextFloat() > 0.9F ? 4 : 0), 0);

				par1World.func_147449_b(par3, par4 + 6, par5, Blocks.mob_spawner);
				TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.func_147438_o(par3, par4 + 6, par5);
				if (spawn3 != null) { spawn3.func_145881_a().setMobID(this.pickMobSpawner(par2Random)); }
			}
			else
			{
				par1World.func_147449_b(par3, par4 + 1, par5, Blocks.mob_spawner);
				par1World.func_147449_b(par3, par4 + 3, par5, Blocks.mob_spawner);
				par1World.func_147449_b(par3, par4 + 6, par5, Blocks.mob_spawner);
				TileEntityMobSpawner spawn1 = (TileEntityMobSpawner)par1World.func_147438_o(par3, par4 + 1, par5);
				TileEntityMobSpawner spawn2 = (TileEntityMobSpawner)par1World.func_147438_o(par3, par4 + 3, par5);
				TileEntityMobSpawner spawn3 = (TileEntityMobSpawner)par1World.func_147438_o(par3, par4 + 6, par5);
				if (spawn1 != null) { spawn1.func_145881_a().setMobID(this.pickMobSpawner(par2Random)); }
				if (spawn2 != null) { spawn2.func_145881_a().setMobID(this.pickMobSpawner(par2Random)); }
				if (spawn3 != null) { spawn3.func_145881_a().setMobID(this.pickMobSpawner(par2Random)); }
				
				par1World.func_147449_b(par3, par4 + 2, par5, Blocks.chest);
				TileEntityChest var16 = (TileEntityChest)par1World.func_147438_o(par3, par4 + 2, par5);
				
				if(randomChest == true)
				{
					for (int var17 = 0; var17 < 20; ++var17)
					{
						ItemStack var18 = this.pickCheckLootItem(par2Random, var17);

						if (var18 != null)
						{
							var16.setInventorySlotContents(var17, var18);
						}
					}
				}
				else
				{
					for (int var17 = 0; var17 < 14; ++var17)
					{
						ItemStack var18 = this.pickCheckLootItem(par2Random, 0);

						if (var18 != null)
						{
							var16.setInventorySlotContents(par2Random.nextInt(var16.getSizeInventory()), var18);
						}
					}
				}	
			}
		}
		else
		{
			byte var6 = 3;
			int var7 = par2Random.nextInt(2) + 2;
			int var8 = par2Random.nextInt(2) + 2;
			int var9 = 0;
			int var10;
			int var11;
			int var12;
			
			if(checkpos)
			{
				for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10)
				{
					for (var11 = par4 - 1; var11 <= par4 + var6 + 1; ++var11)
					{
						for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12)
						{
							Material var13 = par1World.func_147439_a(var10, var11, var12).func_149688_o();

							if (var11 == par4 - 1 && !var13.isSolid())
							{
								return false;
							}

							if (var11 == par4 + var6 + 1 && !var13.isSolid())
							{
								return false;
							}

							if ((var10 == par3 - var7 - 1 || var10 == par3 + var7 + 1 || var12 == par5 - var8 - 1 || var12 == par5 + var8 + 1) && var11 == par4 && par1World.func_147437_c(var10, var11, var12) && par1World.func_147437_c(var10, var11 + 1, var12))
							{
								++var9;
							}
						}
					}
				}
			}

			if ((var9 >= 1 && var9 <= 5) || !checkpos)
			{
				for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10)
				{
					for (var11 = par4 + var6; var11 >= par4 - 1; --var11)
					{
						for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12)
						{
							if (var10 != par3 - var7 - 1 && var11 != par4 - 1 && var12 != par5 - var8 - 1 && var10 != par3 + var7 + 1 && var11 != par4 + var6 + 1 && var12 != par5 + var8 + 1)
							{
								par1World.func_147449_b(var10, var11, var12, Blocks.air);
							}
							else if (var11 >= 0 && !par1World.func_147439_a(var10, var11 - 1, var12).func_149688_o().isSolid())
							{
								par1World.func_147449_b(var10, var11, var12, Blocks.air);
							}
							else if (par1World.func_147439_a(var10, var11, var12).func_149688_o().isSolid())
							{
								if (var11 == par4 - 1 && par2Random.nextInt(4) != 0)
								{
									par1World.func_147449_b(var10, var11, var12, Blocks.mossy_cobblestone);
								}
								else
								{
									par1World.func_147449_b(var10, var11, var12, Blocks.cobblestone);
								}
							}
						}
					}
				}

				var10 = 0;
				if(randomChest == true) { var10 = 1; }

				while (var10 < 2)
				{
					var11 = 0;

					while (true)
					{
						if (var11 < 3)
						{
							label210:
							{
								var12 = par3 + par2Random.nextInt(var7 * 2 + 1) - var7;
								int var14 = par5 + par2Random.nextInt(var8 * 2 + 1) - var8;

								if (par1World.func_147437_c(var12, par4, var14))
								{
									int var15 = 0;

									if (par1World.func_147439_a(var12 - 1, par4, var14).func_149688_o().isSolid()) 
									{
										++var15;
									}

									if (par1World.func_147439_a(var12 + 1, par4, var14).func_149688_o().isSolid())
									{
										++var15;
									}

									if (par1World.func_147439_a(var12, par4, var14 - 1).func_149688_o().isSolid())
									{
										++var15;
									}

									if (par1World.func_147439_a(var12, par4, var14 + 1).func_149688_o() .isSolid())
									{
										++var15;
									}
									
									if(randomChest == true) 
									{
										var15 = 1;
									}

									if (var15 == 1)
									{
										par1World.func_147449_b(var12, par4, var14, Blocks.chest);
										TileEntityChest var16 = (TileEntityChest)par1World.func_147438_o(var12, par4, var14);

										if (var16 != null)
										{
											if(randomChest == true)
											{
												for (int var17 = 0; var17 < 20; ++var17)
												{
													ItemStack var18 = this.pickCheckLootItem(par2Random, var17);

													if (var18 != null)
													{
														var16.setInventorySlotContents(var17, var18);
													}
												}
											}
											else
											{
												for (int var17 = 0; var17 < 14; ++var17)
												{
													ItemStack var18 = this.pickCheckLootItem(par2Random, 0);

													if (var18 != null)
													{
														var16.setInventorySlotContents(par2Random.nextInt(var16.getSizeInventory()), var18);
													}
												}
											}
										}

										break label210;
									}
								}

								++var11;
								continue;
							}
						}

						++var10;
						break;
					}
				}

				par1World.func_147449_b(par3, par4, par5, Blocks.mob_spawner);
				TileEntityMobSpawner var19 = (TileEntityMobSpawner)par1World.func_147438_o(par3, par4, par5);

				if (var19 != null)
				{
					var19.func_145881_a().setMobID(this.pickMobSpawner(par2Random));
				}
				else
				{
					System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
				}

				return true;
			}
			else
			{
				return false;
			}
		}	
		return false;
    }
	
    private ItemStack pickCheckLootItem(Random par1Random, int listitem)
    {
    	return DungeonLoot.pickCheckLootItem(par1Random, listitem, chestID);
	}

    private String pickMobSpawner(Random rand)
    {
        int r = rand.nextInt(4);
		
        if(rand.nextInt(49) == 0)
        {
        	return "Enderman";
        }
        else if(r == 0)
		{
			return "Skeleton";
		}
		else if(r == 1)
		{
			return "Zombie";
		}
		else if(r == 2)
		{
			return "Spider";
		}
		else if(r == 3)
		{
			return "Creeper";
		}
		else
		{
			return "";
		}
    }
}
