package br.com.agencia.model;

public class Publicacao {
    private final String id;
    private final String url;
    private final String autor;

    public Publicacao(String id, String url, String autor) {
        this.id = id;
        this.url = url;
        this.autor = autor;
    }


    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Publicacao [id=" + id + ", url=" + url + ", autor=" + autor + "]";
    }
}