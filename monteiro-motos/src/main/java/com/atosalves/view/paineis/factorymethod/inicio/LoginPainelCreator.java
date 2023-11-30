package com.atosalves.view.paineis.factorymethod.inicio;

import com.atosalves.view.componentes.*;
import com.atosalves.view.paineis.Painel;
import com.atosalves.view.paineis.factorymethod.PainelCreator;
import com.atosalves.view.paineis.factorymethod.menu.MenuPainelCreator;
import com.atosalves.view.paineis.painelbuilder.PainelBuilderImpl;
import com.atosalves.view.util.Tema;

public class LoginPainelCreator implements PainelCreator {

	private TextoCaixa email;
	private SenhaCaixa senha;
	private TipoUsuarioCombo tipoUsuarioCombo;

	private Painel loginPainel;

	private void loginBotao() {
		// TODO: enviar dados por DTO pro controller
		loginPainel.setPainel(new MenuPainelCreator().criarPainel());
	}

	private void cadastroBotao() {
		loginPainel.setPainel(new CadastroUsuarioPainelCreator().criarPainel());
	}

	@Override
	public void inicializarComponentes() {
		this.email = COMPONENTES_FACTORY.criarCaixaTexto();
		this.senha = COMPONENTES_FACTORY.criarCaixaSenha();
		this.tipoUsuarioCombo = COMPONENTES_FACTORY.criarComboTipoUsuario();
	}

	@Override
	public void construirPainel() {
		loginPainel =
			new PainelBuilderImpl()
				.setImagem(Tema.FUNDO_LOGIN)
				.setTexto("MONTEIRO MOTOS", Tema.FONTE_MUITO_FORTE)
				.setTextoCaixa("EMAIL", email)
				.setSenhaCaixa(senha)
				.setTipoUsuarioCombo(tipoUsuarioCombo)
				.setBotao("LOGIN", this::loginBotao)
				.setBotao("CADASTRO", this::cadastroBotao)
				.construir();
	}

	@Override
	public Painel factoryMethod() {
		return loginPainel;
	}
}