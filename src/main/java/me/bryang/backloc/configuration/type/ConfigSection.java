package me.bryang.backloc.configuration.type;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@ConfigSerializable
public class ConfigSection {

    public Plugin plugin = new Plugin();

    @ConfigSerializable
    public static class Plugin{

        @Comment("Set the max death points that can have every user.")
        public int maxBacks = 5;

    }
}
