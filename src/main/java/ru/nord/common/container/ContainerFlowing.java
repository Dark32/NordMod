package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nordwest.nord.common.container.abstracts.ContainerAbstactMachine;
import ru.nordwest.nord.common.tiles.TileFlowing;

public class ContainerFlowing extends ContainerAbstactMachine {


        public ContainerFlowing(InventoryPlayer invPlayer, TileFlowing ent) {
                init(invPlayer, ent);
        }

}
