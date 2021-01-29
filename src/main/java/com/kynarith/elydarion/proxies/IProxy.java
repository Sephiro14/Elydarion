package com.kynarith.elydarion.proxies;

import net.minecraft.world.World;

public interface IProxy
{
    void init();

    World getClientWorld();
}
