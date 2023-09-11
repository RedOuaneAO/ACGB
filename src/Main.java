import domain.entity.*;
import config.ConnetionDb;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.*;
import service.BookServices;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Main {
    public static void main(String[] args){
        BookRepo BookObj = new BookRepo();
        System.out.println("===============================================================");
        System.out.println("=====================YouCode Bibliothèque======================");
        System.out.println("===============================================================");
        int continueMain =1;
        while (continueMain==1){
        System.out.println("======= 1.Add Book");
        System.out.println("======= 2.Display Books");
        System.out.println("======= 3.Search");
        System.out.println("======= 4.Delete a Book");
        System.out.println("======= 5.Update a Book");
        System.out.println("======= 6.Borrow a Book");
        System.out.println("======= 7.Return a Book");
        System.out.println("======= 8.Display Borrowed Book");
        System.out.println("======= 9 Statistic");

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
                search() ;
                break;
            case 4:
                deleteBook();
                break;
            case 5:
                updateBook();
                break;
            case 6:
                borrowBook();
                break;
            case 7:
                returnBook();
                break;
            case 8:
                BookObj.displayBorrowedBook();
                break;
            case 9:
                BookObj.statistique();
                break;
            case 10:
                addMembre();
                break;
        }
        }
    }
    static void addBook(){
        System.out.println("Enter the book title : ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine().trim();
        System.out.println("Enter the book ISBN : ");
        String isbn = scanner.nextLine().trim();
        System.out.println("Enter the book Quantity : ");
        int quantity = scanner.nextInt();
        int auteurId=-2 ;
        BookServices bookService =  new BookServices();
        String auteur ="";
        do {
            System.out.println("Enter the book Author : ");
             auteur = scanner.nextLine().trim();
            auteurId = bookService.checkIfExist(auteur);
        }while (
                auteurId == -1
        );
        if(auteurId !=0){
            Books books =new Books(title,isbn,quantity);
            bookService.addBookService(books , auteurId);
            System.out.println("======= The Book Has been Added Successfuly");
        }else {
            int authorId = bookService.addAuteur(auteur);
            Auteur auteur2 =new Auteur(auteur,authorId);
            Books books =new Books(title,isbn,quantity,auteur2);
            bookService.addBookService1(books);
            System.out.println("======= The Book Has been Added Successfuly");
        }
    }
    static void updateBook(){
        System.out.println("======= 1.Enter Book ISBN");
        Scanner scanner = new Scanner(System.in);
        String bookIsbn = scanner.nextLine();
        Books isbnObj = new Books(bookIsbn);
        BookRepo updateObj = new BookRepo();
        int bookId = updateObj.checkBookEx(isbnObj);
        if(bookId!=0){
            System.out.println("======= Enter the New Title :");
            String title = scanner.nextLine().trim();
            System.out.println("======= Enter the New ISBN :");
            String isbn = scanner.nextLine().trim();
            System.out.println("======= Enter the New Quantity :");
            int quantity = scanner.nextInt();
            int auteurId=-2 ;
            BookServices bookService =  new BookServices();
            String auteur ="";
            do {
                System.out.println("Enter the book Author : ");
                auteur = scanner.nextLine().trim();
                auteurId = bookService.checkIfExist(auteur);
            }while (
                    auteurId == -1
            );
            if(auteurId !=0){
                Auteur auteur1 = new Auteur(auteur , auteurId);
                Books books =new Books(title,isbn,quantity,auteur1);
                bookService.updateBookService(books);
            }else {
                int authorId = bookService.addAuteur(auteur);
                Auteur auteur2 =new Auteur(auteur,authorId);
                Books books =new Books(title,isbn,quantity,auteur2);
            }
            System.out.println("======= !!!! The Book has been Updated successfully!!!");
        }else {
            System.out.println("======= !!!! This Book dosn't Exist!!!");
        }
    }
    static void deleteBook(){
        System.out.println("======= Enter Book ISBN");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine();
        Books isbnObj = new Books(isbn);
        BookRepo deleteObj = new BookRepo();
        int bookId = deleteObj.checkBookEx(isbnObj);
        if(bookId!=0){
            System.out.println("======= Enter the Quantity You Want to Delete :");
            int quantity = scanner.nextInt();
            deleteObj.DeleteBook(bookId , quantity);
            System.out.println("======= !!!! The Book(s) has been Deleted Successfully!!!");
        }else {
            System.out.println("======= !!!! This Book dosn't Exist!!!");
        }
    }
    static void search(){
        System.out.println("======= 1.Search By Book Title");
        System.out.println("======= 2.Search By Author");
        Scanner scanner = new Scanner(System.in);
        BookServices bookServices =new BookServices();
        int choice = scanner.nextInt();
        if(choice ==1){
            System.out.println("======= 1.Enter the book title :");
            String title = scanner.nextLine().trim();
            bookServices.search(title);
        }else if(choice ==2){
            System.out.println("======= 2.Enter the Author Name :");
            String auteur = scanner.nextLine().trim();
           int AuteurId = bookServices.checkIfExist(auteur);
           BookRepo getBooks =new BookRepo();
           getBooks.searchByAuthor(AuteurId);
        }
    }
    static int borrowBook(){
        System.out.println("======= Enter Book ISBN :");
        Scanner scanner = new Scanner(System.in);
        String isbn = scanner.nextLine().trim();
        BookServices myObj2 =new BookServices();
        BookRepo myObj3 =new BookRepo();
        Books books =new Books(isbn);
        int boookID =myObj3.checkBookEx(books);
       if (boookID>0){
           System.out.println("======= Enter Membre Number :");
           int num = scanner.nextInt();
           int membreId= myObj2.memberExit(num);
           if(membreId==0){
               System.out.println("===== !This Membre dosen't Exist! :");
               System.out.println("-----------------------------------");
               System.out.println("|        Do You Want To Add It :  |");
               System.out.println("-----------------------------------");
               System.out.println("======= 1. Yes :");
               System.out.println("======= 2. No  :");
               int choiceresult = scanner.nextInt();
               switch(choiceresult){
                   case 1:
                       addMembre();
                       LocalDate currentDate = LocalDate.now();
                       LocalDate dueDate = currentDate.plus(15, ChronoUnit.DAYS);
                       Reservation reservation = new Reservation(boookID, addMembre(), "Borrowed", currentDate, dueDate ,null);
                       BookServices borrowObj =new BookServices();
                       borrowObj.borrowBook(reservation);
                       System.out.println("Successfuly");
                       break;
                   case 2:
                       return 1;
               }
           }else {
               LocalDate currentDate = LocalDate.now();
               LocalDate dueDate = currentDate.plus(15, ChronoUnit.DAYS);
               Reservation reservation = new Reservation(boookID, membreId, "Borrowed", currentDate, dueDate ,null);
               BookServices borrowObj =new BookServices();
               borrowObj.borrowBook(reservation);
               System.out.println("Successfuly");
           }
       }else{
           System.out.println("This Book Doesn't Exist");
       }
       return 1;
    }
static int addMembre(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("===== Enter The Member Frist Name :");
    String firstName =scanner.nextLine().trim();
    System.out.println("===== Enter The Member Second Name :");
    String secondName =scanner.nextLine().trim();
    BookRepo bookRepo =new BookRepo();
    int membreNum =bookRepo.lastMembreNum() +1;
    Membre membre = new Membre(membreNum , firstName , secondName);
    BookServices bookServices =new BookServices();
   boolean result = bookServices.addMembre(membre);
   if(result){
       System.out.println("\n ==The Membre Numbre is : " + membreNum);
   }else {
       System.out.println("please enter a valide data");
   }
   return membreNum;
}
static void returnBook(){
    System.out.println("======= Enter Book ISBN :");
    Scanner scanner = new Scanner(System.in);
    String isbn = scanner.nextLine().trim();
    System.out.println("======= Enter Membre Number :");
    int num = scanner.nextInt();
    BookRepo myObj =new BookRepo();
    Books books =new Books(isbn);
    BookServices myObj2 =new BookServices();
    int boookID =myObj.checkBookEx(books);
    int membreId= myObj2.memberExit(num);
    Reservation reservation =new Reservation(membreId,boookID);
    myObj2.returnBookService(reservation);
    System.out.println("The Book Has Been Returned Successfuly");
}

}
