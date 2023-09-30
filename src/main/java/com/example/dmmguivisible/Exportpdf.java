package com.example.dmmguivisible;

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
import javafx.scene.layout.Pane;
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


public class Exportpdf {
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

    ByteArrayOutputStream outputStream;
    DateTimeFormatter globeldtformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    DateTimeFormatter formattor24Hours = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    public byte[] getpdfbytes(ResultSet resultSet, String Comname, String Adress, String Email, String PNo) throws IOException {
        System.out.println("emailing pdf");

        BufferedImage image = null;
        File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (isImageFile(file)) {
                image = ImageIO.read(file);
                break;
            } else {
                Image image00 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                image = SwingFXUtils.fromFXImage(image00, null);
            }
        }
        Image image0 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage bufferedImage1 = SwingFXUtils.fromFXImage(image0, null);
//          BufferedImage image1 = ImageIO.read(new File("src/main/resources/images/dmmlogo.jpg"));
        float ix = 10; // X-coordinate
        float iy = 720; // Y-coordinate
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
            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
            //line
            Vdrawline(contentStream, 130);
            Vdrawline(contentStream, 25);
            Vdrawline(contentStream, 590);
            Vdrawline(contentStream, 55);
            pageNo += 1;
            contentStream.setFont(PDType1Font.HELVETICA, 7);
            contentStream.beginText();
            contentStream.newLineAtOffset(550, 20);
            contentStream.showText(String.valueOf("Page " + pageNo));
            contentStream.endText();

            headerfooter(contentStream, Comname, Adress, Email, PNo);
            Hdrawline(contentStream, 700);
            Hdrawline(contentStream, 676);
            int idx = 36;
            int srnox = 60;
            int macidx = 150;
            int comdx = 270;
            int Sqrx = 430;
            int temx = 270;
            int dattimex = 150;
            int moisx = 430;
            int othrinfox = 150;
            int otherinfoarrax = 180;

            int macVX = 180;
            int comDVX = 345;
            int samsizeVX = 535;
            int dattimeVX = 175;
            int tempVX = 355;
            int moistVX = 470;

