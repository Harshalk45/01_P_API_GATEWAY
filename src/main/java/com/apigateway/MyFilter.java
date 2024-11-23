package com.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
public class MyFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("MyPreFilter :: filter () method executed....");

        ServerHttpRequest request = exchange.getRequest();

        HttpHeaders headers = request.getHeaders();
        Set<String> keySet = headers.keySet();

//        HttpHeaders headers = request.getHeaders();
//        Set<String> keySet = headers.keySet();
           
        keySet.forEach(key -> {
            List<String> values = headers.get(key);
            logger.info(key + "::" + values);
        });
        
//        keySet.forEach(key -> {
//            List<String> values = headers.get(key);
//            logger.info(key + "::" + values);
//        });
        
        

        return chain.filter(exchange);
    }
}

