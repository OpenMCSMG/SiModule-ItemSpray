package cn.cyanbukkit.shit

import cn.cyanbukkit.shit.command.MainCommand
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.SimpleCommandMap
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.plugin.java.JavaPlugin

class ItemSpray : JavaPlugin() , Listener{

    companion object {
        lateinit var instance: ItemSpray

        fun Command.register() {
            val pluginManagerClazz = instance.server.pluginManager.javaClass
            val field = pluginManagerClazz.getDeclaredField("commandMap")
            field.isAccessible = true
            val commandMap = field.get(instance.server.pluginManager) as SimpleCommandMap
            commandMap.register(instance.name, this)
        }

    }

    override fun onEnable() {
        // Start
        instance = this
        server.pluginManager.registerEvents(this, this)
        saveDefaultConfig()
        MainCommand().register()
        logger.info("ItemSpray is enabled!")
        logger.info("ItemSpray by CyanBukkit Code")
    }

    override fun onDisable() {
        logger.info("ItemSpray is disabled!")
        logger.info("ItemSpray by CyanBukkit Code")
    }


    @EventHandler
    fun onPick(e: EntityPickupItemEvent){
        if(e.item.itemStack.type == Material.AIR) return
        val item = e.item.itemStack
        config.getKeys(false).forEach {
            val i = config.getItemStack(it)
            if(i.isSimilar(item)){
                e.isCancelled = true
            }
        }
    }

}