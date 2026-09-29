package model;

public class RegistroDecomposicao {
    private int id;
    private double qtdLixoOrganico;
    private String descricaoLixo;
    private double qtdAdubo;
    private double qtdChorume;
    private int tempoDecomposicaoDias;

    // Construtor vazio
    public RegistroDecomposicao() {}

    // Construtor sem ID (usado no momento do cadastro/registro)
    public RegistroDecomposicao(double qtdLixoOrganico, String descricaoLixo, double qtdAdubo, double qtdChorume, int tempoDecomposicaoDias) {
        this.qtdLixoOrganico = qtdLixoOrganico;
        this.descricaoLixo = descricaoLixo;
        this.qtdAdubo = qtdAdubo;
        this.qtdChorume = qtdChorume;
        this.tempoDecomposicaoDias = tempoDecomposicaoDias;
    }

    // Construtor completo com ID (usado ao carregar dados do banco)
    public RegistroDecomposicao(int id, double qtdLixoOrganico, String descricaoLixo, double qtdAdubo, double qtdChorume, int tempoDecomposicaoDias) {
        this(qtdLixoOrganico, descricaoLixo, qtdAdubo, qtdChorume, tempoDecomposicaoDias);
        this.id = id;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQtdLixoOrganico() {
        return qtdLixoOrganico;
    }

    public void setQtdLixoOrganico(double qtdLixoOrganico) {
        this.qtdLixoOrganico = qtdLixoOrganico;
    }

    public String getDescricaoLixo() {
        return descricaoLixo;
    }

    public void setDescricaoLixo(String descricaoLixo) {
        this.descricaoLixo = descricaoLixo;
    }

    public double getQtdAdubo() {
        return qtdAdubo;
    }

    public void setQtdAdubo(double qtdAdubo) {
        this.qtdAdubo = qtdAdubo;
    }

    public double getQtdChorume() {
        return qtdChorume;
    }

    public void setQtdChorume(double qtdChorume) {
        this.qtdChorume = qtdChorume;
    }

    public int getTempoDecomposicaoDias() {
        return tempoDecomposicaoDias;
    }

    public void setTempoDecomposicaoDias(int tempoDecomposicaoDias) {
        this.tempoDecomposicaoDias = tempoDecomposicaoDias;
    }
}
