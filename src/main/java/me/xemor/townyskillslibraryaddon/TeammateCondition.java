package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.xemor.skillslibrary.conditions.Condition;
import me.xemor.skillslibrary.conditions.TargetCondition;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

//This has a bug where mayors are not counted as a resident seemingly
public class TeammateCondition extends Condition implements TargetCondition {

    public TeammateCondition(int condition, ConfigurationSection configurationSection) {
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
            if (resident1.hasTown()) {
                Town town = null;
                try {
                    town = resident1.getTown();
                    return town.hasResident(resident2);
                } catch (NotRegisteredException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
