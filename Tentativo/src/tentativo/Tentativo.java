package tentativo;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tentativo {

    public static void main(String[] args) {

        //Variáveis para armazenar os dados do funcionário
        String nome = "";
        String dataAdmissao = "";
        float valorHora = 0;
        float horasTrabalhadas = 0;

        //Validar o nome do funcionário
        while (true) {
            nome = JOptionPane.showInputDialog(null, "Informe o nome do funcionário:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {  //Verifica se o nome está vazio
                JOptionPane.showMessageDialog(null, "O nome do funcionário não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }else if (nome.matches(".*\\d.*")) {  //Verifica se o nome contém números
                JOptionPane.showMessageDialog(null, "O nome não pode conter números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                break; //Sai do laço se o nome for válido
            }
        }

        //Validar a data de admissão
        while (true) {
            dataAdmissao = JOptionPane.showInputDialog(null, "Informe a data de admissão (dd/mm/aaaa):", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);  //Desabilita datas ambíguas
            try {
                if (dataAdmissao == null || dataAdmissao.trim().isEmpty()) {
                    throw new IllegalArgumentException("A data de admissão não pode estar vazia.");
                }
                //Verifica se a data está no formato correto
                Date date = sdf.parse(dataAdmissao);
                if (date.after(new Date())) {  // Verifica se a data não é futura
                    throw new IllegalArgumentException("A data de admissão não pode ser uma data futura.");
                }
                break;  //Sai do laço se a data for válida
            } catch (ParseException | IllegalArgumentException e) {
                //Exibe a mensagem de erro se houver um problema com a data
                JOptionPane.showMessageDialog(null, "Erro ao processar a data de admissão. Use o formato dd/mm/aaaa e certifique-se de não usar uma data futura.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Validar o valor por hora
        while (true) {
            String valorHoraStr = JOptionPane.showInputDialog(null, "Informe o valor pago por hora trabalhada:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            try {
                valorHora = Float.parseFloat(valorHoraStr);
                if (valorHora <= 0) {  //Verifica se o valor por hora é positivo
                    throw new IllegalArgumentException("O valor por hora deve ser positivo.");
                }
                break;  //Sai do laço se o valor for válido
            } catch (NumberFormatException e) {
                // Exibe a mensagem de erro se o número de horas for inválido (não for um número válido)
                JOptionPane.showMessageDialog(null, "Erro ao processar o número de horas trabalhadas. Certifique-se de inserir um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Exibe a mensagem de erro se o número de horas for negativo
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Validar o número de horas trabalhadas
        while (true) {
            String horasTrabalhadasStr = JOptionPane.showInputDialog(null, "Informe o número de horas trabalhadas:", "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            try {
                horasTrabalhadas = Float.parseFloat(horasTrabalhadasStr);
                if (horasTrabalhadas < 0) {  //Verifica se o número de horas trabalhadas não é negativo
                    throw new IllegalArgumentException("O número de horas trabalhadas não pode ser negativo.");
                }
                break;  // Sai do laço se o valor for válido
            } catch (NumberFormatException e) {
                // Exibe a mensagem de erro se o número de horas for inválido (não for um número válido)
                JOptionPane.showMessageDialog(null, "Erro ao processar o número de horas trabalhadas. Certifique-se de inserir um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Exibe a mensagem de erro se o número de horas for negativo
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Criar objeto funcionário após todas as validações
        try {
            funcio funcionario = new funcio(nome, dataAdmissao, valorHora, horasTrabalhadas);

            //Exibir resultados
            String mensagem = String.format(
                    "Nome: %s\nTempo de Empresa: %d anos\nSalário a Receber: R$ %.2f",
                    funcionario.getNome(),
                    funcionario.calcularTempoEmpresa(),
                    funcionario.calcularSalario()
            );
            JOptionPane.showMessageDialog(null, mensagem, "Detalhes do Funcionário", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            //Em caso de erro inesperado, exibe a mensagem
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
