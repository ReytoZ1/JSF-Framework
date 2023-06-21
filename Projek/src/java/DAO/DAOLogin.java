/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.DataAdmin;
import pojo.ProjekUtil;

/**
 *
 * @author denan
 */
public class DAOLogin {
    public List<DataAdmin>getBy(String uName, String uPass) {
        DataAdmin da = new DataAdmin();
        List<DataAdmin> data = new ArrayList();
        
        Transaction trans = null;
        Session session = ProjekUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            Query query = session.createQuery("from DataAdmin where username=" + " :uName AND password= :uPass");
            query.setString("uName", uName);
            query.setString("uPass", uPass);
            da = (DataAdmin) query.uniqueResult();
            data = query.list();
            
            trans.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
      return data;  
    }
    
}
