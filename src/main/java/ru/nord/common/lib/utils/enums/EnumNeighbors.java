package ru.nord.common.lib.utils.enums;

public enum EnumNeighbors {
    INVALID(-1),
    ACCEPTOR(0),
    DISPENSER(1),
    CABLE(2),
    ACCUMULATOR_ACCEPTOR(3),
    ACCUMULATOR_DISPENSER(4);
    private final int type;

    EnumNeighbors(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    static public EnumNeighbors getByType(int type){
        for (int i = 0; i <EnumNeighbors.values().length ; i++) {
            EnumNeighbors neighbors = EnumNeighbors.values()[i];
            if (neighbors.getType() == type) return neighbors;
        }
        return EnumNeighbors.INVALID;
    }
}
