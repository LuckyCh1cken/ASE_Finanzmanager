package scene_service;

import domainservices.Domain_Service_Account;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scene_factory.Scene_Creator;
import scene_factory.Scene_Factory;
import scenes.Basic_Scene;
import scenes.Login_Scene;

public class Scene_Service{

    private final Scene_Creator sceneFactory = new Scene_Factory();

    private Basic_Scene currentScene;

    private Stage mainStage;

    private final Domain_Service_Account accountService;

    public Scene_Service(Domain_Service_Account accountService) {
        this.accountService = accountService;
    }

    public void setMainStage(Stage newStage){
        this.mainStage = newStage;
    }

    public void changeScene(Scene_Creator.SCENE newScene) {
        currentScene = sceneFactory.getScene(newScene, this, accountService);
        mainStage.setScene(currentScene.getScene());
    }

}