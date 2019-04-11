package com.github.daggerok.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

  private String id;

  @NonNull
  private String name;
}
