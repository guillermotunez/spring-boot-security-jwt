package com.challenge.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NotTestProfileCondition implements Condition {
	
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    	return context.getEnvironment().acceptsProfiles(Profiles.of("!test"));
    }

}
