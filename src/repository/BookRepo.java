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
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void addBooks(){
        Connection con = ConnetionDb.getConnection();
        try{
            Statement statement= con.createStatement();
            statement.executeUpdate("INSERT INTO `book`( `title`, `isbn`, `quantity`, `auteurId`) VALUES ('test','AF54865','3','1')");

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void updateBooks(){
        Connection con = ConnetionDb.getConnection();
        try{
            Statement statement= con.createStatement();
            statement.executeUpdate("UPDATE `book` SET `title`='power',`isbn`='AT986',`quantity`=7 WHERE id = 1");

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean checkAuthorExistence(String auteurName) {
        Connection con = ConnetionDb.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT COUNT(*) FROM auteur WHERE name = ?");
            preparedStatement.setString(1, auteurName);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                if(count > 0){
                return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}

