package me.lanzhi.bluestargame.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.lanzhi.bluestargame.BluestarGamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class ChatCommand implements CommandExecutor, TabExecutor
{
    private final BluestarGamePlugin plugin;

    public ChatCommand(BluestarGamePlugin plugin)
    {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args)
    {
        if (args.length<2)
        {
            sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"错误!");
            return false;
        }
        Player player=Bukkit.getPlayer(args[0]);
        if (player==null)
        {
            sender.sendMessage(plugin.getMessageHead()+ChatColor.RED+"玩家不存在");
        }
        StringBuilder builder=new StringBuilder();
        for (int i=1;i<args.length;i++)
        {
            builder.append(args[i]+" ");
        }
        player.chat(PlaceholderAPI.setPlaceholders(player,builder.toString()));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String label,String[] args)
    {
        if (args.length>=2)
        {
            List<String> tablist=new ArrayList<>();
            tablist.add("message");
            return tablist;
        }
        return null;
    }
}
