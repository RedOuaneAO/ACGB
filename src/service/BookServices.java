package service;
import domain.entity.*;
import config.*;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.*;
import java.sql.Connection;
public class BookServices {
    public BookServices(String auteurName ,String title , String isbn,int quantity) {
        BookRepo checkIfExists= new BookRepo();
        boolean isExist = checkIfExists.checkAuthorExistence(auteurName);
        if(!isExist){
            System.out.println("not");

        }else {
        System.out.println("ex");
        }
    }
}
