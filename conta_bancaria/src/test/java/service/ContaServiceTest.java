package service;
import model.Conta;
import org.junit.*;

import java.util.Objects;
import java.util.Scanner;


public class ContaServiceTest {

    ContaService contaService;
    Conta conta1, conta2;
    //static Scanner input;

    @Before  // Antes de cada teste executa esta a��o
    public void setup(){
        contaService = new ContaService();
        conta1 = contaService.cadastrar("Gabriel");
        conta2 = contaService.cadastrar("B�rbara");
        //input = new Scanner(System.in);
    }

    /*
    @AfterClass
    public static void tearDown(){
        System.out.println("After");
        //input.close();
    }
     */

    @Test
    public void verificarQueUmaContaFoiCriada(){
        // Dado: Conta e Conta Service

        // Quando: Forem criados objetos com nomes para as contas

        //Ent�o: O objeto conta1 n�o pode ser nulo
        Assert.assertFalse(null == conta1.getAgencia());
        Assert.assertFalse(null == conta1.getNumero());
        Assert.assertEquals("Gabriel", conta1.getNomeDoCliente());
    }

    @Test
    public void novasContasDevemIniciarComSaldoZero(){
        //Dado: Uma conta nova foi criada

        //Ent�o: Seu saldo deve ser zero
        Assert.assertTrue(conta1.getSaldo()==0);
    }

    @Test
    public void deveSerPossivelDepositar(){
        //Dado: Uma conta existe e tem saldo zero

        //Quando: For depositado um valor de dez reais
        contaService.depositar(conta1,10.0);

        //Ent�o: O Valor final dever� ser dez
        Assert.assertEquals(10, conta1.getSaldo(), 0.1);
    }

    @Test
    public void deveSerPossivelSacar(){
        //Dado: Uma conta existe

        //Quando: O saldo for maior do que o valor do saque
        conta1.setSaldo(50.0);
        conta2.setSaldo(10.0);
        contaService.sacar(conta1, 20.0);
        contaService.sacar(conta2, 20.0);

        //Ent�o: O valor do saque ser� subtra�do do saldo - caso contr�rio o saldo permanece o mesmo
        Assert.assertEquals(30, conta1.getSaldo(), 0.1);
        Assert.assertNotEquals(-10, conta2.getSaldo(), 0.1);
    }

    @Test
    public void deveSerPossivelTransferir(){
        //Dado: Duas contas existem e s�o objetos diferentes

        Assert.assertNotSame(conta1,conta2);

        //Quando: O saldo da primeira foi maior que o valor da transfer�ncia
        conta1.setSaldo(50.0);
        conta2.setSaldo(10.0);
        contaService.transferir(conta1, conta2, 20.0);

        //Ent�o: O saldo da segunda conta dever� ser igual o valor da transfer�ncia mais o saldo anterior
        //       E o saldo da primeira conta dever� ser igual o valor do saldo anterior menos o valor da transfer�ncia
        //       Neste caso o valor de ambos (os saldos) dever� ser igual a 30.0

        Assert.assertEquals(conta1.getSaldo(), conta2.getSaldo(), 0.1);

    }

    @Test
    public void verificaContasIguais(){
        //Dado: Duas contas existem e s�o objetos diferentes
        Conta conta3 = new Conta(1,1, "Felipe");
        Assert.assertNotSame(conta1,conta3);

        //Quando: Por�m tem o mesmo n�mero de ag�ncia e n� da conta
        //Ent�o: Elas s�o iguais
        if (Objects.equals(conta1.getAgencia(), conta3.getAgencia())){
            Assert.assertEquals("Essas contas s�o iguais!!",conta1.getNumero(), conta3.getNumero());
            }
    }

}
