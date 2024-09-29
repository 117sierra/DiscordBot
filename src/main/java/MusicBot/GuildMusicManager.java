package MusicBot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;


public class GuildMusicManager {

    private TrackScheduler trackScheduler;
    private AudioForwarder forwarder;

    public GuildMusicManager(AudioPlayerManager audioManager) {
        AudioPlayer player = audioManager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        forwarder=new AudioForwarder(player);
    }
    public TrackScheduler getTrackScheduler() {
        return trackScheduler;
    }
    public AudioForwarder getForwarder() {
        return forwarder;
    }
}
