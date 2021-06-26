package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.TownyAPI;
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
            try {
                Town town = resident1.getTown();
                return town.getResidents().contains(resident2);
            } catch (NotRegisteredException e) {
                return false;
            }
        }
        return false;
    }

}
