package bwg4.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiSmallButton;

public class BWG4GuiGeneratorButton 
{
	public GuiButton button;
	public int generatorID;
	
	public BWG4GuiGeneratorButton(String name, int genID, int buttonID, int posY, int width)
	{
		button = new GuiSmallButton(buttonID, width / 2 - 155, posY, 150, 20, name);
		generatorID = genID;
	}
}