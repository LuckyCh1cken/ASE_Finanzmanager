package scenes;

import domainservices.Domain_Service_Account;
import javafx.scene.Scene;
import scene_service.Scene_Service;

public abstract class Basic_Scene {
    protected static Scene_Service sceneService;
    protected Scene scene;
    protected static Domain_Service_Account accountService;

    public void setSceneService(Scene_Service sceneService) {
        if(Basic_Scene.sceneService == null)
            Basic_Scene.sceneService = sceneService;
    }

    public void setAccountService(Domain_Service_Account accountService) {
            this.accountService = accountService;
    }

    abstract public void init();
    abstract public void build();

    public javafx.scene.Scene getScene() {
        return this.scene;
    }
}
