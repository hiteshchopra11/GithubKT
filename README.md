# GithubKT

GithubKT is an Android application that displays a list of GitHub repositories for a given organization (Adobe) and provides a search feature to look up specific repositories. It uses clean architecture with MVVM and is built using Jetpack Compose.

---

## Features

### 1. Home Page
- Displays a list of repositories from the Adobe organization.
- Clicking on a repository shows its details on a separate screen.
- Supports pagination with a loading indicator at the bottom for the next page.
- Handles errors like server downtime or no internet connection, with a retry option.
- Survives configuration changes (e.g., screen rotation).
- Images are loaded using Coil Library.

**API Details:**
- URL: `https://api.github.com/orgs/adobe/repos?page=1&pageSize=10`
- Authentication: Bearer token in the header

---

### 2. Search Page
- Accessed via a search icon on the AppBar/Toolbar.
- Opens a search screen with a keyboard for typing the query.
- Displays paginated search results.
- Implements a 500ms debounce to minimize unnecessary API calls.
- Shows "No results found" if no matches are returned.
- Provides a cross icon to clear the search query.
- Handles errors and supports retry options.
- Survives configuration changes.
- Images are loaded using Coil Library.

**API Details:**
- URL: `https://api.github.com/search/repositories?q=google&per_page=10&page=1`
- Authentication: Bearer token in the header

---

### 3. Details Page
- Displays detailed information about the selected repository.
- Includes a button to open the repository URL in the default web browser.
- Receives the selected repository object via routing from the Home or Search screen.

**API Details:**  
- N/A  

---

## Tech Stack
- **Architecture:** Clean Architecture + MVVM
- **UI:** Jetpack Compose
- **Networking Library:** Ktor Client
- **Dependency Injection:** Koin
- **Pagination:** Paging 3 Compose
- **Image Loading Library:** Coil
- **Navigation:** Navigation-compose

**Note:**  
Offline caching is not yet implemented in the app.

---

## Authentication
- The app uses a GitHub Token for authenticating API requests.
- The token is stored in the `local.properties` file with minimal permissions for security purposes.

---

## Installation and Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/hiteshchopra11/GithubKT
    ```
2. Open the project in Android Studio.
3. Add your GitHub token in the `local.properties` file:
    ```properties
    GITHUB_TOKEN=your_github_token_here
    ```
4. Build and run the app on an Android device or emulator.

---

## Demo and APK
- [Demo Video](https://github.com/hiteshchopra11/GithubKT/blob/main/GithubKT_Video.mp4)
- [APK Download](https://drive.google.com/file/d/1xcP6B6_BtHC7dcTaHfx-hbEfDWAv4tpp/view?usp=sharing)

---

## Acknowledgements
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Ktor Client](https://ktor.io/)
- [Coil](https://coil-kt.github.io/coil/)

---

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

---

## Contact
If you have any questions or suggestions, feel free to reach out at:  
📧 hiteshchopra11@gmail.com  

---

## Author
Developed by **Hitesh Chopra**.
