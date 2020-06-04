package agentchatbot;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AgentChatBot extends Application {

    ContainerController myContainer;
    public static TextArea textarea1;
    public static TextField text;

    public void start(Stage primaryStage) {//FX GUI
        textarea1 = new TextArea("");
        text = new TextField();
        Button send = new Button("send");
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.add(text, 0, 0);
        gridpane.add(textarea1, 0, 1);
        gridpane.add(send, 0, 2);
        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(gridpane);
        borderpane.setStyle(" -fx-background-image: url(\"p.JPG\");-fx-background-size: 800 500;");
        Scene scene = new Scene(borderpane, 800, 400, Color.WHITE);
        primaryStage.setTitle("Customer services app");
        primaryStage.setScene(scene);
        primaryStage.show();
        RecieveBehavior behave = new RecieveBehavior();
        send.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {//button action
                if (!text.getText().equals("")) {
                    new Human().setup();//send SMS
                    textarea1.appendText(text.getText() + "\n");
                    new RecieveBehavior().processSMS(text.getText());
                    text.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            new AgentChatBot().test();//run JADE GUI
            launch(args);//run my FX GUI
        } catch (Exception e) {
            System.out.println("main Error " + e.toString());
        }
    }

    public void test() {
        //Create the JADE envioenment
        Runtime myRuntime = Runtime.instance();
        Profile myProfile = new ProfileImpl();
        myContainer = myRuntime.createMainContainer(myProfile);
        //Call the RMA GUI
        try {
            AgentController rma = myContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
