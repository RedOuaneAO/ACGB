package domain.entity;

public class Membre {
    private int membreNumber;
    private String firstName;
    private String secondName;

    public int getMembreNumber() {
        return membreNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Membre(int membreNumber, String firstName, String secondName) {
        this.membreNumber = membreNumber;
        this.firstName = firstName;
        this.secondName = secondName;
    }
}
