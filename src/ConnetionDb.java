import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnetionDb {
    public static void main(String[] args){
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/acgb" , "root" , "");
            Statement statement = con.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from  book ");
            while(resultSet.next()){
                System.out.println("id : " + resultSet.getInt(1));
                System.out.println("title : " + resultSet.getString(2));
                System.out.println("isbn : " + resultSet.getString(3));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
