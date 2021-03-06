package com.xalero.dominion.controller.settings;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.xalero.dominion.manager.IViewManager;
import com.xalero.dominion.server.dao.InitializationDAO;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MainController extends BorderPane implements IViewManager {
	
	private static final Logger log = LogManager.getLogManager().getLogger(MainController.class.getName());
	
	@FXML private MenubarController menubarController;
	@FXML private LoginController loginController;
	@FXML private SplitPane splitPane;
	
	private DashboardController dashboardController;
	
	private Pane currentController;
	
	public MainController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			log.log(Level.WARNING, "Error loading fxml", e);
		}
		
		dashboardController = new DashboardController(this);
		menubarController.setViewManager(this);
		loginController.setViewManager(this);
		currentController = loginController;
		
		this.authenticated();
		
		try (InitializationDAO initDao = new InitializationDAO()) {
			initDao.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void authenticated() {
		this.getChildren().remove(splitPane);
		this.setCenter(dashboardController);
	}

	@Override
	public void startGame() {
	}

	@Override
	public void exitGame() {
	}

	@Override
	public void logout() {
		this.getChildren().remove(dashboardController);
		this.setCenter(splitPane);
		menubarController.requestLayout();
		splitPane.requestLayout();
		this.requestLayout();
	}
	
	public void exitApplication() {
		Platform.exit();
	}
	
	public void setDisplay(AnchorPane pane) { }
	
	public void requestFocus() {
		currentController.requestFocus();
	}

}
