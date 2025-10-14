### Common architectural principles
- Separation of concerns
- Drive UI from data models
  ```
  Persistent models are ideal for the following reasons:
    - Your users don't lose data if the Android OS destroys your app to free up resources.
    - Your app continues to work in cases when a network connection is flaky or not available. 
  If you base your app architecture on data model classes, you make your app more testable and robust.
  ```
- Single source of truth
    ```
  This pattern brings multiple benefits:
    - It centralizes all the changes to a particular type of data in one place.
    - It protects the data so that other types cannot tamper with it.
    - It makes changes to the data more traceable. Thus, bugs are easier to spot.
    In an offline-first application, the source of truth for application data is typically a database. In some other cases, the source of truth can be a ViewModel or even the UI.
  ```
- Unidirectional Data Flow

### Recommended app architecture
Considering the common architectural principles mentioned in the previous section, each application should have at least two layers:

- The UI layer that displays application data on the screen.
- The data layer that contains the business logic of your app and exposes application data.

You can add an additional layer called the domain layer to simplify and reuse the interactions between the UI and data layers

<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png" width="400">

### Modern App Architecture

This Modern App Architecture encourages using the following techniques, among others:

- A reactive and layered architecture.
- Unidirectional Data Flow (UDF) in all layers of the app.
- A UI layer with state holders to manage the complexity of the UI.
- Coroutines and flows.
- Dependency injection best practices.

#### UI Layer
The UI layer is made up of two things:

- UI elements that render the data on the screen. You build these elements using Views or Jetpack Compose functions.
- State holders (such as ViewModel classes) that hold data, expose it to the UI, and handle logic.

<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-ui.png" width="400">

#### Data Layer

<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-data.png" width="400">

Repository classes are responsible for the following tasks:

- Exposing data to the rest of the app.
- Centralizing changes to the data.
- Resolving conflicts between multiple data sources.
- Abstracting sources of data from the rest of the app.
- Containing business logic.

#### Domain Layer

<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-domain.png" width="400">

### Manage dependencies between components

Classes in your app depend on other classes in order to function properly. You can use either of the following design patterns to gather the dependencies of a particular class:

- Dependency injection (DI): Dependency injection allows classes to define their dependencies without constructing them. At runtime, another class is responsible for providing these dependencies.
- Service locator: The service locator pattern provides a registry where classes can obtain their dependencies instead of constructing them.

### General best practices

- Don't store data in app components.
- Reduce dependencies on Android classes.
- Create well-defined boundaries of responsibility between various modules in your app.
- Expose as little as possible from each module.
- Focus on the unique core of your app so it stands out from other apps.
- Consider how to make each part of your app testable in isolation.
- Types are responsible for their concurrency policy.
- Persist as much relevant and fresh data as possible.

### Benefits of Architecture

Having a good Architecture implemented in your app brings a lot of benefits to the project and engineering teams:

- It improves the maintainability, quality and robustness of the overall app.
- It allows the app to scale. More people and more teams can contribute to the same codebase with minimal code conflicts.
- It helps with onboarding. As Architecture brings consistency to your project, new members of the team can quickly get up to speed and be more efficient in less amount of time.
- It is easier to test. A good Architecture encourages simpler types which are generally easier to test.
- Bugs can be investigated methodically with well defined processes.
