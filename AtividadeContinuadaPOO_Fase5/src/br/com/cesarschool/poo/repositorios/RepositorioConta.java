package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoJaExistente;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoNaoExistente;
import br.com.cesarschool.poo.utils.Identificavel;

public class RepositorioConta extends RepositorioGenerico{

	private static RepositorioConta instancia = null;

	
	private RepositorioConta() {
		
	}
	
	public static RepositorioConta getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioConta();
		}
		return instancia;
	}
	
	public void incluir(Conta conta) throws ExcecaoObjetoJaExistente {
		super.incluir(conta);
	}
	public void alterar(Conta conta) throws ExcecaoObjetoNaoExistente {
		super.alterar(conta);				
	}
	
	public Conta buscar(long codigo) throws ExcecaoObjetoNaoExistente {
				
		return (Conta)super.buscar("" + codigo);
	}
	
	public void excluir(long codigo) throws ExcecaoObjetoNaoExistente {
		super.excluir("" + codigo);
	}

	public Conta[] buscarTodos() {
		Conta[] resultado = new Conta[tamanhoAtual];
		int indice = 0;
		for (Identificavel identificavel : getCadastro()) {
			if (identificavel != null) {
				resultado[indice++] = (Conta)identificavel;
			}
		}
		return resultado;
	}	

	@Override
	public int getTamanhoMaximoRepositorio() {
		return 1000;
	}
	
	@Override
	public String getNomeCadastro() {
		return "Conta";
	}
}
