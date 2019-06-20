package eShop.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import eShop.form.ProductForm;
import eShop.model.ShoppingCart;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;
import eShop.utils.SessionUtils;

@WebServlet("/ajax/json/product/remove")
public class RemoveProductAjaxController extends AbstractController {

	private static final long serialVersionUID = 8773338799389468967L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductForm productForm = createProductForm(req);
		ShoppingCart cart = SessionUtils.getCurrentShoppingCart(req);

		getOrderService().removeProductFromCart(productForm, cart);

		if (cart.getItems().isEmpty()) {
			SessionUtils.clearCurrentShoppingCart(req, resp);
		} else {
			SessionUtils.updateCurrentShoppingCart(getOrderService().shoppingCartToString(cart), resp);
		}

		JSONObject json = new JSONObject();
		json.put("totalCount", cart.getTotalCount());
		json.put("totalCost", cart.getTotalCost());

		RoutingUtils.sendJsonObject(json, req, resp);
	}
}
