package com.jalasoft.bootcamp.setting.infrastructure.events.eventPublisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;
    private static final Logger logger = LogManager.getLogger(EventPublisher.class);

    @Autowired
    public EventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(final Object event) {
        applicationEventPublisher.publishEvent(event);
        logger.info("Publish event:" + event);
    }
}
