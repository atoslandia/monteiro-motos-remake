package com.atosalves.dao;

import com.atosalves.model.Usuario;
import java.util.HashSet;
import java.util.Set;

// TODO r: classe para conectar com o banco de dados
public class UsuarioDAO implements DAO<Usuario, String> {

	private Set<Usuario> usuarios = new HashSet<>();

	@Override
	public Set<Usuario> recuperarTodos() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'recuperarTodos'");
	}

	@Override
	public boolean cadastrar(Usuario entidade) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
	}

	@Override
	public Usuario recuperarPeloId(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'recuperarPeloId'");
	}

	@Override
	public Usuario update(Usuario entidade) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}
}
