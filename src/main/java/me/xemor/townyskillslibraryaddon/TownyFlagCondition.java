package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import me.xemor.skillslibrary2.conditions.Condition;
import me.xemor.skillslibrary2.conditions.EntityCondition;
import me.xemor.skillslibrary2.conditions.LocationCondition;
import me.xemor.skillslibrary2.conditions.TargetCondition;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


/**
 * A condition that checks that a player has the ability to perform four different actions: BUILD, DESTROY, ITEM_USE, SWITCH
 */
public class TownyFlagCondition extends Condition implements EntityCondition, TargetCondition, LocationCondition {

    private TownyPermission.ActionType action;

    public TownyFlagCondition(int condition, ConfigurationSection configurationSection) {
        super(condition, configurationSection);
        String flag = configurationSection.getString("flag");
        try {
            action = TownyPermission.ActionType.valueOf(flag);
        } catch (IllegalArgumentException e) {
            TownySkillsLibraryAddon.getInstance().getLogger().severe("You have entered an invalid flag " + flag + " at " + configurationSection.getCurrentPath() + ".flag" + ". The only valid flags are BUILD, DESTROY, ITEM_USE and SWITCH.");
        }
    }

    @Override
    public boolean isTrue(Entity entity, Location location) {
        if (entity instanceof Player) {
            World world = location.getWorld();
            Material type = world.getBlockAt(location).getType();
            return PlayerCacheUtil.getCachePermission((Player) entity, location, type, action);
        }
        return true;
    }

    @Override
    public boolean isTrue(Entity entity) {
        if (entity instanceof Player) {
            return PlayerCacheUtil.getCachePermission((Player) entity, entity.getLocation(), entity.getLocation().getBlock().getType(), action);
        }
        return true;
    }

    @Override
    public boolean isTrue(Entity entity, Entity target) {
        if (entity instanceof Player) {
            return PlayerCacheUtil.getCachePermission((Player) entity, target.getLocation(), target.getLocation().getBlock().getType(), action);
        }
        return false;
    }
}
