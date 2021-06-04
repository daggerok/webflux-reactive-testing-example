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
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

@AutoConfigureJsonTesters
@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8080)
@Import({ ReactiveReservationClient.class, ReservationClientApplication.class })
public class ReservationWireMockTests {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ReactiveReservationClient client;

  @Before
  public void setUp() throws Exception {
    final String json = objectMapper.writeValueAsString(List.of(Reservation.allOf("1", "Max")));

    WireMock.stubFor(
        WireMock.get("/v1/reservations")
                .willReturn(WireMock.aResponse()
                                    .withHeader(CONTENT_TYPE, APPLICATION_PROBLEM_JSON_VALUE)
                                    .withBody(json))
    );
  }

  @Test
  public void test() {
    StepVerifier.create(client.getReservations())
                .expectNextMatches(r -> r.getId() != null && r.getName().equalsIgnoreCase("max"))
                .expectComplete().log()
                .verify();
  }
}
