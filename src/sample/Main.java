package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        VBox vbox = new VBox();
        HBox h1box = new HBox();
        HBox h2box = new HBox();
        HBox h3box = new HBox();

        vbox.getChildren().addAll(h1box,h2box,h3box);
        root.getChildren().add(vbox);


        Button btforward = new Button("Forward");
        h1box.getChildren().add(btforward);

        Button btleft = new Button("Left");
        h2box.getChildren().add(btleft);

        Button btright = new Button("Right");
        h2box.getChildren().add(btright);

        Button btBackwards = new Button("Backwards");
        h3box.getChildren().add(btBackwards);

        btforward.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        }


                String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("raspberrypi", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();

    }


    public static void main(
            String[] args) {
        launch(args);



    }
}
