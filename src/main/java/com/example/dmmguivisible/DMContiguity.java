package com.example.dmmguivisible;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.*;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

public class DMContiguity {

    public String gmailid_of_II = "chauhantejas98@gmail.com";    // set Gmail id here
    public String gmailpass_of_II = "kyrzstpvvfaoycnq";    // set gmail password here
    public String defaultPath;
    @FXML
    public ImageView tr;
    @FXML
    public TextField proCoNmae;
    @FXML
    public TextField proEmId;
    @FXML
    public TextField proPhoNo;
    @FXML
    public TextArea proAddress;
    @FXML
    public Button proeditlogo;
    @FXML
    public Button proupdate;
    public Button proedit;
    public ImageView headerimage;

    @FXML
    public ListView<String> myListView;
    public Button shareexcel;
    public TextField exmail2;
    public TextField exmail1;
    public TextField numberofday;
    public TextField daysforhistory;
    public ImageView excelicon;
    public Label headercompanyname;

    public SplitMenuButton connectmenu;
    public AnchorPane conectionwindow;
    public AnchorPane dmmdatarivecerwindow;
    public ListView portListView;
    public Button comportselected;
    public Button portselectcancle;
    public TextField pdfemail1;
    public TextField pdfemail2;
    public DatePicker DateFrom;
    public DatePicker DateTo;
    public Button browsefile;
    public TextField Tfd1;
    public TextField Tfd2;
    public TextField Tfd3;
    public TextField Tfd4;
    public TextField Tfd5;
    public TextField Tfd6;
    public TextField Tfd7;
    public TextField Tfd8;
    public TextField Tfd9;
    public TextField Tfd10;
    public Button addtextfield;
    public Label tfl1;
    public Label tfl2;
    public Label tfl3;
    public Label tfl4;
    public Label tfl5;
    public Label tfl6;
    public Label tfl7;
    public Label tfl8;
    public Label tfl9;
    public Label tfl10;
    public TextField browsepath;
    public RadioButton RedBtn1;
    public RadioButton RedBtn2;
    public RadioButton RedBtn3;
    public Button sendasPDF;
    public Button sendasEXCEL;
    public Button sendEmail;
    public ProgressIndicator progressindicator2;
    public ProgressIndicator progressindicator1;
    //    public Label openporttittle;
    public Button ConnectButton;
    public Button maxdatechecker;
    public Label AvailableRecData;
    public ImageView quick_guiad;
    public Label Disconnectedstatus;
    public Label Connectedstatus;

    public Label ConnectedstatusSYB;
    public Label DisconnectedstatusSYB;
    @FXML
    private ToggleGroup toggleGroup1;
    private Hoder dataHolder;
    private Hoder infoHolder;
    public String exlefilepath;

    public String viewfilepath;
    public String ExTime;
    public String listViewItemName;

    public String Portname = null;
    public SerialPort _serialPort;
    public Hoder infoHolder1;
    public Hoder comnameHoder;
    public Hoder resultsetHoder;
    public Hoder oneYearResultHolder;
    public Hoder twoYearResultHolder;

    public Hoder todayresultHolder;
    public static Connection connection = null;
    public int CHARACTER_LIMIT = 80;

    public String Realogview = "Realogview";
    public String DMM10 = "DMM1.0";
    public String Data_Base = "DataBase";
    public String User_Profile = "User Profile";
    public String Data = "Data";
    public String IPDF = "PDF";
    public String Records = "Records";
    public String PDF = "PDF";
    public String EXCEL = "EXCEL";
    public String Blemodulename;
    public String userCompanyName = "Innovative Instruments";
    public String userCompanyEmailId = "info@innovative-instruments.in";
    public String userCompanyPhNo = "6356615024";
    int statusbit = 0;
    public String userCompanyAddress = "No. 125, Mahajan Society, Behind Convent \n School, Fatehgunj, Vadodara, Gujarat - \n 390 002, India.";

    public String FromDateForEm_Ex;
    public String ToDateForEm_Ex;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public String MAC_ID = "";

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    private boolean isUserTxtFile(File file) {
        String name = file.getName();
        System.out.println("name " + name);
        return name.equals("UserProfileData.txt");
    }

    void getTotalRecordData() {

        String firstRecordDate = GetFirstTableDate();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(firstRecordDate, formatter1);
        LocalDate date = dateTime.toLocalDate();

        String lastRecordDate = GetlastTableDate();
        LocalDateTime dateTime1 = LocalDateTime.parse(lastRecordDate, formatter1);
        LocalDate date1 = dateTime1.toLocalDate();

    }

    private int counter = 0;
    public String ConPortname;

    public void initialize() throws IOException, SQLException, ClassNotFoundException, SQLException {

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        DateFrom.setConverter(converter);
        DateTo.setConverter(converter);

        comnameHoder = new Hoder();
        resultsetHoder = new Hoder();
        oneYearResultHolder = new Hoder();
        twoYearResultHolder = new Hoder();
        todayresultHolder = new Hoder();
        RedBtn1.setOnAction(event -> handleOptionSelected(RedBtn1));
        RedBtn2.setOnAction(event -> handleOptionSelected(RedBtn2));
        RedBtn3.setOnAction(event -> handleOptionSelected(RedBtn3));

        textlimit();
        Tooltip tooltip = new Tooltip("(जोड़ना)");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            counter++;
            portListView.getItems().clear();
            ports = _serialPort.getCommPorts(); // Get the available COM ports

            if (ports.length >= 1) {
                for (SerialPort port : ports) {

                    portListView.getItems().add(port.getSystemPortName());
                }
            } else {

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        // Start the timeline
        timeline.play();

        // Set the tooltip for the button
//        ConnectButton.setTooltip(tooltip);

        DateFrom.setDayCellFactory(picker -> new CustomDateCell(DateFrom));
        DateTo.setDayCellFactory(picker -> new CustomDateCell(DateTo));

        DateFrom.setOnAction(event -> {
            boolean hasTables = SQLiteTableChecker.hasTables();
            System.out.println(DateFrom.getValue());

            if (DateFrom.getValue() != null && DateTo.getValue() != null) {
                int d1 = getduration();
                maxdatechecker.setText(String.valueOf(d1));
            }
            if (hasTables) {
                LocalDate se1 = DateFrom.getValue();
                FromDateForEm_Ex = se1.format(FromAndToFormatter);
                from_Date = false;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate selectedDate = DateFrom.getValue();
                String formattedDate = selectedDate.format(formatter);
                System.out.println(formattedDate);
                String firstRecordDate = GetFirstTableDate();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(firstRecordDate, formatter1);
                LocalDate date = dateTime.toLocalDate();
                LocalDate currentDate = LocalDate.now();

                DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
                String fdate = datefro.format(formatterpiker);
                System.out.println("from " + fdate);

                if (selectedDate.isBefore(date) || selectedDate.isAfter(currentDate)) {
                    System.out.println("Selected Date is in the past than recored data and is future data");
                } else {
                    System.out.println("Selected date is valid");
                    from_Date = true;

                }
            } else {
//                showAlertwarning("DataBase error","No Records Found");
            }

        });
        DateTo.setOnAction(event -> {
            boolean hasTables = SQLiteTableChecker.hasTables();
            if (DateFrom.getValue() != null && DateTo.getValue() != null) {
                int d1 = getduration();
                maxdatechecker.setText(String.valueOf(d1));
            }
            if (hasTables) {
                LocalDate se1 = DateTo.getValue();
                ToDateForEm_Ex = se1.format(FromAndToFormatter);
                to_Date = false;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate selectedDate = DateTo.getValue();
                String formattedDate = selectedDate.format(formatter);
                System.out.println(formattedDate);
                String firstRecordDate = GetFirstTableDate();
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(firstRecordDate, formatter1);
                LocalDate date = dateTime.toLocalDate();

                String lastRecordDate = GetlastTableDate();
                LocalDateTime dateTime1 = LocalDateTime.parse(lastRecordDate, formatter1);
                LocalDate date1 = dateTime1.toLocalDate();

                DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
                String tdate = datefro.format(formatterpiker);
                System.out.println("+to " + tdate);

                if (selectedDate.isAfter(date1) || selectedDate.isBefore(date)) {
                    System.out.println("Selected date is in the future and not in record");
                } else {
                    System.out.println("Selected Date is valid");
                    to_Date = true;
                }
            } else {
//                showAlertwarning("Database error","No Records Found");
            }
        });

        LocalDate today = LocalDate.now();
        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIN);
        System.out.println("Today's date and time starting from 00:00:00 is: " + todayStart);

        defaultPath = System.getProperty("user.home");
        viewfilepath = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data;
        Path path = Paths.get(defaultPath + "/" + Realogview);

        Path path001 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10);
        Path path002 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data);
        Path path003 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records);
        Path path004 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data_Base);
        Path path005 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
        Path path006 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF);
        Path path007 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + EXCEL);
        Path path008 = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data + "/" + PDF);
        Path path00up = Paths.get(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile + "/UserProfileData.txt");
        boolean folderExists = Files.exists(path) && Files.isDirectory(path);
        boolean folderExists001 = Files.exists(path001) && Files.isDirectory(path001);
        boolean folderExists002 = Files.exists(path002) && Files.isDirectory(path002);
        boolean folderExists003 = Files.exists(path003) && Files.isDirectory(path003);
        boolean folderExists004 = Files.exists(path004) && Files.isDirectory(path004);
        boolean folderExists005 = Files.exists(path005) && Files.isDirectory(path005);
        boolean folderExists006 = Files.exists(path006) && Files.isDirectory(path006);
        boolean folderExists007 = Files.exists(path007) && Files.isDirectory(path007);
        boolean folderExists008 = Files.exists(path008) && Files.isDirectory(path008);
        boolean userfile = Files.exists(path00up);
        if (folderExists && folderExists001 && folderExists002 && folderExists003 && folderExists004 && folderExists005 && folderExists006 && folderExists007 && folderExists008 && userfile) {
            System.out.println("done" + path);
            Image image = new Image(getClass().getResource("/images/l5.jpg").toExternalForm());
            headerimage.setImage(image);
            Image image1 = new Image(getClass().getResource("/images/temp logo.jpg").toExternalForm());
//            headerlogo.setImage(image1);
            Image image9 = new Image(getClass().getResource("/images/qg1.png").toExternalForm());
            quick_guiad.setImage(image9);
            String filepath01 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile;
            File folder = new File(filepath01);
            File[] files = folder.listFiles();
            for (File file1 : files) {
                boolean dest1 = isUserTxtFile(file1);
                if (dest1) {
                    Path file = Paths.get(file1.toURI());
                    List<String> lines = Files.readAllLines(file);
                    System.out.println("lines " + lines.isEmpty());
                    if (lines.isEmpty()) {
                        System.out.println("not usr data");
                        proCoNmae.setText(userCompanyName);
                        proEmId.setText(userCompanyEmailId);
                        proPhoNo.setText(userCompanyPhNo);
                        proAddress.setText(userCompanyAddress);
                    } else {
                        String[] array = lines.toArray(new String[0]);
                        System.out.println(array.length);
                        proCoNmae.setText(array[0]);
                        proEmId.setText(array[1]);
                        proPhoNo.setText(array[2]);
                        String addrdata = array[3].trim().replace("|", "\n");
                        proAddress.setText(addrdata);
                    }
                    break;
                } else {
                    System.out.println("no1234");
                }
            }

            File folder1 = new File(filepath01);
            File[] files2 = folder1.listFiles();
            for (File file : files2) {
                boolean dest1 = isImageFile(file);
                if (dest1) {
                    System.out.println("yes");
                    Image image2 = new Image(String.valueOf(file));
                    tr.setImage(image2);
                    break;
                } else {
                    System.out.println("no");
                    Image image2 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                    tr.setImage(image2);
                }
            }

            headercompanyname.setText("ReaLogView DMM 1.0");
            System.out.println("yesssss here");
            String DBpath = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data_Base + "/Realogview.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBpath);
//            PreparedStatement statement=connection.prepareStatement("CREATE TABLE DMM2023(id INTEGER PRIMARY KEY AUTOINCREMENT, SerialNo TEXT,CommodityName TEXT ,Moisture Text,Temperature Text, DateTime TEXT, SaQunReqid TEXT,OtherInfo TEXT)");
//        statement.execute();
        } else {

            proCoNmae.setText(userCompanyName);
            proEmId.setText(userCompanyEmailId);
            proPhoNo.setText(userCompanyPhNo);
            proAddress.setText(userCompanyAddress);

            System.out.println("notttt here");
            String path0 = defaultPath + "/" + Realogview + "/" + DMM10;
            String path1 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data_Base;
            String path2 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data;
            String path02 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records;
            String path03 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile;
            String path04 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data + "/" + PDF;
            String path05 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF;
            String path06 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + EXCEL;
            Path folderPath0 = Paths.get(path0);
            Path folderPath1 = Paths.get(path1);
            Path folderPath2 = Paths.get(path2);
            Path folderPath02 = Paths.get(path02);
            Path folderPath03 = Paths.get(path03);
            Path folderPath04 = Paths.get(path04);
            Path folderPath05 = Paths.get(path05);
            Path folderPath06 = Paths.get(path06);
            Files.createDirectories(folderPath0);
            ;
            Files.createDirectories(folderPath1);
            Files.createDirectories(folderPath2);
            Files.createDirectories(folderPath02);
            Files.createDirectories(folderPath03);
            Files.createDirectories(folderPath04);
            Files.createDirectories(folderPath05);
            Files.createDirectories(folderPath06);

            String filePath1 = path03 + "/UserProfileData.txt";
            String filePathdb = path1 + "/Realogview.db";
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePathdb);
            File file = new File(filePath1);
            File file4 = new File(filePathdb);

            file.createNewFile();
            file4.createNewFile();
            String filepath01 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile;
            File folder1 = new File(filepath01);
            File[] files2 = folder1.listFiles();
            for (File file0 : files2) {
                boolean dest1 = isImageFile(file0);
                if (dest1) {
                    System.out.println("yes");
                    Image image2 = new Image(String.valueOf(file0));
                    tr.setImage(image2);
                    break;
                } else {
                    System.out.println("no");
                    Image image2 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                    tr.setImage(image2);
                }
            }

            Image image = new Image(getClass().getResource("/images/l5.jpg").toExternalForm());
            headerimage.setImage(image);
            Image image9 = new Image(getClass().getResource("/images/qg1.png").toExternalForm());
            quick_guiad.setImage(image9);

        }

    }

    public static boolean isInternetConnected() throws IOException, InterruptedException {
        Process process = java.lang.Runtime.getRuntime().exec("ping www.google.org");
        int x = process.waitFor();
        if (x == 0) {
            return true;
        } else {
            return false;
        }
//        try {
//            Socket socket = new Socket();
//            socket.connect(new InetSocketAddress("www.google.com", 80), 3000);
//            socket.close();
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
    }

    private void handleOptionSelected(RadioButton redBtn3) {
        String ab = redBtn3.getText();
        if (ab.equals("From :")) {
            System.out.println("a");
            sendEmail.setDisable(true);
            sendasPDF.setDisable(false);
            sendasEXCEL.setDisable(false);
            browsefile.setDisable(true);
            DateTo.setDisable(false);
            DateFrom.setDisable(false);
        } else if (ab.equals("Today :")) {
            System.out.println("ab");
            sendEmail.setDisable(true);
            sendasPDF.setDisable(false);
            sendasEXCEL.setDisable(false);
            browsefile.setDisable(true);
            DateTo.setDisable(true);
            DateFrom.setDisable(true);
        } else if (ab.equals("Choose File :")) {
            System.out.println("ac");
            sendEmail.setDisable(false);
            sendasPDF.setDisable(true);
            sendasEXCEL.setDisable(true);
            browsefile.setDisable(false);
            DateTo.setDisable(true);
            DateFrom.setDisable(true);

        }
    }

    public String GetFirstTableDate() {
        String DateName;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);
            List<String> tableNames = new ArrayList<>();
            while (resultSet.next()) {
                tableNames.add(resultSet.getString("TABLE_NAME"));
            }
            resultSet.close();
            String[] tableArray = tableNames.toArray(new String[0]);
