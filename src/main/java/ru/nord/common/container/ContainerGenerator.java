package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import ru.nord.common.container.abstracts.ContainerAbstactGenerator;
import ru.nord.common.container.abstracts.ContainerAbstactMachine;
import ru.nord.common.tiles.TileFlowing;
import ru.nord.common.tiles.TileGenerator;

public class ContainerGenerator extends ContainerAbstactGenerator {

        public ContainerGenerator(InventoryPlayer invPlayer, TileGenerator ent) {
                init(invPlayer, ent);
        }

}
