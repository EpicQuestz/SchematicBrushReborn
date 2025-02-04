/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2021 EldoriaRPG Team and Contributor
 */

package de.eldoria.schematicbrush.rendering;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import org.bukkit.World;

public class FakeWorldImpl extends BukkitWorld implements FakeWorld {
    private final ChangesImpl.Builder changes = ChangesImpl.builder();

    /**
     * Construct the object.
     *
     * @param world the world
     */
    public FakeWorldImpl(World world) {
        super(world);
    }

    @Override
    public <B extends BlockStateHolder<B>> boolean setBlock(BlockVector3 position, B block, boolean notifyAndLight) {
        var data = BukkitAdapter.adapt(block.toBaseBlock());
        var location = BukkitAdapter.adapt(getWorld(), position);
        changes.add(location, location.getBlock().getBlockData(), data);
        return true;
    }

    @Override
    public Changes changes() {
        return changes.build();
    }


}
