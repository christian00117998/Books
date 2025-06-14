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
        Dispatcher dispatcher = actions.getOrDefault(action, actions.get("view_titles"));

        String nextPage = dispatcher.execute(request, response);
        dispatch(request, response, nextPage);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "FrontController handles all requests centrally.";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    private final HashMap actions = new HashMap();
//    
//    
//    
//
//    /**
//     * Initialize global variables.
//     * @param config ServletConfig object
//     * @throws ServletException if an error occurs during initialization
//     */
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        // Additional initialization code can be added here
//        
//        actions.put("add_to_cart", new AddToCartDispatcher());
//        actions.put("checkout", new CheckoutDispatcher());
//        actions.put("continue", new ContinueDispatcher());
//        actions.put("update_cart", new UpdateCartDispatcher());
//        actions.put("view_cart", new ViewCartDispatcher());
//        actions.put("view_titles", new TitleDispatcher()); // This line
//         
//    }
//
//    /**
//     * Process the HTTP GET request.
//     * @param request HttpServletRequest object
//     * @param response HttpServletResponse object
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.err.println("doGet()");
//        // Forward GET requests to doPost method
//        doPost(request, response);
//    }
//
//    /**
//     * Process the HTTP POST request.
//     * @param request HttpServletRequest object
//     * @param response HttpServletResponse object
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        // Get the requested action from the request parameters
//        //
//        String requestedAction = request.getParameter("action");
//        
//        
//        // Get dispatcher based on the action
//        Dispatcher dispatcher = (Dispatcher) actions.get(requestedAction);
//
//        // Fallback to default dispatcher if no action or not recognized
//        if (dispatcher == null) {
//            dispatcher = (Dispatcher) actions.get("view_titles");
//        }
//
//        String nextPage = dispatcher.execute(request, response);
//        dispatch(request, response, nextPage);
//        
//
//    }
//
//    /**
//     * Retrieve a book from the list of books stored in the session.
//     * @param isbn ISBN of the book
//     * @param session HttpSession object
//     * @return Book object
//     */
//    
//
//    /**
//     * Forward the request to the specified page.
//     * @param request HttpServletRequest object
//     * @param response HttpServletResponse object
//     * @param page Page to forward to
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//        dispatcher.forward(request, response);
//    }
//
//    /**
//     * Get Servlet information.
//     * @return Servlet information
//     */
//    public String getServletInfo() {
//        return "controller.FrontController Information";
//    }
//
//    public void persist(Object object) {
//        try {
//            utx.begin();
//            em.persist(object);
//            utx.commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            throw new RuntimeException(e);
//        }
//    }
}



























































