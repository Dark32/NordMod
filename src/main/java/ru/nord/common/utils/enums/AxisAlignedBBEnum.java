package ru.nord.common.utils.enums;

import net.minecraft.util.math.AxisAlignedBB;

public enum AxisAlignedBBEnum {
    EmpireFloorLamp(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F),
    FloorLamp(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F),
    OilLamp(0.187F, 0.0F, 0.187F, 0.812F, 1.0F, 0.812F),

    ;

    private final AxisAlignedBB aabb;

    AxisAlignedBBEnum(AxisAlignedBB aabb) {
        this.aabb = aabb;
    }
    AxisAlignedBBEnum(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.aabb = new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
    }

    public AxisAlignedBB getBound() {
        return this.aabb;
    }
}
