package net.mcbbs.zzz1999;

import cn.nukkit.Player;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.event.entity.ExplosionPrimeEvent;
import cn.nukkit.plugin.PluginBase;

public class EventListener extends PluginBase implements Listener {
    private WorldProtection plugin;

    EventListener(WorldProtection plugin){
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void BlockBreak(BlockBreakEvent event){
        if(plugin.isProtectWorld(event.getPlayer().getLevel().getFolderName()) && !plugin.isAdmin(event.getPlayer().getName())){
            this.ignoreEvent(event,event.getPlayer());
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void BlockPlace(BlockPlaceEvent event){
        if(plugin.isProtectWorld(event.getPlayer().getLevel().getFolderName()) && !plugin.isAdmin(event.getPlayer().getName())){
            this.ignoreEvent(event,event.getPlayer());
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void Boom(EntityExplosionPrimeEvent event){
        if(plugin.isProtectWorld(event.getEntity().getLevel().getFolderName())){
            this.ignoreEvent(event);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void Boom2(ExplosionPrimeEvent event){
        if(plugin.isProtectWorld(event.getEntity().getLevel().getFolderName())){
            this.ignoreEvent(event);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void HitOther(EntityDamageEvent event) {
        if (plugin.isProtectWorld(event.getEntity().getLevel().getFolderName()) && !plugin.isAdmin(event.getEntity().getName())){
            this.ignoreEvent(event,(Player)event.getEntity());
        }
    }



    private void ignoreEvent(Cancellable event , Player player){
        event.setCancelled();
        player.sendPopup("[WorldProtection] 这个世界被保护了");
    }
    private void ignoreEvent(Cancellable event){
        event.setCancelled();
    }


}