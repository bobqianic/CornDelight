package krelox.corndelight.data;

import krelox.corndelight.CornDelightTags;
import krelox.corndelight.block.CornDelightBlocks;
import krelox.corndelight.item.CornDelightItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class CornDelightItemTags extends FabricTagProvider.ItemTagProvider {
    public CornDelightItemTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(CornDelightTags.Items.CORN).add(CornDelightItems.CORN);
        getOrCreateTagBuilder(CornDelightTags.Items.CORN_SEEDS).add(CornDelightItems.CORN_SEEDS);
        getOrCreateTagBuilder(CornDelightTags.Items.FOODS_TORTILLA_MEATS)
                .addOptionalTag(CommonTags.FOODS_COOKED_CHICKEN)
                .addOptionalTag(CommonTags.FOODS_COOKED_BEEF)
                .addOptionalTag(CommonTags.FOODS_COOKED_MUTTON)
                .addOptionalTag(CommonTags.FOODS_COOKED_PORK);

        getOrCreateTagBuilder(ModTags.WILD_CROPS_ITEM).add(CornDelightBlocks.WILD_CORN.asItem());

        getOrCreateTagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT).add(CornDelightItems.CREAMY_CORN_DRINK);
        getOrCreateTagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(CornDelightItems.CORN);
        getOrCreateTagBuilder(CompatibilityTags.TINKERS_CONSTRUCT_SEEDS).addOptionalTag(CornDelightTags.Items.CORN_SEEDS);
    }
}
