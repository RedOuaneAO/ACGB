package domain.entity;

import domain.entity.Auteur;

public class Books {
    private String title;
    private String isbn ;
    private int quantite ;
    private Auteur auteur;
    public String getTitle() {
        return title;
    }
    public int getQuantite() {
        return quantite;
    }
    public String getIsbn() {
        return isbn;
    }
    public Auteur getAuteur() {
        return auteur;
    }

    public Books(String title, String isbn,Integer quantite ,Auteur auteur) {
        this.title = title;
        this.quantite = quantite;
        this.isbn = isbn;
        this.auteur = auteur;
    }
    public Books(String title,String isbn,Integer quantite) {
        this.title = title;
        this.quantite = quantite;
        this.isbn = isbn;
    }
    public Books(String isbn){
        this.isbn = isbn;
    }
    public void setAuthor(Auteur auteur) {
        this.auteur = auteur;
    }
}
