package fragment.redis.config;

public class MockConfig {
    public static com.github.jsonzou.jmockdata.MockConfig getMockConfig() {
        com.github.jsonzou.jmockdata.MockConfig mockConfig = new com.github.jsonzou.jmockdata.MockConfig();
        mockConfig.globalDataConfig().sizeRange(10, 100);
        return mockConfig;
    }
}
