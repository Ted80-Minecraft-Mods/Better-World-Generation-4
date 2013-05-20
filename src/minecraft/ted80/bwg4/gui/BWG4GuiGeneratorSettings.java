package ted80.bwg4.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.util.StatCollector;

public class BWG4GuiGeneratorSettings extends GuiScreen
{
	private final GuiCreateWorld createWorldGui;
	private String GeneratorString = "";
	
    public BWG4GuiGeneratorSettings(GuiCreateWorld gcw, String gs)
    {
        createWorldGui = gcw;
        GeneratorString = gs;
    }
    
    public void initGui()
    {
        buttonList.clear();
        buttonList.add(new GuiSmallButton(0, width / 2 - 155, height - 28, 150, 20, StatCollector.translateToLocal("gui.done")));
        buttonList.add(new GuiSmallButton(1, width / 2 + 5, height - 28, 150, 20, StatCollector.translateToLocal("gui.cancel")));
    }
    
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 0) //DONE
        {
        	mc.displayGuiScreen(this.createWorldGui);
        }
        else if (par1GuiButton.id == 1) //CANCEL
        {
        	mc.displayGuiScreen(this.createWorldGui);
        }
    }
    
	public void updateButtons()
	{
		
	}
	
    public void drawScreen(int par1, int par2, float par3)
    {
    	drawDefaultBackground();
    	
    	String title = "Better World Generation 4 - Generator settings";
    	drawString(fontRenderer, title, (int) Math.floor(width / 2) - (int) Math.floor(fontRenderer.getStringWidth(title) / 2), 10, 16777215);
    	
    	super.drawScreen(par1, par2, par3);
    }
}
