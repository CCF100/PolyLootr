package cc.unilock.polylootr.items;

import cc.unilock.polylootr.PolyLootr;
import eu.pb4.factorytools.api.item.FactoryBlockItem;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PolyLootrBlockItem extends FactoryBlockItem {

    @SuppressWarnings("unchecked")
    public <T extends Block & PolymerBlock> PolyLootrBlockItem(Block block, Item.Properties settings) {
        super((T) block, settings);
        PolyLootr.LOGGER.info("A PolyLootrBlockItem has been created :3");
    }

    @SuppressWarnings("unchecked")
    public <T extends Block & PolymerBlock> PolyLootrBlockItem(Block block, Properties settings, Item clientItem) {
        super((T) block, settings, clientItem);
        PolyLootr.LOGGER.info("A PolyLootrBlockItem has been created :3");
    }
}
