package ru.nord_core.common.lib;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nord_core.common.lib.abstracts.ATemplateReader;

import java.util.Arrays;

/*
|3|3|3
|0|0|0
------
|1|2|1
|3|4|3
|1|2|1
------
|5|5|5
|6|4|5
|5|5|6
------
|7|0|7
|0|5|0
|7|0|7
------
|3|nord:nordLog1:8
|2|nord:nordLog1:4
|6|nord:nordLeaves3:0
|1|nord:nordLeaves2:0
|4|nord:nordLog1:0
|7|minecraft:glass:0
|5|nord:nordLeaves3:8
 */
public class TemplateReader extends ATemplateReader {
    private int[][][] template;

    @Override
    public Error prepare() {
        if (!this.getRawTemplate().contains("------")) return Error.INVALID_FORMAT;
        String[] part = this.getRawTemplate().split("------");

        String[] demensional = part[0].split("\n");
        String[] table = Arrays.copyOfRange(part, 1, part.length - 2);
        String[] blockTable = part[part.length - 1].split("\n");

        String[] laeyrs = demensional[0].split("|");
        String[] offset = demensional[1].split("|");

        yLayers = Integer.parseInt(laeyrs[0]);
        xLayers = Integer.parseInt(laeyrs[1]);
        zLayers = Integer.parseInt(laeyrs[2]);
        yOffset = Integer.parseInt(offset[0]);
        xOffset = Integer.parseInt(offset[1]);
        zOffset = Integer.parseInt(offset[2]);

        for (String line : blockTable) {
            String[] blockLine = line.split("|");
            int index = Integer.parseInt(blockLine[0]);
            String[] blockID = blockLine[1].split(":");
            modIndex.put(index, blockID[0]);
            nameIndex.put(index, blockID[1]);
            metadataIndex.put(index, Integer.parseInt(blockID[2]));
            readBlockIndexes(index, blockID[1], blockID[2]);
        }
        this.template = new int[xLayers][yLayers][zLayers];
        if (yLayers != table.length) return Error.INVALID_LAYER_COUNT;
        for (int y = 0; y < yLayers; y++) {
            String[] yTable = table[y].split("\n");
            if (xLayers != yTable.length) return Error.INVALID_LAYER_COUNT;
            for (int x = 0; x < xLayers; x++) {
                String[] xTable = yTable[x].split("|");
                if (zLayers != xTable.length) return Error.INVALID_LAYER_COUNT;
                for (int z = 0; z < zLayers; z++) {
                    template[x][y][z] = Integer.parseInt(xTable[z]);
                }
            }
        }
        return Error.NONE;
    }


    @Override
    public void generate(World world, BlockPos pos) {
        pos = pos.add(-getXOffset(), -getYOffset(), -getZOffset());
        for (int x = 0; x < getXLayers(); x++) {
            for (int y = 0; y < getYLayers(); y++) {
                for (int z = 0; z < getZLayers(); z++) {
                    int indx= template[x][y][x];
                    Block block = blockIndex.get(indx);
                    int meta = metadataIndex.get(indx);
                    IBlockState state = block.getStateFromMeta(meta);
                    world.setBlockState(pos.add(x,y,z),state,3);
                }
            }
        }
    }
}
