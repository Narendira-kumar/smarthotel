package com.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingGatewayFilterFactory extends 
AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config>{

	public LoggingGatewayFilterFactory() {
        super(Config.class);
    }
	
	@Override
    public GatewayFilter apply(Config config) {
		return null;
        // ...
    }

    public static class Config {
        // ...
    }
}
