/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ACER
 */
public class MySQLConnection {
    String Username = "root";
    String Password = "bachb2014640";
    String Database = "qlcuahangiaydb";
    
    Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public MySQLConnection(String Host, String Username, String Password, String Database) {
        this.Username = Username;
        this.Password = Password;
        this.Database = Database;
    }
    
    protected Connection getConnect() throws Exception {
        if (this.connect == null) {
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connect = DriverManager.getConnection("jdbc:mysql://localhost:/qlhanghoasieuthi", "root", "bachb2014640");
            }        
            catch (SQLException e) {
                throw new Exception("không thể kết nối tới Database");
            }
        }
        return this.connect;
    }
    
//    tạo statement để thực thi Queury
    protected Statement getStatement() throws Exception {
        if (this.statement == null) {
            this.statement = getConnect().createStatement();
        }
        else {
            this.statement.isClosed();
        }
        return this.statement;
    }
    
    //hàm thực thi các câu lệnh SQl 
    public ResultSet excuteQuery(String Query) throws Exception{
        try {
            this.resultSet = getStatement().executeQuery(Query);
        } catch (Exception e) {
            throw new Exception("Error excuteQuery " + e.getMessage());
        }
        
        return this.resultSet;
    }
    
//    thực thi các Insert, Update, Delete
    public int executeUpdate(String Query) throws Exception {
        //khai báo biến int để lưu trữ kết quả torng quá trình thực thi
        int res = Integer.MIN_VALUE;
        
        try {
            //thực thi câu lệnh
            res = getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception("Error " + e.getMessage());
        }
        
        return res;
    }
    
    //hàm đóng kết nối
    public void Close() throws Exception {
        if (this.resultSet != null && this.resultSet.isClosed()) {
            this.resultSet.close();
            this.resultSet = null;
        }
        if (this.statement != null && this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        if (this.connect != null && this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }
}
