package scene_factory;

import domainservices.Domain_Service_Account;
import javafx.scene.Scene;
import scene_service.Scene_Service;
import scenes.Basic_Scene;
import services.AccountManager;

import java.util.List;

public abstract class Scene_Creator {

    public enum SCENE {
        MAIN_SCREEN,
        TRANSACTION_HISTORY,
        LOGIN
    }

    public Basic_Scene getScene(SCENE sceneName, Scene_Service sceneService, Domain_Service_Account accountService) {
        Basic_Scene newScene = createBoggleScene(sceneName);
        return finalizeScene(newScene, sceneService, accountService);
    }

    protected abstract Basic_Scene createBoggleScene(SCENE sceneName);

    private Basic_Scene finalizeScene(Basic_Scene scene, Scene_Service sceneService, Domain_Service_Account accountService) {
        scene.setSceneService(sceneService);
        scene.setAccountService(accountService);

        scene.init();
        scene.build();

        return scene;
    }
}