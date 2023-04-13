package br.com.cesarschool.poo.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ExcecaoValidacao extends Exception{
	private List<InformacaoValidacao> erros = new ArrayList<InformacaoValidacao>();
	
	public ExcecaoValidacao() {
		
	}
	
	public ExcecaoValidacao(List<InformacaoValidacao> erros) {
		super();
		this.erros = erros;
	}
	
	public List<InformacaoValidacao> getErros() {
		return erros;
	}

	public void addErro(InformacaoValidacao erro) {
		erros.add(erro);
	}
}
