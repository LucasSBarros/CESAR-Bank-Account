package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoJaExistente;
import br.com.cesarschool.poo.mediators.CorrentistaMediator;

public class TesteCorrentistas {

	public static void main(String[] args) throws ExcecaoObjetoJaExistente {
		// TODO Auto-generated method stub
		Correntista[] correntistas = {
				new Correntista("12345678901", "Claudio"),
				new Correntista("10987654321", "Maria"),
				new Correntista("00000000000", "Sergio"),
				new Correntista("99999999999", "Kamilla"),
				new Correntista("11111111111", "Josa"),
				new Correntista("66666666666", "Marcia")
				};
		CorrentistaMediator cm = new CorrentistaMediator();
		for (Correntista correntista : correntistas) {
			cm.incluir(correntista);
		}
		Correntista[] ord = cm.consultarTodosOrdenadoPorNome();
		for (Correntista correntista : ord) {
			System.out.println(correntista.getNome());
		}
	}

}
