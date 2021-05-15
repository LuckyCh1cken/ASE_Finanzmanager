import domainservices.Domain_Service_Account;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repositories.Repository_Account;
import scene_factory.Scene_Creator;
import scene_service.Scene_Service;
import services.AccountManager;

public class Finanzmanager extends Application  {

    private Stage mainStage;
    private static Scene_Service sceneService;

    public static void main(String[] args) {

        Repository_Account accounts = new Repository_Bridge_Account();
        Domain_Service_Account accountService = new AccountManager(accounts);
        sceneService = new Scene_Service(accountService);
        launch(args);

    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        //Image icon = new Image("/smallIcon.png");
        //mainStage.getIcons().add(icon);

        mainStage.setTitle("Finanzmanager");
        mainStage.setResizable(false);

       this.sceneService.setMainStage(mainStage);
       this.sceneService.changeScene(Scene_Creator.SCENE.LOGIN);
        mainStage.show();
    }


}
