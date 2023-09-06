import domain.entity.*;
import config.ConnetionDb;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import repository.*;
import service.BookServices;

public class Main {
    public static void main(String[] args){
        System.out.println("======= 1.Add Book");
        System.out.println("======= 2.Display domain.entity.Books");
        System.out.println("======= 3.Delete Book");
        System.out.println("======= 4.Update Book");
        System.out.println("======= 5.Generate");
        Scanner myObj = new Scanner(System.in);
        int a = myObj.nextInt();
        switch (a) {
            case 1:
                addBook();
                break;
            case 2:
                System.out.println("there");
                break;
        }

        //BookObj.addBooks();
        //BookObj.displayBooks();
        //BookObj.updateBooks();
        //ajoutBooks();
        //displayBooks();

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
        System.out.println("inter the book Auteur : ");
        Scanner bookAuteur = new Scanner(System.in);
        String auteur = bookAuteur.nextLine();
        /*String[] authorParts = auteur.split(" ");
        String firstName = authorParts[0];
        String lastName = authorParts[1];*/
        Books setdata = new Books(title , isbn , quantity);
        Auteur author = new Auteur(auteur);
        setdata.setAuthor(author);
        BookServices bookSObj = new BookServices(author.getName() ,title ,isbn,quantity);
    }
}
