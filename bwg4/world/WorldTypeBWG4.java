package bwg4.world;

import bwg4.gui.GuiGeneratorSettings;
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
	
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
    {
    	instance.func_147108_a(new GuiGeneratorSettings(guiCreateWorld, guiCreateWorld.field_146334_a));
    }
    
    public boolean isCustomizable()
    {
        return true;
    }
}
