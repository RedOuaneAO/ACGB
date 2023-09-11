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
        }
        }
    }
    static void addBook(){
        System.out.println("Enter the book title : ");
        Scanner bookTitle = new Scanner(System.in);
        String title = bookTitle.nextLine().trim();
        System.out.println("Enter the book ISBN : ");
        Scanner bookISBN = new Scanner(System.in);
        String isbn = bookISBN.nextLine().trim();
        System.out.println("Enter the book Quantity : ");
        Scanner bookQuant = new Scanner(System.in);
        int quantity = bookQuant.nextInt();
        int auteurId=-2 ;
        BookServices bookService =  new BookServices();
        String auteur ="";
        do {
            System.out.println("Enter the book Author : ");
             Scanner bookAuteur = new Scanner(System.in);
             auteur = bookAuteur.nextLine().trim();
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
        Scanner myObj = new Scanner(System.in);
        String bookIsbn = myObj.nextLine();
        Books isbnObj = new Books(bookIsbn);
        BookRepo updateObj = new BookRepo();
        int bookId = updateObj.checkBookEx(isbnObj);
        if(bookId!=0){
            System.out.println("======= Enter the New Title :");
            Scanner bookTitle = new Scanner(System.in);
            String title = bookTitle.nextLine().trim();
            System.out.println("======= Enter the New ISBN :");
            Scanner bookISBN = new Scanner(System.in);
            String isbn = bookISBN.nextLine().trim();
            System.out.println("======= Enter the New Quantity :");
            Scanner bookQuant = new Scanner(System.in);
            int quantity = bookQuant.nextInt();
            int auteurId=-2 ;
            BookServices bookService =  new BookServices();
            String auteur ="";
            do {
                System.out.println("Enter the book Author : ");
                Scanner bookAuteur = new Scanner(System.in);
                auteur = bookAuteur.nextLine().trim();
                auteurId = bookService.checkIfExist(auteur);
            }while (
                    auteurId == -1
            );
            if(auteurId !=0){
                Auteur auteur1 = new Auteur(auteur , auteurId);
                Books books =new Books(title,isbn,quantity,auteur1);
                bookService.updateBookService(books);
                System.out.println("test1");
            }else {
                System.out.println("test2");
                int authorId = bookService.addAuteur(auteur);
                Auteur auteur2 =new Auteur(auteur,authorId);
                Books books =new Books(title,isbn,quantity,auteur2);
              //  bookService.updateBookService1(books);
            }
            System.out.println("======= !!!! The Book has been Updated successfully!!!");
        }else {
            System.out.println("======= !!!! This Book dosn't Exist!!!");
        }
    }
    static void deleteBook(){
        System.out.println("======= Enter Book ISBN");
        Scanner myObj = new Scanner(System.in);
        String isbn = myObj.nextLine();
        Books isbnObj = new Books(isbn);
        BookRepo deleteObj = new BookRepo();
        int bookId = deleteObj.checkBookEx(isbnObj);
        if(bookId!=0){
            System.out.println("======= Enter the Quantity You Want to Delete :");
            Scanner myObj2 = new Scanner(System.in);
            int quantity = myObj2.nextInt();
            deleteObj.DeleteBook(bookId , quantity);
            System.out.println("======= !!!! The Book(s) has been Deleted Successfully!!!");
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
            System.out.println("======= 1.Enter the book title :");
            Scanner bookTitle = new Scanner(System.in);
            String title = bookTitle.nextLine().trim();
            myObj2.search(title);
        }else if(choice ==2){
            System.out.println("======= 2.Enter the Author Name :");
            Scanner auteurName = new Scanner(System.in);
            String auteur = auteurName.nextLine().trim();
            BookServices bookService= new BookServices();
           int AuteurId = bookService.checkIfExist(auteur);
           BookRepo getBooks =new BookRepo();
           getBooks.searchByAuthor(AuteurId);
        }
    }
    static int borrowBook(){
        System.out.println("======= Enter Book ISBN :");
        Scanner bookIsbn = new Scanner(System.in);
        String isbn = bookIsbn.nextLine().trim();
        BookServices myObj2 =new BookServices();
        BookRepo myObj3 =new BookRepo();
        Books books =new Books(isbn);
        int boookID =myObj3.checkBookEx(books);
       if (boookID>0){
           System.out.println("======= Enter Membre Number :");
           Scanner memberNum = new Scanner(System.in);
           int num = memberNum.nextInt();
           int membreId= myObj2.memberExit(num);
           if(membreId==0){
               System.out.println("===== !This Membre dosen't Exist! :");
               System.out.println("-----------------------------------");
               System.out.println("|        Do You Want To Add It :  |");
               System.out.println("-----------------------------------");
               System.out.println("======= 1. Yes :");
               System.out.println("======= 2. No  :");
               Scanner choice = new Scanner(System.in);
               int choiceresult = choice.nextInt();
               switch(choiceresult){
                   case 1:
                       addMembre();
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
static void addMembre(){
    System.out.println("===== Enter The Member Frist Name :");
    Scanner firstNInput = new Scanner(System.in);
    String firstName =firstNInput.nextLine().trim();
    System.out.println("===== Enter The Member Second Name :");
    Scanner secondNInput = new Scanner(System.in);
    String secondName =secondNInput.nextLine().trim();

    //Membre membre =new Membre()
}
static void returnBook(){
    System.out.println("======= Enter Book ISBN :");
    Scanner bookIsbn = new Scanner(System.in);
    String isbn = bookIsbn.nextLine().trim();
    System.out.println("======= Enter Membre Number :");
    Scanner memberNum = new Scanner(System.in);
    int num = memberNum.nextInt();
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
