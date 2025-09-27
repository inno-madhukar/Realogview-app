package com.example.ReaLogViewDMM;


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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;

public class Pdf_Generate_With_Print_Operation {
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

    public String Realogview = "Realogview";
    public String DMM10 = "DMM1.0";
    public String Data_Base = "DataBase";
    public String User_Profile = "User Profile";
    public String Data = "Data";
    public String IPDF = "PDF";
    public String Records = "Records";
    public String PDF = "PDF";
    public String EXCEL = "EXCEL";

    Pdf_Generate_With_Print_Operation(String macid, String[] Dmmdata, String data_r_time, String comname, String adress, String comphone, String comemail, String[] otherinfo) throws IOException {
        this.fruits = Dmmdata;
        this.comName = comname;
        this.address = adress;
        this.comPhone = comphone;
        this.comEmail = comemail;
        this.otherinformation = otherinfo;
        this.data_r_time = data_r_time;
        this.macid = macid;
        String[] clientData={"Client Name","Location","Truck Number","Total Weight","Remarks"};

    }

    private void showAlertinfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinSize(300, 150);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    public static String[] splitIntoChunks(String input, int chunkSize) {
        if (input == null || input.isEmpty()) {
            return new String[0]; // return empty array for null or empty string
        }

        List<String> chunks = new ArrayList<>();
        int length = input.length();

        for (int i = 0; i < length; i += chunkSize) {
            int end = Math.min(length, i + chunkSize);
            chunks.add(input.substring(i, end));
        }

        return chunks.toArray(new String[0]);
    }
    String pdfPath="";
    public String genratepdf(String idatetime) throws IOException {

        System.out.println(otherinformation[0]);
        Image image0 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage bufferedImage1 = SwingFXUtils.fromFXImage(image0, null);
//        BufferedImage image1 = ImageIO.read(new File("src/main/resources/images/dmmlogo.jpg"));

        float ix1 = 20; // X-coordinate
        float iy1 = 20; // Y-coordinate
        float iwidth1 = 100; // Width
        float iheight1 = 30; // Height
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            //image
            float x = 25; // X-coordinate
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
            File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
            File[] files = folder.listFiles();
            for (File file : files) {
                if (isImageFile(file)) {
                    BufferedImage image = ImageIO.read(file);
                    PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
                    contentStream.drawImage(pdImage, x, y, width, height);
                    break;
                } else {
                    Image image00 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                    BufferedImage image = SwingFXUtils.fromFXImage(image00, null);
                    PDImageXObject pdImage01 = LosslessFactory.createFromImage(document, image);
                    contentStream.drawImage(pdImage01, x, y, width, height);
                }
            }


            float gy=760;
            float emX = 340;
            float pmX = 400;


            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(emX, gy);
            contentStream.showText("Company Name :");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(pmX+15, gy);
            contentStream.showText(comName);
            contentStream.endText();
            gy-=12;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(emX, gy);
            contentStream.showText("Email Id : ");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(pmX-20, gy);
            contentStream.showText(comEmail);
            contentStream.endText();
            gy-=11;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(emX, gy);
            contentStream.showText("Ph No :  " );
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(pmX-20, gy);
            contentStream.showText(comPhone);
            contentStream.endText();

            gy-=11;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
            contentStream.beginText();
            contentStream.newLineAtOffset(emX, gy);
            contentStream.showText("Address :" );
            contentStream.endText();
            String newaddress = address.trim().replaceAll("\n", " ");
            System.out.println(newaddress);
            String[] newStrg1 = address.split("\n");
            for (String line : newStrg1) {
                contentStream.setFont(PDType1Font.HELVETICA, 9);
                contentStream.beginText();
                contentStream.newLineAtOffset(pmX-20, gy);
                contentStream.showText(line + " ,");
                contentStream.endText();
                gy -= 11;
            }

            gy-=21;
            //lines
            float startX = 30; // X-coordinate of the starting point
            float endX = 580; // X-coordinate of the ending point
            float lineWidth = 1; // Line width in points
            float[] lineColor = {0, 0, 0}; //
            contentStream.setLineWidth(lineWidth);
            contentStream.setStrokingColor(lineColor[0], lineColor[1], lineColor[2]);
            contentStream.moveTo(startX, gy);
            contentStream.lineTo(endX, gy);
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


            float loopy = gy-30;
            float loopyDmm = gy-30;
            //device mesured data
            float loopx = 110;

            String[] cars = {"Serial No :", "Commodity Name :", "Moisture  :", "Sample Temperature :", "Time :", "Sample Quantity Required :"};
            for (String i : cars) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(loopx, loopy);
                contentStream.showText(i);
                contentStream.endText();
                loopy -= 24;
            }
            loopy-=10;

