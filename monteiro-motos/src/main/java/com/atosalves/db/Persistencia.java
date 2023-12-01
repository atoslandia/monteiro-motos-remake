package com.atosalves.db;

import com.atosalves.model.Corrida;
import com.atosalves.model.Usuario;
import com.atosalves.observerpattern.Observavel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Persistencia {

	private XStream xs = new XStream(new DomDriver());
	private File arquivo;
	private final String nomeDoArquivo = "persistencia.xml";
	private static volatile Persistencia instance;

	private Persistencia(){
		this.arquivo = new File(nomeDoArquivo);
	}

	public static Persistencia getInstance(){
		if(instance == null){
			synchronized(Persistencia.class){
				if (instance == null) {
					instance = new Persistencia();
				}
			}
		}
		return instance;
	} 

	public void salvarUsuarios(Map<String, Usuario> usuarios) {
		xs.addPermission(AnyTypePermission.ANY);
		try (PrintWriter escrever = new PrintWriter(arquivo)) {
			String xml = xs.toXML(usuarios);
			escrever.print(xml);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void salvarCorridas(Map<String, ArrayList<Corrida>> corridas) {
		xs.addPermission(AnyTypePermission.ANY);
		try (PrintWriter escrever = new PrintWriter(arquivo)) {
			xs.autodetectAnnotations(true);
			String xml = xs.toXML(corridas);
			escrever.print(xml);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Map<String, Usuario> carregarUsuarios() throws RuntimeException {
		if (arquivo.exists()) {
			try {
				FileReader ler = new FileReader(nomeDoArquivo);
				xs.addPermission(AnyTypePermission.ANY);
				Map<String, Usuario> usuarios = (HashMap<String, Usuario>) xs.fromXML(ler);
				return usuarios;
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return new HashMap<String,Usuario>();
	}

	public Map<String, ArrayList<Corrida>> carregarCorridas() throws RuntimeException {
		Map<String, ArrayList<Corrida>> corridas = null;
		if (arquivo.exists()) {
			try {
				FileReader ler = new FileReader(nomeDoArquivo);
				xs.addPermission(AnyTypePermission.ANY);
				corridas = (HashMap<String, ArrayList<Corrida>>) xs.fromXML(ler);
				return corridas;	

			} catch (FileNotFoundException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		corridas = new HashMap<>();
		corridas.put("PENDENTE", new ArrayList<>());
		corridas.put("REIVINDICADA", new ArrayList<>());
		corridas.put("FINALIZADA", new ArrayList<>());
		corridas.put("CANCELADA", new ArrayList<>());
		return corridas;
	}
}
