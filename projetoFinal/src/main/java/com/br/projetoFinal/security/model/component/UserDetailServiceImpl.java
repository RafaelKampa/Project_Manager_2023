package com.br.projetoFinal.security.model.component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.br.projetoFinal.entity.Login;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.LoginRepository;
import com.br.projetoFinal.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        Optional<Usuario> login = loginRepository.findUserByUsername(userName);
        Usuario u = login.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new SecurityUser(u);
//        try {
//
//            UserDetails u = new CustomUser("admin",
//                    "$2a$12$hytSyTnX6Dddyz8i8nY/7.sd7FuVQtZ9euGLjU8Qp1NbJ1llquyeW",
//                    true,
//                    true,
//                    true,
//                    true,
//                    new ArrayList<>(),
//                    null);
//
//            logger.info("Username: " + userName + " encontrado.");
//
//            return u;
//        } catch (Exception ex) {
//            logger.error("Username: " + userName + " não econtrado na base. Acesso negado. ");
//            throw new UsernameNotFoundException(userName);
//        }

    }

//    private CustomUser getCustomUser(String userName) {
//
//        logger.info("getCustomUser: " + userName + ".");
//
//        CustomUser customUser = jdbcTemplate.queryForObject(
//                "select email, senha, guidusuario from usuario where email=?", new Object[] { userName },
//                new UserRowMapper());
//
//        if (customUser != null) {
//
//            customUser = new CustomUser(customUser.getUsername(), customUser.getPassword(), customUser.isEnabled(),
//                    customUser.isAccountNonExpired(), customUser.isCredentialsNonExpired(),
//                    customUser.isAccountNonLocked(), getUserRoles(customUser), customUser.getGuidUsuario());
//        }
//
//        return customUser;
//
//    }
//
//    private class UserRowMapper implements RowMapper<CustomUser> {
//        @Override
//        public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new CustomUser(rs.getString("email"), rs.getString("senha"), true, true, true, true,
//                    Collections.emptyList(), rs.getInt("guidusuario"));
//
//        }
//    }
//
//    private List<GrantedAuthority> getUserRoles(CustomUser user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        return authorities;
//    }

}
