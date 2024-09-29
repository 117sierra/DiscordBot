package MusicBot;

import MusicBot.commands.*;
import MusicBot.commands.music.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

        public static void main(String[] args) throws LoginException {
            JDABuilder jda = JDABuilder.createDefault("MTI4OTkxNDI5NjkxODM0Mzc5MQ.GA0EOX.5Kz47q0WIxVP8Oh-33iGNZ527U4VNYlcUikh5w");
            CommandManager manager = new CommandManager();
            manager.add(new Sum());
            manager.add(new Embed());
            manager.add(new Buttons());
            manager.add(new Modals());
            manager.add(new Ban());
            manager.add(new Mute());
            manager.add(new Staff());
            manager.add(new UnStaff());
            manager.add(new Unmute());
            manager.add(new Play());
            manager.add(new Skip());
            manager.add(new Stop());
            manager.add(new NowPlaying());
            manager.add(new Queue());
            manager.add(new Repeat());
            jda.addEventListeners(manager);
            jda.build();
        }
}