//            System.out.println(tableArray[0]+"  "+tableArray[tableArray.length - 1]);
//            System.out.println("len  "+tableArray.length);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + tableArray[0] + " LIMIT 1";
            ResultSet resultSet1 = statement.executeQuery(query);

            DateName = resultSet1.getString("DateTime");

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DateName;
    }

    public String GetlastTableDate() {
        String DateName;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", null);
            List<String> tableNames = new ArrayList<>();
            while (resultSet.next()) {
                tableNames.add(resultSet.getString("TABLE_NAME"));
            }
            resultSet.close();
            String[] tableArray = tableNames.toArray(new String[0]);
//            System.out.println(tableArray[tableArray.length - 1]);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + tableArray[tableArray.length - 1] + "  ORDER BY id DESC LIMIT 1";
            ResultSet resultSet1 = statement.executeQuery(query);

            DateName = resultSet1.getString("DateTime");
//            System.out.println("ID: " + ", Name: " + DateName);
            // Close the resources
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return DateName;
    }

    @FXML
    public Button minimize;

    @FXML
    private Button profileButton;
    @FXML
    private Button gernaralinfoButton;
    @FXML
    private Button aboutusButtton;
    @FXML
    private Button historyButton;
    @FXML
    private Button homeButton;

    @FXML
    private Button sharePastDataButton;

    @FXML
    private AnchorPane companyProfile;
    @FXML
    private AnchorPane generalinfoBox;
    @FXML
    private AnchorPane aboutUsBox;
    @FXML
    private AnchorPane historyDataBox;
    @FXML
    private AnchorPane sharepastDataBox;
    @FXML
    private javafx.scene.control.Label data1;
    @FXML
    private javafx.scene.control.Label data2;
    @FXML
    private javafx.scene.control.Label data3;
    @FXML
    private javafx.scene.control.Label data4;
    @FXML
    private javafx.scene.control.Label data5;
    @FXML
    private javafx.scene.control.Label data7;
    @FXML
    private javafx.scene.control.Label data8;
    public String printtime;
    public String[] fruits;
    private final Label jobStatus = new Label();
    DateTimeFormatter globeldtformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    DateTimeFormatter formattor24Hours = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    SerialPort[] ports;
    String time_of_recived_data;
    String formate24hourse;
    public String Prefix_of_DBTable_Name = "DMM";
    public String textfieldof_adddata = "Tfd";
    public boolean from_Date = false;
    public boolean to_Date = false;
    File globselectedFile;
    int fild_inc_val = 5;

    int bit0 = 0;
    int bit1 = 1;

    public String Con_Port;
    DateTimeFormatter FromAndToFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    public String[] informationarray = new String[10];

    @FXML
    public void switchForm(ActionEvent event) throws IOException {
        if (event.getSource() == profileButton) {

            generalinfoBox.setVisible(false);
            companyProfile.setVisible(true);
            historyDataBox.setVisible(false);

            profileButton.setStyle("-fx-background-color:linear-gradient(to bottom right,#ff930f, #fff95b);-fx-color:#000;-fx-border-color: #fff;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
            gernaralinfoButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
            historyButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");


        } else if (event.getSource() == gernaralinfoButton) {

            generalinfoBox.setVisible(true);
            companyProfile.setVisible(false);
            historyDataBox.setVisible(false);

            gernaralinfoButton.setStyle("-fx-background-color:linear-gradient(to bottom right,#ff930f, #fff95b);-fx-color:#000;-fx-border-color: #fff;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
            profileButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
            historyButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
        } else if (event.getSource() == historyButton) {

            generalinfoBox.setVisible(false);
            companyProfile.setVisible(false);
            historyDataBox.setVisible(true);

            historyButton.setStyle("-fx-background-color:linear-gradient(to bottom right,#ff930f, #fff95b);-fx-color:#000;-fx-border-color: #fff;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
            profileButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
            gernaralinfoButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");

            boolean hasTables = SQLiteTableChecker.hasTables();
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            if (hasTables) {
                String Date1 = GetFirstTableDate();
                LocalDateTime localDateTime = LocalDateTime.parse(Date1, formattor24Hours);
                String d1 = localDateTime.format(formatter1);
                String Date2 = GetlastTableDate();
                LocalDateTime localDateTime1 = LocalDateTime.parse(Date2, formattor24Hours);
                String d2 = localDateTime1.format(formatter1);
                if (localDateTime.getDayOfMonth() == localDateTime1.getDayOfMonth()) {
//                     AvailableRecData.setText(" Available Records  : from "+localDateTime+"to " +localDateTime1 );
                } else {
                    AvailableRecData.setText(" Available Records  :  From  " + d1 + "  To  " + d2);
                }
            }
        }
    }

    @FXML
    public void viewfilefunc(ActionEvent actionEvent) {
        String defaultPath = System.getProperty("user.home");
        String initialDirectoryPath = "";
        File initialDirectory = new File(viewfilepath);

        // Create a FileChooser and set the initial directory
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(initialDirectory);
        FileChooser.ExtensionFilter excelFilter = new FileChooser.ExtensionFilter("Excel Files", "*.xlsx");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files", "*.pdf");
        fileChooser.getExtensionFilters().addAll(
                pdfFilter, excelFilter);

        // Show the file picker dialog
        Window stage = null;
        globselectedFile = fileChooser.showOpenDialog(null);
        System.out.println(globselectedFile);
        System.out.println(globselectedFile);
        if (globselectedFile == null) {
            browsepath.setText("");
        } else if (globselectedFile != null) {
            browsepath.setText(String.valueOf(String.valueOf(globselectedFile)));
        }
    }

    public void close1() throws SQLException {
        if (connection == null) {
            if (_serialPort != null) {
                _serialPort.closePort();
            }
            System.exit(0);
        } else {
            if (_serialPort != null) {
                _serialPort.closePort();
            }
            connection.close();
            System.exit(0);

        }

    }

    @FXML
    private void minimizeWindow() {
        Stage currentStage = (Stage) minimize.getScene().getWindow();
        currentStage.setIconified(true);
    }

    //.........................................................................................

    @FXML
    private void printPDF() throws IOException, SQLException, PrinterException {

        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene12 = new Scene(fxmlLoader1.load(), 500, 600);
        HelloController a1 = fxmlLoader1.getController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController secondController = loader.getController();
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/temp logo.jpg")));
        newStage.setTitle("Print Output");
        newStage.setScene(new Scene(root, 500, 600));
        newStage.show();

        String comName = proCoNmae.getText();
        String address = proAddress.getText();
        String comphon = proPhoNo.getText();
        String comemail = proEmId.getText();

        Arrays.fill(informationarray, null);
        System.out.println("heeeee");
        // Replace this with the path to your generated PDF file

        if (!isFieldEmpty(Tfd1)) {
            informationarray[0] = Tfd1.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd2)) {
            informationarray[1] = Tfd2.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd3)) {
            informationarray[2] = Tfd3.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd4)) {
            informationarray[3] = Tfd4.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd5)) {
            informationarray[4] = Tfd5.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd6)) {
            informationarray[5] = Tfd6.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd7)) {
            informationarray[6] = Tfd7.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd8)) {
            informationarray[7] = Tfd8.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd9)) {
            informationarray[8] = Tfd9.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd10)) {
            informationarray[9] = Tfd10.getText().trim() + "|";
        }

        secondController.setData(MAC_ID, fruits, time_of_recived_data, informationarray, comName, address, comemail, comphon);

        System.out.println("array  " + informationarray);
        a1.setData(MAC_ID, fruits, time_of_recived_data, informationarray, comName, address, comemail, comphon);
        AnchorPane v1 = new AnchorPane(scene12.getRoot());
        Stage stage = null;
        printAnchorPane(v1, stage);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String formattedDateTime00 = formatter12.format(now);
        if (bit0 == 1) {
//}
            Pdfgenrator Pdf1 = new Pdfgenrator(MAC_ID, fruits, time_of_recived_data, comName, address, comphon, comemail, informationarray);
            Pdf1.genratepdf(formattedDateTime00);    //get image from imageview of fxml
            String array2 = Arrays.toString(informationarray);
            String newTemp_info2 = array2.replace("|,", "|");
            String new_arr3 = newTemp_info2.replace("null,", "");
            String new_arr4 = new_arr3.replace("null", "");
            System.out.println("new array   " + new_arr4);
            addDataToTable(fruits, formate24hourse, new_arr4);
            bit0 = 0;
        }
        showAlertinfo("", "PDF File is saved at " + defaultPath + "\\Realogview\\DMM1.0\\Data\\PDF\\ \n" + fruits[0] + "_" + formattedDateTime00 + ".pdf");

    }

    private boolean isFieldEmpty(TextField textField) {
        return textField.getText().trim().isEmpty();
    }

    private boolean isFieldEmptytextarea(TextArea textField) {
        return textField.getText().trim().isEmpty();
    }

    private void printSetup(Node node, Stage owner) {
        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();
//        PageLayout pageLayout = printer.createPageLayout(Printer.MarginType.HARDWARE_MINIMUM);

        if (job == null) {
            return;
        }
        // Show the print setup dialog
        boolean proceed = job.showPrintDialog(owner);
        if (proceed) {
            double scaleX = node.getBoundsInParent().getWidth();
            double scaleY = node.getBoundsInParent().getHeight();
            double scaleFactor = Math.min(scaleX, scaleY);

            // Apply the scale transformation to the AnchorPane
//            node.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));

            print(job, node);
        }

    }

    private void printAnchorPane(AnchorPane anchorPane, Stage owner) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 3, 3, 3, 3);

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        if (job != null) {
            boolean proceed = job.showPrintDialog(owner);
            if (proceed) {
                // Calculate the scale factor to fit the AnchorPane to the page size
                double scaleX = pageLayout.getPrintableWidth() / anchorPane.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / anchorPane.getBoundsInParent().getHeight();
                double scaleFactor = Math.min(scaleX, scaleY);

                // Apply the scale transformation to the AnchorPane
                anchorPane.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
                System.out.println("hirifht " + scaleFactor);
                // Set the page layout for the print job
                job.getJobSettings().setPageLayout(pageLayout);

                // Print the AnchorPane
                boolean printed = job.printPage(anchorPane);

                if (printed) {
                    job.endJob();
                }

                // Remove the scale transformation after printing
                anchorPane.getTransforms().remove(anchorPane.getTransforms().size() - 1);
            }
        }
    }

    private void print(PrinterJob job, Node node) {
        // Set the Job Status Message
        jobStatus.textProperty().bind(job.jobStatusProperty().asString());
        // Print the node
        boolean printed = job.printPage(node);
        if (printed) {
            job.endJob();
        }
    }

    @FXML
    void editProf() throws IOException {

        if (proedit.getText().equals("Edit")) {
            proCoNmae.setDisable(false);
            proAddress.setDisable(false);
            proEmId.setDisable(false);
            proPhoNo.setDisable(false);
//            proeditlogo.setDisable(false);
            proupdate.setDisable(false);
//    proedit.setDisable(true);
            proedit.setText("Cancel");
        } else if (proedit.getText().equals("Cancel")) {
            String filepath01 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile + "/" + "UserProfileData.txt";
            Path file = Paths.get(filepath01);
            List<String> lines = Files.readAllLines(file);
            if (lines.isEmpty()) {
//                    showAlert("user data info","data is not update.");
                proCoNmae.setText(userCompanyName);
                proEmId.setText(userCompanyEmailId);
                proPhoNo.setText(userCompanyPhNo);
                proAddress.setText(userCompanyAddress);
            } else {
                String[] array = lines.toArray(new String[0]);
                System.out.println(array.length);
                proCoNmae.setText(array[0]);
                proEmId.setText(array[1]);
                proPhoNo.setText(array[2]);
                String addrdata = array[3].trim().replace("|", "\n");
                proAddress.setText(addrdata);
//                showAlert("user data info","data is not update.");
            }
            proCoNmae.setDisable(true);
            proAddress.setDisable(true);
            proEmId.setDisable(true);
            proPhoNo.setDisable(true);
//            proeditlogo.setDisable(true);
            proupdate.setDisable(true);
            proedit.setText("Edit");
        }

    }

    @FXML
    void updateprofile() throws IOException {
        int phl = proPhoNo.getText().length();
        String pcn = proCoNmae.getText();
        String ppn = proAddress.getText();
        System.out.println(pcn);
        if (!validateEmail(proEmId.getText()) || phl != 10 || isFieldEmpty(proCoNmae) || isFieldEmptytextarea(proAddress)) {
            if (isFieldEmpty(proCoNmae)) {
                showAlert("error", "Please enter Company Name.");
            } else if (!validateEmail(proEmId.getText())) {
                showAlert("error", " Please enter valid Email Id.");
            } else if (phl != 10) {
                showAlert("error", "Please enter correct Phone Number.");
            } else if (isFieldEmptytextarea(proAddress)) {
                showAlert("error", "Please enter Company Address.");
            } else {

            }
        } else {
            proCoNmae.setDisable(true);
            proAddress.setDisable(true);
            proEmId.setDisable(true);
            proPhoNo.setDisable(true);
//                proeditlogo.setDisable(true);
            proupdate.setDisable(true);
            proedit.setText("Edit");

            String[] listofuserdata = new String[4];
            listofuserdata[0] = proCoNmae.getText();
            listofuserdata[1] = proEmId.getText();
            listofuserdata[2] = proPhoNo.getText();
            listofuserdata[3] = proAddress.getText().replace("\n", "|");
            String filepath01 = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile + "/" + "UserProfileData.txt";
            Path file = Paths.get(filepath01);
            Files.write(file, Arrays.asList(listofuserdata), StandardOpenOption.CREATE);
            System.out.println("list  " + listofuserdata[3]);

            showAlertinfo(" ", "New User Profile saved.");
        }
    }

    @FXML
    public void changelogofun(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            tr.setImage(image);
            File folder1 = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
            File[] files2 = folder1.listFiles();
            for (File file : files2) {
                boolean dest1 = isImageFile(file);
                if (dest1) {
                    file.delete();
                } else {

                }
            }
            if (folder1.exists() && folder1.isDirectory()) {
                Path sourcePath = selectedFile.toPath();
                String fileName = selectedFile.getName();
                Path destinationPath = folder1.toPath().resolve(fileName);
                try {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Image saved to: " + destinationPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showAlertinfo(" ", "Logo has been updated.");
            } else {
                System.out.print("not folder available");
            }
        } else {
            System.out.println("no pinchre");

        }
    }


    // email function was static why???
    public void EXsendEmailWithAttachment(String smtpHost, String senderEmail, String senderPassword, String recipientEmail, String subject, String body, byte[] EXattachmentContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        Session session = Session.getInstance(properties, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(EXattachmentContent, "application/excel");
            messageBodyPart.setDataHandler(new DataHandler(source));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
            String formattedDateTime = formatter12.format(now);

            if (RedBtn1.isSelected()) {
                messageBodyPart.setFileName("From_" + FromDateForEm_Ex + "_To_" + ToDateForEm_Ex + "_" + formattedDateTime + ".xlsx");

            } else if (RedBtn2.isSelected()) {
                messageBodyPart.setFileName(formattedDateTime + ".xlsx");

            }
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    @FXML
    private void populateSerialPortList() throws IOException {

        dmmdatarivecerwindow.setVisible(false);
        conectionwindow.setVisible(true);
        generalinfoBox.setVisible(false);
        companyProfile.setVisible(false);
        historyDataBox.setVisible(false);

        profileButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
        gernaralinfoButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
        historyButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
    }


    public void cancleportfu(ActionEvent event) {
        portListView.getItems().clear();
        dmmdatarivecerwindow.setVisible(false);
        conectionwindow.setVisible(false);

    }


    public void porthandleMouseClick(MouseEvent mouseEvent) throws InterruptedException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {

        Portname = (String) portListView.getSelectionModel().getSelectedItem();
        System.out.println("clicked on " + Portname);
//        onselect1(Portname);
        try {
            onclickselection();
        } catch (SerialPortTimeoutException e) {
            System.out.println(e.toString());
            showAlert("Communication error", "Device is not connected.");
        }

    }

    void onclickselection() throws InterruptedException, IOException {

        if (Portname != null) {
            if (_serialPort != null) {
                _serialPort.closePort();
            }
            _serialPort = SerialPort.getCommPort(Portname);
            _serialPort.setBaudRate(19200);
            _serialPort.setNumDataBits(8);
            _serialPort.setNumStopBits(1);
            _serialPort.setParity(SerialPort.NO_PARITY);

            _serialPort.openPort();
            _serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 200, 100);
            OutputStream outputStream = _serialPort.getOutputStream();
            InputStream inputStream = _serialPort.getInputStream();
            byte[] buf = new byte[200];
//            TimeUnit.SECONDS.sleep(1/4);
            outputStream.write(hexStringToByteArray("CC"));
            TimeUnit.SECONDS.sleep(1 / 4);
            outputStream.write(hexStringToByteArray("AB"));
            TimeUnit.SECONDS.sleep(1 / 4);
            outputStream.write(hexStringToByteArray("AB"));

            if (_serialPort.bytesAvailable() == -1) {
//                Portname=null;
                disconnect_value = null;
                invisibleall();
                showAlert("Communication error", "Please select Proper Device Port.");
                _serialPort.closePort();
                return;
            }

//            byte[] readBuffer = new byte[_serialPort.bytesAvailable()];
            int bytesRead = inputStream.read(buf);
            System.out.println(bytesRead);
            System.out.println("string is  " + new String(buf, 0, bytesRead));

            String response = new String(buf, 0, bytesRead).trim();
            System.out.println("re len " + response.length());
            String[] response1 = response.split(",");

            System.out.println("ble len " + response1[0].length());
            if (response1[0].equals("BLE_READER")) {
                MAC_ID = response1[1].trim();
                String[] ab = response.split(",");
                statusbit = 2;
                Connectedstatus.setText(ab[1] + " is Connected");
                Disconnectedstatus.setText(ab[1] + " is Disconnected");
                disconnect_value = ab[1];
                deviceCon();
                portdisCoAle();
                System.out.println("yess logger1");
                data1.setText("");
                data2.setText("");
                data3.setText("");
                data4.setText("");
                data5.setText("");
                data7.setText("");
                data8.setText("");
                conectionwindow.setVisible(false);
                outputStream.close();
                inputStream.close();
                showAlertinfo(" ", ab[1] + " is connected on " + Portname + ".");
            } else if (response1[0].equals("DMM_PRINT_")) {

                MAC_ID = "";
                String[] ab = response.split(",");
                statusbit = 2;
                Connectedstatus.setText("20" + ab[1] + " is Connected");
                Disconnectedstatus.setText("20" + ab[1] + " is Disconnected");
                disconnect_value = ab[1];
                deviceCon();
                portdisCoAle();
                System.out.println("yess logger1");
                data1.setText("");
                data2.setText("");
                data3.setText("");
                data4.setText("");
                data5.setText("");
                data7.setText("");
                data8.setText("");
                conectionwindow.setVisible(false);
                outputStream.close();
                inputStream.close();
                showAlertinfo(" ", "20" + ab[1] + " is connected on " + Portname + ".");
            } else {
                disconnect_value = null;
                invisibleall();
                _serialPort.closePort();
//                openporttittle.setText("Open Port : ");
                showAlert("Communication error", "Please select Proper Device Port.");
            }
        } else if (Portname == null) {
            disconnect_value = null;
            invisibleall();
            showAlert("Communication error", "Please select Proper Device Port.");
//            openporttittle.setText("Open Port : ");
        } else {
            Portname = null;
//            openporttittle.setText("Open Port : ");
            showAlert("Communication error", "Please select Proper Device Port.");
        }
    }

    public void portgetdata(ActionEvent event) throws InterruptedException, IOException {
        bit0 = 1;
        fild_inc_val = 5;
        if (dmmdatarivecerwindow.isVisible()) {
            dmmdatarivecerwindow.setVisible(false);
        }
        conectionwindow.setVisible(false);
        Tfd6.setVisible(false);
        tfl6.setVisible(false);
        Tfd7.setVisible(false);
        tfl7.setVisible(false);
        Tfd8.setVisible(false);
        tfl8.setVisible(false);
        Tfd9.setVisible(false);
        tfl9.setVisible(false);
        Tfd10.setVisible(false);
        tfl10.setVisible(false);
        Tfd1.setText("");
        Tfd6.setText("");
        Tfd2.setText("");
        Tfd7.setText("");
        Tfd3.setText("");
        Tfd8.setText("");
        Tfd4.setText("");
        Tfd9.setText("");
        Tfd5.setText("");
        Tfd10.setText("");
        addtextfield.setDisable(false);

        if (Portname != null) {

            _serialPort.closePort();

            _serialPort = SerialPort.getCommPort(Portname);
            _serialPort.setBaudRate(19200);
            _serialPort.setNumDataBits(8);
            _serialPort.setNumStopBits(1);
            _serialPort.setParity(SerialPort.NO_PARITY);

            if (_serialPort.openPort()) {
                boolean rDIPort = false;
                try {
                    rDIPort = isrightdeviceport();
                } catch (SerialPortTimeoutException e) {
                    System.out.println(e.toString());
                    if (disconnect_value == null) {
                        showAlert("Communication error", "Device is not connected.");
                    }
                }

                if (rDIPort == false) {
                    deviceDisCon();
                    if (disconnect_value != null) {
                        showAlert("Communication error", disconnect_value + " is Disconnected.");
                    } else {
                        showAlert("Communication error", "Device is not connected.");
                    }
                    return;
                }
//                TimeUnit.SECONDS.sleep(1/3);
                _serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 500, 100);
                OutputStream outputStream = _serialPort.getOutputStream();
                InputStream inputStream = _serialPort.getInputStream();
                byte[] buf = new byte[1024];
//                TimeUnit.SECONDS.sleep(1/3);
                outputStream.write(hexStringToByteArray("CC"));
                TimeUnit.SECONDS.sleep(1 / 4);
                outputStream.write(hexStringToByteArray("C4"));
                TimeUnit.SECONDS.sleep(1 / 4);
                outputStream.write(hexStringToByteArray("C4"));

                if (_serialPort.bytesAvailable() == -1) {
                    deviceDisCon();
                    if (disconnect_value != null) {
                        showAlert("Communication error", disconnect_value + " is Disconnected.");
                    } else {
                        showAlert("Communication error", "Device is not connected.");
                    }
                    _serialPort.closePort();
                    return;
                }

                int bytesRead = inputStream.read(buf);
                System.out.println(bytesRead);
                System.out.println("string is  " + new String(buf, 0, bytesRead));
                String response = new String(buf, 0, bytesRead).trim();
                System.out.println("hee " + response);
                fruits = response.split(",");
//                _serialPort.closePort();
                data1.setText(MAC_ID);  //data from
                data2.setText(fruits[0]);   //sr no
                data3.setText(fruits[1]);    //comodi
                data4.setText(fruits[2] + " %");  //mistur
                data5.setText(fruits[3] + " °C");   //temp

                if (fruits[4].equals("FULL")) {
                    data8.setText(fruits[4]);
                } else {
                    data8.setText(fruits[4] + " gram");
                }

                //time create
                LocalDateTime currentDateTime = LocalDateTime.now();
                formate24hourse = currentDateTime.format(formattor24Hours);
                String formattedDateTime = currentDateTime.format(globeldtformatter);
                printtime = formattedDateTime;
                time_of_recived_data = formattedDateTime;
                data7.setText(formattedDateTime);

                PauseTransition delay = new PauseTransition(Duration.seconds(0.1));
                delay.setOnFinished(event1 -> {

                    if (!dmmdatarivecerwindow.isVisible()) {
                        dmmdatarivecerwindow.setVisible(true);
                    }
                });
                delay.play();

                generalinfoBox.setVisible(false);
                companyProfile.setVisible(false);
                historyDataBox.setVisible(false);
                profileButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
                gernaralinfoButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
                historyButton.setStyle("-fx-background-color:linear-gradient(to right, #ffffff, #ffffff);");
//                _serialPort.closePort();

            } else {
                deviceDisCon();
                if (disconnect_value != null) {
                    showAlert("Communication error", disconnect_value + " is Disconnected.");
                } else {
                    showAlert("Communication error", "Device is not connected.");
                }
            }
        } else if (Portname == null) {
            if (disconnect_value != null) {
                showAlert("Communication error", disconnect_value + " is Disconnected.");
            } else {
                showAlert("Communication error", "Device is not connected.");
            }

        } else {
            showAlert("Communication error", "Device is not connected");
        }

    }

    boolean butClick = false;

    boolean isrightdeviceport() throws InterruptedException, IOException {
        byte[] buf = new byte[200];

        _serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 200, 100);
        OutputStream outputStream = _serialPort.getOutputStream();
        InputStream inputStream = _serialPort.getInputStream();

