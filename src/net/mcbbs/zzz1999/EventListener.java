package net.mcbbs.zzz1999;

import cn.nukkit.Player;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.*;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.event.entity.ExplosionPrimeEvent;
import cn.nukkit.event.player.PlayerAnimationEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.PlayerActionPacket;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class EventListener implements Listener {
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
        if (plugin.isProtectWorld(event.getEntity().getLevel().getFolderName())){
            this.ignoreEvent(event);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onPlayerBreak(DataPacketReceiveEvent event){
        DataPacket pk = event.getPacket();
        if(pk instanceof PlayerActionPacket){
            if(((PlayerActionPacket) pk).action == PlayerActionPacket.ACTION_START_BREAK){
                if(plugin.isProtectWorld(event.getPlayer().getLevel().getFolderName())){
                    pk.clean();
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onIgnite(BlockIgniteEvent event){
        if(plugin.isProtectWorld(event.getSource().getLevel().getFolderName())){
            this.ignoreEvent(event);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onWTF(ItemFrameDropItemEvent event){
        if (plugin.isProtectWorld(event.getPlayer().getLevel().getFolderName())){
            this.ignoreEvent(event);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onLeavesDecay(LeavesDecayEvent event){
        if(plugin.isProtectWorld(event.getBlock().getLevel().getFolderName())){
            ignoreEvent(event);
        }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onBlockBurn(BlockBurnEvent event){
        if(plugin.isProtectWorld(event.getBlock().getLevel().getFolderName())){
            ignoreEvent(event);
        }
    }





    private void ignoreEvent(Cancellable event , Player player){
        event.setCancelled();
        player.sendPopup(TextFormat.RED+"[WorldProtection] 这个世界被保护了");
    }
    private void ignoreEvent(Cancellable event){
        event.setCancelled();
    }


}