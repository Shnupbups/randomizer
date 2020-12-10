package com.shnupbups.randomizer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.PersistentState;

import java.util.Random;

public class RandomizerState extends PersistentState {
	private int lootSeed;
	private boolean lootRandomizerEnabled = false;
	private int recipeSeed;
	private boolean recipeRandomizerEnabled = false;

	public RandomizerState() {
		super("randomizer");
		this.setLootSeed(new Random().nextInt());
		this.setRecipeSeed(new Random().nextInt());
	}

	@Override
	public void fromTag(CompoundTag tag) {
		CompoundTag loot = tag.getCompound("loot");
		lootRandomizerEnabled = loot.getBoolean("enabled");
		lootSeed = loot.getInt("seed");
		CompoundTag recipe = tag.getCompound("recipe");
		recipeRandomizerEnabled = recipe.getBoolean("enabled");
		recipeSeed = recipe.getInt("seed");
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		CompoundTag loot = new CompoundTag();
		loot.putBoolean("enabled", lootRandomizerEnabled);
		loot.putInt("seed", lootSeed);
		tag.put("loot", loot);
		CompoundTag recipe = new CompoundTag();
		recipe.putBoolean("enabled", recipeRandomizerEnabled);
		recipe.putInt("seed", recipeSeed);
		tag.put("recipe", recipe);
		return tag;
	}

	public boolean isLootRandomizerEnabled() {
		return lootRandomizerEnabled;
	}

	public int getLootSeed() {
		return lootSeed;
	}

	public void setLootSeed(int seed) {
		this.lootSeed = seed;
		this.markDirty();
	}

	public void setLootRandomizerEnabled(boolean enabled) {
		this.lootRandomizerEnabled = enabled;
		this.markDirty();
	}

	public boolean isRecipeRandomizerEnabled() {
		return recipeRandomizerEnabled;
	}

	public int getRecipeSeed() {
		return recipeSeed;
	}

	public void setRecipeSeed(int seed) {
		this.recipeSeed = seed;
		this.markDirty();
	}

	public void setRecipeRandomizerEnabled(boolean enabled) {
		this.recipeRandomizerEnabled = enabled;
		this.markDirty();
	}
}
