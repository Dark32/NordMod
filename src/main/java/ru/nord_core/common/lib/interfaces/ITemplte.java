package ru.nord_core.common.lib.interfaces;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.HashMap;

/**
 * Created by andrew on 11.12.15.
 */
public interface ITemplte {
    enum Error {
        NONE, // нет ошибки
        INVALID_FORMAT, // общая ошибка формата
        BLOCK_NOT_FOUND, // блок не найден
        INCORRECT_LAYER_COUNT, // неверно указаны размеры шаблона
        INVALID_LAYER, // ошибка в слое
        INVALID_LAYER_COUNT, // число слоёв не соответствует определённму выше
        INCORRECT_OFFSET_COUNT, // неверно указан сдвиг центра
        INVALID_OFFSET_COUNT, // сдвиг центра за пределами шаблона
    }

    /**
     * Установить сырой шаблон
     *
     * @param template шаблон
     */

    void setRawTemplate(String template);

    /**
     * Получить сырой шаблое
     *
     * @return шаблон
     */
    String getRawTemplate();

    /**
     * Подготовить шаблон из сырова вида во внутренние предствление
     *
     * @return ошибка подготовки
     */
    Error prepare();

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
     * Сдвиг начала Х
     *
     * @return сдвиг
     */
    int getXOffset();

    /**
     * Сдвиг начала Y
     *
     * @return сдвиг
     */
    int getYOffset();

    /**
     * Сдвиг начала Z
     *
     * @return сдвиг
     */
    int getZOffset();

    /**
     * Обработать идесы блоков
     * @param index Индекс
     * @param modId ИД мода
     * @param name имя блока
     */
    void readBlockIndexes(int index, String modId, String name);

    /**
     * Таблица соответствия индекса и блока
     *
     * @return таблица
     */
    HashMap<Integer, Block> getIndexsesBlock();
    /**
     *
     * Таблица соответствия индекса и мода блока
     *
     * @return таблица
     */
    HashMap<Integer, String> getIndexsesMod();

    /**
     *
     * Таблица соответствия индекса и имени блока
     *
     * @return таблица
     */
    HashMap<Integer, String> getIndexsesName();

    /**
     * Таблица соответствия индекса блока и методаты
     *
     * @return таблица
     */
    HashMap<Integer, Integer> getIndexesBlockMetadata();

    /**
     * Сгенерировать структуру по шаблону
     */
    void generate(World world, BlockPos pos);
}
