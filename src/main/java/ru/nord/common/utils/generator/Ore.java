package ru.nord.common.utils.generator;

import net.minecraft.block.state.IBlockState;

/**
 * Created by lionzxy on 13.07.15.
 */
public class Ore {

    public int maxY;
    public int minY;
    public IBlockState block;
    public int frequencyOre;
    public int dimId;
    public int veinSize;

    public Ore(int minY,int maxY,IBlockState block,int frequencyOre, int dimId,int veinSize){
        this.veinSize=veinSize;
        this.minY=minY;
        this.maxY=maxY;
        this.block=block;
        this.frequencyOre=frequencyOre;
        this.dimId=dimId;
    }
}
