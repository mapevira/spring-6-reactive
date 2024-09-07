package guru.springframework.spring6reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Sink;
import org.zalando.logbook.json.JsonHttpLogFormatter;
import org.zalando.logbook.logstash.LogstashLogbackSink;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 07/09/2024 - 09:48
 * @since jdk 1.21
 */
@Configuration
public class LogbookConfig {

    @Bean
    public Sink logbookLogStash(){
        HttpLogFormatter formatter = new JsonHttpLogFormatter();

        return new LogstashLogbackSink(formatter);
    }
    
}
