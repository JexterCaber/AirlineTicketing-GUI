package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;


public class Main extends Application {

    Stage window;
    Scene scene1, scene2;

    String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rng = new Random();

    static String[] ageArray;

    public static void main(String[] args) {
        launch(args);
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        //SCENE1

        //LOGO
        Image logo = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\sample\\Logo.png");
        ImageView imageview = new ImageView(logo);
        imageview.setFitWidth(500);
        imageview.setPreserveRatio(true);

        //SET LAYOUT
        BorderPane root1 = new BorderPane();
        scene1 = new Scene(root1, 500, 500);

        Button button = new Button("Book Now!");
        button.setFont(f);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.setScene(scene2);
            }
        });

        //CREATE VBOX
        VBox vboxlogo = new VBox();
        vboxlogo.setAlignment(Pos.CENTER);
        vboxlogo.getChildren().addAll(imageview,button);

        root1.setCenter(vboxlogo);
        root1.setBackground(Background.fill(Color.rgb(0,180,216)));

        /////////////////////////////////////////////////////////////

        //SCENE2

        //SET LAYOUT
        BorderPane root2 = new BorderPane();
        scene2 = new Scene(root2,500,500);

        //INITIALIZE LABELS
        Label rgc = new Label(generateString(rng,characters,5));
        Label firstName = new Label("First Name:");
        Label lastName = new Label("Last Name:");
        Label age = new Label("Age");
        Label aptLabel = new Label("Airplane Type");

        rgc.setFont(f);
        firstName.setFont(f);
        lastName.setFont(f);
        age.setFont(f);
        aptLabel.setFont(f);

        //INITIALIZE TEXTFIELDS
        TextField firstNameText = new TextField();
        TextField lastNameText = new TextField();

        //CREATE CHOICEBOX FOR AGE
        ageArray = new String[101];

        for(int a = 1; a<101; a++) {
            ageArray[a] = Integer.toString(a);
        }
        ChoiceBox<String> c = new ChoiceBox<String>(FXCollections.observableArrayList(ageArray));

        //VBOX FOR AGE AND CB
        VBox agebox = new VBox();
        agebox.setPadding(new Insets(5));
        agebox.setSpacing(5);

        agebox.getChildren().addAll(age,c);

        //CREATE CHOICEBOX FOR AIRPLANE TYPE
        String[] airplaneType = {"Private","Business","Regular"};
        ChoiceBox<String> apT = new ChoiceBox<String>(FXCollections.observableArrayList(airplaneType));

        //VBOX FOR APT AND CB
        VBox aptBox = new VBox();
        aptBox.setPadding(new Insets(5));
        aptBox.setSpacing(5);

        aptBox.getChildren().addAll(aptLabel,apT);

        //RADIOBUTTONS
        RadioButton r1 = new RadioButton("Travel Insurance");
        r1.setFont(f);
        RadioButton r2 = new RadioButton("Round Trip");
        r2.setFont(f);

        //HBOX
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5));
        hbox.setSpacing(5);

        hbox.getChildren().addAll(agebox,aptBox,r1,r2);
        hbox.setAlignment(Pos.CENTER);

        //BUTTON
        Button nextButton = new Button("Next");
        nextButton.setFont(f);
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);
        //confirmButton.setFont(Font.font("Poppins", FontWeight.BOLD,20));
        //confirmButton.setMinSize(50,30);

        //HBOX FOR BUTTON
        HBox buttonHbox = new HBox();
        buttonHbox.setPadding(new Insets(5));
        buttonHbox.setSpacing(5);

        buttonHbox.getChildren().addAll(nextButton,confirmButton);
        buttonHbox.setAlignment(Pos.BOTTOM_CENTER);

        //CREATE VBOX
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5));
        vbox.setSpacing(5);

        vbox.getChildren().addAll(rgc,firstName,firstNameText,lastName,lastNameText);

        //CREATE PARENT VBOX
        VBox parentVbox = new VBox();
        parentVbox.setPadding(new Insets(5));
        parentVbox.setSpacing(5);

        parentVbox.getChildren().addAll(vbox,hbox);
        parentVbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        VBox leftArea = new VBox();
        VBox rightArea = new VBox();

        //CREATE HBOX TO ADD MARGIN
        HBox parentHbox = new HBox();
        parentHbox.getChildren().addAll(leftArea,parentVbox,rightArea);
        parentHbox.setAlignment(Pos.CENTER);

        HBox.setMargin(leftArea, new Insets(20,0,10,10));
        HBox.setMargin(parentVbox, new Insets(20,10,10,10));
        HBox.setMargin(rightArea, new Insets(20,10,10,0));

        root2.setTop(parentHbox);
        root2.setCenter(buttonHbox);
        root2.setBackground(Background.fill(Color.rgb(0,180,216)));

        window.setResizable(false);
        window.initStyle(StageStyle.DECORATED);
        window.setScene(scene1);
        window.setTitle("Airline Ticketing System");
        window.show();

    }
}
