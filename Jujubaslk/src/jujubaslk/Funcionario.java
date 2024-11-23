/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jujubaslk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Funcionario { //Atributos
    private String nome;
    private Date dataAdmissao;
    private float valorHora;
    private float horasTrabalhadas;
    
    //Cria um objeto funcionarioo e inicializa seus atributos 
     public Funcionario(String nome, String dataAdmissao, float valorHora, float horasTrabalhadas) throws ParseException  {
        this.nome = nome; //Nome é recebido como parâmetro e armazenado
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Define o formato da data
        this.dataAdmissao = sdf.parse(dataAdmissao); //Converte String para Date
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    } //throws ParseException caso haja um erro  

    // Método para calcular o tempo de empresa em anos
    public int calcularTempoEmpresa() {
        Date dataAtual = new Date();
        long diferencaMilissegundos = dataAtual.getTime() - dataAdmissao.getTime(); //Diferença entre as datas em milissegundos
        long diferencaAnos = diferencaMilissegundos / (1000L * 60 * 60 * 24 * 365); //Converte para anos
        return (int) diferencaAnos;
    }

    // Método para calcular o salário
    public float calcularSalario() {
        float salarioBase = valorHora * horasTrabalhadas; //Salário sem bonificação
        int tempoEmpresa = calcularTempoEmpresa(); //Chama o método anterior
        if (tempoEmpresa >= 10) {
            return salarioBase * 1.10f; //Aumenta 10%
        } else if (tempoEmpresa >= 5) {
            return salarioBase * 1.05f; //Aumenta 5%
        } else {
            return salarioBase; //Sem aumento
        }
    }

    //Como o atributo nome é privado é necessario p getNome para obter esse valor
    public String getNome() {
        return nome;
    }
}
