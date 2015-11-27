package ru.nord.common.tiles;

import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyCable2;
import ru.nord_core.common.utils.Constants;

public class TileEnergyCable2 extends TileAbstractEnergyCable2 {


    @Override
    public int getMaxEnergy() {
        return Constants.SHARE_MULTIPLE*10;
    }

    @Override
    public int getPacketEnergy() {
        return Constants.SHARE_MULTIPLE;
    }

}
