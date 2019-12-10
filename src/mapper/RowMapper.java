/**
 * 
 */
package mapper;

import java.sql.ResultSet;

/**
 * @author quan.lh173316
 *
 */
public interface RowMapper<T> {
	T mapRow(ResultSet rs);
}
