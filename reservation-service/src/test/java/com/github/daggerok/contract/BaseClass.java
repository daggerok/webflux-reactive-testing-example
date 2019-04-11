package com.github.daggerok.contract;

import com.github.daggerok.domain.Reservation;
import com.github.daggerok.domain.ReservationReactiveMongoRepository;
import com.github.daggerok.rest.WebFluxResourceConfig;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static java.lang.String.format;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@Import(WebFluxResourceConfig.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "server.port=0")
public class BaseClass {

  @LocalServerPort
  private int port;

  @MockBean
  private ReservationReactiveMongoRepository repository;

  @Before
  public void setUp() throws Exception {
    RestAssured.baseURI = format("http://127.0.0.1:%s", port);

    Mockito.when(repository.findAll())
           .thenReturn(Flux.just(Reservation.allOf("5", "Max")));
  }
}
