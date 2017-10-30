package com.teeny.wms.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see AuthorizationServerConfiguration
 * @since 2017/10/25
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 30 * 24 * 60;  //a month
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 24 * 60;  //a month

    private DataSource mDataSource;
    private AuthenticationManager mAuthenticationManager;

    @Autowired
    public AuthorizationServerConfiguration(@Qualifier("dataSource") DataSource dataSource, @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.mDataSource = dataSource;
        this.mAuthenticationManager = authenticationManager;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(mDataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(mAuthenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("wms")
                .scopes("read", "write")
                .authorities("PLATFORM")
                .authorizedGrantTypes("password", "refresh_token")
                .secret("secret")
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

}
