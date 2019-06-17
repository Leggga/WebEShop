package eShop.utils;

public class UrlUtils {

	private UrlUtils() {
	}
	
	public static boolean isAjaxUrl(String url) {
		return url.startsWith("/WebShop/ajax/");
	}
	
	public static boolean isAjaxJsonUrl(String url) {
		return url.startsWith("/WebShop/ajax/json/");
	}
	
	public static boolean isAjaxHtmlUrl(String url) {
		return url.startsWith("/WebShop/ajax/html/");
	}
	
	public static boolean isStaticUrl(String url) {
		return url.startsWith("/WebShop/static/");
	}
	
	public static boolean isMediaUrl(String url) {
		return url.startsWith("/WebShop/media/");
	}
}
