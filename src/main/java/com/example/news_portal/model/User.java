package com.example.news_portal.model;

import com.example.news_portal.model.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;
    private String nickname;
    private String photo;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = ALL, mappedBy = "publisher")
    private List<News> myPublications = new ArrayList<>();

    @ManyToMany(cascade = {
            PERSIST,
            REFRESH,
            MERGE,
            DETACH})
    private List<News> favorites = new ArrayList<>();

    @OneToMany(cascade = {
            REFRESH,
            MERGE,
            DETACH}, mappedBy = "user")
    private List<Comment> myComments = new ArrayList<>();

    @ManyToMany(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private List<Comment> comments = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
