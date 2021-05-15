package scene_factory;

import scenes.Basic_Scene;
import scenes.Login_Scene;

import java.util.List;

public class Scene_Factory extends Scene_Creator{

    @Override
    protected Basic_Scene createBoggleScene(SCENE sceneName) {

        switch (sceneName) {
            //case MAIN_SCREEN: -> new Main_Screen_Scene();
            case LOGIN: return new Login_Scene();
            //case ADD_TRANSACTION: -> new Add_Transaction_Scene();
            default : throw new RuntimeException(sceneName + " throws runtime error");
        }
    }
}