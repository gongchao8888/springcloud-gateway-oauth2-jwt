package com.gc.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * 
 * @author leader zhuo
 *
 * 转发时候执行：比如我的项目中通过gateway转发到project
 * 当前全局filter的目的是转发时候， token可以传递过去对应的微服务 参考行        
 *
 *
 */
@Component
public class MyGateFilter implements GlobalFilter,Ordered{
	private ServerOAuth2AuthorizedClientRepository authorizedClientRepository;

	public MyGateFilter(ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
		this.authorizedClientRepository = authorizedClientRepository;
	}
	@Override
	public int getOrder() {
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return exchange.getPrincipal()
				.cast(OAuth2AuthenticationToken.class)
				.flatMap(authentication -> authorizedClient(exchange, authentication))
				.map(OAuth2AuthorizedClient::getAccessToken)
				.map(token -> withBearerAuth(exchange, token))
				.defaultIfEmpty(exchange)
				.flatMap(chain::filter);
	}
	private Mono<OAuth2AuthorizedClient> authorizedClient(ServerWebExchange exchange, OAuth2AuthenticationToken oauth2Authentication) {
		return this.authorizedClientRepository.loadAuthorizedClient(
				oauth2Authentication.getAuthorizedClientRegistrationId(), oauth2Authentication, exchange);
	}

	private ServerWebExchange withBearerAuth(ServerWebExchange exchange, OAuth2AccessToken accessToken) {
		return exchange.mutate()
				.request(r -> r.headers(headers -> headers.setBearerAuth(accessToken.getTokenValue())))
				.build();
	}
}
