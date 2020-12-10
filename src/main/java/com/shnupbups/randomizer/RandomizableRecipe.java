package com.shnupbups.randomizer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

public interface RandomizableRecipe extends Comparable<RandomizableRecipe> {
	ItemStack getOriginalOutput();
	default ItemStack getRandomizedOutput() {
		if(Randomizer.randomizedRecipes.containsKey(this))
			return Randomizer.randomizedRecipes.get(this).getOriginalOutput();
		else {
			System.out.println("oh gosh "+this+" with og output"+getOriginalOutput().getTranslationKey()+" isnt in the thing");
			return getOriginalOutput();
		}
	}
	Identifier getId();

	@Override
	default int compareTo(RandomizableRecipe other) {
		return Integer.compare(getId().hashCode(), other.getId().hashCode());
	}
}
