package com.TpObjetos2.TpGrupo12.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSample {
	@Scheduled(fixedDelay=5000)
	public void runJob() {
		System.out.println("Hello!");
	}
}
