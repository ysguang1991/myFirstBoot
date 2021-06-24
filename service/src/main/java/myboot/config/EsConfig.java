package myboot.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {
    @Value("${es-host:http://127.0.0.1}")
    private String host;
    @Value("${es-port:9200}")
    private int port;

    @Bean
    public JestClient jestClient() {
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig.Builder builder = new HttpClientConfig.Builder(host + ":" + port);
        jestClientFactory.setHttpClientConfig(builder.build());
        return jestClientFactory.getObject();
    }
}
