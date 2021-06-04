package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

  request {
    method(GET())
    url("/v1/reservations")
  }

  response {
    status(200)
    headers {
      contentType(applicationJson())
    }
    body("""
      [
        { "id":"5", "name":"Max" }
      ]
    """)
  }
}
