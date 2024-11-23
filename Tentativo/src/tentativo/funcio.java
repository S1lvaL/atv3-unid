package tentativo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class funcio {

    private String nome;
    private Date dataAdmissao;
    private float valorHora;
    private float horasTrabalhadas;

    //Construtor
    public funcio(String nome, String dataAdmissao, float valorHora, float horasTrabalhadas) throws ParseException {
        if (nome == null || nome.trim().isEmpty()) { //Validar nome e garantir  que não receba vazio
            throw new IllegalArgumentException("O nome do funcionário não pode estar vazio.");
        }
        //Validar se o nome contém números
        if (nome.matches(".*\\d.*")) { //A expressão regular verifica se há números no nome
            throw new IllegalArgumentException("O nome não pode conter números.");
        }

        if (valorHora <= 0) { //Validar valor hora 
            throw new IllegalArgumentException("O valor por hora deve ser positivo.");
        }
        if (horasTrabalhadas < 0) { //Validar horas trabalhadas
            throw new IllegalArgumentException("O número de horas trabalhadas não pode ser negativo.");
        }

        //Validar data de admissão no formato certo e não ser depois da data atual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Define o formato da data
        sdf.setLenient(false); //Garante que apenas datas válidas sejam aceitas
        Date dataAdmissaoFormatada = sdf.parse(dataAdmissao);
        Date dataAtual = new Date();
        if (dataAdmissaoFormatada.after(dataAtual)) {
            throw new IllegalArgumentException("A data de admissão não pode ser no futuro.");
        }

        this.nome = nome; //Nome é recebido como parâmetro e armazenado
        this.dataAdmissao = sdf.parse(dataAdmissao); //Converte String para Date
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    } //throws ParseException caso haja um erro

    // Método para calcular o tempo de empresa em anos
    public int calcularTempoEmpresa() {
        Date dataAtual = new Date();
        long diferencaMilissegundos = dataAtual.getTime() - dataAdmissao.getTime();
        long diferencaAnos = diferencaMilissegundos / (1000L * 60 * 60 * 24 * 365);
        return (int) diferencaAnos;
    }

    // Método para calcular o salário
    public float calcularSalario() {
        float salarioBase = valorHora * horasTrabalhadas;
        int tempoEmpresa = calcularTempoEmpresa();
        if (tempoEmpresa >= 10) {
            return salarioBase * 1.10f; // Acrescenta 10%
        } else if (tempoEmpresa >= 5) {
            return salarioBase * 1.05f; // Acrescenta 5%
        } else {
            return salarioBase; // Sem acréscimo
        }
    }

    // Getters
    public String getNome() {
        return nome;
    }
}
