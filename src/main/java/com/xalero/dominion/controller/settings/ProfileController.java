package com.xalero.dominion.controller.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.xalero.dominion.manager.UserManager;
import com.xalero.dominion.server.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfileController extends AnchorPane {

	@FXML
	private ImageView profileImageView;
	@FXML
	private Button changePhotoButton;
	@FXML
	private TextArea descriptionArea;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField confirmPasswordField;

	public ProfileController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"profile_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		initView();
	}

	private void initView() {
		UserManager userManager = UserManager.INSTANCE;
		User user = userManager.getCurrentUser();
		usernameField.setText(user.getUsername());
		passwordField.setText(user.getPassword());
		confirmPasswordField.setText(user.getPassword());
		descriptionArea.setText(user.getDescription());
	}

	public void changePhotoClicked(MouseEvent evt) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File userImageFile = fileChooser.showOpenDialog(new Stage());
		Image userImage = null;
		try {
			if (userImageFile != null) {
				userImage = new Image(new FileInputStream(userImageFile));
				// userManager.setUserImage(profileView.getUsername(),
				// userImage);
				profileImageView.setImage(userImage);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void changePhotoEntered(KeyEvent evt) {
		if (!evt.getCode().equals(KeyCode.ENTER)) {
			return;
		}
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File userImageFile = fileChooser.showOpenDialog(new Stage());
		Image userImage = null;
		try {
			if (userImageFile != null) {
				userImage = new Image(new FileInputStream(userImageFile));
				// userManager.setUserImage(profileView.getUsername(),
				// userImage);
				profileImageView.setImage(userImage);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void saveClicked(MouseEvent evt) {
		UserManager.INSTANCE.saveUserProfile(profileImageView.getImage(),
				usernameField.getText(), passwordField.getText(),
				descriptionArea.getText());
	}

	public void saveEntered(KeyEvent evt) {
		if (evt.getCode().equals(KeyCode.ENTER)) {
			UserManager.INSTANCE.saveUserProfile(profileImageView.getImage(),
					usernameField.getText(), passwordField.getText(),
					descriptionArea.getText());
		}
	}
}
