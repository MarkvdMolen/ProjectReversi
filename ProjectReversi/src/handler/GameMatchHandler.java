package handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.GameFW;

public class GameMatchHandler extends ActionHandler{
    GameFW gFW;

    public GameMatchHandler(GameFW gFW) {
        this.gFW = gFW;
    }

    public void run(String message){
        Pattern pattern = Pattern.compile("SVR GAME MATCH \\{PLAYERTOMOVE: \"(.*?)\", GAMETYPE: \"(.*?)\", OPPONENT: \"(.*?)\"\\}");
        Matcher matcher = pattern.matcher(message);

        String playerToMove;
        String gameType;
        String opponent;

        if (matcher.find()){
            playerToMove = matcher.group(1);
            gameType = matcher.group(2);
            opponent = matcher.group(3);
        }
        else{
        	playerToMove = null;
            gameType = null;
            opponent = null;
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                	gFW.pageController().getAlertView().hideAlert();
                    gFW.pageController().setGame(Class.forName("model." + gameType.replace("-", "")).getConstructor().newInstance(), playerToMove, opponent);
                } catch (Exception e) {
                    gFW.pageController().getAlertView().setAlert(Alert.AlertType.INFORMATION);
                    Alert alert = gFW.pageController().getAlertView().getAlert();
                    alert.setHeaderText(null);
                    alert.setTitle("Unknown game");
                    e.printStackTrace();
                    alert.setContentText("Can not find game: " +gameType);
                    alert.show();
                }
            }
        });
    }
}
