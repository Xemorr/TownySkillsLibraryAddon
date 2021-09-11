package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.utils.CombatUtil;
import me.xemor.skillslibrary2.conditions.Condition;
import me.xemor.skillslibrary2.conditions.TargetCondition;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

//fucking strange class, pls fix
public class TeammateCondition extends Condition implements TargetCondition {

    public TeammateCondition(int condition, ConfigurationSection configurationSection) {
        super(condition, configurationSection);
    }

    @Override
    public boolean isTrue(Entity entity, Entity target) {
        if (entity instanceof Player && target instanceof Player) {
            Player player = (Player) entity;
            Player targetPlayer = (Player) target;
            Resident resident1 = TownyUniverse.getInstance().getResident(player.getUniqueId());
            Resident resident2 = TownyUniverse.getInstance().getResident(targetPlayer.getUniqueId());
            if (resident1 == null || resident2 == null) {
                return true;
            }
            return CombatUtil.isSameTown(resident1, resident2);
        }
        return false;
    }

}
