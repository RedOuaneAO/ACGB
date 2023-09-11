package domain.entity;

import java.time.LocalDate;

public class Reservation {
    private int membreId;
    private int boookID;
    private String status;
    private LocalDate borrowDate;
    private LocalDate duedate ;

    private LocalDate returnedDate ;

    public int getMembreId() {
        return membreId;
    }

    public int getBoookID() {
        return boookID;
    }


    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public int membreId() {
        return membreId;
    }

    public String getStatus() {
        return status;
    }

    public Reservation(int membreId, int boookID) {
        this.membreId = membreId;
        this.boookID = boookID;
    }

    public Reservation(int boookID, int membreId, String status , LocalDate borrowDate , LocalDate duedate , LocalDate returnedDate) {
        this.boookID = boookID;
        this.membreId = membreId;
        this.status = status;
        this.borrowDate=borrowDate;
        this.duedate=duedate;
        this.returnedDate=returnedDate;
    }
}
