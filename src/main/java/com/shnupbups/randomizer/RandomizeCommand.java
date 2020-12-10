package com.shnupbups.randomizer;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import java.util.Random;

public class RandomizeCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("randomize").requires((serverCommandSource) -> {
			return serverCommandSource.hasPermissionLevel(2);
		}).then(CommandManager.literal("loot").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			int seed =  new Random().nextInt();
			Randomizer.setLootSeed(serverCommandSource.getMinecraftServer(), seed);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.loot.seed", seed), true);
			return 1;
		}).then(CommandManager.literal("disable").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			Randomizer.setLootEnabled(serverCommandSource.getMinecraftServer(), false);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.loot.disable"), true);
			return 1;
		})).then(CommandManager.literal("enable").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			Randomizer.setLootEnabled(serverCommandSource.getMinecraftServer(), true);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.loot.enable"), true);
			return 1;
		})).then(CommandManager.literal("query").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			RandomizerState randomizer = Randomizer.getRandomizer(serverCommandSource.getMinecraftServer());
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.loot.query", randomizer.isLootRandomizerEnabled(), randomizer.getLootSeed()), false);
			return 1;
		})).then(CommandManager.literal("seed").then(CommandManager.argument("seed", StringArgumentType.string()).executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			int seed = commandContext.getArgument("seed", String.class).hashCode();
			Randomizer.setLootSeed(serverCommandSource.getMinecraftServer(), seed);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.loot.seed", seed), true);
			return 1;
		})))).then(CommandManager.literal("recipe").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			int seed =  new Random().nextInt();
			Randomizer.setRecipeSeed(serverCommandSource.getMinecraftServer(), seed);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.recipe.seed", seed), true);
			return 1;
		}).then(CommandManager.literal("disable").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			Randomizer.setRecipeEnabled(serverCommandSource.getMinecraftServer(), false);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.recipe.disable"), true);
			return 1;
		})).then(CommandManager.literal("enable").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			Randomizer.setRecipeEnabled(serverCommandSource.getMinecraftServer(), true);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.recipe.enable"), true);
			return 1;
		})).then(CommandManager.literal("query").executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			RandomizerState randomizer = Randomizer.getRandomizer(serverCommandSource.getMinecraftServer());
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.recipe.query", randomizer.isRecipeRandomizerEnabled(), randomizer.getRecipeSeed()), false);
			return 1;
		})).then(CommandManager.literal("seed").then(CommandManager.argument("seed", StringArgumentType.string()).executes((commandContext) -> {
			ServerCommandSource serverCommandSource = commandContext.getSource();
			int seed = commandContext.getArgument("seed", String.class).hashCode();
			Randomizer.setRecipeSeed(serverCommandSource.getMinecraftServer(), seed);
			serverCommandSource.sendFeedback(new TranslatableText("commands.randomizer.recipe.seed", seed), true);
			return 1;
		})))));
	}
}
