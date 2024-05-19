package cn.cyanbukkit.shit.command

import cn.cyanbukkit.shit.ItemSpray
import org.bukkit.Location
import org.bukkit.block.Dropper
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class MainCommand : Command("spray") {
    override fun execute(p0: CommandSender, p1: String, p2: Array<out String>): Boolean {
        //    spray throw xxx <x> <y> <z> 直接扔了这个物品
        //    spray save  xxx  从手里保存
        //    spray
        if (p0 is ConsoleCommandSender) return true
        val p = p0 as Player
        if (p2.isEmpty()) return true
        when {
            p2[0].equals("throw", true) -> {
                try {
                    val o = ItemSpray.instance.config.getItemStack(p2[1])
                    val x = p2[2].toDoubleOrNull()?:p.location.x
                    val y = p2[3].toDoubleOrNull()?:p.location.y
                    val z = p2[4].toDoubleOrNull()?:p.location.z
                    val loc = Location(p.world, x, y, z)
                    val item = p.world.dropItem(loc, o)
                    item.velocity = Vector(0, 0, 0)
                    item.pickupDelay = -1
                    item.setGravity(false)
                } catch (e: Exception) {
                    p.sendMessage("§c§l[ItemSpray] §r§c参数错误")
                    e.printStackTrace()
                }
            }

            p2[0].equals("save", true) -> {
                val name = p2[1]
                val item = p.inventory.itemInMainHand
                ItemSpray.instance.config.set(name, item)
                ItemSpray.instance.saveConfig()
                ItemSpray.instance.reloadConfig()
                p.sendMessage("§a§l[ItemSpray] §r§a保存成功")
            }

        }


        return true
    }
}