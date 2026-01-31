# Backend Design Overview

## Architecture

Stateless Spring Boot REST API (Java 21, Spring Boot 4.0.2) that proxies and simplifies OpenWeatherMap API responses. No database, no auth, no session state.

## Layer Summary

```
Controller                 Service                    Client                     External
(@RestController)          (@Service)                 (@Component)               (OpenWeatherMap)
|                          |                          |                          |
| - Input validation       | - Orchestration          | - URI construction       |
|   (Jakarta annotations)  | - DTO assembly           | - RestClient calls       |
| - Parameter binding      | - Response transformation| - JSON deserialization   |
```

## Request Flows

### GET /getCurrentWeather (Zip Lookup)

```
Frontend              Controller              Service                Client              OW API
|                     |                       |                      |                    |
|-- GET /getCurrentWeather(zip, country, units, langCode) -------->  |                    |
|                     |                       |                      |                    |
|                [validate params]            |                      |                    |
|                zip: @NotBlank               |                      |                    |
|                country: @NotBlank           |                      |                    |
|                     |                       |                      |                    |
|                [fail? -> ConstraintViolationException -> 400]      |                    |
|                     |                       |                      |                    |
|                     |                       |                      |                    |
|                     |-getCurrentWeather()-->|                      |                    |
|                     |                       |                      |                    |
|                     |              [build ZipRequestDTO]           |                    |
|                     |                       |                      |                    |
|                     |                       |-- getCoordsByZip()-->|                    |
|                     |                       |                      |-- /geo/1.0/zip --> |
|                     |                       |                      |<-- GeocodingRes ---|
|                     |                       |<-- lat, lon, name ---|                    |
|                     |                       |                      |                    |
|                     |                       |                      |                    |
|                     |            [build WeatherRequestDTO]         |                    |
|                     |                       |                      |                    |
|                     |                       |-getCurrentWeather()->|                    |
|                     |                       |                      |-- /data/2.5/w ---> |
|                     |                       |                      |<-- WeatherRes -----|
|                     |                       |<-- weather data -----|                    |
|                     |                       |                      |                    |
|                     |                       |                      |                    |
|                     |         [build SimplifiedWeatherRes]         |                    |
|                     |         [format time via DateTimeUtils]      |                    |
|                     |                       |                      |                    |
|                     |<-SimplifiedWeatherRes-|                      |                    |
|                     |                       |                      |                    |
|<-- 200 OK + JSON ---|                       |                      |                    |
```

## Exception Flow

```
Any exception
    |
    v
GlobalExceptionHandler (@ControllerAdvice, extends ResponseEntityExceptionHandler)
    |
    |- RestClientResponseException -> maps status codes to user-friendly messages
    |     404: "Location not found..."
    |     401: "API authentication failed..."
    |     429: "Too many requests..."
    |     default: "Weather service unavailable..."
    |
    |- ConstraintViolationException -> aggregates violation messages, 400
    |
    |- Exception (catch-all) -> "An unexpected error occurred", 500
    |
    v
ErrorResponseDTO { message, status, timestamp }
```

## Validation Strategy

Validation is at the **controller parameter level**, not the DTO level. The controller uses `@Validated` with Jakarta annotations directly on `@RequestParam` parameters:

| Parameter  | Constraints                                      |
|------------|--------------------------------------------------|
| `zip`      | `@NotBlank`, `@Size(2-10)`                       |
| `country`  | `@NotBlank`, `@Size(2)`, `@Pattern([a-zA-Z]{2})` |
| `units`    | Optional (default: "imperial"), `@Pattern(imperial|metric)` |
| `langCode` | Optional (default: "en"), `@Size(2-5)`           |

Request DTOs have no validation annotations. They are plain data carriers constructed by the service layer after controller validation has already passed.

## DTO Design

**Request DTOs (classes, mutable):** Used to package validated controller params before passing to OpenWeatherClient.

| DTO                       | Fields                                  | Notable                          |
|---------------------------|-----------------------------------------|----------------------------------|
| `GeocodingZipRequestDTO`  | zipCode, countryCode                    | `toZipParam()` formats API query |
| `GeocodingDirectRequestDTO` | cityName, stateCode, countryCode, limit | `toQueryParam()` - not currently used |
| `WeatherRequestDTO`       | latitude (double), longitude (double), units, language | **Design note:** lat/lon should be `Double` (boxed) - primitives can't distinguish "absent" from 0,0 which is a valid coordinate |

**Response DTOs (records, immutable):** Direct deserialization from OpenWeatherMap JSON or simplified output to frontend.

| DTO                          | Purpose                                  |
|------------------------------|------------------------------------------|
| `WeatherResponseDTO`        | Raw API response mapping (nested `Sys` record) |
| `GeocodingResponseDTO`      | Raw geocoding response mapping           |
| `SimplifiedWeatherResponseDTO` | Curated response to frontend (custom builder) |
| `ErrorResponseDTO`          | Standardized error format (`of()` factory) |

**Shared component records:** `WeatherConditionDTO`, `WeatherMeasurementsDTO`, `CloudsDTO`, `CoordDTO`, `WindDTO` - nested structures within `WeatherResponseDTO`.

## Configuration

**Properties** (`application.yml` / `application-local.yml`):
- `openweather.api.key` - API key via `@Value` (env var: `OPENWEATHER_API_KEY`)
- `openweather.api.base-url` - Base URL (default: `https://api.openweathermap.org`)

`OpenWeatherClient` builds its own `RestClient` internally. No separate config class.

## Utilities

**DateTimeUtils** (final class, private constructor):
- `toLocalDateTimeString(long dt, int timezoneOffsetSeconds)` - epoch + offset to formatted local time (default pattern: "MMMM d, yyyy h:mm a")
- `toLocalDateTimeString(long dt, int timezoneOffsetSeconds, String pattern)` - custom pattern overload
- `getCurrentSystemTime(String pattern)` - system time formatting (not used in current flow)

## Dependency Injection Graph

```
WeatherController
    <- WeatherService (constructor)

WeatherService
    <- OpenWeatherClient (constructor)

OpenWeatherClient
    <- @Value properties (api key, base url)
    <- Builds own RestClient internally
```

## Key Design Decisions

- **Removed `RestClientConfig`** during RestTemplate -> RestClient migration. Single-client app doesn't warrant a centralized bean factory. `OpenWeatherClient` builds its own `RestClient` with the base URL baked in. Would reintroduce a config class if multiple clients needed shared configuration (timeouts, interceptors, error handlers).

## Open Design Questions

- [ ] `WeatherRequestDTO.latitude/longitude` should be `Double` (boxed) instead of `double` (primitive) - 0,0 is a valid coordinate, primitives can't represent "absent"
- [ ] `GeocodingDirectRequestDTO` is defined but unused in the current flow
