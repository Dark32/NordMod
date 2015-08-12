package ru.nord.client.gui.inventory;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import ru.nord_core.client.gui.inventory.abstracts.GuiMachine;
import ru.nord.common.container.ContainerFlowing;
import ru.nord_core.common.utils.Constants;
import ru.nord.common.utils.Version;
import ru.nord.common.tiles.TileFlowing;

public class GuiFlowing extends GuiMachine {
        private final TileFlowing tileEntity;

        public GuiFlowing(EntityPlayer player, TileFlowing tileFlowing) {
                super(new ContainerFlowing(player.inventory, tileFlowing));
                this.tileEntity = tileFlowing;
                this.player = player;
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(Version.MODID + ":textures/gui/container/flowing.png"));
            int x = (width - xSize) / 2;
            int y = (height - ySize) / 2;
            int k = (this.width - this.xSize) / 2;
            int l = (this.height - this.ySize) / 2;
            this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

            int progress = this.tileEntity.getProgressScaled(24);
            this.drawTexturedModalRect(k + 78, l + 35, 180, 16, progress, 21);

            progress = this.tileEntity.getEnergyProgressScaled(52);
            this.drawTexturedModalRect(k + 11, l + 22, 183, 37, 3, 52); // Отрисовать полную текстуру огня
            this.drawTexturedModalRect(k + 11, l + 22, 11, 22, 3, 52 - progress); // А поверх нее рисовать обычную текстуру (без огня)

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
            drawOverText(9, 20, 4, 54, xAxis, yAxis, String.valueOf(energy) + "/" + String.valueOf(maxEnergy) +  " " +Constants.ENERGY);
        }


}
