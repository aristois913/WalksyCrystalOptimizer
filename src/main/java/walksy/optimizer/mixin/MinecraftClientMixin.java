package walksy.optimizer.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.optimizer.WalksyCrystalOptimizerMod;
import walksy.optimizer.command.EnableOptimizerCommand;

import static walksy.optimizer.WalksyCrystalOptimizerMod.limitPackets;
import static walksy.optimizer.WalksyCrystalOptimizerMod.mc;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "startUseItem", cancellable = true)
    private void onDoItemUse(CallbackInfo ci) {
        if (EnableOptimizerCommand.fastCrystal) {
            ItemStack mainHand = mc.player.getMainHandItem();
            if (mainHand.is(Items.END_CRYSTAL))
                //ensures only are packets are being sent through
                if (WalksyCrystalOptimizerMod.hitCount != limitPackets())
                    ci.cancel();
        }
    }
}
