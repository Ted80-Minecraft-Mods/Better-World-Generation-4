package bwg4.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class BWG4ComponentPyramid extends BWG4ComponentScattered
{	
	private int _height = -10;
	private int _center = 50;
	
    public BWG4ComponentPyramid(Random $rand, int $par2, int $par3)
    {
        super($rand, $par2, 80, $par3, 100, 100, 100);
    }
	
    public boolean addComponentParts(World $world, Random $rand, StructureBoundingBox $box)
    {
        if (!this.func_74935_a($world, $box, 0))
        {
            return false;
        }
        else
        {
        	//GENERATE STRUCTURE
        	int _size = center;
        	for(int _y = 0 + _height; _y < _center + _height; y++)
        	{
        		for(int _x = _center - _size; _x < _center + _size; x++)
        		{
        			for(int z = _center - _size; z < _center + _size; z++)
        			{
        				if(y == 0 + _height)
        				{
        					fillCurrentPositionBlocksDownwards($world, Block.sandStone.blockID, 0, _x, _y, _z, $box);
        				}
        				else
        				{
        					placeBlockAtCurrentPosition($world, Block.sandStone.blockID, 0, _x, _y, _z, $box);
        				}
        			}
        		}
        		_size--;
        	}
        	 
        	//GENERATE STAIRS 
        	int _stair = 24;
        	int _d = this.getMetadataWithOffset(Block.stairsSandStone.blockID, 0);
        	while(_stair > -1)
        	{
        		if(getBlockIdAtCurrentPosition($world, _stair - 1, _stair + _height, _center, $box) == 0)
        		{
        			placeBlockAtCurrentPosition($world, Block.stairsSandStone.blockID, _d, _stair, _stair + _height, _center, $box);
        			placeBlockAtCurrentPosition($world, Block.stairsSandStone.blockID, _d, _stair, _stair + _height, _center - 1, $box);
        		}
        		else
        		{
        			break;
        		}
        		
        		_stair--;
        	}
        	
        	//GENERATE ROOMS
        	int _r;
        	for(r = 0; r < 3; r++)
        	{
        		generateRooms($world, $box, $rand, _r);
        	}
        	
        	//GENERATE ENTRANCE
        	fillWithMetadataBlocks($world, $box, 25, 25 - 1 + _height, _center - 2, _center - 11, 26 + 1 + _height, _center + 1, Block.sandStone.blockID, 2, 0, 0, true);
        	fillWithAir($world, $box, 25, 25 + _height, _center - 1, _center - 11, 26 + _height, _center);
        	
        	//GENERATE DOWNSTAIRS
        	for(int ds = 0; ds < r + 1; ds++)
        	{
        		
        	}
        	
        	return true;
        }
    }
    
    public void generateRooms(World $world, StructureBoundingBox $box, Random $rand, int $layer)
    {
    	if($layer < 3)
    	{
    		//box
	    	int _h = 24 - ($layer * 5) + _height;
	    	int _center = 50;
	    	for(int _y = _h; _y < _h + 7; _y++)
	    	{
	    		for(int _x = -12 + _center; _x < 12 + _center; x++)
	    		{
	    			for(int _z = -12 + _center; _z < 12 + _center; z++)
	    			{
	    				if((y == _h + 5 && layer == 0) || x == -12 + center || x == 11 + center || z == -12 + center || z == 11 + center)
	    				{
	    					placeBlockAtCurrentPosition(world, Block.obsidian.blockID, 0, x, y, z, box);
	    				}
	    				else if (y == h || y == h + 4 || x == -11 + center || x == 10 + center || z == -11 + center || z == 10 + center)
	    				{
	    					if(y == h + 2)
	    					{
	    						placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 1, x, y, z, box);
	    					}
	    					else
	    					{
	    						placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 2, x, y, z, box);
	    					}
	    				}
	    				else if(y < h + 4)
	    				{
	    					placeBlockAtCurrentPosition(world, 0, 0, x, y, z, box);
	    				}
	    			}
	    		}
	    	}
	    	
	    	//pillars
	    	generatePillar(world, box, rand, center - 6, h + 1, center - 6);
	    	generatePillar(world, box, rand, center + 5, h + 1, center + 5);
	    	generatePillar(world, box, rand, center - 6, h + 1, center + 5);
	    	generatePillar(world, box, rand, center + 5, h + 1, center - 6);
	    	
	    	//deco
    	}
    }
    
    public void generatePillar(World world, StructureBoundingBox box, Random rand, int x, int y, int z)
    {
    	placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 2, x, y, z, box);
    	placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 1, x, y + 1, z, box);
    	placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 2, x, y + 2, z, box);
    }
}
