package ru.kpfu.itis.dmitry_ivanov.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Dmitry on 20.05.2017.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles1 = new ArrayList<>();
        Iterator<Role> iterator= roles.iterator();
        while(iterator.hasNext()){
            roles1.add(iterator.next());
        }
        return roles1;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
