package cc.unilock.polylootr.mixin.blocks;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.LootrBarrelBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import cc.unilock.polylootr.PolyLootr;
import xyz.nucleoid.packettweaker.PacketContext;

@Mixin(LootrBarrelBlock.class)
public class LootrBarrelBlockMixin implements PolymerTexturedBlock {
	private BlockState polymerBlockState;
	// Behold: A nightmare
	final private BlockState baseStateBarrelUnopenedNorthOpenedFace = makeBlockState("barrel_unopened_open", 90, 0);
	final private BlockState baseStateBarrelUnopenedEastOpenedFace = makeBlockState("barrel_unopened_open", 90, 90);
	final private BlockState baseStateBarrelUnopenedSouthOpenedFace = makeBlockState("barrel_unopened_open", 90, 180);
	final private BlockState baseStateBarrelUnopenedWestOpenedFace = makeBlockState("barrel_unopened_open", 90, 270);
	final private BlockState baseStateBarrelUnopenedUpOpenedFace = makeBlockState("barrel_unopened_open", 90, 0);
	final private BlockState baseStateBarrelUnopenedDownOpenedFace = makeBlockState("barrel_unopened_open",180, 0);


	final private BlockState baseStateBarrelOpenedNorthOpenedFace = makeBlockState("opened_barrel_open", 90, 0);
	final private BlockState baseStateBarrelOpenedEastOpenedFace = makeBlockState("opened_barrel_open", 90, 90);
	final private BlockState baseStateBarrelOpenedSouthOpenedFace = makeBlockState("opened_barrel_open", 90, 90);
	final private BlockState baseStateBarrelOpenedWestOpenedFace = makeBlockState("opened_barrel_open", 90, 270);
	final private BlockState baseStateBarrelOpenedUpOpenedFace = makeBlockState("opened_barrel_open", 0, 0);
	final private BlockState baseStateBarrelOpenedDownOpenedFace = makeBlockState("opened_barrel_open",180, 0);

	final private BlockState baseStateBarrelUnopenedNorthClosedFace = makeBlockState("barrel_unopened", 90, 0);
	final private BlockState baseStateBarrelUnopenedEastClosedFace = makeBlockState("barrel_unopened", 90, 90);
	final private BlockState baseStateBarrelUnopenedSouthClosedFace = makeBlockState("barrel_unopened", 90, 180);
	final private BlockState baseStateBarrelUnopenedWestClosedFace = makeBlockState("barrel_unopened", 90, 270);
	final private BlockState baseStateBarrelUnopenedUpClosedFace = makeBlockState("barrel_unopened", 0, 0);
	final private BlockState baseStateBarrelUnopenedDownClosedFace = makeBlockState("barrel_unopened",180, 0);

	final private BlockState baseStateBarrelOpenedNorthClosedFace = makeBlockState("opened_barrel", 90, 0);
	final private BlockState baseStateBarrelOpenedEastClosedFace = makeBlockState("opened_barrel", 90, 90);
	final private BlockState baseStateBarrelOpenedSouthClosedFace = makeBlockState("opened_barrel", 90, 180);
	final private BlockState baseStateBarrelOpenedWestClosedFace = makeBlockState("opened_barrel", 90, 270);
	final private BlockState baseStateBarrelOpenedUpClosedFace = makeBlockState("opened_barrel", 0, 0);
	final private BlockState baseStateBarrelOpenedDownClosedFace = makeBlockState("opened_barrel",180, 0);
	

	@Inject(method = "<init>", at = @At("RETURN"), remap = false)
	private void onConstructor(CallbackInfo ci) {
		this.polymerBlockState = baseStateBarrelUnopenedUpClosedFace;
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
		int x = 0, y = 0;
		boolean isOpened = false;
		if (blockEntity instanceof ILootrBlockEntity) {
			switch (pState.getValue(BarrelBlock.FACING)) {
				case DOWN:
				PolyLootr.LOGGER.info("Block facing: DOWN");
					if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelOpenedDownOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelOpenedDownClosedFace;
							isOpened = false;
						}
					} else {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelUnopenedDownOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelUnopenedDownClosedFace;
							isOpened = false;
						}
					}
					break;
				case EAST:
				PolyLootr.LOGGER.info("Block facing: EAST");
					if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelOpenedEastOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelOpenedEastClosedFace;
							isOpened = false;
						}
					} else {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelUnopenedEastOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelUnopenedEastClosedFace;
							isOpened = false;
						}
					}
					break;
				case NORTH:
				PolyLootr.LOGGER.info("Block facing: NORTH");
				if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
					if (pState.getValue(BarrelBlock.OPEN)) {
						this.polymerBlockState = baseStateBarrelOpenedNorthOpenedFace;
						isOpened = true;
					} else {
						this.polymerBlockState = baseStateBarrelOpenedNorthClosedFace;
						isOpened = false;
					}
				} else {
					if (pState.getValue(BarrelBlock.OPEN)) {
						this.polymerBlockState = baseStateBarrelUnopenedNorthOpenedFace;
						isOpened = true;
					} else {
						this.polymerBlockState = baseStateBarrelUnopenedNorthClosedFace;
						isOpened = false;
					}
				}
					break;
				case SOUTH:
				PolyLootr.LOGGER.info("Block facing: SOUTH");
					if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelOpenedSouthOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelOpenedSouthClosedFace;
							isOpened = false;
						}
					} else {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelUnopenedSouthOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelUnopenedSouthClosedFace;
							isOpened = false;
						}
					}
					break;
				case UP:
				PolyLootr.LOGGER.info("Block facing: UP");
					if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelOpenedUpOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelOpenedUpClosedFace;
							isOpened = false;
						}
					} else {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelUnopenedUpOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelUnopenedUpClosedFace;
							isOpened = false;
						}
					}
					break;
				case WEST:
				PolyLootr.LOGGER.info("Block facing: WEST");
					if ((((ILootrBlockEntity) blockEntity).hasBeenOpened())) {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelOpenedWestOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelOpenedWestClosedFace;
							isOpened = false;
						}
					} else {
						if (pState.getValue(BarrelBlock.OPEN)) {
							this.polymerBlockState = baseStateBarrelUnopenedWestOpenedFace;
							isOpened = true;
						} else {
							this.polymerBlockState = baseStateBarrelUnopenedWestClosedFace;
							
							isOpened = false;
						}
					}
					break;
				default:
					break;
			}
		}

		PolyLootr.LOGGER.info("Is opened? " + (isOpened? "true": "false"));
		// this.polymerBlockState =
	}

	private BlockState makeBlockState(String barrelVarient, int x, int y) {
		String resourcePath = "block/lootr_" + barrelVarient;
		PolyLootr.LOGGER.info("PolyLootr resource path: " + resourcePath);
		PolyLootr.LOGGER
				.info("Blockstates left: " + PolymerBlockResourceUtils.getBlocksLeft(BlockModelType.FULL_BLOCK));
		return PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK,
				PolymerBlockModel.of(ResourceLocation.fromNamespaceAndPath("lootr", resourcePath), x, y));
	}
}
