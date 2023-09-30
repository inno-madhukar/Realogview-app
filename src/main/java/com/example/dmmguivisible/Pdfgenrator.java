package com.example.dmmguivisible;


import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.embed.swing.SwingFXUtils;
public class Pdfgenrator {
    DateTimeFormatter globeldtformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    String defaultPath = System.getProperty("user.home");
    String[] fruits;
    String comName;
    String address;
    String comPhone;
    String comEmail;
    String[] otherinformation;
    String data_r_time;
    String[] v = new String[3];
    String macid;

    public String Realogview= "Realogview";
    public String DMM10= "DMM1.0";
    public String Data_Base= "DataBase";
    public String User_Profile= "User Profile";
    public String Data= "Data";
    public String IPDF="PDF";
    public String Records= "Records";
    public String PDF="PDF";
    public String EXCEL="EXCEL";

    Pdfgenrator(String macid, String[] Dmmdata, String data_r_time, String comname, String adress, String comphone, String comemail, String[] otherinfo) throws IOException {
        this.fruits = Dmmdata;
        this.comName = comname;
        this.address = adress;
        this.comPhone = comphone;
        this.comEmail = comemail;
        this.otherinformation = otherinfo;
        this.data_r_time=data_r_time;
        this.macid=macid;

    }

    private void showAlertinfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinSize(300,150);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }
    public void genratepdf(String idatetime) throws IOException {
//        String imagePath12 = "images/white_Image.png";
//        String absoluteImagePath12 = String.valueOf(new File(imagePath12).getAbsolutePath());
//        System.out.println("path  -  "+absoluteImagePath12);
//        showAlertinfo("",absoluteImagePath12);
        System.out.println(otherinformation[0]);
        Image image0 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage bufferedImage1 = SwingFXUtils.fromFXImage(image0,null);
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

            PDImageXObject pdImage1 = LosslessFactory.createFromImage(document, bufferedImage1);
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
            File folder = new File(defaultPath+"/"+Realogview+"/"+DMM10+"/"+User_Profile);
            File[] files = folder.listFiles();
            for(File file:files){
                if(isImageFile(file)){
                    BufferedImage image = ImageIO.read(file);
                    PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
                    contentStream.drawImage(pdImage, x, y, width, height);
                    break;
                }
                else{
                    Image image00 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                    BufferedImage image = SwingFXUtils.fromFXImage(image00,null);
                    PDImageXObject pdImage01 = LosslessFactory.createFromImage(document, image);
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
                    contentStream.showText(line.replace("|",",").trim());
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

            }
            else {

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
            contentStream.showText(macid);
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
            contentStream.newLineAtOffset(loopx2+15, loopyDmm - 72);
            contentStream.showText(fruits[2] + " %");
            contentStream.endText();
//
            System.out.println(fruits[3]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2+60, loopyDmm - 96);
            contentStream.showText(fruits[3] + " °C");
            contentStream.endText();
//
            LocalDateTime currentDateTime2 = LocalDateTime.now();
            String formattedDateTime2 = currentDateTime2.format(globeldtformatter);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2-10, loopyDmm - 120);
            contentStream.showText(data_r_time);
            contentStream.endText();
//
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(290, loopyDmm - 144);
            if(fruits[4].equals("FULL")){
                contentStream.showText(fruits[4]);
                contentStream.endText();
            }
            else{
                contentStream.showText(fruits[4] + " gram");
                contentStream.endText();
            }

//


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
            String pdffilename = fruits[0] + "_" + idatetime;
            document.save(defaultPath+"/"+Realogview+"/"+DMM10+"/"+Data+"/"+IPDF+"/"+ pdffilename + ".pdf");
            System.out.println("PDF created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

byte[] pdfbytesend(ResultSet resultSet){

return null;
}

}
