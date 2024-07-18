package com.sofrecom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
@RefreshScope
public class RegistryApplication {

	private static Logger logger = LoggerFactory.getLogger(RegistryApplication.class);

	public RegistryApplication() {
		super();
	}

	public static ConfigurableApplicationContext startMicroService(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(RegistryApplication.class, args);
		String propertyValue = ctx.getEnvironment().getProperty("info.app.name");
        System.out.println("Property Value: " + propertyValue);
		ctx.addApplicationListener((ContextClosedEvent arg0) -> ctx.close());
		return ctx;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = RegistryApplication.startMicroService(args);
		if (ctx.isActive() && logger.isInfoEnabled()) {
			
			logger.info("Micro service Cloud [SIRHUS-Registry] started correctly.");
			
		}
	}
}
