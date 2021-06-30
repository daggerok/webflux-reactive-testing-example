package com.github.daggerok;

import com.github.daggerok.rest.ReactiveReservationClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@SpringBootTest
@Import({ ReactiveReservationClient.class, ReservationClientApplication.class })
@AutoConfigureStubRunner(ids = "com.github.daggerok:reservation-service:1.0.1-SNAPSHOT:8080", stubsMode = LOCAL)
public class ReservationStubRunnerTests {

  @Autowired
  private ReactiveReservationClient client;

  @Test
  public void test() {
    StepVerifier.create(client.getReservations())
                .expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("max"))
                .expectComplete().log()
                .verify();
  }
}
