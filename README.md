# Dev Tools Examples

A Domain-Driven Design (DDD) Spring Boot project demonstrating the integration of essential Java development tools including Lombok, NullAway, MapStruct, and ErrorProne.

## Overview

This project serves as a practical example of how to configure and use modern Java development tools together in a real-world application. It showcases:

- **Domain-Driven Design** implementation with clean separation of concerns
- **Lombok** for reducing boilerplate code
- **NullAway** with ErrorProne for compile-time null safety checking
- **MapStruct** for efficient object mapping
- **Spring Boot** for dependency injection and web framework

## Project Structure

```
src/main/java/org/mickleak/devtoolsexamples/
├── domain/                 # Core business logic and entities
│   └── foo/                # Example domain module
│       ├── vo/             # Value objects with null safety
│       └── Foo.java        # Main entity with business rules
├── application/            # Use cases and business logic orchestration
│   └── foo/
│       └── create/         # Feature-specific package with CQRS pattern
└── infrastructure/         # Technical implementations
```

## Key Features

### Null Safety
Comprehensive null safety is implemented through:
- Lombok's `@NonNull` for runtime null checks
- JSpecify's `@Nullable` for marking intentionally nullable fields
- JSpecify's `@NullMarked` for non-null by default at package level
- All packages contain `package-info.java` with `@NullMarked` annotation
- NullAway (ErrorProne plugin) for compile-time null safety verification

### Value Object Pattern
All domain primitives are wrapped in typed Value Objects that extend `BaseVO<T>`:
- VO classes are `final` and immutable
- Automatic null safety through `@NonNull` Lombok annotation
- Extensible for custom validation rules

### Use Case Pattern
Each feature is implemented as a use case following the CQRS pattern:
- `XxxCommand` records for input parameters
- `XxxResult` records for output data
- `XxxUseCase` classes with `execute()` methods
- Spring's `@Component` for automatic detection

## Getting Started

### Prerequisites
- Java 21+
- Gradle 8+

### Build & Run

```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Run the application
./gradlew bootRun
```

### Running Specific Tests

```bash
# Run a single test class
./gradlew test --tests "org.mickleak.devtoolsexamples.domain.foo.FooTest"

# Run a specific test method
./gradlew test --tests "org.mickleak.devtoolsexamples.domain.foo.FooTest.nullabilityErrorFromLombok_nameVoShouldBeNotNull"
```

## Development Tools Integration

### Lombok
Reduces boilerplate code with annotations like:
- `@RequiredArgsConstructor` for constructor injection
- `@Getter`/`@Setter` for accessors
- `@Builder` for builder pattern

### NullAway
Enforced at compile time on all non-test code:
- Prevents NullPointerExceptions at runtime
- Integrated with ErrorProne for fast compilation
- Disabled for test code to allow flexible test scenarios

### MapStruct
Provides fast object mapping with:
- Compile-time code generation
- Type-safe mapping methods
- Integration with Spring for dependency injection
