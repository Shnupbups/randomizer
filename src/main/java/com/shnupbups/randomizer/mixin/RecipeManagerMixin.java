package com.shnupbups.randomizer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;

import com.shnupbups.randomizer.RandomizableRecipe;
import com.shnupbups.randomizer.Randomizer;
import com.shnupbups.randomizer.RecipeRandomizer;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin implements RecipeRandomizer {
	@Override
	public Map<RandomizableRecipe, RandomizableRecipe> getRandomizedRecipes() {
		return Randomizer.randomizedRecipes;
	}

	@Override
	public List<RandomizableRecipe> getUnrandomizedRecipes() {
		ArrayList<RandomizableRecipe> list = new ArrayList<>();
		for(Recipe<?> recipe:values()) {
			if(recipe instanceof RandomizableRecipe) {
				list.add((RandomizableRecipe) recipe);
			}
		}
		list.sort(null);
		return list;
	}

	@Shadow
	public abstract Collection<Recipe<?>> values();

	@Override
	public void setRandomizedRecipes(Map<RandomizableRecipe, RandomizableRecipe> recipes) {
		Randomizer.randomizedRecipes = recipes;
	}
}
