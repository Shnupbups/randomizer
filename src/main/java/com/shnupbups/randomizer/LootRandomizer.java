package com.shnupbups.randomizer;

import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Random;

public interface LootRandomizer {
	Map<Identifier, LootTable> getRandomizedTables();
	Map<Identifier, LootTable> getUnrandomizedTables();
	void setRandomizedTables(Map<Identifier, LootTable> tables);
	default LootTable getRandomizedTable(Identifier id) {
		return getRandomizedTables().getOrDefault(id, getUnrandomizedTables().getOrDefault(id, LootTable.EMPTY));
	}
	default void randomize(Map<Identifier, LootTable> input, Random random) {
		setRandomizedTables(Randomizer.shuffleMap(input, random));
	}
	default void randomize(Random random) {
		randomize(getUnrandomizedTables(), random);
	}
	default void reset() {
		setRandomizedTables(getUnrandomizedTables());
	}
}
