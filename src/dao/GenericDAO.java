package dao;

import java.util.List;
import mapper.RowMapper;

/**
 * @author quan.lh173316
 *
 */
public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

	void update(String sql, Object... parameters);

	Long insert(String sql, Object... parameters);
}
