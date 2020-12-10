package com.shnupbups.randomizer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

import com.shnupbups.randomizer.LootRandomizer;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

@Mixin(LootManager.class)
public class LootManagerMixin implements LootRandomizer {
	@Shadow private Map<Identifier, LootTable> tables;
	public Map<Identifier, LootTable> randomizedTables = ImmutableMap.of();

	@Override
	public Map<Identifier, LootTable> getRandomizedTables() {
		return randomizedTables;
	}

	@Override
	public Map<Identifier, LootTable> getUnrandomizedTables() {
		return tables;
	}

	@Inject(method = "getTable(Lnet/minecraft/util/Identifier;)Lnet/minecraft/loot/LootTable;", at = @At("HEAD"), cancellable = true)
	public void hahaYes(Identifier id, CallbackInfoReturnable<LootTable> cir) {
		cir.setReturnValue(getRandomizedTable(id));
	}

	@Override
	public void setRandomizedTables(Map<Identifier, LootTable> tables) {
		this.randomizedTables = tables;
	}
}
