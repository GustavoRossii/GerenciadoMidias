package br.com.agencia.adapter;

import br.com.agencia.api.LinkedInAPI;
import br.com.agencia.model.Conteudo;
import br.com.agencia.model.Estatisticas;
import br.com.agencia.model.Publicacao;
import br.com.agencia.service.ISocialMediaPlatform;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedInAdapter implements ISocialMediaPlatform {
    
    private final LinkedInAPI api;

    public LinkedInAdapter() {
        this.api = new LinkedInAPI();
    }

    @Override
    public void autenticar(String token) {
        String[] parts = token.split(":");
        api.connect(parts[0], parts[1]); 
    }

    @Override
    public Publicacao postar(Conteudo conteudo) {
        String shareId = api.submitShare(conteudo.getTexto());
        
        String url = "https://linkedin.com/feed/update/" + shareId;
        return new Publicacao(shareId, url, "Agência Corp"); 
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {

        String xml = api.getShareAnalytics(publicacaoId);

        int likes = Integer.parseInt(parseXml(xml, "likes"));
        int comments = Integer.parseInt(parseXml(xml, "comments"));
        int shares = Integer.parseInt(parseXml(xml, "shares"));
        
        return new Estatisticas(likes, shares, comments); 
    }

    private String parseXml(String xml, String tag) {
        Matcher m = Pattern.compile("<" + tag + ">(\\d+)</" + tag + ">").matcher(xml);
        return m.find() ? m.group(1) : "0";
    }
}