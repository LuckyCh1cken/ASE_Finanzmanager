package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import scene_factory.Scene_Creator;
import valueobjects.VO_SpendingType;
import valueobjects.VO_Transaction;

import java.util.Date;
import java.util.Objects;

public class Main_Screen_Scene extends Basic_Scene{

    @FXML private TextField newTransactionValue;
    @FXML private ComboBox newSpendingType;
    @FXML private Label accountBalance;
    @FXML private Label accountName;

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

    @FXML
    public void initialize(){
        accountBalance.setText("Balance: " +
                String.valueOf(Basic_Scene.accountService.getAccount().getWallet().getBalance()) +
                "€");
        accountName.setText(super.accountService.getAccount().getAccountName().getName());

    }

    public void addTransaction(){

        if(newTransactionValue.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter transaction value", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        double value=Double.parseDouble(newTransactionValue.getText());

        if(newSpendingType.getValue() == (null)){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a spending type", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        VO_SpendingType spendingType = new VO_SpendingType(newSpendingType.getValue().toString());


        VO_Transaction newTransaction = new VO_Transaction(value , spendingType, new Date());
        super.accountService.addTransaction(newTransaction);
        accountBalance.setText("Balance: " +
                String.valueOf(super.accountService.getAccount().getWallet().getBalance())+
                "€");
    }

    public void transactionHistoryPressed(){
        Basic_Scene.sceneService.changeScene(Scene_Creator.SCENE.TRANSACTION_HISTORY);
    }

    public void logoutPress(){
        super.accountService.logout();
        Basic_Scene.sceneService.changeScene(Scene_Creator.SCENE.LOGIN);
    }
}
