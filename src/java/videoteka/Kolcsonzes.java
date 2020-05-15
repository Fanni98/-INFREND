import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.Statement;  
import java.sql.ResultSet;  
import java.util.ArrayList;   
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.RequestScoped;  
 
@ManagedBean  
@RequestScoped  
public class Kolcsonzes{
    private int id;
    private int uid;
    private int usorszam;
    private String mikortol;
    ArrayList kolcsonzesLista ;
    Connection connection;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getUsorszam() {
        return usorszam;
    }
    public void setUsorszam(int usorszam) {
        this.usorszam = usorszam;
    }
    public String getMikortol() {
        return mikortol;
    }
    public void setMikortol(String mikortol) {
        this.mikortol = mikortol;
    }  
        
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/videoteka?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
        }catch(Exception e){
            System.out.println(e);
        }
        return connection;
    }
    
    public ArrayList kolcsonzesLista(){
        try{
            kolcsonzesLista = new ArrayList();
            connection = getConnection();
            Statement stmt=getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("select * from kolcsonzes order by uid");
            while(rs.next()){
                Kolcsonzes k = new Kolcsonzes();
                k.setUid(rs.getInt("uid"));
                k.setUsorszam(rs.getInt("usorszam"));
                k.setMikortol(rs.getString("mikortol"));
                kolcsonzesLista.add(k);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return kolcsonzesLista;
    }
    
    public String save(){
        int result = 0;
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "insert into kolcsonzes(uid,usorszam,mikortol) values(?,?,?)");
            stmt.setInt(1, uid);
            stmt.setInt(2, usorszam);
            stmt.setString(3, mikortol);
            result = stmt.executeUpdate();
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        if(result !=0)
            return "dvdfoglalasa.xhtml?faces-redirect=true";
        else return "ujkolcsonzes.xhtml?faces-redirect=true";
    }
    
    
    public void delete(int usorszam){
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete from kolcsonzes where usorszam = ?");
            stmt.setInt(1, usorszam);
            stmt.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }  

} 

