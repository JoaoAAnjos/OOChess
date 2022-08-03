package oochess.app.discordintegration;

import com.chavetasfechadas.JDAMockBuilder;

/**
 * The adapter for the JDA Discord API
 *
 */
public class JDAAdapter implements DiscordSender {
	
	private String token;
	
	public JDAAdapter(String token) {
		this.token = token;
	}
	
	@Override
	public void sendMessage(String username, String message) {
		new JDAMockBuilder().createDefault(token).build().sendMessage(username, message);
	}

}
