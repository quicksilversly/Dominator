/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.controller.terminal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import com.xalero.dominion.IObserver;
import com.xalero.dominion.command.CommandHandler;
import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.model.GameSettings;
import com.xalero.dominion.model.SimplePlayerInfo;

import dominion.application.displays.KingdomCardsDisplay;
import dominion.application.displays.PlayerOptionsDisplay;
import dominion.application.displays.PlayerTurnsDisplay;
import dominion.application.displays.SupplyPilesDisplay;

/**
 *
 * @author jonathan
 */
public class TerminalController extends AnchorPane implements IObserver {
    
    private static final Logger log = Logger.getLogger(TerminalController.class.getCanonicalName());
    
    @FXML
    private TextArea gameOutput;
    @FXML
    private TextField gameInput;
    @FXML
    private KingdomCardsDisplay kingdomCardsDisplay;
    @FXML
    private SupplyPilesDisplay supplyPilesDisplay;
    @FXML 
    private PlayerTurnsDisplay playerTurnsDisplay;
    @FXML
    private PlayersCardsController playersCardsController;
    @FXML
    private PlayerOptionsDisplay playerOptionsDisplay;
    @FXML
    private DiscardPileController discardPileController;
    
    private CommandHandler commandHandler;
    private long playerId;
    
    private final DominionModel dominionModel;
    
    public TerminalController(GameSettings gameSettings) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("terminal_controller.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            log.log(Level.WARNING, "Error loading fxml for Terminal Controller", e);
        }
        
        gameOutput.setEditable(false);
        gameOutput.appendText("Welcome to the Game");
        
        playerId = gameSettings.getCurrentPlayerId();
        
        dominionModel = new DominionModel(gameSettings);
        dominionModel.registerObserver(this);
        
        commandHandler = new CommandHandler(gameOutput, dominionModel);
        
        kingdomCardsDisplay.initController(dominionModel);
        supplyPilesDisplay.initController(dominionModel);
        playerTurnsDisplay.initController(dominionModel);
        playersCardsController.initController(dominionModel, playerId);
        playerOptionsDisplay.initController(dominionModel, playerId);
        discardPileController.initController(dominionModel);    
        
        gameOutput.setFocusTraversable(false);
        gameInput.setFocusTraversable(true);
        gameInput.requestFocus();
    }
    
    @FXML
    private void keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            handleUserInput(gameInput.getText());
            gameInput.clear();
        }
    }
    
    private void handleUserInput(String text) {
    	commandHandler.handleUserInput(text, playerId);
    }
    
    public long getCurrentPlayerId() {
    	return playerId;
    }

    @Override
    public void update() {
        
    }
}