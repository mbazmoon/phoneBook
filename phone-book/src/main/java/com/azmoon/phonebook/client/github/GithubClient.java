package com.azmoon.phonebook.client.github;

import com.azmoon.phonebook.exception.PhoneBookClientException;
import com.azmoon.phonebook.response.GithubResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "github", url = "https://api.github.com/")
public interface GithubClient {
	@GetMapping(value = "/users/{username}/repos")
	List<GithubResponse> getRepository(@PathVariable String username)throws PhoneBookClientException;
}
