package me.lanzhi.bluestargame.Type;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.lanzhi.bluestargame.BluestarGame.plugin;

public enum CompressedCoal implements ConfigurationSerializable
{
    /**
     * 烧制过的煤炭块
     */
    FIRED_COAL_BLOCK(1),
    /**
     * 压缩煤炭
     */
    COMPRESSED_COAL(2),
    /**
     * 烧制过的压缩煤炭
     */
    FIRED_COMPRESSED_COAL(3);
    private final NBTItem item;
    private final int id;
    private final List<NamespacedKey> allowRecipe;

    CompressedCoal(int id)
    {
        ItemStack itemm=new ItemStack(Material.COAL_BLOCK);
        ItemMeta meta=itemm.getItemMeta();
        switch (id)
        {
            case 1:
            {
                meta.setDisplayName(ChatColor.GOLD+"烧制煤炭");
                allowRecipe=Collections.singletonList(new NamespacedKey(plugin,"compressed_coal"));
                itemm.setItemMeta(meta);
                item=new NBTItem(itemm);
                item.addCompound("BluestarGame").setInteger("coal",id);
                this.id=id;
                break;
            }
            case 2:
            {
                meta.setDisplayName(ChatColor.GOLD+"压缩煤炭");
                allowRecipe=Collections.singletonList(new NamespacedKey(plugin,"fired_compressed_coal"));
                itemm.setItemMeta(meta);
                item=new NBTItem(itemm);
                item.addCompound("BluestarGame").setInteger("coal",id);
                this.id=id;
                break;
            }
            case 3:
            {
                meta.setDisplayName(ChatColor.GOLD+"烧制压缩煤炭");
                allowRecipe=Collections.singletonList(new NamespacedKey(plugin,"coal_diamond"));
                itemm.setItemMeta(meta);
                item=new NBTItem(itemm);
                item.addCompound("BluestarGame").setInteger("coal",id);
                this.id=id;
                break;
            }
            default:
            {
                this.allowRecipe=null;
                this.id=0;
                this.item=null;
            }
        }
    }

    public NBTItem getNbtItem()
    {
        return item;
    }

    public ItemStack getItem()
    {
        return item.getItem();
    }

    public int getId()
    {
        return id;
    }

    public boolean canUseInRecipe(NamespacedKey key)
    {
        return allowRecipe.contains(key);
    }

    public static CompressedCoal getFromId(int id)
    {
        switch (id)
        {
            case 1:
            {
                return FIRED_COAL_BLOCK;
            }
            case 2:
            {
                return COMPRESSED_COAL;
            }
            case 3:
            {
                return FIRED_COMPRESSED_COAL;
            }
            default:
            {
                return null;
            }
        }
    }

    public static CompressedCoal getFromItemStack(@Nullable ItemStack itemStack)
    {
        return itemStack==null?null:getFromNbtItem(new NBTItem(itemStack));
    }

    @Nullable
    public static CompressedCoal getFromNbtItem(@Nullable NBTItem item)
    {
        if (item==null)
        {
            return null;
        }
        NBTCompound compound=item.getCompound("BluestarGame");
        return compound==null?null:getFromId(compound.getInteger("coal"));
    }

    @NotNull
    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> map=new HashMap<>();
        map.put("id",getId());
        return map;
    }

    public static CompressedCoal deserialize(Map<String, Object> map)
    {
        return getFromId((int) map.get("id"));
    }
}
