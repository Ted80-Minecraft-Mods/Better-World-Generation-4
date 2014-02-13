package bwg4.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiGeneratorButton 
{
	public GuiButton button;
	public int generatorID;
	
	public GuiGeneratorButton(String name, int genID, int buttonID, int posY, int width)
	{
		button = new GuiButton(buttonID, width / 2 - 155, posY, 150, 20, name);
		generatorID = genID;
	}
}