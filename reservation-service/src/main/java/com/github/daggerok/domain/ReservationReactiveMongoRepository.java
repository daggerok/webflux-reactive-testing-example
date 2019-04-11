package com.github.daggerok.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface ReservationReactiveMongoRepository extends ReactiveMongoRepository<Reservation, String> {
  Flux<Reservation> findAllByName(@Param("name") String nane);
}
