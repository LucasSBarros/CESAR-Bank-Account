package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoJaExistente;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoNaoExistente;
import br.com.cesarschool.poo.utils.Identificavel;

public class RepositorioCorrentista extends RepositorioGenerico{

	private static RepositorioCorrentista instancia;

	
	private RepositorioCorrentista() {
	}
	
	public static RepositorioCorrentista getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioCorrentista();
		}
		return instancia;
	}

	public void incluir(Correntista correntista) throws ExcecaoObjetoJaExistente {
		super.incluir(correntista);
	}
	public void alterar(Correntista correntista) throws ExcecaoObjetoNaoExistente {
		super.alterar(correntista);
	}
	
	public Correntista buscar(String cpf) throws ExcecaoObjetoNaoExistente {
		return (Correntista)super.buscar(cpf);
	}
	
	public void excluir(String cpf) throws ExcecaoObjetoNaoExistente {
		super.excluir(cpf);
	}
	
	public Correntista[] buscarTodos() {
		Correntista[] resultado = new Correntista[tamanhoAtual];
		int indice = 0;
		for (Identificavel identificavel : getCadastro()) {
			if (identificavel != null) {
				resultado[indice++] = (Correntista)identificavel;
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
		return "Correntista";
	}
}
