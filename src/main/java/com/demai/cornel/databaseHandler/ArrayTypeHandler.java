package com.demai.cornel.databaseHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author tfzhu
 */
public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {
    private static final String VARCHAR_TYPE_NAME = "varchar";
    private static final String INTEGER_TYPE_NAME = "integer";
    private static final String BOOLEAN_TYPE_NAME = "boolean";
    private static final String NUMERIC_TYPE_NAME = "numeric";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType) throws SQLException {
        String typeName = null;
        if (parameter instanceof Integer[]) {
            typeName = INTEGER_TYPE_NAME;
        } else if (parameter instanceof String[]) {
            typeName = VARCHAR_TYPE_NAME;
        } else if (parameter instanceof Boolean[]) {
            typeName = BOOLEAN_TYPE_NAME;
        } else if (parameter instanceof Double[]) {
            typeName = NUMERIC_TYPE_NAME;
        }

        if (typeName == null) {
            throw new TypeException("ArrayTypeHandler parameter typeName error, your type is " + parameter.getClass().getName());
        }

        // 这3行是关键的代码，创建Array，然后ps.setArray(i, array)就可以了
        Connection conn = ps.getConnection();
        Array array = conn.createArrayOf(typeName, parameter);
        ps.setArray(i, array);
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getArray(rs.getArray(columnName));
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getArray(rs.getArray(columnIndex));
    }

    @Override
    public Object[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getArray(cs.getArray(columnIndex));
    }

    private Object[] getArray(Array array) {
        if (array == null) {
            return null;
        }

        try {
            return (Object[]) array.getArray();
        } catch (Exception e) {
        }
        return null;
    }
}
