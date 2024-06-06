package com.colak.springtutorial.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.session.SessionIdGenerator;
import org.springframework.session.hazelcast.SessionUpdateEntryProcessor;


@Configuration
public class HazelcastHttpSessionConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig()
                .addAddress("0.0.0.0:5701"); // connect to local server

        clientConfig.getUserCodeDeploymentConfig()
                .setEnabled(true)
                .addClass(Session.class)
                .addClass(MapSession.class)
                .addClass(SessionUpdateEntryProcessor.class)
                .addClass(SessionIdGenerator.class)
        ;
        return HazelcastClient.newHazelcastClient(clientConfig);
    }
}
