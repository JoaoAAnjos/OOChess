package oochess.app.discordintegration;

import net.padroesfactory.Discord4JMock;

/**
 * The adapter for the Discord4J Discord API
 *
 */
public class Discord4JAdapter implements DiscordSender {
	
	private String token;
	
	public Discord4JAdapter(String token) {
		this.token = token;
	}

	@Override
	public void sendMessage(String username, String message) {
		new Discord4JMock(token).getChannel(username).sendMessage(message);
	}

}
