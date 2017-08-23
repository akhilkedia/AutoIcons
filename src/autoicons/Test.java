package autoicons;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Test {

	public static void main (String[] args) throws Exception {
		Properties prop = new Properties();
		OutputStream output = null;


			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("dbuser", "mkyong");
			prop.setProperty("dbpassword", "password");

			// save properties to project root folder
			prop.store(output, null);
			return;
	}

}
