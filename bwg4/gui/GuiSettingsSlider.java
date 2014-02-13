package bwg4.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiSettingsSlider extends GuiSettingsButton
{
	public GuiSettingsSlider(String[] text, int[] values, int def, int buttonID, int posY, int width)
	{
		super(new GuiSlider(buttonID, width / 2 + 5, posY), text, values, buttonID, -1, new int[0]);
		((GuiSlider) button).setSlider(this, def);
	}
	
	public GuiSettingsSlider(String[] text, int[] values, int def, int buttonID, int posY, int width, int dep, int[] vel)
	{
		super(new GuiSlider(buttonID, width / 2 + 5, posY), text, values, buttonID, dep, vel);
		((GuiSlider) button).setSlider(this, def);
	}
	
	@Override
	public void click()
	{
	}

	@Override
	public void setOldValue(int oldValue)
	{
		for(int i = 0; i < valuearray.length; i++)
		{
			if(valuearray[i] == oldValue)
			{
				((GuiSlider) button).sliderValue = Math.round(oldValue) / (float) (valuearray.length - 1);
				((GuiSlider) button).setText();
				break;
			}
		} 
	}
}
