# The Score QA Assignment

## Project Description
This project is a coding QA assignment for The Score.
It involves building a Java application using Gradle for dependency
management and build automation.
The application's purpose and detailed functionalities are
defined in the test specification document.

## How to Install and Run the Project

### Prerequisites
- Ensure you have Java Development Kit (JDK) installed. For the purpose of this assignment OpenJDK 11 is utilised!
- Gradle should be installed.
- For local runs ensure you have Appium Server GUI active.
- Ensure emulator details and environment is matching with config.properties file - if need be, please update either to match each other.

### Cloning the Repository
To clone the repository, open your terminal and run the following command:

```git clone https://github.com/lvndnyrv/the_score_assignment.git```

Navigate into the cloned directory: ```cd the_score_assignment```

### Setting Up the Android Emulator
You will need an Android emulator named emulator-5554. If you have a different emulator name, update the config.properties file with your local emulator name.

### Running the Project
To build and run the project using Gradle, execute the following commands in the terminal:

To build the project: ```./gradlew build```
To run the tests: ```./gradlew test```

