package com.atosalves.view.componentes.abstractfactory;

import com.atosalves.dto.corrida.CorridaDTO;
import com.atosalves.view.componentes.Botao;
import com.atosalves.view.componentes.DataCaixa;
import com.atosalves.view.componentes.EmailCaixa;
import com.atosalves.view.componentes.ListaDeCorridas;
import com.atosalves.view.componentes.NumeroCaixa;
import com.atosalves.view.componentes.SenhaCaixa;
import com.atosalves.view.componentes.Texto;
import com.atosalves.view.componentes.TextoCaixa;
import com.atosalves.view.componentes.TipoUsuarioCombo;
import java.awt.Font;

public interface ComponentesFactory {
	SenhaCaixa criarCaixaSenha();
	TextoCaixa criarCaixaTexto();
	Texto criarTexto(String texto, Font fonte);
	DataCaixa criarDataCaixa();
	TipoUsuarioCombo criarComboTipoUsuario();
	Botao criarBotao(String titulo);
	ListaDeCorridas criarListaDeItems(CorridaDTO[] lista);
	EmailCaixa criarEmailCaixa();
	NumeroCaixa criarNumeroCaixa();
}
