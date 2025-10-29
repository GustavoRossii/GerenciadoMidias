package br.com.agencia.model;

public class Estatisticas {
    private final int curtidas;
    private final int compartilhamentos;
    private final int comentarios;

    public Estatisticas(int curtidas, int compartilhamentos, int comentarios) {
        this.curtidas = curtidas;
        this.compartilhamentos = compartilhamentos;
        this.comentarios = comentarios;
    }
    @Override
    public String toString() {
        return "Estatisticas [curtidas=" + curtidas + ", compartilhamentos=" + compartilhamentos + ", comentarios=" + comentarios + "]";
    }
}