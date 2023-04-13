package br.com.cesarschool.poo.telas;

import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.mediators.CorrentistaMediator;

public class ProgramaCadastroConta {

	public static void main(String[] args) {
		carregarCorrentistas();
		TelaConta tela = new TelaConta();
		tela.executaTela();
	}
	 
	// m�todo para incluir alguns correntistas!!
	private static void carregarCorrentistas() {
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
			try {
				cm.incluir(correntista);
			} catch (Exception e) {
				System.out.println("ERRO NA CARGA DE CORRENTISTAS: " + e.getMessage());
			}	
		}
		System.out.println("Fornecedores carregados com sucesso!");
	}
}
