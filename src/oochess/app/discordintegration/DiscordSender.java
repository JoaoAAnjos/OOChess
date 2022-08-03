package oochess.app.discordintegration;

/**
 * This interface sets the methods that the classes that 
 * adapt Discord api's must implement
 *
 */
public interface DiscordSender {
	
	/**
	 * send a message through the discord API
	 * 
	 * @param username - the receiver's username
	 * @param message  - the message
	 */
	public void sendMessage(String username, String message);

}
