package com.atosalves.controller.chainpattern;

import com.atosalves.controller.exceptions.CredenciaisInvalidasException;
import com.atosalves.dao.UsuarioDAO;
import com.atosalves.dto.usuario.CadastroDTO;
import com.atosalves.dto.usuario.LoginDTO;
import com.atosalves.dto.usuario.UsuarioDTO;
import com.atosalves.model.Usuario;

public class TratadorSenha extends TratadorEtapa{

    @Override
    public void tratarRequisicao(LoginDTO login, UsuarioDTO usuarioDTO) throws CredenciaisInvalidasException {
        Usuario usuario = usuarioDTO.usuario();
        if(!usuario.getSenha().equals(login.senha())) throw new CredenciaisInvalidasException("Senha Incorreta");
        if(getProximoTratador() != null){
            getProximoTratador().tratarRequisicao(login, usuarioDTO);
        }
    }


    @Override
    public void tratarRequisicao(CadastroDTO cadastro) throws CredenciaisInvalidasException {
        throw new UnsupportedOperationException("Metodo não suportado");
    }
    
}
