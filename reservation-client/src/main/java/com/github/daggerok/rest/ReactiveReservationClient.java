package com.github.daggerok.rest;

import com.github.daggerok.dpmain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ReactiveReservationClient {

  private final WebClient webClient;

  public Flux<Reservation> getReservations() {
    return webClient.get().uri("/v1/reservations")
                    .retrieve()
                    .bodyToFlux(Reservation.class);
  }
}
