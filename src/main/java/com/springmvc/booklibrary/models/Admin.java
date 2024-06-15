package com.springmvc.booklibrary.models;

import com.springmvc.booklibrary.dao.ModelDao;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class Admin extends ModelDao {
    private String id;
    private String email;
    private String password;

    public Admin() { super("admin", "ADM", "admin_seq"); }

    public Admin(String email, String password) {
        super("admin", "ADM", "admin_seq");
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String authenticate() throws SQLException {
        Admin admin = (Admin) this.get();
        if (admin != null) {
            return admin.getId();
        }
        return null;
    }
}
