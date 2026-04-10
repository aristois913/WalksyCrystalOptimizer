package walksy.optimizer.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;

public class EnableOptimizerCommand {

    public static boolean fastCrystal = true;

    public void initializeToggleCommands() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommands.literal("walksyfastcrystal").executes(context -> {
                    if (fastCrystal) {
                        fastCrystal = false;
                        displayMessage("Walksy's Fast crystals disabled!");
                    } else if (!fastCrystal) {
                        fastCrystal = true;
                        displayMessage("Walksy's Fast crystals enabled");
                    }
                    return 1;
                }))
        );
    }


    public static void displayMessage(String message) {
        // Make sure that they are in game.
        if (!inGame()) return;

        Minecraft client = Minecraft.getInstance();
        ChatComponent chatHud = client.gui.getChat();

        chatHud.addClientSystemMessage(Component.nullToEmpty(message));
    }


    public static Boolean inGame() {
        Minecraft client = Minecraft.getInstance();
        return client.player != null && client.getConnection() != null;
    }
}