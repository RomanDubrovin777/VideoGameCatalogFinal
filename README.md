# Video Game Catalog Android App 📱

Video Game Catalog is an Android application that provides detailed information about various video games. Users can sign up, log in, and explore a catalog of games, each with a platform, release date, developer info, genre, a short description, and a direct link to start playing. This document highlights key features, installation steps, usage details, and screenshots demonstrating the app’s main screens.

--------------------------------------------------------------------------------
TABLE OF CONTENTS 📌
--------------------------------------------------------------------------------
- Features
- Screenshots
- Technologies Used
- Installation
- Usage
- Project Structure

--------------------------------------------------------------------------------
FEATURES 🔥
--------------------------------------------------------------------------------
- User Authentication
   - User login with email and password.
  
  - Registration with email validation and password length verification.
  
  - Phone number validation.
  
  - Password confirmation check.
  
  - Displays error messages for failed logins.
  
  - Stores user information in Firebase Realtime Database upon first login.
  
  - Shows user details in the Account Info screen if they already exist.

  - Profile Management: Update contact info and upload a profile picture.
  
- Game Catalog
  - Browse Games: Scroll through a collection of games, each displayed with a cover image.
  - Search and Filter: Filter by genre, release date, or developer. Search by game title.
  - Detailed Game Info: View a short description, release date, supported platforms, genre, and developer details.
- Play Link
  - Each game entry includes a “Click Now To Play” button, redirecting users to the official site or download page.
- Modern UI
  - Responsive and visually appealing design with easy navigation.
  - Supports various screen sizes and orientations.

--------------------------------------------------------------------------------
SCREENSHOTS
--------------------------------------------------------------------------------


1. Login Screen:
 
<img src="https://github.com/user-attachments/assets/c4bf51ce-363d-43b7-bc4a-9eba9e1611bd" width="200">

    A simple login form prompting for email and password.

2.Register Screen:

<img src="https://github.com/user-attachments/assets/5f63be65-4d6f-497d-ba54-8e90566e1bfa" width="200">

3. Profile Screen:
   
<img src="https://github.com/user-attachments/assets/899cb446-fe00-4f62-84a4-490ff5ece6f3" width="200">

   Users can enter or edit their email and phone number, and upload a profile picture.

5. Game List / Catalog:

<img src="https://github.com/user-attachments/assets/11d69175-8991-458b-bb61-b99595d9b76b" width="200">
  
  A grid of popular titles, complete with cover images.

4. Game Details:

<img src="https://github.com/user-attachments/assets/e6b12020-9b7a-4573-b548-70646daf920b" width="200">

   Displays the selected game’s info: platform, developer, launch date, genre, and description. Includes a button to play.

6. External Play Link:

<img src="https://github.com/user-attachments/assets/a544a320-7a5b-423d-bfe2-2df1ecd5a126" width="200">

   Redirects users to the official website or platform-specific store page.

8. Filter by Genre, Date, and Developer:

<img src="https://github.com/user-attachments/assets/618775ba-f4ae-4d34-8280-fde981fe12d7" width="200">

   Dropdown filters for genre, release date, and developer.

9. Search Results:
    
<img src="https://github.com/user-attachments/assets/cf75149a-cb77-46b5-babc-7aa72c051632" width="200">

   Displays relevant games as users type into the search bar.

USAGE
--------------------------------------------------------------------------------
1. Launch the App: Upon opening, you’ll be greeted by the Login Screen.
2. Create an Account: If you’re not a member, click the Register link to sign up.
3. Log In: Enter valid credentials to access the main catalog.
4. Explore Games:
   - Scroll through the list to see various titles.
   - Use the top filters or the search bar to find specific games.
5. View Game Details: Tap on any game to see more information.
6. Play the Game: Click the “Click Now To Play” button to launch an external link in your browser.
7. Profile Management: Access your profile from the menu (if applicable) to update your info and upload a profile picture.

