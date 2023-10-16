package com.atosalves.view.paineis.menu;

import com.atosalves.view.componentes.Botao;
import com.atosalves.view.paineis.PainelPadrao;

public class InicioPainel extends PainelPadrao {

	private Botao solicitarCorrida;
	private Botao compraCredito;
	private Botao usuarios;
	private Botao valorCredito;
	private Botao caixa;

	@Override
	protected void construirComponentes() {
		construtor
			// TODO se for passageiro
			.botao("SOLICITAR CORRIDA", solicitarCorrida)
			.botao("COMPRAR CRÉDITOS", compraCredito)
			// TODO se for adminastro
			.botao("LISTAR TODOS OS USUÁRIOS", usuarios)
			.botao("DEFINIR VALOR DE CRÉTIDO DE REINVIDICAÇÃO", valorCredito)
			.botao("DADOS DO CAIXA", caixa)
			.construir();
	}

	@Override
	protected void instanciarComponentes() {
		this.solicitarCorrida = fabrica.criarBotao();
		this.compraCredito = fabrica.criarBotao();
		this.usuarios = fabrica.criarBotao();
		this.valorCredito = fabrica.criarBotao();
		this.caixa = fabrica.criarBotao();
	}
}