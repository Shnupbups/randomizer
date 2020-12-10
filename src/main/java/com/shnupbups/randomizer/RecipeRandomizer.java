package com.shnupbups.randomizer;

import net.minecraft.recipe.Recipe;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public interface RecipeRandomizer {
	Map<RandomizableRecipe, RandomizableRecipe> getRandomizedRecipes();
	List<RandomizableRecipe> getUnrandomizedRecipes();
	void setRandomizedRecipes(Map<RandomizableRecipe, RandomizableRecipe> recipes);
	default RandomizableRecipe getRandomizedRecipe(RandomizableRecipe recipe) {
		return getRandomizedRecipes().get(recipe);
	}
	default void randomize(List<RandomizableRecipe> input, Random random) {
		Map<RandomizableRecipe, RandomizableRecipe> recipes = makeMapFromList(input);
		setRandomizedRecipes(Randomizer.shuffleMap(recipes, random));
	}
	static Map<RandomizableRecipe, RandomizableRecipe> makeMapFromList(List<RandomizableRecipe> list) {
		Map<RandomizableRecipe, RandomizableRecipe> map = new HashMap<>();
		for(RandomizableRecipe recipe:list) {
			map.put(recipe, recipe);
		}
		return map;
	}
	default void randomize(Random random) {
		randomize(getUnrandomizedRecipes(), random);
	}
	default void reset() {
		setRandomizedRecipes(makeMapFromList(getUnrandomizedRecipes()));
	}
}
