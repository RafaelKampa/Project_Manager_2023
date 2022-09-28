package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import com.br.projetoFinal.service.UsuarioService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario) throws ExcecaoExemplo {
        if (usuario.getLogin().equals(null)) {
            throw new ExcecaoExemplo("ERR0001", "É necessáraio informar um login para o usuário.");
        } else if (usuario.getSenha().equals(null)) {
            throw new ExcecaoExemplo("ERR0002", "É necessáraio informar uma senha para o usuário.");
        } else if (usuario.getNome().equals(null)) {
            throw new ExcecaoExemplo("ERR0003", "É necessáraio informar o nome do usuário.");
        } else if (usuario.getContratante().equals(null)) {
            throw new ExcecaoExemplo("ERR0004", "É necessáraio informar o contratante do usuário.");
        } else if (usuario.getDataAdmissao().equals(null)) {
            throw new ExcecaoExemplo("ERR0005", "É necessáraio informar a data de admissão do usuário");
        } else if (usuario.getCargo().equals(null)) {
            throw new ExcecaoExemplo("ERR0006", "É necessáraio informar o cargo do usuário.");
        } else if (usuario.getRemuneracao() == 0) {
            throw new ExcecaoExemplo("ERR0007", "É necessáraio informar o valor da remuneração do usuário.");
        } else {
            usuarioRepository.salvar(usuario);
        }
    }

    public List<Usuario> listar() {
        return usuarioRepository.listar();
    }

    public UsuarioDto buscarPorId(Integer id) {
        return usuarioRepository.buscarPorId(id);
    }

    public UsuarioDto buscarPorNome(String nome){
        return usuarioRepository.buscarPorNome(nome);
    }

    public void excluir(Integer id){
        usuarioRepository.excluirPorId(id);
    }

}
