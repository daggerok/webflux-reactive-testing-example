# Reactive TDD with spring-boot [![CI](https://github.com/daggerok/webflux-reactive-testing-example/actions/workflows/ci.yaml/badge.svg)](https://github.com/daggerok/webflux-reactive-testing-example/actions/workflows/ci.yaml)

## Dev

```bash
rm -rf ~/.m2/repository/com/github/daggerok
./mvnw clean test install -f reservation-service ; ./mvnw clean test -f reservation-client
```

## Docs

See `reservation-service/src/test/java/com/github/daggerok` tests

Follow guide from git commits...

_run tests_

```bash
./mvnw clean test
```

_run app with embedded mongo_

```bash
./mvnw -f ./reservation-service/pom.xml -P mongoEmbedded spring-boot:run
# or
./mvnw -pl :reservation-service -Dmongo=embedded spring-boot:run
```

resources:

* [YouTube: Bootiful Testing](https://www.youtube.com/watch?v=1W5_tOiwEAc)
