package com.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	 
	 
	@Bean

	//public RouteLocator gatewayRouter(RouteLocatorBuilder builder,TokenRelayGatewayFilterFactory filterFactory)
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
	{
		return builder.routes()
			//	.route("smarthotelservice", p-> p.path("http://localhost:8085/smarthotelservice/SmartHotelService/resendOTP")
			//			 .filters(f -> f.filter(filterFactory.apply()))
			//			.uri("lb:http://localhost:8085/smarthotelservice/SmartHotelService/sendOTP"))
				// .route(p -> p.path("/smarthotelservice/SmartHotelService/resendOTP"). 
				//          filters(f -> f.setPath("/smarthotelservice/SmartHotelService/resendOTP")).uri("lb://smarthotelservice/SmartHotelService/sendOTP"))
				.build();
	}
}
