package cc.unilock.polylootr.mixin.blocks;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import noobanidus.mods.lootr.common.block.TrophyBlock;
import xyz.nucleoid.packettweaker.PacketContext;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(TrophyBlock.class)
public class TrophyBlockMixin implements PolymerHeadBlock {
	@Override
	public BlockState getPolymerBlockState (BlockState state, PacketContext context) {
		return Blocks.PLAYER_HEAD.withPropertiesOf(state);
	}

	// https://minecraft.novaskin.me/skin/6459833115/Trophy-gold
	@Override
	public String getPolymerSkinValue(BlockState state, BlockPos pos, PacketContext context) {
		return "ewogICJ0aW1lc3RhbXAiIDogMTY5MTM1OTg5MTMxOSwKICAicHJvZmlsZUlkIiA6ICJiNGJmZDZhNmRiZGQ0MDg2ODRhYmIzYzlmNDQyNmRiYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJWZXJzYWNlNjciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjU2MDVhN2NlNThhNzExZGU3Y2UxYzRiOGM4MjhkY2ZkNjZjYWE1M2Q3MTIwNGQ0NTNmMGViZDM1ZTk5YzJhNiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
	}
}
