package lab5.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalConfig
{
    private static final String CONFIG_NAME = "config.properties";
    private static final Properties GLOBAL_CONFIG = new Properties();

    /**
     * Loads default config
     * @throws IOException if can't read config
     */
    public static void initGlobalConfig() throws IOException {
        initGlobalConfig(null);
    }

    /**
     * Loads config with given name
     * @param name filename of config
     * @throws IOException if can't read config
     */
    public static void initGlobalConfig(String name) throws IOException {
        if (name != null && !name.trim().isEmpty()) {
            GLOBAL_CONFIG.load(new FileReader(name));
        } else {
            GLOBAL_CONFIG.load(new FileReader(CONFIG_NAME));
        }
    }

    /**
     * Gets value from loaded config
     * @param property key (name) of needed property
     * @return value of needed property
     */
    public static String getProperty(String property) {
        return GLOBAL_CONFIG.getProperty(property);
    }
}