            loopy-=24;

//            // serial data printing
            float loopx2 = 185;


            System.out.println(fruits[0]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2, loopyDmm );
            contentStream.showText(fruits[0]);
            contentStream.endText();

            System.out.println(fruits[1]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(240, loopyDmm -= 24);
            contentStream.showText(fruits[1]);
            contentStream.endText();
//
            System.out.println(fruits[2]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 15, loopyDmm -= 24);
            contentStream.showText(fruits[2] + " %");
            contentStream.endText();
//
            System.out.println(fruits[3]);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 60, loopyDmm -= 24);
            contentStream.showText(fruits[3] + " °C");
            contentStream.endText();
//
            LocalDateTime currentDateTime2 = LocalDateTime.now();
            String formattedDateTime2 = currentDateTime2.format(globeldtformatter);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 - 10, loopyDmm -= 24);
            contentStream.showText(data_r_time);
            contentStream.endText();
//
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(290, loopyDmm -= 24);
            if (fruits[4].equals("FULL")) {
                contentStream.showText(fruits[4]);
                contentStream.endText();
            } else {
                contentStream.showText(fruits[4] + " gram");
                contentStream.endText();
            }
            loopyDmm-=24;
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm-=10);
            contentStream.showText("Other Information");
            loopyDmm-=20;
            contentStream.endText();
            String[] cars1 = {"Client Name :","Location :","Truck Number :","Vendor ID","Total Weight :","Remarks :"};
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm);
            contentStream.showText(cars1[0]);
            contentStream.endText();
            if(otherinformation[0].trim().length()>40){
                String[] result = splitIntoChunks(otherinformation[0],40);
                for(String chunk:result){
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(loopx2+20, loopyDmm );
                    contentStream.showText(chunk);
                    contentStream.endText();
                    loopyDmm-=15;
                }
                loopyDmm+=15;
            }
            else{
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(loopx2+20, loopyDmm );
                contentStream.showText(otherinformation[0]);
                contentStream.endText();
            }

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm -=24);
            contentStream.showText("Location :");
            contentStream.endText();

            if(otherinformation[1].trim().length()>40){
                String[] result = splitIntoChunks(otherinformation[1],40);
                for(String chunk:result){
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(loopx2+20, loopyDmm );
                    contentStream.showText(chunk);
                    contentStream.endText();
                    loopyDmm-=15;
                }
                loopyDmm+=15;
            }
            else{
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(loopx2+20, loopyDmm );
                contentStream.showText(otherinformation[1]);
                contentStream.endText();
            }
//
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm -=24);
            contentStream.showText("Truck Number :");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 27, loopyDmm );
            contentStream.showText(otherinformation[2] );
            contentStream.endText();
//
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm -=24);
            contentStream.showText("Vendor ID :");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 20, loopyDmm );
            contentStream.showText(otherinformation[5] );
            contentStream.endText();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm -=24);
            contentStream.showText("Total Weight :");
            contentStream.endText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx2 + 23, loopyDmm );
            contentStream.showText(otherinformation[3] +" Kg" );
            contentStream.endText();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(loopx, loopyDmm -=24);
            contentStream.showText("Remarks :");
            contentStream.endText();
            if(otherinformation[4].trim().length()>40){
                String[] result = splitIntoChunks(otherinformation[4],40);
                for(String chunk:result){
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(loopx2+20, loopyDmm );
                    contentStream.showText(chunk);
                    contentStream.endText();
                    loopyDmm-=15;
                }
//                loopyDmm+=15;
            }
            else{
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(loopx2+20, loopyDmm );
                contentStream.showText(otherinformation[4]);
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
            String pdffilename = fruits[0] + "_" + idatetime+"_"+otherinformation[0];
            document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data + "/" + IPDF + "/" + pdffilename + ".pdf");
            System.out.println("PDF created successfully.");
             pdfPath = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Data + "/" + IPDF + "/" + pdffilename + ".pdf";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfPath;

    }

    byte[] pdfbytesend(ResultSet resultSet) {

        return null;
    }

}
