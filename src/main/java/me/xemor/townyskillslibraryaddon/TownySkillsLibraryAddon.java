package me.xemor.townyskillslibraryaddon;

import me.xemor.skillslibrary2.conditions.Conditions;
import org.bukkit.plugin.java.JavaPlugin;

public final class TownySkillsLibraryAddon extends JavaPlugin {

    private static TownySkillsLibraryAddon townySkillsLibraryAddon;

    @Override
    public void onEnable() {
        // Plugin startup logic
        townySkillsLibraryAddon = this;
        Conditions.register("TOWNYFLAG", TownyFlagCondition.class);
        Conditions.register("TOWN", TownyCondition.class);
        Conditions.register("TEAMMATE", TeammateCondition.class);
        Conditions.register("FRIEND", FriendCondition.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TownySkillsLibraryAddon getInstance() {
        return townySkillsLibraryAddon;
    }
}

