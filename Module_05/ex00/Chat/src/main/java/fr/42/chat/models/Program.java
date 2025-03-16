package fr._42.chat.models;

import java.sql.*;

public class Program {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        System.out.println("PostgreSQL JDBC Driver Registered!");
        String url = "jdbc:postgresql://localhost:5432/FirstOne";
        String uname = "postgres";
        String pass = "Serghini2@@4";
        Connection con = DriverManager.getConnection(url, uname, pass);
        System.out.println("Connection Established.");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            int id = rs.getInt("sid");
            String sname = rs.getString("sname");
            int marks = rs.getInt("marks");
            System.out.println("ID: " + id + ", Name: " + sname + ", Marks: " + marks);
        }
        
		// while (rs.next()) {
        //     int id = rs.getInt(1);
        //     String sname = rs.getString(2);
        //     int marks = rs.getInt(3);
        //     System.out.println("ID: " + id + ", Name: " + sname + ", Marks: " + marks);
        // }
        rs.close();
        st.close();
        con.close();
    }
}