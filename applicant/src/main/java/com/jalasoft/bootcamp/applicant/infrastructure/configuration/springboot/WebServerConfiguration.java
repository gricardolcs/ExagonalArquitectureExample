package com.jalasoft.bootcamp.applicant.infrastructure.configuration.springboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerConfiguration {

    public static final String RELAXED_QUERY_CHARS_VALUE = "\\|{}[]";
    public static final String RELAXED_QUERY_CHARS_NAME = "relaxedQueryChars";

    @Bean
    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        webServerFactory.addConnectorCustomizers(
                connector -> connector.setProperty(RELAXED_QUERY_CHARS_NAME, RELAXED_QUERY_CHARS_VALUE));
        return webServerFactory;
    }
}
