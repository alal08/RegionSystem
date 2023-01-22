package io.github.alal08.regionsystem;

import io.github.alal08.regionsystem.util.YamlManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class Region {

    private final UUID regionUUID = UUID.randomUUID();
    private World world;
    private double minX;
    private double minY;
    private double minZ;
    private double maxX;
    private double maxY;
    private double maxZ;
    private static ArrayList<Region> loadedRegions = new ArrayList<>();

    private static final YamlManager yamlRegion = new YamlManager("data", "regions");

    public Region(World world, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.world = world;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        save();
    }

    public Region(@NotNull Location location1, @NotNull Location location2) {
        World world1 = location1.getWorld();
        World world2 = location2.getWorld();
        if (world1 != world2) return;
        world = world1;
        double x1 = location1.getX();
        double y1 = location1.getY();
        double z1 = location1.getZ();
        double x2 = location2.getX();
        double y2 = location2.getY();
        double z2 = location2.getZ();
        minX = Math.min(x1, x2);
        minY = Math.min(y1, y2);
        minZ = Math.min(z1, z2);
        maxX = Math.max(x1, x2);
        maxY = Math.max(y1, y2);
        maxZ = Math.max(z1, z2);
        save();
    }

    private void save() {
        loadedRegions.add(this);
        yamlRegion.put(regionUUID.toString(), this);
    }

    public static void load() {
        for (String key : yamlRegion.getKeys()) {
            loadedRegions.add((Region) yamlRegion.get(key));
        }
    }

    public UUID getUniqueId() {
        return regionUUID;
    }

    public World getWorld() {
        return world;
    }

    public Location getMinLocation() {
        return new Location(world, minX, minY, minZ);
    }

    public Location getMaxLocation() {
        return new Location(world, maxX, maxY, maxZ);
    }

    public static ArrayList<Region> getLoadedRegion() {
        return loadedRegions;
    }

    public boolean isIn(@NotNull Location location) {
        if (world != location.getWorld()) return false;
        Vector minVector = new Vector(minX, minY, minZ);
        Vector maxVector = new Vector(maxX, maxY, maxZ);
        Vector vector = location.toVector();
        return vector.isInAABB(minVector, maxVector);
    }
}
