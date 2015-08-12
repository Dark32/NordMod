package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nord.common.tiles.TileExtractor;
import ru.nord_core.common.container.abstracts.ContainerAbstactMachine;

public class ContainerExtractor extends ContainerAbstactMachine {

        public ContainerExtractor(InventoryPlayer invPlayer, TileExtractor ent) {
                init(invPlayer, ent);
        }

}