//        TimeUnit.SECONDS.sleep(1/3);
        outputStream.write(hexStringToByteArray("CC"));
        TimeUnit.SECONDS.sleep(1 / 5);
        outputStream.write(hexStringToByteArray("AB"));
        TimeUnit.SECONDS.sleep(1 / 5);
        outputStream.write(hexStringToByteArray("AB"));

        if (_serialPort.bytesAvailable() == -1) {
            deviceDisCon();
            _serialPort.closePort();
            return false;
        }
        int bytesRead = inputStream.read(buf);
        System.out.println(bytesRead);
        System.out.println("string is  " + new String(buf, 0, bytesRead));

        String response = new String(buf, 0, bytesRead).trim();
        System.out.println("re len " + response.length());
        String[] response1 = response.split(",");
        System.out.println("ble len " + response1[0].length());
        if (response1[0].equals("BLE_READER")) {
            String[] ab = response.split(",");
//                Blemodulename=ab[1];
            Connectedstatus.setText(ab[1] + " is Connected");
            Disconnectedstatus.setText(ab[1] + " is Disconnected");
            disconnect_value = ab[1];
            statusbit = 2;
            deviceCon();
            portdisCoAle();
            System.out.println("yess logger");
            inputStream.close();
            outputStream.close();
            return true;
        } else if (response1[0].equals("DMM_PRINT_")) {
            MAC_ID = "";
            String[] ab = response.split(",");
//                Blemodulename=ab[1];
            Connectedstatus.setText("20" + ab[1] + " is Connected");
            Disconnectedstatus.setText("20" + ab[1] + " is Disconnected");
            disconnect_value = ab[1];
            statusbit = 2;
            deviceCon();
            portdisCoAle();
            System.out.println("yess logger");
            inputStream.close();
            outputStream.close();
            return true;
        } else {
            deviceDisCon();
            _serialPort.closePort();
            inputStream.close();
            outputStream.close();
            return false;
        }
    }

    public String disconnect_value;

    void portdisCoAle() {
        _serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return _serialPort.LISTENING_EVENT_PORT_DISCONNECTED;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() == _serialPort.LISTENING_EVENT_PORT_DISCONNECTED) {
                    Portname = null;
                    statusbit = 1;
                    deviceDisCon();

                    _serialPort.closePort();
                    Runnable functionToBeCalled = () -> showAlert("Communication error", disconnect_value + " is Disconnected");
                    Platform.runLater(functionToBeCalled);
//                        System.out.println("Read " + event.getEventType() + " bytes."+SerialPort.LISTENING_EVENT_PORT_DISCONNECTED);
                    return;
                }
            }
        });//268435456

    }

    void func1() {

    }

    void deviceDisCon() {
        if (progressindicator2.isVisible()) {
            Disconnectedstatus.setVisible(false);
            DisconnectedstatusSYB.setVisible(false);
            Connectedstatus.setVisible(false);
            ConnectedstatusSYB.setVisible(false);
            progressindicator2.setVisible(true);
        } else {
            Disconnectedstatus.setVisible(true);
            DisconnectedstatusSYB.setVisible(true);
            Connectedstatus.setVisible(false);
            ConnectedstatusSYB.setVisible(false);
            progressindicator2.setVisible(false);
        }

    }

    void deviceCon() {
        Disconnectedstatus.setVisible(false);
        DisconnectedstatusSYB.setVisible(false);
        ConnectedstatusSYB.setVisible(true);
        Connectedstatus.setVisible(true);
        progressindicator2.setVisible(false);
    }

    void progressShow() {
        DisconnectedstatusSYB.setVisible(false);
        ConnectedstatusSYB.setVisible(false);
        Disconnectedstatus.setVisible(false);
        Connectedstatus.setVisible(false);
        progressindicator2.setVisible(true);
    }

    void progressShownot() {

        if (statusbit == 1) {
            DisconnectedstatusSYB.setVisible(true);
            ConnectedstatusSYB.setVisible(false);
            Disconnectedstatus.setVisible(true);
            Connectedstatus.setVisible(false);
        } else if (statusbit == 2) {
            DisconnectedstatusSYB.setVisible(false);
            ConnectedstatusSYB.setVisible(true);
            Disconnectedstatus.setVisible(false);
            Connectedstatus.setVisible(true);
        } else {
            DisconnectedstatusSYB.setVisible(true);
            ConnectedstatusSYB.setVisible(false);
            Disconnectedstatus.setVisible(true);
            Connectedstatus.setVisible(false);
        }

        progressindicator2.setVisible(false);
    }

    void invisibleall() {

        DisconnectedstatusSYB.setVisible(true);
        ConnectedstatusSYB.setVisible(false);
        Disconnectedstatus.setVisible(true);
        Disconnectedstatus.setText("  Device is not Connected");
        Connectedstatus.setVisible(false);
        progressindicator2.setVisible(false);
    }

    @FXML
    public void sendemailpdf() throws IOException, InterruptedException {

        if (!isInternetConnected()) {
            showAlert("Network error", "Internet is not connected");
            return;
        }

        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "PDF Attachment";
        String body = "Please find attached the generated PDF.";

        String comName = proCoNmae.getText();

        String address = proAddress.getText();

        String comphon = proPhoNo.getText();

        String comemail = proEmId.getText();


        String[] informationarray1 = new String[10];
        Arrays.fill(informationarray1, null);
        if (!isFieldEmpty(Tfd1)) {
            informationarray1[0] = Tfd1.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd2)) {
            informationarray1[1] = Tfd2.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd3)) {
            informationarray1[2] = Tfd3.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd4)) {
            informationarray1[3] = Tfd4.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd5)) {
            informationarray1[4] = Tfd5.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd6)) {
            informationarray1[5] = Tfd6.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd7)) {
            informationarray1[6] = Tfd7.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd8)) {
            informationarray1[7] = Tfd8.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd9)) {
            informationarray1[8] = Tfd9.getText().trim() + "|";
        }
        if (!isFieldEmpty(Tfd10)) {
            informationarray1[9] = Tfd10.getText().trim() + "|";
        }

        System.out.println("array  " + informationarray1);

        try {
            String filemail = pdfemail1.getText();
            String filemail2 = pdfemail2.getText();
            if (!filemail.equals("") && filemail2.equals("")) {
                // Valid email addresses
                if (validateEmail(filemail)) {

                    byte[] pdfContent = generatePDFContent(fruits, comName, address, comphon, comemail, informationarray1);
                    String mail1 = pdfemail1.getText();
                    progressShow();
                    Task<Void> sendEmailTask = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            IsendEmailWithAttachment(smtpHost, senderEmail, senderPassword, mail1, subject, body, pdfContent);
                            for (int i = 0; i <= 100; i++) {
                                updateProgress(i, 100);
                                Thread.sleep(1); // Simulate email sending delay
                            }
                            return null;
                        }
                    };
                    progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                    sendEmailTask.setOnSucceeded(e -> {
                        progressShownot();
                        showAlertinfo("", "Pdf File sent on " + mail1 + "");
                    });
                    new Thread(sendEmailTask).start();

                } else {
                    showAlert("Email Id error", "Please enter valid  Email Id.");
                }
            } else if (!filemail.equals("") && !filemail2.equals("")) {
                // Invalid email addresses
                if (validateEmail(filemail) && validateEmails(filemail2)) {

                    byte[] pdfContent = generatePDFContent(fruits, comName, address, comphon, comemail, informationarray1);
                    String mail11 = pdfemail1.getText();
                    String mail2 = pdfemail2.getText();
                    progressShow();
                    Task<Void> sendEmailTask = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            indivipdfsend(mail11, mail2, pdfContent);
                            for (int i = 0; i <= 100; i++) {
                                updateProgress(i, 100);
                                Thread.sleep(1); // Simulate email sending delay
                            }
                            return null;
                        }
                    };

                    progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                    sendEmailTask.setOnSucceeded(e -> {
                        progressShownot();
                        showAlertinfo("", "Pdf File sent on " + mail11 + "," + mail2);
                    });
                    new Thread(sendEmailTask).start();

                    System.out.println("Email Addresses are valid: send files heare " + filemail);
                } else {
                    showAlert("Email Id error", "Please enter valid  Email Id.");
                }
            } else if (filemail.equals("") && filemail2.equals("")) {
                showAlert("Email Id error", "Please enter valid  Email Id.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void indivipdfsend(String mail2, String mail11, byte[] pdfContent) throws MessagingException {
        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "PDF Attachment";
        String body = "Please find attached the generated PDF.";
        String[] addresses = mail2.split(",");
        IsendEmailWithAttachment(smtpHost, senderEmail, senderPassword, mail11, subject, body, pdfContent);
        for (String address1 : addresses) {
            IsendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address1.trim(), subject, body, pdfContent);
        }
    }

    public byte[] generatePDFContent(String[] fruits, String comName, String address, String comPhone, String comEmail, String[] otherinformation) throws IOException {
        System.out.println(otherinformation[0]);
        Image image00 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage image1 = SwingFXUtils.fromFXImage(image00, null);
//        BufferedImage image1 = ImageIO.read(new File("src/main/resources/images/dmmlogo.jpg"));
        float ix1 = 20; // X-coordinate
        float iy1 = 20; // Y-coordinate
        float iwidth1 = 80; // Width
        float iheight1 = 30; // Height

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            //image
            float x = 20; // X-coordinate
            float y = 695; // Y-coordinate
            float width = 140; // Width
            float height = 80; // Height
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDImageXObject pdImage1 = LosslessFactory.createFromImage(document, image1);
            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
            //border
            PDRectangle pageSize = page.getMediaBox();
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();
            float borderWidth = 1; // Border width in points
            float[] borderColor = {0, 0, 0};
            contentStream.setLineWidth(borderWidth);
            contentStream.setStrokingColor(borderColor[0], borderColor[1], borderColor[2]);
            contentStream.addRect(borderWidth / 2, borderWidth / 2, pageWidth - borderWidth, pageHeight - borderWidth);
            contentStream.stroke();
            //add image
            File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);

            File[] files = folder.listFiles();
            for (File file : files) {
                if (isImageFile(file)) {
                    BufferedImage image = ImageIO.read(file);
                    PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
                    contentStream.drawImage(pdImage, x, y, width, height);
                    break;
                } else {
                    Image image02 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                    BufferedImage image01 = SwingFXUtils.fromFXImage(image02, null);
//                    BufferedImage image01 = ImageIO.read(new File("src/main/resources/images/white_Image.png"));
                    PDImageXObject pdImage01 = LosslessFactory.createFromImage(document, image01);
                    contentStream.drawImage(pdImage01, x, y, width, height);
                }
            }
            //lines
            float startX = 30; // X-coordinate of the starting point
            float startY = 660; // Y-coordinate of the starting point
            float endX = 580; // X-coordinate of the ending point
            float endY = 660; // Y-coordinate of the ending point
            float lineWidth = 1; // Line width in points
            float[] lineColor = {0, 0, 0}; //
            contentStream.setLineWidth(lineWidth);
            contentStream.setStrokingColor(lineColor[0], lineColor[1], lineColor[2]);
            contentStream.moveTo(startX, startY);
            contentStream.lineTo(endX, endY);
            contentStream.stroke();

            //line2
            float startX1 = 30; // X-coordinate of the starting point
            float startY1 = 50; // Y-coordinate of the starting point
            float endX1 = 580; // X-coordinate of the ending point
            float endY1 = 50; // Y-coordinate of the ending point
            float lineWidth1 = 1; // Line width in points
            float[] lineColor1 = {0, 0, 0}; //
            contentStream.setLineWidth(lineWidth1);
            contentStream.setStrokingColor(lineColor1[0], lineColor1[1], lineColor1[2]);
            contentStream.moveTo(startX1, startY1);
            contentStream.lineTo(endX1, endY1);
            contentStream.stroke();

            //header
            float x3 = 180;
            float y3 = 730;
            float x1 = 210;
            float y1 = 715;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset(x3, y3);
            contentStream.showText(comName);
            contentStream.endText();

            String newaddress = address.trim().replaceAll("\n", " ");
            System.out.println(newaddress);
            String[] newStrg1 = address.split("\n");
            for (String line : newStrg1) {
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.beginText();
                contentStream.newLineAtOffset(x1, y1);
                contentStream.showText(line + " ,");
                contentStream.endText();
                y1 -= 11;
            }

            float emX = 440;
            float emy = 770;
            float pmX = 440;
            float pmy = 755;

            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(emX, emy);
            contentStream.showText("Email Id:  " + comEmail);
            contentStream.endText();

            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(pmX, pmy);
            contentStream.showText("Ph No:  " + comPhone);
            contentStream.endText();


            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 630);
            contentStream.showText("Other Information:");
            contentStream.endText();

            int otherinfoIncre = 0;
            float loopy4 = 610;
            for (String line : otherinformation) {
                if (line != null) {
                    System.out.println("lines   " + line);
                    contentStream.setFont(PDType1Font.HELVETICA, 10);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, loopy4);
                    contentStream.showText(line.replace("|", ",").trim());
                    contentStream.endText();
                    loopy4 -= 14;
                    otherinfoIncre += 1;
                }
            }
            System.out.println(otherinfoIncre);

            float mstartY1 = 460;
            float mendY1 = 460;
            float loopy = 430;
            float loopyDmm = 430;
            if (otherinfoIncre <= 5) {
                mstartY1 = 520;
                mendY1 = 520;
                loopy = 490;
                loopyDmm = 490;

            } else {

            }
            //line2mid
            float mstartX1 = 200; // X-coordinate of the starting point
            // Y-coordinate of the starting point
            float mendX1 = 450; // X-coordinate of the ending point
            // Y-coordinate of the ending point
            float mlineWidth1 = 0.4F;
            float[] mlineColor1 = {0, 0, 0}; //
            contentStream.setLineWidth(mlineWidth1);
            contentStream.setStrokingColor(mlineColor1[0], mlineColor1[1], mlineColor1[2]);
            contentStream.moveTo(mstartX1, mstartY1);
            contentStream.lineTo(mendX1, mendY1);
            contentStream.stroke();

            //device mesured data
            float loopx = 110;

            String[] cars = {"Mac ID :", "Serial No :", "Commodity Name :", "Moisture  :", "Sample Temperature :", "Time :", "Sample Quantity Required :"};
            for (String i : cars) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(loopx, loopy);
                contentStream.showText(i);
                contentStream.endText();
                loopy -= 24;
            }
