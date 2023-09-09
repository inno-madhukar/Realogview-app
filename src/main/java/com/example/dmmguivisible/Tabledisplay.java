package com.example.dmmguivisible;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Tabledisplay   {
    public TableColumn<TableModel,String> Tid;
    public TableColumn<TableModel,String> Tsrno;
    public TableColumn<TableModel,String> Tmacid;
    public TableColumn<TableModel,String> Tcomname;
    public TableColumn<TableModel,String> Tmois;
    public TableColumn<TableModel,String> Ttemp;
    public TableColumn<TableModel,String> Ttime;
    public TableColumn<TableModel,String> sqrequired;
    public TableColumn<TableModel,String> othinfo;
    @FXML
    public TableView<TableModel> TableView1;
    public Button onclickexcel;

    public String[] comnamedata;
    public String comadress1;

    public String[] oneyearArray;
    public String[] towyearArray;

    public String[] todayArray;

    public Hoder comdata;
    public Hoder comdata1;

    ResultSet resultsetdata=null;
    ObservableList<String> dataList = FXCollections.observableArrayList();

    DateTimeFormatter globeldtformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    DateTimeFormatter formattor24Hours = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String defaultPath = System.getProperty("user.home");

    public  void initialize(){
        Tid.setCellValueFactory(cellData -> cellData.getValue().nameProperty0());
        Tsrno.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Tmacid.setCellValueFactory(cellData -> cellData.getValue().nameProperty2());
        Tcomname.setCellValueFactory(cellData -> cellData.getValue().nameProperty3());
        Tmois.setCellValueFactory(cellData -> cellData.getValue().nameProperty4());

        Ttemp.setCellValueFactory(cellData -> cellData.getValue().nameProperty5());
        Ttime.setCellValueFactory(cellData -> cellData.getValue().nameProperty6());
        sqrequired.setCellValueFactory(cellData -> cellData.getValue().nameProperty7());
        othinfo.setCellValueFactory(cellData -> cellData.getValue().nameProperty9());

    }
    public void setcompanyname(Hoder comdata) {
        this.comdata=comdata;
        comnamedata=comdata.getComNmae();
    }

    public void oneyeardataArray(Hoder arrayone){
        this.oneyearArray=arrayone.getResultdataforOneyear();
    }
    public void twoyeardataArray(Hoder arrayone){
        this.towyearArray=arrayone.getResultdataforTwoyear();
    }

    public void todayArraydata(Hoder todayA){
        this.todayArray=todayA.getResultdatafortoday();
    }


    @FXML
    void print_table_data() throws SQLException, InterruptedException, IOException {

        if(oneyearArray!=null){
            System.out.print("777777777777777777777777777777777777777777777777777777777777777777777777777777777777" +oneyearArray.length);
            DMContiguity dmContiguity=new DMContiguity();
          ResultSet resultSet= dmContiguity.getallresultdata(oneyearArray[0],oneyearArray[1],oneyearArray[2]);
            Exportpdf exportpdf=new Exportpdf();
            exportpdf.printtable(resultSet,comnamedata[0],comnamedata[1],comnamedata[3],comnamedata[2]);
        }
        else if(towyearArray!=null){
            DMContiguity dmContiguity=new DMContiguity();
            ResultSet resultSet=dmContiguity.getallresultdatafortowyears(towyearArray[0],towyearArray[1],towyearArray[2],towyearArray[3]);
            Exportpdf exportpdf=new Exportpdf();
            exportpdf.printtable(resultSet,comnamedata[0],comnamedata[1],comnamedata[3],comnamedata[2]);
        }
        else if(todayArray!=null){
System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
            DMContiguity dmContiguity=new DMContiguity();
            ResultSet resultSet= dmContiguity.getallresultdata(todayArray[0],todayArray[1],todayArray[2]);
            Exportpdf exportpdf=new Exportpdf();
            exportpdf.printtable(resultSet,comnamedata[0],comnamedata[1],comnamedata[3],comnamedata[2]);
        }
        else {

        }


    }

@FXML
    public void exportpdf(ActionEvent actionEvent) throws IOException, SQLException {

    if(oneyearArray!=null){
        System.out.print("777777777777777777777777777777777777777777777777777777777777777777777777777777777777" +oneyearArray.length);
        DMContiguity dmContiguity=new DMContiguity();
        ResultSet resultSet= dmContiguity.getallresultdata(oneyearArray[0],oneyearArray[1],oneyearArray[2]);
        Exportpdf exportpdf=new Exportpdf();
        exportpdf.exportpdf(resultSet,comnamedata[0],comnamedata[1],comnamedata[3],comnamedata[2],oneyearArray[3],oneyearArray[4]);
    }
    else if(towyearArray!=null){
        DMContiguity dmContiguity=new DMContiguity();
        ResultSet resultSet=dmContiguity.getallresultdatafortowyears(towyearArray[0],towyearArray[1],towyearArray[2],towyearArray[3]);

        Exportpdf exportpdf=new Exportpdf();
        exportpdf.exportpdf(resultSet,comnamedata[0],comnamedata[1],comnamedata[3],comnamedata[2],towyearArray[4],towyearArray[5]);
    }
    else if(todayArray!=null){
        System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
        DMContiguity dmContiguity=new DMContiguity();
        ResultSet resultSet= dmContiguity.getallresultdata(todayArray[0],todayArray[1],todayArray[2]);
        Exportpdf exportpdf=new Exportpdf();
        exportpdf.exportpdf(resultSet,comnamedata[0],comnamedata[1],comnamedata[3],comnamedata[2],"today","today");
    }
    else {

    }
    }
    public String Realogview= "Realogview";
    public String DMM10= "DMM1.0";
    public String Data_Base= "DataBase";
    public String User_Profile= "User Profile";
    public String Data= "Data";
    public String IPDF="PDF";
    public String Records= "Records";
    public String PDF="PDF";
    public String EXCEL="EXCEL";
@FXML
    public void exportexcel(ActionEvent actionEvent) throws Exception {
        LocalDateTime now1 = LocalDateTime.now();
        DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String formattedDateTime = formatter12.format(now1);

//        String exlefilepath1 = defaultPath+"\\"+Realogview+"\\"+DMM10+"\\"+Records+"\\"+EXCEL+"\\"+formattedDateTime+".xlsx";

    if(oneyearArray!=null){
        String exlefilepath1 = defaultPath+"\\"+Realogview+"\\"+DMM10+"\\"+Records+"\\"+EXCEL+"\\From_"+oneyearArray[3]+"_To_"+oneyearArray[4]+"_"+formattedDateTime+".xlsx";
        String exlefilepath = defaultPath+"/"+Realogview+"/"+DMM10+"/"+Records+"/"+EXCEL+"/From_"+oneyearArray[3]+"_To_"+oneyearArray[4]+"_"+formattedDateTime+".xlsx";
        System.out.print("777777777777777777777777777777777777777777777777777777777777777777777777777777777777" +oneyearArray.length);
        DMContiguity dmContiguity=new DMContiguity();
        ResultSet resultSet= dmContiguity.getallresultdata(oneyearArray[0],oneyearArray[1],oneyearArray[2]);
        convertToExcel(resultSet,exlefilepath);

        if (exlefilepath1!= null) {
            // Open the selected PDF file in a PDF viewer
            try {
                Desktop.getDesktop().open(new File(exlefilepath1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showAlertinfo("","Excel File is saved at "+exlefilepath1);
    }
    else if(towyearArray!=null){
        String exlefilepath1 = defaultPath+"\\"+Realogview+"\\"+DMM10+"\\"+Records+"\\"+EXCEL+"\\From_"+towyearArray[4]+"_To_"+towyearArray[5]+"_"+formattedDateTime+".xlsx";
        String exlefilepath = defaultPath+"/"+Realogview+"/"+DMM10+"/"+Records+"/"+EXCEL+"/From_"+towyearArray[3]+"_To_"+towyearArray[4]+"_"+formattedDateTime+".xlsx";
        DMContiguity dmContiguity=new DMContiguity();
        ResultSet resultSet=dmContiguity.getallresultdatafortowyears(towyearArray[0],towyearArray[1],towyearArray[2],towyearArray[3]);

        convertToExcel(resultSet,exlefilepath);
        if (exlefilepath1!= null) {
            // Open the selected PDF file in a PDF viewer
            try {
                Desktop.getDesktop().open(new File(exlefilepath1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showAlertinfo("","Excel File is saved at "+exlefilepath1);
    }
    else if(todayArray!=null){
        String exlefilepath1 = defaultPath+"\\"+Realogview+"\\"+DMM10+"\\"+Records+"\\"+EXCEL+"\\"+formattedDateTime+".xlsx";
        String exlefilepath = defaultPath+"/"+Realogview+"/"+DMM10+"/"+Records+"/"+EXCEL+"/"+formattedDateTime+".xlsx";
        System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
        DMContiguity dmContiguity=new DMContiguity();
        ResultSet resultSet= dmContiguity.getallresultdata(todayArray[0],todayArray[1],todayArray[2]);
        convertToExcel(resultSet,exlefilepath);

        if (exlefilepath1!= null) {
            // Open the selected PDF file in a PDF viewer
            try {
                Desktop.getDesktop().open(new File(exlefilepath1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showAlertinfo("","Excel File is saved at "+exlefilepath1);
    }
    else {

    }

    }

    private void showAlertinfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setMinSize(300,150);
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
        String[] colname={"0","ID","MAC ID","Serial No","Commodity Name","Moisture %","Sample Temperature (°C) ","Time","Sample Quantity Required (gram)","Other information"};
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.println(columnName);
            Cell cell = headerRow.createCell(i - 1);
            cell.setCellValue(colname[i]);
        }
        int firstColumnWidth = 10; // You can adjust the width as needed
        sheet.setColumnWidth(0, firstColumnWidth * 256);
        sheet.setColumnWidth(1, firstColumnWidth+20 * 256);
        sheet.setColumnWidth(2, firstColumnWidth+20 * 256);
        sheet.setColumnWidth(3, firstColumnWidth+20 * 256);
        sheet.setColumnWidth(4, firstColumnWidth+10 * 256);
        sheet.setColumnWidth(5, firstColumnWidth+30 * 256);
        sheet.setColumnWidth(6, firstColumnWidth+30 * 256);
        sheet.setColumnWidth(7, firstColumnWidth+30 * 256);
        sheet.setColumnWidth(8, firstColumnWidth+40 * 256);

        int rowNum = 1;
        int idvalue=1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 1; i <= columnCount; i++) {

                if(i==9){
                    String r8=resultSet.getString(9);
                    String ab2=r8.substring(0, r8.length() - 1);
                    String modifiedString = ab2.substring(1);
                    String newr8=modifiedString.replace("|",",\n");
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(newr8);
                } else if (i==1) {
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(idvalue);
                } else if(i==7){
                    Cell cell = row.createCell(i - 1);
                    String r6=resultSet.getString(7);
                    LocalDateTime localDateTime = LocalDateTime.parse(r6, formattor24Hours);
                    String nr6=localDateTime.format(globeldtformatter);
                    cell.setCellValue(nr6);
                }
                else{
                    Cell cell = row.createCell(i - 1);
                    cell.setCellValue(resultSet.getString(i));
                }

            }
            idvalue+=1;
        }

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {

            workbook.write(outputStream);
        }

        workbook.close();
    }
}

    class TableModel{
        private SimpleStringProperty Tid;
    private SimpleStringProperty Tsrno;
        private SimpleStringProperty Tmacid;
    private SimpleStringProperty commname;
    private SimpleStringProperty moist;
    private SimpleStringProperty Ttemp;
    private SimpleStringProperty Ttime;
    private SimpleStringProperty sqrequire;
    private SimpleStringProperty waight;
    private SimpleStringProperty othinfom;
    public TableModel(String Tid,String srno, String MACId, String commname, String moist,String Ttemp,String Ttime, String sqreqd, String othinf) {
        this.Tid=new SimpleStringProperty(Tid);
        this.Tsrno = new SimpleStringProperty(srno);
        this.Tmacid=new SimpleStringProperty(MACId);
        this.commname = new SimpleStringProperty(commname);
        this.moist = new SimpleStringProperty(moist);
        this.Ttemp = new SimpleStringProperty(Ttemp);
        this.Ttime = new SimpleStringProperty(Ttime);
        this.sqrequire = new SimpleStringProperty(sqreqd);
        this.othinfom= new SimpleStringProperty(othinf);
    }

    public String getrno() {
        return Tsrno.get();
    }
        public String getr12() {
            return Tmacid.get();
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
    public String getWeight() {
        return waight.get();
    }
    public String getInfo() {
        return othinfom.get();
    }

        public SimpleStringProperty nameProperty0() {
            return Tid;
        }
    public SimpleStringProperty nameProperty() {
        return Tsrno;
    }
        public SimpleStringProperty nameProperty2() {
            return Tmacid;
        }
    public SimpleStringProperty nameProperty3() {
        return commname;
    }
    public SimpleStringProperty nameProperty4() {
        return moist;
    }
    public SimpleStringProperty nameProperty5() {
        return Ttemp;
    }
    public SimpleStringProperty nameProperty6() {
        return Ttime;
    }
    public SimpleStringProperty nameProperty7() {
        return sqrequire;
    }
    public SimpleStringProperty nameProperty9() {
        return othinfom;
    }

}