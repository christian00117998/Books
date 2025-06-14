/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.*;
import javax.servlet.http.*;
import model.CartItem;

/**
 *
 * @author Christian
 */
public class UpdateCartDispatcher implements Dispatcher {
    
     @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        String[] booksToRemove = request.getParameterValues("remove");
        if (booksToRemove != null) {
            for (String isbn : booksToRemove) {
                cart.remove(isbn);
            }
        }

        for (Map.Entry<String, CartItem> entry : cart.entrySet()) {
            String isbn = entry.getKey();
            CartItem item = entry.getValue();
            int quantity = Integer.parseInt(request.getParameter(isbn));
            item.updateQuantity(quantity);
        }

        return "/jsp/cart.jsp";
    }
    
}