//            // serial data printing
            float loopx2 = 185;
            System.out.println(fruits[0]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2, loopyDmm);
            contentStream.showText(MAC_ID);
            contentStream.endText();

            System.out.println(fruits[0]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2, loopyDmm - 24);
            contentStream.showText(fruits[0]);
            contentStream.endText();

            System.out.println(fruits[1]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(240, loopyDmm - 48);
            contentStream.showText(fruits[1]);
            contentStream.endText();
//
            System.out.println(fruits[2]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 15, loopyDmm - 72);
            contentStream.showText(fruits[2] + " %");
            contentStream.endText();
//
            System.out.println(fruits[3]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 60, loopyDmm - 96);
            contentStream.showText(fruits[3] + " °C");
            contentStream.endText();
//
            LocalDateTime currentDateTime2 = LocalDateTime.now();
            String formattedDateTime2 = currentDateTime2.format(globeldtformatter);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 - 10, loopyDmm - 120);
            contentStream.showText(time_of_recived_data);
            contentStream.endText();
//
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(290, loopyDmm - 144);
            if (fruits[4].equals("FULL")) {
                contentStream.showText(fruits[4]);
                contentStream.endText();
            } else {
                contentStream.showText(fruits[4] + " gram");
                contentStream.endText();
            }

            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(150, 40);
            contentStream.showText("Measured in Digital Moisture Meter By Innovative Instruments,vadodara,gujarat,india.");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(250, 30);
            contentStream.showText("Visit Us : www.innovative-instruments.in ");
            contentStream.endText();

            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanAssembleDocument(true); // Deny document assembly
            accessPermission.setCanExtractContent(false); // Deny content extraction
            accessPermission.setCanModify(false); // Deny document modification
            accessPermission.setCanModifyAnnotations(false); // Deny annotation modification
            accessPermission.setCanFillInForm(false); // Deny form filling
            accessPermission.setCanPrint(true); // Deny printing

            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(null, null, accessPermission);
            protectionPolicy.setEncryptionKeyLength(128); // Key length for encryption

            document.protect(protectionPolicy);

            contentStream.close();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();
            return outputStream.toByteArray();
        }
    }

    public void sendEmailWithAttachment(String smtpHost, String senderEmail, String senderPassword, String recipientEmail, String subject, String body, byte[] attachmentContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        Session session = Session.getInstance(properties, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(attachmentContent, "application/pdf");
            messageBodyPart.setDataHandler(new DataHandler(source));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
            String formattedDateTime = formatter12.format(now);
            if (RedBtn1.isSelected()) {
                messageBodyPart.setFileName("From_" + FromDateForEm_Ex + "_To_" + ToDateForEm_Ex + "_" + formattedDateTime + ".pdf");
            } else if (RedBtn2.isSelected()) {
                messageBodyPart.setFileName(formattedDateTime + ".pdf");
            }

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void IsendEmailWithAttachment(String smtpHost, String senderEmail, String senderPassword, String recipientEmail, String subject, String body, byte[] attachmentContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        Session session = Session.getInstance(properties, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(attachmentContent, "application/pdf");
            messageBodyPart.setDataHandler(new DataHandler(source));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
            String formattedDateTime = formatter12.format(now);
            String pdffilename = fruits[0] + "_" + formattedDateTime;
            messageBodyPart.setFileName("" + pdffilename + ".pdf");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void addDataToTable(String[] data, String DT, String OthInfo) throws SQLException {

        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String tablecreate = "CREATE TABLE IF NOT EXISTS DMM" + year + " (id INTEGER PRIMARY KEY AUTOINCREMENT, SerialNo TEXT,MACId TEXT,CommodityName TEXT ,Moisture Text,Temperature Text, DateTime TEXT, SaQunReqid TEXT,OtherInfo TEXT)";
        try (PreparedStatement statement = connection.prepareStatement(tablecreate)) {
            statement.execute();
            System.out.println("User table created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO DMM" + year + "  (SerialNo,MACId,CommodityName,Moisture,Temperature,DateTime,SaQunReqid,OtherInfo) VALUES (?,?,?,?,?,?,?,?)");
        insertStatement.setString(1, data[0]);
        insertStatement.setString(2, MAC_ID);
        insertStatement.setString(3, data[1]);
        insertStatement.setString(4, data[2]);
        insertStatement.setString(5, data[3]);
        insertStatement.setString(6, DT);
        insertStatement.setString(7, data[4]);
        insertStatement.setString(8, OthInfo);
        insertStatement.execute();


    }

    public int getduration() {
        LocalDate startDate = DateFrom.getValue();
        LocalDate endDate = DateTo.getValue();
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        return (int) daysBetween;

    }

    public void addtextfieldFunc(ActionEvent actionEvent) {

        if (fild_inc_val == 5) {
            Tfd6.setVisible(true);
            tfl6.setVisible(true);
        }
        if (fild_inc_val == 6) {
            Tfd7.setVisible(true);
            tfl7.setVisible(true);
        }
        if (fild_inc_val == 7) {
            Tfd8.setVisible(true);
            tfl8.setVisible(true);
        }
        if (fild_inc_val == 8) {
            Tfd9.setVisible(true);
            tfl9.setVisible(true);
        }
        if (fild_inc_val == 9) {
            Tfd10.setVisible(true);
            tfl10.setVisible(true);
            addtextfield.setDisable(true);

        }
        fild_inc_val += 1;
    }

    private String getStringToLocalDateTime(String dateStr) {
        // Create a DateTimeFormatter object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY hh:mm:ss a");

        // Convert the string to a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        return dateTime.toString();
    }

    boolean checkDataisAvailable(int matchyear, String fdate, String fdate1) throws SQLException {
        String sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime >= ? AND DateTime <= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fdate);
        statement.setString(2, fdate1);
        ResultSet result1 = statement.executeQuery();

        if (result1.next()) {
            return true;
        } else {
            return false;
        }
    }


    boolean checkDataisAvailableforTwo(int matchyear, int matchyear1, String fdate, String fdate1) throws SQLException {
        ResultSet result2;
        ResultSet result21;
        String sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime > ? AND DateTime < ?";
        String sql2 = "SELECT * FROM DMM" + matchyear1 + " WHERE DateTime > ? AND DateTime < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fdate);
        statement.setString(2, fdate1);
        PreparedStatement statement1 = connection.prepareStatement(sql2);
        statement1.setString(1, fdate);
        statement1.setString(2, fdate1);
        result2 = statement.executeQuery();
        result21 = statement1.executeQuery();
        List<ResultSet> resultSets = new ArrayList<>();
        resultSets.add(result2);
        resultSets.add(result21);
        ResultSet combinedResultSet = mergeResultSets(resultSets);

        if (combinedResultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void ViewDataFunc(ActionEvent actionEvent) throws SQLException, IOException {
//        System.out.println("from  555  "+DateFrom.getValue());
        if (RedBtn1.isSelected()) {
            boolean hasTables = SQLiteTableChecker.hasTables();
            if (hasTables) {

                if (from_Date == true && to_Date == true) {
                    LocalDate startDate = DateFrom.getValue();
                    LocalDate endDate = DateTo.getValue();
                    long duration60 = getduration();
                    System.out.println("d  " + duration60);
                    String date = String.valueOf(startDate);
                    String date2 = String.valueOf(endDate);
                    System.out.println(date);
                    long dration = 60;
                    if (duration60 > dration || startDate.isAfter(endDate)) {
                        if (duration60 > dration) {
                            showAlert("error", "The Maximum Duration for Date selection is 60 Days.");
                        } else if (startDate.isAfter(endDate)) {
                            showAlert("error", " Selected Dates are not Proper.");
                        } else {

                        }
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("tabledisplay.fxml"));
                        Parent root = loader.load();
                        Tabledisplay secondController = loader.getController();
                        ObservableList<TableModel> TableModels = FXCollections.observableArrayList();
                        Stage newStage = new Stage();
                        newStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/temp logo.jpg")));
                        newStage.setTitle("Table View");
                        Screen screen = Screen.getPrimary();
                        newStage.setScene(new Scene(root, 1200, 531));


                        LocalDate selectedDate = DateFrom.getValue();
                        DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
                        LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
                        String fdate = datefro.format(formatterpiker);
                        LocalDate selectedDate1 = DateTo.getValue();
                        LocalDateTime datefro1 = LocalDateTime.of(selectedDate1, LocalTime.MAX);
                        String fdate1 = datefro1.format(formatterpiker);

                        int matchyear = selectedDate.getYear();
                        int matchyear1 = selectedDate.getYear();

                        String sql;
                        String sql2;
                        ResultSet result1;
                        ResultSet result2;
                        ResultSet result21;

                        if (matchyear == matchyear1) {

                            boolean cDa = checkDataisAvailable(matchyear, fdate, fdate1);
                            if (cDa == false) {
                                showAlert("DataBase error", "No Record found for selected Duration.");
                                return;
                            }
                            sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime >= ? AND DateTime <= ?";
                            PreparedStatement statement = connection.prepareStatement(sql);
                            statement.setString(1, fdate);
                            statement.setString(2, fdate1);
                            result1 = statement.executeQuery();
                            System.out.println("actual table created hear");
                            String[] allusedata = new String[4];

                            if (proCoNmae.getText().isEmpty()) {
                                allusedata[0] = "Company Name";
                            } else {
                                allusedata[0] = proCoNmae.getText();
                            }
                            if (proAddress.getText().isEmpty()) {
                                allusedata[1] = "Company Address";
                            } else {
                                allusedata[1] = proAddress.getText();
                            }
                            if (proPhoNo.getText().isEmpty()) {
                                allusedata[2] = "Phone No";
                            } else {
                                allusedata[2] = proPhoNo.getText();
                            }
                            if (proEmId.getText().isEmpty()) {
                                allusedata[3] = "Email Id";
                            } else {
                                allusedata[3] = proEmId.getText();
                            }

                            String[] attayOfResultsetoneYear = new String[5];
                            attayOfResultsetoneYear[0] = String.valueOf(matchyear);
                            attayOfResultsetoneYear[1] = fdate;
                            attayOfResultsetoneYear[2] = fdate1;
                            attayOfResultsetoneYear[3] = FromDateForEm_Ex;
                            attayOfResultsetoneYear[4] = ToDateForEm_Ex;

                            oneYearResultHolder.setResultdataforOneyear(attayOfResultsetoneYear);
                            comnameHoder.setComNmae(allusedata);

                            secondController.setcompanyname(comnameHoder);
                            secondController.oneyeardataArray(oneYearResultHolder);
                            int Tid = 1;

                            while (result1.next()) {
                                String r1 = result1.getString("SerialNo");
                                System.out.println("srno----   " + r1);
                                String r2 = result1.getString("MACId");
                                String r3 = result1.getString("CommodityName");
                                String r4 = result1.getString("Moisture");
                                String r5 = result1.getString("Temperature");
                                String r6 = result1.getString("DateTime");
                                LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                                String nr6 = localDateTime.format(globeldtformatter);
                                String r7 = result1.getString("SaQunReqid");
                                String r8 = result1.getString("OtherInfo");

                                String ab2 = r8.substring(0, r8.length() - 1);
                                String modifiedString = ab2.substring(1);
                                String newr8 = modifiedString.replace("|", ",\n");
                                TableModels.add(new TableModel(Tid + "", r1 + "", r2 + "", r3 + "", r4 + "", r5 + "", nr6 + "", r7 + "", newr8 + ""));
                                Tid += 1;
                            }
                            secondController.TableView1.setItems(TableModels);
                            newStage.show();
                        } else {
                            boolean cDa = checkDataisAvailableforTwo(matchyear, matchyear1, fdate, fdate1);
                            if (cDa == false) {
                                showAlert("DataBase error", "No Record found for selected Duration.");
                                return;
                            }
                            //Check here what is error
                            sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime > ? AND DateTime < ?";
                            sql2 = "SELECT * FROM DMM" + matchyear1 + " WHERE DateTime > ? AND DateTime < ?";
                            PreparedStatement statement = connection.prepareStatement(sql);
                            statement.setString(1, fdate);
                            statement.setString(2, fdate1);
                            PreparedStatement statement1 = connection.prepareStatement(sql2);
                            statement1.setString(1, fdate);
                            statement1.setString(2, fdate1);
                            result2 = statement.executeQuery();
                            result21 = statement1.executeQuery();
                            List<ResultSet> resultSets = new ArrayList<>();
                            resultSets.add(result2);
                            resultSets.add(result21);
                            ResultSet combinedResultSet = mergeResultSets(resultSets);

                            String[] allusedata = new String[4];
                            if (proCoNmae.getText().isEmpty()) {
                                allusedata[0] = "Company Name";
                            } else {
                                allusedata[0] = proCoNmae.getText();
                            }
                            if (proAddress.getText().isEmpty()) {
                                allusedata[1] = "Company Address";
                            } else {
                                allusedata[1] = proAddress.getText();
                            }
                            if (proPhoNo.getText().isEmpty()) {
                                allusedata[2] = "Phone No";
                            } else {
                                allusedata[2] = proPhoNo.getText();
                            }
                            if (proEmId.getText().isEmpty()) {
                                allusedata[3] = "Email Id";
                            } else {
                                allusedata[3] = proEmId.getText();
                            }

                            String[] attayOfResultseTwoYear = new String[6];
                            attayOfResultseTwoYear[0] = String.valueOf(matchyear);
                            attayOfResultseTwoYear[1] = String.valueOf(matchyear1);
                            attayOfResultseTwoYear[2] = fdate;
                            attayOfResultseTwoYear[3] = fdate1;
                            attayOfResultseTwoYear[4] = FromDateForEm_Ex;
                            attayOfResultseTwoYear[5] = ToDateForEm_Ex;

                            twoYearResultHolder.setResultdataforTwoyear(attayOfResultseTwoYear);
                            comnameHoder.setComNmae(allusedata);

                            secondController.setcompanyname(comnameHoder);
                            secondController.twoyeardataArray(twoYearResultHolder);
                            int Tid = 1;

                            while (combinedResultSet.next()) {
                                String r1 = combinedResultSet.getString("SerialNo");
                                String r2 = combinedResultSet.getString("MACId");
                                String r3 = combinedResultSet.getString("CommodityName");
                                String r4 = combinedResultSet.getString("Moisture");
                                String r5 = combinedResultSet.getString("Temperature");
                                String r6 = combinedResultSet.getString("DateTime");
                                LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                                String nr6 = localDateTime.format(globeldtformatter);
                                String r7 = combinedResultSet.getString("SaQunReqid");
                                String r8 = combinedResultSet.getString("OtherInfo");

                                String ab2 = r8.substring(0, r8.length() - 1);
                                String modifiedString = ab2.substring(1);
                                String newr8 = modifiedString.replace("|", ",\n");
                                TableModels.add(new TableModel(Tid + "", r1 + "", r2 + "", r3 + "", r4 + "", r5 + "", nr6 + "", r7 + "", newr8 + ""));
                                Tid += 1;
                            }
                            secondController.TableView1.setItems(TableModels);
                            newStage.show();
                        }

                    }
                } else {
                    if (DateFrom.getValue() == null) {
                        showAlert("DataBase error", "Records not found for selected \"FROM\" Date ");
                    } else if (DateTo.getValue() == null) {
                        showAlert("DataBase error", "Records not found for selected \"To\" Date ");
                    } else if (from_Date == false) {
                        showAlert("DataBase error", "Records not found for selected \"FROM\" Date ");
                    } else if (to_Date == false) {
                        showAlert("DataBase error", "Records not found for selected \"To\" Date ");
                    } else {

                    }
                }

                //dd
            } else {
                showAlert("DataBase error", "No Records found");
            }
        } else if (RedBtn2.isSelected()) {

            boolean hasTables = SQLiteTableChecker.hasTables();
            if (hasTables) {
                String lastdata = GetlastTableDate();
                LocalDateTime dateTime1 = LocalDateTime.parse(lastdata, formattor24Hours);
                LocalDate Ldate = dateTime1.toLocalDate();
                LocalDate Ltoday = LocalDate.now();
                if (Ldate.equals(Ltoday)) {
                    System.out.println("last dataa a " + lastdata);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("tabledisplay.fxml"));
                    Parent root = loader.load();
//            Scene scene = new Scene(loader, 898, 562);
                    Tabledisplay secondController = loader.getController();
                    ObservableList<TableModel> TableModels = FXCollections.observableArrayList();

                    Stage newStage = new Stage();
                    newStage.setTitle("Table View");
                    newStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/temp logo.jpg")));
                    Screen screen = Screen.getPrimary();
                    newStage.setScene(new Scene(root, 1200, 531));
                    newStage.show();

                    DateTimeFormatter nowdatformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter nowdatformat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    LocalDate today = LocalDate.now();
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIN);

                    String fdate = todayStart.format(nowdatformat);
                    String fdate1 = now.format(nowdatformat2);

                    LocalDate currentDate = LocalDate.now();
                    String year = String.valueOf(currentDate.getYear());
                    String sql = "SELECT * FROM DMM" + year + " WHERE DateTime > ? AND DateTime < ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, fdate);
                    statement.setString(2, fdate1);
                    System.out.println(fdate);
                    System.out.println(fdate1);
                    ResultSet resultSet1 = statement.executeQuery();
                    System.out.println("actual table created hear");


                    String[] allusedata = new String[4];
                    if (proCoNmae.getText().isEmpty()) {
                        allusedata[0] = "Company Name";
                    } else {
                        allusedata[0] = proCoNmae.getText();
                    }
                    if (proAddress.getText().isEmpty()) {
                        allusedata[1] = "Company Address";
                    } else {
                        allusedata[1] = proAddress.getText();
                    }
                    if (proPhoNo.getText().isEmpty()) {
                        allusedata[2] = "Phone No";
                    } else {
                        allusedata[2] = proPhoNo.getText();
                    }
                    if (proEmId.getText().isEmpty()) {
                        allusedata[3] = "Email Id";
                    } else {
                        allusedata[3] = proEmId.getText();
                    }

                    String[] attayOfResultsettodayYear = new String[3];
                    attayOfResultsettodayYear[0] = year;
                    attayOfResultsettodayYear[1] = fdate;
                    attayOfResultsettodayYear[2] = fdate1;

                    todayresultHolder.setResultdatafortoday(attayOfResultsettodayYear);
                    comnameHoder.setComNmae(allusedata);

                    secondController.setcompanyname(comnameHoder);
                    secondController.todayArraydata(todayresultHolder);
                    int Tid = 1;
                    while (resultSet1.next()) {

                        String r1 = resultSet1.getString("SerialNo");
                        String r2 = resultSet1.getString("MACId");
                        String r3 = resultSet1.getString("CommodityName");
                        String r4 = resultSet1.getString("Moisture");
                        String r5 = resultSet1.getString("Temperature");
                        String r6 = resultSet1.getString("DateTime");
                        LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                        String nr6 = localDateTime.format(globeldtformatter);
                        String r7 = resultSet1.getString("SaQunReqid");/**/
                        String r8 = resultSet1.getString("OtherInfo");

                        String ab2 = r8.substring(0, r8.length() - 1);
                        String modifiedString = ab2.substring(1);
                        String newr8 = modifiedString.replace("|", ",\n");
                        TableModels.add(new TableModel(Tid + "", r1 + "", r2 + "", r3 + "", r4 + "", r5 + "", nr6 + "", r7 + "", newr8 + ""));
                        Tid += 1;
                    }
//            System.out.println("dgddfg    "+TableModels.get(1));
                    secondController.TableView1.setItems(TableModels);
                    System.out.println("today data show");

                } else {
                    showAlert("DataBase error", "Records not found ");
                }

            } else {
                showAlert("DataBase error", "No Records found");
            }


        } else if (RedBtn3.isSelected()) {

            if (globselectedFile != null) {
                // Open the selected PDF file in a PDF viewer
                try {
                    Desktop.getDesktop().open(globselectedFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert("error", "Please select File ");
            }

        }
    }

    private String getStringDateToDate(String dateStr) {
        // Create a SimpleDateFormat object for yyyy-MM-dd HH:mm:ss format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

        // Convert the string to a Date object
        Date date = null;
        try {
            date = (Date) sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date.toString();
    }

    public ResultSet getallresultdata(String year, String fdate1, String fdate) throws SQLException {

//        List<DataModel> dataList = new ArrayList<>();

        String sql = "SELECT * FROM DMM" + year + " WHERE DateTime > ? AND DateTime < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fdate1);
        statement.setString(2, fdate);
        System.out.println(fdate1);
        System.out.println(fdate);
        ResultSet resultSet1 = statement.executeQuery();

        return resultSet1;
    }

    public ResultSet getallresultdatafortowyears(String year, String year1, String fdate, String fdate1) throws SQLException {

//        List<DataModel> dataList = new ArrayList<>();
        ResultSet result2;
        ResultSet result21;
        String sql = "SELECT * FROM DMM" + year + " WHERE DateTime > ? AND DateTime < ?";
        String sql2 = "SELECT * FROM DMM" + year1 + " WHERE DateTime > ? AND DateTime < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fdate);
        statement.setString(2, fdate1);
        PreparedStatement statement1 = connection.prepareStatement(sql2);
        statement1.setString(1, fdate);
        statement1.setString(2, fdate1);
        result2 = statement.executeQuery();
        result21 = statement1.executeQuery();
        List<ResultSet> resultSets = new ArrayList<>();
        resultSets.add(result2);
        resultSets.add(result21);
        ResultSet combinedResultSet = mergeResultSets(resultSets);

        return combinedResultSet;
    }

    public void sharechoosenfile(ActionEvent actionEvent) throws IOException, InterruptedException {
        if (!isInternetConnected()) {
            showAlert("Network error", "Internet is not connected");
            return;
        }
        if (globselectedFile == null || globselectedFile.equals("")) {
            showAlert("error", "Please select File");
        } else {
            String filemail = exmail1.getText();
            String filemail2 = exmail2.getText();
            if (!filemail.equals("") && filemail2.equals("")) {
                // Valid email addresses
                if (validateEmail(filemail)) {

                    Task<Void> sendEmailTask = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            sendEmailWithAttachmentfile(globselectedFile, filemail);
                            for (int i = 0; i <= 100; i++) {
                                updateProgress(i, 100);
                                Thread.sleep(1); // Simulate email sending delay
                            }
                            return null;
                        }
                    };

                    progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                    sendEmailTask.setOnSucceeded(e -> {
                        showAlertinfo("", "Selected File sent on " + filemail + "");
                    });
                    new Thread(sendEmailTask).start();

                    System.out.println("Email Addresses are valid: send file hare " + filemail);

                } else {
                    showAlert("Email Id error", "Please enter valid  Email Id.");

                }

            } else if (!filemail.equals("") && !filemail2.equals("")) {
                // Invalid email addresses

                if (validateEmail(filemail) && validateEmails(filemail2)) {

                    Task<Void> sendEmailTask = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            sendselectedfiles(filemail, filemail2);
                            for (int i = 0; i <= 100; i++) {
                                updateProgress(i, 100);
                                Thread.sleep(1); // Simulate email sending delay
                            }
                            return null;
                        }
                    };

                    progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                    sendEmailTask.setOnSucceeded(e -> {
                        showAlertinfo("", "Selected File sent on " + filemail + "," + filemail2);
                    });
                    new Thread(sendEmailTask).start();

                    System.out.println("Email Addresses are valid: send files heare " + filemail);
                } else {
                    showAlert("Email Id error", "Please enter valid  Email Id.");

                }

            } else {
                showAlert("Email Id error", "Please enter valid  Email Id.");
            }

        }
    }

    private boolean validateEmails(String ccEmails) {
        String[] emailAddresses = ccEmails.split(",");
        for (String email : emailAddresses) {
            if (!validateEmail(email.trim())) {
                return false;
            }
        }
        return true;
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
//        alert.getDialogPane().setMinSize(300,500);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertwarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertinfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinSize(300, 150);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertconf(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void sendasPDF(ActionEvent actionEvent) throws SQLException, MessagingException, IOException, InterruptedException {
        if (!isInternetConnected()) {
            showAlert("Network error", "Internet is not connected");
            return;
        }
        if (RedBtn1.isSelected()) {
            boolean hasTables = SQLiteTableChecker.hasTables();
            if (hasTables) {
                if (from_Date == true && to_Date == true) {

                    LocalDate startDate = DateFrom.getValue();
                    LocalDate endDate = DateTo.getValue();
                    long duration60 = getduration();
                    System.out.println("d  " + duration60);
                    String date = String.valueOf(startDate);
                    String date2 = String.valueOf(endDate);
                    System.out.println(date);
                    long dration = 60;
                    if (duration60 > dration || startDate.isAfter(endDate)) {
                        if (duration60 > dration) {
                            showAlert("error", "The Maximum Duration for Date selection is 60 Days.");
                        } else if (startDate.isAfter(endDate)) {
                            showAlert("error", "Selected Dates are not Proper");
                        }
                    } else {

                        LocalDate selectedDate = DateFrom.getValue();
                        DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
                        LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
                        String fdate = datefro.format(formatterpiker);
                        LocalDate selectedDate1 = DateTo.getValue();
                        LocalDateTime datefro1 = LocalDateTime.of(selectedDate1, LocalTime.MAX);
                        String fdate1 = datefro1.format(formatterpiker);

                        int matchyear = selectedDate.getYear();
                        int matchyear1 = selectedDate.getYear();

                        if (matchyear == matchyear1) {

                            boolean cDa = checkDataisAvailable(matchyear, fdate, fdate1);
                            if (cDa == false) {
                                showAlert("DataBase error", "No Record found for selected Duration.");
                                return;
                            }
                        } else {
                            boolean cDa = checkDataisAvailableforTwo(matchyear, matchyear1, fdate, fdate1);
                            if (cDa == false) {
                                showAlert("DataBase error", "No Record found for selected Duration.");
                                return;
                            }
                        }

                        String filemail = exmail1.getText();
                        String filemail2 = exmail2.getText();
                        if (!filemail.equals("") && filemail2.equals("")) {
                            // Valid email addresses
                            if (validateEmail(filemail)) {
                                progressShow();
                                Task<Void> sendEmailTask = new Task<Void>() {
                                    @Override
                                    protected Void call() throws Exception {
                                        sharepdf_after_validate(filemail);
                                        for (int i = 0; i <= 100; i++) {
                                            updateProgress(i, 100);
                                            Thread.sleep(1); // Simulate email sending delay
                                        }
                                        return null;
                                    }
                                };

                                progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                                sendEmailTask.setOnSucceeded(e -> {
                                    progressShownot();
                                    showAlertinfo("", "Pdf File sent on " + filemail + ",");
                                });
                                new Thread(sendEmailTask).start();

                                System.out.println("Email Addresses are valid: send file hare " + filemail);

                            } else {
                                showAlert("Email Id error", "Please enter valid  Email Id.");
                            }
                        } else if (!filemail.equals("") && !filemail2.equals("")) {
                            // Invalid email addresses
                            if (validateEmail(filemail) && validateEmails(filemail2)) {

                                progressShow();
                                Task<Void> sendEmailTask = new Task<Void>() {
                                    @Override
                                    protected Void call() throws Exception {
                                        sendpdfs(filemail, filemail2);
                                        for (int i = 0; i <= 100; i++) {
                                            updateProgress(i, 100);
                                            Thread.sleep(1); // Simulate email sending delay
                                        }
                                        return null;
                                    }
                                };

                                progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                                sendEmailTask.setOnSucceeded(e -> {
                                    progressShownot();
                                    showAlertinfo("", "Pdf File sent on " + filemail + "," + filemail2);
                                });
                                new Thread(sendEmailTask).start();

                                System.out.println("Email Addresses are valid: send files heare " + filemail);
                            } else {

                                showAlert("Email Id error", "Please enter valid  Email Id.");

                            }
                        } else {
                            showAlert("Email Id error", "Please enter valid  Email Id.");
                        }
                        System.out.println("actual pdf created hear");

                    }
                } else {
                    if (DateFrom.getValue() == null) {
                        showAlert("DataBase error", "Records not found for selected \"FROM\" Date ");
                    } else if (DateTo.getValue() == null) {
                        showAlert("DataBase error", "Records not found for selected \"To\" Date ");
                    } else if (from_Date == false) {
                        showAlert("DataBase error", "Records not found for selected \"FROM\" Date ");
                    } else if (to_Date == false) {
                        showAlert("DataBase error", "Records not found for selected \"To\" Date ");
                    } else {
                        showAlert("Date selection error", "please select Proper Date first,\n otherwise check that date should not be empty");
                    }
                }
            } else {
                showAlert("DataBase error", "No Records Found");
            }

        } else if (RedBtn2.isSelected()) {
            boolean hasTables = SQLiteTableChecker.hasTables();
            if (hasTables) {
                String lastdata = GetlastTableDate();
                LocalDateTime dateTime1 = LocalDateTime.parse(lastdata, formattor24Hours);
                LocalDate Ldate = dateTime1.toLocalDate();
                LocalDate Ltoday = LocalDate.now();
                if (Ldate.equals(Ltoday)) {
                    String filemail = exmail1.getText();
                    String filemail2 = exmail2.getText();
                    if (!filemail.equals("") && filemail2.equals("")) {
                        // Valid email addresses
                        if (validateEmail(filemail)) {

                            progressShow();
                            Task<Void> sendEmailTask = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    share_today_as_pdf_after_validate(filemail);
                                    for (int i = 0; i <= 100; i++) {
                                        updateProgress(i, 100);
                                        Thread.sleep(1); // Simulate email sending delay
                                    }
                                    return null;
                                }
                            };
                            progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                            sendEmailTask.setOnSucceeded(e -> {
                                progressShownot();
                                showAlertinfo("", "Pdf File sent on " + filemail + ",");
                            });
                            new Thread(sendEmailTask).start();


                            System.out.println("Email Addresses are valid: send file hare " + filemail);
                        } else {
                            showAlert("Email Id error", "Please enter valid  Email Id.");
                        }
                    } else if (!filemail.equals("") && !filemail2.equals("")) {
                        // Invalid email addresses
                        if (validateEmail(filemail) && validateEmails(filemail2)) {

                            progressShow();
                            Task<Void> sendEmailTask = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    sendtodaypdf(filemail, filemail2);
                                    for (int i = 0; i <= 100; i++) {
                                        updateProgress(i, 100);
                                        Thread.sleep(1); // Simulate email sending delay
                                    }
                                    return null;
                                }
                            };

                            progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                            sendEmailTask.setOnSucceeded(e -> {
                                progressShownot();
                                showAlertinfo("", "Pdf File sent on " + filemail + "," + filemail2);
                            });
                            new Thread(sendEmailTask).start();

                            System.out.println("Email Addresses are valid: send files heare " + filemail);
                        } else {
                            showAlert("Email Id error", "Please enter valid  Email Id.");

                        }

                    } else {
                        showAlert("Email Id error", "Please enter valid  Email Id.");
                    }
                } else {
                    showAlert("DataBase error", "Records not found ");
                }

            } else {
                showAlert("DataBase error", "No Records found");
            }

        }

    }

    void sendpdfs(String filemail, String filemail2) throws SQLException, MessagingException, IOException {

        sharepdf_after_validate(filemail);
        String[] emailAddresses = filemail2.split(",");
        for (String email : emailAddresses) {
            sharepdf_after_validate(email);
        }
    }

    void sendtodaypdf(String filemail, String filemail2) throws SQLException, MessagingException, IOException, InterruptedException {
        share_today_as_pdf_after_validate(filemail);
        String[] emailAddresses = filemail2.split(",");
        for (String email : emailAddresses) {
            share_today_as_pdf_after_validate(email);
        }
    }

    void sendexcels(String filemail, String filemail2) throws Exception {
        shareexcel_after_validat(filemail);
        String[] emailAddresses = filemail2.split(",");
        for (String email : emailAddresses) {
            shareexcel_after_validat(email);
        }
    }

    void sendtodayexcel(String filemail, String filemail2) throws Exception {
        send_todat_as_excel_after_validate(filemail);
        String[] emailAddresses = filemail2.split(",");
        for (String email : emailAddresses) {
            send_todat_as_excel_after_validate(email);
        }
    }

    void sendselectedfiles(String filemail, String filemail2) {
        sendEmailWithAttachmentfile(globselectedFile, filemail);
        String[] emailAddresses = filemail2.split(",");
        for (String email : emailAddresses) {
            sendEmailWithAttachmentfile(globselectedFile, email);
        }
    }

    public void sendasEXCEL(ActionEvent actionEvent) throws Exception {
        if (!isInternetConnected()) {
            showAlert("Network error", "Internet is not connected");
            return;
        }
        if (RedBtn1.isSelected()) {
            boolean hasTables1 = SQLiteTableChecker.hasTables();
            if (hasTables1) {

                if (from_Date == true && to_Date == true) {
                    LocalDate startDate = DateFrom.getValue();
                    LocalDate endDate = DateTo.getValue();
                    long duration60 = getduration();
                    System.out.println("d  " + duration60);
                    String date = String.valueOf(startDate);
                    String date2 = String.valueOf(endDate);
                    System.out.println(date);
                    long dration = 60;
                    if (duration60 > dration || startDate.isAfter(endDate)) {
                        if (duration60 > dration) {
                            showAlert("error", "The Maximum Duration for Date selection is 60 Days.");
                        } else if (startDate.isAfter(endDate)) {
                            showAlert("error", "Selected Dates are not Proper");
                        }

                    } else {

                        LocalDate selectedDate = DateFrom.getValue();
                        DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
                        LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
                        String fdate = datefro.format(formatterpiker);
                        LocalDate selectedDate1 = DateTo.getValue();
                        LocalDateTime datefro1 = LocalDateTime.of(selectedDate1, LocalTime.MAX);
                        String fdate1 = datefro1.format(formatterpiker);

                        int matchyear = selectedDate.getYear();
                        int matchyear1 = selectedDate.getYear();

                        if (matchyear == matchyear1) {

                            boolean cDa = checkDataisAvailable(matchyear, fdate, fdate1);
                            if (cDa == false) {
                                showAlert("DataBase error", "No Record found for selected Duration.");
                                return;
                            }
                        } else {
                            boolean cDa = checkDataisAvailableforTwo(matchyear, matchyear1, fdate, fdate1);
                            if (cDa == false) {
                                showAlert("DataBase error", "No Record found for selected Duration.");
                                return;
                            }
                        }

                        String filemail = exmail1.getText();
                        String filemail2 = exmail2.getText();
                        if (!filemail.equals("") && filemail2.equals("")) {
                            // Valid email addresses

                            if (validateEmail(filemail)) {

                                progressShow();
                                Task<Void> sendEmailTask = new Task<Void>() {
                                    @Override
                                    protected Void call() throws Exception {
                                        shareexcel_after_validat(filemail);
                                        for (int i = 0; i <= 100; i++) {
                                            updateProgress(i, 100);
                                            Thread.sleep(1); // Simulate email sending delay
                                        }
                                        return null;
                                    }
                                };

                                progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                                sendEmailTask.setOnSucceeded(e -> {
                                    progressShownot();
                                    showAlertinfo("", "Excel File sent on " + filemail + ",");
                                });
                                new Thread(sendEmailTask).start();
                                System.out.println("Email Addresses are valid: send file hare " + filemail);

                            } else {
                                showAlert("Email Id error", "Please enter valid  Email Id.");
                            }
                        } else if (!filemail.equals("") && !filemail2.equals("")) {
                            // Invalid email addresses
                            if (validateEmail(filemail) && validateEmails(filemail2)) {

                                progressShow();
                                Task<Void> sendEmailTask = new Task<Void>() {
                                    @Override
                                    protected Void call() throws Exception {
                                        sendexcels(filemail, filemail2);
                                        for (int i = 0; i <= 100; i++) {
                                            updateProgress(i, 100);
                                            Thread.sleep(1); // Simulate email sending delay
                                        }
                                        return null;
                                    }
                                };

                                progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                                sendEmailTask.setOnSucceeded(e -> {
                                    progressShownot();
                                    showAlertinfo("", "Excel File sent on " + filemail + "," + filemail2);
                                });
                                new Thread(sendEmailTask).start();

                                System.out.println("Email Addresses are valid: send files heare " + filemail);

                            } else {
                                showAlert("Email Id error", "Please enter valid  Email Id.");
                            }
                        } else {
                            showAlert("Email Id error", "Please enter valid  Email Id.");
                        }
                    }
                } else {
                    if (DateFrom.getValue() == null) {
                        showAlert("DataBase error", "Records not found for selected \"FROM\" Date ");
                    } else if (DateTo.getValue() == null) {
                        showAlert("DataBase error", "Records not found for selected \"To\" Date ");
                    } else if (from_Date == false) {
                        showAlert("DataBase error", "Records not found for selected \"FROM\" Date ");
                    } else if (to_Date == false) {
                        showAlert("DataBase error", "Records not found for selected \"To\" Date ");
                    } else {
                        showAlert("date selection error", "please Select Proper Date first,\n otherwise check that date should not be empty");
                    }
                }
            } else {
                showAlert("DataBase error", "No Records found");
            }

        } else if (RedBtn2.isSelected()) {
            boolean hasTables = SQLiteTableChecker.hasTables();
            if (hasTables) {
                String lastdata = GetlastTableDate();
                LocalDateTime dateTime1 = LocalDateTime.parse(lastdata, formattor24Hours);
                LocalDate Ldate = dateTime1.toLocalDate();
                LocalDate Ltoday = LocalDate.now();
                if (Ldate.equals(Ltoday)) {
                    String filemail = exmail1.getText();
                    String filemail2 = exmail2.getText();

                    if (!filemail.equals("") && filemail2.equals("")) {
                        // Valid email addresses
                        if (validateEmail(filemail)) {

                            progressShow();
                            Task<Void> sendEmailTask = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    send_todat_as_excel_after_validate(filemail);
                                    for (int i = 0; i <= 100; i++) {
                                        updateProgress(i, 100);
                                        Thread.sleep(1); // Simulate email sending delay
                                    }
                                    return null;
                                }
                            };

                            progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                            sendEmailTask.setOnSucceeded(e -> {
                                progressShownot();
                                showAlertinfo("", "Excel File sent on " + filemail + ",");
                            });
                            new Thread(sendEmailTask).start();

                            System.out.println("Email Addresses are valid: send file hare " + filemail);

                        } else {
                            showAlert("Email Id error", "Please enter valid  Email Id.");
                        }
                    } else if (!filemail.equals("") && !filemail2.equals("")) {
                        // Invalid email addresses
                        if (validateEmail(filemail) && validateEmails(filemail2)) {

                            progressShow();
                            Task<Void> sendEmailTask = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    sendtodayexcel(filemail, filemail2);
                                    for (int i = 0; i <= 100; i++) {
                                        updateProgress(i, 100);
                                        Thread.sleep(1); // Simulate email sending delay
                                    }
                                    return null;
                                }
                            };

                            progressindicator2.progressProperty().bind(sendEmailTask.progressProperty());
                            sendEmailTask.setOnSucceeded(e -> {
                                progressShownot();
                                showAlertinfo("", "Excel File sent on " + filemail + "," + filemail2);
                            });
                            new Thread(sendEmailTask).start();
                            System.out.println("Email Addresses are valid: send files heare " + filemail);

                        } else {
                            showAlert("Email Id error", "Please enter valid  Email Id.");

                        }
                    } else {
                        showAlert("Email Id error", "Please enter valid  Email Id.");
                    }

                } else {
                    showAlert("DataBase error", "Records not found ");
                }
            } else {
                showAlert("DataBase error", "No Records found");
            }

        }
    }


    void sharepdf_after_validate(String address) throws SQLException, IOException, MessagingException {

        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "Excel Attachment";
        String body = "Please find attached the generated Excel.";

        String comName = proCoNmae.getText();
        if (comName.isEmpty()) {
            comName = "Company Name";
        }
        String comaddress = proAddress.getText();
        if (comaddress.isEmpty()) {
            comaddress = "Company Address";
        }
        String comphon = proPhoNo.getText();
        if (comphon.isEmpty()) {
            comphon = "Phone No";
        }
        String comemail = proEmId.getText();
        if (comemail.isEmpty()) {
            comemail = "Email Id";
        }
        System.out.print("selected----- work");
        LocalDate selectedDate = DateFrom.getValue();

        DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
        String fdate = datefro.format(formatterpiker);
        System.out.println("from " + fdate);
        LocalDate selectedDate1 = DateTo.getValue();
        LocalDateTime datefro1 = LocalDateTime.of(selectedDate1, LocalTime.MAX);
        String fdate1 = datefro1.format(formatterpiker);
        System.out.println("to " + fdate1);

        int matchyear = selectedDate.getYear();
        int matchyear1 = selectedDate.getYear();
        String sql;
        String sql2;
        ResultSet result1;
        ResultSet result2;
        ResultSet result21;

        if (matchyear == matchyear1) {

            sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime > ? AND DateTime < ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fdate);
            statement.setString(2, fdate1);
            result1 = statement.executeQuery();

            Exportpdf exportpdf = new Exportpdf();
            byte[] pdfbytes = exportpdf.getpdfbytes(result1, comName, comaddress, comemail, comphon);
            sendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address.trim(), subject, body, pdfbytes);

            System.out.println("actual excel created hear");
        } else {
            //Check here what is error
            sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime > ? AND DateTime < ?";
            sql2 = "SELECT * FROM DMM" + matchyear1 + " WHERE DateTime > ? AND DateTime < ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement1 = connection.prepareStatement(sql2);
            result2 = statement.executeQuery();
            result21 = statement1.executeQuery();
            List<ResultSet> resultSets = new ArrayList<>();
            resultSets.add(result2);
            resultSets.add(result21);
            ResultSet combinedResultSet = mergeResultSets(resultSets);

            Exportpdf exportpdf1 = new Exportpdf();
            byte[] pdfbytes = exportpdf1.getpdfbytes(combinedResultSet, comName, comaddress, comemail, comphon);
            sendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address.trim(), subject, body, pdfbytes);

        }


    }

    void shareexcel_after_validat(String address) throws Exception {
        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "Excel Attachment";
        String body = "Please find attached the generated Excel.";

        System.out.print("selected work");
        LocalDate selectedDate = DateFrom.getValue();
        DateTimeFormatter formatterpiker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime datefro = LocalDateTime.of(selectedDate, LocalTime.MIN);
        String fdate = datefro.format(formatterpiker);
        System.out.println("from " + fdate);
        LocalDate selectedDate1 = DateTo.getValue();
        LocalDateTime datefro1 = LocalDateTime.of(selectedDate1, LocalTime.MAX);
        String fdate1 = datefro1.format(formatterpiker);
        System.out.println("to " + fdate1);

        int matchyear = selectedDate.getYear();
        int matchyear1 = selectedDate.getYear();
        String sql;
        String sql2;
        ResultSet result1;
        ResultSet result2;
        ResultSet result21;
//       ResultSet totalresult;

        if (matchyear == matchyear1) {

            sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime > ? AND DateTime < ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fdate);
            statement.setString(2, fdate1);
            result1 = statement.executeQuery();

            byte[] excelfilebyt = convertToExcelbyte(result1);
            EXsendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address.trim(), subject, body, excelfilebyt);

            System.out.println("actual excel created hear");
        } else {
            sql = "SELECT * FROM DMM" + matchyear + " WHERE DateTime > ? AND DateTime < ?";
            sql2 = "SELECT * FROM DMM" + matchyear1 + " WHERE DateTime > ? AND DateTime < ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement1 = connection.prepareStatement(sql2);
            result2 = statement.executeQuery();
            result21 = statement1.executeQuery();
            List<ResultSet> resultSets = new ArrayList<>();
            resultSets.add(result2);
            resultSets.add(result21);
            ResultSet combinedResultSet = mergeResultSets(resultSets);

            byte[] excelfilebyt = convertToExcelbyte(combinedResultSet);
            EXsendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address.trim(), subject, body, excelfilebyt);
        }
    }

    void share_today_as_pdf_after_validate(String address) throws SQLException, MessagingException, IOException, InterruptedException {
        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "Excel Attachment";
        String body = "Please find attached the generated Excel.";

        String comName = proCoNmae.getText();
        if (comName.isEmpty()) {
            comName = "Company Name";
        }
        String comaddress = proAddress.getText();
        if (comaddress.isEmpty()) {
            comaddress = "Company Address";
        }
        String comphon = proPhoNo.getText();
        if (comphon.isEmpty()) {
            comphon = "Phone No";
        }
        String comemail = proEmId.getText();
        if (comemail.isEmpty()) {
            comemail = "Email Id";
        }

        DateTimeFormatter nowdatformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter nowdatformat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIN);

        String fdate = now.format(nowdatformat2);
        String fdate1 = todayStart.format(nowdatformat);

        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String sql = "SELECT * FROM DMM" + year + " WHERE DateTime > ? AND DateTime < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fdate1);
        statement.setString(2, fdate);
        System.out.println(fdate1);
        System.out.println(fdate);
        ResultSet resultSet = statement.executeQuery();
