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
public class Ugyfel {

        private int id;
	private String nev;
	private String lakcim;
	private int telefonszam;
	private String szigszam;
	private String statusz;
        private ArrayList ugyfelLista ;  
        private ArrayList idNev ;
        private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
        Connection connection; 

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLakcim() {
		return lakcim;
	}

	public void setLakcim(String lakcim) {
		this.lakcim = lakcim;
	}

	public int getTelefonszam() {
		return telefonszam;
	}

	public void setTelefonszam(int telefonszam) {
		this.telefonszam = telefonszam;
	}

	public String getSzigszam() {
		return szigszam;
	}

	public void setSzigszam(String szigszam) {
		this.szigszam = szigszam;
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
        
    public ArrayList ugyfelLista(){  
    try{  
    ugyfelLista = new ArrayList();  
    connection = getConnection();  
    Statement stmt=getConnection().createStatement();    
    ResultSet rs=stmt.executeQuery("select * from ugyfel ");    
    while(rs.next()){  
    Ugyfel ugyfel = new Ugyfel();
    ugyfel.setId(rs.getInt("id"));
    ugyfel.setNev(rs.getString("nev"));  
    ugyfel.setLakcim(rs.getString("lakcim"));  
    ugyfel.setTelefonszam(rs.getInt("telefonszam"));  
    ugyfel.setSzigszam(rs.getString("szigszam"));  
    ugyfel.setStatusz(rs.getString("statusz"));  
    ugyfelLista.add(ugyfel);  
    }  
    connection.close();          
    }catch(Exception e){  
    System.out.println(e);  
    }  
    return ugyfelLista;  
    }  
    public ArrayList idNev(){  
    try{  
    idNev = new ArrayList();  
    connection = getConnection();  
    Statement stmt=getConnection().createStatement();    
    ResultSet rs=stmt.executeQuery("select * from ugyfel where statusz='aktív' ");    
    while(rs.next()){  
    Ugyfel ugyfel = new Ugyfel();
    ugyfel.setId(rs.getInt("id"));
    ugyfel.setNev(rs.getString("nev"));    
    idNev.add(ugyfel);  
    }  
    connection.close();          
    }catch(Exception e){  
    System.out.println(e);  
    }  
    return idNev;  
    }
    public String save(){  
    int result = 0;  
    try{  
    connection = getConnection();  
    PreparedStatement stmt = connection.prepareStatement(  
    "insert into ugyfel(nev,lakcim,telefonszam,szigszam,statusz) values(?,?,?,?,?)");  
    stmt.setString(1, nev);  
    stmt.setString(2, lakcim);  
    stmt.setInt(3, telefonszam);  
    stmt.setString(4, szigszam);  
    stmt.setString(5, statusz);  
    result = stmt.executeUpdate();  
    connection.close();  
    }catch(Exception e){  
    System.out.println(e);  
    }  
    if(result !=0)  
    return "index.xhtml?faces-redirect=true";  
    else return "create.xhtml?faces-redirect=true";  
}  
    public String edit(int id){  
    Ugyfel ugyfel = null;  
    System.out.println(id);  
    try{  
    connection = getConnection();  
    Statement stmt=getConnection().createStatement();    
    ResultSet rs=stmt.executeQuery("select * from ugyfel where id = "+(id));  
    rs.next();     
    ugyfel.setNev(rs.getString("nev"));  
    ugyfel.setLakcim(rs.getString("lakcim"));  
    ugyfel.setTelefonszam(rs.getInt("telefonszam"));  
    ugyfel.setSzigszam(rs.getString("szigszam"));  
    ugyfel.setStatusz(rs.getString("statusz"));  
    sessionMap.put("editUgyfel", ugyfel);  
    connection.close();  
    }catch(Exception e){  
    System.out.println(e);  
    }         
    return "/edit.xhtml?faces-redirect=true";  
}  
// Used to update user record  
    public String update(Ugyfel u){  
//int result = 0;  
    try{  
    connection = getConnection();    
    PreparedStatement stmt=connection.prepareStatement(  
    "update ugyfel set name=?,lakcim=?,telefonszam=?,szigszam=?,statusz=? where id=?");    
    stmt.setString(1,u.getNev());    
    stmt.setString(2,u.getLakcim());    
    stmt.setInt(3,u.getTelefonszam());    
    stmt.setString(4,u.getSzigszam());    
    stmt.setString(5,u.getStatusz());    
    stmt.setInt(6,u.getId());    
    stmt.executeUpdate();  
    connection.close();  
    }catch(Exception e){  
    System.out.println();  
    }  
return "/index.xhtml?faces-redirect=true";        
}  
// Used to delete user record  
public void delete(String nev){  
try{  
connection = getConnection();    
PreparedStatement stmt = connection.prepareStatement("update ugyfel set statusz = 'törölt' where nev = ?");
stmt.setString(1, nev);
stmt.execute();    
}catch(Exception e){  
System.out.println(e);  
} } 
public void aktival(String nev){  
try{  
connection = getConnection();    
PreparedStatement stmt = connection.prepareStatement("update ugyfel set statusz = 'aktív' where nev = ?");
stmt.setString(1, nev);
stmt.execute();    
}catch(Exception e){  
System.out.println(e);  
}  
}
        
        

}
