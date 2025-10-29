package br.com.agencia.service;

import br.com.agencia.model.Conteudo;
import br.com.agencia.model.Estatisticas;
import br.com.agencia.model.Publicacao;



public interface ISocialMediaPlatform {
    Publicacao postar(Conteudo conteudo);
    Estatisticas obterEstatisticas(String publicacaoId);
    void autenticar(String token); 
}