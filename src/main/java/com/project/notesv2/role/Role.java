package com.project.notesv2.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.notesv2.user.User;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Entity
@Table(name = "user_roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    String name;
    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<User> users;
    @Override
    public String getAuthority() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

@Repository
interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);

    boolean existsByName(String name);
}