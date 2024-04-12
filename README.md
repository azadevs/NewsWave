# NewsWave-App

Welcome to NewsWave-App! This is a modern news application built for Android, utilizing various powerful libraries and architectural components.

---

## News API

News-App utilizes the [News API](https://newsdata.io/blog/news-api-for-android/) to fetch the latest news articles from various sources. The News API provides a comprehensive database of news articles from around the world, allowing our app to deliver up-to-date and relevant content to users.

For more information on how to integrate the News API into your Android app, please refer to the [News API Documentation](https://newsdata.io/docs/news-api).

---

## Screenshots

<img src="https://imgur.com/LXyz8Oe.png" width="250"> <img src="https://imgur.com/9Jl3oe0.png" width="250">
<img src="https://imgur.com/4LZIY4z.png" width="250"> <img src="https://imgur.com/6UmGoCu.png" width="250">
<img src="https://imgur.com/uSc2p8D.png" width="250"> <img src="https://imgur.com/m1iCQTV.png" width="250">
<img src="https://imgur.com/zakEgup.png" width="250"> <img src="https://imgur.com/em2P92c.png" width="250">
<img src="https://imgur.com/ruNJ22k.png" width="250"> <img src="https://imgur.com/swlhNHw.png" width="250">                 
---

## Features
- Browse the latest news articles from various sources.
- Search for news articles based on keywords.
- Save favourite articles for offline reading.
- Share articles with friends and on social media platforms.

---


## Architecture
News-App follows a clean architecture pattern, utilizing MVVM (Model-View-ViewModel) with a repository pattern for data management, and employing Android Architecture Components for a robust app structure.
<p align="center">
<img src="https://th.bing.com/th/id/OIP.RboNE1JcU4rpMKiEA-4mAgHaFj?rs=1&pid=ImgDetMain.png"  />  
</p>

---

## Libraries and Tools

### Coroutines
- **Description**: Kotlin Coroutines provide a concise and expressive way to manage asynchronous programming in Android applications, ensuring a smooth and responsive user experience.
- **Link**: [Kotlin Coroutines GitHub Repository](https://github.com/Kotlin/kotlinx.coroutines)

### Room
- **Description**: Room is an SQLite object mapping library that simplifies database operations in Android apps. It provides an abstraction layer over SQLite, making it easy to work with structured data.
- **Link**: [Room GitHub Repository](https://developer.android.com/topic/libraries/architecture/room)

### Retrofit
- **Description**: Retrofit is a type-safe HTTP client for Android and Java, which simplifies the process of making network requests and parsing JSON responses. It is widely used for accessing RESTful APIs.
- **Link**: [Retrofit GitHub Repository](https://github.com/square/retrofit)

### Glide
- **Description**: Glide is a fast and efficient image-loading library for Android, offering powerful features for loading, caching, and displaying images. It helps optimize image loading in your app.
- **Link**: [Glide GitHub Repository](https://github.com/bumptech/glide)

### Navigation Components
- **Description**: Navigation Components is a part of Android Jetpack that helps you implement navigation in your app, making it easier to navigate between different screens and handle deep linking.
- **Link**: [Navigation Components Overview](https://developer.android.com/guide/navigation)

### Hilt
- **Description**: Hilt is a dependency injection library for Android, built on top of Dagger 2. It simplifies dependency injection setup and management, improving app modularity and testability.
- **Link**: [Dagger Hilt GitHub Repository](https://dagger.dev/hilt/)

----

## Getting Started

To set up and run NewsWave-App on your local machine, follow these steps:

1. Clone this repository.
2. Register for an API key on [News API](https://newsdata.io/blog/news-api-for-android/) to access the news articles. 
3. Open the project in Android Studio.
4. In the project's root directory, create a file named `local.properties`.
5. Inside `local.properties`, add the following line with your News API key: Replace `YOUR_API_KEY_HERE` with your actual News API key.
6. Build and run the app on an Android device or emulator.

---

## Contact
For any inquiries or feedback, please contact [Azamat Kalmurzayev](@codingwithme28@gmail.com).
