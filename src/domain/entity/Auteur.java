package domain.entity;

public class Auteur {
    private String name;
    private int id;
    public String getName() {
        return name;
    }
    public  int getId() {
        return id;
    }

    public Auteur(String name ,int id) {
        this.name = name;
this.id=id;    }
}
