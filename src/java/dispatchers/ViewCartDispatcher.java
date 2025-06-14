/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Map;
import javax.servlet.http.*;
import model.CartItem;

/**
 *
 * @author Christian
 */
public class ViewCartDispatcher implements Dispatcher {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "/jsp/titles.jsp";
        }

        return "/jsp/cart.jsp";
    }
    
}
