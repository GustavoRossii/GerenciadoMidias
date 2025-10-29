package br.com.agencia.factory;

import br.com.agencia.adapter.InstagramAdapter;
import br.com.agencia.adapter.LinkedInAdapter;
import br.com.agencia.adapter.TwitterAdapter;
import br.com.agencia.service.ISocialMediaPlatform;
import java.util.Map;

public class SocialMediaFactory {

    /**
     * Factory Method que cria e configura o adapter correto.
     * @param platform O nome da plataforma (ex: "twitter")
     * @param config Um mapa de configuração (Dica 4)
     * @return Uma instância de ISocialMediaPlatform
     */
    public ISocialMediaPlatform createPlatform(String platform, Map<String, String> config) {
        ISocialMediaPlatform adapter;

        switch (platform.toLowerCase()) {
            case "twitter":
                adapter = new TwitterAdapter();
                adapter.autenticar(config.get("TWITTER_API_KEY"));
                break;
            case "instagram":
                adapter = new InstagramAdapter();
                adapter.autenticar(config.get("INSTAGRAM_OAUTH_TOKEN"));
                break;
            case "linkedin":
                adapter = new LinkedInAdapter();
                String token = config.get("LINKEDIN_CLIENT_ID") + ":" + config.get("LINKEDIN_CLIENT_SECRET");
                adapter.autenticar(token);
                break;
            default:
                throw new IllegalArgumentException("Plataforma desconhecida: " + platform);
        }
        
        return adapter;
    }
}