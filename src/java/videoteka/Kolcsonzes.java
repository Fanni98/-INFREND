package videoteka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext; 

@ManagedBean  
@RequestScoped 
public class Kolcsonzes {
        private int id;
	private int sorszam;
	private String mikortol;
        private ArrayList kolcsonzesLista ;  
        private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
        Connection connection; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSorszam() {
		return sorszam;
	}

	public void setSorszam(int sorszam) {
		this.sorszam = sorszam;
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
            }
            catch(Exception e){  
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
                k.setId(rs.getInt("uid"));
                k.setSorszam(rs.getInt("usorszam"));  
                k.setMikortol(rs.getString("mikortol"));  
                kolcsonzesLista.add(k);  
            }  
            connection.close();          
            }catch(Exception e){  
                System.out.println(e);  
            }  
                return kolcsonzesLista;  
            }  
    
   
    
}
