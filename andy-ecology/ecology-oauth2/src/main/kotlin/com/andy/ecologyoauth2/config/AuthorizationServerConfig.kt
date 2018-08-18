package com.andy.ecologyoauth2.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

/**
 * FileName: AuthorizationServerConfig
 * author:   候帅
 * data:     18/08/2018 07:38
 * Description: 授权服务配置
 * History:
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter(){

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var connectionFactory: RedisConnectionFactory

    @Bean
    fun tokenStore(): RedisTokenStore {
        return RedisTokenStore(connectionFactory)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!
                .inMemory()
                .withClient("android")
                .scopes("xx")
                .secret("android")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .and()
                .withClient("webapp")
                .scopes("xx")
                .authorizedGrantTypes("implicit")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
    }
}