package ru.nord_core.common.tiles.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import ru.nord_core.common.utils.enums.*;

public interface IEnergoCable extends IInventory, IEnergyTile {

    /**
     * Обновить список соседей и получить соседей
     * @return соседи
     */
    EnumNeighbors[] updateNeighbors();

    /**
     * Послать энергию соседям
     */
    boolean sendEnergy();

    /**
     * Валидация соседей
     * @param index индекс
     * @return валидность и тип
     */
    EnumNeighbors validateNeighbors(int index);

    /**
     * Получаем абсолютные координаты соседа
     * @param index индекс
     * @return координыты
     */
    BlockPos getNeighborACoordinate(int index);
}
