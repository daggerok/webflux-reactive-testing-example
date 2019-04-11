package com.github.daggerok.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationTest {

  @Test
  public void test() {
    Reservation reservation = new Reservation("1", "Mike");

    assertThat(reservation.getId()).as("reservation ID")
                                   .isEqualTo("1");

    assertThat(reservation.getName()).as("guest name")
                                     .isEqualTo("Mike");
  }
}
