package eShop.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eShop.Constants;
import eShop.entity.Product;
import eShop.form.SearchForm;
import eShop.servlet.AbstractController;
import eShop.utils.RoutingUtils;

@WebServlet("/search")
public class SearchController extends AbstractController{

	private static final long serialVersionUID = -569664003260569583L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SearchForm searchForm = createSearchForm(req);
		
		List<Product>products = getProductService().productsBySearch(searchForm, 1, Constants.MAX_PRODUCTS_PER_PAGE);
		int totalCount = getProductService().countProductsBySearchForm(searchForm);

		req.setAttribute("products", products);
		req.setAttribute("pages", getPageCount(totalCount, Constants.MAX_PRODUCTS_PER_PAGE));
		req.setAttribute("countProductFound", totalCount);
		req.setAttribute("searchForm", searchForm);
		RoutingUtils.forwardToPage("search-result.jsp", req, resp);
	}
	
}
