package com.github.daggerok.rest;

import com.github.daggerok.domain.Reservation;
import com.github.daggerok.domain.ReservationReactiveMongoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@Import(WebFluxResourceConfig.class)
public class ReservationRestResourceTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private ReservationReactiveMongoRepository repository;

  @Test
  public void test() {
    Mockito.when(repository.findAll())
           .thenReturn(Flux.just(Reservation.allOf("4", "Max")));

    webTestClient.get().uri("/v1/reservations")
                 .exchange()
                 .expectStatus().isOk()
                 .expectHeader().contentType(MediaType.APPLICATION_JSON)
                 .expectBody().jsonPath("@.[0].name").isEqualTo("Max");
  }
}
