/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Tbooks;
//import utility.AdmitBookStoreDAO;

/**
 *
 * @author Christian
 */
public class TitleDispatcher implements Dispatcher{
    
    @Resource
    private javax.transaction.UserTransaction utx;
    private EntityManager em;
    private EntityManagerFactory emf;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        List<Tbooks> books;
        
        try{
            emf = Persistence.createEntityManagerFactory("BookShopPU");
            em = emf.createEntityManager();
            
            books = em.createQuery("SELECT t FROM Tbooks t", Tbooks.class).getResultList();
            session.setAttribute("books", books);
            return "/jsp/titles.jsp";
        } catch (Exception e){
            request.setAttribute("result", "Error Loading books:" + e.getMessage());
            return "/jsp/error.jsp";
        }
        
    }
        
         public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
        
}


































