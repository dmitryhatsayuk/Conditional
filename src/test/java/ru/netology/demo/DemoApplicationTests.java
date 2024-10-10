package ru.netology.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;


    public final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    public final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);


    @BeforeEach
    void setUp() {
        prodapp.start();
        devapp.start();

    }

    @Test
    void contextLoads() {

        ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(">>>Current profile is dev<<<\n", devEntity.getBody());
        ResponseEntity<?> prodEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(">>>Current profile is production<<<\n", prodEntity.getBody());
    }

}