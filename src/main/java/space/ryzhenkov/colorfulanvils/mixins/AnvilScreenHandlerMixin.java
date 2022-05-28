package space.ryzhenkov.colorfulanvils.mixins;

import eu.pb4.placeholders.TextParser;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Shadow
    private String newItemName;

    @ModifyArg(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setCustomName(Lnet/minecraft/text/Text;)Lnet/minecraft/item/ItemStack;", ordinal = 0))
    public Text updateResult(Text name) {
        return TextParser.parse(this.newItemName);
    }

    @ModifyArg(method = "setNewItemName", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setCustomName(Lnet/minecraft/text/Text;)Lnet/minecraft/item/ItemStack;", ordinal = 0))
    public Text newItemName(Text name) {
        return TextParser.parse(this.newItemName);
    }
}
