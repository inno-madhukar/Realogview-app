package com.example.ReaLogViewDMM;

import javafx.embed.swing.SwingFXUtils;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.sql.ResultSet;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Print_And_Pdf_Of_Table_Records {
    public String Realogview = "Realogview";
    public String DMM10 = "DMM1.0";
    public String Data_Base = "DataBase";
    public String User_Profile = "User Profile";
    public String Data = "Data";
    public String IPDF = "PDF";
    public String Records = "Records";
    public String PDF = "PDF";
    public String EXCEL = "EXCEL";
    String defaultPath = System.getProperty("user.home");
    float gy2=700;
    ByteArrayOutputStream outputStream;
    DateTimeFormatter globeldtformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    DateTimeFormatter formattor24Hours = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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


    public static List<String> chunkWithLockedPhrases(String text, int chunkSize) {
        // 1. Define important phrases you don't want to split
        String[][] lockedPhrases = {
                {"Pvt Ltd", "Pvt_Ltd"},
                {"Co. Ltd", "Co._Ltd"},
                {"Private Limited", "Private_Limited"},
                {"Ltd.", "Ltd."} // single word, safe anyway
        };

        // 2. Replace them with placeholders
        for (String[] pair : lockedPhrases) {
            text = text.replace(pair[0], pair[1]);
        }

        // 3. Do the normal chunking by words
        List<String> chunks = new ArrayList<>();
        String[] words = text.split("\\s+");
        StringBuilder current = new StringBuilder();

        for (String word : words) {
            if (word.length() > chunkSize) {
                // break extra-long word if needed
                int index = 0;
                while (index < word.length()) {
                    int end = Math.min(index + chunkSize, word.length());
                    chunks.add(word.substring(index, end).replace("_", " "));
                    index = end;
                }
                current = new StringBuilder();
            } else if (current.length() + word.length() + 1 > chunkSize) {
                chunks.add(current.toString().trim().replace("_", " "));
                current = new StringBuilder(word).append(" ");
            } else {
                current.append(word).append(" ");
            }
        }

        if (current.length() > 0) {
            chunks.add(current.toString().trim().replace("_", " "));
        }

        return chunks;
    }

    public byte[] getpdfbytes(ResultSet resultSet, String Comname, String Adress, String Email, String PNo) throws IOException {

        int flag1=0;
        tableprintpath="";
        BufferedImage image = null;
        File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (isImageFile(file)) {
                image = ImageIO.read(file);
                flag1=1;
                break;
            } else {

            }
        }
        if(flag1==0){
            Image image00 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
            image = SwingFXUtils.fromFXImage(image00, null);
        }
        Image image0 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage bufferedImage1 = SwingFXUtils.fromFXImage(image0, null);
//          BufferedImage image1 = ImageIO.read(new File("src/main/resources/images/dmmlogo.jpg"));
        float ix = 40; // X-coordinate
        float iy = 700; // Y-coordinate
        float iwidth = 100; // Width
        float iheight = 60; // Height

        float ix1 = 20; // X-coordinate
        float iy1 = 20; // Y-coordinate
        float iwidth1 = 80; // Width
        float iheight1 = 20; // Height

        int pageNo = 0;
        try (PDDocument document = new PDDocument()) {

            PDPage page1 = new PDPage();
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
            PDImageXObject pdImage1 = LosslessFactory.createFromImage(document, bufferedImage1);
            document.addPage(page1);
            PDPageContentStream contentStream = new PDPageContentStream(document, page1);
            //image
            contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
//            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
            //line

            pageNo += 1;
            contentStream.setFont(PDType1Font.HELVETICA, 7);
            contentStream.beginText();
            contentStream.newLineAtOffset(550, 20);
            contentStream.showText(String.valueOf("Page " + pageNo));
            contentStream.endText();

            headerfooter(contentStream, Comname, Adress, Email, PNo);
            Hdrawline(contentStream, gy2+5);
            Hdrawline(contentStream, gy2-15);
            Vdrawline(contentStream, 130,(int)gy2+5);
            Vdrawline(contentStream, 25,(int)gy2+5);
            Vdrawline(contentStream, 590,(int)gy2+5);
            Vdrawline(contentStream, 55,(int)gy2+5);
            int idx = 36;
            int srnox = 60;
            int commx = 440;
            int semqrx = 280;
            int moistx = 150;
            int timex = 150;
            int tempx = 280;
            int cnamex = 150;   //440
            int locax = 150;
            int trunamex = 440;  //280
            int totweightx=150;
            int remarkx=150;
            int vendorx=280;

            int commxv = 510;
            int semqrxv = 315;
            int moistxv= 190;
            int timexv = 175;
            int tempxv = 335;
            int cnamexv = 205; //490
            int locaxv = 215 ;
            int trunamexv = 500;   //340
            int totweightxv=200;
            int remarkxv=190;
            int vendorxv=328;

            int y1 = (int) gy2-30;
            int idv = 0;
            while (resultSet.next()) {
                if (y1 <= 100) {
                    System.out.println("wht is dasf--------------------------------------------------------------------------------");
                    y1 = (int) gy2-30;
                    contentStream.close();
                    PDPage newpage = new PDPage();
                    document.addPage(newpage);
                    contentStream = new PDPageContentStream(document, newpage);
                    contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
//                    contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
                    pageNo += 1;
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(550, 20);
                    contentStream.showText(String.valueOf("Page " + pageNo));
                    contentStream.endText();

                    headerfooter(contentStream, Comname, Adress, Email, PNo);
                    Hdrawline(contentStream, gy2+5);
                    Hdrawline(contentStream, gy2-15);
                    Vdrawline(contentStream, 130,(int)gy2+5);
                    Vdrawline(contentStream, 25, (int)gy2+5);
                    Vdrawline(contentStream, 590,(int)gy2+5);
                    Vdrawline(contentStream, 55,(int)gy2+5);
                    contentStream.setFont(PDType1Font.HELVETICA, 8);
//                    lasty2=y1+20;
                }
                idv += 1;
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(idx, y1);
                contentStream.showText(String.valueOf(idv));
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(srnox, y1);
                contentStream.showText(resultSet.getString(2));
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(commx, y1);
                contentStream.showText("Commodity Name :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(commxv, y1);
                contentStream.showText("  " + resultSet.getString(3));
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(semqrx, y1);
                contentStream.showText("Weight :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(semqrxv, y1);
                if (resultSet.getString(7).equals("FULL")) {
                    contentStream.showText("  " + resultSet.getString(7));
                } else {
                    contentStream.showText("  " + resultSet.getString(7) + " grams");
                }
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(moistx, y1);
                contentStream.showText("Moisture :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(moistxv, y1);
                contentStream.showText("  " + resultSet.getString(4)+ " %");
                contentStream.endText();
                y1-=10;
                String r6 = resultSet.getString(6);
                LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                String nr6 = localDateTime.format(globeldtformatter);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(timex, y1 );
                contentStream.showText("Date :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(timexv, y1);
                contentStream.showText("  " + nr6);
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(tempx, y1 );
                contentStream.showText("Temperature :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(tempxv, y1 );
                contentStream.showText("  " + resultSet.getString(5) + " °C");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(trunamex, y1 );
                contentStream.showText("Truck Number :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(trunamexv, y1 );
                contentStream.showText("  " + resultSet.getString(10) );
                contentStream.endText();

                y1-=10;

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(totweightx, y1 );
                contentStream.showText("Total Weight :");
                contentStream.endText();

                if(resultSet.getString(11).isBlank()){
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(totweightxv, y1 );
                    contentStream.showText("  " + resultSet.getString(11) );
                    contentStream.endText();
                }
                else{
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(totweightxv, y1 );
                    contentStream.showText("  " + resultSet.getString(11));
                    contentStream.endText();
                }


                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(vendorx, y1 );
                contentStream.showText("Vendor ID :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(vendorxv, y1 );
                contentStream.showText("  " + resultSet.getString(13) );
                contentStream.endText();
                y1-=10;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(cnamex, y1 );
                contentStream.showText("Client Name :");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String cname=resultSet.getString(8);
                if(cname.trim().length()>50){
                    String[] result = chunkWithLockedPhrases(cname, 100).toArray(new String[0]);
                    for(String chunk:result){
                        contentStream.beginText();
                        contentStream.newLineAtOffset(cnamexv, y1 );
                        contentStream.showText(chunk);
                        contentStream.endText();
                        y1-=10;
                    }
                    y1+=10;
                }
                else{
                    contentStream.beginText();
                    contentStream.newLineAtOffset(cnamexv, y1 );
                    contentStream.showText(" " + cname );
                    contentStream.endText();
                }

                y1-=10;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(remarkx, y1 );
                contentStream.showText("Client Address :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String lname=resultSet.getString(9);
                if(lname.trim().length()>50){
                    String[] result = chunkWithLockedPhrases(lname, 100).toArray(new String[0]);
                    for(String chunk:result){
                        contentStream.beginText();
                        contentStream.newLineAtOffset(locaxv, y1 );
                        contentStream.showText(chunk);
                        contentStream.endText();
                        y1-=10;
                    }
                    y1+=10;
                }
                else{
                    contentStream.beginText();
                    contentStream.newLineAtOffset(locaxv, y1 );
                    contentStream.showText("  " + cname );
                    contentStream.endText();
                }

                y1-=10;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(remarkx, y1 );
                contentStream.showText("Remarks :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String rname=resultSet.getString(12);
                if(rname.trim().length()>50){
                    String[] result = chunkWithLockedPhrases(rname, 100).toArray(new String[0]);
                    for(String chunk:result){
                        contentStream.beginText();
                        contentStream.newLineAtOffset(remarkxv, y1 );
                        contentStream.showText(chunk);
                        contentStream.endText();
                        y1-=10;
                    }
                    y1+=10;
                }
                else{
                    contentStream.beginText();
                    contentStream.newLineAtOffset(remarkxv, y1 );
                    contentStream.showText("  " + cname );
                    contentStream.endText();
                }

                Hdrawline(contentStream, y1-5);
                y1 -= 20;

            }
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
            outputStream = new ByteArrayOutputStream();
            document.save(outputStream);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return outputStream.toByteArray();
    }
    String tableprintpath="";
    String exportpdf(ResultSet resultSet, String Comname, String Adress, String Email, String PNo, String fromexem, String toexem, String print1) throws IOException {
//
        int flag1=0;
        tableprintpath="";
        BufferedImage image = null;
        File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (isImageFile(file)) {
                image = ImageIO.read(file);
                flag1=1;
                break;
            } else {

            }
        }
        if(flag1==0){
            Image image00 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
            image = SwingFXUtils.fromFXImage(image00, null);
        }
        Image image0 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage bufferedImage1 = SwingFXUtils.fromFXImage(image0, null);
//          BufferedImage image1 = ImageIO.read(new File("src/main/resources/images/dmmlogo.jpg"));
        float ix = 40; // X-coordinate
        float iy = 700; // Y-coordinate
        float iwidth = 100; // Width
        float iheight = 60; // Height

        float ix1 = 20; // X-coordinate
        float iy1 = 20; // Y-coordinate
        float iwidth1 = 80; // Width
        float iheight1 = 20; // Height

        int pageNo = 0;
        try (PDDocument document = new PDDocument()) {

            PDPage page1 = new PDPage();
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);
            PDImageXObject pdImage1 = LosslessFactory.createFromImage(document, bufferedImage1);
            document.addPage(page1);
            PDPageContentStream contentStream = new PDPageContentStream(document, page1);
            //image
            contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
//            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
            //line

            pageNo += 1;
            contentStream.setFont(PDType1Font.HELVETICA, 7);
            contentStream.beginText();
            contentStream.newLineAtOffset(550, 20);
            contentStream.showText(String.valueOf("Page " + pageNo));
            contentStream.endText();

            headerfooter(contentStream, Comname, Adress, Email, PNo);
            Hdrawline(contentStream, gy2+5);
            Hdrawline(contentStream, gy2-15);
            Vdrawline(contentStream, 130,(int)gy2+5);
            Vdrawline(contentStream, 25,(int)gy2+5);
            Vdrawline(contentStream, 590,(int)gy2+5);
            Vdrawline(contentStream, 55,(int)gy2+5);
            int idx = 36;
            int srnox = 60;
            int commx = 440;
            int semqrx = 280;
            int moistx = 150;
            int timex = 150;
            int tempx = 280;
            int cnamex = 150;   //440
            int locax = 150;
            int trunamex = 440;  //280
            int totweightx=150;
            int remarkx=150;
            int vendorx=280;

            int commxv = 510;
            int semqrxv = 315;
            int moistxv= 190;
            int timexv = 175;
            int tempxv = 335;
            int cnamexv = 205; //490
            int locaxv = 215 ;
            int trunamexv = 500;   //340
            int totweightxv=200;
            int remarkxv=190;
            int vendorxv=328;

            int y1 = (int) gy2-30;
            int idv = 0;
            while (resultSet.next()) {
                if (y1 <= 100) {
                        System.out.println("wht is dasf--------------------------------------------------------------------------------");
                    y1 = (int) gy2-30;
                    contentStream.close();
                    PDPage newpage = new PDPage();
                    document.addPage(newpage);
                    contentStream = new PDPageContentStream(document, newpage);
                    contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
//                    contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
                    pageNo += 1;
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(550, 20);
                    contentStream.showText(String.valueOf("Page " + pageNo));
                    contentStream.endText();

                    headerfooter(contentStream, Comname, Adress, Email, PNo);
                    Hdrawline(contentStream, gy2+5);
                    Hdrawline(contentStream, gy2-15);
                    Vdrawline(contentStream, 130,(int)gy2+5);
                    Vdrawline(contentStream, 25, (int)gy2+5);
                    Vdrawline(contentStream, 590,(int)gy2+5);
                    Vdrawline(contentStream, 55,(int)gy2+5);
                    contentStream.setFont(PDType1Font.HELVETICA, 8);
//                    lasty2=y1+20;
                }
                idv += 1;
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(idx, y1);
                contentStream.showText(String.valueOf(idv));
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(srnox, y1);
                contentStream.showText(resultSet.getString(2));
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(commx, y1);
                contentStream.showText("Commodity Name :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(commxv, y1);
                contentStream.showText("  " + resultSet.getString(3));
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(semqrx, y1);
                contentStream.showText("Weight :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(semqrxv, y1);
                if (resultSet.getString(7).equals("FULL")) {
                    contentStream.showText("  " + resultSet.getString(7));
                } else {
                    contentStream.showText("  " + resultSet.getString(7) + " grams");
                }
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(moistx, y1);
                contentStream.showText("Moisture :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(moistxv, y1);
                contentStream.showText("  " + resultSet.getString(4)+ " %");
                contentStream.endText();
                y1-=10;
                String r6 = resultSet.getString(6);
                LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                String nr6 = localDateTime.format(globeldtformatter);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(timex, y1 );
                contentStream.showText("Date :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(timexv, y1);
                contentStream.showText("  " + nr6);
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(tempx, y1 );
                contentStream.showText("Temperature :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(tempxv, y1 );
                contentStream.showText("  " + resultSet.getString(5) + " °C");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(trunamex, y1 );
                contentStream.showText("Truck Number :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(trunamexv, y1 );
                contentStream.showText("  " + resultSet.getString(10) );
                contentStream.endText();

                y1-=10;

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(totweightx, y1 );
                contentStream.showText("Total Weight :");
                contentStream.endText();
                if(resultSet.getString(11).isBlank()){
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(totweightxv, y1 );
                    contentStream.showText("  " + resultSet.getString(11) );
                    contentStream.endText();
                }
                else{
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(totweightxv, y1 );
                    contentStream.showText("  " + resultSet.getString(11) );
                    contentStream.endText();
                }

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(vendorx, y1 );
                contentStream.showText("Vendor ID :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(vendorxv, y1 );
                contentStream.showText("  " + resultSet.getString(13) );
                contentStream.endText();
                y1-=10;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(cnamex, y1 );
                contentStream.showText("Client Name :");
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String cname=resultSet.getString(8);
                if(cname.trim().length()>50){
                    String[] result = chunkWithLockedPhrases(cname, 100).toArray(new String[0]);
                    for(String chunk:result){
                        contentStream.beginText();
                        contentStream.newLineAtOffset(cnamexv, y1 );
                        contentStream.showText(chunk);
                        contentStream.endText();
                        y1-=10;
                    }
                y1+=10;
                }
                else{
                    contentStream.beginText();
                    contentStream.newLineAtOffset(cnamexv, y1 );
                    contentStream.showText(" " + cname );
                    contentStream.endText();
                }

                y1-=10;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(remarkx, y1 );
                contentStream.showText("Cllient Address :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String lname=resultSet.getString(9);
                if(lname.trim().length()>50){
                    String[] result = chunkWithLockedPhrases(lname, 100).toArray(new String[0]);
                    for(String chunk:result){
                        contentStream.beginText();
                        contentStream.newLineAtOffset(locaxv, y1 );
                        contentStream.showText(chunk);
                        contentStream.endText();
                        y1-=10;
                    }
                    y1+=10;
                }
                else{
                    contentStream.beginText();
                    contentStream.newLineAtOffset(locaxv, y1 );
                    contentStream.showText("  " + cname );
                    contentStream.endText();
                }

                y1-=10;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(remarkx, y1 );
                contentStream.showText("Remarks :");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String rname=resultSet.getString(12);
                if(rname.trim().length()>50){
                    String[] result = chunkWithLockedPhrases(rname, 100).toArray(new String[0]);
                    for(String chunk:result){
                        contentStream.beginText();
                        contentStream.newLineAtOffset(remarkxv, y1 );
                        contentStream.showText(chunk);
                        contentStream.endText();
                        y1-=10;
                    }
                    y1+=10;
                }
                else{
                    contentStream.beginText();
                    contentStream.newLineAtOffset(remarkxv, y1 );
                    contentStream.showText("  " + cname );
                    contentStream.endText();
                }

                Hdrawline(contentStream, y1-5);
                y1 -= 20;

            }
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
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
            String formattedDateTime = formatter12.format(now);
            String pdffilename = formattedDateTime;
            if (fromexem.equals("today") && toexem.equals("today")) {

                if (print1!="print") {
                    // Open the selected PDF file in a PDF viewer
                    document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF + "/" + pdffilename+"_Record"+".pdf");
                    String filepath2 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + PDF + "\\" + pdffilename+"_Record"+".pdf";

                    try {
                        Desktop.getDesktop().open(new File(filepath2));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    showAlertinfo("", "Pdf File is saved at " + filepath2);

                }
                else{
                    document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF + "/" + "printing" + ".pdf");
                    System.out.println("PDF created successfully.");
                    String filepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + PDF + "\\" +"printing"  + ".pdf";
                    tableprintpath=filepath1;
                }
//                showAlertinfo("", "Pdf File is saved at " + filepath1);
            } else {

                if ( print1!="print") {
                    // Open the selected PDF file in a PDF viewer
                    document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF + "/From_" + fromexem + "_To_" + toexem+"_Record"+".pdf");
                    System.out.println("PDF created successfully.");
                    String filepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + PDF + "\\From_" + fromexem + "_To_" + toexem +"_Record"+".pdf";

                    try {
                        Desktop.getDesktop().open(new File(filepath1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    showAlertinfo("", "Pdf File is saved at " + filepath1);

                }
                else{
                    document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF+ "/" + "printing1" + ".pdf");
                    System.out.println("PDF created successfully.");
                    String filepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + PDF + "\\" +"printing1"  + ".pdf";
                    tableprintpath=filepath1;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tableprintpath;
    }

    private void showAlertinfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinSize(300, 150);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void Hdrawline(PDPageContentStream contentStream, float y1) throws IOException {
        float startX1 = 25; // X-coordinate of the starting point
        float startY1 = y1; // Y-coordinate of the starting point
        float endX1 = 590; // X-coordinate of the ending point
        float endY1 = y1; // Y-coordinate of the ending point
        float lineWidth1 = 0.3F; // Line width in points
        float[] lineColor1 = {0, 0, 0}; //
        contentStream.setLineWidth(lineWidth1);
        contentStream.setStrokingColor(lineColor1[0], lineColor1[1], lineColor1[2]);
        contentStream.moveTo(startX1, startY1);
        contentStream.lineTo(endX1, endY1);
        contentStream.stroke();
    }

    void Vdrawline(PDPageContentStream contentStream, float x1,int y) throws IOException {
        float startX1 = x1; // X-coordinate of the starting point
        float startY1 = y; // Y-coordinate of the starting point
        float endX1 = x1; // X-coordinate of the ending point
        float endY1 = 50; // Y-coordinate of the ending point
        float lineWidth1 = 0.5F; // Line width in points
        float[] lineColor1 = {0, 0, 0}; //
        contentStream.setLineWidth(lineWidth1);
        contentStream.setStrokingColor(lineColor1[0], lineColor1[1], lineColor1[2]);
        contentStream.moveTo(startX1, startY1);
        contentStream.lineTo(endX1, endY1);
        contentStream.stroke();
    }

    String[] getstringarray1(String otherinfo1) {
//        String ab= otherinfo1.replace("null", "");
        System.out.println("ok           " + otherinfo1);
        String ab2 = otherinfo1.substring(0, otherinfo1.length() - 1);
        String modifiedString = ab2.substring(1);
//        System.out.println("modi  "+modifiedString);
        String[] array1 = modifiedString.split("\\|");
        System.out.println("lenght  123   " + array1.length);
        System.out.println("lenght value  " + array1[0]);
//        System.out.println(array1[0]);
//        System.out.println(array1[1]);
//        System.out.println(array1[2]);
//        System.out.println(array1[3]);
        return array1;
    }

    void headerfooter(PDPageContentStream contentStream, String Comname, String Adress, String Email, String PNo) throws IOException {
        //company name
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
        contentStream.showText(Comname);
        contentStream.endText();
        gy-=12;
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(emX, gy);
        contentStream.showText("Email Id: ");
        contentStream.endText();
        contentStream.setFont(PDType1Font.HELVETICA, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(pmX-20, gy);
        contentStream.showText(Email);
        contentStream.endText();
        gy-=11;
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(emX, gy);
        contentStream.showText("Ph No:  " );
        contentStream.endText();
        contentStream.setFont(PDType1Font.HELVETICA, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(pmX-20, gy);
        contentStream.showText(PNo);
        contentStream.endText();

        gy-=11;
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(emX, gy);
        contentStream.showText("Address :" );
        contentStream.endText();
        String newaddress = Adress.trim().replaceAll("\n", " ");
        System.out.println(newaddress);
        String[] newStrg1 = Adress.split("\n");
        for (String line : newStrg1) {
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.beginText();
            contentStream.newLineAtOffset(pmX-17, gy);
            contentStream.showText(line + " ,");
            contentStream.endText();
            gy -= 11;
        }


        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
        contentStream.beginText();
        contentStream.newLineAtOffset(36, gy-10);
        contentStream.showText("ID ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(70, gy-10);
        contentStream.showText("Serial No ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(300, gy-10);
        contentStream.showText("Description");
        contentStream.endText();
        //footer
        Hdrawline(contentStream, 50);
        contentStream.setFont(PDType1Font.HELVETICA, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(90, 35);
        contentStream.showText("Measured in Digital Moisture Meter By Innovative Instruments,vadodara,gujarat,india.");
        contentStream.endText();
        contentStream.setFont(PDType1Font.HELVETICA, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(110, 25);
        contentStream.showText("Visit Us : www.innovative-instruments.in ");
        contentStream.endText();
        gy2=gy;
    }


    public void printtable(ResultSet resultSet, String Comname, String Adress, String Email, String PNo) throws SQLException, InterruptedException, IOException {
        Image image = null;
        File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);

        int pageNo = 0;
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
        Thread.sleep(20);
        // Create a VBox to hold the nodes
        AnchorPane vbox = new AnchorPane();//documnet
        vbox.getStyleClass().add("back_anchore_color");
        List<AnchorPane> anchorPanes = new ArrayList<>();
        AnchorPane page;

        page = new AnchorPane();
        page.setPrefWidth(600);
        page.setPrefHeight(830);
        page.getStyleClass().add("back_anchore_color");
        pageNo += 1;
        Label label1 = lablefunc("Page " + pageNo, 545, 800);
        page.getChildren().add(label1);
        hederfooterfxml(page, Comname, Adress, Email, PNo);
        ImageView imageView = new ImageView(image1);
        imageView.setLayoutX(10);
        imageView.setLayoutY(775);
        double desiredWidth = 75.0;
        double desiredHeight = 30.0;
        imageView.setFitWidth(desiredWidth);
        imageView.setFitHeight(desiredHeight);
        page.getChildren().add(imageView);

        ImageView imageView2 = new ImageView(image);
        imageView2.setLayoutX(5);
        imageView2.setLayoutY(5);
        double desiredWidth2 = 90.0;
        double desiredHeight2 = 60.0;
        imageView2.setFitWidth(desiredWidth2);
        imageView2.setFitHeight(desiredHeight2);
        page.getChildren().add(imageView2);

        anchorPanes.add(page);
        int inc_y = 120;
        int id_value = 0;
        int srx = 50;
        int idx = 20;

        int macx = 125;
        int comdx = 255;
        int sqrx = 410;

        int moistx = 410;
        int tempx = 255;
        int timdatex = 125;

        int macvx = 160;
        int comdvx = 330;
        int sqrvx = 515;

        int moistvx = 455;
        int tempvx = 340;
        int timdatevx = 150;

        int otherinfox = 125;
        int lasty2;

        while (resultSet.next()) {
            id_value += 1;

            if (inc_y >= 730) {

                inc_y = 120;
                page = new AnchorPane();
                page.setPrefWidth(600);
                page.setPrefHeight(830);
                page.getStyleClass().add("back_anchore_color");
                ImageView imageView1 = new ImageView(image);
                imageView1.setLayoutX(5);
                imageView1.setLayoutY(5);
                double desiredWidth1 = 90.0;
                double desiredHeight1 = 60.0;
                imageView1.setFitWidth(desiredWidth1);
                imageView1.setFitHeight(desiredHeight1);
                page.getChildren().add(imageView1);

                ImageView imageView3 = new ImageView(image1);
                imageView3.setLayoutX(10);
                imageView3.setLayoutY(775);
                double desiredWidth3 = 75.0;
                double desiredHeight3 = 30.0;
                imageView3.setFitWidth(desiredWidth3);
                imageView3.setFitHeight(desiredHeight3);
                page.getChildren().add(imageView3);

                pageNo += 1;
                Label label2 = lablefunc("Page " + pageNo, 545, 800);
                page.getChildren().add(label2);

                hederfooterfxml(page, Comname, Adress, Email, PNo);
                anchorPanes.add(page);
            }
            page.getChildren().add(lablefunc(String.valueOf(id_value), idx, inc_y));
            page.getChildren().add(lablefunc(resultSet.getString(2), srx, inc_y));

            page.getChildren().add(lablefunc1("Mac ID : ", macx, inc_y));
            page.getChildren().add(lablefunc(" " + resultSet.getString(3), macvx, inc_y + 1));
            page.getChildren().add(lablefunc1("Commodity Name : ", comdx, inc_y));
            page.getChildren().add(lablefunc("  " + resultSet.getString(3), comdvx, inc_y + 1));

            page.getChildren().add(lablefunc1("Sample Quantity Required : ", sqrx, inc_y));
            if (resultSet.getString(7).equals("FULL")) {
                page.getChildren().add(lablefunc(" " + resultSet.getString(7), sqrvx, inc_y + 1));
            } else {
                page.getChildren().add(lablefunc(" " + resultSet.getString(7) + " grams", sqrvx, inc_y + 1));
            }
            String r6 = resultSet.getString(6);
            LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
            String nr6 = localDateTime.format(globeldtformatter);
            page.getChildren().add(lablefunc1("Time : ", timdatex, inc_y + 11));
            page.getChildren().add(lablefunc(" " + nr6, timdatevx, inc_y + 11 + 1));
            page.getChildren().add(lablefunc1("Sample Temperature : ", tempx, inc_y + 11));
            page.getChildren().add(lablefunc("  " + resultSet.getString(5) + " (°C)", tempvx, inc_y + 11 + 1));
            page.getChildren().add(lablefunc1("Moisture  : ", moistx, inc_y + 11));
            page.getChildren().add(lablefunc("  " + resultSet.getString(4) + " %", moistvx, inc_y + 11 + 1));

            page.getChildren().add(lablefunc1("Other Information : ", otherinfox, inc_y + 21));

            String otherinfo1 = resultSet.getString(8);
            String[] otherinfoarray = getstringarray1("otherinfo1");
            lasty2 = inc_y + 33;
            System.out.print("onterrrrrrr" + otherinfoarray[0]);
            if (otherinfoarray.length == 1) {

                page.getChildren().add(lablefunc("---", otherinfox + 110, inc_y + 21));
                Line line2 = new Line(10, lasty2 + 5, 575, lasty2 + 5);
                line2.setStrokeWidth(0.3);
                page.getChildren().add(line2);
                inc_y = lasty2;
            }
            inc_y += 20;
        }
        vbox.getChildren().addAll(anchorPanes);
        // Create a scene with the VBox
        Scene scene = new Scene(vbox, vbox.getWidth(), vbox.getHeight());
        scene.getStylesheets().add("dashboardDesign.css");
        // Create a printer job
        javafx.print.PrinterJob job = PrinterJob.createPrinterJob();
        Stage s1 = null;
        if (job != null) {
            // Show the print dialog
            boolean showDialog = job.showPrintDialog(s1);
            if (showDialog) {
                // Set the job settings
                Printer printer = Printer.getDefaultPrinter();
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 5, 5, 5, 5);

                double scaleX = pageLayout.getPrintableWidth() / vbox.getBoundsInParent().getWidth();
                double scaleY = pageLayout.getPrintableHeight() / vbox.getBoundsInParent().getHeight();
                double scaleFactor = Math.min(scaleX, scaleY);

                // Apply the scale transformation to the AnchorPane
                vbox.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
                System.out.println("hirifht " + scaleFactor);
                // Set the page layout for the print job

                job.getJobSettings().setPageLayout(pageLayout);

                // Print each node on separate pages
                for (Node node : vbox.getChildren()) {
                    boolean success = job.printPage(node);
                    if (!success) {
                        break;
                    }
                }

                job.endJob();
            } else {
                // User canceled the print dialog
                job.cancelJob();
            }
        }

    }

    Label lablefunc(String data, float x, float y) {
        Label label = new Label(data);
//        label.styleProperty();
        label.getStyleClass().add("P_lab_row");
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    Label lablefunc1(String data, float x, float y) {
        Label label = new Label(data);
//        label.styleProperty();
        label.getStyleClass().add("p_lab_row1");
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    void hederfooterfxml(AnchorPane page, String comname, String address, String email, String phno) {

        page.getStyleClass().add("P_background_color");
        page.setMinSize(600, 700);
        Label companyname = new Label(comname);
        companyname.getStyleClass().add("p_lab_com_name");
        Label company_adress = new Label(address);
        Label emailid = new Label("Email Id:  " + email);
        Label phoneNo = new Label("Phone No :  " + phno);
        emailid.getStyleClass().add("p_lab_email_ph");
        phoneNo.getStyleClass().add("p_lab_email_ph");
        company_adress.getStyleClass().add("p_lab_address");

        companyname.setLayoutX(155);
        companyname.setLayoutY(17);
        company_adress.setLayoutX(190);
        company_adress.setLayoutY(37);
        emailid.setLayoutX(420);
        emailid.setLayoutY(5);
        phoneNo.setLayoutX(420);
        phoneNo.setLayoutY(13);

        Line line = new Line(10, 80, 575, 80);
        Line line2 = new Line(10, 770, 575, 770);

        Line line3 = new Line(10, 80, 10, 770);
        Line line4 = new Line(575, 80, 575, 770);

        Line line5 = new Line(110, 80, 110, 770);

        Line line6 = new Line(10, 111, 575, 111);
        Line line7 = new Line(40, 80, 40, 770);
        line.setStrokeWidth(0.5);
        line2.setStrokeWidth(0.5);
        line3.setStrokeWidth(0.5);
        line4.setStrokeWidth(0.5);
        line5.setStrokeWidth(0.5);
        line6.setStrokeWidth(0.5);
        line7.setStrokeWidth(0.5);
        Label footer = lablefunc("Measured in Digital Moisture Meter By Innovative Instruments,vadodara,gujarat,india.\n      Visit Us : www.innovative-instruments.in ", 150, 780);
        footer.getStyleClass().add("p_lab_address");
        Label id = new Label("ID");
        Label srno1 = new Label("Serial No");
        Label srno2 = new Label("Description");
        id.setLayoutX(20);
        id.setLayoutY(95);
        id.getStyleClass().add("p_lab_id_sr");
        srno1.setLayoutX(50);
        srno1.setLayoutY(95);
        srno1.getStyleClass().add("p_lab_id_sr");
        srno2.setLayoutX(280);
        srno2.setLayoutY(95);
        srno2.getStyleClass().add("p_lab_id_sr");

//    page.getChildren().add(imageView);
        page.getChildren().add(companyname);
        page.getChildren().add(company_adress);
        page.getChildren().add(emailid);
        page.getChildren().add(phoneNo);
        page.getChildren().add(line);
        page.getChildren().add(line2);
        page.getChildren().add(line3);
        page.getChildren().add(line4);
        page.getChildren().add(line5);
        page.getChildren().add(line6);
        page.getChildren().add(line7);
        page.getChildren().add(id);
        page.getChildren().add(srno1);
        page.getChildren().add(srno2);
        page.getChildren().add(footer);
    }




}

