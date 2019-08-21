package com.nroad.amcc.support.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${public.resources:/resource/**}")
	private String publicResources;
	
	@Value("${resource.id:oauth2/admin}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenServices(defaultTokenServices())
			.resourceId(resourceId);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(publicResources.split(",")).permitAll()
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and().csrf().disable();
	}

	// ===================================================以下代码与认证服务器一致=========================================
	/**
	 * token存储,这里使用jwt方式存储
	 * 
	 * @param accessTokenConverter
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
		return tokenStore;
	}

	/**
	 * Token转换器必须与认证服务一致
	 * 
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		Resource resource = new ClassPathResource("public.txt");
		String publicKey = null;
		try {
			publicKey = IOUtils.toString(resource.getInputStream());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);
		return converter;
	}

	/**
	 * 创建一个默认的资源服务token
	 * 
	 * @return
	 */
	@Bean
	public ResourceServerTokenServices defaultTokenServices() {
//		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//		defaultTokenServices.setTokenEnhancer(accessTokenConverter());
//		defaultTokenServices.setTokenStore(tokenStore());
//		return defaultTokenServices;
		
		final CustomResourceTokenServices customResourceTokenServices=new CustomResourceTokenServices();
		customResourceTokenServices.setTokenStore(tokenStore());
		return customResourceTokenServices;
	}
	// ===================================================以上代码与认证服务器一致=========================================


	@Bean("classCategoryList")
	public List<String> classCategoryList(){
		return  Arrays.asList("理工", "文史");
	}

}
