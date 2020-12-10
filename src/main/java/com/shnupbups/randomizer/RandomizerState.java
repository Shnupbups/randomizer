package com.shnupbups.randomizer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.PersistentState;

import java.util.Random;

public class RandomizerState extends PersistentState {
	private int lootSeed;
	private boolean lootRandomizerEnabled = false;

	public RandomizerState() {
		super("randomizer");
		this.setLootSeed(new Random().nextInt());
	}

	@Override
	public void fromTag(CompoundTag tag) {
		CompoundTag loot = tag.getCompound("loot");
		lootRandomizerEnabled = loot.getBoolean("enabled");
		lootSeed = loot.getInt("seed");
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		CompoundTag loot = new CompoundTag();
		loot.putBoolean("enabled", lootRandomizerEnabled);
		loot.putInt("seed", lootSeed);
		tag.put("loot", loot);
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
}
