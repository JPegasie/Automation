package com.pegasie.util;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class SQLSrvUtil {
    //private String[] arrCols;

    public static ResultSet rs;

    public static String getConn(String sServer, String sPort, String sDB, String sUser, String sPass) {
        String connectionUrl = "jdbc:sqlserver://"+sServer+":"+sPort+";databaseName="+sDB+";user="+sUser+";password="+sPass;
        return connectionUrl;
    }

    public static String getFirstTenRecords(String sNb, String sColName, String sTbl) {
        System.out.println(sColName);
        //single pipe and double pipe doesn't work
        //String arrCols[] = sColName1.split(",");


        String sSQL ="SELECT " +sColName+"  From " +sTbl;
        System.out.println("The SQL query is:"+sSQL);
        return sSQL;

    }

    public static List<String []> getResultFromExecution(String sConnStrIndex, String sSQLType, String sCols, String sTbl) {
        String Conn="";

        String sSQL="";


        //build up connection string
        if (sConnStrIndex.equalsIgnoreCase("ALMDB")) {
            Conn = SQLSrvUtil.getConn("192.168.10.244","1433","default_sandox1253_dino_db","sa", "Pegasie2008!");
        }

        System.out.println("Connection string is:"+Conn);

        //build up SQLString
        if (sSQLType.equalsIgnoreCase("SELECT")) {
            //sSQL = SQLSrvUtil.getFirstTenRecords("10","RQ_REQ_NAME, RQ_REQ_STATUS", "[td].[REQ]");
            sSQL = SQLSrvUtil.getFirstTenRecords("10",sCols, sTbl);
        }

        List<String []> table;
        for (String[] strings : table = new ArrayList<>()) {
            
        }
        ;
        try (Connection con = DriverManager.getConnection(Conn); Statement stmt = con.createStatement()) {
            //Execute the query
            rs = stmt.executeQuery(sSQL);

            //Retrive total number of columns
            int nCol = rs.getMetaData().getColumnCount();

            String [] arrCol = sCols.split(",");
            System.out.println(arrCol.length);
            for (int iCnt =0; iCnt<arrCol.length; iCnt++) {
                System.out.println(arrCol[iCnt].trim());
            }

            int iRow=0;
            while (rs.next()) {
                //debug
                //System.out.println(rs.getString("RQ_REQ_NAME") + " | " + rs.getString("RQ_REQ_STATUS"));
                String[] row = new String[nCol];



                for( int iCol = 1; iCol <= nCol; iCol++ ){
                    Object obj = rs.getObject( iCol );
                    row [iCol-1] = (obj == null) ?null:obj.toString();
                }
                table.add( row );

            }

            rs.close();


        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();

        }
        return table;

    }


}

