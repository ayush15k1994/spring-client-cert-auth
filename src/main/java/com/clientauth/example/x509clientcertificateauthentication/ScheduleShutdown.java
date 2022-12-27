package com.clientauth.example.x509clientcertificateauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleShutdown {
    private final int MILLISECONDS_IN_MINUTE = 1000 * 60;

    @Autowired
    private ApplicationContext applicationContext;

    @Scheduled(initialDelay = 10 * MILLISECONDS_IN_MINUTE, fixedRate = MILLISECONDS_IN_MINUTE)
    public void shutdownServer() {
//        System.out.println("Hello World");
        System.exit(SpringApplication.exit(applicationContext));
    }
}