            int y1 = 660;
            int lasty2;
            int idv = 0;
            while (resultSet.next()) {
                if (y1 <= 100) {
                    System.out.println("wht is dasf--------------------------------------------------------------------------------");
                    y1 = 660;
                    contentStream.close();
                    PDPage newpage = new PDPage();
                    document.addPage(newpage);
                    contentStream = new PDPageContentStream(document, newpage);
                    contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
                    contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
                    pageNo += 1;
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(550, 20);
                    contentStream.showText(String.valueOf("Page " + pageNo));
                    contentStream.endText();

                    headerfooter(contentStream, Comname, Adress, Email, PNo);
                    Hdrawline(contentStream, 700);
                    Hdrawline(contentStream, 676);
                    Vdrawline(contentStream, 130);
                    Vdrawline(contentStream, 25);
                    Vdrawline(contentStream, 590);
                    Vdrawline(contentStream, 55);
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
                contentStream.newLineAtOffset(macidx, y1);
                contentStream.showText("MAC ID :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(macVX, y1);
                contentStream.showText("  " + resultSet.getString(3));
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(comdx, y1);
                contentStream.showText("Commodity Name :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(comDVX, y1);
                contentStream.showText("  " + resultSet.getString(4));
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(Sqrx, y1);
                contentStream.showText("Sample Quantity Required :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(samsizeVX, y1);
                if (resultSet.getString(8).equals("FULL")) {
                    contentStream.showText("  " + resultSet.getString(8));
                } else {
                    contentStream.showText("  " + resultSet.getString(8) + " gram");
                }
                contentStream.endText();

                String r6 = resultSet.getString(7);
                LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                String nr6 = localDateTime.format(globeldtformatter);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(dattimex, y1 - 10);
                contentStream.showText("Time :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(dattimeVX, y1 - 10);
                contentStream.showText("  " + nr6);
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(temx, y1 - 10);
                contentStream.showText("Sample Temperature :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(tempVX, y1 - 10);
                contentStream.showText("  " + resultSet.getString(6) + " °C");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(moisx, y1 - 10);
                contentStream.showText("Moisture :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(moistVX, y1 - 10);
                contentStream.showText("  " + resultSet.getString(5) + " %");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(othrinfox, y1 - 21);
                contentStream.showText("Other Information : ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String otherinfo1 = resultSet.getString(9);
                String[] otherinfoarray = getstringarray1(otherinfo1);
                System.out.println("yee " + otherinfoarray.length);  //lenth 1 if no data.
                lasty2 = y1 - 31;
                if (otherinfoarray.length == 1) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(otherinfoarrax + 50, lasty2 + 10);
                    contentStream.showText("---");
                    contentStream.endText();
                    Hdrawline(contentStream, lasty2);
                    y1 = lasty2;
                } else {
                    for (int a = 0; a < otherinfoarray.length - 1; a++) {

                        if (lasty2 <= 100) {
                            y1 = 660;
                            contentStream.close();
                            PDPage newpage = new PDPage();
                            document.addPage(newpage);
                            contentStream = new PDPageContentStream(document, newpage);
                            contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
                            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
                            pageNo += 1;
                            contentStream.setFont(PDType1Font.HELVETICA, 7);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(550, 20);
                            contentStream.showText(String.valueOf("Page " + pageNo));
                            contentStream.endText();

                            headerfooter(contentStream, Comname, Adress, Email, PNo);
                            Hdrawline(contentStream, 700);
                            Hdrawline(contentStream, 676);
                            Vdrawline(contentStream, 130);
                            Vdrawline(contentStream, 25);
                            Vdrawline(contentStream, 590);
                            Vdrawline(contentStream, 55);
                            contentStream.setFont(PDType1Font.HELVETICA, 8);
                            lasty2 = y1 + 20;
                        }
                        contentStream.beginText();
                        contentStream.newLineAtOffset(otherinfoarrax, lasty2);
                        contentStream.showText(otherinfoarray[a] + ",");
                        contentStream.endText();
                        lasty2 -= 10;

                        if (a == otherinfoarray.length - 2) {
                            y1 = lasty2;
                            System.out.println("loop  " + y1);
                            Hdrawline(contentStream, y1 - 5);
                        }
                    }
                }
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

    void exportpdf(ResultSet resultSet, String Comname, String Adress, String Email, String PNo, String fromexem, String toexem) throws IOException {
//
        BufferedImage image = null;
        File folder = new File(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + User_Profile);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (isImageFile(file)) {
                image = ImageIO.read(file);
                break;
            } else {
                Image image00 = new Image(getClass().getResource("/images/innovativeLogo.jpg").toExternalForm());
                image = SwingFXUtils.fromFXImage(image00, null);
            }
        }
        Image image0 = new Image(getClass().getResource("/images/dmmlogo.jpg").toExternalForm());
        BufferedImage bufferedImage1 = SwingFXUtils.fromFXImage(image0, null);
//          BufferedImage image1 = ImageIO.read(new File("src/main/resources/images/dmmlogo.jpg"));
        float ix = 10; // X-coordinate
        float iy = 720; // Y-coordinate
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
            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
            //line
            Vdrawline(contentStream, 130);
            Vdrawline(contentStream, 25);
            Vdrawline(contentStream, 590);
            Vdrawline(contentStream, 55);
            pageNo += 1;
            contentStream.setFont(PDType1Font.HELVETICA, 7);
            contentStream.beginText();
            contentStream.newLineAtOffset(550, 20);
            contentStream.showText(String.valueOf("Page " + pageNo));
            contentStream.endText();

            headerfooter(contentStream, Comname, Adress, Email, PNo);
            Hdrawline(contentStream, 700);
            Hdrawline(contentStream, 676);
            int idx = 36;
            int srnox = 60;
            int macidx = 150;
            int comdx = 270;
            int Sqrx = 430;
            int temx = 270;
            int dattimex = 150;
            int moisx = 430;
            int othrinfox = 150;
            int otherinfoarrax = 180;

            int macVX = 180;
            int comDVX = 345;
            int samsizeVX = 535;
            int dattimeVX = 175;
            int tempVX = 355;
            int moistVX = 470;

            int y1 = 660;
            int lasty2;
            int idv = 0;
            while (resultSet.next()) {
                if (y1 <= 100) {
                    System.out.println("wht is dasf--------------------------------------------------------------------------------");
                    y1 = 660;
                    contentStream.close();
                    PDPage newpage = new PDPage();
                    document.addPage(newpage);
                    contentStream = new PDPageContentStream(document, newpage);
                    contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
                    contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
                    pageNo += 1;
                    contentStream.setFont(PDType1Font.HELVETICA, 7);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(550, 20);
                    contentStream.showText(String.valueOf("Page " + pageNo));
                    contentStream.endText();

                    headerfooter(contentStream, Comname, Adress, Email, PNo);
                    Hdrawline(contentStream, 700);
                    Hdrawline(contentStream, 676);
                    Vdrawline(contentStream, 130);
                    Vdrawline(contentStream, 25);
                    Vdrawline(contentStream, 590);
                    Vdrawline(contentStream, 55);
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
                contentStream.newLineAtOffset(macidx, y1);
                contentStream.showText("MAC ID :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(macVX, y1);
                contentStream.showText("  " + resultSet.getString(3));
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(comdx, y1);
                contentStream.showText("Commodity Name :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(comDVX, y1);
                contentStream.showText("  " + resultSet.getString(4));
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(Sqrx, y1);
                contentStream.showText("Sample Quantity Required :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(samsizeVX, y1);
                if (resultSet.getString(8).equals("FULL")) {
                    contentStream.showText("  " + resultSet.getString(8));
                } else {
                    contentStream.showText("  " + resultSet.getString(8) + " gram");
                }
                contentStream.endText();

                String r6 = resultSet.getString(7);
                LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                String nr6 = localDateTime.format(globeldtformatter);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(dattimex, y1 - 10);
                contentStream.showText("Time :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(dattimeVX, y1 - 10);
                contentStream.showText("  " + nr6);
                contentStream.endText();

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(temx, y1 - 10);
                contentStream.showText("Sample Temperature :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(tempVX, y1 - 10);
                contentStream.showText("  " + resultSet.getString(6) + " °C");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(moisx, y1 - 10);
                contentStream.showText("Moisture :  ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                contentStream.beginText();
                contentStream.newLineAtOffset(moistVX, y1 - 10);
                contentStream.showText("  " + resultSet.getString(5) + " %");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
                contentStream.beginText();
                contentStream.newLineAtOffset(othrinfox, y1 - 21);
                contentStream.showText("Other Information : ");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 7);
                String otherinfo1 = resultSet.getString(9);
                String[] otherinfoarray = getstringarray1(otherinfo1);
                System.out.println("yee " + otherinfoarray.length);  //lenth 1 if no data.
                lasty2 = y1 - 31;
                if (otherinfoarray.length == 1) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(otherinfoarrax + 50, lasty2 + 10);
                    contentStream.showText("---");
                    contentStream.endText();
                    Hdrawline(contentStream, lasty2);
                    y1 = lasty2;
                } else {
                    for (int a = 0; a < otherinfoarray.length - 1; a++) {

                        if (lasty2 <= 100) {
                            y1 = 660;
                            contentStream.close();
                            PDPage newpage = new PDPage();
                            document.addPage(newpage);
                            contentStream = new PDPageContentStream(document, newpage);
                            contentStream.drawImage(pdImage, ix, iy, iwidth, iheight);
                            contentStream.drawImage(pdImage1, ix1, iy1, iwidth1, iheight1);
                            pageNo += 1;
                            contentStream.setFont(PDType1Font.HELVETICA, 7);
                            contentStream.beginText();
                            contentStream.newLineAtOffset(550, 20);
                            contentStream.showText(String.valueOf("Page " + pageNo));
                            contentStream.endText();

                            headerfooter(contentStream, Comname, Adress, Email, PNo);
                            Hdrawline(contentStream, 700);
                            Hdrawline(contentStream, 676);
                            Vdrawline(contentStream, 130);
                            Vdrawline(contentStream, 25);
                            Vdrawline(contentStream, 590);
                            Vdrawline(contentStream, 55);
                            contentStream.setFont(PDType1Font.HELVETICA, 8);
                            lasty2 = y1 + 20;
                        }
                        contentStream.beginText();
                        contentStream.newLineAtOffset(otherinfoarrax, lasty2);
                        contentStream.showText(otherinfoarray[a] + ",");
                        contentStream.endText();
                        lasty2 -= 10;

                        if (a == otherinfoarray.length - 2) {
                            y1 = lasty2;
                            System.out.println("loop  " + y1);
                            Hdrawline(contentStream, y1 - 5);
                        }
                    }
                }
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
                document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF + "/" + pdffilename + ".pdf");
                System.out.println("PDF created successfully.");
                String filepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + PDF + "\\" + pdffilename + ".pdf";
                if (filepath1 != null) {
                    // Open the selected PDF file in a PDF viewer
                    try {
                        Desktop.getDesktop().open(new File(filepath1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                showAlertinfo("", "Pdf File is saved at " + filepath1);
            } else {
                document.save(defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + PDF + "/From_" + fromexem + "_To_" + toexem + "_" + pdffilename + ".pdf");
                System.out.println("PDF created successfully.");
                String filepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + PDF + "\\From_" + fromexem + "_To_" + toexem + "_" + pdffilename + ".pdf";
                if (filepath1 != null) {
                    // Open the selected PDF file in a PDF viewer
                    try {
                        Desktop.getDesktop().open(new File(filepath1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                showAlertinfo("", "Pdf File is saved at " + filepath1);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    void Vdrawline(PDPageContentStream contentStream, float x1) throws IOException {
        float startX1 = x1; // X-coordinate of the starting point
        float startY1 = 700; // Y-coordinate of the starting point
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
        contentStream.setFont(PDType1Font.HELVETICA, 19);
        contentStream.beginText();
        contentStream.newLineAtOffset(160, 750);
        contentStream.showText(Comname);
        contentStream.endText();
        //address
        float Ax1 = 190;
        float Ay1 = 740;
        String newaddress = Adress.trim().replaceAll("\n", " ");
        System.out.println(newaddress);
        String[] newStrg1 = Adress.split("\n");
        for (String line : newStrg1) {
            contentStream.setFont(PDType1Font.HELVETICA, 7);
            contentStream.beginText();
            contentStream.newLineAtOffset(Ax1, Ay1);
            contentStream.showText(line + " ,");
            contentStream.endText();
            Ay1 -= 11;
        }
        //email and phone no
        contentStream.setFont(PDType1Font.HELVETICA, 7);
        contentStream.beginText();
        contentStream.newLineAtOffset(450, 775);
        contentStream.showText("Email id : " + Email);
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(450, 765);
        contentStream.showText("Phone No : " + PNo);
        contentStream.endText();

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 9);
        contentStream.beginText();
        contentStream.newLineAtOffset(36, 680);
        contentStream.showText("ID ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(70, 680);
        contentStream.showText("Serial No ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(300, 680);
        contentStream.showText("Description");
        contentStream.endText();
        //footer
        Hdrawline(contentStream, 50);
        contentStream.setFont(PDType1Font.HELVETICA, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(140, 35);
        contentStream.showText("Measured in Digital Moisture Meter By Innovative Instruments,vadodara,gujarat,india.");
        contentStream.endText();
        contentStream.setFont(PDType1Font.HELVETICA, 8);
        contentStream.beginText();
        contentStream.newLineAtOffset(220, 25);
        contentStream.showText("Visit Us : www.innovative-instruments.in ");
        contentStream.endText();

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
            page.getChildren().add(lablefunc("  " + resultSet.getString(4), comdvx, inc_y + 1));

            page.getChildren().add(lablefunc1("Sample Quantity Required : ", sqrx, inc_y));
            if (resultSet.getString(8).equals("FULL")) {
                page.getChildren().add(lablefunc(" " + resultSet.getString(8), sqrvx, inc_y + 1));
            } else {
                page.getChildren().add(lablefunc(" " + resultSet.getString(8) + " gram", sqrvx, inc_y + 1));
            }
            String r6 = resultSet.getString(7);
            LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
            String nr6 = localDateTime.format(globeldtformatter);
            page.getChildren().add(lablefunc1("Time : ", timdatex, inc_y + 11));
            page.getChildren().add(lablefunc(" " + nr6, timdatevx, inc_y + 11 + 1));
            page.getChildren().add(lablefunc1("Sample Temperature : ", tempx, inc_y + 11));
            page.getChildren().add(lablefunc("  " + resultSet.getString(6) + " (°C)", tempvx, inc_y + 11 + 1));
            page.getChildren().add(lablefunc1("Moisture  : ", moistx, inc_y + 11));
            page.getChildren().add(lablefunc("  " + resultSet.getString(5) + " %", moistvx, inc_y + 11 + 1));

            page.getChildren().add(lablefunc1("Other Information : ", otherinfox, inc_y + 21));

            String otherinfo1 = resultSet.getString(9);
            String[] otherinfoarray = getstringarray1(otherinfo1);
            lasty2 = inc_y + 33;
            System.out.print("onterrrrrrr" + otherinfoarray[0]);
            if (otherinfoarray.length == 1) {

                page.getChildren().add(lablefunc("---", otherinfox + 110, inc_y + 21));
                Line line2 = new Line(10, lasty2 + 5, 575, lasty2 + 5);
                line2.setStrokeWidth(0.3);
                page.getChildren().add(line2);
                inc_y = lasty2;
            } else {

                for (int a = 0; a < otherinfoarray.length - 1; a++) {
                    if (lasty2 >= 730) {
                        inc_y = 130;

                        page = new AnchorPane();
                        page.getStyleClass().add("back_anchore_color");
                        page.setPrefWidth(600);
                        page.setPrefHeight(830);

                        ImageView imageView1 = new ImageView(image);
                        imageView1.setLayoutX(2);
                        imageView1.setLayoutY(2);
                        double desiredWidth1 = 80.0;
                        double desiredHeight1 = 60.0;
                        imageView1.setFitWidth(desiredWidth1);
                        imageView1.setFitHeight(desiredHeight1);
                        page.getChildren().add(imageView1);

                        ImageView imageView3 = new ImageView(image1);
                        imageView3.setLayoutX(10);
                        imageView3.setLayoutY(780);
                        double desiredWidth3 = 60.0;
                        double desiredHeight3 = 30.0;
                        imageView3.setFitWidth(desiredWidth3);
                        imageView3.setFitHeight(desiredHeight3);
                        page.getChildren().add(imageView3);

                        pageNo += 1;
                        Label label3 = lablefunc("Page " + pageNo, 545, 800);
                        page.getChildren().add(label3);

                        hederfooterfxml(page, Comname, Adress, Email, PNo);
                        anchorPanes.add(page);
                        lasty2 = inc_y - 20;
                    }
                    page.getChildren().add(lablefunc(otherinfoarray[a].trim() + ",", otherinfox + 5, lasty2));
                    lasty2 += 11;

                    if (a == otherinfoarray.length - 2) {
                        inc_y = lasty2;
                        Line line3 = new Line(10, lasty2 + 5, 575, lasty2 + 5);
                        line3.setStrokeWidth(0.3);
                        page.getChildren().add(line3);
                        System.out.println("c");
                    }
                }
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

    Label pagelablefunc(String data, float x, float y) {
        Label label = new Label(data);
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

    void hederfooterfxmlindivisual(AnchorPane page, String comname, String address, String email, String phno) {
        page.getStyleClass().add("P_background_color");
        Label companyname = new Label(comname);
        companyname.getStyleClass().add("p_lab_com_name");
        Label company_adress = new Label(address);
        Label emailid = new Label("Email Id:  " + email);
        Label phoneNo = new Label("Phone No :  " + phno);
        emailid.getStyleClass().add("p_lab_email_ph");
        phoneNo.getStyleClass().add("p_lab_email_ph");
        company_adress.getStyleClass().add("p_lab_address");

        companyname.setLayoutX(125);
        companyname.setLayoutY(10);
        company_adress.setLayoutX(180);
        company_adress.setLayoutY(40);
        emailid.setLayoutX(395);
        emailid.setLayoutY(10);
        phoneNo.setLayoutX(395);
        phoneNo.setLayoutY(20);

        Line line = new Line(30, 80, 212, 80);
        Line line2 = new Line(30, 500, 212, 500);
        line.setStrokeWidth(0.5);
        line2.setStrokeWidth(0.5);
        Label footer = lablefunc("Measured in Digital Moisture Meter By Innovative Instruments,vadodara,gujarat,india.\n      Visit Us : www.innovative-instruments.in ", 90, 610);
        footer.getStyleClass().add("p_lab_address");

//    page.getChildren().add(imageView);
        page.getChildren().add(companyname);
        page.getChildren().add(company_adress);
        page.getChildren().add(emailid);
        page.getChildren().add(phoneNo);
        page.getChildren().add(line);
        page.getChildren().add(line2);
        page.getChildren().add(footer);
    }

    public Scene printindivusual(String[] fruits, String printtime, String[] informationarray, String comName, String address, String comemail, String comphon) {
        String defaultPath = System.getProperty("user.home");
        File folder = new File(defaultPath + "/dmm3/dmmcompro/imgpro");
        File[] files = folder.listFiles();
        Image image = new Image(String.valueOf(files[0]));
        Pane pane = new Pane();
        pane.setPrefHeight(706);
        pane.setPrefWidth(513);
        AnchorPane doc = new AnchorPane();
        doc.setPrefHeight(706);
        doc.setPrefWidth(513);
        pane.getChildren().add(doc);
        AnchorPane page = new AnchorPane();
        page.setPrefHeight(706);
        page.setPrefWidth(513);
        doc.getChildren().add(page);

        hederfooterfxmlindivisual(page, comName, address, comemail, comphon);

        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(2);
        imageView.setLayoutY(2);
        double desiredWidth = 80.0;
        double desiredHeight = 60.0;
        imageView.setFitWidth(desiredWidth);
        imageView.setFitHeight(desiredHeight);
        page.getChildren().add(imageView);
        int oy = 100;
        int ox = 100;

        int otherinfox = 120;
        int otherinfoy = 120;
        int lasty2;
        int devicedatax = 100;
        page.getChildren().add(lablefunc("Other Information : ", ox, oy));

        for (int a = 0; a < informationarray.length - 1; a++) {
            if (informationarray[a] != null) {
                page.getChildren().add(lablefunc(informationarray[a].trim().replace("|", ","), otherinfox, otherinfoy));
                otherinfoy += 10;
            }

        }
        Line line2 = new Line(30, otherinfoy + 20, 450, otherinfoy + 20);
        line2.setStrokeWidth(0.3);
        page.getChildren().add(line2);
        lasty2 = otherinfoy + 50;

        page.getChildren().add(lablefunc("Serial No : " + fruits[0], devicedatax, lasty2 + 11));
        page.getChildren().add(lablefunc("MAC ID : " + fruits[0], devicedatax, lasty2 + 22));
        page.getChildren().add(lablefunc("Commodity Name : " + fruits[1], devicedatax, lasty2 + 33));
        page.getChildren().add(lablefunc("Moisture : " + fruits[2], devicedatax, lasty2 + 44));
        page.getChildren().add(lablefunc("Sample Temperature : " + fruits[3], devicedatax, lasty2 + 55));
        page.getChildren().add(lablefunc("Time : " + printtime, devicedatax, lasty2 + 66));
        page.getChildren().add(lablefunc("Sample Quantity Required : " + fruits[4], devicedatax, lasty2 + 77));

        Scene scene = new Scene(pane, pane.getWidth(), pane.getHeight());
        scene.getStylesheets().add("dashboardDesign.css");

        return scene;
    }
}

