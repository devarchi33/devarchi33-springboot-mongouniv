package com.devarchi33.mongouniv;

import com.devarchi33.mongouniv.config.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;

@SpringBootApplication
@EnableWebMvc
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Properties properties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Map<String, Map<String, String>> servers = properties.getServers();
        Map<String, String> mongoInfo = servers.get("mongo");
        String host = mongoInfo.get("host");
        int port = Integer.parseInt(mongoInfo.get("port"));
        logger.info("Mongo host : {}, port: {}", host, port);
    }
}
