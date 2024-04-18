import java.util.Date;

public class 25questao.java {
    private Date data;
    private double valor;
    private String produto;

    public Venda(Date data, double valor, String produto) {
        this.data = data;
        this.valor = valor;
        this.produto = produto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda(new Date(), 100.0, "Produto A"));
        vendas.add(new Venda(new Date(), 150.0, "Produto B"));
        vendas.add(new Venda(new Date(), 200.0, "Produto C"));

        
        exportarParaCSV(vendas);
    }

    private static void exportarParaCSV(List<Venda> vendas) {
        String csvFile = "vendas.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            
            String[] header = {"Data", "Valor", "Produto"};
            writer.writeNext(header);

            
            for (Venda venda : vendas) {
                String[] vendaData = {venda.getData().toString(), String.valueOf(venda.getValor()), venda.getProduto()};
                writer.writeNext(vendaData);
            }
            System.out.println("Vendas exportadas para " + csvFile + " com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar as vendas para CSV: " + e.getMessage());
        }
    }
}