package me.lanzhi.bluestargame.api;

import me.lanzhi.bluestargame.BluestarGamePlugin;
import me.lanzhi.bluestargameapi.api.IPluginApi;

public final class BluestarGameApi implements IPluginApi
{
    private final xiaoMoBank xmBank;
    private final BluestarGamePlugin plugin;

    public BluestarGameApi(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
        this.xmBank=new xiaoMoBank(plugin);
    }

    @Override
    public xiaoMoBank getXiaoMoBank()
    {
        return xmBank;
    }
}
