package com.shnupbups.randomizer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.loot.LootManager;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Randomizer implements ModInitializer {
	public static final String MOD_ID = "randomizer";

	public static MinecraftServer server;
	
	public static Map<RandomizableRecipe, RandomizableRecipe> randomizedRecipes = ImmutableMap.of();
	
	@Override
	public void onInitialize() {
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
			refreshLoot(server);
			refreshRecipe(server);
		});
		ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
			Randomizer.server = server;
			refreshLoot(server);
			refreshRecipe(server);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> RandomizeCommand.register(dispatcher));
	}

	public static void setLootSeed(MinecraftServer server, int seed) {
		RandomizerState randomizer = getRandomizer(server);
		randomizer.setLootRandomizerEnabled(true);
		randomizer.setLootSeed(seed);
		refreshLoot(server);
	}

	public static void setLootEnabled(MinecraftServer server, boolean enabled) {
		RandomizerState randomizer = getRandomizer(server);
		randomizer.setLootRandomizerEnabled(enabled);
		refreshLoot(server);
	}

	public static void refreshLoot(MinecraftServer server) {
		RandomizerState randomizer = getRandomizer(server);
		if(randomizer.isLootRandomizerEnabled()) {
			((LootRandomizer)server.getLootManager()).randomize(new Random(randomizer.getLootSeed()));
		} else {
			((LootRandomizer)server.getLootManager()).reset();
		}
	}

	public static void setRecipeSeed(MinecraftServer server, int seed) {
		RandomizerState randomizer = getRandomizer(server);
		randomizer.setRecipeRandomizerEnabled(true);
		randomizer.setRecipeSeed(seed);
		refreshRecipe(server);
	}

	public static void setRecipeEnabled(MinecraftServer server, boolean enabled) {
		RandomizerState randomizer = getRandomizer(server);
		randomizer.setRecipeRandomizerEnabled(enabled);
		refreshRecipe(server);
	}

	public static void refreshRecipe(MinecraftServer server) {
		RandomizerState randomizer = getRandomizer(server);
		if(randomizer.isRecipeRandomizerEnabled()) {
			((RecipeRandomizer)server.getRecipeManager()).randomize(new Random(randomizer.getRecipeSeed()));
		} else {
			((RecipeRandomizer)server.getRecipeManager()).reset();
		}
	}

	public static RandomizerState getRandomizer(MinecraftServer server) {
		return server.getOverworld().getPersistentStateManager().getOrCreate(RandomizerState::new, "randomizer");
	}
	
	public static Identifier id(String id) {
		return new Identifier(MOD_ID, id);
	}

	public static <K,V> Map<K,V> shuffleMap(Map<K,V> map, Random random) {
		List<V> valueList = new ArrayList<>(map.values());
		Collections.shuffle(valueList, random);
		Iterator<V> valueIt = valueList.iterator();
		Map<K,V> newMap = new HashMap<>(map.size());
		for(K key : map.keySet()) {
			newMap.put(key, valueIt.next());
		}
		return newMap;
	}
}
