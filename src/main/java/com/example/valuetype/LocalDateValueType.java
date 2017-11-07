package com.example.valuetype;

import com.miragesql.miragesql.type.AbstractValueType;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LocalDateValueType extends AbstractValueType<LocalDate> {

    public LocalDateValueType() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate get(Class<? extends LocalDate> type, ResultSet rs, int columnIndex) throws SQLException {
        if(rs.getObject(columnIndex) == null){
            return null;
        }
        return rs.getDate(columnIndex).toLocalDate();
    }

    @Override
    public LocalDate get(Class<? extends LocalDate> type, ResultSet rs, String columnName) throws SQLException {
        if(rs.getObject(columnName) == null){
            return null;
        }
        return rs.getDate(columnName).toLocalDate();
    }

    @Override
    public LocalDate get(Class<? extends LocalDate> type, CallableStatement cs, int index) throws SQLException {
        Date value = cs.getDate(index);

        if (value != null && cs.wasNull()) {
            value = null;
        }

        return value.toLocalDate();
    }

    @Override
    public LocalDate get(Class<? extends LocalDate> type, CallableStatement cs, String parameterName) throws SQLException {
        Date value =  cs.getDate(parameterName);

        if (value != null && cs.wasNull()) {
            value = null;
        }

        return value.toLocalDate();
    }

    @Override
    public void set(Class<? extends LocalDate> type, PreparedStatement stmt, LocalDate value, int index) throws SQLException {
        if (value == null){
            setNull(type, stmt, index);
        } else {
            stmt.setDate(index, Date.valueOf(value));
        }
    }
}
