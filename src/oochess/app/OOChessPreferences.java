package oochess.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * This class is responsible for loading the 
 * configuration / properties file
 *
 */
public class OOChessPreferences {
	
	private static OOChessPreferences instance = null;
	
	private OOChessPreferences() {
		
	}
	
	public static OOChessPreferences getInstance() {
		if(instance == null)
			instance = new OOChessPreferences();
		return instance;
	}
	
	
	/**
	 * loads a given property
	 * 
	 * @param key - the property key
	 * @return the property
	 */
	public String loadProperty(String key) {
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/preferences.properties"));
			return p.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
