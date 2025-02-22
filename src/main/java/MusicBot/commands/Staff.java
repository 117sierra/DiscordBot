package MusicBot.commands;

import MusicBot.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Staff implements ICommand {
    @Override
    public String getName() {
        return "staff";
    }

    @Override
    public String getDescription() {
        return "Will give you staff";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        Role role = guild.getRoleById(992827277706526771L);
        guild.addRoleToMember(member, role).queue();
        event.reply("Role Added").queue();
    }
}
