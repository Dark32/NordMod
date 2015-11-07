package ru.nord.common.world.generator;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import ru.nord.common.utils.enums.EnumNordPlank;

import java.util.Random;

public class WorldGenSomeTree extends WorldGenAbstractTree {
    private final Block leaves = null;
    private final IPlantable sapling = null;
    private final Block log = null;

    public WorldGenSomeTree(boolean flag) {
        super(flag);
    }

    public boolean generate(World worldIn, Random random, BlockPos pos) {
        int i = random.nextInt(4) + 6;
        int j = 1 + random.nextInt(2);
        int k = i - j;
        int l = 2 + random.nextInt(2);
        boolean flag = true;

        if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256) {
            int j1;
            int i3;

            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + i && flag; ++i1) {
                boolean flag1 = true;

                if (i1 - pos.getY() < j) {
                    i3 = 0;
                } else {
                    i3 = l;
                }

                for (j1 = pos.getX() - i3; j1 <= pos.getX() + i3 && flag; ++j1) {
                    for (int k1 = pos.getZ() - i3; k1 <= pos.getZ() + i3 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            BlockPos off = new BlockPos(j1, i1, k1);
                            Block block = worldIn.getBlockState(off).getBlock();

                            if (!block.isAir(worldIn, off) && !block.isLeaves(worldIn, off)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos down = pos.down();
                Block block1 = worldIn.getBlockState(down).getBlock();
                boolean isSoil = block1.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, this.sapling);

                if (isSoil && pos.getY() < 256 - i - 1) {
                    block1.onPlantGrow(worldIn, down, pos);
                    i3 = random.nextInt(2);
                    j1 = 1;
                    byte b0 = 0;
                    int l1;
                    int j3;

                    for (j3 = 0; j3 <= k; ++j3) {
                        l1 = pos.getY() + i - j3;

                        for (int i2 = pos.getX() - i3; i2 <= pos.getX() + i3; ++i2) {
                            int j2 = i2 - pos.getX();

                            for (int k2 = pos.getZ() - i3; k2 <= pos.getZ() + i3; ++k2) {
                                int l2 = k2 - pos.getZ();

                                if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                                    BlockPos blockpos1 = new BlockPos(i2, l1, k2);

                                    if (worldIn.getBlockState(blockpos1).getBlock().canBeReplacedByLeaves(worldIn, blockpos1)) {
                                        this.func_175905_a(worldIn, blockpos1, this.leaves, EnumNordPlank.SOME_PLANK.getMetadata());
                                    }
                                }
                            }
                        }

                        if (i3 >= j1) {
                            i3 = b0;
                            b0 = 1;
                            ++j1;

                            if (j1 > l) {
                                j1 = l;
                            }
                        } else {
                            ++i3;
                        }
                    }

                    j3 = random.nextInt(3);

                    for (l1 = 0; l1 < i - j3; ++l1) {
                        BlockPos upN = pos.up(l1);
                        Block block2 = worldIn.getBlockState(upN).getBlock();

                        if (block2.isAir(worldIn, upN) || block2.isLeaves(worldIn, upN)) {
                            this.func_175905_a(worldIn, pos.up(l1), this.log, EnumNordPlank.SOME_PLANK.getMetadata());
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
