package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nord.common.tiles.TileFurnace;
import ru.nord_core.common.container.abstracts.ContainerAbstactMachine;

public class ContainerFurnace extends ContainerAbstactMachine {

        public ContainerFurnace(InventoryPlayer invPlayer, TileFurnace ent) {
                init(invPlayer, ent);
        }

}
