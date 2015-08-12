package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nord_core.common.container.abstracts.ContainerAbstactGenerator;
import ru.nord.common.tiles.TileGenerator;

public class ContainerGenerator extends ContainerAbstactGenerator {

        public ContainerGenerator(InventoryPlayer invPlayer, TileGenerator ent) {
                init(invPlayer, ent);
        }

}
