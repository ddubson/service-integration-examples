# Service Integration Examples

## Goals

To gain a greater understanding of service integration points and how to properly test 
not just happy paths but failure points of service integration.

- Plain unit test is used to cover the happy path of calling a service from an app
- Integration tests (with help of WireMock) are used to cover service delay scenarios:
    - Service does not respond within its SLA (e.g. 2 seconds)
    - Service is not available (no connection can be established)