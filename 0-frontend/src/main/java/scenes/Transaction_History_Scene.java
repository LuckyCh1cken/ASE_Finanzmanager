package scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import scene_factory.Scene_Creator;
import valueobjects.VO_Transaction;

import java.util.*;

public class Transaction_History_Scene extends Basic_Scene{

    @FXML private ListView transactionHistory;
    private List<String> transactions = new ArrayList<>();
    ObservableList observableList = FXCollections.observableArrayList();


    @Override
    public void init() {

    }

    @Override
    public void build() {
        System.out.println("Building Transaction History Scene");

        try {

            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Transaction_History.fxml")));

            super.scene = new Scene(root);


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
       setListView();
    }

    public void returnToMainScreen(){
        Basic_Scene.sceneService.changeScene(Scene_Creator.SCENE.MAIN_SCREEN);
    }

    public void setListView()
    {

        super.accountService.getAccount().getWallet().getTransactionHistory().forEach(transaction -> {
            transactions.add(String.valueOf(transaction.getValue()) + "€, " + transaction.getSpendingType().getSpendingType() + ", " + transaction.getDate());
        });

        Collections.reverse(transactions);

        observableList.setAll(transactions);
        transactionHistory.setItems(observableList);

    }

}
