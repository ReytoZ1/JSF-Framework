/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.DataTransaksi;
import pojo.ProjekUtil;

/**
 *
 * @author denan
 */
public class DAOTransaksi {
    
    ArrayList usersList ;
     private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
    Connection connection; 
    
   
    //METHOD FOR INSERT
    public void addProduk(DataTransaksi produk)
    {
        Transaction trans=null;
        Session session=ProjekUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.save(produk);
            trans.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    
    
 
    public List<DataTransaksi> getbyID(String idP) {
        DataTransaksi tra = new DataTransaksi();
        List<DataTransaksi> lTra = new ArrayList();
        
        Transaction trans = null;
        Session session = ProjekUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from DataTransaksi where id_Transaksi= :id");
            query.setString("id", idP);
            tra = (DataTransaksi) query.uniqueResult();
            lTra = query.list();
            trans.commit();
        } catch (Exception e) {
        }
        return lTra;
    }
    
    
    //BERHASIL 
     public List<DataTransaksi> retrieveProduk()
    {
       
        List prod=new ArrayList();
        Transaction trans=null;
        Session session=ProjekUtil.getSessionFactory().openSession();
        try
        {
            trans=session.beginTransaction();
            Query query=session.createQuery("from DataTransaksi");
            prod=query.list();
         
            trans.commit();
            
        }
        catch(Exception e)
        {

        }
        return prod;
    }
    

     public void updateProduk(DataTransaksi produk)
    {
        Transaction trans=null;
        Session session=ProjekUtil.getSessionFactory().openSession();
        try 
        {
            trans=session.beginTransaction();
            session.update(produk);
            trans.commit();
        }
        catch(Exception e)
        {
            
        }   
    } 
        public void deleteTransaksi (int idTransaksi) {
        Transaction trans = null;
        Session session = ProjekUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            DataTransaksi tra = (DataTransaksi) session.load(DataTransaksi.class, new Integer (idTransaksi));
            session.delete(tra);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
   
}
