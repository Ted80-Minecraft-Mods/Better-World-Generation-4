package bwg4.gui;

import bwg4.api.DefaultBiomeList;
import bwg4.generatordata.BWG4GeneratorType;
import bwg4.generatordata.BWG4WorldSettings;
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
	private GuiButton Setting_BD_Biome;
	private GuiButton Setting_BD_World;
	
	private GuiButton Setting_BD_Size;
	
	private GuiButton Setting_BETA_Biomes;
	private GuiButton Setting_INFDEV_Snow;
	private GuiButton Setting_INDEV_Type;
	private GuiButton Setting_INDEV_Theme;
	private GuiButton Setting_ISLAND_Theme;
	private GuiButton Setting_SKYLAND_Theme;
	private GuiButton Setting_SKY_Biomes;
	
	//GEN SETTINGS
	public String BD_biomestring;
	public String BD_worldstring;
	
	private int BD_size = 0;
	
	private int BETA_biomes = 0;
	private int INFDEV_snow = 0;
	private int INDEV_type = 0;
	private int INDEV_theme = 0;
	private int ISLAND_theme = 0;
	private int SKYLAND_theme = 0;
	private int SKY_biomes = 0;

    public BWG4GuiGeneratorSettings(GuiCreateWorld gcw, String gs)
    {
        createWorldGui = gcw;
        decodeString(gs);
        
        for(int c = 0; c < BWG4GeneratorType.generatortypes.length; c++)
        {
        	if(BWG4GeneratorType.generatortypes[c] != null && BWG4GeneratorType.generatortypes[c].CanBeCreated())
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
        buttonList.add(new GuiSmallButton(3, width / 2 - 155, height - 53, 310, 20, "Copy generator-settings to Clipboard"));
        
        //GEN OPTION BUTTONS
        buttonList.add(Setting_BD_Biome = new GuiSmallButton(10, width / 2 - 75, 115, 150, 20, "[BIOME-SETTINGS]")); 
        //buttonList.add(Setting_BD_World = new GuiSmallButton(11, width / 2 - 75, 135, 150, 20, "[WORLD-SETTINGS]"));
        
        buttonList.add(Setting_BD_Size = new GuiSmallButton(11, width / 2 - 75, 135, 150, 20, "[BIOME-SIZE]"));
        
        buttonList.add(Setting_BETA_Biomes = new GuiSmallButton(12, width / 2 - 75, 115, 150, 20, "[BIOMES]")); 
        buttonList.add(Setting_INFDEV_Snow = new GuiSmallButton(13, width / 2 - 75, 115, 150, 20, "[SNOW]")); 
        buttonList.add(Setting_INDEV_Type = new GuiSmallButton(14, width / 2 - 75, 115, 150, 20, "[TYPE]")); 
        buttonList.add(Setting_INDEV_Theme = new GuiSmallButton(15, width / 2 - 75, 135, 150, 20, "[THEME]")); 
        buttonList.add(Setting_ISLAND_Theme = new GuiSmallButton(16, width / 2 - 75, 115, 150, 20, "[THEME]")); 
        buttonList.add(Setting_SKYLAND_Theme = new GuiSmallButton(17, width / 2 - 75, 115, 150, 20, "[THEME]")); 
        buttonList.add(Setting_SKY_Biomes = new GuiSmallButton(19, width / 2 - 75, 115, 150, 20, "[BIOMES]")); 
        
        updateButtons();
        //UpdateSettings();
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
        	if(generatorselected >= BWG4GeneratorType.generatortypes.length)
        	{
        		generatorcount = 0;
        		generatorselected = 0;
        	}
        	
        	while(BWG4GeneratorType.generatortypes[generatorselected] == null || !BWG4GeneratorType.generatortypes[generatorselected].CanBeCreated())
        	{
        		generatorselected++;
        		
            	if(generatorselected >= BWG4GeneratorType.generatortypes.length)
            	{
            		generatorcount = 0;
            		generatorselected = 0;
            	}
        	}

        	UpdateSettings();
        }
        else if (button.id == 3) //COPY STRING
        {
        	String copy = createString();
        	System.out.println(copy);
        	setClipboardString(copy);
        }
        else if (button.id == 10)
        {
        	mc.displayGuiScreen(new BWG4GuiBiomeSettings(mc, this, BD_biomestring, fontRenderer));
        }
        else if (button.id == 11)
        {
        	if(BD_size == 0) { BD_size = 1; } else { BD_size = 0; }
        	
        	//mc.displayGuiScreen(new BWG4GuiWorldSettings(mc, this, fontRenderer, new BWG4WorldSettings()));
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
        	if(INDEV_type == 0) { INDEV_type = 1; } else if(INDEV_type == 1) { INDEV_type = 2; } else { INDEV_type = 0; }
        }
        else if (button.id == 15)
        {
        	if(INDEV_theme == 0) { INDEV_theme = 1; } else if(INDEV_theme == 1) { INDEV_theme = 2; } else if(INDEV_theme == 2) { INDEV_theme = 3; } else if(INDEV_theme == 3) { INDEV_theme = 4; } else { INDEV_theme = 0; }
        }
        else if (button.id == 16)
        {
        	if(ISLAND_theme == 0) { ISLAND_theme = 1; } else if(ISLAND_theme == 1) { ISLAND_theme = 3; } else { ISLAND_theme = 0; }
        }
        else if (button.id == 17)
        {
        	if(SKYLAND_theme == 0) { SKYLAND_theme = 2; } else if(SKYLAND_theme == 2) { SKYLAND_theme = 3; } else { SKYLAND_theme = 0; }
        }
        else if (button.id == 19)
        {
        	if(SKY_biomes == 0) { SKY_biomes = 1; } else { SKY_biomes = 0; }
        }

    	updateButtons();
    }
    
	public void updateButtons()
	{
		Setting_Generator.displayString = BWG4GeneratorType.generatortypes[generatorselected].GetScreenName();
		
		//BETTER DEFAULT
		Setting_BD_Biome.displayString = "Biome Settings";
		//Setting_BD_World.displayString = "World Settings";
		Setting_BD_Biome.drawButton = (generatorselected == 0);
		//Setting_BD_World.drawButton = (generatorselected == 0);
		
		if(BD_size == 0) { Setting_BD_Size.displayString = "Size: Default"; } else { Setting_BD_Size.displayString = "Size: Large"; }
		Setting_BD_Size.drawButton = (generatorselected == 0);
		
		//BETA
		if(BETA_biomes == 0) { Setting_BETA_Biomes.displayString = "Biomes: Beta"; } else { Setting_BETA_Biomes.displayString = "Biomes: Default"; }
		Setting_BETA_Biomes.drawButton = (generatorselected == 2);
		
		//INFDEV
		if(INFDEV_snow == 0) { Setting_INFDEV_Snow.displayString = "Snow World: OFF"; } else { Setting_INFDEV_Snow.displayString = "Snow World: ON"; }
		Setting_INFDEV_Snow.drawButton = (generatorselected == 5);
		
		//INDEV
		if(INDEV_type == 0) { Setting_INDEV_Type.displayString = "Type: Island"; } else if(INDEV_type == 1) { Setting_INDEV_Type.displayString = "Type: Floating"; } else if(INDEV_type == 2) { Setting_INDEV_Type.displayString = "Type: Inland"; } else { Setting_INDEV_Type.displayString = "Type: Finite"; }
		if(INDEV_theme == 0) { Setting_INDEV_Theme.displayString = "Theme: Normal"; } else if(INDEV_theme == 1) { Setting_INDEV_Theme.displayString = "Theme: Hell"; } else if(INDEV_theme == 2) { Setting_INDEV_Theme.displayString = "Theme: Paradise"; } else if(INDEV_theme == 3) { Setting_INDEV_Theme.displayString = "Theme: Woods"; } else { Setting_INDEV_Theme.displayString = "Theme: Snow"; }
		Setting_INDEV_Type.drawButton = (generatorselected == 6);
		Setting_INDEV_Theme.drawButton = (generatorselected == 6);
		
		//ISLAND
		if(ISLAND_theme == 0) { Setting_ISLAND_Theme.displayString = "Theme: Normal"; } else if(ISLAND_theme == 1) { Setting_ISLAND_Theme.displayString = "Theme: Tropical"; } else if(ISLAND_theme == 2) { Setting_ISLAND_Theme.displayString = "Theme: Iceberg"; } else if(ISLAND_theme == 3) { Setting_ISLAND_Theme.displayString = "Theme: Paradise"; } else if(ISLAND_theme == 4) { Setting_ISLAND_Theme.displayString = "Theme: [NAME]"; } else { Setting_ISLAND_Theme.displayString = "Theme: [NAME]"; }
		Setting_ISLAND_Theme.drawButton = (generatorselected == 8);
		
		//SKYLAND
		if(SKYLAND_theme == 0) { Setting_SKYLAND_Theme.displayString = "Theme: Normal"; } else if(SKYLAND_theme == 1) { Setting_SKYLAND_Theme.displayString = "Theme: [NAME]"; } else if(SKYLAND_theme == 2) { Setting_SKYLAND_Theme.displayString = "Theme: Snow"; } else if(SKYLAND_theme == 3) { Setting_SKYLAND_Theme.displayString = "Theme: Jungle"; } else if(SKYLAND_theme == 4) { Setting_SKYLAND_Theme.displayString = "Theme: [NAME]"; } else { Setting_SKYLAND_Theme.displayString = "Theme: [NAME]"; }
		Setting_SKYLAND_Theme.drawButton = (generatorselected == 9);

		//SKY DIMENSION
		if(SKY_biomes == 0) { Setting_SKY_Biomes.displayString = "Biomes: Default"; } else { Setting_SKY_Biomes.displayString = "Biomes: Beta"; }
		Setting_SKY_Biomes.drawButton = (generatorselected == 11);
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
    	if(BWG4GeneratorType.generatortypes[generatorselected].HasSettings())
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
    	
    	String[] genstring = input.split("#");

    	//BETTER DEFAULT
    	if(genstring[0].equals("BetterDefault"))
    	{
    		if(genstring.length >= 2)
    		{
    			BD_biomestring = genstring[1];
    		}
    		else
    		{
    			BD_biomestring = DefaultBiomeList.getDefaultString();
    		}
    		
    		BD_size = 0;
    		
    		generatorselected = 0;
    		generatorcount = 0;
    	}
    	if(genstring[0].equals("BetterDefaultLarge"))
    	{
    		if(genstring.length >= 2)
    		{
    			BD_biomestring = genstring[1];
    		}
    		else
    		{
    			BD_biomestring = DefaultBiomeList.getDefaultString();
    		}
    		
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
    	
    	//ALPHA
    	if(genstring[0].equals("Alpha"))
    	{
    		generatorselected = 4;
    		generatorcount = 2;
    	}
    	
    	//INFDEV
    	if(input.equals("Infdev#1"))
    	{
    		INFDEV_snow = 0;
    		generatorselected = 5;
    		generatorcount = 3;
    	}
    	if(input.equals("Infdev#0"))
    	{
    		INFDEV_snow = 1;
    		generatorselected = 5;
    		generatorcount = 3;
    	}
    	
    	//INDEV
    	if(genstring[0].equals("Indev"))
    	{
    		String[] indevsettings = genstring[1].split("&");
    		
    		INDEV_type = Integer.parseInt(indevsettings[0]) - 1;
    		INDEV_theme = Integer.parseInt(indevsettings[1]) - 1;
    		generatorselected = 6;
    		generatorcount = 4;
    	}
    	
    	//OLD INDEV
    	if(genstring[0].equals("Indev_inland"))
    	{
    		INDEV_type = 2;
    		INDEV_theme = Integer.parseInt(genstring[1]) - 1;
    		generatorselected = 6;
    		generatorcount = 4;
    	}
    	if(genstring[0].equals("Indev_floating"))
    	{
    		INDEV_type = 1;
    		INDEV_theme = Integer.parseInt(genstring[1]) - 1;
    		generatorselected = 6;
    		generatorcount = 4;
    	}
    	
    	//SURVIVAL ISLAND
    	if(genstring[0].equals("Survival_Island"))
    	{
    		ISLAND_theme = Integer.parseInt(genstring[1]) - 1;
    		generatorselected = 8;
    		generatorcount = 5;
    	}
    	
    	//SURVIVAL SKYLAND
    	if(genstring[0].equals("Survival_Skyland"))
    	{
    		SKYLAND_theme = Integer.parseInt(genstring[1]) - 1;
    		generatorselected = 9;
    		generatorcount = 6;
    	}
    	
    	//SKYBLOCK SURVIVAL
    	if(input.equals("Skyblock"))
    	{
    		generatorselected = 10;
    		generatorcount = 7;
    	}
    	
    	//SKYDIMENSION
    	if(input.equals("Sky_Default"))
    	{
    		SKY_biomes = 1;
    		generatorselected = 11;
    		generatorcount = 8;
    	}
    	if(input.equals("Sky_Beta"))
    	{
    		SKY_biomes = 2;
    		generatorselected = 11;
    		generatorcount = 8;
    	}
    	
    	//WASTELAND
    	if(input.equals("Sky_Default"))
    	{
    		generatorselected = 15;
    		generatorcount = 9;
    	}
    }
    
    public String createString()
    {
    	//BETTER DEFAULT
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
    	
    	//ALPHA
    	if(generatorselected == 4)
    	{
    		return "Alpha";
    	}

    	//INFDEV
    	if(generatorselected == 5 && INFDEV_snow == 0)
    	{
    		return "Infdev#1";
    	}
    	if(generatorselected == 5 && INFDEV_snow == 1)
    	{
    		return "Infdev#0";
    	}
    	
    	//INDEV
    	if(generatorselected == 6)
    	{
    		return "Indev#" + (INDEV_type + 1) + "&" + (INDEV_theme + 1);
    	}
    	
    	//SURVIVAL ISLAND
    	if(generatorselected == 8)
    	{
    		return "Survival_Island#" + (ISLAND_theme + 1);
    	}
    	
    	//SURVIVAL SKYLAND
    	if(generatorselected == 9)
    	{
    		return "Survival_Skyland#" + (SKYLAND_theme + 1);
    	}
    	
    	//SKYBLOCK
    	if(generatorselected == 10)
    	{
    		return "Skyblock";
    	}
    	
    	//SKYDIMENSION
    	if(generatorselected == 11 && SKY_biomes == 0)
    	{
    		return "Sky_Default";
    	}
    	if(generatorselected == 11 && SKY_biomes == 1)
    	{
    		return "Sky_Beta";
    	}
    	
    	//WASTELAND
    	if(generatorselected == 15)
    	{
    		return "Wasteland";
    	}
    	
    	return "";
    }
}
