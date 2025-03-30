package cc.unilock.polylootr.mixin.blocks;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import noobanidus.mods.lootr.common.block.LootrInventoryBlock;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import cc.unilock.polylootr.impl.model.SimpleBlockModel;
import xyz.nucleoid.packettweaker.PacketContext;

@Mixin(LootrInventoryBlock.class)
public class LootrInventoryBlockMixin implements FactoryBlock {
	@Override
	public BlockState getPolymerBlockState(BlockState blockState, PacketContext packetContext) {
		return Blocks.BARRIER.defaultBlockState();
		// .withPropertiesOf(blockState);
	}

	@Override
	public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
		return Blocks.CHEST.withPropertiesOf(state);
	}

	@Override
	public void onPolymerBlockSend(BlockState blockState, BlockPos.MutableBlockPos pos,
			PacketContext.NotNullWithPlayer contexts) {
		contexts.getClientConnection()
				.send(PolymerBlockUtils.createBlockEntityPacket(pos, BlockEntityType.CHEST, null));
	}

	@Override
	public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
		return new SimpleBlockModel(pos, initialBlockState);
	}
}
