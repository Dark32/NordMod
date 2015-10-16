package ru.nord.common.items;


import net.minecraft.item.ItemStack;
import ru.nord_core.common.items.abstracts.AItemDrill;
import ru.nord_core.common.utils.Constants;


public class ItemDrill extends AItemDrill {
    public ItemDrill(float attackDamage, ToolMaterial material,
                     int energyByUse, int energyByHit) {
        super(attackDamage, material, energyByUse, energyByHit);
    }

    @Override
    public TOOLS getHarvestType() {
        return TOOLS.PICKAXE;
    }



    @Override
    public int maxEnergy(ItemStack itemStack) {
        return 160000;
    }

    @Override
    public int packetEnergy(ItemStack itemStack) {
        return Constants.SHARE_MULTIPLE;
    }

}
