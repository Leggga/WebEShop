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

@WebServlet("/ajax/json/product/add")
public class AddProductAjaxController extends AbstractController {

	private static final long serialVersionUID = -6550354701771044883L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductForm productForm = createProductForm(req);
		ShoppingCart cart = SessionUtils.getCurrentShoppingCart(req);

		getOrderService().addProductToCart(productForm, cart);

		JSONObject json = new JSONObject();
		json.put("totalCount", cart.getTotalCount());
		json.put("totalCost", cart.getTotalCost());

		RoutingUtils.sendJsonObject(json, req, resp);
	}
}
