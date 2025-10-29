package br.com.agencia.adapter;

import br.com.agencia.api.TwitterAPI;
import br.com.agencia.model.Conteudo;
import br.com.agencia.model.Estatisticas;
import br.com.agencia.model.Publicacao;
import br.com.agencia.service.ISocialMediaPlatform;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TwitterAdapter implements ISocialMediaPlatform {

    private final TwitterAPI api;

    public TwitterAdapter() {
        this.api = new TwitterAPI();
    }

    @Override
    public void autenticar(String token) {
        this.api.setAuthToken(token); 
    }

    @Override
    public Publicacao postar(Conteudo conteudo) {

        String respostaJson = api.tweet(conteudo.getTexto());

        String id = parseJson(respostaJson, "id");
        String user = parseJson(respostaJson, "user");
        String url = "https://twitter.com/" + user + "/status/" + id;
        
        return new Publicacao(id, url, user); 
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {
        String metricasJson = api.getTweetMetrics(publicacaoId);
        int curtidas = Integer.parseInt(parseJson(metricasJson, "favorites"));
        int compartilhamentos = Integer.parseInt(parseJson(metricasJson, "retweets"));
        int comentarios = Integer.parseInt(parseJson(metricasJson, "replies"));
        
        return new Estatisticas(curtidas, compartilhamentos, comentarios); 
    }

    private String parseJson(String json, String key) {
        Matcher m = Pattern.compile("\"" + key + "\":\\s*\"?([^\",}]+)\"?").matcher(json);
        return m.find() ? m.group(1) : null;
    }
}