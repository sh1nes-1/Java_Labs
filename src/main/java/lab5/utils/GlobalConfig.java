package lab5.utils;

import java.io.IOException;
import java.util.Properties;

public class GlobalConfig {
    private static final String CONFIG_PATH = "/";
    private static final String CONFIG_NAME = "config.properties";
    private final Properties globalConfig;

    {
        globalConfig = new Properties();
    }

    /**
     * Loads default config.properties from resources
     *
     * @throws IOException if can't read config
     */
    public void loadGlobalConfig() throws IOException {
        loadGlobalConfig(null);
    }

    /**
     * Loads config with given name
     *
     * @param name filename of config
     * @throws IOException if can't read config
     */
    public void loadGlobalConfig(String name) throws IOException {
        if (name != null && !name.trim().isEmpty()) {
            globalConfig.load(getClass().getResourceAsStream(CONFIG_PATH + name));
        } else {
            globalConfig.load(getClass().getResourceAsStream(CONFIG_PATH + CONFIG_NAME));
        }
    }

    /**
     * Gets value from loaded config
     *
     * @param property key (name) of needed property
     * @return value of needed property
     */
    public String getProperty(String property) {
        return globalConfig.getProperty(property);
    }
}
