package com.wyh.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder route=routeLocatorBuilder.routes();
        route.route("path_route_wyh",r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return route.build();
    }
    @Bean
    public RouteLocator customerRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder route=routeLocatorBuilder.routes();
        route.route("path_route_wyh2",r->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return route.build();
    }
}
