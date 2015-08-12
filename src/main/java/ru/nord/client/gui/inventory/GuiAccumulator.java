package ru.nord.client.gui.inventory;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.nord_core.client.gui.inventory.abstracts.GuiMachine;
import ru.nord.common.container.ContainerAccumulator;
import ru.nord_core.common.utils.Constants;
import ru.nord.common.utils.Version;
import ru.nord.common.tiles.TileAccumulator;

public class GuiAccumulator extends GuiMachine {
        private final TileAccumulator tileEntity;

        public GuiAccumulator(EntityPlayer player, TileAccumulator tileFlowing) {
                super(new ContainerAccumulator(player.inventory, tileFlowing));
                this.tileEntity = tileFlowing;
                this.player = player;
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(Version.MODID + ":textures/gui/container/energy_storage.png"));
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;

            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

            int progress = this.tileEntity.getEnergyProgressScaled(1,52);
            this.drawTexturedModalRect(k + 11, l + 22, 183, 37, 3, 52);
            this.drawTexturedModalRect(k + 11, l + 22, 11, 22, 3, 52 - progress);

            progress = this.tileEntity.getEnergyProgressScaled(2,52);
            this.drawTexturedModalRect(k + 17, l + 22, 183, 37, 3, 52);
            this.drawTexturedModalRect(k + 17, l + 22, 11, 22, 3, 52 - progress);

            progress = this.tileEntity.getEnergyProgressScaled(3,52);
            this.drawTexturedModalRect(k + 23, l + 22, 183, 37, 3, 52);
            this.drawTexturedModalRect(k + 23, l + 22, 11, 22, 3, 52 - progress);

            progress = this.tileEntity.getEnergyProgressScaled(4,52);
            this.drawTexturedModalRect(k + 29, l + 22, 183, 37, 3, 52);
            this.drawTexturedModalRect(k + 29, l + 22, 11, 22, 3, 52 - progress);

            boolean charge = this.tileEntity.getCharge();
            if (charge)
                 this.drawTexturedModalRect(k + 130, l + 43, 176, 30, 6, 6);

            charge = this.tileEntity.getDisCharge();
            if (charge)
                 this.drawTexturedModalRect(k + 152, l + 43, 182, 30, 6, 6);

            if (tileEntity.getBonusMaxEnergy()>0){
                this.drawTexturedModalRect(k + 33, l + 21, 188, 37, 5, 54);
                progress = this.tileEntity.getBonusEnergy()*52/this.tileEntity.getBonusMaxEnergy();
                this.drawTexturedModalRect(k + 34, l + 22, 183, 37, 3, 52);
                this.drawTexturedModalRect(k + 34, l + 22, 11, 22, 3, 52 - progress);
            }

        }

        @Override
        protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
            int xAxis = (mouseX - (width - xSize) / 2);
            int yAxis = (mouseY - (height - ySize) / 2);

            String name = this.tileEntity.hasCustomName()
                    ? this.tileEntity.getName()
                    : I18n.format(this.tileEntity.getName());

            int energy;
            int maxEnergy = this.tileEntity.getEnergyByLine() / Constants.SHARE_MULTIPLE;
            this.fontRendererObj.drawString(name, 8, 6, 4210752);

            energy = this.tileEntity.getEnergyProgressScaled(1,this.tileEntity.getEnergyByLine())/ Constants.SHARE_MULTIPLE;
            drawOverText(9, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) +  " " +Constants.ENERGY);
            energy = this.tileEntity.getEnergyProgressScaled(2,this.tileEntity.getEnergyByLine())/ Constants.SHARE_MULTIPLE;
            drawOverText(15, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) + " " +Constants.ENERGY);
            energy = this.tileEntity.getEnergyProgressScaled(3,this.tileEntity.getEnergyByLine())/ Constants.SHARE_MULTIPLE;
            drawOverText(21, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) +  " " +Constants.ENERGY);
            energy = this.tileEntity.getEnergyProgressScaled(4,this.tileEntity.getEnergyByLine())/ Constants.SHARE_MULTIPLE;
            drawOverText(27, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) + " " +Constants.ENERGY);
            if (tileEntity.getBonusMaxEnergy()>0) {
                energy = this.tileEntity.getBonusEnergy() / Constants.SHARE_MULTIPLE;
                maxEnergy = this.tileEntity.getBonusMaxEnergy() / Constants.SHARE_MULTIPLE;
                drawOverText(33, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) + " share");
            }
            this.fontRendererObj.drawString("X: " + xAxis + " Y: " + yAxis, xAxis, yAxis, 4210752);
            this.fontRendererObj.drawString(":" +tileEntity.getEnergy(), xAxis, yAxis+12, 4210752);
        }


}
