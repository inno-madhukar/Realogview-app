package com.example.ReaLogViewDMM;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Print_Layout_Controller {
    @FXML
    public Label cadress;
    @FXML
    public Label psrn;
    @FXML
    public Label pcomodityname;
    @FXML
    public Label pmoisture;
    @FXML
    public Label ptempr;
    @FXML
    public Label pweight;
    @FXML
    public Label ptime;
    @FXML
    public Label psqr;
    @FXML
    public Label poinfo;
    @FXML
    public Label pcadress;
    @FXML
    public Label pcname;
    @FXML
    public ImageView pcompimage;

    public String[] array1;
    public Label lab10;
    public Label lab9;
    public Label lab8;
    public Label lab7;
    public Label lab6;
    public Label lab5;
    public Label lab4;
    public Label lab3;
    public Label lab2;
    public Label lab1;
    public Label mid;
    public Label cmail;
    public Label cphone;
    public ImageView imagefooter;

    public String Realogview = "Realogview";
    public String DMM10 = "DMM1.0";
    public String Data_Base = "DataBase";
    public String User_Profile = "User Profile";
    public String Data = "Data";
    public String IPDF = "PDF";
    public String Records = "Records";
    public String PDF = "PDF";
    public String EXCEL = "EXCEL";

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    public void setData(String mid1, String[] fruits, String printtime, String[] ddata, String comname, String Address, String cemail, String cphone1) {
        lab1.setVisible(false);
        lab2.setVisible(false);
        lab3.setVisible(false);
        lab4.setVisible(false);
        lab5.setVisible(false);
        lab6.setVisible(false);
        lab7.setVisible(false);
        lab8.setVisible(false);
        lab9.setVisible(false);
        lab10.setVisible(false);
        String defaultPath = System.getProperty("user.home");
        System.out.println(fruits[0]);
        psrn.setText(fruits[0]);
        mid.setText(mid1);
        pcomodityname.setText(fruits[1]);
        pmoisture.setText(fruits[2] + " %");
        ptempr.setText(fruits[3] + " °C");
        cmail.setText(cemail);
        cphone.setText(cphone1);
//        pweight.setText(fruits[4]+ " g");
        if (fruits[4].equals("FULL")) {
            psqr.setText(fruits[4]);
        } else {
            psqr.setText(fruits[4] + " gram");
        }

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        LocalDateTime currentDateTime2 = LocalDateTime.now();
        String formattedDateTime2 = currentDateTime2.format(formatter1);
        ptime.setText(printtime);
        System.out.println("lebn777777777777 " + ddata.length);
        if (ddata == null) {
            lab1.setText("no data available");
            lab1.setVisible(true);
        } else {
            if (ddata[0] != null) {
                lab1.setText(ddata[0].replace("|", ","));
                lab1.setVisible(true);
            }
            if (ddata[1] != null) {
                lab2.setText(ddata[1].replace("|", ","));
                lab2.setVisible(true);
            }
            if (ddata[2] != null) {
                lab3.setText(ddata[2].replace("|", ","));
                lab3.setVisible(true);
            }
            if (ddata[3] != null) {
                lab4.setText(ddata[3].replace("|", ","));
                lab4.setVisible(true);
            }
            if (ddata[4] != null) {
                lab5.setText(ddata[4].replace("|", ","));
                lab5.setVisible(true);
            }
            if (ddata[5] != null) {
                lab6.setText(ddata[5].replace("|", ","));
                lab6.setVisible(true);
            }
            if (ddata[6] != null) {
                lab7.setText(ddata[6].replace("|", ","));
                lab7.setVisible(true);
            }
            if (ddata[7] != null) {
                lab8.setText(ddata[7].replace("|", ","));
                lab8.setVisible(true);
            }
            if (ddata[8] != null) {
                lab9.setText(ddata[8].replace("|", ","));
                lab9.setVisible(true);
            }
            if (ddata[9] != null) {
                lab10.setText(ddata[9].replace("|", ","));
                lab10.setVisible(true);
            }

        }

        pcname.setText(comname);
        pcadress.setText(Address);

        Image image = null;
        File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);

        File[] files = folder.listFiles();
        for (File file : files) {
            if (isImageFile(file)) {
                image = new Image(String.valueOf(files[0]));
                break;
            } else {
                image = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());

            }
        }
//        Image image1 = new Image("C:/Users/Asus/Desktop/javafx app/ReaLogView DMM 1.0 App/DMMGUIVisible/src/main/resources/images/dmmlogo.jpg");
        Image image1 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        pcompimage.setImage(image);

//        tr.setImage(image);
//        Image image1 = new Image("C:/Users/Asus/Desktop/javafx app/ReaLogView DMM 1.0 App/DMMGUIVisible/src/main/resources/images/dmmlogo.jpg");
        imagefooter.setImage(image1);


    }


}