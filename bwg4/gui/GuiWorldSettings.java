package bwg4.gui;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiWorldSettings extends GuiScreen
{
	/*
	protected Minecraft minecraft;
	protected FontRenderer fr;
    private final GuiGeneratorSettings guiGeneratorSettings;
    private WorldSettings oldsettings;

	private GuiSlider Slider_ORES;
	private GuiSlider Slider_PLANTS;
	private GuiSlider Slider_TREES; 
	private GuiSlider Slider_PONDS;
	private GuiSlider Slider_DUNGEONS;
	private GuiSlider Slider_CLAY;
	private GuiSlider Slider_HEIGHT;
	private GuiSlider Slider_CAVES;
	private GuiSlider Slider_RAVINES;
	private GuiSlider Slider_VILLAGES;
	private GuiSlider Slider_SCATTERED;
	private GuiSlider Slider_SIZE;
	
	public GuiWorldSettings(Minecraft instance, GuiGeneratorSettings gengui, FontRenderer f, WorldSettings s)
	{
		minecraft = instance;
		guiGeneratorSettings = gengui;
		fr = f;
		oldsettings = s;
	}
	
	public void initGui()
	{
    	buttonList.clear();
    	
    	buttonList.add(Slider_ORES = new BWG4GuiSlider(2, width / 2 - 165, height / 2 - 80 + (0 * 25), "[SLIDER-ORES]", (float) oldsettings.DECO_ORES));
    	buttonList.add(Slider_PLANTS = new BWG4GuiSlider(2, width / 2 - 165, height / 2 - 80 + (1 * 25), "[SLIDER-PLANTS]", (float) oldsettings.DECO_PLANTS));
    	buttonList.add(Slider_TREES = new BWG4GuiSlider(2, width / 2 - 165, height / 2 - 80 + (2 * 25), "[SLIDER-TREES]", (float) oldsettings.DECO_TREES));
    	buttonList.add(Slider_PONDS = new BWG4GuiSlider(2, width / 2 - 165, height / 2 - 80 + (3 * 25), "[SLIDER-PONDS]", (float) oldsettings.DECO_PONDS));
    	buttonList.add(Slider_DUNGEONS = new BWG4GuiSlider(2, width / 2 - 165, height / 2 - 80 + (4 * 25), "[SLIDER-DUNGEONS]", (float) oldsettings.DECO_DUNGEONS));
    	buttonList.add(Slider_CLAY = new BWG4GuiSlider(2, width / 2 - 165, height / 2 - 80 + (5 * 25), "[SLIDER-CLAY]", (float) oldsettings.DECO_CLAY));

    	buttonList.add(Slider_HEIGHT = new BWG4GuiSlider(2, width / 2 + 15, height / 2 - 80 + (0 * 25), "[SLIDER-HEIGHT]", (float) oldsettings.TERRAIN_HEIGHT));
    	buttonList.add(Slider_CAVES = new BWG4GuiSlider(2, width / 2 + 15, height / 2 - 80 + (1 * 25), "[SLIDER-CAVES]", (float) oldsettings.TERRAIN_CAVES));
    	buttonList.add(Slider_RAVINES = new BWG4GuiSlider(2, width / 2 + 15, height / 2 - 80 + (2 * 25), "[SLIDER-RAVINES]", (float) oldsettings.TERRAIN_RAVINES));
    	buttonList.add(Slider_VILLAGES = new BWG4GuiSlider(2, width / 2 + 15, height / 2 - 80 + (3 * 25), "[SLIDER-VILLAGES]", (float) oldsettings.STRUCTURES_VILLAGES));
    	buttonList.add(Slider_SCATTERED = new BWG4GuiSlider(2, width / 2 + 15, height / 2 - 80 + (4 * 25), "[SLIDER-SCATTERED]", (float) oldsettings.STRUCTURES_SCATTERED));
    	buttonList.add(Slider_SIZE = new BWG4GuiSlider(2, width / 2 + 15, height / 2 - 80 + (5 * 25), "[SLIDER-SIZE]", ((float) (oldsettings.BIOME_SIZE - 1) / 9F)));
    	
    	updateSliders(true);
	}
	
    protected void actionPerformed(GuiButton button)
    {
    }
    
    public void updateScreen()
    {
    	
    }
    
	public void updateSliders(boolean all)
	{
		if(Slider_ORES.dragging || all) { Slider_ORES.displayString = "ORES " + (Math.round((((double) Slider_ORES.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_PLANTS.dragging || all) { Slider_PLANTS.displayString = "PLANTS " + (Math.round((((double) Slider_PLANTS.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_TREES.dragging || all) { Slider_TREES.displayString = "TREES " + (Math.round((((double) Slider_TREES.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_PONDS.dragging || all) { Slider_PONDS.displayString = "PONDS " + (Math.round((((double) Slider_PONDS.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_DUNGEONS.dragging || all) { Slider_DUNGEONS.displayString = "DUNGEONS " + (Math.round((((double) Slider_DUNGEONS.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_CLAY.dragging || all) { Slider_CLAY.displayString = "CLAY " + (Math.round((((double) Slider_CLAY.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_HEIGHT.dragging || all) { Slider_HEIGHT.displayString = "HEIGHT " + (Math.round((((double) Slider_HEIGHT.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_CAVES.dragging || all) { Slider_CAVES.displayString = "CAVES " + (Math.round((((double) Slider_CAVES.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_RAVINES.dragging || all) { Slider_RAVINES.displayString = "RAVINES " + (Math.round((((double) Slider_RAVINES.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_VILLAGES.dragging || all) { Slider_VILLAGES.displayString = "VILLAGES " + (Math.round((((double) Slider_VILLAGES.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_SCATTERED.dragging || all) { Slider_SCATTERED.displayString = "SCATTERED " + (Math.round((((double) Slider_SCATTERED.sliderValue) * 4D) * 100D) / 100D); }
		if(Slider_SIZE.dragging || all) { Slider_SIZE.displayString = "Biome size: " + ((int) Math.floor(Slider_SIZE.sliderValue * 9) + 1); }
	}

    public void drawScreen(int par1, int par2, float par3)
    {
    	drawDefaultBackground();
    	
    	String title = "World Settings";
    	drawString(fontRenderer, title, (int) Math.floor(width / 2) - (int) Math.floor(fontRenderer.getStringWidth(title) / 2), 10, 16777215);
    	
    	updateSliders(false);
    	
    	super.drawScreen(par1, par2, par3);
    }*/
}
