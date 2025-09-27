package com.example.ReaLogViewDMM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Table_View_Controller {
    public TableColumn<TableModel, String> Tid;
    public TableColumn<TableModel, String> Tsrno;
    public TableColumn<TableModel, String> Tcomname;
    public TableColumn<TableModel, String> Tmois;
    public TableColumn<TableModel, String> Ttemp;
    public TableColumn<TableModel, String> Ttime;
    public TableColumn<TableModel, String> sqrequired;
    public TableColumn<TableModel, String> ClientName;
    public TableColumn<TableModel, String> Location;
    public TableColumn<TableModel, String> TruckNumber;
    public TableColumn<TableModel, String> Vendorid;
    public TableColumn<TableModel, String> TotalWeight;
    public TableColumn<TableModel, String> Remarks;
    @FXML
    public TableView<TableModel> TableView1;
    public Button onclickexcel;

    public String[] comnamedata;
    public String comadress1;

    public String[] oneyearArray;
    public String[] towyearArray;

    public String[] todayArray;

    public Data_Holder comdata;
    public Data_Holder comdata1;



    ResultSet resultsetdata = null;
    ObservableList<String> dataList = FXCollections.observableArrayList();

    DateTimeFormatter globeldtformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    DateTimeFormatter formattor24Hours = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String defaultPath = System.getProperty("user.home");

    public void initialize() {
        Tid.setCellValueFactory(cellData -> cellData.getValue().nameProperty0());
        Tsrno.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Tcomname.setCellValueFactory(cellData -> cellData.getValue().nameProperty2());
        Tmois.setCellValueFactory(cellData -> cellData.getValue().nameProperty3());
        Ttemp.setCellValueFactory(cellData -> cellData.getValue().nameProperty4());
        Ttime.setCellValueFactory(cellData -> cellData.getValue().nameProperty5());
        sqrequired.setCellValueFactory(cellData -> cellData.getValue().nameProperty6());
        ClientName.setCellValueFactory(cellData -> cellData.getValue().nameProperty7());
        Location.setCellValueFactory(cellData -> cellData.getValue().nameProperty8());
        TruckNumber.setCellValueFactory(cellData -> cellData.getValue().nameProperty9());
        Vendorid.setCellValueFactory(cellData -> cellData.getValue().nameProperty10());
        TotalWeight.setCellValueFactory(cellData -> cellData.getValue().nameProperty11());
        Remarks.setCellValueFactory(cellData -> cellData.getValue().nameProperty12());


    }

    public void setcompanyname(Data_Holder comdata) {
        this.comdata = comdata;
        comnamedata = comdata.getComNmae();
    }

    public void oneyeardataArray(Data_Holder arrayone) {
        this.oneyearArray = arrayone.getResultdataforOneyear();
    }

    public void twoyeardataArray(Data_Holder arrayone) {
        this.towyearArray = arrayone.getResultdataforTwoyear();
    }

    public void todayArraydata(Data_Holder todayA) {
        this.todayArray = todayA.getResultdatafortoday();
    }


    @FXML
    void print_table_data() throws SQLException, InterruptedException, IOException {

        if (oneyearArray != null) {
            System.out.print("777777777777777777777777777777777777777777777777777777777777777777777777777777777777" + oneyearArray.length);
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdata(oneyearArray[0], oneyearArray[1], oneyearArray[2]);
            Print_And_Pdf_Of_Table_Records printAndPdfOfTableRecords = new Print_And_Pdf_Of_Table_Records();
          String printpath=  printAndPdfOfTableRecords.exportpdf(resultSet, comnamedata[0], comnamedata[1], comnamedata[3], comnamedata[2], oneyearArray[3], oneyearArray[4],"print");
          if (printpath.isEmpty()){
              return;
          }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY); // small utility window
            stage.setWidth(1);
            stage.setHeight(1);
            // Add a minimal empty scene
            stage.setScene(new Scene(new StackPane()));
            stage.show(); // must show to get a valid window handle
            DMMAPPGUIController.printPdfWithJob(printpath,stage);
            stage.close(); // o
        } else if (towyearArray != null) {

            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdatafortowyears(towyearArray[0], towyearArray[1], towyearArray[2], towyearArray[3]);
            Print_And_Pdf_Of_Table_Records printAndPdfOfTableRecords = new Print_And_Pdf_Of_Table_Records();
           String printpath= printAndPdfOfTableRecords.exportpdf(resultSet, comnamedata[0], comnamedata[1], comnamedata[3], comnamedata[2], towyearArray[4], towyearArray[5],"print");
            if (printpath.isEmpty()){
                return;
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY); // small utility window
            stage.setWidth(1);
            stage.setHeight(1);
            // Add a minimal empty scene
            stage.setScene(new Scene(new StackPane()));
            stage.show(); // must show to get a valid window handle
            DMMAPPGUIController.printPdfWithJob(printpath,stage);
            stage.close(); // o
        } else if (todayArray != null) {
            System.out.println(".....................................................");
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdata(todayArray[0], todayArray[1], todayArray[2]);
            Print_And_Pdf_Of_Table_Records printAndPdfOfTableRecords = new Print_And_Pdf_Of_Table_Records();
            String printpath=printAndPdfOfTableRecords.exportpdf(resultSet, comnamedata[0], comnamedata[1], comnamedata[3], comnamedata[2], "today", "today","print");
            if (printpath.isEmpty()){
                return;
            }
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY); // small utility window
            stage.setWidth(1);
            stage.setHeight(1);
            // Add a minimal empty scene
            stage.setScene(new Scene(new StackPane()));
            stage.show(); // must show to get a valid window handle
            DMMAPPGUIController.printPdfWithJob(printpath,stage);
            stage.close(); // optional, close after printing

        } else {

        }


    }

    @FXML
    public void exportpdf(ActionEvent actionEvent) throws IOException, SQLException {

        if (oneyearArray != null) {
            System.out.print("777777777777777777777777777777777777777777777777777777777777777777777777777777777777" + oneyearArray.length);
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdata(oneyearArray[0], oneyearArray[1], oneyearArray[2]);
            Print_And_Pdf_Of_Table_Records printAndPdfOfTableRecords = new Print_And_Pdf_Of_Table_Records();
            printAndPdfOfTableRecords.exportpdf(resultSet, comnamedata[0], comnamedata[1], comnamedata[3], comnamedata[2], oneyearArray[3], oneyearArray[4],"noprint");
        } else if (towyearArray != null) {
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdatafortowyears(towyearArray[0], towyearArray[1], towyearArray[2], towyearArray[3]);
            Print_And_Pdf_Of_Table_Records printAndPdfOfTableRecords = new Print_And_Pdf_Of_Table_Records();
            printAndPdfOfTableRecords.exportpdf(resultSet, comnamedata[0], comnamedata[1], comnamedata[3], comnamedata[2], towyearArray[4], towyearArray[5],"noprint");
        } else if (todayArray != null) {
            System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdata(todayArray[0], todayArray[1], todayArray[2]);
            Print_And_Pdf_Of_Table_Records printAndPdfOfTableRecords = new Print_And_Pdf_Of_Table_Records();
            printAndPdfOfTableRecords.exportpdf(resultSet, comnamedata[0], comnamedata[1], comnamedata[3], comnamedata[2], "today", "today","noprint");
        } else {

        }
    }

    public String Realogview = "Realogview";
    public String DMM10 = "DMM1.0";
    public String Data_Base = "DataBase";
    public String User_Profile = "User Profile";
    public String Data = "Data";
    public String IPDF = "PDF";
    public String Records = "Records";
    public String PDF = "PDF";
    public String EXCEL = "EXCEL";

    @FXML
    public void exportexcel(ActionEvent actionEvent) throws Exception {
        LocalDateTime now1 = LocalDateTime.now();
        DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String formattedDateTime = formatter12.format(now1);

//        String exlefilepath1 = defaultPath+"\\"+Realogview+"\\"+DMM10+"\\"+Records+"\\"+EXCEL+"\\"+formattedDateTime+".xlsx";

        if (oneyearArray != null) {
            String exlefilepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + EXCEL + "\\From_" + oneyearArray[3] + "_To_" + oneyearArray[4]+".xlsx";
            String exlefilepath = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + EXCEL + "/From_" + oneyearArray[3] + "_To_" + oneyearArray[4] +".xlsx";
            System.out.print("777777777777777777777777777777777777777777777777777777777777777777777777777777777777" + oneyearArray.length);
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdata(oneyearArray[0], oneyearArray[1], oneyearArray[2]);
            convertToExcel(resultSet, exlefilepath);

            if (exlefilepath1 != null) {
                // Open the selected PDF file in a PDF viewer
                try {
                    Desktop.getDesktop().open(new File(exlefilepath1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            showAlertinfo("", "Excel File is saved at " + exlefilepath1);
        } else if (towyearArray != null) {
            String exlefilepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + EXCEL + "\\From_" + towyearArray[4] + "_To_" + towyearArray[5] +".xlsx";
            String exlefilepath = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + EXCEL + "/From_" + towyearArray[3] + "_To_" + towyearArray[4] +".xlsx";
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdatafortowyears(towyearArray[0], towyearArray[1], towyearArray[2], towyearArray[3]);

            convertToExcel(resultSet, exlefilepath);
            if (exlefilepath1 != null) {
                // Open the selected PDF file in a PDF viewer
                try {
                    Desktop.getDesktop().open(new File(exlefilepath1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            showAlertinfo("", "Excel File is saved at " + exlefilepath1);
        } else if (todayArray != null) {
            String exlefilepath1 = defaultPath + "\\" + Realogview + "\\" + DMM10 + "\\" + Records + "\\" + EXCEL + "\\" + formattedDateTime + ".xlsx";
            String exlefilepath = defaultPath + "/" + Realogview + "/" + DMM10 + "/" + Records + "/" + EXCEL + "/" + formattedDateTime + ".xlsx";
            System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
            DMM_APP_GUI_Controller DMMAPPGUIController = new DMM_APP_GUI_Controller();
            ResultSet resultSet = DMMAPPGUIController.getallresultdata(todayArray[0], todayArray[1], todayArray[2]);
            convertToExcel(resultSet, exlefilepath);

            if (exlefilepath1 != null) {
                // Open the selected PDF file in a PDF viewer
                try {
                    Desktop.getDesktop().open(new File(exlefilepath1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            showAlertinfo("", "Excel File is saved at " + exlefilepath1);
        } else {

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

    public void convertToExcel(ResultSet resultSet, String filePath) throws Exception {

        Workbook workbook = WorkbookFactory.create(true);
        Sheet sheet = workbook.createSheet("Sheet1");
        sheet.protectSheet("realogview");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);
        String[] colname = {"0", "ID", "Serial No","Commodity Name", "Moisture %", "Sample Temperature (°C) ", "Time", "Sample Quantity Required (gram)", "Client Name","Location","Truck Number","Vendor ID","Total Weight","Remarks"};
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.println(columnName);
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
        sheet.setColumnWidth(9, firstColumnWidth + 30 * 256);
        sheet.setColumnWidth(10, firstColumnWidth + 40 * 256);
        sheet.setColumnWidth(11, firstColumnWidth + 20 * 256);
        sheet.setColumnWidth(13, firstColumnWidth + 20 * 256);
        int rowNum = 1;
        int idvalue = 1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 1; i <= columnCount; i++) {

                if (i == 1) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(idvalue);
                } else if (i == 6) {
                    Cell cell = row.createCell(i - 1);
                    String r6 = resultSet.getString(6);
                    LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                    String nr6 = localDateTime.format(globeldtformatter);
                    cell.setCellValue(nr6);
                }
                else if(i==11){
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(13));
                }
                else if(i==12){
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(11));
                }

                else if(i==13){
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(12));
                }

                else {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                }

            }
            idvalue += 1;
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {

            workbook.write(outputStream);
        }

        workbook.close();
    }
}

class TableModel {
    private SimpleStringProperty Tid;
    private SimpleStringProperty Tsrno;
    private SimpleStringProperty commname;
    private SimpleStringProperty moist;
    private SimpleStringProperty Ttemp;
    private SimpleStringProperty Ttime;
    private SimpleStringProperty sqrequire;
    private SimpleStringProperty ClientName;
    private SimpleStringProperty Location;
    private SimpleStringProperty TruckNumber;
    private SimpleStringProperty Vendorid;
    private SimpleStringProperty TotalWeight;
    private SimpleStringProperty Remarks;

    public TableModel(String Tid, String srno, String commname, String moist, String Ttemp, String Ttime, String sqreqd, String ClientName,String Location,String TruckNumber, String Vendorid,String TotalWeight,String Remarks) {
        this.Tid = new SimpleStringProperty(Tid);
        this.Tsrno = new SimpleStringProperty(srno);
        this.commname = new SimpleStringProperty(commname);
        this.moist = new SimpleStringProperty(moist);
        this.Ttemp = new SimpleStringProperty(Ttemp);
        this.Ttime = new SimpleStringProperty(Ttime);
        this.sqrequire = new SimpleStringProperty(sqreqd);
        this.ClientName = new SimpleStringProperty(ClientName);
        this.Location = new SimpleStringProperty(Location);
        this.TruckNumber = new SimpleStringProperty(TruckNumber);
        this.Vendorid = new SimpleStringProperty(Vendorid);
        this.TotalWeight = new SimpleStringProperty(TotalWeight);
        this.Remarks = new SimpleStringProperty(Remarks);
    }

    public String getrno() {
        return Tsrno.get();
    }

    public String getcommname() {
        return commname.get();
    }

    public String getmoist() {
        return moist.get();
    }

    public String getTemp() {
        return Ttemp.get();
    }

    public String getTime() {
        return Ttime.get();
    }

    public String getsqred() {
        return sqrequire.get();
    }

    public String getClientName() {
        return ClientName.get();
    }
    public String getLocation() {
        return Location.get();
    }
    public String getTurckNumber() {
        return TruckNumber.get();
    }
    public String getVendorid() {
        return Vendorid.get();
    }
    public String getTotalWeight() {
        return TotalWeight.get();
    }
    public String getRemarks() {
        return Remarks.get();
    }

    public SimpleStringProperty nameProperty0() {
        return Tid;
    }

    public SimpleStringProperty nameProperty() {
        return Tsrno;
    }

    public SimpleStringProperty nameProperty2() {
        return commname;
    }

    public SimpleStringProperty nameProperty3() {
        return moist;
    }

    public SimpleStringProperty nameProperty4() {
        return Ttemp;
    }

    public SimpleStringProperty nameProperty5() {
        return Ttime;
    }

    public SimpleStringProperty nameProperty6() {
        return sqrequire;
    }
    public SimpleStringProperty nameProperty7() {
        return ClientName;
    }
    public SimpleStringProperty nameProperty8() {
        return Location;
    }
    public SimpleStringProperty nameProperty9() {
        return TruckNumber;
    }
    public SimpleStringProperty nameProperty10() {
        return Vendorid;
    }
    public SimpleStringProperty nameProperty11() {
        return TotalWeight;
    }
    public SimpleStringProperty nameProperty12() {
        return Remarks;
    }

}