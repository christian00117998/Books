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
        
        
        
         
        
//        HttpSession session = request.getSession();
//        String nextPage = "/jsp/error.jsp";
//
//        try {
//            // Get the robot librarian (EntityManager)
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookShopPU");
//            EntityManager em = emf.createEntityManager();
//
//            // Ask for all the books
//            TypedQuery<Tbooks> query = em.createNamedQuery("Tbooks.findAll", Tbooks.class);
//            List<Tbooks> books = query.getResultList();
//
//            // Save books in the session for JSP to use
//            session.setAttribute("books", books);
//            nextPage = "/jsp/titles.jsp";
//
//            em.close();
//            emf.close();
//
//        } catch (Exception ex) {
//            request.setAttribute("result", ex.getMessage());
//        }
//
//        return nextPage;
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
        
//    @PersistenceContext(unitName = "BookShopPU")
//    private EntityManager em;
//    private EntityManagerFactory emf;
//    @Resource
//    private javax.transaction.UserTransaction utx;
//        
//        
//        HttpSession session = request.getSession();
//        AdmitBookStoreDAO dao = new AdmitBookStoreDAO();
//        
//        
////        dao = new AdmitBookStoreDAO();
////            List<Book> books = null;
////            nextPage = "/jsp/error.jsp";
////            session = request.getSession();
////            try {
////                books = dao.getAllBooks();
////                //
////                session.setAttribute("books", books);
////                nextPage = "/jsp/titles.jsp";
////            } catch (Exception ex) {
////                request.setAttribute("result", ex.getMessage());
////                nextPage = "/jsp/error.jsp";
////            } finally {
////                this.dispatch(request, response, nextPage);
////            }
//        String nextPage = "/jsp/error.jsp";
//
//        try {
//            List<Tbooks> books = dao.getAllBooks();
//            session.setAttribute("books", books);
//            nextPage = "/jsp/titles.jsp";
//        } catch (Exception ex) {
//            request.setAttribute("result", ex.getMessage());
//        }
//
//        return nextPage;
//    }
//
//    public void persist(Object object) {
//        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
//         * <persistence-context-ref>
//         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
//         * <persistence-unit-name>BookShopPU</persistence-unit-name>
//         * </persistence-context-ref>
//         * <resource-ref>
//         * <res-ref-name>UserTransaction</res-ref-name>
//         * <res-type>javax.transaction.UserTransaction</res-type>
//         * <res-auth>Container</res-auth>
//         * </resource-ref> */
//        try {
//            Context ctx = new InitialContext();
//            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
//            utx.begin();
//            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
//            em.persist(object);
//            utx.commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void persist1(Object object) {
//        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
//         * <persistence-context-ref>
//         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
//         * <persistence-unit-name>BookShopPU</persistence-unit-name>
//         * </persistence-context-ref>
//         * <resource-ref>
//         * <res-ref-name>UserTransaction</res-ref-name>
//         * <res-type>javax.transaction.UserTransaction</res-type>
//         * <res-auth>Container</res-auth>
//         * </resource-ref> */
//        try {
//            Context ctx = new InitialContext();
//            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
//            utx.begin();
//            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
//            em.persist(object);
//            utx.commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            throw new RuntimeException(e);
//        }
//    }
    



