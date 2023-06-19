package com.br.projetoFinal.security.model.component;

import com.br.projetoFinal.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final Usuario login;
    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String senha;

    public SecurityUser(Usuario login) {
        this.login = login;
    }

    public SecurityUser(String username, String senha, List<GrantedAuthority> authorities, Usuario login) {
        this.username = username;
        this.senha = senha;
        this.authorities = authorities;
        this.login = login;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return login.getSenha();
    }

    @Override
    public String getUsername() {
        return login.getUsername();
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
