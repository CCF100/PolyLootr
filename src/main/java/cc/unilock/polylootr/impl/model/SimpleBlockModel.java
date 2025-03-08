package cc.unilock.polylootr.impl.model;

import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.world.level.block.state.BlockState;
import noobanidus.mods.lootr.fabric.init.ModItems;

import org.joml.Vector3f;

import cc.unilock.polylootr.PolyLootr;

public class SimpleBlockModel extends ElementHolder {
    private final ItemDisplayElement main;

    public SimpleBlockModel(BlockState state) {
        PolyLootr.LOGGER.info("Created a block model with the name: " + state.getBlock().asItem().getName().getString());
        PolyLootr.LOGGER.info("Lootr item class type: " + ModItems.BARREL.getClass().toString());
        this.main = ItemDisplayElementUtil.createSimple(state.getBlock().asItem());
        this.main.setDisplaySize(1, 1);
        this.addElement(this.main);
    }
}