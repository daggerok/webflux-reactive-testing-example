package com.github.daggerok.domain;

import lombok.*;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "allOf")
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

  private String id;

  @NonNull
  private String name;
}
