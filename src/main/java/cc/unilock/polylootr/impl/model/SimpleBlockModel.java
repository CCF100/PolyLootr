package cc.unilock.polylootr.impl.model;

import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import noobanidus.mods.lootr.fabric.init.ModItems;

import org.joml.Vector3f;

import cc.unilock.polylootr.PolyLootr;

public class SimpleBlockModel extends BlockModel {
    private final ItemDisplayElement main;

    public SimpleBlockModel(BlockPos pos, BlockState state) {
        PolyLootr.LOGGER.info("Created a block model with the name: " + state.getBlock().asItem().getName().getString());
        PolyLootr.LOGGER.info("Recieved item from class: " + state.getBlock().asItem().getClass().toString());
        this.main = ItemDisplayElementUtil.createSimple(state.getBlock().asItem());
        this.main.setScale(new Vector3f(1));
        this.main.setViewRange(0.8f);
        this.addElement(this.main);
    }
}