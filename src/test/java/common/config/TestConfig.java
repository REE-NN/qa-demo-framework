package common.config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",              // предпочтение системным свойствам (-Dkey=value)
        "classpath:test.properties"       // дефолты из ресурсов
})
public interface TestConfig extends Config {
    @Key("base.url")
    @DefaultValue("https://restful-booker.herokuapp.com")
    String baseUrl();

    @Key("http.timeout.ms")
    @DefaultValue("5000")
    int httpTimeoutMs();
}
