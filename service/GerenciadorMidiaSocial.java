package br.com.agencia.service;

import br.com.agencia.model.Conteudo;
import br.com.agencia.model.Publicacao;


public class GerenciadorMidiaSocial {

    public Publicacao agendarPublicacao(ISocialMediaPlatform plataforma, Conteudo conteudo) {
        System.out.println("Gerenciador: Iniciando postagem na plataforma...");
        try {
            Publicacao publicacao = plataforma.postar(conteudo);
            System.out.println("Gerenciador: Postagem realizada com sucesso!");
            return publicacao;
        } catch (Exception e) {
            System.err.println("Gerenciador: Falha ao postar. " + e.getMessage());
            return null;
        }
    }
}