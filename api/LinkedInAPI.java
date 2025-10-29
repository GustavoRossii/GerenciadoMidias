package br.com.agencia.api;

// API 3: Totalmente diferente
public class LinkedInAPI {
    private String clientId;
    private String clientSecret;

    public void connect(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        System.out.println("LinkedInAPI: Conectado com Client ID " + clientId);
    }

    public String submitShare(String textContent) {
        if (clientId == null) throw new RuntimeException("LinkedIn: Não conectado");
        System.out.println("LinkedInAPI: Compartilhando artigo -> " + textContent);
        String shareId = "urn:li:share:" + System.currentTimeMillis();
        return shareId;
    }

    public String getShareAnalytics(String shareUrn) {
        return "<analytics><likes>80</likes><comments>15</comments><shares>5</shares></analytics>";
    }
}