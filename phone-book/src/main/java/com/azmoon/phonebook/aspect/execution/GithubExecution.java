package com.azmoon.phonebook.aspect.execution;

import com.azmoon.phonebook.model.PhoneBook;
import com.azmoon.phonebook.model.Status;
import com.azmoon.phonebook.service.github.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
@RequiredArgsConstructor
public class GithubExecution {

	private final GithubService githubService;


	@Around("@annotation(com.azmoon.phonebook.aspect.annotation.Github)")
	public PhoneBook around(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			logger.info("going to get repository in async mode");
			Object response = joinPoint.proceed();
			PhoneBook phoneBook = (PhoneBook) response;
			logger.info("response ={}",response);
			if (phoneBook.getStatus() == Status.PENDING)
				githubService.sendToGithubQueue((PhoneBook) response);
			return phoneBook;
		} catch (Exception ex) {
			throw ex;
		}
	}

}