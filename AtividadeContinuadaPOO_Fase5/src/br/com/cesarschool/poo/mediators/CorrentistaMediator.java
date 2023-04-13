package br.com.cesarschool.poo.mediators;

//import java.util.InputMismatchException;
import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoJaExistente;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoNaoExistente;
import br.com.cesarschool.poo.repositorios.RepositorioCorrentista;
import br.com.cesarschool.poo.utils.Ordenador;

public class CorrentistaMediator {
		
	private RepositorioCorrentista repositorio = RepositorioCorrentista.getInstancia();
	
	public void incluir(Correntista correntista) throws ExcecaoObjetoJaExistente {
			repositorio.incluir(correntista);

	}
	public void alterar(Correntista correntista) throws ExcecaoObjetoNaoExistente{
		 repositorio.alterar(correntista);
	}
	
	public void excluir(String cpf) throws ExcecaoObjetoNaoExistente{
		repositorio.excluir(cpf);
	}	
	
	public Correntista buscar(String cpf) throws ExcecaoObjetoNaoExistente  {
		return repositorio.buscar(cpf);
	}
	/*
	private boolean cpfValido(String cpf) {
		 if (cpf.equals("00000000000") ||
				 cpf.equals("11111111111") ||
				 cpf.equals("22222222222") || cpf.equals("33333333333") ||
				 cpf.equals("44444444444") || cpf.equals("55555555555") ||
				 cpf.equals("66666666666") || cpf.equals("77777777777") ||
				 cpf.equals("88888888888") || cpf.equals("99999999999") ||
		            (cpf.length() != 11))
		            return(false);

		        char dig10, dig11;
		        int sm, i, r, num, peso;

		        try {
		        	sm = 0;
		            peso = 10;
		            for (i=0; i<9; i++) {

		            num = (int)(cpf.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
		            }

		            r = 11 - (sm % 11);
		            if ((r == 10) || (r == 11))
		                dig10 = '0';
		            else dig10 = (char)(r + 48);
		            
		            sm = 0;
		            peso = 11;
		            for(i=0; i<10; i++) {
		            num = (int)(cpf.charAt(i) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
		            }

		            r = 11 - (sm % 11);
		            if ((r == 10) || (r == 11))
		                 dig11 = '0';
		            else dig11 = (char)(r + 48);

		            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
		                 return(true);
		            else return(false);
		                } catch (InputMismatchException erro) {
		                return(false);
		            }
	}
	*/
	
	public Correntista[] consultarTodosOrdenadoPorNome() {
		Correntista[] todos = repositorio.buscarTodos();
		if (todos != null && todos.length > 0) {
			ordenarCorrentistaPorNome(todos);
		}
		return todos;
	}
	
	private void ordenarCorrentistaPorNome (Correntista[] correntistas) {	
		Ordenador.ordenar(correntistas);		
	}


}
