package domain.entity;

public class Reservation {
    private String isbn;
    private int membreNumb;
    private String status;

    public String getIsbn() {
        return isbn;
    }

    public int getMembreNumb() {
        return membreNumb;
    }

    public String getStatus() {
        return status;
    }

    public Reservation(String isbn, int membreNumb, String status) {
        this.isbn = isbn;
        this.membreNumb = membreNumb;
        this.status = status;
    }
}
