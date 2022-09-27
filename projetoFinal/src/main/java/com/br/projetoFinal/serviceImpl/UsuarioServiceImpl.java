package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import com.br.projetoFinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.listar();
    }

    public Usuario buscarPorId(Integer id) {
        return (Usuario) usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarPorNome(String nome){
        return usuarioRepository.buscarPorNome(nome);
    }

    public void excluir(Integer id){
        usuarioRepository.excluirPorId(id);
    }

}
