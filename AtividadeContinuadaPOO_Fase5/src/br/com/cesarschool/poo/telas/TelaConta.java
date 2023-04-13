package br.com.cesarschool.poo.telas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.entidades.ContaPoupanca;
import br.com.cesarschool.poo.entidades.Correntista;
import br.com.cesarschool.poo.entidades.StatusConta;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoJaExistente;
import br.com.cesarschool.poo.excecoes.ExcecaoObjetoNaoExistente;
import br.com.cesarschool.poo.excecoes.ExcecaoValidacao;
import br.com.cesarschool.poo.excecoes.InformacaoValidacao;
import br.com.cesarschool.poo.mediators.ContaMediator;
import br.com.cesarschool.poo.repositorios.RepositorioConta;

/**
 * @author An�nimo
 */
public class TelaConta {
	
	private static final int OPC_BLOQUEIO = 9;
	private static final int OPC_ENCERRAMENTO = 10;
	private static final int OPC_DESBLOQUEIO = 11; 
	
	private static final String DIGITE_O_NUMERO = "Digite n�mero: ";
	private static final String CONTA_NAO_ENCONTRADA = "Conta n�o encontrada!";
	private static final int NUMERO_DESCONHECIDO = -1;
	private static final Scanner ENTRADA = new Scanner(System.in);
	private ContaMediator contaMediator = new ContaMediator(); 
	private RepositorioConta repositorio = RepositorioConta.getInstancia();

	
	public void executaTela() {
		while(true) {
			long numero = NUMERO_DESCONHECIDO;
			imprimeMenuPrincipal();
			int opcao = ENTRADA.nextInt();
			if (opcao == 1) {
				processaInclusao();
			} else if (opcao == 2) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaAlteracao(numero);
				} 
			} else if (opcao == 3) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaExclusao(numero);
				}
			} else if (opcao == 4) {
				processaBusca();
			} else if (opcao == 5) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaCreditar(numero);
				}
			} else if (opcao == 6) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaDebitar(numero);
				}			
			} else if (opcao == 7) {
								
				relatorioSaldo(repositorio.buscarTodos());
				
			} else if (opcao == 8) {

				relatorioDataAbertura(repositorio.buscarTodos());
				
			} else if (opcao == OPC_BLOQUEIO) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaBloquear(numero);
				}
			} else if (opcao == OPC_ENCERRAMENTO) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaEncerrar(numero);
				}									
			} else if (opcao == OPC_DESBLOQUEIO) {
				numero = processaBusca();
				if (numero != NUMERO_DESCONHECIDO) {
					processaDesbloquear(numero);
				}											
			} else if (opcao == 0) {
				System.out.println("Saindo do cadastro de contas");
				System.exit(0);
			} else {
				System.out.println("Op��o inv�lida!!");
			}
		} 
	}
	
	private void imprimeMenuPrincipal() {		
		System.out.println("1- Incluir");
		System.out.println("2- Alterar");
		System.out.println("3- Excluir");
		System.out.println("4- Buscar");
		System.out.println("5- Creditar");
		System.out.println("6- Debitar");
		System.out.println("7- Relatório por Saldo");
		System.out.println("8- Relatório por Data de Abertura");
		System.out.println("9- Bloquear");
		System.out.println("10- Encerrar");
		System.out.println("11- Desbloquear");
		System.out.println("0- Sair");
		System.out.print("Digite a op��o: ");
	}
		
	private void processarExcecaoValidacao(ExcecaoValidacao e) {
		List<InformacaoValidacao> erros = e.getErros();
		for (Object object : erros) {
			InformacaoValidacao erro = (InformacaoValidacao)object;
			System.out.println(erro.getCodigo() + " - " + erro.getMensagem());
		}
	}
	
	private String lerCpfCorrentista() {
		System.out.print("Digite o CPF do correntista: ");
		String cpf = ENTRADA.next();		
		return cpf;
	}
	
	private String lerNomeCorrentista() {
		System.out.print("Digite o Nome do correntista: ");
		String nome = ENTRADA.next();		
		
		return nome;
	}
	
	private void processaInclusao() {
		Conta conta = capturaConta(NUMERO_DESCONHECIDO);
		String cpf = conta.getCorrentista().getCpf();
		String nome = conta.getCorrentista().getNome();
		try {
			contaMediator.incluir(conta, cpf, nome);
			System.out.println("Conta incluída com sucesso!");
		} catch (ExcecaoObjetoJaExistente e) {
			System.out.println(e.getMessage());
		} catch (ExcecaoValidacao e) {
			processarExcecaoValidacao(e);
		}
	}
	
	private void processaAlteracao(long numero) {
		Conta conta = capturaConta(numero);
		String cpf = lerCpfCorrentista();
		try {
			contaMediator.alterar(conta, cpf);
			System.out.println("Conta alterada com sucesso!");
		} catch (ExcecaoObjetoNaoExistente e) {
			System.out.println(e.getMessage());
		} catch (ExcecaoValidacao e) {
			processarExcecaoValidacao(e);
		}
	}
	
	private long processaBusca() {
		System.out.print(DIGITE_O_NUMERO);
		long numero = ENTRADA.nextLong();
		try {
			Conta conta = contaMediator.buscar(numero);
			System.out.println("Numero: " + conta.getNumero());
			System.out.println("Saldo: " + conta.getSaldo());
			System.out.println("Data Abertura: " + conta.getDataAbertura());			
			System.out.println("Status: " + conta.getStatus().getDescricao());
			
			if (conta instanceof ContaPoupanca) {
				ContaPoupanca contaPoupanca = (ContaPoupanca)conta;
				System.out.println("Taxa de juros: " + contaPoupanca.getTaxaJuros());
				System.out.println("Qtd dep�sitos: " + contaPoupanca.getQtdDepositos());	
				}
			return numero;
			} catch (ExcecaoObjetoNaoExistente e) {
				System.out.println(CONTA_NAO_ENCONTRADA);
				return NUMERO_DESCONHECIDO;
				}
		}
			
	private void relatorioSaldo(Conta[] contas) {
		
		ContaMediator mediator = new ContaMediator();
				
		contas = mediator.consultarTodosOrdenadoPorSaldo();
		
		for (int i = 0; i < contas.length; i++){
			
			System.out.println("Numero: " + contas[i].getNumero());
			System.out.println("Saldo: " + contas[i].getSaldo());
			System.out.println("Data Abertura: " + contas[i].getDataAbertura());			
			System.out.println("Nome: " + contas[i].getCorrentista().getNome());				
		}	
	}
	
	private void relatorioDataAbertura(Conta[] contas) {
		
		ContaMediator mediator = new ContaMediator();
				
		contas = mediator.consultarTodosOrdenadoPorDataAbertura();
		
		for (int i = 0; i < contas.length; i++){
			
			System.out.println("Numero: " + contas[i].getNumero());
			System.out.println("Saldo: " + contas[i].getSaldo());
			System.out.println("Data Abertura: " + contas[i].getDataAbertura());			
			System.out.println("Nome: " + contas[i].getCorrentista().getNome());	
				
		}
		
	}
	
	private void processaExclusao(long numero) {
		try {
			contaMediator.excluir(numero);
			System.out.println("Conta exclu�da com sucesso!");
		} catch (ExcecaoObjetoNaoExistente e) {
			System.out.println(e.getMessage());
		}
	}
	private void processaCreditar(long numero) {
		System.out.print("Digite o valor a ser creditado: ");
		double valor = ENTRADA.nextDouble();
		try {
			contaMediator.creditar(numero, valor);
			System.out.println("Conta creditada com sucesso!");
		} catch (ExcecaoObjetoNaoExistente e) {
			System.out.println(e.getMessage());
		} 
	}
	private void processaDebitar(long numero) {
		System.out.print("Digite o valor a ser debitado: ");
		double valor = ENTRADA.nextDouble();
		try {
			contaMediator.debitar(numero, valor);
			System.out.println("Conta debitada com sucesso!");
		} catch (ExcecaoObjetoNaoExistente e) {
			System.out.println(e.getMessage());
		} 
	}
	private void processaMudancaStatus(long numero, int opcao, String msgSucesso, String msgErro) throws ExcecaoObjetoNaoExistente {
		boolean ret;                                                                                             
		if (opcao == OPC_BLOQUEIO) {
			ret = contaMediator.bloquearConta(numero);
		} else if (opcao == OPC_ENCERRAMENTO) {
			ret = contaMediator.encerrarConta(numero);
		} else {
			ret = contaMediator.desbloquearConta(numero);
		}
		if (ret) {
			System.out.println(msgSucesso);
		} else {
			System.out.println(msgErro);
		}
	}

	private void processaBloquear(long numero) {
		try {
			processaMudancaStatus(numero, OPC_BLOQUEIO, "Conta bloqueada com sucesso!", "Bloqueio n�o processado!");
		} catch (ExcecaoObjetoNaoExistente e) {
			
			System.out.println(CONTA_NAO_ENCONTRADA);
			
		} 
	}
	private void processaEncerrar(long numero) {
		try {
			processaMudancaStatus(numero, OPC_ENCERRAMENTO, "Conta encerrada com sucesso!", "Encerramento n�o processado!");
		} catch (ExcecaoObjetoNaoExistente e) {
			
			System.out.println(CONTA_NAO_ENCONTRADA);;
		} 
	}
	private void processaDesbloquear(long numero) {
		try {
			processaMudancaStatus(numero, OPC_DESBLOQUEIO, "Conta desbloqueada com sucesso!", "Desbloqueio n�o processado!");
		} catch (ExcecaoObjetoNaoExistente e) {
			
			System.out.println(CONTA_NAO_ENCONTRADA);
		} 
	}

	private Conta capturaConta(long numeroDaAlteracao) {
		
		long numero;		
		if (numeroDaAlteracao == NUMERO_DESCONHECIDO) {
			System.out.print(DIGITE_O_NUMERO);
			numero = ENTRADA.nextLong();			
		} else {
			numero = numeroDaAlteracao;
		}
		System.out.print("Digite a data de abertura (dd/mm/yyyy): ");		
		String dataAberturaStr = ENTRADA.next();
		LocalDate dataAbertura = LocalDate.parse(dataAberturaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.print("Digite o saldo: ");
		double saldo = ENTRADA.nextDouble();
		System.out.print("Digite o status: ");
		int statusInt = ENTRADA.nextInt();
		StatusConta status = StatusConta.obterPorCodigo(statusInt);
		System.out.print("Digite <1> para conta normal e <2> para conta poupan�a: ");
		int tipoConta = ENTRADA.nextInt();	
		
		String cpf = lerCpfCorrentista();
		String nome = lerNomeCorrentista();
		Correntista correntista = new Correntista(cpf, nome);
		
		if (tipoConta == 1) {
			return new Conta(numero, dataAbertura, saldo, status, correntista);	
		} else {
			System.out.print("Digite a taxa de juros: ");
			double taxaJuros = ENTRADA.nextDouble();
			return new ContaPoupanca(numero, dataAbertura, saldo, status, correntista, taxaJuros); 
		}
		
	}
}
