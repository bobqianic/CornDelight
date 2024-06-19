package krelox.corndelight;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import krelox.corndelight.block.CornDelightBlocks;
import krelox.corndelight.item.CornDelightItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.ComposterBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class CornDelight implements ModInitializer {

    public static final String MODID = "corndelight";

    @Override
    public void onInitialize() {
        CornDelightItems.registerItems();
        CornDelightBlocks.registerBlocks();

        ItemGroupEvents.modifyEntriesEvent(FarmersDelightMod.ITEM_GROUP).register(entries -> CornDelightItems.ITEMS.forEach(entries::add));

        registerCompostables();

        BiomeModifications.addFeature(context -> context.getBiome().getTemperature() > 0f && context.getBiome().getTemperature() <= 1f,
                GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MODID, "patch_wild_corn")));
    }

    public static void registerCompostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CornDelightItems.CORN_SEEDS, 0.3F);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CornDelightItems.CORN, 0.65F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(CornDelightBlocks.WILD_CORN.asItem(), 0.65F);
    }
}