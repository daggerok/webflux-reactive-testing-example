package com.github.daggerok.rest;

import com.github.daggerok.domain.Reservation;
import com.github.daggerok.domain.ReservationReactiveMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class WebFluxResourceConfig {

  private final ReservationReactiveMongoRepository repository;

  @Bean
  RouterFunction<ServerResponse> routes() {
    return route(GET("/v1/reservations"),
                 req -> ok().body(repository.findAll(), Reservation.class));
  }
}
