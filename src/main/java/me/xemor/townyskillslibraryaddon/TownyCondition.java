package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.TownyAPI;
import me.xemor.skillslibrary2.conditions.Condition;
import me.xemor.skillslibrary2.conditions.EntityCondition;
import me.xemor.skillslibrary2.conditions.LocationCondition;
import me.xemor.skillslibrary2.conditions.TargetCondition;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

public class TownyCondition extends Condition implements EntityCondition, TargetCondition, LocationCondition {

    public TownyCondition(int condition, ConfigurationSection configurationSection) {
        super(condition, configurationSection);
    }

    @Override
    public boolean isTrue(Entity entity, Location location) {
        return !TownyAPI.getInstance().isWilderness(location);
    }

    @Override
    public boolean isTrue(Entity entity) {
        return !TownyAPI.getInstance().isWilderness(entity.getLocation());
    }

    @Override
    public boolean isTrue(Entity entity, Entity target) {
        return !TownyAPI.getInstance().isWilderness(target.getLocation());
    }
}
