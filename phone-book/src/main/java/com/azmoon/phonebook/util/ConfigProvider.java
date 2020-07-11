package com.azmoon.phonebook.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConfigProvider {

	private final Environment environment;


	public String getGithubExchange() {
		return environment.getRequiredProperty("github.exchange");
	}

	public String getGithubRoutingKey() {
		return environment.getRequiredProperty("github.routing.key");
	}

}
