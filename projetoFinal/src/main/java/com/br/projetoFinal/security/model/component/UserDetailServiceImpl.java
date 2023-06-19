package com.br.projetoFinal.security.model.component;

import java.util.ArrayList;
import java.util.List;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailServiceImpl() {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
       Usuario login = loginRepository.findUserByUsername(userName);
        if (login == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        // Aqui, você pode adicionar as authorities com base nas informações do usuário
        authorities.add(new SimpleGrantedAuthority(login.getTipoUsuario().toString()));

        SecurityUser securityUser = new SecurityUser(login.getUsername(), login.getSenha(), authorities, login);

        // Configure outras propriedades do objeto SecurityUser, se necessário

        return securityUser;
    }
}
