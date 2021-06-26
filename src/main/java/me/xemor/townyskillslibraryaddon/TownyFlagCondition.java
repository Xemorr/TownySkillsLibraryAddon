package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import me.xemor.skillslibrary.conditions.BlockCondition;
import me.xemor.skillslibrary.conditions.Condition;
import me.xemor.skillslibrary.conditions.EntityCondition;
import me.xemor.skillslibrary.conditions.TargetCondition;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * A condition that checks that a player has the ability to perform four different actions: BUILD, DESTROY, ITEM_USE, SWITCH
 */
public class TownyFlagCondition extends Condition implements EntityCondition, TargetCondition, BlockCondition {

    TownyPermission.ActionType action;

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
    public boolean isTrue(LivingEntity livingEntity, Block block) {
        if (livingEntity instanceof Player) {
            return PlayerCacheUtil.getCachePermission((Player) livingEntity, block.getLocation(), block.getType(), TownyPermission.ActionType.BUILD);
        }
        return true;
    }

    @Override
    public boolean isTrue(LivingEntity livingEntity) {
        if (livingEntity instanceof Player) {
            return PlayerCacheUtil.getCachePermission((Player) livingEntity, livingEntity.getLocation(), livingEntity.getLocation().getBlock().getType(), TownyPermission.ActionType.BUILD);
        }
        return true;
    }

    @Override
    public boolean isTrue(LivingEntity livingEntity, Entity entity) {
        return false;
    }
}
