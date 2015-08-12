package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nord_core.common.container.abstracts.ContainerAbstactAccumulator;
import ru.nord.common.tiles.TileAccumulator;

public class ContainerAccumulator extends ContainerAbstactAccumulator {


        public ContainerAccumulator(InventoryPlayer invPlayer, TileAccumulator ent) {
                init(invPlayer, ent);
        }

}
