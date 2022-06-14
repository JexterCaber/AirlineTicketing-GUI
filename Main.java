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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Main extends Application {

    //int a = 0;
    int passengercounter = 1;
    int passengershowcounter = 0;
    int agecounter = 0;
    //int comparer;

    double TransactionTotal = 0.00;

    Stage window;
    Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7;

    Label rgc;
    Label firstName;
    Label lastName;
    Label passengerID;
    Label passengerFullName;
    Label passengerAge;
    Label passengerFlightType;
    Label passengerTravelTax;
    Label passengerFare = new Label("");
    Label passengerBaggageFee = new Label("");
    Label passengerTravelInsurance = new Label("");
    Label fligthTypeLabel = new Label("");

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

    RadioButton typePrivate = new RadioButton("");
    RadioButton typeBusiness = new RadioButton("");
    RadioButton typeRegular = new RadioButton("");

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
    ToggleGroup travelType;

    VBox vbox;

    ChoiceBox<String> c;
    //ChoiceBox<String> apT;
    ChoiceBox<String> passengerNumberChoice;

    String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random rng = new Random();

    String ID;
    String Last_Name;
    String First_Name;
    String Age;
    String Airplane_Type;
    String Travel_Insurance = "false";
    String Round_Trip = "false";

    String[] IDARRAY = new String[0];
    String[] AGEARRAY = new String[0];

    Boolean hasAdult = false;

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

    public void whatFlightType() {
        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        //SET LAYOUT
        BorderPane root6 = new BorderPane();
        scene5 = new Scene(root6,300,140);

        //LABEL
        Label lb1 = new Label("What flight type?");
        lb1.setFont(f);

        //SET RADIOBUTTON
        typePrivate.setText("Private");
        typeBusiness.setText("Business");
        typeRegular.setText("Regular");

        //SET FONT
        typePrivate.setFont(f);
        typeBusiness.setFont(f);
        typeRegular.setFont(f);

        //SET TOGGLE GROUP
        travelType = new ToggleGroup();

        typePrivate.setToggleGroup(travelType);
        typeBusiness.setToggleGroup(travelType);
        typeRegular.setToggleGroup(travelType);

        //HBOX
        HBox flightTypes = new HBox();
        flightTypes.setPadding(new Insets(5));
        flightTypes.setSpacing(5);

        flightTypes.getChildren().addAll(typePrivate,typeBusiness,typeRegular);

        //MAKE CONFIRM BUTTON
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                howManyPassengers();
            }
        });

        //VBOX
        VBox parentVBOX = new VBox();
        parentVBOX.setPadding(new Insets(5));
        parentVBOX.setSpacing(5);

        parentVBOX.getChildren().addAll(lb1,flightTypes,confirmButton);
        parentVBOX.setAlignment(Pos.CENTER);

        //CENTER
        HBox center = new HBox();
        center.setPadding(new Insets(5));
        center.setSpacing(5);

        center.getChildren().add(parentVBOX);
        center.setAlignment(Pos.CENTER);

        HBox.setMargin(parentVBOX,new Insets(10,10,10,10));

        /*
        //LABEL
        Label howMany = new Label("How many passengers?");
        howMany.setFont(f);

        //CHOICE BOX HOW MANY PASSENGERS
        final String[] passengerNumberPrivate = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};

        final String[] passengerNumberBusiness = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};

        final String[] passengerNumberRegular = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","35","36","37","38","39","40","41","42","43","44","45","46","47","48"};

        //VBOX
        VBox passengernumber = new VBox();
        passengernumber.setPadding(new Insets(5));
        passengernumber.setSpacing(5);
        passengernumber.getChildren().addAll(howMany,passengerNumberChoice);


        //VBOX
        VBox containter = new VBox();
        containter.setPadding(new Insets(5));
        containter.setSpacing(5);

        containter.getChildren().addAll(flightTypes,passengernumber);

        root6.setTop(containter);
         */

        root6.setTop(center);
        root6.setBackground(Background.fill(Color.rgb(0,180,216)));

        window.setScene(scene5);

    }

    public void howManyPassengers() {
        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        //SET LAYOUT
        BorderPane root7 = new BorderPane();
        scene6 = new Scene(root7,300,100);

        //LABEL
        Label lb1 = new Label("How many Passengers?");
        lb1.setFont(f);

        //CHOICE BOX HOW MANY PASSENGERS
        final String[] passengerNumberPrivate = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};

        final String[] passengerNumberBusiness = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};

        final String[] passengerNumberRegular = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","35","36","37","38","39","40","41","42","43","44","45","46","47","48"};

        if(typePrivate.isSelected()) {
            passengerNumberChoice = new ChoiceBox<String>(FXCollections.observableArrayList(passengerNumberPrivate));
        } else if (typeBusiness.isSelected()) {
            passengerNumberChoice = new ChoiceBox<String>(FXCollections.observableArrayList(passengerNumberBusiness));
        } else if (typeRegular.isSelected()) {
            passengerNumberChoice = new ChoiceBox<String>(FXCollections.observableArrayList(passengerNumberRegular));
        }

        //BUTTON
        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nextPassenger();
            }
        });

        //VBOX
        VBox howpas = new VBox();
        howpas.setPadding(new Insets(5));
        howpas.setSpacing(5);

        howpas.getChildren().addAll(lb1,passengerNumberChoice,confirmButton);
        howpas.setAlignment(Pos.CENTER);

        //CENTER
        HBox center = new HBox();
        center.setPadding(new Insets(5));
        center.setSpacing(5);
        center.getChildren().add(howpas);
        center.setAlignment(Pos.CENTER);

        root7.setTop(center);
        root7.setBackground(Background.fill(Color.rgb(0,180,216)));

        window.setScene(scene6);
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
        //final String[] airplaneType = {"Private","Business","Regular"};
        //apT = new ChoiceBox<String>(FXCollections.observableArrayList(airplaneType));
        //LABEL

        if(typePrivate.isSelected()) {
            fligthTypeLabel = new Label("Private");
        } else if (typeBusiness.isSelected()) {
            fligthTypeLabel = new Label("Business");
        } else if (typeRegular.isSelected()) {
            fligthTypeLabel = new Label("Regular");
        }

        fligthTypeLabel.setFont(f);

        //VBOX FOR APT AND CB
        VBox aptBox = new VBox();
        aptBox.setPadding(new Insets(5));
        aptBox.setSpacing(5);

        //aptBox.getChildren().addAll(aptLabel,apT);
        aptBox.getChildren().addAll(aptLabel,fligthTypeLabel);

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

        //

        //BUTTON
        Button nextButton = new Button("Next");
        nextButton.setFont(f);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ArrayList<String> arrayList2 = new ArrayList<String>(Arrays.asList(AGEARRAY));
                arrayList2.add(c.getValue());
                AGEARRAY = arrayList2.toArray(AGEARRAY);

                System.out.println(Arrays.toString(AGEARRAY));

                if (19 <= Integer.parseInt(AGEARRAY[agecounter])) {
                    hasAdult = true;
                } else {
                    agecounter++;
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("Enter an Adult First!"));
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }

                if (passengercounter < Integer.parseInt(passengerNumberChoice.getValue()) && hasAdult) {
                    passengercounter++;

                    if (r1.isSelected()) {
                        Travel_Insurance = "true";
                    } else {
                        Travel_Insurance = "false";
                    }

                    if (r2.isSelected()) {
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
                        preparedStatement.setString(5, Airplane_Type = fligthTypeLabel.getText());
                        preparedStatement.setString(6, String.valueOf(Travel_Insurance));
                        preparedStatement.setString(7, String.valueOf(Round_Trip));
                        preparedStatement.executeUpdate();
                        stat.close();

                        ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(IDARRAY));
                        arrayList1.add(rgc.getText());
                        IDARRAY = arrayList1.toArray(IDARRAY);

                        System.out.println(Arrays.toString(IDARRAY));

                        nextPassenger();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (hasAdult){
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("This is the Last Passenger!"));
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
            }});

        Button confirmButton = new Button("Confirm");
        confirmButton.setFont(f);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                ArrayList<String> arrayList2 = new ArrayList<String>(Arrays.asList(AGEARRAY));
                arrayList2.add(c.getValue());
                AGEARRAY = arrayList2.toArray(AGEARRAY);

                System.out.println(Arrays.toString(AGEARRAY));

                if (19 <= Integer.parseInt(AGEARRAY[agecounter])) {
                    hasAdult = true;
                } else {
                    agecounter++;
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("Enter an Adult First!"));
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }

                if(passengercounter==Integer.parseInt(passengerNumberChoice.getValue()) && hasAdult) {
                    if (r1.isSelected()) {
                        Travel_Insurance = "true";
                    } else {
                        Travel_Insurance = "false";
                    }

                    if (r2.isSelected()) {
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
                        preparedStatement.setString(5, Airplane_Type = fligthTypeLabel.getText());
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
                } else if (hasAdult){
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("Not the last Passenger Yet!"));
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
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
            preparedStatement.setString(1,IDARRAY[passengershowcounter]);

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
            System.out.println("ERROR");
        }

        //LABEL LABELS
        Label showpassengerName = new Label("Name: ");
        Label showpassengerAge = new Label("Age: ");
        Label showflightType = new Label("Fligth Type: ");
        Label showtravelTax = new Label("Travel Tax: ");
        Label showpassengerFare = new Label("Passenger Fare: ");
        Label showbaggageFee = new Label("Baggage Fee: ");
        Label showtravelInsurance = new Label("Travel Insurance: ");


        passengerID = new Label(ID + " Passenger Number " + (passengershowcounter+1));
        passengerFullName = new Label(First_Name + " " + Last_Name );
        passengerAge = new Label(Age);
        passengerFlightType = new Label(Airplane_Type);


        if(Integer.parseInt(passengerAge.getText())>59){
            passengerTravelTax = new Label("0.00");
        } else {
            if (Airplane_Type.equalsIgnoreCase("Private")) {
                passengerTravelTax = new Label("4260.00");
            } else if (Airplane_Type.equalsIgnoreCase("Business")) {
                passengerTravelTax = new Label("5700.00");
            } else {
                passengerTravelTax = new Label("2500.00");
            }
        }
        if(Airplane_Type.equalsIgnoreCase("Private")) {
            if (fromManila.isSelected() && toBatanes.isSelected()) {
                passengerFare.setText("8000.00");
            } else if (fromManila.isSelected() && toPalawan.isSelected()) {
                passengerFare.setText("9100.00");
            } else if (fromBatanes.isSelected() && toManila.isSelected()) {
                passengerFare.setText("9800.00");
            } else if (fromPalawan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("9850.00");
            } else if (fromManila.isSelected() && toSouthKorea.isSelected()) {
                passengerFare.setText("27450.00");
            } else if (fromSouthKorea.isSelected() && toManila.isSelected()) {
                passengerFare.setText("30890.00");
            } else if (fromManila.isSelected() && toJapan.isSelected()) {
                passengerFare.setText("40450.00");
            } else if (fromJapan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("43855.00");
            } else if (fromManila.isSelected() && toVietnam.isSelected()) {
                passengerFare.setText("8505.00");
            } else if (fromVietnam.isSelected() && toManila.isSelected()) {
                passengerFare.setText("14300.00");
            }
        } else if (Airplane_Type.equalsIgnoreCase("Business")){
            if (fromManila.isSelected() && toBatanes.isSelected()) {
                passengerFare.setText("12500.00");
            } else if (fromManila.isSelected() && toPalawan.isSelected()) {
                passengerFare.setText("10500.00");
            } else if (fromBatanes.isSelected() && toManila.isSelected()) {
                passengerFare.setText("12950.00");
            } else if (fromPalawan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("10975.00");
            } else if (fromManila.isSelected() && toSouthKorea.isSelected()) {
                passengerFare.setText("37390.00");
            } else if (fromSouthKorea.isSelected() && toManila.isSelected()) {
                passengerFare.setText("39650.00");
            } else if (fromManila.isSelected() && toJapan.isSelected()) {
                passengerFare.setText("45355.00");
            } else if (fromJapan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("49780.00");
            } else if (fromManila.isSelected() && toVietnam.isSelected()) {
                passengerFare.setText("12345.00");
            } else if (fromVietnam.isSelected() && toManila.isSelected()) {
                passengerFare.setText("16320.00");
            }
        } else if (Airplane_Type.equalsIgnoreCase("Regular")) {
            if (fromManila.isSelected() && toBatanes.isSelected()) {
                passengerFare.setText("3500.00");
            } else if (fromManila.isSelected() && toPalawan.isSelected()) {
                passengerFare.setText("3900.00");
            } else if (fromBatanes.isSelected() && toManila.isSelected()) {
                passengerFare.setText("3200.00");
            } else if (fromPalawan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("3575.00");
            } else if (fromManila.isSelected() && toSouthKorea.isSelected()) {
                passengerFare.setText("12055.00");
            } else if (fromSouthKorea.isSelected() && toManila.isSelected()) {
                passengerFare.setText("13100.00");
            } else if (fromManila.isSelected() && toJapan.isSelected()) {
                passengerFare.setText("27800.00");
            } else if (fromJapan.isSelected() && toManila.isSelected()) {
                passengerFare.setText("29400.00");
            } else if (fromManila.isSelected() && toVietnam.isSelected()) {
                passengerFare.setText("3200.00");
            } else if (fromVietnam.isSelected() && toManila.isSelected()) {
                passengerFare.setText("4600.00");
            }
        }

        //BAGGAGE FEE
        if(Airplane_Type.equalsIgnoreCase("Private")) {
            passengerBaggageFee.setText("1250.00");
        } else if (Airplane_Type.equalsIgnoreCase("Business")) {
            passengerBaggageFee.setText("2850.00");
        } else {
            passengerBaggageFee.setText("950.00");
        }

        //TRAVEL INSURANCE
        if(Airplane_Type.equalsIgnoreCase("Private") && Travel_Insurance.equalsIgnoreCase("true")) {
            passengerTravelInsurance.setText("4500.00");
        } else if (Airplane_Type.equalsIgnoreCase("Business") && Travel_Insurance.equalsIgnoreCase("true")) {
            passengerTravelInsurance.setText("6500.00");
        } else if (Airplane_Type.equalsIgnoreCase("Regular") && Travel_Insurance.equalsIgnoreCase("true")) {
            passengerTravelInsurance.setText("950.00");
        } else {
            passengerTravelInsurance.setText("0.00");
        }


        //SET FONT
        passengerID.setFont(f);
        passengerFullName.setFont(f);
        passengerAge.setFont(f);
        passengerFlightType.setFont(f);
        passengerTravelTax.setFont(f);
        passengerFare.setFont(f);
        passengerBaggageFee.setFont(f);
        passengerTravelInsurance.setFont(f);

        showpassengerName.setFont(f);
        showpassengerAge.setFont(f);
        showflightType.setFont(f);
        showtravelTax.setFont(f);
        showpassengerFare.setFont(f);
        showbaggageFee.setFont(f);
        showtravelInsurance.setFont(f);

        //NAMEHBOX
        HBox nameHBox = new HBox();
        nameHBox.setPadding(new Insets(5));
        nameHBox.setSpacing(5);

        nameHBox.getChildren().addAll(showpassengerName,passengerFullName);

        //AGEHBOX
        HBox ageHBox = new HBox();
        ageHBox.setPadding(new Insets(5));
        ageHBox.setSpacing(5);

        ageHBox.getChildren().addAll(showpassengerAge,passengerAge);

        //flightTYPEHBOX
        HBox flighttypeHBOX = new HBox();
        flighttypeHBOX.setPadding(new Insets(5));
        flighttypeHBOX.setSpacing(5);

        flighttypeHBOX.getChildren().addAll(showflightType,passengerFlightType);

        //traveltaxHBOX
        HBox traveltaxHBOX = new HBox();
        traveltaxHBOX.setPadding(new Insets(5));
        traveltaxHBOX.setSpacing(5);

        traveltaxHBOX.getChildren().addAll(showtravelTax,passengerTravelTax);

        //passengerfaraHBOX
        HBox passengerfareHBOX = new HBox();
        passengerfareHBOX.setPadding(new Insets(5));
        passengerfareHBOX.setSpacing(5);

        passengerfareHBOX.getChildren().addAll(showpassengerFare,passengerFare);

        //baggagefeeHBOX
        HBox baggagefeeHBOX = new HBox();
        baggagefeeHBOX.setPadding(new Insets(5));
        baggagefeeHBOX.setSpacing(5);

        baggagefeeHBOX.getChildren().addAll(showbaggageFee,passengerBaggageFee);

        //travelINSURANCE
        HBox travelinsuranceHBOX = new HBox();
        travelinsuranceHBOX.setPadding(new Insets(5));
        travelinsuranceHBOX.setSpacing(5);

        travelinsuranceHBOX.getChildren().addAll(showtravelInsurance,passengerTravelInsurance);

        //VBOX LABELS
        VBox labelVBOX = new VBox();
        labelVBOX.setPadding(new Insets(5));
        labelVBOX.setSpacing(5);

        labelVBOX.getChildren().addAll(passengerID, nameHBox,ageHBox,flighttypeHBOX,traveltaxHBOX,passengerfareHBOX,baggagefeeHBOX,travelinsuranceHBOX);
        labelVBOX.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        //NEXT BUTTON
        Button nextButton = new Button("Next");
        nextButton.setFont(f);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                double seniordiscount;

                if(passengershowcounter<IDARRAY.length) {
                    if(Integer.parseInt(passengerAge.getText())>59) {
                        seniordiscount = ((Double.parseDouble(passengerFare.getText()) + Double.parseDouble(passengerBaggageFee.getText()) + Double.parseDouble(passengerTravelInsurance.getText())) * .20);
                        TransactionTotal = TransactionTotal + ((Double.parseDouble(passengerFare.getText())+Double.parseDouble(passengerBaggageFee.getText())+Double.parseDouble(passengerTravelInsurance.getText()))-seniordiscount);
                        System.out.println(TransactionTotal);
                        System.out.println(seniordiscount);
                        showPassengerInfo();
                    } else {
                        TransactionTotal = TransactionTotal + (Double.parseDouble(passengerTravelTax.getText()) + Double.parseDouble(passengerFare.getText()) + Double.parseDouble(passengerBaggageFee.getText()) + Double.parseDouble(passengerTravelInsurance.getText()));
                        showPassengerInfo();
                    }
                } else {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("Last Passenger!"));
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
            }
        });

        //CONFIRM BUTTON
        Button confirm = new Button("Confirm");
        confirm.setFont(f);
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                double seniordiscount;

                if(passengershowcounter==IDARRAY.length) {
                    if(Integer.parseInt(passengerAge.getText())>59) {
                        seniordiscount = ((Double.parseDouble(passengerFare.getText()) + Double.parseDouble(passengerBaggageFee.getText()) + Double.parseDouble(passengerTravelInsurance.getText())) * .20);
                        TransactionTotal = TransactionTotal + ((Double.parseDouble(passengerFare.getText()) + Double.parseDouble(passengerBaggageFee.getText()) + Double.parseDouble(passengerTravelInsurance.getText())) - seniordiscount);
                        System.out.println(TransactionTotal);
                        System.out.println(seniordiscount);
                        showComputation();
                    } else {
                        TransactionTotal = TransactionTotal + (Double.parseDouble(passengerTravelTax.getText()) + Double.parseDouble(passengerFare.getText()) + Double.parseDouble(passengerBaggageFee.getText()) + Double.parseDouble(passengerTravelInsurance.getText()));
                        showComputation();
                    }
                } else {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(window);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("Not the last Passenger!"));
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
            }
        });

        //HBOX
        HBox buttoncontainer = new HBox();
        buttoncontainer.setPadding(new Insets(5));
        buttoncontainer.setSpacing(5);

        buttoncontainer.getChildren().addAll(nextButton,confirm);

        //VBOX parentcontainer
        VBox parentcontainer = new VBox();
        parentcontainer.setPadding(new Insets(5));
        parentcontainer.setSpacing(5);

        parentcontainer.getChildren().addAll(labelVBOX,buttoncontainer);

        //center
        HBox center = new HBox();
        center.setPadding(new Insets(5));
        center.setSpacing(5);

        center.getChildren().add(parentcontainer);
        center.setAlignment(Pos.CENTER);

        root5.setTop(center);
        root5.setBackground(Background.fill(Color.rgb(0,180,216)));

        passengershowcounter++;
        window.setScene(scene4);
    }

    public void showComputation() {
        Font f = Font.font("Poppins", FontWeight.BOLD,14);

        BorderPane root8 = new BorderPane();
        scene7 = new Scene(root8,500,500);

        Label total = new Label("Total: ");
        Label totaloftransaction = new Label(Double.toString(TransactionTotal));

        total.setFont(f);
        totaloftransaction.setFont(f);

        //HBOX
        HBox totalHBOX = new HBox();
        totalHBOX.setPadding(new Insets(5));
        totalHBOX.setSpacing(5);

        totalHBOX.getChildren().addAll(total,totaloftransaction);

        //CENTER
        HBox center = new HBox();
        center.setPadding(new Insets(5));
        center.setSpacing(5);

        center.getChildren().add(totalHBOX);
        center.setAlignment(Pos.CENTER);
        HBox.setMargin(total, new Insets(10,10,10,10));
        HBox.setMargin(totaloftransaction, new Insets(10,10,10,10));

        //
        VBox centerVBOX = new VBox();
        centerVBOX.setPadding(new Insets(5));
        centerVBOX.setSpacing(5);
        
        centerVBOX.getChildren().add(center);
        centerVBOX.setAlignment(Pos.CENTER);

        root8.setTop(center);
        root8.setBackground(Background.fill(Color.rgb(0,180,216)));

        window.setScene(scene7);
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
                whatFlightType();
            }
        });

        //CREATE VBOX
        VBox vboxlogo = new VBox();
        vboxlogo.setAlignment(Pos.CENTER);
        vboxlogo.getChildren().addAll(imageview,button);

        root1.setCenter(vboxlogo);
        root1.setBackground(Background.fill(Color.rgb(0,180,216)));

        window.setResizable(false);
        window.initStyle(StageStyle.DECORATED);
        window.setScene(scene1);
        window.setTitle("Airline Ticketing System");
        window.show();

    }
}
