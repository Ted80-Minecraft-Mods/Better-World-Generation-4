package ted80.bwg4.gui;

import ted80.api.DefaultBiomeList;
import ted80.bwg4.generatordata.GeneratorType;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldType;

public class BWG4GuiGeneratorSettings extends GuiScreen
{
	private final GuiCreateWorld createWorldGui;
	private String GeneratorString = "";
	
	private int generatorselected = 0;
	
	private int generatorcount = 0;
	private int maxgenerators = 0;
	
	//MAIN BUTTONS
	private GuiButton Setting_Generator;
	
	//GEN OPTION BUTTONS
	private GuiButton Setting_BD_Costumize;
	private GuiButton Setting_BD_Size;
	
	//GEN SETTINGS
	private int BD_size = 0;
	
    public BWG4GuiGeneratorSettings(GuiCreateWorld gcw, String gs)
    {
        createWorldGui = gcw;
        GeneratorString = gs;
        
        for(int c = 0; c < GeneratorType.generatortypes.length; c++)
        {
        	if(GeneratorType.generatortypes[c] != null && GeneratorType.generatortypes[c].CanBeCreated())
        	{
        		maxgenerators++;
        	}
        }
    }
    
    public void initGui()
    {
        buttonList.clear();
	        
        //MAIN BUTTONS
        buttonList.add(new GuiSmallButton(0, width / 2 - 155, height - 28, 150, 20, StatCollector.translateToLocal("gui.done")));
        buttonList.add(new GuiSmallButton(1, width / 2 + 5, height - 28, 150, 20, StatCollector.translateToLocal("gui.cancel")));
        buttonList.add(Setting_Generator = new GuiSmallButton(2, width / 2 - 75, 85, 150, 20, "[GENERATORTYPE]"));
        
        //GEN OPTION BUTTONS
        buttonList.add(Setting_BD_Costumize = new GuiSmallButton(10, width / 2 - 75, 115, 150, 20, "[CUSTOMIZE]")); 
        buttonList.add(Setting_BD_Size = new GuiSmallButton(11, width / 2 - 75, 135, 150, 20, "[BIOME-SIZE]"));
        
        updateButtons();
    }
    
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 0) //DONE
        {
        	mc.displayGuiScreen(this.createWorldGui);
        }
        else if (button.id == 1) //CANCEL
        {
        	mc.displayGuiScreen(this.createWorldGui);
        }
        else if (button.id == 2) //GENERATOR SETTING
        {
        	generatorselected++;
        	generatorcount++;
        	if(generatorselected >= GeneratorType.generatortypes.length)
        	{
        		generatorcount = 0;
        		generatorselected = 0;
        	}
        	
        	while(GeneratorType.generatortypes[generatorselected] == null || !GeneratorType.generatortypes[generatorselected].CanBeCreated())
        	{
        		generatorselected++;
        		
            	if(generatorselected >= GeneratorType.generatortypes.length)
            	{
            		generatorcount = 0;
            		generatorselected = 0;
            	}
        	}
        }
        else if (button.id == 10)
        {
        	mc.displayGuiScreen(new BWG4GuiDefault(mc, this, DefaultBiomeList.getDefaultString(), fontRenderer));
        }
        else if (button.id == 11)
        {
        	if(BD_size == 0) { BD_size = 1; } else { BD_size = 0; }
        }

    	updateButtons();
    }
    
	public void updateButtons()
	{
		Setting_Generator.displayString = GeneratorType.generatortypes[generatorselected].GetScreenName();
		Setting_BD_Costumize.displayString = "Customize";
		if(BD_size == 0) { Setting_BD_Size.displayString = "Size: Default"; } else { Setting_BD_Size.displayString = "Size: Large"; }
	}
	
    public void drawScreen(int par1, int par2, float par3)
    {
    	drawDefaultBackground();
    	
    	//TITLE
    	String title = "Better World Generation 4 - Generator settings";
    	drawString(fontRenderer, title, (int) Math.floor(width / 2) - (int) Math.floor(fontRenderer.getStringWidth(title) / 2), 10, 16777215);
    	
    	//GENERATORTYPE
    	drawString(fontRenderer, "Generator:", width / 2 - 75 + 2, 75, 16777215);
    	String sizestring = "(" + (generatorcount + 1) + "/" + maxgenerators + ")";
    	drawString(fontRenderer, sizestring, width / 2 + 75 - fontRenderer.getStringWidth(sizestring), 75, 16777215);
    	 
    	super.drawScreen(par1, par2, par3);
    }
    
    public void createString()
    {
    	
    }
}
