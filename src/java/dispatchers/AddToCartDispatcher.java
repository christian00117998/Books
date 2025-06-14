/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Tbooks;
import model.CartItem;

/**
 *
 * @author Christian
 */
public class AddToCartDispatcher implements Dispatcher{
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession(true);
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        String[] selectedBooks = request.getParameterValues("add");

        // No books selected
        if (selectedBooks == null || selectedBooks.length == 0) {
            return "/jsp/titles.jsp";
        }

        // Create cart if it doesn't exist
        if (cart == null) {
            //cart = new HashMap();
            cart = new HashMap<String, CartItem>();
        }

        for (String isbn : selectedBooks) {
            int quantity = Integer.parseInt(request.getParameter(isbn));

            if (cart.containsKey(isbn)) {
                // Update quantity if already exists
                CartItem item = cart.get(isbn);
                item.setQuantity(quantity);
            } else {
                // Add new item to cart
                Tbooks book = getBookFromList(isbn, session);
                if (book != null) {
                    CartItem item = new CartItem(book);
                    item.setQuantity(quantity);
                    cart.put(isbn, item);
                }
            }
        }

        session.setAttribute("cart", cart);
        return "/jsp/titles.jsp";
        
        
        
    }
    
    private Tbooks getBookFromList(String isbn, HttpSession session) {
        List<Tbooks> list = (List<Tbooks>) session.getAttribute("books");
        if (list == null) return null;
        for (Tbooks book : list) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }
    
}




