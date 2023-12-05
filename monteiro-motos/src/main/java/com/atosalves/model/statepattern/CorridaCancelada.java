package com.atosalves.model.statepattern;

import com.atosalves.dto.CorridaDTO;
import com.atosalves.dto.EnderecoDTO;
import com.atosalves.dto.UsuarioDTO;
import com.atosalves.enums.EstadoCorrida;
import com.atosalves.enums.TipoUsuario;
import com.atosalves.model.Corrida;
import com.atosalves.model.exceptions.AcessoNegadoException;

public class CorridaCancelada extends CorridaState {

	public CorridaCancelada(Corrida corrida) {
		super(corrida);
		nome = EstadoCorrida.CANCELADA;
	}

	@Override
	public void finalizarCorrida() throws AcessoNegadoException {
		throw new AcessoNegadoException("Não foi possivel finalizar");
	}

	@Override
	public void cancelarCorrida(TipoUsuario tipoUsuario) throws AcessoNegadoException {
		throw new AcessoNegadoException("Não foi possivel cancelar");
	}

	@Override
	public void reivindicarCorrida(UsuarioDTO mototaxista) throws AcessoNegadoException {
		throw new AcessoNegadoException("Não foi possivel reivindicar");
	}

	@Override
	public CorridaDTO solicitarCorrida(UsuarioDTO passageiro, EnderecoDTO pontoDeEncontro, EnderecoDTO destino) {
		corrida = new Corrida(passageiro, pontoDeEncontro, destino);
		return new CorridaDTO(corrida);
	}
}
