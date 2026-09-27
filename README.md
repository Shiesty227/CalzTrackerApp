# CalzTracker

CalzTracker is an Android calorie-tracking application designed to help users keep track of their daily food intake and nutritional information.

## About

CalzTracker provides a simple way to search for food, record meals, and monitor daily calorie intake.

The application uses the USDA FoodData Central API to retrieve nutritional information for food items.

## Features

* Search for food and nutritional information
* View calories and nutritional data
* Add food to your daily intake
* Track daily calorie consumption
* View recorded meals
* Store food and meal information locally
* Retrieve food information using the USDA FoodData Central API
* Android-native user interface

## Technologies

* Kotlin
* Android SDK
* Android Studio
* Jetpack Compose
* Room Database
* USDA FoodData Central API
* Gradle

## Project Structure

The project follows a standard Android application structure, separating the user interface, data handling, database components, and API functionality.

```text
CalzTrackerApp/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           ├── res/
│           └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

## USDA API Configuration

CalzTracker uses the USDA FoodData Central API.

The API key is stored locally in `local.properties` and is not included in this repository.

Example:

```properties
USDA_API_KEY=your_api_key_here
```

## Running the Application

1. Clone the repository.

```bash
git clone https://github.com/Shiesty227/CalzTrackerApp.git
```

2. Open the project in Android Studio.

3. Add your USDA API key to your local `local.properties` file.

4. Allow Gradle to sync the project.

5. Connect an Android device or start an Android emulator.

6. Run the application from Android Studio.

## Data Storage

CalzTracker uses a local database to store relevant application data, allowing information to remain available between application sessions.

## Food Data

Food and nutritional information is retrieved using the USDA FoodData Central API.

USDA FoodData Central provides nutritional information for a wide range of foods and food products.

## Requirements

To build and run the project, you need:

* Android Studio
* Android SDK
* A compatible Android device or emulator
* Internet access for USDA food-data requests
* A USDA FoodData Central API key

## License

This project is provided for educational and development purposes.
