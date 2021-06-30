package com.github.daggerok.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
public class ReservationReactiveRepositoryTest {

  @Autowired
  private ReservationReactiveMongoRepository repository;

  @Test
  public void test() {
    final Mono<Reservation> publisher = repository.save(Reservation.of("Maksimko"));

    StepVerifier.create(publisher.thenMany(repository.findAllByName("Maksimko")))
                .expectNextMatches(r -> r.getName().equalsIgnoreCase("maksimko") && r.getId() != null)
                .verifyComplete();
  }
}
