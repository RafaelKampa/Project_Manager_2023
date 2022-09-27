package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;

import java.util.List;
import java.util.Map;

public class UsuarioRepositoryImpl extends RepositorioBaseImpl implements UsuarioRepository {

    @Override
    public Usuario buscarPorNome(String nome) {
//        Usuario query = getEntityManager().createNamedQuery()
        return null;
    }

    @Override
    public void salvar(Usuario usuario) {

    }

    @Override
    public List<Usuario> listar() {
        return null;
    }

    @Override
    public Map<Object, Object> buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void excluirPorId(Integer id) {

    }


}
