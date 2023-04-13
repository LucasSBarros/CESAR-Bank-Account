package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.excecoes.ExcecaoInfra;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoJaExistente;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoNaoExistente;
import br.com.cesarschool.poo.utils.Identificavel;

public abstract class RepositorioGenerico {
	
	private static final String JA_EXISTENTE = " já existente!";
	private static final String NAO_EXISTENTE = " não existente!";
	private Identificavel[] cadastro;
	int tamanhoAtual = 0;
	
	public Identificavel[] getCadastro() {
		return cadastro;
	}
	
	public RepositorioGenerico() {
		cadastro = new Identificavel[getTamanhoMaximoRepositorio()];
	}
	
	public abstract int getTamanhoMaximoRepositorio(); 
	
	public abstract String getNomeCadastro();
	
	public void incluir(Identificavel identificavel) throws ExcecaoObjetoJaExistente {
		if (buscarIndice(identificavel.getIdentificadorUnico()) != -1) {
			throw new ExcecaoObjetoJaExistente(getNomeCadastro() + JA_EXISTENTE);
		} else if (tamanhoAtual == getTamanhoMaximoRepositorio() - 1) {
			throw new ExcecaoInfra("Tamanho m�ximo do reposit�rio excedido!");
		} else {
			for (int i = 0; i < cadastro.length; i++) {
				if (cadastro[i] == null) {
					cadastro[i] = identificavel; 
					break;
				}
			}
			tamanhoAtual++; 
		}
	}
	public void alterar(Identificavel identificavel) throws ExcecaoObjetoNaoExistente {
		int indice = buscarIndice(identificavel.getIdentificadorUnico()); 
		if (indice == -1) {
			throw new ExcecaoObjetoNaoExistente(getNomeCadastro() + NAO_EXISTENTE);
		} else {
			cadastro[indice] = identificavel;
			 
		}
	}
	
	public Identificavel buscar(String identificadorUnico) throws ExcecaoObjetoNaoExistente {
		int indice = buscarIndice(identificadorUnico);
		if (indice == -1) {
			throw new ExcecaoObjetoNaoExistente(getNomeCadastro() + NAO_EXISTENTE);
		} else {
			return cadastro[indice];
		}
	}
	
	public void excluir(String identificadorUnico) throws ExcecaoObjetoNaoExistente {
		int indice = buscarIndice(identificadorUnico);
		if (indice == -1) {
			throw new ExcecaoObjetoNaoExistente(getNomeCadastro() + NAO_EXISTENTE);
		} else {
			cadastro[indice] = null;
			tamanhoAtual--;
			
		}		
	}
	
	public Identificavel[] buscarTodos() {
		Identificavel[] resultado = new Identificavel[tamanhoAtual];
		int indice = 0;
		for (Identificavel identificavel : cadastro) {
			if (identificavel != null) {
				resultado[indice++] = identificavel; 
			}
		}
		return resultado;
	}
	
	private int buscarIndice(String identificadorUnico) {		
		for (int i = 0; i < cadastro.length; i++) {
			Identificavel identificavel = cadastro[i];
			if (identificavel != null && identificavel.getIdentificadorUnico().equals(identificadorUnico)) {
				return i; 				
			}
		}
		return -1;
	}
	
}