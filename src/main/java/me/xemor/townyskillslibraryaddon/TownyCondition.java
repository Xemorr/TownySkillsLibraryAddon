package me.xemor.townyskillslibraryaddon;

import com.palmergames.bukkit.towny.TownyAPI;
import me.xemor.skillslibrary.conditions.BlockCondition;
import me.xemor.skillslibrary.conditions.Condition;
import me.xemor.skillslibrary.conditions.EntityCondition;
import me.xemor.skillslibrary.conditions.TargetCondition;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class TownyCondition extends Condition implements EntityCondition, TargetCondition, BlockCondition {

    public TownyCondition(int condition, ConfigurationSection configurationSection) {
        super(condition, configurationSection);
    }

    @Override
    public boolean isTrue(LivingEntity livingEntity, Block block) {
        return !TownyAPI.getInstance().isWilderness(block.getLocation());
    }

    @Override
    public boolean isTrue(LivingEntity livingEntity) {
        return !TownyAPI.getInstance().isWilderness(livingEntity.getLocation());
    }

    @Override
    public boolean isTrue(LivingEntity livingEntity, Entity entity) {
        return !TownyAPI.getInstance().isWilderness(entity.getLocation());
    }
}
