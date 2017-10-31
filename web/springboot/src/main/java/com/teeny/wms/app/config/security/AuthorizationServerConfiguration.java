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
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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
//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT_ID = "wms";

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

    /**
     * authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象。
     * <p>
     * userDetailsService：如果啊，你设置了这个属性的话，那说明你有一个自己的 UserDetailsService 接口的实现，或者你可以把这个东西设置到全局域上面去
     * （例如 GlobalAuthenticationManagerConfigurer 这个配置对象），当你设置了这个之后，那么 "refresh_token" 即刷新令牌授权类型模式的流程中就会包含一个检查，
     * 用来确保这个账号是否仍然有效，假如说你禁用了这个账户的话。
     * <p>
     * authorizationCodeServices：这个属性是用来设置授权码服务的（即 AuthorizationCodeServices 的实例对象），主要用于 "authorization_code" 授权码类型模式。
     * <p>
     * implicitGrantService：这个属性用于设置隐式授权模式，用来管理隐式授权模式的状态。
     * <p>
     * tokenGranter：这个属性就很牛B了，当你设置了这个东西（即 TokenGranter 接口实现），那么授权将会交由你来完全掌控，并且会忽略掉上面的这几个属性，
     * 这个属性一般是用作拓展用途的，即标准的四种授权模式已经满足不了你的需求的时候，才会考虑使用这个。
     *
     * @param endpoints 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @throws Exception e
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(mAuthenticationManager);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param security 用来配置令牌端点(Token Endpoint)的安全约束.
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients();
    }

    /**
     * clientId：（必须的）用来标识客户的Id。
     * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
     * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
     * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
     * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
     *
     * @param clients 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(CLIENT_ID)
                .resourceIds(ResourceServerConfiguration.RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret("secret");
    }

}
