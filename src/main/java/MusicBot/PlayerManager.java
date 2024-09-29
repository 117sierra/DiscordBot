package MusicBot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private HashMap<Long, GuildMusicManager> musicManagers = new HashMap<>();
    private AudioPlayerManager audioPlayerManager = new DefaultAudioPlayerManager();


    private PlayerManager() {
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
    }

    public static PlayerManager get(){
        if(INSTANCE == null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    public GuildMusicManager getMusicManager(Guild guild){
        return musicManagers.computeIfAbsent(guild.getIdLong(),(guildId)->{
            GuildMusicManager musicManager = new GuildMusicManager(audioPlayerManager);
            guild.getAudioManager().setSendingHandler(musicManager.getForwarder());
            return musicManager;
        });
    }

    public void play(Guild guild, String url){
        GuildMusicManager musicManager = getMusicManager(guild);
        audioPlayerManager.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.getTrackScheduler().queue(audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException e) {

            }
        });
    }

}

