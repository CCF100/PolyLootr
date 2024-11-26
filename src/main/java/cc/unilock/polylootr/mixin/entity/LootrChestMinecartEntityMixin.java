package cc.unilock.polylootr.mixin.entity;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;
import xyz.nucleoid.packettweaker.PacketContext;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(LootrChestMinecartEntity.class)
public class LootrChestMinecartEntityMixin implements PolymerEntity {
	@Override
	public EntityType<?> getPolymerEntityType(PacketContext context) {
		return EntityType.CHEST_MINECART;
	}
}
