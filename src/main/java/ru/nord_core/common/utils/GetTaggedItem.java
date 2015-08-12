package ru.nord_core.common.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import ru.nord.Nord;

public class GetTaggedItem {
    /**
     * Use this method to get fireworks with custom NBT Data.
     * This allows you to use following parameters:
     * <p/>
     * Flight (as Integer):
     * 0 (HIGHLY NOT RECOMMENDATED!) - 1-2 Blocks (Almost on the ground);
     * 1 - 12-22 blocks;
     * 2 - 23-39 blocks;
     * 3 - 40-60 blocks.
     * <p/>
     * Type (as Integer):
     * 0 - Small Ball;
     * 1 - Large Ball;
     * 2 - Star-Shaped;
     * 3 - Creeper-Shaped;
     * 4 - Burst (Nothing special).
     * <p/>
     * Trail (as Boolean) - if true, firework sparks will leave the trace.
     * Flicker (as Boolean) - if true, fireworks will make additional noise.
     * <p/>
     * Colors and Fade Colors (as Integer Arrays) - set colors of the fireworks.
     */
    public static ItemStack getFirework(int flight, int type, boolean trail, boolean flicker, int[] colors, int[] fadeColors) {
        ItemStack itemStack = new ItemStack(Items.fireworks);

        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound fireworks = new NBTTagCompound();
        NBTTagCompound explosion = new NBTTagCompound();

        fireworks.setByte("Flight", (byte) flight);
        explosion.setByte("Trail", (byte) (trail == true ? 1 : 0));
        explosion.setByte("Type", (byte) type);
        explosion.setIntArray("Colors", colors);
        explosion.setIntArray("FadeColors", fadeColors);
        explosion.setByte("Flicker", (byte) (flicker == true ? 1 : 0));

        nbt.setTag("Fireworks", fireworks);
        nbt.getCompoundTag("Fireworks").setTag("Explosions", new NBTTagList());
        nbt.getCompoundTag("Fireworks").getTagList("Explosions", Constants.NBT.TAG_COMPOUND).appendTag(explosion);

        itemStack.setTagCompound(nbt);
        return itemStack;
    }

    public static ItemStack getRandomFirework() {
        int[] colors = new int[1 + Nord.rand.nextInt(3)];
        for (int c : colors) {
            colors[c] = Things.getColor(Nord.rand.nextInt(16));
        }
        int[] fadeColors = new int[1 + Nord.rand.nextInt(1)];
        for (int c : fadeColors) {
            fadeColors[c] = Things.getColor(Nord.rand.nextInt(16));
        }
        return GetTaggedItem.getFirework(1 + Nord.rand.nextInt(2), Nord.rand.nextInt(5),
                Nord.rand.nextInt(2) == 1, Nord.rand.nextInt(2) == 1, colors, fadeColors);
    }
}
