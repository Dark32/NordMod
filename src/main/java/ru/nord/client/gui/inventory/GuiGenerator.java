package ru.nord.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.nord.client.gui.inventory.abstracts.GuiMachine;
import ru.nord.common.container.ContainerFlowing;
import ru.nord.common.container.ContainerGenerator;
import ru.nord.common.lib.utils.Constants;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.lib.utils.Version;
import ru.nord.common.tiles.TileFlowing;
import ru.nord.common.tiles.TileGenerator;

public class GuiGenerator extends GuiMachine {
        private final TileGenerator tileEntity;

        public GuiGenerator(EntityPlayer player, TileGenerator tileFlowing) {
                super(new ContainerGenerator(player.inventory, tileFlowing));
                this.tileEntity = tileFlowing;
                this.player = player;
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(Version.MODID + ":textures/gui/container/generator_1.png"));
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;

            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

            int progress = this.tileEntity.getEnergyProgressScaled(52);
            this.drawTexturedModalRect(k + 11, l + 22, 183, 37, 3, 52);
            this.drawTexturedModalRect(k + 11, l + 22, 11, 22, 3, 52 - progress);

            boolean charge = this.tileEntity.getCharge(1);
            if (charge)
                 this.drawTexturedModalRect(k + 133, l + 43, 182, 95, 5, 11);

            charge = this.tileEntity.getCharge(2);
            if (charge)
                 this.drawTexturedModalRect(k + 153, l + 43, 182, 95, 5, 11);

            progress = this.tileEntity.getBurnTimeRemainingScaled(14);
            this.drawTexturedModalRect(k + 19, l + 41, 176, 2, 14, 14);
            this.drawTexturedModalRect(k + 19, l + 41, 19, 41, 14, 14 - progress);
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
            this.fontRendererObj.drawString(name, 16, 6, 4210752);
            drawOverText(9, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) + " " +Constants.ENERGY);
            this.fontRendererObj.drawString("X: " + xAxis + " Y: " + yAxis, xAxis, yAxis, 4210752);
        }


}