//        if(!resultSet.next()){
//            showAlert("DataBase error","No records Found");
//            return;
//        }
        Exportpdf exportpdf = new Exportpdf();
        byte[] pdfbytess = exportpdf.getpdfbytes(resultSet, comName, comaddress, comemail, comphon);
        sendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address.trim(), subject, body, pdfbytess);

    }

    void send_todat_as_excel_after_validate(String address) throws Exception {
        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "Excel Attachment";
        String body = "Please find attached the generated Excel.";

        DateTimeFormatter nowdatformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter nowdatformat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIN);

        String fdate = now.format(nowdatformat2);
        String fdate1 = todayStart.format(nowdatformat);

        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String sql = "SELECT * FROM DMM" + year + " WHERE DateTime > ? AND DateTime < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fdate1);
        statement.setString(2, fdate);
        System.out.println(fdate1);
        System.out.println(fdate);
        ResultSet resultSet = statement.executeQuery();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   " + resultSet);
//        if(!resultSet.next()){
//            showAlert("DataBase error","No records Found");
//            return;
//        }
        byte[] excelfilebyt = convertToExcelbyte(resultSet);
        EXsendEmailWithAttachment(smtpHost, senderEmail, senderPassword, address.trim(), subject, body, excelfilebyt);

    }

    private ResultSet mergeResultSets(List<ResultSet> resultSets) throws SQLException {
        if (resultSets.isEmpty()) {
            throw new IllegalArgumentException("ResultSets list is empty");
        }

        // Start with the first ResultSet
        ResultSet mergedResultSet = resultSets.get(0);

        // Merge the remaining ResultSets
        for (int i = 1; i < resultSets.size(); i++) {
            ResultSet currentResultSet = resultSets.get(i);

            // Set the fetch direction to forward-only to avoid issues with scrolling
            currentResultSet.setFetchDirection(ResultSet.FETCH_FORWARD);

            // Move to the insert row of the mergedResultSet
            mergedResultSet.moveToInsertRow();

            // Copy the data from the current ResultSet to the mergedResultSet
            mergedResultSet.moveToInsertRow();
            mergedResultSet.moveToCurrentRow();

            mergedResultSet.close();
            currentResultSet.close();
        }

        return mergedResultSet;
    }

    public byte[] convertToExcelbyte(ResultSet resultSet) throws Exception {
        Workbook workbook = WorkbookFactory.create(true);
        Sheet sheet = workbook.createSheet("Sheet1");
        sheet.protectSheet("realogview");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        String infcolumnName = metaData.getColumnName(8);
        Row headerRow = sheet.createRow(0);
        String[] colname = {"0", "ID", "Serial No", "MAC ID", "Commodity Name", "Moisture %", "Sample Temperature (°C) ", "Time", "Sample Quantity Required(gram)", "Other information"};
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            Cell cell = headerRow.createCell(i - 1);
            cell.setCellValue(colname[i]);
        }

        int firstColumnWidth = 10; // You can adjust the width as needed
        sheet.setColumnWidth(0, firstColumnWidth * 256);
        sheet.setColumnWidth(1, firstColumnWidth + 20 * 256);
        sheet.setColumnWidth(2, firstColumnWidth + 20 * 256);
        sheet.setColumnWidth(3, firstColumnWidth + 20 * 256);
        sheet.setColumnWidth(4, firstColumnWidth + 10 * 256);
        sheet.setColumnWidth(5, firstColumnWidth + 30 * 256);
        sheet.setColumnWidth(6, firstColumnWidth + 30 * 256);
        sheet.setColumnWidth(7, firstColumnWidth + 30 * 256);
        sheet.setColumnWidth(8, firstColumnWidth + 40 * 256);


        int rowNum = 1;
        int idvalue = 1;
        while (resultSet.next()) {

            Row row = sheet.createRow(rowNum++);
            for (int i = 1; i <= columnCount; i++) {
                if (i == 9) {
                    String r8 = resultSet.getString(9);
                    String ab2 = r8.substring(0, r8.length() - 1);
                    String modifiedString = ab2.substring(1);
                    String newr8 = modifiedString.replace("|", ",\n");
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(newr8);
                } else if (i == 1) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(idvalue);
                } else if (i == 7) {
                    Cell cell = row.createCell(i - 1);
                    String r6 = resultSet.getString(7);
                    LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                    String nr6 = localDateTime.format(globeldtformatter);
                    cell.setCellValue(nr6);
                } else {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                }
            }
            idvalue += 1;
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            return outputStream.toByteArray();

        }

    }

    public void textlimit() {
        Tfd1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd1.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
            System.out.println("grgr");
        });
        Tfd2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd2.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd3.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd4.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd5.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd5.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd6.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd6.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd7.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd7.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd8.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd8.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd9.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd9.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });
        Tfd10.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > CHARACTER_LIMIT) {
                Tfd10.setText(newValue.substring(0, CHARACTER_LIMIT));
            }
            bit0 = 1;
        });

    }


    private void sendEmailWithAttachmentfile(File attachmentFile, String email1) {
        String smtpHost = "smtp.gmail.com";
        String senderEmail = gmailid_of_II;
        String senderPassword = gmailpass_of_II;
        String recipientEmail = "chauhantejas18@gmail.com";
        String subject = "PDF Attachment";
        String body = "Please find attached the generated PDF.";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email1));
            message.setSubject("sending file from me");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached PDF file.");

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFile);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(attachmentFile.getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void getquickguid(ActionEvent actionEvent) {

        String imagePath12 = "Quick_Guide/QuickGuide.pdf";
        String absoluteImagePath12 = String.valueOf(new File(imagePath12).getAbsolutePath());
//        showAlert("",absoluteImagePath12);
        if (new File(absoluteImagePath12).exists()) {
            // Open the selected PDF file in a PDF viewer
            try {
                Desktop.getDesktop().open(new File(absoluteImagePath12));
                showAlertinfo("", "Quick Guide available at " + absoluteImagePath12);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("error", "Quick Guide file is not available");
        }
    }
}


