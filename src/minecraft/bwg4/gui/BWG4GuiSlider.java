package bwg4.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.EnumOptions;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class BWG4GuiSlider extends GuiButton
{
    public float sliderValue = 1.0F;
    public boolean dragging;

    public BWG4GuiSlider(int id, int x, int y, String text, float par6)
    {
        super(id, x, y, 150, 20, text);
        this.sliderValue = par6;
    }

    protected int getHoverState(boolean par1)
    {
        return 0;
    }

    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            if (this.dragging)
            {
                this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }

                //par1Minecraft.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
                //this.displayString = par1Minecraft.gameSettings.getKeyBinding(this.idFloat);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        if (super.mousePressed(par1Minecraft, par2, par3))
        {
            this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }

            //par1Minecraft.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
            //this.displayString = par1Minecraft.gameSettings.getKeyBinding(this.idFloat);
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void mouseReleased(int par1, int par2)
    {
        this.dragging = false;
    }
}
