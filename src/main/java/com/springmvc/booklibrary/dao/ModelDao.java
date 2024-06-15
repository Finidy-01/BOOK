package com.springmvc.booklibrary.dao;

import com.springmvc.booklibrary.config.DatabaseConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ModelDao {

    private JdbcTemplate jdbcTemplate;
    private String table_name;
    private String id_prefix;
    private String sequence_name;

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getId_prefix() {
        return id_prefix;
    }

    public void setId_prefix(String id_prefix) {
        this.id_prefix = id_prefix;
    }

    public String getSequence_name() {
        return sequence_name;
    }

    public void setSequence_name(String sequence_name) {
        this.sequence_name = sequence_name;
    }

    public ModelDao() {
        this.jdbcTemplate = DatabaseConfig.getJdbcTemplate();
    }
    public ModelDao(String table_name, String id_prefix, String sequence_name) {
        this.setTable_name(table_name);
        this.setId_prefix(id_prefix);
        this.setSequence_name(sequence_name);
        this.jdbcTemplate = DatabaseConfig.getJdbcTemplate();
    }

    public List<Object> findAll() {
        String sql = "SELECT * FROM " + this.getTable_name();
        return this.getJdbcTemplate().query(sql, new ObjectRowMapper(this.getClass()));
    }

    public List<Object> find() throws SQLException {
        Connection con = JdbcService.getConnection();
        try {
            if (con == null) {
                return new ArrayList<>();
            }

            Field[] fields = this.getClass().getDeclaredFields();
            List<Field> setted_fields = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            StringBuilder sql = new StringBuilder();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(this) != null) {
                    setted_fields.add(field);
                }
            }

            // INSERT
            sql.append("SELECT * FROM ").append(this.getTable_name()).append(" WHERE ");
            for (int i = 0; i < setted_fields.size(); i++) {
                setted_fields.get(i).setAccessible(true);
                sql.append(setted_fields.get(i).getName()).append(" = ? AND ");
                values.add(setted_fields.get(i).get(this));
            }
            sql.setLength(sql.length() - 4); // manala ny "AND " am farany

            System.out.println(sql.toString());

            return JdbcService.query(con, sql.toString(), values.toArray(), new ObjectRowMapper(this.getClass()));

        } catch (Exception e) {
            throw new SQLException("erreur eo amle find" + this.getClass().getName(), e);
        } finally {
            con.close();
        }
    }

    public Object get() throws SQLException {
        try {

            List<Object> all = this.find();
            if (all.size() > 0) {
                return all.get(0);
            }
            return null;

        } catch (Exception e) {
            throw new SQLException("erreur eo amle get" + this.getClass().getName(), e);
        }
    }

    public int save() throws SQLException {
        Connection con = JdbcService.getConnection();
        try {
            if (con == null) {
                return 0;
            }

            Field[] fields = this.getClass().getDeclaredFields();
            List<Object> values = new ArrayList<>();
            StringBuilder sql = new StringBuilder();

            Field idField = null;
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase("id")) {
                    idField = field;
                    break;
                }
            }
            String id_value = "CONCAT('"+ this.getId_prefix() +"', LPAD(CAST(NEXTVAL('"+ this.getSequence_name() +"') AS TEXT), 3, '0'))";

            if (idField != null) {
                idField.setAccessible(true);
                Object idValue = idField.get(this);

                if (idValue == null) { // INSERT
                    sql.append("INSERT INTO ").append(this.getTable_name()).append(" (");
                    for (Field field : fields) {
                        field.setAccessible(true);
                        sql.append(field.getName()).append(", ");
                        if (!field.equals(idField)) {
                            values.add(field.get(this));
                        }
//                        sql.setLength(sql.length() - 2);
                    }
                    sql.setLength(sql.length() - 2); // manala ny ", " am farany
                    sql.append(") VALUES (");
                    sql.append(id_value);
                    sql.append(", ");
                    sql.append("?, ".repeat(values.size()));
                    sql.setLength(sql.length() - 2);
                    sql.append(")");

                } else { // UPDATE rehefa tsy misy ny ao amn id
                    sql.append("UPDATE ").append(this.getTable_name()).append(" SET ");
                    for (Field field : fields) {
                        if (!field.equals(idField)) {
                            field.setAccessible(true);
                            sql.append(field.getName()).append(" = ?, ");
                            values.add(field.get(this));
                        }
                    }
                    sql.setLength(sql.length() - 2);
                    sql.append(" WHERE ").append(idField.getName()).append(" = ?");
                    values.add(idValue);
                }

            } else {
                throw new SQLException("No field named 'id' found in class " + this.getClass().getName());
            }

            System.out.println(sql.toString());

            return JdbcService.update(con, sql.toString(), values.toArray());
        } catch (Exception e) {
            throw new SQLException("Failed to save instance of " + this.getClass().getName(), e);
        } finally {
            con.close();
        }
    }

    public int delete() throws SQLException {
        Connection con = JdbcService.getConnection();
        try {
            if (con == null) {
                return 0;
            }

            Field[] fields = this.getClass().getDeclaredFields();

            Field idField = null;
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase("id")) {
                    idField = field;
                    break;
                }
            }
            if (idField != null) {
                idField.setAccessible(true);
                Object[] values = new Object[1];
                values[0] = idField.get(this).toString();
                String sql = "DELETE FROM " + this.getTable_name() + " WHERE id = ?";
                return JdbcService.update(con, sql, values);
            }
            return 0;
        } catch (Exception e) {
            throw new SQLException("Tsy vaofafa le " + this.getClass().getName());
        } finally {
            con.close();
        }
    }

}