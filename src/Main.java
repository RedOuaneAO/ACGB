import domain.entity.*;
import config.ConnetionDb;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.*;
import service.BookServices;
public class Main {
    public static void main(String[] args){
        BookRepo BookObj = new BookRepo();
        System.out.println("======= 1.Add Book");
        System.out.println("======= 2.Display Books");
        System.out.println("======= 3.Search");
        System.out.println("======= 4.Delete Book");
        System.out.println("======= 5.Update Book");
        System.out.println("======= 6.Generate");
        Scanner myObj = new Scanner(System.in);
        int a = myObj.nextInt();
        switch (a) {
            case 1:
                addBook();
                break;
            case 2:
                BookObj.displayBooks();
                break;
            case 3:
                //search() ;
                break;
            case 4:
                deleteBook();
                break;
        }
    }
    static void addBook(){
        System.out.println("inter the book title : ");
        Scanner bookTitle = new Scanner(System.in);
        String title = bookTitle.nextLine();
        System.out.println("inter the book ISBN : ");
        Scanner bookISBN = new Scanner(System.in);
        String isbn = bookISBN.nextLine();
        System.out.println("inter the book Quantity : ");
        Scanner bookQuant = new Scanner(System.in);
        int quantity = bookQuant.nextInt();
        int message=-2 ;
        BookServices bookService =  new BookServices();
        String auteur ="";
        do {
            System.out.println("inter the book Auteur : ");
             Scanner bookAuteur = new Scanner(System.in);
             auteur = bookAuteur.nextLine();
             message = bookService.checkIfExcist(auteur);
        }while (
                message == -1
        );
        if(message !=0){
            Books books =new Books(title,isbn,quantity);
            bookService.addBookService(books , message);
        }else {
            int authorId = bookService.addAuteur(auteur);
            Auteur auteur2 =new Auteur(auteur,authorId);
            Books books =new Books(title,isbn,quantity,auteur2);
            bookService.addBookService1(books);
        }
    }
    static void updateBook(){
        System.out.println("======= 1.Inter Book ISBN");
        Scanner myObj = new Scanner(System.in);
        String bookIsbn = myObj.nextLine();
    }
    static void deleteBook(){
        System.out.println("======= Inter Book ISBN");
        Scanner myObj = new Scanner(System.in);
        String isbn = myObj.nextLine();
        Books isbnObj = new Books(isbn);
        BookRepo deleteObj = new BookRepo();
        int bookId = deleteObj.checkBookEx(isbnObj);
        if(bookId!=0){
            System.out.println("======= Inter the Quantity You Want to Delete :");
            Scanner myObj2 = new Scanner(System.in);
            int quantity = myObj2.nextInt();
            deleteObj.DeleteBook(bookId , quantity);
            System.out.println("======= !!!! The Book(s) has been deleted successfully!!!");
        }else {
            System.out.println("======= !!!! This Book dosn't Exist!!!");
        }
    }
    static void search(){
        System.out.println("======= 1.Search By Book Title");
        System.out.println("======= 2.Search By Author");
        Scanner myObj = new Scanner(System.in);
        BookServices myObj2 =new BookServices();
        int choice = myObj.nextInt();
        if(choice ==1){
            System.out.println("======= 1.inter the book title :");
            Scanner bookTitle = new Scanner(System.in);
            String title = bookTitle.nextLine();
            myObj2.search(title);
            //searchObj.BObj(title);
        }else if(choice ==2){
            System.out.println("======= 2.inter the Author Name :");
            Scanner auteur = new Scanner(System.in);

        }

    }
}
