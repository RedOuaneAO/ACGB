package repository;
import domain.entity.*;
import config.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookRepo {
    public void displayBooks(){
        Connection con = ConnetionDb.getConnection();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book");
            while (resultSet.next()) {
                System.out.println("id : " + resultSet.getInt(1));
                System.out.println("title : " + resultSet.getString(2));
                System.out.println("isbn : " + resultSet.getString(3));
                System.out.println("quantity : " + resultSet.getInt(4));
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void addBooks(Books books ,int auteurId){
        Connection con = ConnetionDb.getConnection();
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
    public void updateBooks(Books books , int bookId){
        Connection con = ConnetionDb.getConnection();
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
        Connection con = ConnetionDb.getConnection();
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
        Connection con = ConnetionDb.getConnection();
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
        Connection con = ConnetionDb.getConnection();
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
        Connection con = ConnetionDb.getConnection();
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
            Connection con = ConnetionDb.getConnection();
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
        Connection con = ConnetionDb.getConnection();
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
}

