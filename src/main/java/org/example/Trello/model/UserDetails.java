package org.example.Trello.model;

import java.util.Set;

public class UserDetails {
    private String username;
    private String email;
    private String password;
    private int tipo;

    public Set<Tablero> getTableros() {
        return tableros;
    }

    public void setTableros(Set<Tablero> tableros) {
        this.tableros = tableros;
    }

    private Set<Tablero> tableros;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
