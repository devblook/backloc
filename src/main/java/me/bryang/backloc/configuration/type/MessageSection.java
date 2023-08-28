package me.bryang.backloc.configuration.type;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.Arrays;
import java.util.List;

@ConfigSerializable
public class MessageSection {

    public Plugin plugin = new Plugin();

    public Error error = new Error();

    @ConfigSerializable
    public static class Error {

        @Comment("When a admin try to use the command in console.")
        public String noConsole = "Err - You can't use the command in console.";

        @Comment("When a player put a admin argument")
        public String noPermission = "<red>[Error] <dark_grey>| <white>You don't have permission to do this.";

        @Comment("When a player doesn't put a number")
        public String unknownNumber = "<red>[Error] <dark_grey>| <white>Unknown number <red>[<argument>]";

        @Comment("When the id doesn't exists")
        public String unknownId = "<red>[Error] <dark_grey>| <white>Unknown id. Check /backlist. Id: <id>";

        @Comment("When a player hasn't died.")
        public String noBacks = "<red>[Error] <dark_grey>| <white>You don't have backs";

    }

    @ConfigSerializable
    public static class Plugin{

        @Comment("""
        Message that will show to the player when uses the backup command
        Variables:
        - <back_format> If the line has this variable, will be the message that will iterated from all back list.
        - <back_id> Id from the back.
        - <back_coords> Coords from the back.
        """)
        public List<String> backListFormat = Arrays.asList(
                "<gold>[Back] <dark_grey>| <grey>List of backs <gold>[<back_length>]",
                "<back_format><dark_grey>- <white><back_id> <dark_grey>- <white><back_coords> <grey>: <green><hover:show_text:'Click here!'><click:run_command:'/back <back_id>'>[Teleport]</click></hover>");

        @Comment("Format in <back_coords> variable in backListFormat message path.")
        public String backCoordFormat = "<x> <y> <z>";

        @Comment("When a player use a backup")
        public String teleportMessage = "<gold>[Back] <dark_grey>| <white>Entering to backup";

        @Comment("When a player died")
        public String deathMessage = "<gold>[Back] <white>You died. Click in the green message to teleport. <green><hover:show_text:'Click here!'><click:run_command:'/back'>[Teleport]</click></hover>";

    }
}
