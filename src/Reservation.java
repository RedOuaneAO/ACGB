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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setMembreNumb(int membreNumb) {
        this.membreNumb = membreNumb;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
