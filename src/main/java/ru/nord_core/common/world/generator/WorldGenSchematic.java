package ru.nord_core.common.world.generator;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord.common.utils.Version;
import ru.nord_core.common.utils.schematic.Schematic;
import ru.nord_core.common.utils.schematic.SchematicUtils;

import java.util.Random;

public class WorldGenSchematic extends WorldGenerator {
    private final Schematic schema;

    public WorldGenSchematic(boolean doBlockNotify, String schemaName) {
        super(doBlockNotify);
        schema = SchematicUtils.get().loadSchematic(schemaName, Version.MODID);
    }

    public WorldGenSchematic(boolean doBlockNotify, Schematic schema) {
        super(doBlockNotify);
        this.schema = schema;
    }

    public boolean generate(World worldIn, Random random, BlockPos pos) {
        if (schema == null) {
            FMLLog.getLogger().error("[NORD CORE] Bad null schema");
            return false;
        }
        boolean check = schema.checkGenerate(worldIn, pos);
        if (check) {
            schema.generate(worldIn, pos);
            return true;
        } else {
            return false;
        }
    }
}