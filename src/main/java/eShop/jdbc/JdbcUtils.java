package eShop.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {

	private JdbcUtils() {
	}

	public static <T> T selectQuery(Connection conn, String sql, ResultSetHandler<T> resultSetHandler, Object... params)
			throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			fillPreparedStatement(ps, params);
			ResultSet rs = ps.executeQuery();

			return resultSetHandler.handle(rs);
		}
	}

	private static void fillPreparedStatement(PreparedStatement ps, Object... params) throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
	}

}
