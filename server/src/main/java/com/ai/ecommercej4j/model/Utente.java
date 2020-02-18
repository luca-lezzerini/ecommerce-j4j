/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ai.ecommercej4j.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author utente
 */



@Entity
@Table
public class Utente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String token;
    @Column
    private String email;
    @Column
    private String doubleOptin;
    @Column
    private LocalDateTime dOptinTimestamp;

    public Utente() {
    }

    public Utente(Long id, String username, String password, String token, String email, String doubleOptin, LocalDateTime dOptinTimestamp) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        this.email = email;
        this.doubleOptin = doubleOptin;
        this.dOptinTimestamp = dOptinTimestamp;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoubleOptin() {
        return doubleOptin;
    }

    public void setDoubleOptin(String doubleOptin) {
        this.doubleOptin = doubleOptin;
    }

    public LocalDateTime getdOptinTimestamp() {
        return dOptinTimestamp;
    }

    public void setdOptinTimestamp(LocalDateTime dOptinTimestamp) {
        this.dOptinTimestamp = dOptinTimestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.token);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.doubleOptin);
        hash = 59 * hash + Objects.hashCode(this.dOptinTimestamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utente other = (Utente) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.doubleOptin, other.doubleOptin)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dOptinTimestamp, other.dOptinTimestamp)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
