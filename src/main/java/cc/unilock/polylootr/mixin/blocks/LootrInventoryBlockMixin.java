package cc.unilock.polylootr.mixin.blocks;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import noobanidus.mods.lootr.common.block.LootrInventoryBlock;
import xyz.nucleoid.packettweaker.PacketContext;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(LootrInventoryBlock.class)
public class LootrInventoryBlockMixin implements PolymerBlock {
	@Override
	public BlockState getPolymerBlockState (BlockState state, PacketContext context) {
		return Blocks.CHEST.withPropertiesOf(state);
	}

	@Override
	public void onPolymerBlockSend(BlockState blockState, BlockPos.MutableBlockPos pos, PacketContext.NotNullWithPlayer contexts) {
		contexts.getPlayer().connection.send(PolymerBlockUtils.createBlockEntityPacket(pos, BlockEntityType.CHEST, null));
	}
}
