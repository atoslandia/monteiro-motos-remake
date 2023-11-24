package com.atosalves.dao.interfaceDAO;


public interface DAO<T, K> {


	public boolean cadastrar(T entidade);

	public T recuperarPeloId(K id);

	public void deletePeloId(K id);
}