package com.k14b.hieptran.Database.Note;

public class Notes {
    int id;
    int idAccount;
    String tilte;
    String content;
    String timeCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Notes() {
    }

    public Notes(int id, int idAccount, String tilte, String content, String timeCreate) {
        this.id = id;
        this.idAccount = idAccount;
        this.tilte = tilte;
        this.content = content;
        this.timeCreate = timeCreate;
    }
}
