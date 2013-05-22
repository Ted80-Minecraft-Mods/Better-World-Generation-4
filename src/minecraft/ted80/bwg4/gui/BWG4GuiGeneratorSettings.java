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
	
	private int generatorselected = 0;
	private int generatorcount = 0;
	private int maxgenerators = 0;
	
	//MAIN BUTTONS
	private GuiButton Setting_Generator;
	
	//GEN OPTION BUTTONS
	private GuiButton Setting_BD_Costumize;
	private GuiButton Setting_BD_Size;
	private GuiButton Setting_BETA_Biomes;
	private GuiButton Setting_INFDEV_Snow;
	private GuiButton Setting_INDEV_Type;
	private GuiButton Setting_INDEV_Theme;
	private GuiButton Setting_ISLAND_Theme;
	private GuiButton Setting_SKYLAND_Theme;
	
	//GEN SETTINGS
	public String BD_biomestring;
	private int BD_size = 0;
	private int BETA_biomes = 0;
	private int INFDEV_snow = 0;
	private int INDEV_type = 0;
	private int INDEV_theme = 0;
	private int ISLAND_theme = 0;
	private int SKYLAND_theme = 0;

    public BWG4GuiGeneratorSettings(GuiCreateWorld gcw, String gs)
    {
        createWorldGui = gcw;
        decodeString(gs);
        
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
        buttonList.add(Setting_Generator = new GuiSmallButton(2, width / 2 - 75, 75, 150, 20, "[GENERATORTYPE]"));
        buttonList.add(new GuiSmallButton(3, width / 2 - 155, height - 53, 310, 20, "Copy generator-settings string to Clipboard"));
        
        //GEN OPTION BUTTONS
        buttonList.add(Setting_BD_Costumize = new GuiSmallButton(10, width / 2 - 75, 115, 150, 20, "[CUSTOMIZE]")); 
        buttonList.add(Setting_BD_Size = new GuiSmallButton(11, width / 2 - 75, 135, 150, 20, "[BIOME-SIZE]"));
        buttonList.add(Setting_BETA_Biomes = new GuiSmallButton(12, width / 2 - 75, 115, 150, 20, "[BIOMES]")); 
        buttonList.add(Setting_INFDEV_Snow = new GuiSmallButton(13, width / 2 - 75, 115, 150, 20, "[SNOW]")); 
        buttonList.add(Setting_INDEV_Type = new GuiSmallButton(14, width / 2 - 75, 115, 150, 20, "[TYPE]")); 
        buttonList.add(Setting_INDEV_Theme = new GuiSmallButton(15, width / 2 - 75, 135, 150, 20, "[THEME]")); 
        buttonList.add(Setting_ISLAND_Theme = new GuiSmallButton(16, width / 2 - 75, 115, 150, 20, "[THEME]")); 
        buttonList.add(Setting_SKYLAND_Theme = new GuiSmallButton(17, width / 2 - 75, 115, 150, 20, "[THEME]")); 
        
        updateButtons();
        UpdateSettings();
    }
    
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 0) //DONE
        {
        	createWorldGui.generatorOptionsToUse = createString();
        	mc.displayGuiScreen(this.createWorldGui); 
        }
        else if (button.id == 1) //CANCEL
        {
        	if(createWorldGui.generatorOptionsToUse.length() < 3) { createWorldGui.generatorOptionsToUse = "BetterDefault#" + DefaultBiomeList.getDefaultString(); }
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
        else if (button.id == 3) //COPY STRING
        {
        	String copy = createString();
        	System.out.println(copy);
        	setClipboardString(copy);
        }
        else if (button.id == 10)
        {
        	mc.displayGuiScreen(new BWG4GuiDefault(mc, this, BD_biomestring, fontRenderer));
        }
        else if (button.id == 11)
        {
        	if(BD_size == 0) { BD_size = 1; } else { BD_size = 0; }
        }
        else if (button.id == 12)
        {
        	if(BETA_biomes == 0) { BETA_biomes = 1; } else { BETA_biomes = 0; }
        }
        else if (button.id == 13)
        {
        	if(INFDEV_snow == 0) { INFDEV_snow = 1; } else { INFDEV_snow = 0; }
        }
        else if (button.id == 14)
        {
        	if(INDEV_type == 0) { INDEV_type = 1; } else { INDEV_type = 0; }
        }
        else if (button.id == 15)
        {
        	if(INDEV_theme == 0) { INDEV_theme = 1; } else if(INDEV_theme == 1) { INDEV_theme = 2; } else if(INDEV_theme == 2) { INDEV_theme = 3; } else if(INDEV_theme == 3) { INDEV_theme = 4; } else { INDEV_theme = 0; }
        }
        else if (button.id == 16)
        {
        	if(ISLAND_theme == 0) { ISLAND_theme = 3; } else { ISLAND_theme = 0; }
        }
        else if (button.id == 17)
        {
        	if(SKYLAND_theme == 0) { SKYLAND_theme = 2; } else if(SKYLAND_theme == 2) { SKYLAND_theme = 3; } else { SKYLAND_theme = 0; }
        }

    	updateButtons();
    	UpdateSettings();
    }
    
	public void updateButtons()
	{
		Setting_Generator.displayString = GeneratorType.generatortypes[generatorselected].GetScreenName();
		
		//BETTER DEFAULT
		Setting_BD_Costumize.displayString = "Customize";
		if(BD_size == 0) { Setting_BD_Size.displayString = "Size: Default"; } else { Setting_BD_Size.displayString = "Size: Large"; }
		Setting_BD_Costumize.drawButton = (generatorselected == 0);
		Setting_BD_Size.drawButton = (generatorselected == 0);
		
		//BETA
		if(BETA_biomes == 0) { Setting_BETA_Biomes.displayString = "Biomes: Beta"; } else { Setting_BETA_Biomes.displayString = "Biomes: Default"; }
		Setting_BETA_Biomes.drawButton = (generatorselected == 2);
		
		//INFDEV
		if(INFDEV_snow == 0) { Setting_INFDEV_Snow.displayString = "Snow World: OFF"; } else { Setting_INFDEV_Snow.displayString = "Snow World: ON"; }
		Setting_INFDEV_Snow.drawButton = (generatorselected == 5);
		
		//INDEV
		if(INDEV_type == 0) { Setting_INDEV_Type.displayString = "Type: Inland"; } else { Setting_INDEV_Type.displayString = "Type: Floating"; }
		if(INDEV_theme == 0) { Setting_INDEV_Theme.displayString = "Theme: Normal"; } else if(INDEV_theme == 1) { Setting_INDEV_Theme.displayString = "Theme: Hell"; } else if(INDEV_theme == 2) { Setting_INDEV_Theme.displayString = "Theme: Paradise"; } else if(INDEV_theme == 3) { Setting_INDEV_Theme.displayString = "Theme: Woods"; } else { Setting_INDEV_Theme.displayString = "Theme: Snow"; }
		Setting_INDEV_Type.drawButton = (generatorselected == 6);
		Setting_INDEV_Theme.drawButton = (generatorselected == 6);
		
		//ISLAND
		if(ISLAND_theme == 0) { Setting_ISLAND_Theme.displayString = "Theme: Normal"; } else if(ISLAND_theme == 1) { Setting_ISLAND_Theme.displayString = "Theme: [NAME]"; } else if(ISLAND_theme == 2) { Setting_ISLAND_Theme.displayString = "Theme: [NAME]"; } else if(ISLAND_theme == 3) { Setting_ISLAND_Theme.displayString = "Theme: Paradise"; } else if(ISLAND_theme == 4) { Setting_ISLAND_Theme.displayString = "Theme: [NAME]"; } else { Setting_ISLAND_Theme.displayString = "Theme: [NAME]"; }
		Setting_ISLAND_Theme.drawButton = (generatorselected == 8);
		
		//SKYLAND
		if(SKYLAND_theme == 0) { Setting_SKYLAND_Theme.displayString = "Theme: Normal"; } else if(SKYLAND_theme == 1) { Setting_SKYLAND_Theme.displayString = "Theme: [NAME]"; } else if(SKYLAND_theme == 2) { Setting_SKYLAND_Theme.displayString = "Theme: Snow"; } else if(SKYLAND_theme == 3) { Setting_SKYLAND_Theme.displayString = "Theme: Jungle"; } else if(SKYLAND_theme == 4) { Setting_SKYLAND_Theme.displayString = "Theme: [NAME]"; } else { Setting_SKYLAND_Theme.displayString = "Theme: [NAME]"; }
		Setting_SKYLAND_Theme.drawButton = (generatorselected == 9);
	}
	
	public void UpdateSettings()
	{
    	if(generatorselected == 0)
    	{
    		BD_biomestring = DefaultBiomeList.getDefaultString();
    	}
	}
	
    public void drawScreen(int par1, int par2, float par3)
    {
    	drawDefaultBackground();
    	
    	//TITLE
    	String title = "Better World Generation 4 - Generator settings";
    	drawString(fontRenderer, title, (int) Math.floor(width / 2) - (int) Math.floor(fontRenderer.getStringWidth(title) / 2), 10, 16777215);
    	
    	//GENERATORTYPE
    	drawString(fontRenderer, "Generator:", width / 2 - 75 + 2, 65, 16777215);
    	String sizestring = "(" + (generatorcount + 1) + "/" + maxgenerators + ")";
    	drawString(fontRenderer, sizestring, width / 2 + 75 - fontRenderer.getStringWidth(sizestring), 65, 16777215);
    	
    	//SETTINGS
    	if(GeneratorType.generatortypes[generatorselected].HasSettings())
    	{
    		drawString(fontRenderer, "Settings:", width / 2 - 75 + 2, 105, 16777215);
    	}
    	 
    	super.drawScreen(par1, par2, par3);
    }
    
    public void decodeString(String input)
    {
    	if(input.length() < 3)
    	{
    		input = "BetterDefault#" + DefaultBiomeList.getDefaultString();
    	}

    	System.out.println(input);
    	String[] genstring = input.split("#");
    	
    	//BETTER DEFAULT
    	if(genstring[0].equals("BetterDefault"))
    	{
    		BD_biomestring = genstring[1];
    		BD_size = 0;
    		generatorselected = 0;
    		generatorcount = 0;
    	}
    	if(genstring[0].equals("BetterDefaultLarge"))
    	{
    		BD_biomestring = genstring[1];
    		BD_size = 1;
    		generatorselected = 0;
    		generatorcount = 0;
    	}

    	//BETA
    	if(genstring[0].equals("Beta_Beta"))
    	{
    		BETA_biomes = 0;
    		generatorselected = 2;
    		generatorcount = 1;
    	}
    	if(genstring[0].equals("Beta_Default"))
    	{
    		BETA_biomes = 1;
    		generatorselected = 2;
    		generatorcount = 1;
    	}
    }
    
    public String createString()
    {
    	//BETTER DEFAULT;
    	if(generatorselected == 0 && BD_size == 0)
    	{
    		return "BetterDefault#" + BD_biomestring;
    	}
    	if(generatorselected == 0 && BD_size == 1)
    	{
    		return "BetterDefaultLarge#" + BD_biomestring;
    	}
    	
    	//BETA
    	if(generatorselected == 2 && BETA_biomes == 0)
    	{
    		return "Beta_Beta";
    	}
    	if(generatorselected == 2 && BETA_biomes == 1)
    	{
    		return "Beta_Default";
    	}
    	
    	return "";
    }
}
