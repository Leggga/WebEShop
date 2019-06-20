package eShop.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

	public static <T> T insertQuery(Connection conn, String sql, ResultSetHandler<T> resultSetHandler, Object... params)
			throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			fillPreparedStatement(ps, params);
			int result = ps.executeUpdate();
			if (result != 1) {
				throw new SQLException("Can't insert row into a database!");
			}
			ResultSet rs = ps.getGeneratedKeys();
			return resultSetHandler.handle(rs);
		}
	}

	public static void insertBatch(Connection conn, String sql, List<Object[]> params) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			for (Object[] param : params) {
				fillPreparedStatement(ps, param);
				ps.addBatch();
			}
			ps.executeBatch();
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
