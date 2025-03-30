package cc.unilock.polylootr.items;

import java.util.Set;

import cc.unilock.polylootr.PolyLootr;
import eu.pb4.factorytools.api.item.FactoryBlockItem;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;

public class PolyLootrBlockItem extends FactoryBlockItem {
/*
    @SuppressWarnings("unchecked")
    public <T extends Block & PolymerBlock> PolyLootrBlockItem(Block block, Item.Properties settings) {
        super((T) block, settings);
        PolyLootr.LOGGER.info("A PolyLootrBlockItem has been created :3");
    }
 */
    @SuppressWarnings("unchecked")
    public <T extends Block & PolymerBlock> PolyLootrBlockItem(Block block, Properties settings, Item clientItem) {
        super((T) block, fixResourceLocation(settings), clientItem);
        PolyLootr.LOGGER.info("A PolyLootrBlockItem has been created :3");
    }

    private static Properties fixResourceLocation(Properties settings) {
        // We need to fix the model paths, because they're messed up for some reason...
        PolyLootr.LOGGER.info("Current resource location: " + settings.effectiveModel().getNamespace() + ":" + settings.effectiveModel().getPath());

        String newResourceString = "block/" + settings.effectiveModel().getPath();
        ResourceLocation replacementLocation = ResourceLocation.fromNamespaceAndPath("lootr", newResourceString);
        //Properties replacementSettings = new Item.Properties().effectiveModel(replacementLocation);
        return settings;
    }
}
