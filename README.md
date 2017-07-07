# Tic Tac Toe in Java

## Game Requirements

- A command line application
- Two players can play tic tac toe
- Each player is prompted in turn to make his/her move
- After making a move, an updated tic tac toe board will be rendered with the last player's requested move
- Following display of the updated board, the next player will be prompted for their next move
- This process will repeat until the game is over
- On Game Over, the application will indicate whether the game was won by X, O or was a draw


## Technical requirements

- Built in Java
- Built using TDD (no new code written without a failing unit test)
- 100% test coverage (except for main)


## Implementation

- Board Class:
    - [x] makes moves (throws error if square already taken)
    - [x] records moves
    - [x] checks if player has won
    - [x] checks if board is complete

- Game Class:
    - [x] stores current player
    - [x] makes move on board
    - [x] reports on current status of game (isGameOver, isWinner, getWinner)

- ConsoleUI:
    - [x] renders board
    - [x] prompts for user input
    - [x] accepts, parses and validates user input

- TicTacToeRunner:
    - [x] initialises a Game
    - [x] requests current player's move
    - [x] makes move and renders updated game
    - [x] if game over, reports winner or draw and offers a play again option
    - [x] if not game over, requests current player's move
    - [x] allows Game to be quit