package tk.jandev.totemfake.client;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class TotemfakeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register((LiteralArgumentBuilder)ClientCommandManager.literal("autoghost").
                    then(ClientCommandManager.argument("totemslot", IntegerArgumentType.integer(0, 100)).executes((context) -> {
                TotemFaker.totemSlot = IntegerArgumentType.getInteger(context, "totemslot");
                context.getSource().sendFeedback(Text.literal("§eUpdated TotemFake slot to §c"+ TotemFaker.totemSlot));
                return 1;
            })));
        });

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("toggleautoghost").executes(context -> {
            TotemFaker.enabled = !TotemFaker.enabled;
            if (TotemFaker.enabled) {
                context.getSource().sendFeedback(Text.literal("§eToggled auto ghost §don!"));
            } else {
                context.getSource().sendFeedback(Text.literal("§eToggled auto ghost §doff!"));
            }
            return 1;
        })));

    }
}
