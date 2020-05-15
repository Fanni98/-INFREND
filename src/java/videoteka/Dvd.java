package videoteka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class Dvd {
    
    private int sorszam;
    private String cim;
    private String beszerzesDatum;
    private String statusz;
    private ArrayList dvdLista;
    private ArrayList cimsorszam;
    private ArrayList kolcsonozhetoDvd;
    private ArrayList keresSorszam;
    private ArrayList talalatsorszam;
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
    public ArrayList keresSorszam(){
        try{
            keresSorszam = new ArrayList();
            connection = getConnection();
            Statement stmt=getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM dvdkazetta where sorszam like '"+  sorszam + "' ");
            while(rs.next()){
                Dvd dvd = new Dvd();
                dvd.setSorszam(rs.getInt("sorszam"));
                dvd.setCim(rs.getString("cim"));
                dvd.setBeszerzesDatum(rs.getString("beszerzesdatum"));
                dvd.setStatusz(rs.getString("statusz"));
                keresSorszam.add(dvd);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return keresSorszam;
    }
    public ArrayList<Dvd> getValues(){
        talalatsorszam = keresSorszam();
        return talalatsorszam;
        
    }
    public ArrayList kolcsonozhetoDvd(){
        try{
            kolcsonozhetoDvd = new ArrayList();
            connection = getConnection();
            Statement stmt=getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("select * from dvdkazetta where statusz='szabad'");
            while(rs.next()){
                Dvd dvd = new Dvd();
                dvd.setSorszam(rs.getInt("sorszam"));
                dvd.setCim(rs.getString("cim"));
                dvd.setStatusz(rs.getString("statusz"));
                kolcsonozhetoDvd.add(dvd);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return kolcsonozhetoDvd;
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
                dvd.setStatusz(rs.getString("statusz"));
                cimsorszam.add(dvd);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return cimsorszam;
    }
    
    
    
    public void statuszSelejtezes(int sorszam){
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("update dvdkazetta set statusz = 'selejtezett' where sorszam = ?");
            stmt.setInt(1, sorszam);
            stmt.execute();
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void statuszSzabad(int sorszam){
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("update dvdkazetta set statusz = 'szabad' where sorszam = ?");
            stmt.setInt(1, sorszam);
            stmt.execute();
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void statuszKolcsonzes(int sorszam){
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("update dvdkazetta set statusz='kikölcsönzött' where sorszam = ?");
            stmt.setInt(1, sorszam);
            stmt.execute();
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    
    
}

    

