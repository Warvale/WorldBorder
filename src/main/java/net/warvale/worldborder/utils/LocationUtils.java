package net.warvale.worldborder.utils;

import org.bukkit.Location;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.worldborder.WorldBorder;

public class LocationUtils {

    public static void safeTeleport(final Player player, final Location location) {
        if (player.getVehicle() != null && player.getVehicle() instanceof Horse) {
            final Horse vehicle = ((Horse) player.getVehicle());
            vehicle.eject();

            // TP the horse
            new BukkitRunnable() {
                public void run() {
                    // Add 1 to location to be safe
                    vehicle.teleport(location.add(0, 1, 0));
                }
            }.runTaskLater(WorldBorder.plugin, 1L);

            // Reattach the player to the horse
            new BukkitRunnable() {
                public void run() {
                    vehicle.setPassenger(player);
                }
            }.runTaskLater(WorldBorder.plugin, 2L);
        } else {
            player.setFallDistance(0);
            player.teleport(location);
        }
    }

}
