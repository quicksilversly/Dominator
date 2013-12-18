package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import dominion.application.model.SingleGameSettings;

public class SingleGameSetupController extends AnchorPane {

	@FXML private MainOverviewTab mainOverviewTab;
	@FXML private PlayersTab playersTab;
	@FXML private BaseTab baseCardsTab;
	@FXML private Tab cardsTab;
	@FXML private CardOverviewTab cardOverviewTab;
	
	@FXML private Tab intrigueTab;
	@FXML private Tab hinterlandsTab;
	@FXML private Tab cornucopiaTab;
	@FXML private Tab alchemyTab;
	@FXML private Tab darkAgesTab;

	private SingleGameSettings gameSettings; 
	
	public SingleGameSetupController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single_game_setup_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.gameSettings = SingleGameSettings.INSTANCE;
		this.mainOverviewTab.initializeController(this.gameSettings);
		this.playersTab.initializeController(this.gameSettings);
		this.cardOverviewTab.initializeController(this.gameSettings);
		this.baseCardsTab.initializeController(this.gameSettings);
	}
}
