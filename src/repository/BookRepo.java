package repository;
import domain.entity.*;
import config.*;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class BookRepo {
    private Connection con;
    public BookRepo() {
        con = ConnetionDb.getConnection();
    }
    public void displayBooks(){
        try{
            PreparedStatement preparedStatement = con.prepareStatement("select * from book");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.println("id : " + result.getInt(1));
                System.out.println("title : " + result.getString(2));
                System.out.println("isbn : " + result.getString(3));
                System.out.println("quantity : " + result.getInt(4));
            }
            //con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void displayBorrowedBook(){
        try{
           /* PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM book join reservation on book.id =reservation.bookId WHERE reservation.status='Borrowed'");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.println("id : " + result.getInt(1));
                System.out.println("title : " + result.getString(2));
                System.out.println("isbn : " + result.getString(3));
                System.out.println("quantity : " + result.getInt(4));
            }*/
            PreparedStatement preparedStatement = con.prepareStatement("SELECT reservation.*  ,book.title , membre.memberNum FROM reservation join book on book.id =reservation.bookId JOIN membre on reservation.memberId=membre.id  WHERE reservation.status='Borrowed';");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.println("id : " + result.getInt(1));
                System.out.println("title : " + result.getString(8));
                System.out.println("status : " + result.getString(4));
                System.out.println("member Numbre : " + result.getInt(9));
                System.out.println("borrow date : " + result.getDate(5));
                System.out.println("borrow date : " + result.getDate(6));
            }
            //con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void addBooks(Books books ,int auteurId){
        try{
            PreparedStatement preparedStatement= con.prepareStatement("INSERT INTO `book`( `title`, `isbn`, `quantity`, `auteurId`) VALUES (?,?,?,?)");
            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setString(2, books.getIsbn());
            preparedStatement.setInt(3, books.getQuantite());
            preparedStatement.setInt(4, auteurId);
            preparedStatement.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //add an existing book
    public void updateBooks(Books books , int bookId){
        try{
            PreparedStatement preparedStatement= con.prepareStatement("UPDATE `book` SET `quantity`= quantity+? WHERE id = ?");
            preparedStatement.setInt(1, books.getQuantite());
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int checkAuthorExistence(String auteur) {
        int authorId = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM auteur WHERE name = ?");
            preparedStatement.setString(1, auteur);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                authorId = result.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return authorId;
    }
    public int addAuteur(String auteurName) {
        int generatedAuteurId =0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `auteur`(`name`)  VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, auteurName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedAuteurId = generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return generatedAuteurId;
    }
    public int checkBookEx(Books books){
        int bookId = 0;
        try{
            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM book WHERE isbn like ?");
            preparedStatement.setString(1, books.getIsbn());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                bookId = result.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return bookId;
    }
    public void addBooks2(Books books){
        try{
            PreparedStatement preparedStatement= con.prepareStatement("INSERT INTO `book`( `title`, `isbn`, `quantity`, `auteurId`) VALUES (?,?,?,?)");
            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setString(2, books.getIsbn());
            preparedStatement.setInt(3, books.getQuantite());
            preparedStatement.setInt(4, books.getAuteur().getId());
            preparedStatement.execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void DeleteBook(int bookId , int quantity){
            try{
                PreparedStatement preparedStatement= con.prepareStatement("UPDATE `book` SET `quantity`= quantity-? WHERE id = ?");
                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, bookId);
                preparedStatement.execute();
            }catch (Exception e){
                System.out.println(e);
        }
    }
    public void searchByTitle(String title){
        try{
            PreparedStatement preparedStatement= con.prepareStatement("select * from book WHERE title like ?");
            preparedStatement.setString(1, title);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id : " + resultSet.getInt(1));
                System.out.println("title : " + resultSet.getString(2));
                System.out.println("isbn : " + resultSet.getString(3));
                System.out.println("quantity : " + resultSet.getInt(4));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    // Search By Author
    public void searchByAuthor(int authorId){
        try{
            PreparedStatement preparedStatement =con.prepareStatement("SELECT * FROM `book` JOIN auteur on book.auteurId = auteur.id WHERE auteur.id =?");
            preparedStatement.setInt(1,authorId);
            ResultSet result =preparedStatement.executeQuery();
            int num =1;
            while (result.next()) {
                System.out.println("-----Book Numbre: " + num++);
                System.out.println("id : " + result.getInt(1));
                System.out.println("title : " + result.getString(2));
                System.out.println("isbn : " + result.getString(3));
                System.out.println("quantity : " + result.getInt(4));
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
   /*public boolean checkBookEx2(String isbn){
       try{
           PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM book WHERE isbn like ?");
           preparedStatement.setString(1, isbn);
           ResultSet result = preparedStatement.executeQuery();
           if (result.next()) {
               return true;
           }
       }catch (Exception e){
           System.out.println(e);
       }
       return false;
    }*/
    public int checkMembreEx(int membreNum){
        int membreId =0;
        try{
            PreparedStatement preparedStatement = con.prepareStatement("SELECT id FROM membre WHERE memberNum = ?");
            preparedStatement.setInt(1, membreNum);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                membreId = result.getInt("id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return membreId;
    }
    public void updateBook(Books books ,int bookId){
        try{
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM `book` WHERE id =?");
            preparedStatement.setInt(1, bookId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                //old data
               String oldTitle = result.getString("title");
               String oldISBN = result.getString("isbn");
               int oldQuantity =result.getInt("quantity");
               int oldAuthorId =result.getInt("auteurId");
                //new data
                String newTitle = books.getTitle();
                String newISBN = books.getIsbn();
                int newQuantity = books.getQuantite();
                int newAuthorId = books.getAuteur().getId();
               if(newTitle.isEmpty())  newTitle= oldTitle;
                if(newISBN.isEmpty())  newISBN= oldISBN;
                if(newQuantity <=0)  newQuantity= oldQuantity;
                if(newAuthorId <=0) {
                    newAuthorId= oldAuthorId;
                }else {
                    newAuthorId=books.getAuteur().getId();
                }
                try{
                    PreparedStatement updateStatement = con.prepareStatement("UPDATE `book` SET `title`=?,`isbn`=?,`quantity`=?,`auteurId`=? WHERE id =?");
                    updateStatement.setString(1 , newTitle);
                    updateStatement.setString(2,newISBN);
                    updateStatement.setInt(3,newQuantity);
                    updateStatement.setInt(4,newAuthorId);
                    updateStatement.setInt(5,bookId);
                    updateStatement.executeUpdate();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void borrowBook(Reservation reservation){
        try{
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `reservation`(`memberId`, `bookId`, `status`, `borrowDate`, `dueDate`, `returnDate`) VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1,reservation.membreId());
            preparedStatement.setInt(2,reservation.getBoookID());
            preparedStatement.setString(3,reservation.getStatus());
            // Set the borrowDate as a java.sql.Date object
            java.sql.Date borrowDateSql = java.sql.Date.valueOf(reservation.getBorrowDate());
            preparedStatement.setObject(4, borrowDateSql);

            // Set the dueDate as a java.sql.Date object
            java.sql.Date dueDateSql = java.sql.Date.valueOf(reservation.getDuedate());
            preparedStatement.setObject(5, dueDateSql);
            // If returnDate is null, set it as null, otherwise set it as a java.sql.Date object
            LocalDate returnDate = reservation.getReturnedDate();
            if (returnDate != null) {
                java.sql.Date returnDateSql = java.sql.Date.valueOf(returnDate);
                preparedStatement.setObject(6, returnDateSql);
            } else {
                preparedStatement.setNull(6, java.sql.Types.DATE);
            }

            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void returnBook(Reservation reservation){
        try{
            PreparedStatement preparedStatement =con.prepareStatement("UPDATE `reservation` SET `status`='Returned',`returnDate`=now() WHERE memberId = ? and bookId  =?");
            preparedStatement.setInt(1,reservation.getMembreId());
            preparedStatement.setInt(2,reservation.getBoookID());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void statistique(){
        try{
            PreparedStatement preparedStatement = con.prepareStatement("SELECT SUM(book.quantity) as availableBooks ,(SELECT COUNT(reservation.id) FROM reservation WHERE reservation.status ='borrowed') as borrowedBook , (SELECT COUNT(reservation.id) FROM reservation WHERE reservation.status ='Lost') as lostBook FROM book LEFT JOIN reservation on book.id = reservation.bookId");
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                System.out.println("Available Book :\n" + result.getInt(1));
                System.out.println("Borrowed Book :\n" + result.getInt(2));
                System.out.println("Lost Book :\n" + result.getInt(3));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
 public int lastMembreNum(){
        try {
            PreparedStatement preparedStatement =con.prepareStatement("SELECT memberNum FROM membre ORDER BY memberNum DESC LIMIT 1");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                return  result.getInt("memberNum");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0 ;
 }
 public void addMembre(Membre membre){
        try{
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `membre`(`firstName`, `secondName`, `memberNum`) VALUES (?,?,?)");
            preparedStatement.setString(1,membre.getFirstName());
            preparedStatement.setString(2,membre.getSecondName());
            preparedStatement.setInt(3,membre.getMembreNumber());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member added successfully!");
            } else {
                System.out.println("Member not added. No rows affected.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
 }
}

