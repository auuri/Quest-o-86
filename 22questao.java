import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 22questao.java {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void exibirDetalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Preço: " + preco);
        System.out.println("Quantidade: " + quantidade);
        System.out.println();
    }

    public static void main(String[] args) {
        String arquivoCSV = "produtos.csv";
        List<Produto> produtos = new ArrayList<>();

        try {
            CSVReader leitor = new CSVReader(new FileReader(arquivoCSV));
            String[] linha;

            while ((linha = leitor.readNext()) != null) {
                String nome = linha[0];
                double preco = Double.parseDouble(linha[1]);
                int quantidade = Integer.parseInt(linha[2]);
                Produto produto = new Produto(nome, preco, quantidade);
                produtos.add(produto);
            }

            leitor.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do produto que deseja atualizar: ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Digite o novo preço do produto: ");
        double novoPreco = scanner.nextDouble();

        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                produto.setPreco(novoPreco);
                break;
            }
        }

        try {
            CSVWriter escritor = new CSVWriter(new FileWriter(arquivoCSV));
            for (Produto produto : produtos) {
                String[] dados = {produto.getNome(), String.valueOf(produto.getPreco()), String.valueOf(produto.getQuantidade())};
                escritor.writeNext(dados);
            }
            escritor.close();
            System.out.println("Informações atualizadas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }

     
        for (Produto produto : produtos) {
            produto.exibirDetalhes();
        }
    }
}