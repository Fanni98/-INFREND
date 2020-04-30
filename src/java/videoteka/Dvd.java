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
public class Dvd {
   
	private int sorszam;
	private String cim;
	private String beszerzesDatum;
	private String statusz;
        private ArrayList dvdLista ;  
        private ArrayList cimsorszam ;
        private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
        Connection connection; 
        
	public String getCim() {
		return cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	public String getBeszerzesDatum() {
		return beszerzesDatum;
	}

	public void setBeszerzesDatum(String beszerzesDatum) {
		this.beszerzesDatum = beszerzesDatum;
	}

	public int getSorszam() {
		return sorszam;
	}

	public void setSorszam(int sorszam) {
		this.sorszam = sorszam;
	}

	public String getStatusz() {
		return statusz;
	}

	public void setStatusz(String statusz) {
		this.statusz = statusz;
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
        public ArrayList dvdLista(){  
         try{  
             dvdLista = new ArrayList();  
             connection = getConnection();  
             Statement stmt=getConnection().createStatement();    
             ResultSet rs=stmt.executeQuery("select * from dvdkazetta ");    
            while(rs.next()){  
                Dvd dvd = new Dvd();   
                dvd.setSorszam(rs.getInt("sorszam"));  
                dvd.setCim(rs.getString("cim"));  
                dvd.setBeszerzesDatum(rs.getString("beszerzesdatum"));   
                dvd.setStatusz(rs.getString("statusz"));  
                dvdLista.add(dvd);  
            }  
            connection.close();          
            }catch(Exception e){  
                System.out.println(e);  
            }  
                return dvdLista;  
            } 
        public ArrayList cimSorszam(){  
         try{  
             cimsorszam = new ArrayList();  
             connection = getConnection();  
             Statement stmt=getConnection().createStatement();    
             ResultSet rs=stmt.executeQuery("select * from dvdkazetta where statusz='kikölcsönzött'");    
            while(rs.next()){  
                Dvd dvd = new Dvd();   
                dvd.setSorszam(rs.getInt("sorszam"));  
                dvd.setCim(rs.getString("cim"));  
              
                cimsorszam.add(dvd);  
            }  
            connection.close();          
            }catch(Exception e){  
                System.out.println(e);  
            }  
                return cimsorszam;  
            }  
        public void statuszvaltasKolcsonzes(int sorszam){  
        try{  
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("update dvd set statusz = 'kikölcsönzött' where sorszam = ?");
            stmt.setInt(1, sorszam);
            stmt.execute();    
            }catch(Exception e){  
            System.out.println(e);  
            } }
        
        public void statuszvaltasSelejtezes(int sorszam){  
        try{  
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("update dvd set statusz = 'selejtezett' where sorszam = ?");
            stmt.setInt(1, sorszam);
            stmt.execute();    
            }catch(Exception e){  
            System.out.println(e);  
            } 
        
        }
        }
    
    

