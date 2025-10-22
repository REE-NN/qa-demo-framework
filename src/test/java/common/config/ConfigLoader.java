package common.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public class ConfigLoader {
    private static final TestConfig CFG = ConfigFactory.create(TestConfig.class);

    private ConfigLoader() {
    }

    public static TestConfig cfg() {
        return CFG;
    }

    public static <T extends Config> T create(Class<T> type) {
        return ConfigFactory.create(type);
    }
}
