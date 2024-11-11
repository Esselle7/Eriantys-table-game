# Eriantys Table Game

## Description

This project is a digital version of the board game **Eriantys**, developed as a final project for the *Software Engineering* course. It provides a multiplayer experience with both CLI and GUI interfaces, aiming to replicate the original board game's rules while adhering to software engineering principles for code quality and maintainability.

## Key Features

- **CLI and GUI Interfaces**: Supports both command-line and graphical user interface interactions.
- **Multiplayer Support**: Centralized server connection for multiplayer gameplay.
- **Design Patterns**: The architecture is based on design patterns to improve maintainability.
- **Scalable Gameplay**: Multiple difficulty levels and game modes.

## Technologies Used

- **Java**: Primary programming language.
- **JavaFX**: For building the graphical user interface.
- **Socket and RMI**: For network communication between clients and server.
- **Maven**: For dependency management and project build.

## Design Patterns

The project uses several design patterns to optimize its architecture:

- **Observer**: Synchronizes the game state with the user interface.
- **Factory**: Centralizes the creation of complex game objects (e.g., cards, characters).
- **Command**: Manages moves as commands for easier undoing and recording.

## Project Structure

The code is organized into modules to promote clarity and modularity:

- **/src/model**: Contains the core game logic and main entities (players, cards, board).
- **/src/controller**: Handles the game flow and client-server communication.
- **/src/view**: Implements the CLI and GUI interfaces.
- **/src/network**: Manages network communication via sockets.

## Installation

### Prerequisites

- **Java 17** or higher
- **Maven**

### Steps

1. Clone the repository:

   `git clone https://github.com/Esselle7/Eriantys-table-game.git`
   `cd Eriantys-table-game`

2. Build the project using Maven:

   `mvn clean install`

3. Start the server:

   `java -jar target/server.jar`

4. Run the client (CLI or GUI):

   `java -jar target/client.jar`

## License

Distributed under the MIT License. See the `LICENSE` file for more details.

## Contact

For more information, contact the maintainers via GitHub.
