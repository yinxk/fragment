package fragment.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import picocli.shell.jline3.PicocliCommands;

@Configuration
public class PicocliConfig {

    @Primary
    @Bean
    public PicocliCommands.PicocliCommandsFactory picocliCommandsFactory() {
        return new PicocliCommands.PicocliCommandsFactory();
    }
}
