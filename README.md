# CalzTracker 📱

CalzTracker is an Android calorie and nutrition tracking app built with Kotlin. It lets users log meals, automatically retrieve nutritional information from the USDA FoodData Central API, track daily macronutrient totals, record weight over time, and configure personal preferences.

## Features

- 🍽️ **Meal tracking**
  - Add meals with calories, protein, carbohydrates, and fats.
  - View recently logged meals from the home screen.
  - Automatically calculate total calories and macronutrients.

- 🔎 **USDA food search**
  - Search for foods using the USDA FoodData Central API.
  - Automatically populate meal nutrition information from search results.

- 📈 **Progress tracking**
  - Record weight entries against specific dates.
  - Visualize weight history with a line chart.

- ⚙️ **Settings**
  - Set a daily calorie goal.
  - Toggle dark mode.
  - Reset saved meal/macronutrient data.
  - Reset weight history.

- 💾 **Local storage**
  - Meal and weight data is stored locally using Room.



Example:

![Home screen](screenshots/home.png)

## Tech Stack

- **Kotlin**
- **Android SDK**
- **Android Views / XML layouts**
- **AndroidX Fragments**
- **Navigation Component**
- **ViewModel + LiveData**
- **Room Database**
- **Kotlin Coroutines**
- **Retrofit + Gson**
- **OkHttp**
- **USDA FoodData Central API**
- **MPAndroidChart**
- **Material Components**
- **Gradle**

## Architecture

The project separates UI, local data, and remote API code:

```text
app/src/main/java/com/example/calztrackerapp/
├── data/
│   ├── model/
│   │   └── db/
│   │       ├── AppDatabase.kt
│   │       ├── Meal.kt
│   │       ├── MealDao.kt
│   │       ├── WeightDao.kt
│   │       └── WeightEntry.kt
│   │
│   └── remote/
│       ├── UsdaApi.kt
│       ├── UsdaApiService.kt
│       └── model/
│
├── ui/
│   ├── home/
│   ├── log/
│   ├── progress/
│   └── settings/
│
└── MainActivity.kt
```

The application uses:

- **Room** for persistent local meal and weight data.
- **ViewModels** to manage UI-related data.
- **LiveData** to observe database changes.
- **Retrofit** for communication with the USDA API.
- **Navigation Component** for switching between the main screens.

## Requirements

- Android Studio
- Android SDK 34
- JDK 8+
- An Android device or emulator
- A USDA FoodData Central API key

The app supports devices running **Android 7.0 (API 24) or higher**.

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/CalzTrackerApp.git
cd CalzTrackerApp
```

### 2. Add your USDA API key

The app expects the API key to be stored in `local.properties`.

Create or edit:

```text
local.properties
```

and add:

```properties
sdk.dir=/path/to/your/Android/sdk
USDA_API_KEY=YOUR_USDA_API_KEY
```

On Windows, Android Studio normally creates the `sdk.dir` entry automatically.

> **Important:** `local.properties` should never be committed to GitHub. The repository's `.gitignore` already excludes it.

You can obtain a USDA FoodData Central API key from the USDA FoodData Central API service.

### 3. Open the project

Open the project in Android Studio and allow Gradle to sync and download the required dependencies.

### 4. Run the app

Start an Android emulator or connect an Android device, select it in Android Studio, and click:

```text
▶ Run
```

You can also build/install the debug version from the project root:

```bash
./gradlew installDebug
```

On Windows:

```powershell
gradlew.bat installDebug
```

## Main Screens

### Home

Displays recently logged meals along with total:

- Calories
- Protein
- Carbohydrates
- Fat

### Log Meal

Users can manually enter nutrition information or search for a food through the USDA API. The first matching food result is used to populate the nutrition fields.

### Progress

Users can select a date and record their weight. Saved entries are displayed as a weight-over-time chart.

### Settings

Provides controls for:

- Daily calorie goal
- Dark mode
- Resetting meal data
- Resetting weight history

## Data Storage

CalzTracker uses a local Room database named `meal_db`.

The database contains:

- `meal_table` — logged meals and their macronutrient information.
- `weight_table` — dated weight entries.

Settings such as the calorie goal and dark-mode preference are stored using Android `SharedPreferences`.

## API

Food searches use the **USDA FoodData Central API**.

The application sends a food search query and uses nutritional data returned by the API to populate:

- Calories
- Protein
- Carbohydrates
- Total fat

An API key is required to use this functionality.

## Future Improvements

Potential improvements include:

- [ ] Allow users to select from multiple USDA search results instead of using the first result.
- [ ] Add daily calorie-goal progress indicators.
- [ ] Add meal editing and deletion from the UI.
- [ ] Add more detailed nutrition information.
- [ ] Improve chart date labels and interaction.
- [ ] Add automated unit and UI tests.
- [ ] Improve error handling for network/API failures.
- [ ] Add a proper release build and distribution workflow.

## License

This project does not currently specify a license.

If you intend to make the project open source, consider adding an appropriate `LICENSE` file.

## Author

**Ivaylo Ninov**

GitHub: `https://github.com/Shiesty227`
