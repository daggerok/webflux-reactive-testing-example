package com.github.daggerok.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ReservationReactiveTemplateTest {

  @Autowired
  private ReactiveMongoTemplate template;

  @Test
  public void test() {
    Reservation reservation = new Reservation("2", "Max");

    StepVerifier.create(template.save(reservation))
                .expectNextMatches(r -> r.getId().equals("2") && r.getName().equals("Max"))
                //.verifyComplete() // doing same what next two:
                .expectComplete().log() // if you need log or...
                .verify();
  }
}
