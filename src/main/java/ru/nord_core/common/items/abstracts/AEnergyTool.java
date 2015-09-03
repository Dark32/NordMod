package ru.nord_core.common.items.abstracts;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.nord_core.common.items.interfaces.IEnergyTool;
import ru.nord_core.common.utils.ChargeHelper;

import java.util.List;
import java.util.Set;

public abstract class AEnergyTool extends ItemTool implements IEnergyTool {
    protected int powerOnUse = 1;
    protected int poweOnHit = 1;
    private static final Set EFFECTIVE_ON = Sets.newHashSet();

    protected AEnergyTool(float attackDamage,
                          ToolMaterial material,
                          int energyByUse,
                          int energyByHit) {
        super(attackDamage, material, EFFECTIVE_ON);
        this.powerOnUse = energyByUse;
        this.poweOnHit = energyByHit;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block) {
        if (ChargeHelper.hasEnergy(stack, powerOnUse(stack))) {
            return this.efficiencyOnProperMaterial;
        } else {
            return 1.0F;
        }
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     *
     * @param target   The Entity being hit
     * @param attacker the attacking entity
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (ChargeHelper.hasEnergy(stack, powerOnHit(stack))) {
            ChargeHelper.subEnergy(stack, powerOnHit(stack));
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
        if ((double) blockIn.getBlockHardness(worldIn, pos) != 0.0D) {
            ChargeHelper.subEnergy(stack, powerOnUse(stack));
        }

        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }



    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        if (!(ChargeHelper.hasEnergy(stack, powerOnUse(stack))) ||
                toolClass == null ||
                !this.getHarvestType().getName().equals(toolClass)){
            return -1;
        }
        return this.toolMaterial.getHarvestLevel();
    }

    @Override
    public float getDigSpeed(ItemStack stack, net.minecraft.block.state.IBlockState state)
    {
       if (state.getBlock().isToolEffective(getHarvestType().getName(), state))
                return efficiencyOnProperMaterial;

        return 1.0F;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            list.add("Power: " + currectEnergy(itemStack) +"/" + maxEnergy(itemStack) + " share");
            list.add("Harvest Level: " + toolMaterial.getHarvestLevel());
            list.add("Efficiency: " + toolMaterial.getEfficiencyOnProperMaterial());
        } else list.add(StatCollector.translateToLocal("information.ShiftDialog"));
    }

    @Override
    public boolean hasDisCharge() {
        return false;
    }

    @Override
    public int powerOnUse(ItemStack stack) {
        return powerOnUse;
    }

    @Override
    public int powerOnHit(ItemStack stack) {
        return poweOnHit;
    }

    @Override
    public abstract TOOLS getHarvestType();

    @Override
    public abstract int currectEnergy(ItemStack itemStack);

    @Override
    public abstract int maxEnergy(ItemStack itemStack);

    @Override
    public abstract int setEnergy(ItemStack itemStack, int energy);

    @Override
    public abstract int packetEnergy(ItemStack itemStack);
}
