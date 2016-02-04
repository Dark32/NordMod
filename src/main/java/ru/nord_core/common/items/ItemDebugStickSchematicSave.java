package ru.nord_core.common.items;



import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.utils.schematic.Schematic;

import java.util.List;

public class ItemDebugStickSchematicSave extends ItemBase {

    public ItemDebugStickSchematicSave(String modid) {
        super(modid);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return false;
        if (playerIn.isSneaking()) {
            System.err.println("onItemUseFirst+Shift");
        } else {
            NBTTagCompound posTag = new NBTTagCompound();
            posTag.setInteger("PosX", pos.getX());
            posTag.setInteger("PosY", pos.getY());
            posTag.setInteger("PosZ", pos.getZ());
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            NBTTagCompound tag = stack.getTagCompound();
            if (!tag.hasKey("pos1", Constants.NBT.TAG_COMPOUND)) {
                tag.setTag("pos1", posTag);
                playerIn.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GRAY +
                                "[debugStick] Set pos 1: "
                                + pos.getX() + ", "
                                + pos.getY() + ", "
                                + pos.getZ()
                ));
            } else if (!tag.hasKey("pos2", Constants.NBT.TAG_COMPOUND)) {
                tag.setTag("pos2", posTag);
                playerIn.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GRAY +
                                "[debugStick] Set pos 2: "
                                + pos.getX() + ", "
                                + pos.getY() + ", "
                                + pos.getZ()
                ));
            } else if (!tag.hasKey("pos3", Constants.NBT.TAG_COMPOUND)) {
                tag.setTag("pos3", posTag);
                playerIn.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.GRAY +
                                "[debugStick] Set pos 3: "
                                + pos.getX() + ", "
                                + pos.getY() + ", "
                                + pos.getZ()
                ));
            } else {
                NBTTagCompound pos1Tag = tag.getCompoundTag("pos1");
                int x1 = pos1Tag.getInteger("PosX");
                int y1 = pos1Tag.getInteger("PosY");
                int z1 = pos1Tag.getInteger("PosZ");
                NBTTagCompound pos2Tag = tag.getCompoundTag("pos2");
                int x2 = pos2Tag.getInteger("PosX");
                int y2 = pos2Tag.getInteger("PosY");
                int z2 = pos2Tag.getInteger("PosZ");
                NBTTagCompound pos3Tag = tag.getCompoundTag("pos3");
                int x3 = pos3Tag.getInteger("PosX");
                int y3 = pos3Tag.getInteger("PosY");
                int z3 = pos3Tag.getInteger("PosZ");

                AxisAlignedBB aabb = new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
                Schematic schem = new Schematic();
                String time = String.valueOf(System.currentTimeMillis());
                BlockPos posOrig = new BlockPos(x1 - x3, y1-y3, z1-z3);
                schem.save(time, schem.getFromWorld(aabb, worldIn, posOrig));
                playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[debugStick] Save "+time));
            }
            stack.setTagCompound(tag);
        }
        return false;
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        if (worldIn.isRemote) return itemStackIn;
        if (playerIn.isSneaking()) {
            if (!itemStackIn.hasTagCompound()) {
                itemStackIn.setTagCompound(new NBTTagCompound());
            } else {
                NBTTagCompound tag = itemStackIn.getTagCompound();
                if (tag.hasKey("pos1", Constants.NBT.TAG_COMPOUND)) {
                    tag.removeTag("pos1");
                }
                if (tag.hasKey("pos2", Constants.NBT.TAG_COMPOUND)) {
                    tag.removeTag("pos2");
                }
                if (tag.hasKey("pos3", Constants.NBT.TAG_COMPOUND)) {
                    tag.removeTag("pos3");
                }
                playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[debugStick] Clear all pos"));
            }
        }
        return itemStackIn;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("pos1", Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound posTag = tag.getCompoundTag("pos1");
                int x = posTag.getInteger("PosX");
                int y = posTag.getInteger("PosY");
                int z = posTag.getInteger("PosZ");
                tooltip.add(EnumChatFormatting.WHITE + "Pos 1: " + x + ", " + y + ", " + z);
            }
            if (tag.hasKey("pos2", Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound posTag = tag.getCompoundTag("pos2");
                int x = posTag.getInteger("PosX");
                int y = posTag.getInteger("PosY");
                int z = posTag.getInteger("PosZ");
                tooltip.add(EnumChatFormatting.WHITE + "Pos 2: " + x + ", " + y + ", " + z);
            }
            if (tag.hasKey("pos3", Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound posTag = tag.getCompoundTag("pos3");
                int x = posTag.getInteger("PosX");
                int y = posTag.getInteger("PosY");
                int z = posTag.getInteger("PosZ");
                tooltip.add(EnumChatFormatting.WHITE + "Pos origin: " + x + ", " + y + ", " + z);
            }
        } else {
            tooltip.add(EnumChatFormatting.WHITE + "Select 2 poin to draw selection box");
            tooltip.add(EnumChatFormatting.WHITE + "First Block R Click - set position 1");
            tooltip.add(EnumChatFormatting.WHITE + "Second Block R Click - set position 2");
            tooltip.add(EnumChatFormatting.WHITE + "Еhird Block R Click - set position origin");
            tooltip.add(EnumChatFormatting.WHITE + "Shift R Click - clear selection");
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

}