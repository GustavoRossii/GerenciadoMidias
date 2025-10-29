package br.com.agencia.model;

public class Conteudo {
    private String texto;
    private byte[] midia; 

    public Conteudo(String texto, byte[] midia) {
        this.texto = texto;
        this.midia = midia;
    }
    public String getTexto() { return texto; }
    public byte[] getMidia() { return midia; }
}