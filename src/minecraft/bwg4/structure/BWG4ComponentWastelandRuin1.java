package bwg4.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class BWG4ComponentWastelandRuin1 extends BWG4ComponentScattered
{	
	private int[] main;
	private int[] top;
	private int[] right;
	
    public BWG4ComponentWastelandRuin1(Random rand, int par2, int par3)
    {
        super(rand, par2, 64, par3, 30, 30, 30);
    	main = new int[]{-4 - rand.nextInt(4), -4 - rand.nextInt(4), 4 + rand.nextInt(4), 4 + rand.nextInt(4)};
    	top = new int[]{main[0] + rand.nextInt(4), main[1] - 3 - rand.nextInt(4), main[2] - rand.nextInt(4), main[1]};
    	right = new int[]{main[2], main[1] + rand.nextInt(4), main[2] + 3 + rand.nextInt(4), main[3] - rand.nextInt(4)};
    }
	
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        if (!this.func_74935_a(world, box, 0))
        {
            return false;
        }
        else
        {
        	generateRoom(world, box, rand, main[0] + 15, -3, main[1] + 15, main[2] + 15, 1, main[3] + 15);
        	generateRoom(world, box, rand, top[0] + 15, -3, top[1] + 15, top[2] + 15, 1, top[3] + 15);
        	generateRoom(world, box, rand, right[0] + 15, -3, right[1] + 15, right[2] + 15, 1, right[3] + 15);
        	return true;
        }
    }
    
    public void generateRoom(World w, StructureBoundingBox s, Random r, int sx1, int sy1, int sz1, int sx2, int sy2, int sz2)
    {
    	for(int x1 = sx1; x1 < sx2 + 1; x1++)
    	{
    		for(int y1 = sy1; y1 < sy2 + 1; y1++)
    		{
    			for(int z1 = sz1; z1 < sz2 + 1; z1++)
    			{
    				if(!(x1 > sx1 && y1 > sy1 && z1 > sz1 && x1 < sx2 && y1 < sy2 && z1 < sz2))
    				{
	    				if(getBlockIdAtCurrentPosition(w, x1, y1, z1, s) != 0)
	    				{
	    					placeBlockAtCurrentPosition(w, Block.stoneBrick.blockID, r.nextInt(3), x1, y1, z1, s);
	    				}
	    				else if(getBlockIdAtCurrentPosition(w, x1, y1 - 1, z1, s) != 0 && r.nextInt(3) == 0)
	    				{
	    					placeBlockAtCurrentPosition(w, Block.stoneBrick.blockID, r.nextInt(3), x1, y1, z1, s);
	    				}
    				}
    			}
    		}
    	}

    	fillWithAir(w, s, sx1 + 1, sy1 + 1, sz1 + 1, sx2 - 1, sy2 - 1, sz2 - 1);
    }
}