//package controller;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.*;
//import java.util.*;
//import model.Book;
//import model.CartItem;
//import utility.AdmitBookStoreDAO;
//import dispatchers.*;
//
///**
// * FrontController class to handle HTTP requests and responses.
// */
//public class FrontController extends HttpServlet {
//
//    private final HashMap actions = new HashMap();
//
//    /**
//     * Initialize global variables.
//     * @param config ServletConfig object
//     * @throws ServletException if an error occurs during initialization
//     */
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        // Additional initialization code can be added here
//        
//        actions.put("add_to_cart", new AddToCartDispatcher());
//        actions.put("checkout", new CheckoutDispatcher());
//        actions.put("continue", new ContinueDispatcher());
//        actions.put("update_cart", new UpdateCartDispatcher());
//        actions.put("view_cart", new ViewCartDispatcher());
//        actions.put("view_titles", new TitleDispatcher()); // This line
//         
//    }
//
//    /**
//     * Process the HTTP GET request.
//     * @param request HttpServletRequest object
//     * @param response HttpServletResponse object
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.err.println("doGet()");
//        // Forward GET requests to doPost method
//        doPost(request, response);
//    }
//
//    /**
//     * Process the HTTP POST request.
//     * @param request HttpServletRequest object
//     * @param response HttpServletResponse object
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//
//        // Get the requested action from the request parameters
//        //
//        String requestedAction = request.getParameter("action");
//        
//        
//        // Get dispatcher based on the action
//        Dispatcher dispatcher = (Dispatcher) actions.get(requestedAction);
//
//        // Fallback to default dispatcher if no action or not recognized
//        if (dispatcher == null) {
//            dispatcher = (Dispatcher) actions.get("view_titles");
//        }
//
//        String nextPage = dispatcher.execute(request, response);
//        dispatch(request, response, nextPage);
//        
////        HttpSession session = request.getSession();
////        AdmitBookStoreDAO dao = new AdmitBookStoreDAO();
////        String nextPage = "";
//
//        // If no action is specified, fetch all books and display them
//        //if (requestedAction == null) {
////            dao = new AdmitBookStoreDAO();
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
//            //
////        } else if (requestedAction.equals("add_to_cart")) {
////            nextPage = "/jsp/titles.jsp";
//
//            // Retrieve the cart from the session
////            Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
////            String[] selectedBooks = request.getParameterValues("add");
////
////            // Check if selectedBooks is null or empty
////            if (selectedBooks == null || selectedBooks.length == 0) {
////                this.dispatch(request, response, nextPage);
////            }
////
////            // If the cart is null, create a new cart and add selected books
////            if (cart == null) {
////                cart = new HashMap();
////
////                for (String isbn : selectedBooks) {
////                    int quantity = Integer.parseInt(request.getParameter(isbn));
////                    Book book = this.getBookFromList(isbn, session);
////                    CartItem item = new CartItem(book);
////                    item.setQuantity(quantity);
////                    cart.put(isbn, item);
////                }
////                session.setAttribute("cart", cart);
////            } else {
////                // If the cart already exists, update the quantities of selected books
////                for (String isbn : selectedBooks) {
////                    int quantity = Integer.parseInt(request.getParameter(isbn));
////                    if (cart.containsKey(isbn)) {
////                        CartItem item = cart.get(isbn);
////                        item.setQuantity(quantity);
////                    } else {
////                        //
////                        Book book = this.getBookFromList(isbn, session);
////                        CartItem item = new CartItem(book);
////                        item.setQuantity(quantity);
////                        cart.put(isbn, item);
////                    }
////                }
////            }
////
////            this.dispatch(request, response, nextPage);
//            //
//            
//            
////        } else if (requestedAction.equals("checkout")) {
////            // Redirect to the checkout page
////            nextPage = "/jsp/checkout.jsp";
////            this.dispatch(request, response, nextPage);
////            //
//            
////        } else if (requestedAction.equals("continue")) {
////            // Redirect to the titles page
////            nextPage = "/jsp/titles.jsp";
////            this.dispatch(request, response, nextPage);
////        }
//
//            
////        else if (requestedAction.equals("update_cart")) {
////            Map<String, CartItem> cart = null;
////            CartItem item = null;
////            String isbn = null;
////            nextPage = "/jsp/cart.jsp";
////            cart = (Map<String, CartItem>) session.getAttribute("cart");
////            String[] booksToRemove = request.getParameterValues("remove");
////            if (booksToRemove != null) {
////                for (String bookToRemove : booksToRemove) {
////                    cart.remove(bookToRemove);
////                }
////            }
////            Set<Map.Entry<String, CartItem>> entries = cart.entrySet();
////            Iterator<Map.Entry<String, CartItem>> iter = entries.iterator();
////            while (iter.hasNext()) {
////                Map.Entry<String, CartItem> entry = iter.next();
////                isbn = entry.getKey();
////                item = entry.getValue();
////                int quantity = Integer.parseInt(request.getParameter(isbn));
////                item.updateQuantity(quantity);
////            }
////            this.dispatch(request, response, nextPage);
////        } 
//                
////                else if (requestedAction.equals("view_cart")) {
////            // Redirect to the cart page
////            nextPage = "/jsp/cart.jsp";
////            Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
////            if (cart == null) {
////                nextPage = "/jsp/titles.jsp";
////            }
////            this.dispatch(request, response, nextPage);
////        }
//    }
//
//    /**
//     * Retrieve a book from the list of books stored in the session.
//     * @param isbn ISBN of the book
//     * @param session HttpSession object
//     * @return Book object
//     */
//    // 
////    private Book getBookFromList(String isbn, HttpSession session) {
////        List<Book> list = (List<Book>) session.getAttribute("books");
////        Book aBook = null;
////        for (Book book : list) {
////            if (isbn.equals(book.getIsbn())) {
////                aBook = book;
////                break;
////            }
////        }
////        return aBook;
////    }
//
//    /**
//     * Forward the request to the specified page.
//     * @param request HttpServletRequest object
//     * @param response HttpServletResponse object
//     * @param page Page to forward to
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//        dispatcher.forward(request, response);
//    }
//
//    /**
//     * Get Servlet information.
//     * @return Servlet information
//     */
//    public String getServletInfo() {
//        return "controller.FrontController Information";
//    }
//}
//
