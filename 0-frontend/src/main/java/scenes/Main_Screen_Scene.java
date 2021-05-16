package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import scene_factory.Scene_Creator;
import valueobjects.VO_Transaction;

import java.util.Objects;

public class Main_Screen_Scene extends Basic_Scene{

    @FXML private TextField newTransactionValue;
    @FXML private TextField newTransactionDescription;

    @Override
    public void init() {

    }

    @Override
    public void build() {
        System.out.println("Building Main Scene");

        try {

            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main_Screen.fxml")));

            super.scene = new Scene(root);

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void addTransaction(){

        //VO_Transaction newTransaction = new VO_Transaction()
        //super.accountService.addTransaction();
    }

    public void transactionHistoryPressed(){
        Basic_Scene.sceneService.changeScene(Scene_Creator.SCENE.TRANSACTION_HISTORY);
    }

}
