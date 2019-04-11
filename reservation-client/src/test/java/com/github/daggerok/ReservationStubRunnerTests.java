package com.github.daggerok;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.daggerok.dpmain.Reservation;
import com.github.daggerok.rest.ReactiveReservationClient;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.List;

import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@Import({ ReactiveReservationClient.class, ReservationClientApplication.class })
@AutoConfigureStubRunner(ids = "com.github.daggerok:reservation-service:+:8080", stubsMode = LOCAL)
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
