# Star Trader Remake

Remake of Star Trader game for _CS2340_, this game is a decision based game where you are a wandering traveler that must make decisions about resources, companions, and beyond in order to keep your Star Trader alive!

### Dependencies and Acknowledgements

 This game is built off the stack of `Java` & `JavaFX 11`. All music in this game is courtesy of [hooksounds](https://www.hooksounds.com/). 

### Setup

To run the JavaFX application, follow these steps:

#### Install Prerequisites: 

- Java Development Kit (JDK): Ensure you have the JDK installed. JavaFX requires at least `JDK 8.` For the latest features, it's recommended to use the latest JDK version.
- JavaFX SDK: Starting from `JDK 11`, JavaFX is not bundled with the JDK, so you may need to download the `JavaFX SDK`separately.

#### Clone The Repository:

Run the command `git clone https://github.com/adithya-r-01/star-trader.git`

#### Configure Your IDE:

If you are using an IDE like `IntelliJ IDEA` or `Eclipse`, configure it to include the `JavaFX SDK` in your project.

- For `IntelliJ IDEA`: Go to `File -> Project Structure -> Libraries` and add the `JavaFX SDK`.
- For `Eclipse`: Right-click on your `project -> Build Path -> Configure Build Path,` and add the `JavaFX SDK` under the `Libraries` tab.

#### Run From Your Command-Line:

- Compile the Java Files `javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml Main.java`
- Run the Application `java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml Main`
