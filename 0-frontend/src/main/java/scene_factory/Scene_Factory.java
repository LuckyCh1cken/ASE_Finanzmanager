package scene_factory;

import scenes.Basic_Scene;
import scenes.Login_Scene;
import scenes.Main_Screen_Scene;
import scenes.Transaction_History_Scene;

import java.util.List;

public class Scene_Factory extends Scene_Creator{

    @Override
    protected Basic_Scene createBasicScene(SCENE sceneName) {

        switch (sceneName) {
            case MAIN_SCREEN:return new Main_Screen_Scene();
            case LOGIN: return new Login_Scene();
            case TRANSACTION_HISTORY: return new Transaction_History_Scene();
            default : throw new RuntimeException(sceneName + " throws runtime error");
        }
    }
}