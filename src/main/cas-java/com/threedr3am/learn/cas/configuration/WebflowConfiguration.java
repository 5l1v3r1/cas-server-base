package com.threedr3am.learn.cas.configuration;

import com.threedr3am.learn.cas.configurer.CredentialWebflowConfigurer;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.config.CasWebflowContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * @author xuanyh
 */
@Configuration("captchaWebflowConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@AutoConfigureBefore(value = CasWebflowContextConfiguration.class)
public class WebflowConfiguration {
  @Autowired
  @Qualifier("loginFlowRegistry")
  private FlowDefinitionRegistry loginFlowRegistry;
  @Autowired
  private ApplicationContext applicationContext;
  @Autowired
  private CasConfigurationProperties casProperties;
  @Autowired
  private FlowBuilderServices flowBuilderServices;

  @Bean("defaultLoginWebflowConfigurer")
  public CasWebflowConfigurer defaultLoginWebflowConfigurer() {
    CredentialWebflowConfigurer c = new CredentialWebflowConfigurer(flowBuilderServices, loginFlowRegistry, applicationContext, casProperties);
    c.initialize();
    return c;
  }
}
