# Service Integration Examples

[![Build Status](https://travis-ci.org/ddubson/service-integration-examples.svg?branch=master)](https://travis-ci.org/ddubson/service-integration-examples)

## Goals

To gain a greater understanding of service integration points and how to properly test 
not just happy paths but failure points of service integration.

What's addressed:

- **Unit** tests are used to cover the happy path(s) of calling a service from an app
- **Integration** tests (with the help of WireMock) are used to cover full or partial service failure scenarios,
such as:
    - Service does not respond within its SLA (e.g. 2 seconds)
    - Service is not available (no connection can be established)
    
---

## Architecture

- Address Book Application (`address-book-app`) - front facing application that 
- User Profile Service (`user-profile-service`) - user details API