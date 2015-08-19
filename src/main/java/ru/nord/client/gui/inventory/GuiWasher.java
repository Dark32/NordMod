package ru.nord.client.gui.inventory;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.nord.common.container.ContainerWasher;
import ru.nord.common.tiles.TileWasher;
import ru.nord.common.utils.Version;
import ru.nord_core.client.gui.inventory.abstracts.GuiMachine;
import ru.nord_core.common.utils.Constants;

public class GuiWasher extends GuiMachine {
    private final TileWasher tileEntity;

    public GuiWasher(EntityPlayer player, TileWasher tileWasher) {
        super(new ContainerWasher(player.inventory, tileWasher));
        this.tileEntity = tileWasher;
        this.player = player;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int xAxis = (mouseX - (width - xSize) / 2);
        int yAxis = (mouseY - (height - ySize) / 2);
        String name = this.tileEntity.hasCustomName()
                ? this.tileEntity.getName()
                : I18n.format(this.tileEntity.getName());

        int energy = this.tileEntity.getEnergy() / Constants.SHARE_MULTIPLE;
        int maxEnergy = this.tileEntity.getMaxEnergy() / Constants.SHARE_MULTIPLE;
        int capacity = this.tileEntity.getTank().getFluidAmount();
        int maxCapacity = this.tileEntity.getTank().getCapacity();
        this.fontRendererObj.drawString(name, 16, 6, 4210752);



        drawOverText(9, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) + " share" + (energy > 1 ? "s" : ""));
        drawOverText(161, 20, 4, 54, xAxis, yAxis, String.valueOf(capacity) + "/" + String.valueOf(maxCapacity) + " mb");

        this.fontRendererObj.drawString("X: " + xAxis + " Y: " + yAxis, xAxis, yAxis, 4210752);
        this.fontRendererObj.drawString(":" +tileEntity.getEnergy(), xAxis, yAxis+12, 4210752);
        this.fontRendererObj.drawString(":" +tileEntity.getTank().getFluidAmount(), xAxis, yAxis+24, 4210752);
//        System.err.println(tileEntity.getTank());
//        System.err.println(tileEntity.getTank().getFluidAmount());
//        System.err.println(tileEntity.getTank().getCapacity());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        this.mc.renderEngine.bindTexture(new ResourceLocation(Version.MODID + ":textures/gui/container/washer.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        int progress = this.tileEntity.getProgressScaled(24);
        this.drawTexturedModalRect(k + 92, l + 34, 178, 16, progress, 21);

        progress = this.tileEntity.getEnergyProgressScaled(52);
        this.drawVerticalProgressBar(k + 11, l + 22, 183, 37, 3, 52, progress);

        progress = this.tileEntity.getBurnTimeRemainingScaled(14);
        this.drawVerticalProgressBar(k + 19, l + 41, 176, 2, 14, 14, progress);

        progress = (int)(this.tileEntity.getWaterLevel()*24);
        this.drawVerticalProgressBar(k + 162, l + 22, 216, 37, 3, 14,progress);


    }

}
