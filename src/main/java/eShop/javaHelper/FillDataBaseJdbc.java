package eShop.javaHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class FillDataBaseJdbc {
	public static void main(String[] args) throws ClassNotFoundException {
		String[] brands = { "Huawei", "Lenovo", "Meizu", "Xiaomi", "Sharp", "Canon", "Samsung", "Asus", "Acer",
				"Dell" };
		String[] categories = { "E-book", "Mp3-player", "Notebook", "Phone", "Smartphone", "Smartwatch", "Tablet" };
		String[] images = {"736d61727470686f6e65.jpg","652d626f6f6b.jpg", "6d70332d706c61796572.jpg", "6e6f7465626f6f6b.jpg",
							"70686f6e65.jpg", "736d6172747761746368.jpg", "7461626c6574.jpg"};
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/n92vl1LaLo", "n92vl1LaLo",
				"P8IjujPLOY")) {
//			fillBrands(conn, brands);
//			fillCategories(conn, categories);
//			fillProducts(conn, brands, categories, images);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("All done");
	}

	public static void fillBrands(Connection conn, String[] brands) throws SQLException {
		PreparedStatement stat = conn.prepareStatement("INSERT INTO producer VALUES (?, ?, ?);");

		for (int i = 0; i < brands.length; i++) {
			stat.setInt(1, i + 1);
			stat.setString(2, brands[i]);
			stat.setInt(3, 3);
			stat.execute();
		}
	}

	public static void fillCategories(Connection conn, String[] categories) throws SQLException {
		PreparedStatement stat = conn.prepareStatement("INSERT INTO category VALUES (?,?,?,?);");
		for (int i = 0; i < categories.length; i++) {
			stat.setInt(1, i + 1);
			stat.setString(2, categories[i]);
			stat.setString(3, "products/" + categories[i].replace("-", "").toLowerCase());
			stat.setInt(4, 3);
			stat.execute();
		}
	}

	public static void fillProducts(Connection conn, String[] brands, String[] categories, String[] images ) throws SQLException {
		PreparedStatement stat = conn.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?);");
		int idPos = 1;
		Random rnd = new Random();
		
		for (int i = 0; i < brands.length; i++) {
			for (int j = 0; j < 3; j++) {
				stat.setInt(1, idPos++);
				stat.setString(2, brands[i] + " " + (char) (rnd.nextInt(26) + 'A') + (char) (rnd.nextInt(26) + 'A') + rnd.nextInt(2555555));
				stat.setString(3, "Monitor diagonal 4.4 / " + 
						"Camera: 3.2Mp / RAM: 1 Gb / Black / 1700 mA/h / Weight 800 g / " + 
						"Bluetooth / 3G / FM receiver");
				stat.setString(4, "media/" + images[rnd.nextInt(images.length)]);
				stat.setFloat(5, rnd.nextInt(3800));
				stat.setInt(6, rnd.nextInt(categories.length) + 1);
				stat.setInt(7, i + 1);
				stat.execute();
			}
		}
	}

}
