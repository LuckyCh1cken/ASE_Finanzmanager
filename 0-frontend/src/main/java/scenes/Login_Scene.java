package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import scene_factory.Scene_Creator;

import java.util.Objects;

public class Login_Scene extends Basic_Scene{

    @FXML private TextField username;
    @FXML private PasswordField password;

    @Override
    public void init() {

    }

    @Override
    public void build() {
        System.out.println("Building Login Scene");

        try {

            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login_Screen.fxml")));

            super.scene = new Scene(root);

        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    public void loginPress(){


        if(super.accountService.login(username.getText(), password.getText())){
            Basic_Scene.sceneService.changeScene(Scene_Creator.SCENE.MAIN_SCREEN);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "user couldnt be logged in", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void registerPress(){
        if(super.accountService.register(username.getText(), password.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successful registration", ButtonType.OK);
            alert.showAndWait();

            System.out.println("new user registered");
            Basic_Scene.sceneService.changeScene(Scene_Creator.SCENE.MAIN_SCREEN);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "user couldn't be registered", ButtonType.OK);
            alert.showAndWait();

        }
    }
}
