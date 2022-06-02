package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.exception.EntidadeEmUsoException;
import com.lmg.lmgfood.domain.exception.NegocioException;
import com.lmg.lmgfood.domain.exception.UsuarioNaoEncontradoException;
import com.lmg.lmgfood.domain.model.Usuario;
import com.lmg.lmgfood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroUsuarioService {

    private static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removido, pois está em uso ";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodas() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario adicionar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void remover(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }
}
