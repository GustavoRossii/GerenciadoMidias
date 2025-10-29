package br.com.agencia;

import br.com.agencia.factory.SocialMediaFactory;
import br.com.agencia.model.Conteudo;
import br.com.agencia.model.Estatisticas;
import br.com.agencia.model.Publicacao;
import br.com.agencia.service.GerenciadorMidiaSocial;
import br.com.agencia.service.ISocialMediaPlatform;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("### Iniciando Sistema de Gerenciamento de Mídia Social ###\n");
        
        
        Map<String, String> config = new HashMap<>();
        config.put("TWITTER_API_KEY", "key_twitter_123");
        config.put("INSTAGRAM_OAUTH_TOKEN", "token_insta_xyz");
        config.put("LINKEDIN_CLIENT_ID", "id_linkedin_abc");
        config.put("LINKEDIN_CLIENT_SECRET", "secret_linkedin_456");

        
        SocialMediaFactory factory = new SocialMediaFactory();
        GerenciadorMidiaSocial gerenciador = new GerenciadorMidiaSocial();

        
        System.out.println("--- Cenário 1: Twitter ---");
        try {
            
            ISocialMediaPlatform twitter = factory.createPlatform("twitter", config);
            
            Conteudo tweet = new Conteudo("Olá", null);
            
            
            Publicacao pubTwitter = gerenciador.agendarPublicacao(twitter, tweet);
            
            if (pubTwitter != null) {
                System.out.println("Publicação criada: " + pubTwitter);
                Estatisticas stats = twitter.obterEstatisticas(pubTwitter.getId());
                System.out.println("Estatísticas: " + stats);
            }
        } catch (Exception e) {
            System.err.println("Erro no cenário Twitter: " + e.getMessage());
        }

        System.out.println("\n--- Cenário 2: Postar no Instagram ---");
        
        try {
            ISocialMediaPlatform instagram = factory.createPlatform("instagram", config);
            
            byte[] imagem = "simulacao_de_bytes_de_uma_imagem.jpg".getBytes();
            Conteudo postInsta = new Conteudo("Olá", imagem);
            
            Publicacao pubInsta = gerenciador.agendarPublicacao(instagram, postInsta);
            
            if (pubInsta != null) {
                System.out.println("Publicação criada: " + pubInsta);
                Estatisticas stats = instagram.obterEstatisticas(pubInsta.getId());
                System.out.println("Estatísticas: " + stats);
            }
        } catch (Exception e) {
            System.err.println("Erro no cenário Instagram: " + e.getMessage());
        }

        System.out.println("\n--- Cenário 3: Postar no LinkedIn ---");
        
        try {
            ISocialMediaPlatform linkedin = factory.createPlatform("linkedin", config);
            
            Conteudo postLinkedin = new Conteudo("Olá", null);
            
            Publicacao pubLinkedin = gerenciador.agendarPublicacao(linkedin, postLinkedin);
            
            if (pubLinkedin != null) {
                System.out.println("Publicação criada: " + pubLinkedin);
                Estatisticas stats = linkedin.obterEstatisticas(pubLinkedin.getId());
                System.out.println("Estatísticas: " + stats);
            }
        } catch (Exception e) {
            System.err.println("Erro no cenário LinkedIn: " + e.getMessage());
        }
        
        System.out.println("\n### Sistema Finalizado ###");
    }
}