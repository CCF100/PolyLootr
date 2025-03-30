package cc.unilock.polylootr.mixin.blocks;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import eu.pb4.polymer.virtualentity.api.ElementHolder;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.LootrBarrelBlock;
import noobanidus.mods.lootr.common.block.entity.LootrBarrelBlockEntity;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import cc.unilock.polylootr.PolyLootr;
import cc.unilock.polylootr.impl.model.SimpleBlockModel;
import xyz.nucleoid.packettweaker.PacketContext;

@Mixin(LootrBarrelBlock.class)
public class LootrBarrelBlockMixin implements PolymerTexturedBlock {
	private BlockState polymerBlockState;

	@Inject(method = "<init>", at = @At("RETURN"), remap = false)
	private void onConstructor(CallbackInfo ci) {
		this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK,
				PolymerBlockModel.of(ResourceLocation.fromNamespaceAndPath("lootr", "block/lootr_barrel_unopened")));
		// Lootr.LOGGER.info("Hello from the LootrBarrelBlockMixin :3");
	}

	@Inject(method = "tick", at = @At("HEAD"), remap = false)
	private void onTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, CallbackInfo ci) {
		determinePolymerBlockState(pState, pLevel, pPos);
	}

	@Override
	public BlockState getPolymerBlockState(BlockState blockState, PacketContext context) {
		return this.polymerBlockState;
	}

	@Override
	public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
		return this.polymerBlockState;
	}

	@Override
	public void onPolymerBlockSend(BlockState blockState, BlockPos.MutableBlockPos pos,
			PacketContext.NotNullWithPlayer contexts) {
		contexts.getClientConnection()
				.send(PolymerBlockUtils.createBlockEntityPacket(pos, BlockEntityType.BARREL, null));
	}

public void determinePolymerBlockState(BlockState pState, ServerLevel pLevel, BlockPos pPos) {
		BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
		String resourcePath = "block/lootr_";
		int x = 0, y = 0;
		if (blockEntity instanceof ILootrBlockEntity) {
		switch(pState.getValue(BarrelBlock.FACING)) {
			case DOWN:
                x = 180;
				break;
			case EAST:
                y = 90;
				break;
			case NORTH:
				break;
			case SOUTH:
                y = 180;
				break;
			case UP:
				break;
			case WEST:
                y = 270;
				break;
			default:
				break;
		}
	}
    if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
		if (pState.getValue(BarrelBlock.OPEN)) {
			resourcePath += "opened_barrel_open";
		} else {
			resourcePath += "opened_barrel";
		}
    } else {
		if (pState.getValue(BarrelBlock.OPEN)) {
			resourcePath += "barrel_unopened_open";
		} else {
			resourcePath += "barrel_unopened";
		}
	}
	PolyLootr.LOGGER.info("PolyLootr resource path: " + resourcePath);
	PolyLootr.LOGGER.info("Block facing: x: " + x + ", y: " + y);
	this.polymerBlockState = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK,PolymerBlockModel.of(ResourceLocation.fromNamespaceAndPath("lootr",resourcePath), x, y));
	PolyLootr.LOGGER.info("Blockstates left: " + PolymerBlockResourceUtils.getBlocksLeft(BlockModelType.FULL_BLOCK));
	}

	private BlockState makeBlockState(String barrelVarient, int x, int y) {
		
	}
}
