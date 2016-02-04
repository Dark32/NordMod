package ru.nord_core.common.utils.schematic.interfaces;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface ISchematic {

    /**
     * получить количество Х слоёв
     *
     * @return количество
     */
    int getXLayers();

    /**
     * получить количество Y слоёв
     *
     * @return количество
     */
    int getYLayers();

    /**
     * получить количество Z слоёв
     *
     * @return количество
     */
    int getZLayers();

    /**
     * Получить точку начала
     *
     * @return точка начала
     */
    BlockPos getOrigin();

    /**
     * Сгенерировать структуру по шаблону
     */
    void generate(World world, BlockPos pos);
}
