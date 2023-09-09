package com.example.dmmguivisible;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteTableChecker {
    private static boolean isDBtFile(File file) {
        String name = file.getName();
        System.out.println("name "+name);
        return name.equals("realogview.db");
    }
    public static String Realogview= "Realogview";
    public static String DMM10= "DMM1.0";
    public static String Data_Base= "DataBase";
    static String  defaultPath = System.getProperty("user.home");

    static String  DBpath = defaultPath+"/"+Realogview+"/"+DMM10+"/"+Data_Base+"/Realogview.db";
    static String  DBfolder = defaultPath+"/"+Realogview+"/"+DMM10+"/"+Data_Base;
    public static boolean hasTables() {
        String url = "jdbc:sqlite:"+DBpath; // Replace with the path to your SQLite database file

        try (Connection conn = DriverManager.getConnection(url)) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, null, new String[]{"TABLE"});

            return tables.next(); // Returns true if there's at least one table, false otherwise

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean hasDatabase(){
        boolean des1 = false;
        File folder = new File(DBfolder);
        File[] files = folder.listFiles();
        for(File file:files){
            des1=isDBtFile(file);
            if(des1){
                break;
            }
            else{
                System.out.println("not database");
            }
        }
        return des1;
    }

    // Your other JavaFX methods and code go here...
}
