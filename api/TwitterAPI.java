package br.com.agencia.api;

public class TwitterAPI {
    private String apiKey;
    
    public void setAuthToken(String apiKey) {
        this.apiKey = apiKey;
        System.out.println("TwitterAPI: Autenticado com " + apiKey);
    }
    
    public String tweet(String texto) {
        if (apiKey == null) throw new RuntimeException("Twitter: Não autenticado");
        if (texto.length() > 280) throw new RuntimeException("Twitter: Texto muito longo");
        
        System.out.println("TwitterAPI: Postando tweet -> " + texto);
        String tweetId = String.valueOf(System.currentTimeMillis());
        return "{\"id\": \"" + tweetId + "\", \"user\": \"@agencia\"}";
    }

    public String getTweetMetrics(String tweetId) {
        return "{\"favorites\": 150, \"retweets\": 30, \"replies\": 10}";
    }
}