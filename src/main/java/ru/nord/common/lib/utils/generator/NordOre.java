package ru.nord.common.lib.utils.generator;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import ru.nord.common.lib.utils.generator.Ore;

import java.util.Random;
/**
 * Created by lionzxy on 13.07.15.
 */
public class NordOre implements IWorldGenerator {
    public static Ore[] listOre = new Ore[16];;
    public static int thisPos=0;
    // Which dimension to generate in by dimension ID (Nether -1, Overworld 0, End 1, etc)
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
            case -1:
                generateAllNetherOre(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateAllOverworldOre(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateAllEndOre(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }
    private void generateAllOverworldOre(World world, Random rand, int chunkX, int chunkZ){
    for(int i=0;i< thisPos;i++){
        if(listOre[i].dimId==0){
            for (int k = 0; k < listOre[i].frequencyOre; k++)
            {
                int firstBlockXCoord = chunkX + rand.nextInt(16);
                int firstBlockZCoord = chunkZ + rand.nextInt(16);
                int quisqueY=-1;
                while (listOre[i].minY>quisqueY||listOre[i].maxY<quisqueY){
                    quisqueY=rand.nextInt(listOre[i].minY+listOre[i].maxY);
                }
                BlockPos quisquePos = new BlockPos(firstBlockXCoord, quisqueY, firstBlockZCoord);
                    //The listOre[i].veinSize as the second parameter sets the maximum vein size
                    (new WorldGenMinable(listOre[i].block,listOre[i].veinSize)).generate(world, rand, quisquePos);
            }
        }
    }
    }
    private void generateAllNetherOre(World world, Random rand, int chunkX, int chunkZ){
        for(int i=0;i< thisPos;i++){
            if(listOre[i].dimId==-1){
                for (int k = 0; k < listOre[i].frequencyOre; k++)
                {
                    int firstBlockXCoord = chunkX + rand.nextInt(16);
                    int firstBlockZCoord = chunkZ + rand.nextInt(16);
                    int quisqueY=-1;
                    while (listOre[i].minY>quisqueY||listOre[i].maxY<quisqueY){
                        quisqueY=rand.nextInt(listOre[i].minY+listOre[i].maxY);
                    }
                    BlockPos quisquePos = new BlockPos(firstBlockXCoord, quisqueY, firstBlockZCoord);
                    //The listOre[i].veinSize as the second parameter sets the maximum vein size
                    (new WorldGenMinable(listOre[i].block,listOre[i].veinSize)).generate(world, rand, quisquePos);
                }
            }
        }
    }
    private void generateAllEndOre(World world, Random rand, int chunkX, int chunkZ){
        for(int i=0;i< thisPos;i++){
            if(listOre[i].dimId==1){
                for (int k = 0; k < listOre[i].frequencyOre; k++)
                {
                    int firstBlockXCoord = chunkX + rand.nextInt(16);
                    int firstBlockZCoord = chunkZ + rand.nextInt(16);
                    int quisqueY=-1;
                    while (listOre[i].minY>quisqueY||listOre[i].maxY<quisqueY){
                        quisqueY=rand.nextInt(listOre[i].minY+listOre[i].maxY);
                    }
                    BlockPos quisquePos = new BlockPos(firstBlockXCoord, quisqueY, firstBlockZCoord);
                    //The listOre[i].veinSize as the second parameter sets the maximum vein size
                    (new WorldGenMinable(listOre[i].block,listOre[i].veinSize)).generate(world, rand, quisquePos);
                }
            }
        }
    }
    //The actual generation method.
   /* private void generateCopper(World world, Random rand, int chunkX, int chunkZ) {
        for (int k = 0; k < 16; k++)
        {
            int firstBlockXCoord = chunkX + rand.nextInt(16);
            int firstBlockZCoord = chunkZ + rand.nextInt(16);
            //Will be found between y = 0 and y = 20
            int quisqueY=-1;
            while (NordConfig.getMinY("copper")>quisqueY||NordConfig.getMaxY("copper")<quisqueY){
                rand.nextInt(NordConfig.getMinY("copper")+NordConfig.getMaxY("copper"));
            }
            BlockPos quisquePos = new BlockPos(firstBlockXCoord, quisqueY, firstBlockZCoord);
            if (NordConfig.copperGeneration)
                //The 10 as the second parameter sets the maximum vein size
                (new WorldGenMinable(NordBloks.metalOre.getStateFromMeta(1), 10)).generate(world, rand, quisquePos);
        }
    }*/
}
