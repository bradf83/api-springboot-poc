package com.example.sbapi.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This is needed due to a bug that exists in Spring Data REST, Spring Data REST should do this by default for us.
 * BUG: https://jira.spring.io/browse/DATAREST-524
 * Additional Information: https://www.baeldung.com/spring-data-rest-validators
 */

@Configuration
public class ValidatorEventRegisterConfig implements InitializingBean {

    @Autowired
    ValidatingRepositoryEventListener validatingRepositoryEventListener;

    @Autowired
    private Map<String, Validator> validators;

    @Override
    public void afterPropertiesSet() throws Exception {
        // Could add additional event types if needed (https://docs.spring.io/spring-data/rest/docs/current/reference/html/#events).
        List<String> events = Collections.singletonList("beforeCreate");
        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
            events.stream()
                    .filter(p -> entry.getKey().startsWith(p))
                    .findFirst()
                    .ifPresent(
                            p -> validatingRepositoryEventListener
                                    .addValidator(p, entry.getValue()));
        }
    }
}
