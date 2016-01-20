package ru.nord.common.world.generator;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import ru.nord.NordBloks;
import ru.nord.common.blocks.wood.type2.BlockNordLeaves2;
import ru.nord.common.blocks.wood.type2.BlockNordLog2;
import ru.nord.common.utils.enums.EnumNordPlank2;

public class WorldGenOrangeTree extends WorldGenAbstractTree
{
    private static final IBlockState field_181653_a = NordBloks.nordLog2.getDefaultState().withProperty(BlockNordLog2.VARIANT,EnumNordPlank2.ORANGE);
    private static final IBlockState field_181654_b = Blocks.leaves.getDefaultState().withProperty(BlockNordLeaves2.VARIANT, EnumNordPlank2.ORANGE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

    /** The minimum height of a generated tree. */
    private final int minTreeHeight;
    /** The metadata value of the wood to use in tree generation. */
    private final IBlockState metaWood;
    /** The metadata value of the leaves to use in tree generation. */

    private final IBlockState metaLeaves;
    private static final String __OBFID = "CL_00000438";

    public WorldGenOrangeTree(boolean notify)
    {
        this(notify, 5,field_181653_a, field_181654_b);
    }

    public WorldGenOrangeTree(boolean doBlockNotify, int min, IBlockState meta, IBlockState metaL)
    {
        super(doBlockNotify);
        this.minTreeHeight = min;
        this.metaWood = meta;
        this.metaLeaves = metaL;
    }

    public boolean generate(World worldIn, Random random, BlockPos pos)
    {
        int treeHeight = random.nextInt(2) + this.minTreeHeight;
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + treeHeight + 1 <= 256)
        {
            byte b0;
            int leaveY;

            for (int j = pos.getY(); j <= pos.getY() + 1 + treeHeight; ++j)
            {
                b0 = 1;

                if (j == pos.getY())
                {
                    b0 = 0;
                }

                if (j >= pos.getY() + 1 + treeHeight - 2)
                {
                    b0 = 2;
                }

                for (int k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k)
                {
                    for (leaveY = pos.getZ() - b0; leaveY <= pos.getZ() + b0 && flag; ++leaveY)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(worldIn, new BlockPos(k, j, leaveY)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = pos.down();
                Block block1 = worldIn.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, (IPlantable) NordBloks.nordSapling2);

                if (isSoil && pos.getY() < 256 - treeHeight - 1)
                {
                    block1.onPlantGrow(worldIn, down, pos);
                    b0 = 4;
                    byte b1 = 0;
                    int i1;
                    int j1;
                    int leaveX;
                    int l1;
                    BlockPos blockpos1;

                    for (leaveY = pos.getY() - b0 + treeHeight; leaveY <= pos.getY() + treeHeight; ++leaveY)
                    {
                        i1 = leaveY - (pos.getY() + treeHeight);
                        j1 = b1 + 1 - i1 / 2;
                        if (j1>2) {j1=1;}
                        for (leaveX = pos.getX() - j1; leaveX <= pos.getX() + j1; ++leaveX)
                        {
                            l1 = leaveX - pos.getX();

                            for (int leaveZ = pos.getZ() - j1; leaveZ <= pos.getZ() + j1; ++leaveZ)
                            {
                                int j2 = leaveZ - pos.getZ();
                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || random.nextInt(1) != 0 && i1 != 0)
                                {
                                    blockpos1 = new BlockPos(leaveX, leaveY, leaveZ);
                                    Block block = worldIn.getBlockState(blockpos1).getBlock();

                                    if (block.isAir(worldIn, blockpos1) || block.isLeaves(worldIn, blockpos1) || block.getMaterial() == Material.vine)
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos1, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (leaveY = 0; leaveY < treeHeight; ++leaveY)
                    {
                        BlockPos upN = pos.up(leaveY);
                        Block block2 = worldIn.getBlockState(upN).getBlock();

                        if (block2.isAir(worldIn, upN) || block2.isLeaves(worldIn, upN) || block2.getMaterial() == Material.vine)
                        {
                            this.setBlockAndNotifyAdequately(worldIn, pos.up(leaveY),  this.metaWood);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}