/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastlogin;

/**
 *
 * @author Андрей
 */
public class LoginSet {
    private boolean active;
    private String name;
    private String key;

    public LoginSet() {
    }

    
    public void setAll(boolean active, String name, String key) {
        this.active = active;
        this.name = name;
        this.key = key;
        
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
       
}
