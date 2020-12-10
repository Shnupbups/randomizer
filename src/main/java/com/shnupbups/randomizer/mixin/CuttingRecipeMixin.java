package com.shnupbups.randomizer.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.Recipe;

import com.shnupbups.randomizer.RandomizableRecipe;

@Mixin(CuttingRecipe.class)
public abstract class CuttingRecipeMixin implements Recipe, RandomizableRecipe {
	@Final
	@Shadow
	protected ItemStack output;

	@Inject(method = "getOutput()Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
	public void hahaYes(CallbackInfoReturnable<ItemStack> cir) {
		cir.setReturnValue(getRandomizedOutput());
	}

	@Override
	public ItemStack getOriginalOutput() {
		return output;
	}
}
