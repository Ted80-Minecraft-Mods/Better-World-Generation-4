package bwg4.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.WorldType;

public class WorldTypeBWG4 extends WorldType
{
	public WorldTypeBWG4(String name)
	{
		super(name);
	}
	
    /*@SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
    {
    	instance.displayGuiScreen(new BWG4GuiGeneratorSettings(guiCreateWorld, guiCreateWorld.generatorOptionsToUse));
    }*/
    
    public boolean isCustomizable()
    {
        return true;
    }
}
