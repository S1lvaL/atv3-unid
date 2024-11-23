/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jujubaslk;

import java.text.ParseException;
import java.util.Scanner;

public class Principal {
     public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
            // Solicitar dados do funcionário
            System.out.print("Informe o nome do funcionário: ");
            String nome = teclado.nextLine();

            System.out.print("Informe a data de admissão (dd/mm/aaaa): ");
            String dataAdmissao = teclado.nextLine();

            System.out.print("Informe o valor pago por hora trabalhada: ");
            float valorHora = teclado.nextFloat();

            System.out.print("Informe o número de horas trabalhadas: ");
            float horasTrabalhadas = teclado.nextFloat();

            // Criar objeto Funcionario
            Funcionario funcionario = new Funcionario(nome, dataAdmissao, valorHora, horasTrabalhadas);

            // Exibir resultados
            System.out.println("\n--- Detalhes do Funcionário ---");
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Tempo de Empresa: " + funcionario.calcularTempoEmpresa() + " anos");
            System.out.printf("Salário a Receber: R$ %.2f\n", funcionario.calcularSalario());

        //Tratamento de erros
        } catch (ParseException e) {  //Caso a data esteja no formato errado
            System.out.println("Erro ao processar a data de admissão. Certifique-se de usar o formato dd/mm/aaaa.");
        }catch (NumberFormatException e) { //Caso valores numéricos sejam inválidos
            System.out.println( "Erro ao processar valores numéricos. Certifique-se de usar números válidos.");
        }

        teclado.close();
    }
}
