package chess.config;

import chess.utils.StringToPieceTypeConverter;
import chess.utils.StringToTeamConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new StringToTeamConverter());
        registry.addConverter(new StringToPieceTypeConverter());
    }

}
