package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import me.xemor.skillslibrary.conditions.Condition;
import me.xemor.skillslibrary.conditions.TargetCondition;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FriendCondition extends Condition implements TargetCondition {

    public FriendCondition(int condition, ConfigurationSection configurationSection) {
        super(condition, configurationSection);
    }

    @Override
    public boolean isTrue(LivingEntity livingEntity, Entity entity) {
        if (livingEntity instanceof Player && entity instanceof Player) {
            Player player = (Player) livingEntity;
            Player target = (Player) entity;
            Resident resident1 = TownyUniverse.getInstance().getResident(player.getUniqueId());
            Resident resident2 = TownyUniverse.getInstance().getResident(target.getUniqueId());
            if (resident1 == null || resident2 == null) {
                return true;
            }
            return resident1.hasFriend(resident2);
        }
        return false;
    }
}
