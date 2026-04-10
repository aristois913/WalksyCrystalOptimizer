package walksy.optimizer.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.optimizer.WalksyCrystalOptimizerMod;
import walksy.optimizer.command.EnableOptimizerCommand;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin {

    /**
     * @Author Walksy
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V", ordinal = 0), method = "tick()V")
    private void useOwnTicks(CallbackInfo ci) {
        if (EnableOptimizerCommand.fastCrystal) {
            WalksyCrystalOptimizerMod.useOwnTicks();
        }
    }
}

