package org.example.online_food_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableCaching
public class OnlineFoodStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodStorageApplication.class, args);
    }

}
