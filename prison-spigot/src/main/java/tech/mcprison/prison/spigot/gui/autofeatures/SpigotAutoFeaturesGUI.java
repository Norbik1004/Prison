package tech.mcprison.prison.spigot.gui.autofeatures;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.cryptomorin.xseries.XMaterial;

import tech.mcprison.prison.autofeatures.AutoFeaturesFileConfig;
import tech.mcprison.prison.autofeatures.AutoFeaturesFileConfig.AutoFeatures;
import tech.mcprison.prison.spigot.SpigotPrison;
import tech.mcprison.prison.spigot.gui.ListenersPrisonManager;
import tech.mcprison.prison.spigot.gui.SpigotGUIComponents;

/**
 * @author GABRYCA
 */
public class SpigotAutoFeaturesGUI extends SpigotGUIComponents {

    private final Player p;
    private final AutoFeaturesFileConfig afConfig = AutoFeaturesFileConfig();
    private final Configuration messages = messages();

    public SpigotAutoFeaturesGUI(Player p){
        this.p = p;
    }

    public void open() {

        // Create the inventory and set up the owner, dimensions or number of slots, and title
        int dimension = 54;
        Inventory inv = Bukkit.createInventory(null, dimension, SpigotPrison.format("&3PrisonManager -> AutoFeatures"));


        if (guiBuilder(inv)) return;

        // Open the inventory
        openGUI(p, inv);
    }

    private boolean guiBuilder(Inventory inv) {
        try {
            buttonsSetup(inv);
        } catch (NullPointerException ex){
            p.sendMessage(SpigotPrison.format("&cThere's a null value in the GuiConfig.yml [broken]"));
            ex.printStackTrace();
            return true;
        }
        return false;
    }

