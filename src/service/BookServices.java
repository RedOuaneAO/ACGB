package service;
import domain.entity.*;
import config.*;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.*;
import java.sql.Connection;
public class BookServices {
    private BookRepo bookRepObj;
    public BookServices() {
        bookRepObj = new BookRepo();
    }
    public int checkIfExist(String auteur){
        int message = -1;
        if(auteur.equals("")){
            return  message;
        }else{
        int isExist = bookRepObj.checkAuthorExistence(auteur);
            message = isExist;
        }
        return message;
    }
    public void addBookService(Books books ,int authorId){
        int bookId =bookRepObj.checkBookEx(books);
        if(bookId!=0){
            bookRepObj.updateBooks(books ,bookId);
        }else {
            bookRepObj.addBooks(books ,authorId);
        }
    }
    public void updateBookService(Books books){
        int bookId =bookRepObj.checkBookEx(books);
        bookRepObj.updateBook(books ,bookId );
    }
    public int addAuteur(String auteur){
       return bookRepObj.addAuteur(auteur);
    }
    public void addBookService1(Books books){
        bookRepObj.addBooks2(books);
    }
    public void search(String title){
        bookRepObj.searchByTitle(title);
    }
    public int memberExit(int memberNum){
        return bookRepObj.checkMembreEx(memberNum);
    }
    public void borrowBook(Reservation reservation){
        bookRepObj.borrowBook(reservation);
    }
    public void returnBookService(Reservation reservation){
        bookRepObj.returnBook(reservation);
    }
    public boolean addMembre(Membre membre){
    if(membre.getFirstName().equals("") || membre.getSecondName().equals("")){
        return false;
    }else {
        bookRepObj.addMembre(membre);
     return true;
    }
    }
}
