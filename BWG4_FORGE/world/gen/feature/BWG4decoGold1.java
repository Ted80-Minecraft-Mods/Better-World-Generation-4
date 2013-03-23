package net.minecraft.world.gen.feature;

import java.util.Random;
import java.lang.Math;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BWG4decoGold1 extends WorldGenerator
{
	private int objectID;
	private int input1, input2, input3;

	//NORMAL OBJECT
    public BWG4decoGold1(int object)
    {
		objectID = object;
    }
	
	//TREE OBJECTS WITH INPUT
    public BWG4decoGold1(int object, int in1, int in2, int in3)
    {
		objectID = object;
		input1 = in1;
		input2 = in2;
		input3 = in3;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		if(objectID == 1)//PINE TREE 1
		{	
			int i = par2Random.nextInt(input1) + input2; //4 13 0
			int j = i - par2Random.nextInt(2) - 3;
			int k = i - j;
			int l = 1 + par2Random.nextInt(k + 1);
			boolean flag = true;

			if (y < 1 || y + i + 1 > 128)
			{
				return false;
			}

			for (int i1 = y; i1 <= y + 1 + i && flag; i1++)
			{
				int k1 = 1;

				if (i1 - y < j)
				{
					k1 = 0;
				}
				else
				{
					k1 = l;
				}

				for (int i2 = x - k1; i2 <= x + k1 && flag; i2++)
				{
					for (int l2 = z - k1; l2 <= z + k1 && flag; l2++)
					{
						if (i1 >= 0 && i1 < 128)
						{
							int k3 = par1World.getBlockId(i2, i1, l2);

							if (k3 != 0 && k3 != Block.leaves.blockID)
							{
								flag = false;
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}

			if (!flag)
			{
				return false;
			}

			int j1 = par1World.getBlockId(x, y - 1, z);

			if (j1 != Block.grass.blockID && j1 != Block.dirt.blockID || y >= 128 - i - 1)
			{
				return false;
			}

			setBlock(par1World, x, y - 1, z, Block.dirt.blockID);
			int l1 = 0;

			for (int j2 = y + i; j2 >= y + j; j2--)
			{
				for (int i3 = x - l1; i3 <= x + l1; i3++)
				{
					int l3 = i3 - x;

					for (int i4 = z - l1; i4 <= z + l1; i4++)
					{
						int j4 = i4 - z;

						if ((Math.abs(l3) != l1 || Math.abs(j4) != l1 || l1 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(i3, j2, i4)])
						{
							setBlockAndMetadata(par1World, i3, j2, i4, Block.leaves.blockID, 0);
						}
					}
				}

				if (l1 >= 1 && j2 == y + j + 1)
				{
					l1--;
					continue;
				}

				if (l1 < l)
				{
					l1++;
				}
			}

			for (int k2 = 0; k2 < i - 1; k2++)
			{
				int j3 = par1World.getBlockId(x, y + k2, z);

				if (j3 == 0 || j3 == Block.leaves.blockID)
				{
					setBlockAndMetadata(par1World, x, y + k2, z, Block.wood.blockID, 0);
				}
			}

			return true;
		}
		else if(objectID == 2)//PINE TREE 2
		{	
			int i = par2Random.nextInt(input1) + input2; //4 13 5
			int j = input3 + par2Random.nextInt(2);
			int k = i - j;
			int l = 2 + par2Random.nextInt(2);
			boolean flag = true;

			if (y < 1 || y + i + 1 > 256)
			{
				return false;
			}

			for (int i1 = y; i1 <= y + 1 + i && flag; i1++)
			{
				int k1 = 1;

				if (i1 - y < j)
				{
					k1 = 0;
				}
				else
				{
					k1 = l;
				}

				for (int i2 = x - k1; i2 <= x + k1 && flag; i2++)
				{
					for (int k2 = z - k1; k2 <= z + k1 && flag; k2++)
					{
						if (i1 >= 0 && i1 < 256)
						{
							int l2 = par1World.getBlockId(i2, i1, k2);

							if (l2 != 0 && l2 != Block.leaves.blockID)
							{
								flag = false;
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}

			if (!flag)
			{
				return false;
			}

			int j1 = par1World.getBlockId(x, y - 1, z);

			if (j1 != Block.grass.blockID && j1 != Block.dirt.blockID || y >= 256 - i - 1)
			{
				return false;
			}

			setBlock(par1World, x, y - 1, z, Block.dirt.blockID);
			int l1 = par2Random.nextInt(2);
			int j2 = 1;
			boolean flag1 = true;

			for (int i3 = 0; i3 <= k; i3++)
			{
				int k3 = (y + i) - i3;

				for (int i4 = x - l1; i4 <= x + l1; i4++)
				{
					int k4 = i4 - x;

					for (int l4 = z - l1; l4 <= z + l1; l4++)
					{
						int i5 = l4 - z;

						if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(i4, k3, l4)])
						{
							setBlockAndMetadata(par1World, i4, k3, l4, Block.leaves.blockID, 0);
						}
					}
				}

				if (l1 >= j2)
				{
					l1 = ((flag1) ? 1 : 0);
					flag1 = true;

					if (++j2 > l)
					{
						j2 = l;
					}
				}
				else
				{
					l1++;
				}
			}

			int j3 = par2Random.nextInt(3);

			for (int l3 = 0; l3 < i - j3; l3++)
			{
				int j4 = par1World.getBlockId(x, y + l3, z);

				if (j4 == 0 || j4 == Block.leaves.blockID)
				{
					setBlockAndMetadata(par1World, x, y + l3, z, Block.wood.blockID, 0);
				}
			}

			return true;
		}
		else if(objectID == 3)//PINE TREE 3
		{	
			int i = par2Random.nextInt(input1) + input2; //4 13 5
			int j = input3 + par2Random.nextInt(2);
			int k = i - j;
			int l = 4 + par2Random.nextInt(2);
			boolean flag = true;

			if (y < 1 || y + i + 1 > 256)
			{
				return false;
			}

			for (int i1 = y; i1 <= y + 1 + i && flag; i1++)
			{
				int k1 = 1;

				if (i1 - y < j)
				{
					k1 = 0;
				}
				else
				{
					k1 = l;
				}

				for (int i2 = x - k1; i2 <= x + k1 && flag; i2++)
				{
					for (int k2 = z - k1; k2 <= z + k1 && flag; k2++)
					{
						if (i1 >= 0 && i1 < 256)
						{
							int l2 = par1World.getBlockId(i2, i1, k2);

							if (l2 != 0 && l2 != Block.leaves.blockID)
							{
								flag = false;
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}

			if (!flag)
			{
				return false;
			}

			int j1 = par1World.getBlockId(x, y - 1, z);

			if (j1 != Block.grass.blockID && j1 != Block.dirt.blockID || y >= 256 - i - 1)
			{
				return false;
			}

			setBlock(par1World, x, y - 1, z, Block.dirt.blockID);
			int l1 = par2Random.nextInt(2);
			int j2 = 1;
			boolean flag1 = true;

			for (int i3 = 0; i3 <= k; i3++)
			{
				int k3 = (y + i) - i3;

				for (int i4 = x - l1; i4 <= x + l1; i4++)
				{
					int k4 = i4 - x;

					for (int l4 = z - l1; l4 <= z + l1; l4++)
					{
						int i5 = l4 - z;

						if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(i4, k3, l4)])
						{
							setBlockAndMetadata(par1World, i4, k3, l4, Block.leaves.blockID, 0);
						}
					}
				}

				if (l1 >= j2)
				{
					l1 = ((flag1) ? 1 : 0);
					flag1 = true;

					if (++j2 > l)
					{
						j2 = l;
					}
				}
				else
				{
					l1++;
				}
			}

			int j3 = par2Random.nextInt(3);

			for (int l3 = 0; l3 < i - j3; l3++)
			{
				int j4 = par1World.getBlockId(x, y + l3, z);

				if (j4 == 0 || j4 == Block.leaves.blockID)
				{
					setBlockAndMetadata(par1World, x, y + l3, z, Block.wood.blockID, 0);
				}
			}

			return true;
		}
		else if(objectID == 4)//ICE
		{	
			for (int var7 = 0; var7 < 32; ++var7)
			{
				int var8 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
				int var10 = z + par2Random.nextInt(8) - par2Random.nextInt(8);
				int var11 = par1World.getBlockId(var8, 62, var10);
				double count = 0.01D;
				
				if (var11 == Block.waterStill.blockID)
				{
					for(int deep = 1; deep < 8; deep++)
					{
						int var22 = par1World.getBlockId(var8, 62 - deep, var10);
						if(var22 != Block.waterStill.blockID)
						{
							break;
						}
						else
						{
							if(deep < 3)
							{
								count += 0.22D;
							}	
							else if(deep < 5)
							{
								count += 0.65D;
							}	
							else
							{
								count += 0.95D;
							}	
						}
					}
					
					if(count < 4.0D)
					{
						count = Math.ceil(count);
						if(par2Random.nextInt((int)count) == 0)
						{
							par1World.setBlock(var8, 62, var10, Block.ice.blockID, 0, 0);
						}	
					}	
				}
			}
			return true;
		}
		else if(objectID == 5)//new pine tree
		{
			int ground = par1World.getBlockId(x, y - 1, z);
			if(ground != Block.grass.blockID && ground != Block.dirt.blockID) 
			{ 
				return true;
			}
			int randheight = (input1 - 5) + par2Random.nextInt(5); 
			
			//leaves
			
			//wood
			for(int ty1 = y - 3; ty1 < randheight + y; ty1++ )  { int block = par1World.getBlockId(x, ty1, z); if(block == 0) { par1World.setBlock(x, ty1, z, Block.wood.blockID, 12, 0); } }
			
			//root
			for(int ry1 = y - 3; ry1 < y + 5; ry1++ ) { int block = par1World.getBlockId(x + 1, ry1, z); if(block == 0) { par1World.setBlock(x + 1, ry1, z, Block.wood.blockID, 12, 0); if(ry1 > y + 0 && par2Random.nextInt(3) == 0) { break; } } }
			for(int ry2 = y - 3; ry2 < y + 5; ry2++ ) { int block = par1World.getBlockId(x - 1, ry2, z); if(block == 0) { par1World.setBlock(x - 1, ry2, z, Block.wood.blockID, 12, 0); if(ry2 > y + 0 && par2Random.nextInt(3) == 0) { break; } } }
			for(int ry3 = y - 3; ry3 < y + 5; ry3++ ) { int block = par1World.getBlockId(x, ry3, z - 1); if(block == 0) { par1World.setBlock(x, ry3, z - 1, Block.wood.blockID, 12, 0); if(ry3 > y + 0 && par2Random.nextInt(3) == 0) { break; } } }
			for(int ry4 = y - 3; ry4 < y + 5; ry4++ ) { int block = par1World.getBlockId(x, ry4, z + 1); if(block == 0) { par1World.setBlock(x, ry4, z + 1, Block.wood.blockID, 12, 0); if(ry4 > y + 0 && par2Random.nextInt(3) == 0) { break; } } }
			
			return true;
		}
		
		/*
		else if(objectID == 5)//ICE
		{	
			x -= 8;

			for (z -= 8; y > 5 && par1World.isAirBlock(x, y, z); --y)
			{
				;
			}

			if (y <= 4)
			{
				return false;
			}
			else
			{
				y -= 4;
				boolean[] var6 = new boolean[2048];
				int var7 = par2Random.nextInt(4) + 4;
				int var8;

				for (var8 = 0; var8 < var7; ++var8)
				{
					double var9 = par2Random.nextDouble() * 6.0D + 3.0D;
					double var11 = par2Random.nextDouble() * 4.0D + 2.0D;
					double var13 = par2Random.nextDouble() * 6.0D + 3.0D;
					double var15 = par2Random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
					double var17 = par2Random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
					double var19 = par2Random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

					for (int var21 = 1; var21 < 15; ++var21)
					{
						for (int var22 = 1; var22 < 15; ++var22)
						{
							for (int var23 = 1; var23 < 7; ++var23)
							{
								double var24 = ((double)var21 - var15) / (var9 / 2.0D);
								double var26 = ((double)var23 - var17) / (var11 / 2.0D);
								double var28 = ((double)var22 - var19) / (var13 / 2.0D);
								double var30 = var24 * var24 + var26 * var26 + var28 * var28;

								if (var30 < 1.0D)
								{
									var6[(var21 * 16 + var22) * 8 + var23] = true;
								}
							}
						}
					}
				}

				int var10;
				int var32;
				boolean var33;

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 0; var10 < 8; ++var10)
						{
							var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

							if (var33)
							{
								Material var12 = par1World.getBlockMaterial(x + var8, y + var10, z + var32);

								if (var10 >= 4 && var12.isLiquid())
								{
									return false;
								}

								if (var10 < 4 && !var12.isSolid() && par1World.getBlockId(x + var8, y + var10, z + var32) != this.blockIndex)
								{
									return false;
								}
							}
						}
					}
				}

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 0; var10 < 8; ++var10)
						{
							if (var6[(var8 * 16 + var32) * 8 + var10])
							{
								par1World.setBlock(x + var8, y + var10, z + var32, var10 >= 4 ? 0 : this.blockIndex);
							}
						}
					}
				}

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 4; var10 < 8; ++var10)
						{
							if (var6[(var8 * 16 + var32) * 8 + var10] && par1World.getBlockId(x + var8, y + var10 - 1, z + var32) == Block.dirt.blockID && par1World.getSavedLightValue(EnumSkyBlock.Sky, x + var8, y + var10, z + var32) > 0)
							{
								BiomeGenBase var35 = par1World.getBiomeGenForCoords(x + var8, z + var32);

								if (var35.topBlock == Block.mycelium.blockID)
								{
									par1World.setBlock(x + var8, y + var10 - 1, z + var32, Block.mycelium.blockID);
								}
								else
								{
									par1World.setBlock(x + var8, y + var10 - 1, z + var32, Block.grass.blockID);
								}
							}
						}
					}
				}

				if (Block.blocksList[this.blockIndex].blockMaterial == Material.lava)
				{
					for (var8 = 0; var8 < 16; ++var8)
					{
						for (var32 = 0; var32 < 16; ++var32)
						{
							for (var10 = 0; var10 < 8; ++var10)
							{
								var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

								if (var33 && (var10 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlockMaterial(x + var8, y + var10, z + var32).isSolid())
								{
									par1World.setBlock(x + var8, y + var10, z + var32, Block.stone.blockID);
								}
							}
						}
					}
				}

				if (Block.blocksList[this.blockIndex].blockMaterial == Material.water)
				{
					for (var8 = 0; var8 < 16; ++var8)
					{
						for (var32 = 0; var32 < 16; ++var32)
						{
							byte var34 = 4;

							if (par1World.isBlockFreezable(x + var8, y + var34, z + var32))
							{
								par1World.setBlock(x + var8, y + var34, z + var32, Block.ice.blockID);
							}
						}
					}
				}

				return true;
			}
		}
		else if(objectID == 6)//STONE
		{	
			x -= 8;

			for (z -= 8; y > 5 && par1World.isAirBlock(x, y, z); --y)
			{
				;
			}

			if (y <= 4)
			{
				return false;
			}
			else
			{
				y -= 4;
				boolean[] var6 = new boolean[2048];
				int var7 = par2Random.nextInt(4) + 4;
				int var8;

				for (var8 = 0; var8 < var7; ++var8)
				{
					double var9 = par2Random.nextDouble() * 6.0D + 3.0D;
					double var11 = par2Random.nextDouble() * 4.0D + 2.0D;
					double var13 = par2Random.nextDouble() * 6.0D + 3.0D;
					double var15 = par2Random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
					double var17 = par2Random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
					double var19 = par2Random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

					for (int var21 = 1; var21 < 15; ++var21)
					{
						for (int var22 = 1; var22 < 15; ++var22)
						{
							for (int var23 = 1; var23 < 7; ++var23)
							{
								double var24 = ((double)var21 - var15) / (var9 / 2.0D);
								double var26 = ((double)var23 - var17) / (var11 / 2.0D);
								double var28 = ((double)var22 - var19) / (var13 / 2.0D);
								double var30 = var24 * var24 + var26 * var26 + var28 * var28;

								if (var30 < 1.0D)
								{
									var6[(var21 * 16 + var22) * 8 + var23] = true;
								}
							}
						}
					}
				}

				int var10;
				int var32;
				boolean var33;

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 0; var10 < 8; ++var10)
						{
							var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

							if (var33)
							{
								Material var12 = par1World.getBlockMaterial(x + var8, y + var10, z + var32);

								if (var10 >= 4 && var12.isLiquid())
								{
									return false;
								}

								if (var10 < 4 && !var12.isSolid() && par1World.getBlockId(x + var8, y + var10, z + var32) != this.blockIndex)
								{
									return false;
								}
							}
						}
					}
				}

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 0; var10 < 8; ++var10)
						{
							if (var6[(var8 * 16 + var32) * 8 + var10])
							{
								par1World.setBlock(x + var8, y + var10, z + var32, var10 >= 4 ? 0 : this.blockIndex);
							}
						}
					}
				}

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 4; var10 < 8; ++var10)
						{
							if (var6[(var8 * 16 + var32) * 8 + var10] && par1World.getBlockId(x + var8, y + var10 - 1, z + var32) == Block.dirt.blockID && par1World.getSavedLightValue(EnumSkyBlock.Sky, x + var8, y + var10, z + var32) > 0)
							{
								BiomeGenBase var35 = par1World.getBiomeGenForCoords(x + var8, z + var32);

								if (var35.topBlock == Block.mycelium.blockID)
								{
									par1World.setBlock(x + var8, y + var10 - 1, z + var32, Block.mycelium.blockID);
								}
								else
								{
									par1World.setBlock(x + var8, y + var10 - 1, z + var32, Block.grass.blockID);
								}
							}
						}
					}
				}

				if (Block.blocksList[this.blockIndex].blockMaterial == Material.lava)
				{
					for (var8 = 0; var8 < 16; ++var8)
					{
						for (var32 = 0; var32 < 16; ++var32)
						{
							for (var10 = 0; var10 < 8; ++var10)
							{
								var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

								if (var33 && (var10 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlockMaterial(x + var8, y + var10, z + var32).isSolid())
								{
									par1World.setBlock(x + var8, y + var10, z + var32, Block.stone.blockID);
								}
							}
						}
					}
				}

				if (Block.blocksList[this.blockIndex].blockMaterial == Material.water)
				{
					for (var8 = 0; var8 < 16; ++var8)
					{
						for (var32 = 0; var32 < 16; ++var32)
						{
							byte var34 = 4;

							if (par1World.isBlockFreezable(x + var8, y + var34, z + var32))
							{
								par1World.setBlock(x + var8, y + var34, z + var32, Block.ice.blockID);
							}
						}
					}
				}

				return true;
			}
		}
		else if(objectID == 7)//HUGE CAVE
		{	
			x -= 8;

			for (z -= 8; y > 5 && par1World.isAirBlock(x, y, z); --y)
			{
				;
			}

			if (y <= 4)
			{
				return false;
			}
			else
			{
				y -= 4;
				boolean[] var6 = new boolean[2048];
				int var7 = par2Random.nextInt(4) + 4;
				int var8;

				for (var8 = 0; var8 < var7; ++var8)
				{
					double var9 = par2Random.nextDouble() * 6.0D + 3.0D;
					double var11 = par2Random.nextDouble() * 4.0D + 2.0D;
					double var13 = par2Random.nextDouble() * 6.0D + 3.0D;
					double var15 = par2Random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
					double var17 = par2Random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
					double var19 = par2Random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

					for (int var21 = 1; var21 < 15; ++var21)
					{
						for (int var22 = 1; var22 < 15; ++var22)
						{
							for (int var23 = 1; var23 < 7; ++var23)
							{
								double var24 = ((double)var21 - var15) / (var9 / 2.0D);
								double var26 = ((double)var23 - var17) / (var11 / 2.0D);
								double var28 = ((double)var22 - var19) / (var13 / 2.0D);
								double var30 = var24 * var24 + var26 * var26 + var28 * var28;

								if (var30 < 1.0D)
								{
									var6[(var21 * 16 + var22) * 8 + var23] = true;
								}
							}
						}
					}
				}

				int var10;
				int var32;
				boolean var33;

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 0; var10 < 8; ++var10)
						{
							var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

							if (var33)
							{
								Material var12 = par1World.getBlockMaterial(x + var8, y + var10, z + var32);

								if (var10 >= 4 && var12.isLiquid())
								{
									return false;
								}

								if (var10 < 4 && !var12.isSolid() && par1World.getBlockId(x + var8, y + var10, z + var32) != this.blockIndex)
								{
									return false;
								}
							}
						}
					}
				}

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 0; var10 < 8; ++var10)
						{
							if (var6[(var8 * 16 + var32) * 8 + var10])
							{
								par1World.setBlock(x + var8, y + var10, z + var32, var10 >= 4 ? 0 : this.blockIndex);
							}
						}
					}
				}

				for (var8 = 0; var8 < 16; ++var8)
				{
					for (var32 = 0; var32 < 16; ++var32)
					{
						for (var10 = 4; var10 < 8; ++var10)
						{
							if (var6[(var8 * 16 + var32) * 8 + var10] && par1World.getBlockId(x + var8, y + var10 - 1, z + var32) == Block.dirt.blockID && par1World.getSavedLightValue(EnumSkyBlock.Sky, x + var8, y + var10, z + var32) > 0)
							{
								BiomeGenBase var35 = par1World.getBiomeGenForCoords(x + var8, z + var32);

								if (var35.topBlock == Block.mycelium.blockID)
								{
									par1World.setBlock(x + var8, y + var10 - 1, z + var32, Block.mycelium.blockID);
								}
								else
								{
									par1World.setBlock(x + var8, y + var10 - 1, z + var32, Block.grass.blockID);
								}
							}
						}
					}
				}

				if (Block.blocksList[this.blockIndex].blockMaterial == Material.lava)
				{
					for (var8 = 0; var8 < 16; ++var8)
					{
						for (var32 = 0; var32 < 16; ++var32)
						{
							for (var10 = 0; var10 < 8; ++var10)
							{
								var33 = !var6[(var8 * 16 + var32) * 8 + var10] && (var8 < 15 && var6[((var8 + 1) * 16 + var32) * 8 + var10] || var8 > 0 && var6[((var8 - 1) * 16 + var32) * 8 + var10] || var32 < 15 && var6[(var8 * 16 + var32 + 1) * 8 + var10] || var32 > 0 && var6[(var8 * 16 + (var32 - 1)) * 8 + var10] || var10 < 7 && var6[(var8 * 16 + var32) * 8 + var10 + 1] || var10 > 0 && var6[(var8 * 16 + var32) * 8 + (var10 - 1)]);

								if (var33 && (var10 < 4 || par2Random.nextInt(2) != 0) && par1World.getBlockMaterial(x + var8, y + var10, z + var32).isSolid())
								{
									par1World.setBlock(x + var8, y + var10, z + var32, Block.stone.blockID);
								}
							}
						}
					}
				}

				if (Block.blocksList[this.blockIndex].blockMaterial == Material.water)
				{
					for (var8 = 0; var8 < 16; ++var8)
					{
						for (var32 = 0; var32 < 16; ++var32)
						{
							byte var34 = 4;

							if (par1World.isBlockFreezable(x + var8, y + var34, z + var32))
							{
								par1World.setBlock(x + var8, y + var34, z + var32, Block.ice.blockID);
							}
						}
					}
				}

				return true;
			}
		}*/
		else
		{
		}
		return true;
	}
	
    private ItemStack getChestList(int chestid, Random par1Random, World par1World)
    {
		return null;
    }
}
