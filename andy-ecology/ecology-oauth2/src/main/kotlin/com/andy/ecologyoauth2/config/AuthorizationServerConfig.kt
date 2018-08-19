package com.andy.ecologyoauth2.config

import com.andy.ecologyoauth2.service.DatabaseTokenStoreService
import com.andy.ecologyoauth2.service.OAuth2DatabaseClientDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

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
    private lateinit var tokenStoreService: DatabaseTokenStoreService

    @Autowired
    private lateinit var oAuth2DatabaseClientDetailsService: OAuth2DatabaseClientDetailsService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager


    @Bean
    fun tokenStore(): ApprovalStore {
        val tokenApprovalStore = TokenApprovalStore()
        tokenApprovalStore.setTokenStore(tokenStoreService)
        return tokenApprovalStore
    }



    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.withClientDetails(oAuth2DatabaseClientDetailsService)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStoreService)
    }
}