package cicadas.mixtape.armordurability.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.awt.*;

@Mixin(EquipmentLayerRenderer.class)
public abstract class EquipmentLayerRendererMixin {
    @ModifyExpressionValue(method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/EquipmentLayerRenderer;getColorForLayer(Lnet/minecraft/client/resources/model/EquipmentClientInfo$Layer;I)I"))
    public int renderLayers(int original, @Local(argsOnly = true) ItemStack stack) {
        int color;
        var percent = 100f - ((float) stack.getDamageValue() / stack.getMaxDamage() * 100f);

        if (percent >= 80f) {
            color = original;
        } else if (percent >= 60f) {
            color = Color.CYAN.getRGB();
        } else if (percent >= 40f) {
            color = Color.GREEN.getRGB();
        } else if (percent >= 20f) {
            color = Color.YELLOW.getRGB();
        } else {
            color = Color.RED.getRGB();
        }

        return color;
    }
}