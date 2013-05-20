package ted80.bwg4.gen;

import ted80.bwg4.gui.BWG4GuiGeneratorSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.WorldType;

public class BWG4WorldType extends WorldType
{
	public BWG4WorldType(int id, String name)
	{
		super(id, name);
	}
	
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft instance, GuiCreateWorld guiCreateWorld)
    {
    	instance.displayGuiScreen(new BWG4GuiGeneratorSettings(guiCreateWorld, guiCreateWorld.generatorOptionsToUse));
    }
    
    public boolean isCustomizable()
    {
        return true;
    }
}
