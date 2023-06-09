package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import com.br.projetoFinal.service.UsuarioService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarUsuario(UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException {
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (usuarioDto.getUsername().equals(null)) {
                throw new ExcecaoExemplo("ERR0001", "É necessáraio informar um login para o usuário.");
            } else if (usuarioDto.getSenha().equals(null)) {
                throw new ExcecaoExemplo("ERR0002", "É necessáraio informar uma senha para o usuário.");
            } else if (usuarioDto.getNome().equals(null)) {
                throw new ExcecaoExemplo("ERR0003", "É necessáraio informar o nome do usuário.");
            } else if (usuarioDto.getContratante().equals(null)) {
                throw new ExcecaoExemplo("ERR0004", "É necessáraio informar o contratante do usuário.");
            } else if (usuarioDto.getDataAdmissao().equals(null)) {
                throw new ExcecaoExemplo("ERR0005", "É necessáraio informar a data de admissão do usuário");
            } else if (usuarioDto.getCargo().equals(null)) {
                throw new ExcecaoExemplo("ERR0006", "É necessáraio informar o cargo do usuário.");
            } else if (usuarioDto.getRemuneracao() == 0) {
                throw new ExcecaoExemplo("ERR0007", "É necessáraio informar o valor da remuneração do usuário.");
            } else {
                usuarioRepository.salvarUsuario((UsuarioDto) usuario);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<UsuarioDto> listar() {
        return usuarioRepository.listar();
    }

    @Override
    public List<UsuarioDto> buscarConferentes() {
        return usuarioRepository.buscarConferentes();
    }

    @Override
    public List<UsuarioDto> buscarExecutores() {
        return usuarioRepository.buscarExecutores();
    }

    @Override
    public UsuarioDto buscarPorId(Integer idUsuario) {
        return usuarioRepository.buscarPorId(idUsuario);
    }

    @Override
    public UsuarioDto buscarPorNome(String nomeUsuario) {
        return usuarioRepository.buscarPorNome(nomeUsuario);
    }

    @Override
    public void excluir(Integer idUsuario) throws SystemException {
        try {
            utx.begin();
            usuarioRepository.excluirPorId(idUsuario);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Integer buscarUltimoId() {
        return usuarioRepository.buscarUltimoId();
    }

    @Override
    public UsuarioDto buscarPorUsername(String username) {
        return usuarioRepository.buscarPorUsername(username);
    }
}
