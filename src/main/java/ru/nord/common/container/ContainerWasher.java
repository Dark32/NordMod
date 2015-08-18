package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nord.common.tiles.TileWasher;
import ru.nord_core.common.container.abstracts.ContainerAbstactMachine;

public class ContainerWasher extends ContainerAbstactMachine {

    public ContainerWasher(InventoryPlayer invPlayer, TileWasher ent) {
        init(invPlayer, ent);
    }

}
