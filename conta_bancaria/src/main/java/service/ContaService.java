package service;
import model.Conta;

public class ContaService {
    private static Integer numeroAgencia = 1;
    private static Integer numeroConta = 1;


    public Conta cadastrar(String nome){
        Conta conta = new Conta(numeroAgencia,numeroConta, nome);
        numeroAgencia++;
        numeroConta++;
        return conta;
    }

    public void depositar(Conta conta, Double valor){
        conta.setSaldo(conta.getSaldo()+ valor);
    }

    public void sacar(Conta conta, Double valor) {
        if(conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void transferir(Conta contaTransferindo, Conta contaRecebendo, Double valor) {
        if(contaTransferindo.getSaldo() >= valor) {
            contaTransferindo.setSaldo(contaTransferindo.getSaldo() - valor);
            contaRecebendo.setSaldo(contaRecebendo.getSaldo() + valor);
        }
    }
}
