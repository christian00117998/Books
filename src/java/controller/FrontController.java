package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import model.Tbooks;
import model.CartItem;
//import utility.AdmitBookStoreDAO;
import dispatchers.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * FrontController class to handle HTTP requests and responses.
 */
public class FrontController extends HttpServlet {
    
    
     private final HashMap<String, Dispatcher> actions = new HashMap();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Map actions to dispatchers
        actions.put("add_to_cart", new AddToCartDispatcher());
        actions.put("checkout", new CheckoutDispatcher());
        actions.put("continue", new ContinueDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
        actions.put("view_titles", new TitleDispatcher());  // default action
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);  // Forward GET to POST
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String action = request.getParameter("action");
        Dispatcher dispatcher = actions.getOrDefault(action, actions.get("view_titles"));// calling the titlepage from the action and titledispatcher executes...

        String nextPage = dispatcher.execute(request, response);
        dispatch(request, response, nextPage);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(page); //request dispatcher
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FrontController handles all requests centrally.";
    }
    
}
    
    
    
    
    
    
    
    
    
    
    