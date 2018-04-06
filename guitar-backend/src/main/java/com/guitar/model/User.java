package com.guitar.model;


import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode(exclude = {"id", "grantedAuthorities", "nonExpired", "nonLocked", "credentialsNotExpired", "enabled"})
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "userBuilder")
@Table(name = "users")
@Data
public class User implements UserDetails,CredentialsContainer{

    @Id
    private String id;

    @NonNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Size(min = 2, max = 15)
    private String name;

    @NonNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Size(min = 2, max = 25)
    private String surname;

    @NonNull
    @Column(unique = true)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Size(min = 2, max = 20)
    private String nick;

    @NonNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @NonNull
    @Pattern(regexp = "[0-9]+")
    private String level;

    private int songsLearnedInThisLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private List<Authority> grantedAuthorities;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "learned_songs")
    private List<String> learnedSongs;

    private boolean nonExpired;
    private boolean nonLocked;
    private boolean credentialsNotExpired;
    private boolean enabled;

    public User(String name, String password, List<Authority> authorities) {
        this.name = name;
        this.password = password;
        this.grantedAuthorities = authorities;
        this.nonExpired = true;
        this.nonLocked = true;
        this.credentialsNotExpired = true;
        this.enabled = true;
    }

/*
    public User(String name, String surname, String nick, String email, String password) {
        this.id=UUID.randomUUID().toString();
        this.name = name;
        this.surname=surname;
        this.nick=nick;
        this.email=email;
        this.password = password;
    }
*/
    public static UserBuilder builder() {
        return userBuilder()
                .id(UUID.randomUUID().toString())
                .nonExpired(true)
                .nonLocked(true)
                .credentialsNotExpired(true)
                .enabled(true);
    }


    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    public void setAuthorities(Authority authority) {
        grantedAuthorities.add(authority);
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.nonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.nonExpired;
    }
}
