package com.atosalves.view.componentes.abstractfactory.componentesafactory;

import com.atosalves.dto.corrida.CorridaDTO;
import com.atosalves.enums.TipoUsuario;
import com.atosalves.view.componentes.*;
import com.atosalves.view.componentes.abstractfactory.ComponentesFactory;
import java.awt.Font;

public class ComponentesFactoryImpl implements ComponentesFactory {

	@Override
	public SenhaCaixa criarCaixaSenha() {
		return new SenhaCaixa();
	}

	@Override
	public TextoCaixa criarCaixaTexto() {
		return new TextoCaixa();
	}

	@Override
	public Texto criarTexto(String texto, Font fonte) {
		return new Texto(texto, fonte);
	}

	@Override
	public DataCaixa criarDataCaixa() {
		return new DataCaixa();
	}

	@Override
	public TipoUsuarioCombo criarComboTipoUsuario() {
		return new TipoUsuarioCombo(TipoUsuario.values());
	}

	@Override
	public Botao criarBotao(String titulo) {
		return new Botao(titulo);
	}

	@Override
	public ListaDeCorridas criarListaDeItems(CorridaDTO[] lista) {
		return new ListaDeCorridas(lista);
	}

	@Override
	public EmailCaixa criarEmailCaixa() {
		return new EmailCaixa();
	}

	@Override
	public NumeroCaixa criarNumeroCaixa() {
		return new NumeroCaixa();
	}
}
