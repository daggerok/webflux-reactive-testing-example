package com.github.daggerok.dpmain;

import lombok.*;

@Getter
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor(staticName = "allOf")
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Reservation {

  private String id;

  @NonNull
  private String name;
}
