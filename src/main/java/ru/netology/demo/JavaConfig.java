package ru.netology.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import ru.netology.demo.DevProfile;
import ru.netology.demo.ProductionProfile;
import ru.netology.demo.SystemProfile;

public class JavaConfig {
    @ConditionalOnProperty ("netology.profile.dev")
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }
    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
