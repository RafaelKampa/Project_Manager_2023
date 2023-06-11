package com.br.projetoFinal.security.model.component;

import java.util.Optional;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
        Optional<Usuario> login = loginRepository.findUserByUsername(userName);
        Usuario u = login.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new SecurityUser(u);
    }
}
