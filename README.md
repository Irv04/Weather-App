# Android Weather App
This is an Android Weather app built using Kotlin programming language and following the MVVM Clean Architecture pattern. The app is designed to display the current weather conditions for a given location and provides information such as temperature, humidity, and more.

### Features

- Current weather data including temperature, weather condition, max and min temperatures, and humidity
- Shows the weather forecast for the next 5 days.
- Hourly and daily weather forecast
- Ability to search for weather in any city worldwide
- Automatic location detection

### Architecture

The app is built using MVVM Clean Architecture, which consists of the following layers:

- Presentation Layer: The UI layer that contains the UI components such as Activities and Fragments. This layer is responsible for displaying data to the user and handling user interactions.
- Domain Layer: The layer that contains the business logic of the app. This layer is responsible for handling use cases and defining the data models and repository interfaces.
- Data Layer: The layer that contains the data sources such as local and remote data sources. This layer is responsible for fetching data and storing it.

### Libraries Used

- Retrofit: For making network requests to the OpenWeatherMap API.
- Hilt: For dependency injection.
- Picasso: For loading and caching images.
- Coroutines: For handling asynchronous operations.
- LiveData: For observing data changes.
- Room: For storing forecast.


### ScreenShots

<img width="313" alt="Screen Shot 2023-04-10 at 8 14 17 PM" src="https://user-images.githubusercontent.com/54603399/231034683-769e3649-1003-4c45-8634-493cd2503711.png">
<img width="289" alt="Screen Shot 2023-04-10 at 9 55 04 PM" src="https://user-images.githubusercontent.com/54603399/231035434-922ff035-8d8e-4776-8c4e-e70762358eee.png">

