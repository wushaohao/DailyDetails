package template;

import java.sql.ResultSet;

/**
 * 通过传入的ResultSet对象将每一条记录通过泛型映射成对应的对象 实用的接口是匿名内部类
 *
 * @param <T>
 */
public interface RowMapper<T> {
    public T mappingRows(ResultSet rs, int rowNum) throws Exception;
}
