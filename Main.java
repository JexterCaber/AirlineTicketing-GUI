package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Main extends Application {

    int a = 0;

    Stage window;
    Scene scene1, scene2, scene3, scene4;

    Label rgc;
    Label firstName;
    Label lastName;
    Label passengerID;
    Label passengerFullName;
    Label passengerAge;
    Label passengerFlightType;
    Label passengerTravelTax;
    Label passengerFare = new Label("");

    TextField firstNameText;
    TextField lastNameText;

    Image manila;
    Image batanes;
    Image palawan;
    Image skor;
    Image japan;
    Image vietnam;
    Image black;

    ImageView topImageView;
    ImageView bottomImageView;

    RadioButton r1;
    RadioButton r2;

    RadioButton fromManila;
    RadioButton fromBatanes;
    RadioButton fromPalawan;
    RadioButton fromSouthKorea;
    RadioButton fromJapan;
    RadioButton fromVietnam;

    RadioButton toManila;
    RadioButton toBatanes;
    RadioButton toPalawan;
    RadioButton toSouthKorea;
    RadioButton toJapan;
    RadioButton toVietnam;

    ToggleGroup fromTG;
    ToggleGroup toTG;

    VBox vbox;

    ChoiceBox<String> c;
    ChoiceBox<String> apT;

    String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rng = new Random();

    String ID;
    String Last_Name;
    String First_Name;
    String Age;
    String Airplane_Type;
    String Travel_Insurance = "false";
    String Round_Trip = "false";

    String[] IDARRAY = new String[1];


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


    public void nextPassenger() {

        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        //SET LAYOUT
        BorderPane root3 = new BorderPane();
        scene2 = new Scene(root3,500,300);

        //INITIALIZE LABELS
        rgc = new Label(generateString(rng,characters,5));
        firstName = new Label("First Name:");
        lastName = new Label("Last Name:");
        Label age = new Label("Age");
        Label aptLabel = new Label("Airplane Type");

        rgc.setFont(f);
        firstName.setFont(f);
        lastName.setFont(f);
        age.setFont(f);
        aptLabel.setFont(f);

        //INITIALIZE TEXTFIELDS
        firstNameText = new TextField();
        lastNameText = new TextField();

        //CREATE CHOICEBOX FOR AGE
        ageArray = new String[101];

        for(int a = 1; a<101; a++) {
            ageArray[a] = Integer.toString(a);
        }
        c = new ChoiceBox<String>(FXCollections.observableArrayList(ageArray));

        //VBOX FOR AGE AND CB
        VBox agebox = new VBox();
        agebox.setPadding(new Insets(5));
        agebox.setSpacing(5);

        agebox.getChildren().addAll(age,c);

        //CREATE CHOICEBOX FOR AIRPLANE TYPE
        final String[] airplaneType = {"Private","Business","Regular"};
        apT = new ChoiceBox<String>(FXCollections.observableArrayList(airplaneType));

        //VBOX FOR APT AND CB
        VBox aptBox = new VBox();
        aptBox.setPadding(new Insets(5));
        aptBox.setSpacing(5);

        aptBox.getChildren().addAll(aptLabel,apT);

        //RADIOBUTTONS
        r1 = new RadioButton("Travel Insurance");
        r1.setFont(f);
        r2 = new RadioButton("Round Trip");
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
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(r1.isSelected()){
                    Travel_Insurance = "true";
                } else {
                    Travel_Insurance = "false";
                }

                if(r2.isSelected()){
                    Round_Trip = "true";
                } else {
                    Round_Trip = "false";
                }

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/airline_data", "root", "");
                    Statement stat = conn.createStatement();
                    String sm = "INSERT INTO passenger_information VALUES(?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sm);
                    preparedStatement.setString(1, ID = rgc.getText());
                    preparedStatement.setString(2, First_Name = firstNameText.getText());
                    preparedStatement.setString(3, Last_Name = lastNameText.getText());
                    preparedStatement.setString(4, Age = c.getValue());
                    preparedStatement.setString(5, Airplane_Type = apT.getValue());
                    preparedStatement.setString(6, String.valueOf(Travel_Insurance));
                    preparedStatement.setString(7, String.valueOf(Round_Trip));
                    preparedStatement.executeUpdate();
                    stat.close();

                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(IDARRAY));
                    arrayList.add(rgc.getText());
                    IDARRAY = arrayList.toArray(IDARRAY);

                    System.out.println(Arrays.toString(IDARRAY));

                    nextPassenger();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(r1.isSelected()){
                    Travel_Insurance = "true";
                } else {
                    Travel_Insurance = "false";
                }

                if(r2.isSelected()){
                    Round_Trip = "true";
                } else {
                    Round_Trip = "false";
                }

               try {
                   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/airline_data", "root", "");
                   Statement stat = conn.createStatement();
                   String sm = "INSERT INTO passenger_information VALUES(?,?,?,?,?,?,?)";
                   PreparedStatement preparedStatement = conn.prepareStatement(sm);
                   preparedStatement.setString(1, ID = rgc.getText());
                   preparedStatement.setString(2, First_Name = firstNameText.getText());
                   preparedStatement.setString(3, Last_Name = lastNameText.getText());
                   preparedStatement.setString(4, Age = c.getValue());
                   preparedStatement.setString(5, Airplane_Type = apT.getValue());
                   preparedStatement.setString(6, String.valueOf(Travel_Insurance));
                   preparedStatement.setString(7, String.valueOf(Round_Trip));
                   preparedStatement.executeUpdate();
                   stat.close();

                   ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(IDARRAY));
                   arrayList.add(rgc.getText());
                   IDARRAY = arrayList.toArray(IDARRAY);

                   System.out.println(Arrays.toString(IDARRAY));

                   fromToLocation();
               } catch (Exception e){
                   e.printStackTrace();
               }

                fromToLocation();
            }
        });
        //confirmButton.setFont(Font.font("Poppins", FontWeight.BOLD,20));
        //confirmButton.setMinSize(50,30);

        //HBOX FOR BUTTON
        HBox buttonHbox = new HBox();
        buttonHbox.setPadding(new Insets(5));
        buttonHbox.setSpacing(5);

        buttonHbox.getChildren().addAll(nextButton,confirmButton);
        buttonHbox.setAlignment(Pos.BOTTOM_CENTER);

        //CREATE VBOX
        vbox = new VBox();
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

        root3.setTop(parentHbox);
        root3.setCenter(buttonHbox);
        root3.setBackground(Background.fill(Color.rgb(0,180,216)));

        window.setScene(scene2);
    }

    public void fromToLocation() {
        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        //SET LAYOUT
        BorderPane root4 = new BorderPane();
        scene3 = new Scene(root4,900,350);

        //CREATE FROM RADIOBUTTONS
        fromManila = new RadioButton("Manila");
        fromBatanes = new RadioButton("Batanes");
        fromPalawan = new RadioButton("Palawan");
        fromSouthKorea = new RadioButton("South Korea");
        fromJapan = new RadioButton("Japan");
        fromVietnam = new RadioButton("Vietnam");

        //CREATE TO RADIOBUTTONS
        toManila = new RadioButton("Manila");
        toBatanes = new RadioButton("Batanes");
        toPalawan = new RadioButton("Palawan");
        toSouthKorea = new RadioButton("South Korea");
        toJapan = new RadioButton("Japan");
        toVietnam = new RadioButton("Vietnam");

        //SET FONT OF RADIO BUTTONS
        fromManila.setFont(f);
        fromBatanes.setFont(f);
        fromPalawan.setFont(f);
        fromSouthKorea.setFont(f);
        fromJapan.setFont(f);
        fromVietnam.setFont(f);

        toManila.setFont(f);
        toBatanes.setFont(f);
        toPalawan.setFont(f);
        toSouthKorea.setFont(f);
        toJapan.setFont(f);
        toVietnam.setFont(f);

        //CREATE TOGGLE GROUP FROM
        fromTG = new ToggleGroup();

        fromManila.setToggleGroup(fromTG);
        fromBatanes.setToggleGroup(fromTG);
        fromPalawan.setToggleGroup(fromTG);
        fromSouthKorea.setToggleGroup(fromTG);
        fromJapan.setToggleGroup(fromTG);
        fromVietnam.setToggleGroup(fromTG);

        //CREATE TOGGLE GROUP TO
        toTG = new ToggleGroup();

        toManila.setToggleGroup(toTG);
        toBatanes.setToggleGroup(toTG);
        toPalawan.setToggleGroup(toTG);
        toSouthKorea.setToggleGroup(toTG);
        toJapan.setToggleGroup(toTG);
        toVietnam.setToggleGroup(toTG);

        //CREATE VBOX FOR FROM
        VBox fromTGVBox = new VBox();
        fromTGVBox.setPadding(new Insets(5));
        fromTGVBox.setSpacing(5);

        fromTGVBox.getChildren().addAll(fromManila,fromBatanes,fromPalawan,fromSouthKorea,fromJapan,fromVietnam);
        fromTGVBox.setAlignment(Pos.BASELINE_LEFT);

        //CREATE VBOX FOR TO
        VBox toTGVBox = new VBox();
        toTGVBox.setPadding(new Insets(5,10,5,10));
        toTGVBox.setSpacing(5);

        toTGVBox.getChildren().addAll(toManila,toBatanes,toPalawan,toSouthKorea,toJapan,toVietnam);
        toTGVBox.setAlignment(Pos.BASELINE_LEFT);

        //CREATE BUTTON FOR CONFIRM
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showPassengerInfo();
            }
        });

        //CREATE HBOX FOR FROMVBOX AND TOVBOX
        HBox container = new HBox();
        container.setPadding(new Insets(5,10,5,10));
        container.setSpacing(5);

        container.getChildren().addAll(fromTGVBox,toTGVBox);
        container.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        HBox.setMargin(fromTGVBox,new Insets(5,10,5,10));
        HBox.setMargin(toTGVBox, new Insets(5,10,5,10));

        //CREATE VBOX TO ADD CONFIRM BUTTON
        VBox addButtonVBox = new VBox();
        addButtonVBox.setPadding(new Insets(5));
        addButtonVBox.setSpacing(5);

        addButtonVBox.getChildren().addAll(container,confirmButton);

        //IMAGE
        manila = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\manila.png");
        batanes = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\batanes.png");
        palawan = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\palawan.png");
        skor = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\skor.png");
        japan = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\japan.png");
        vietnam = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\vietnam.png");
        black = new Image("C:\\Users\\user\\TicketingSystem-GUI\\src\\black.png");

        topImageView = new ImageView();
        topImageView.setFitWidth(250);
        topImageView.setPreserveRatio(true);

        bottomImageView = new ImageView();
        bottomImageView.setFitWidth(250);
        bottomImageView.setPreserveRatio(true);

        //CREATE VBOX FOR IMAGEVIEWS
        HBox imageViewHBox = new HBox();
        imageViewHBox.setPadding(new Insets(5));
        imageViewHBox.setSpacing(5);

        imageViewHBox.getChildren().addAll(topImageView,bottomImageView);

        //CREATE PARENT HBoX
        HBox parentHBox = new HBox();
        parentHBox.setPadding(new Insets(5));
        parentHBox.setSpacing(5);

        parentHBox.getChildren().addAll(addButtonVBox,imageViewHBox);

        fromTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) fromTG.getSelectedToggle();

                //CHANGE TOPIMAGEVIEW IMAGE
                if (rb.getText().equalsIgnoreCase("Manila")) {
                    topImageView.setImage(manila);
                    toManila.setDisable(true);
                    toBatanes.setDisable(false);
                    toPalawan.setDisable(false);
                    toSouthKorea.setDisable(false);
                    toJapan.setDisable(false);
                    toVietnam.setDisable(false);

                    if (toManila.isSelected()) {
                        bottomImageView.setImage(black);
                    }
                    toManila.setSelected(false);
                } else if (rb.getText().equalsIgnoreCase("Batanes")) {
                    topImageView.setImage(batanes);
                    toManila.setDisable(false);
                    toBatanes.setDisable(true);
                    toPalawan.setDisable(true);
                    toSouthKorea.setDisable(true);
                    toJapan.setDisable(true);
                    toVietnam.setDisable(true);

                    if (toBatanes.isSelected()) {
                        bottomImageView.setImage(black);
                    }
                    toBatanes.setSelected(false);
                } else if (rb.getText().equalsIgnoreCase("Palawan")) {
                    topImageView.setImage(palawan);
                    toManila.setDisable(false);
                    toBatanes.setDisable(true);
                    toPalawan.setDisable(true);
                    toSouthKorea.setDisable(true);
                    toJapan.setDisable(true);
                    toVietnam.setDisable(true);

                    if (toPalawan.isSelected()) {
                        bottomImageView.setImage(black);
                    }
                    toPalawan.setSelected(false);
                } else if (rb.getText().equalsIgnoreCase("South Korea")) {
                    topImageView.setImage(skor);
                    toManila.setDisable(false);
                    toBatanes.setDisable(true);
                    toPalawan.setDisable(true);
                    toSouthKorea.setDisable(true);
                    toJapan.setDisable(true);
                    toVietnam.setDisable(true);

                    if (toSouthKorea.isSelected()) {
                        bottomImageView.setImage(black);
                    }
                    toSouthKorea.setSelected(false);
                } else if (rb.getText().equalsIgnoreCase("Japan")) {
                    topImageView.setImage(japan);
                    toManila.setDisable(false);
                    toBatanes.setDisable(true);
                    toPalawan.setDisable(true);
                    toSouthKorea.setDisable(true);
                    toJapan.setDisable(true);
                    toVietnam.setDisable(true);

                    if (toJapan.isSelected()) {
                        bottomImageView.setImage(black);
                    }
                    toJapan.setSelected(false);
                } else if (rb.getText().equalsIgnoreCase("Vietnam")) {
                    topImageView.setImage(vietnam);
                    toManila.setDisable(false);
                    toBatanes.setDisable(true);
                    toPalawan.setDisable(true);
                    toSouthKorea.setDisable(true);
                    toJapan.setDisable(true);
                    toVietnam.setDisable(true);

                    if (toVietnam.isSelected()) {
                        bottomImageView.setImage(black);
                    }
                    toVietnam.setSelected(false);
                }
            }
        });

        toTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) toTG.getSelectedToggle();

                //CHANGE BOTTOMIMAGEVIEW IMAGE
                if (rb.getText().equalsIgnoreCase("Manila")) {
                    bottomImageView.setImage(manila);
                } else if (rb.getText().equalsIgnoreCase("Batanes")) {
                    bottomImageView.setImage(batanes);
                } else if (rb.getText().equalsIgnoreCase("Palawan")) {
                    bottomImageView.setImage(palawan);
                } else if (rb.getText().equalsIgnoreCase("South Korea")) {
                    bottomImageView.setImage(skor);
                } else if (rb.getText().equalsIgnoreCase("Japan")) {
                    bottomImageView.setImage(japan);
                } else if (rb.getText().equalsIgnoreCase("Vietnam")) {
                    bottomImageView.setImage(vietnam);
                }
            }
        });

        //SET MARGIN
        HBox.setMargin(container, new Insets(20,0,20,20));
        HBox.setMargin(imageViewHBox, new Insets(20,20,20,0));

        //CENTER
        VBox centerVbox = new VBox();
        centerVbox.setPadding(new Insets(5));
        centerVbox.setSpacing(5);

        centerVbox.getChildren().add(parentHBox);
        centerVbox.setAlignment(Pos.CENTER);

        root4.setBackground(Background.fill(Color.rgb(0,180,216)));
        root4.setCenter(centerVbox);

        window.setScene(scene3);
    }

    public void showPassengerInfo() {
        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        BorderPane root5 = new BorderPane();
        scene4 = new Scene(root5,500,500);

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/airline_data","root","");

            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM passenger_information WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,IDARRAY[a+1]);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                ID = resultSet.getString("ID");
                First_Name = resultSet.getString("First_Name");
                Last_Name = resultSet.getString("Last_Name");
                Age = resultSet.getString("Age");
                Airplane_Type = resultSet.getString("Airplane_Type");
                Travel_Insurance = resultSet.getString("Travel_Insurance");
                Round_Trip = resultSet.getString("Round_Trip");
            }

            stat.close();
            conn.close();
        }catch (Exception e){
            System.out.println("ERROR GAGO");
        }

        passengerID = new Label(ID);
        passengerFullName = new Label(First_Name + " " + Last_Name );
        passengerAge = new Label(Age);
        passengerFlightType = new Label(Airplane_Type);

        if (Airplane_Type.equalsIgnoreCase("Private")) {
            passengerTravelTax = new Label("4,260.00");
        } else if (Airplane_Type.equalsIgnoreCase("Business")) {
            passengerTravelTax = new Label("5,700.00");
        } else {
            passengerTravelTax = new Label("2,500.00");
        }

        if(Airplane_Type.equalsIgnoreCase("Private")) {
            if (fromManila.isSelected() && toBatanes.isSelected()) {
                passengerFare.setText("8,000.00");
            } else if (fromManila.isSelected() && toPalawan.isSelected()) {
                passengerFare.setText("9,100.00");
            } else if (fromBatanes.isSelected() && toManila.isSelected()) {
                passengerFare.setText("9,800.00");
            } else if (fromPalawan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("9,850.00");
            } else if (fromManila.isSelected() && toSouthKorea.isSelected()) {
                passengerFare.setText("27,450.00");
            } else if (fromSouthKorea.isSelected() && toManila.isSelected()) {
                passengerFare.setText("30,890.00");
            } else if (fromManila.isSelected() && toJapan.isSelected()) {
                passengerFare.setText("40,450.00");
            } else if (fromJapan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("43,855.00");
            } else if (fromManila.isSelected() && toVietnam.isSelected()) {
                passengerFare.setText("8,505.00");
            } else if (fromVietnam.isSelected() && toManila.isSelected()) {
                passengerFare.setText("14,300.00");
            }
        } else if (Airplane_Type.equalsIgnoreCase("Business")){
            if (fromManila.isSelected() && toBatanes.isSelected()) {
                passengerFare.setText("12,500.00");
            } else if (fromManila.isSelected() && toPalawan.isSelected()) {
                passengerFare.setText("12,950.00");
            } else if (fromBatanes.isSelected() && toManila.isSelected()) {
                passengerFare.setText("10,500.00");
            } else if (fromPalawan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("10,975.00");
            } else if (fromManila.isSelected() && toSouthKorea.isSelected()) {
                passengerFare.setText("37,390.00");
            } else if (fromSouthKorea.isSelected() && toManila.isSelected()) {
                passengerFare.setText("39,650.00");
            } else if (fromManila.isSelected() && toJapan.isSelected()) {
                passengerFare.setText("45,355.00");
            } else if (fromJapan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("49,780.00");
            } else if (fromManila.isSelected() && toVietnam.isSelected()) {
                passengerFare.setText("12,345.00");
            } else if (fromVietnam.isSelected() && toManila.isSelected()) {
                passengerFare.setText("16,320.00");
            }
        } else if (Airplane_Type.equalsIgnoreCase("Regular")) {
            if (fromManila.isSelected() && toBatanes.isSelected()) {
                passengerFare.setText("3,500.00");
            } else if (fromManila.isSelected() && toPalawan.isSelected()) {
                passengerFare.setText("3,900.00");
            } else if (fromBatanes.isSelected() && toManila.isSelected()) {
                passengerFare.setText("3,200.00");
            } else if (fromPalawan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("3,575.00");
            } else if (fromManila.isSelected() && toSouthKorea.isSelected()) {
                passengerFare.setText("12,055.00");
            } else if (fromSouthKorea.isSelected() && toManila.isSelected()) {
                passengerFare.setText("13,100.00");
            } else if (fromManila.isSelected() && toJapan.isSelected()) {
                passengerFare.setText("27,800.00");
            } else if (fromJapan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("29,400.00");
            } else if (fromManila.isSelected() && toVietnam.isSelected()) {
                passengerFare.setText("3,200.00");
            } else if (fromVietnam.isSelected() && toManila.isSelected()) {
                passengerFare.setText("4,600.00");
            }
        }

        //SET FONT
        passengerID.setFont(f);
        passengerFullName.setFont(f);
        passengerAge.setFont(f);
        passengerFlightType.setFont(f);
        passengerTravelTax.setFont(f);
        passengerFare.setFont(f);

        //CREATE VBOX
        VBox passengerVBox = new VBox();
        passengerVBox.setPadding(new Insets(5));
        passengerVBox.setSpacing(5);

        passengerVBox.getChildren().addAll(passengerID,passengerFullName,passengerAge,passengerFlightType,passengerTravelTax,passengerFare);
        passengerVBox.setAlignment(Pos.BASELINE_LEFT);

        root5.setTop(passengerVBox);


        window.setScene(scene4);
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
        scene2 = new Scene(root2,500,300);

        //INITIALIZE LABELS
        rgc = new Label(generateString(rng,characters,5));
        firstName = new Label("First Name:");
        lastName = new Label("Last Name:");
        Label age = new Label("Age");
        Label aptLabel = new Label("Airplane Type");

        rgc.setFont(f);
        firstName.setFont(f);
        lastName.setFont(f);
        age.setFont(f);
        aptLabel.setFont(f);

        //INITIALIZE TEXTFIELDS
        firstNameText = new TextField();
        lastNameText = new TextField();

        //CREATE CHOICEBOX FOR AGE
        ageArray = new String[101];

        for(int a = 1; a<101; a++) {
            ageArray[a] = Integer.toString(a);
        }
        c = new ChoiceBox<String>(FXCollections.observableArrayList(ageArray));

        //VBOX FOR AGE AND CB
        VBox agebox = new VBox();
        agebox.setPadding(new Insets(5));
        agebox.setSpacing(5);

        agebox.getChildren().addAll(age,c);

        //CREATE CHOICEBOX FOR AIRPLANE TYPE
        final String[] airplaneType = {"Private","Business","Regular"};
        apT = new ChoiceBox<String>(FXCollections.observableArrayList(airplaneType));

        //VBOX FOR APT AND CB
        VBox aptBox = new VBox();
        aptBox.setPadding(new Insets(5));
        aptBox.setSpacing(5);

        aptBox.getChildren().addAll(aptLabel,apT);

        //RADIOBUTTONS
        r1 = new RadioButton("Travel Insurance");
        r1.setFont(f);
        r2 = new RadioButton("Round Trip");
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
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(r1.isSelected()){
                    Travel_Insurance = "true";
                } else {
                    Travel_Insurance = "false";
                }

                if(r2.isSelected()){
                    Round_Trip = "true";
                } else {
                    Round_Trip = "false";
                }

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/airline_data", "root", "");
                    Statement stat = conn.createStatement();
                    String sm = "INSERT INTO passenger_information VALUES(?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sm);
                    preparedStatement.setString(1, ID = rgc.getText());
                    preparedStatement.setString(2, First_Name = firstNameText.getText());
                    preparedStatement.setString(3, Last_Name = lastNameText.getText());
                    preparedStatement.setString(4, Age = c.getValue());
                    preparedStatement.setString(5, Airplane_Type = apT.getValue());
                    preparedStatement.setString(6, String.valueOf(Travel_Insurance));
                    preparedStatement.setString(7, String.valueOf(Round_Trip));
                    preparedStatement.executeUpdate();
                    stat.close();

                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(IDARRAY));
                    arrayList.add(rgc.getText());
                    IDARRAY = arrayList.toArray(IDARRAY);

                    System.out.println(Arrays.toString(IDARRAY));

                    rgc = new Label(generateString(rng,characters,5));
                    nextPassenger();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(r1.isSelected()){
                    Travel_Insurance = "true";
                } else {
                    Travel_Insurance = "false";
                }

                if(r2.isSelected()){
                    Round_Trip = "true";
                } else {
                    Round_Trip = "false";
                }

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/airline_data", "root", "");
                    Statement stat = conn.createStatement();
                    String sm = "INSERT INTO passenger_information VALUES(?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sm);
                    preparedStatement.setString(1, ID = rgc.getText());
                    preparedStatement.setString(2, First_Name = firstNameText.getText());
                    preparedStatement.setString(3, Last_Name = lastNameText.getText());
                    preparedStatement.setString(4, Age = c.getValue());
                    preparedStatement.setString(5, Airplane_Type = apT.getValue());
                    preparedStatement.setString(6, String.valueOf(Travel_Insurance));
                    preparedStatement.setString(7, String.valueOf(Round_Trip));
                    preparedStatement.executeUpdate();
                    stat.close();

                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(IDARRAY));
                    arrayList.add(rgc.getText());
                    IDARRAY = arrayList.toArray(IDARRAY);

                    System.out.println(Arrays.toString(IDARRAY));

                    fromToLocation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //confirmButton.setFont(Font.font("Poppins", FontWeight.BOLD,20));
        //confirmButton.setMinSize(50,30);

        //HBOX FOR BUTTON
        HBox buttonHbox = new HBox();
        buttonHbox.setPadding(new Insets(5));
        buttonHbox.setSpacing(5);

        buttonHbox.getChildren().addAll(nextButton,confirmButton);
        buttonHbox.setAlignment(Pos.BOTTOM_CENTER);

        //CREATE VBOX
        vbox = new VBox();
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
