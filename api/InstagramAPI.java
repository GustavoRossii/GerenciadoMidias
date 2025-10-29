package br.com.agencia.api;


public class InstagramAPI {
    private String oauthToken;

    public boolean login(String token) {
        this.oauthToken = token;
        System.out.println("InstagramAPI: Logado com token " + token.substring(0, 5) + "...");
        return true;
    }
        public long postMedia(String caption, byte[] image) {
        if (oauthToken == null) throw new RuntimeException("Instagram: Não logado");
        if (image == null) throw new RuntimeException("Instagram: Imagem é obrigatória");
        
        System.out.println("InstagramAPI: Postando foto com legenda -> " + caption);
        return System.currentTimeMillis();
    }
    
    public int[] getPostInsights(long postId) {
        return new int[]{500, 45, 20};
    }
}