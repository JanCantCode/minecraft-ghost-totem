package tk.jandev.totemfake.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;

public class TotemFaker {

    static MinecraftClient mc = MinecraftClient.getInstance();
    public static boolean enabled = true;
    static int totemSlot = 1;

    public static int putTotemInHand()  {
        mc.player.getInventory().selectedSlot = totemSlot-1;
        mc.player.getInventory().setStack(totemSlot-1, Items.TOTEM_OF_UNDYING.getDefaultStack());
        return 1;
    }
}
