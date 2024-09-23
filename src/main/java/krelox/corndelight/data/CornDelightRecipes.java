package krelox.corndelight.data;

import krelox.corndelight.CornDelightTags;
import krelox.corndelight.block.CornDelightBlocks;
import krelox.corndelight.item.CornDelightItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import vectorwing.farmersdelight.common.tag.ConventionalTags;

import java.util.concurrent.CompletableFuture;

public class CornDelightRecipes extends FabricRecipeProvider {
    public CornDelightRecipes(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSingleOutputShapelessRecipe(exporter, CornDelightItems.CORN_SEEDS, CornDelightItems.CORN, null);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, CornDelightItems.CORN,
                RecipeCategory.FOOD, CornDelightBlocks.CORN_CRATE);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, CornDelightItems.CORN_SEEDS,
                RecipeCategory.FOOD, CornDelightBlocks.CORN_KERNEL_BAG);

        offerFoodCookingRecipes(exporter, CornDelightItems.CORN, CornDelightItems.GRILLED_CORN);
        offerFoodCookingRecipes(exporter, CornDelightItems.CORN_SEEDS, CornDelightItems.POPCORN);
        offerFoodCookingRecipes(exporter, CornDelightItems.CORNBREAD_BATTER, CornDelightItems.CORNBREAD);
        offerFoodCookingRecipes(exporter, CornDelightItems.RAW_TORTILLA, CornDelightItems.TORTILLA);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, CornDelightItems.CARAMEL_POPCORN)
                .input(CornDelightItems.POPCORN)
                .input(Items.SUGAR)
                .criterion(hasItem(CornDelightItems.POPCORN), conditionsFromItem(CornDelightItems.POPCORN))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, CornDelightItems.CORNBREAD_BATTER, 3)
                .input(CornDelightTags.Items.CORN)
                .input(CornDelightTags.Items.CORN)
                .input(ConventionalTags.MILKS)
                .input(Items.EGG)
                .criterion(hasItem(CornDelightItems.CORN), conditionsFromTag(CornDelightTags.Items.CORN))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, CornDelightBlocks.POPCORN_BOX)
                .input(CornDelightItems.CARAMEL_POPCORN, 4)
                .input(Items.PAPER)
                .criterion(hasItem(CornDelightItems.CARAMEL_POPCORN), conditionsFromItem(CornDelightItems.CARAMEL_POPCORN))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, CornDelightItems.TACO)
                .input(CornDelightItems.TORTILLA)
                .input(ConventionalTags.CROPS_CABBAGE)
                .input(ConventionalTags.CROPS_ONION)
                .input(CornDelightTags.Items.FOODS_TORTILLA_MEATS)
                .criterion(hasItem(CornDelightItems.TORTILLA), conditionsFromItem(CornDelightItems.TORTILLA))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, CornDelightItems.RAW_TORTILLA, 3)
                .input('#', CornDelightItems.CORN).input('W', ConventionalItemTags.WATER_BUCKETS)
                .pattern("###")
                .pattern(" W ")
                .criterion(hasItem(CornDelightItems.CORN), conditionsFromTag(CornDelightTags.Items.CORN))
                .offerTo(exporter);
    }

    private static void offerFoodCookingRecipes(RecipeExporter exporter, ItemConvertible input, ItemConvertible output) {
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, SmeltingRecipe::new, 200, input, output, 0.5F);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, 600, input, output, 0.5F);
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, input, output, 0.5F);
    }
}
