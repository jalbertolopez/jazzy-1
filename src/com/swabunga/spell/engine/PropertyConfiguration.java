package com.swabunga.spell.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyConfiguration extends Configuration {

	public Properties prop;
	public URL filename;

	public PropertyConfiguration() {
		prop = new Properties();
		try {
			filename = getClass().getClassLoader().getResource("com/swabunga/spell/engine/configuration.properties");
			InputStream in = filename.openStream();
			prop.load(in);
		} catch (Exception e) {
			System.out.println("No se pudieron cargar las configuraciones :\n" + e);
		}
	}

	public boolean getBoolean(String key) {
		return Boolean.getBoolean(prop.getProperty(key));
	}

	public int getInteger(String key) {
		return Integer.parseInt(prop.getProperty(key));
	}

	public void setBoolean(String key, boolean value) {
		String string = null;
		if (value)
			string = "true";
		else
			string = "false";

		prop.setProperty(key, string);
		save();
	}

	public void setInteger(String key, int value) {
		prop.setProperty(key, Integer.toString(value));
		save();
	}

	public void save() {
		try {
			File file = new File(filename.getFile());
			FileOutputStream fout = new FileOutputStream(file);
			prop.store(fout, "HEADER");
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

}
