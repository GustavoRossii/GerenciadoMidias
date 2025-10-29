package br.com.agencia.adapter;

import br.com.agencia.api.InstagramAPI;
import br.com.agencia.model.Conteudo;
import br.com.agencia.model.Estatisticas;
import br.com.agencia.model.Publicacao;
import br.com.agencia.service.ISocialMediaPlatform;

public class InstagramAdapter implements ISocialMediaPlatform {

    private final InstagramAPI api;

    public InstagramAdapter() {
        this.api = new InstagramAPI();
    }

    @Override
    public void autenticar(String token) {
        api.login(token); 
    }

    @Override
    public Publicacao postar(Conteudo conteudo) {
        if (conteudo.getMidia() == null) {
            throw new IllegalArgumentException("Instagram exige uma mídia (imagem/vídeo).");
        }
        
        long postId = api.postMedia(conteudo.getTexto(), conteudo.getMidia());
        
        String url = "https://instagram.com/p/" + postId;
        return new Publicacao(String.valueOf(postId), url, "agencia_user"); 
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {
    
        int[] insights = api.getPostInsights(Long.parseLong(publicacaoId));
        
        return new Estatisticas(insights[0], insights[2], insights[1]); 
    }
}