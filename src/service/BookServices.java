package service;
import domain.entity.*;
import config.*;
import java.sql.ResultSet;
import java.sql.Statement;
import repository.*;
import java.sql.Connection;
public class BookServices {
    public int checkIfExcist(String auteur){
        int message = -1;
        if(auteur.equals("")){
            return  message;
        }else{
        BookRepo bookRepObj= new BookRepo();
        int isExist = bookRepObj.checkAuthorExistence(auteur);
            message = isExist;
        }
        return message;
    }
    public void addBookService(Books books ,int auteurId){
        BookRepo addBook = new BookRepo();
        int bookId =addBook.checkBookEx(books);
        if(bookId!=0){
            addBook.updateBooks(books ,bookId);
        }else {
            addBook.addBooks(books ,auteurId);
        }
    }
    public int addAuteur(String auteur){
        BookRepo addAuthor =new BookRepo();
       return addAuthor.addAuteur(auteur);
    }
    public void addBookService1(Books books){
        BookRepo addBook = new BookRepo();
        addBook.addBooks2(books);
    }
    public void search(String title){
        BookRepo searchBook = new BookRepo();
        searchBook.searchByTitle(title);
    }
}
