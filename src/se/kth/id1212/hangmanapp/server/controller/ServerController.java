package se.kth.id1212.hangmanapp.server.controller;

import se.kth.id1212.hangmanapp.server.model.HandleUserGuess;

public class ServerController {

    private HandleUserGuess handleGuess = new HandleUserGuess();
    private String responseFromServer;

    public String handleUserInput(String userInputFromClient) {
        if (userInputFromClient.contains("Connect")) {
            responseFromServer = handleGuess.initialMessage();
        } else if (userInputFromClient.equals("New Game")) {
            responseFromServer = handleGuess.newGame();
        } else if (userInputFromClient.length() == 1) {
            responseFromServer = handleGuess.letterGuess(userInputFromClient.charAt(0));
        } else {
            responseFromServer = handleGuess.wordGuess(userInputFromClient);
        }

        return responseFromServer;
    }
}
