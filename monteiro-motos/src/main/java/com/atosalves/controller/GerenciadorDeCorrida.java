package com.atosalves.controller;

import com.atosalves.controller.exceptions.NenhumaCorridaEncontradaException;
import com.atosalves.controller.observerpattern.Observador;
import com.atosalves.dao.CorridaDAO;
import com.atosalves.dao.UsuarioDAO;
import com.atosalves.dto.corrida.CorridaDTO;
import com.atosalves.dto.corrida.CorridaEventoDTO;
import com.atosalves.dto.corrida.EnderecoDTO;
import com.atosalves.dto.corrida.EnderecoViewDTO;
import com.atosalves.dto.usuario.LoginDTO;
import com.atosalves.dto.usuario.UsuarioDTO;
import com.atosalves.enums.EstadoCorrida;
import com.atosalves.model.Avaliacao;
import com.atosalves.model.Corrida;
import com.atosalves.model.Endereco;
import com.atosalves.model.Mototaxista;
import com.atosalves.model.exceptions.AcessoNegadoException;
import com.atosalves.model.exceptions.SaldoInsuficienteExceptions;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.util.List;

public class GerenciadorDeCorrida implements Observador {

	@XStreamAsAttribute
	private CorridaDAO corridaDAO = new CorridaDAO();

	@XStreamAsAttribute
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	public void update(CorridaEventoDTO corrida) {
		CorridaDTO corridaDTO = new CorridaDTO(corrida.corrida());
		corridaDAO.update(corridaDTO, corrida.corrida().getId());
		corridaDAO.moverCorrida(corrida);
	}

	public Long solicitarCorrida(LoginDTO loginDTO, EnderecoViewDTO pontoDeEncontoDTO, EnderecoViewDTO destinoDTO)
		throws AcessoNegadoException, SaldoInsuficienteExceptions {
		UsuarioDTO passageiro = usuarioDAO.recuperarPeloId(loginDTO.email());

		List<CorridaDTO> todasAsCorridas = corridaDAO.buscarCorridasDoUsuario(loginDTO.email());

		CorridaDTO corridaDTO = null;
		Corrida corrida = null;
		Endereco pontoDeEncontro = new Endereco(pontoDeEncontoDTO.bairro(), pontoDeEncontoDTO.rua(), pontoDeEncontoDTO.cep());
		Endereco destino = new Endereco(destinoDTO.bairro(), destinoDTO.rua(), destinoDTO.cep());

		if (!todasAsCorridas.isEmpty()) {
			corridaDTO = todasAsCorridas.get(0);
			corrida =
				corridaDTO.corrida().solicitarCorrida(passageiro, new EnderecoDTO(pontoDeEncontro), new EnderecoDTO(destino)).corrida();
		} else {
			corrida = new Corrida(passageiro, new EnderecoDTO(pontoDeEncontro), new EnderecoDTO(destino));
		}

		corridaDTO = new CorridaDTO(corrida);
		corrida.adicionarObservador(this);
		corridaDAO.cadastrar(corridaDTO);
		return corrida.getId();
	}

	public void reivindicarCorrida(LoginDTO login, Long idCorrida) throws AcessoNegadoException {
		CorridaDTO corridaDTO = corridaDAO.buscarCorridaReivindicadaMototaxista(login.email());
		Corrida corrida = null;
		if (corridaDTO != null) {
			corrida = corridaDTO.corrida();
		} else {
			corridaDTO = corridaDAO.recuperarPeloId(idCorrida);
			corrida = corridaDTO.corrida();
		}
		UsuarioDTO mototaxista = usuarioDAO.recuperarPeloId(login.email());
		corrida.reivindicarCorrida(mototaxista);
	}

	public CorridaDTO buscarCorridaPeloId(Long id) {
		Corrida corrida = corridaDAO.recuperarPeloId(id).corrida();
		return new CorridaDTO(corrida);
	}

	public void cancelarCorrida(LoginDTO login, Long idCorrida) throws AcessoNegadoException {
		Corrida corrida = corridaDAO.recuperarPeloId(idCorrida).corrida();
		corrida.cancelarCorrida(login.tipoUsuario());
	}

	public void finalizarCorrida(Long idCorrida) throws AcessoNegadoException {
		Corrida corrida = corridaDAO.recuperarPeloId(idCorrida).corrida();
		corrida.finalizarCorrida();
	}

	public void avaliarMototaxista(long idCorrida, int estrelas) throws AcessoNegadoException {
		Corrida corrida = corridaDAO.recuperarPeloId(idCorrida).corrida();
		Avaliacao avaliacao = new Avaliacao(new UsuarioDTO(corrida.getPassageiro()), estrelas);
		corrida.avaliarMototaxista(avaliacao);
	}

	public CorridaDTO[] buscarHistoricoDeCorridas(LoginDTO login) {
		List<CorridaDTO> corridas = corridaDAO.buscarCorridasDoUsuario(login.email());
		CorridaDTO[] corridasView = transformarEmArray(corridas);
		return corridasView;
	}

	public CorridaDTO[] buscarCorridasPendentes() {
		List<CorridaDTO> corridas = corridaDAO.buscarCorridaPeloEstado(EstadoCorrida.PENDENTE);
		CorridaDTO[] corridasView = transformarEmArray(corridas);
		return corridasView;
	}

	private CorridaDTO[] transformarEmArray(List<CorridaDTO> corridaArray) {
		CorridaDTO[] objects = new CorridaDTO[corridaArray.size()];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = corridaArray.get(i);
		}
		return objects;
	}

	public CorridaDTO[] buscarCorridaReinvidicada(LoginDTO login) throws NenhumaCorridaEncontradaException {
		CorridaDTO corrida = corridaDAO.buscarCorridaReivindicadaMototaxista(login.email());
		if (corrida == null) {
			throw new NenhumaCorridaEncontradaException("Nenhuma corrida foi encontrada!");
		}
		CorridaDTO[] corridas = { corrida };
		return corridas;
	}
}
