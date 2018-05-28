package com.ay.testlab.atom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
/*
server = new Server();
        server.setAddress("localhost");
        server.setDatabaseName(0, "mydb1");
        server.setDatabasePath(0, "file:E:/hsqldb_databases/db");
        server.setPort(1234);
        server.setTrace(true);
        server.setLogWriter(new PrintWriter(System.out));
        server.start();
        try {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
        e.printStackTrace(System.out);
        }
        try {
        con = DriverManager.getConnection(
        "jdbc:hsqldb:hsql://localhost:1234/mydb1", "SA", "");
        con.createStatement()
        .executeUpdate(
        "create table contacts (name varchar(45),email varchar(45),phone varchar(45))");
        ServletContext context = getServletContext();
        context.setAttribute("con", con);
        } catch (SQLException e) {
        e.printStackTrace(System.out);
        }
*/