    private void buttonsSetup(Inventory inv) {


        // Declare buttons
        ItemStack autoPickup;
        ItemStack autoSmelt;
        ItemStack autoBlock;
        ItemStack enabledOrDisabled;
        ItemStack playSound;
        ItemStack hologram;

        List<String> closeGUILore = createLore(
          messages.getString("Lore.ClickToClose")
        );

        ItemStack closeGUI = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), closeGUILore, SpigotPrison.format("&c" + "Close"));

        if ( afConfig.isFeatureBoolean( AutoFeatures.playSoundIfInventoryIsFull ) ){

            List<String> EnabledOrDisabledLore = createLore(
                    messages.getString("Lore.FullSoundEnabled"),
                    messages.getString("Lore.ShiftAndRightClickToDisable"));
            playSound = createButton(XMaterial.LIME_STAINED_GLASS_PANE.parseItem(), EnabledOrDisabledLore, SpigotPrison.format("&a" + "Full_Inv_Play_Sound Enabled"));

        } else {

            List<String> EnabledOrDisabledLore = createLore(
                    messages.getString("Lore.FullSoundDisabled"),
                    messages.getString("Lore.RightClickToEnable"));
            playSound = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), EnabledOrDisabledLore, SpigotPrison.format("&c" + "Full_Inv_Play_Sound Disabled"));

        }

        if ( afConfig.isFeatureBoolean( AutoFeatures.hologramIfInventoryIsFull ) ){

            List<String> EnabledOrDisabledLore = createLore(
                    messages.getString("Lore.FullHologramEnabled"),
                    messages.getString("Lore.ShiftAndRightClickToDisable"));
            hologram = createButton(XMaterial.LIME_STAINED_GLASS_PANE.parseItem(), EnabledOrDisabledLore, SpigotPrison.format("&a" + "Full_Inv_Hologram Enabled"));

        } else {

            List<String> EnabledOrDisabledLore = createLore(
                    messages.getString("Lore.FullHologramDisabled"),
                    messages.getString("Lore.RightClickToEnable"));
            hologram = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), EnabledOrDisabledLore, SpigotPrison.format("&c" + "Full_Inv_Hologram Disabled"));

        }

        if ( afConfig.isFeatureBoolean( AutoFeatures.isAutoManagerEnabled ) ){

            List<String> EnabledOrDisabledLore = createLore(
                    messages.getString("Lore.EnabledAll"),
                    messages.getString("Lore.ShiftAndRightClickToDisable"));
            enabledOrDisabled = createButton(XMaterial.LIME_STAINED_GLASS_PANE.parseItem(), EnabledOrDisabledLore, SpigotPrison.format("&a" + "All Enabled"));

        } else {

            List<String> EnabledOrDisabledLore = createLore(
                    messages.getString("Lore.DisabledAll"),
                    messages.getString("Lore.RightClickToEnable"));
            enabledOrDisabled = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), EnabledOrDisabledLore, SpigotPrison.format("&c" + "All Disabled"));

        }

        if ( afConfig.isFeatureBoolean( AutoFeatures.autoPickupEnabled ) ) {
            // Lore of the button
            List<String> autoPickupLore = createLore(
                    messages.getString("Lore.AutoPickupGuiManager"),
                    messages.getString("Lore.ShiftAndRightClickToDisable"),
                    messages.getString("Lore.LeftClickToOpen"));
            autoPickup = createButton(XMaterial.LIME_STAINED_GLASS_PANE.parseItem(), autoPickupLore, SpigotPrison.format("&3" + "AutoPickup Enabled"));
        } else {
            // Lore of the button
            List<String> autoPickupLore = createLore(
                    messages.getString("Lore.AutoPickupGuiManager"),
                    messages.getString("Lore.RightClickToEnable"),
                    messages.getString("Lore.LeftClickToOpen"));
            autoPickup = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), autoPickupLore, SpigotPrison.format("&c" + "AutoPickup Disabled"));
        }


        if ( afConfig.isFeatureBoolean( AutoFeatures.autoSmeltEnabled ) ) {
            // Lore of the button
            List<String> autoSmeltLore = createLore(
                    messages.getString("Lore.AutoSmeltGuiManager"),
                    messages.getString("Lore.ShiftAndRightClickToDisable"),
                    messages.getString("Lore.LeftClickToOpen"));
            autoSmelt = createButton(XMaterial.LIME_STAINED_GLASS_PANE.parseItem(), autoSmeltLore, SpigotPrison.format("&3" + "AutoSmelt Enabled"));
        } else {
            // Lore of the button
            List<String> autoSmeltLore = createLore(
                    messages.getString("Lore.AutoSmeltGuiManager"),
                    messages.getString("Lore.RightClickToEnable"),
                    messages.getString("Lore.LeftClickToOpen"));
            autoSmelt = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), autoSmeltLore, SpigotPrison.format("&c" + "AutoSmelt Disabled"));
        }


        if ( afConfig.isFeatureBoolean( AutoFeatures.autoBlockEnabled ) ) {
            // Lore of the button
            List<String> autoBlockLore = createLore(
                    messages.getString("Lore.AutoBlockGuiManager"),
                    messages.getString("Lore.ShiftAndRightClickToDisable"),
                    messages.getString("Lore.LeftClickToOpen"));
            autoBlock = createButton(XMaterial.LIME_STAINED_GLASS_PANE.parseItem(), autoBlockLore, SpigotPrison.format("&3" + "AutoBlock Enabled"));

        } else {
            // Lore of the button
            List<String> autoBlockLore = createLore(
                    messages.getString("Lore.AutoBlockGuiManager"),
                    messages.getString("Lore.RightClickToEnable"),
                    messages.getString("Lore.LeftClickToOpen"));
            autoBlock = createButton(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), autoBlockLore, SpigotPrison.format("&c" + "AutoBlock Disabled"));
        }

        // Position of the button
        inv.setItem(11, playSound);
        inv.setItem(15, hologram);
        inv.setItem(28, autoPickup);
        inv.setItem(31, autoSmelt);
        inv.setItem(34, autoBlock);
        inv.setItem(37, enabledOrDisabled);
        inv.setItem(40, enabledOrDisabled);
        inv.setItem(43, enabledOrDisabled);
        inv.setItem(53, closeGUI);
    }
}
