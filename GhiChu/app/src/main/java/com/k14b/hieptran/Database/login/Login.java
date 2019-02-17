package com.k14b.hieptran.Database.login;

public class Login {
    //    id integer primary key autoincrement, idaccount integer, email text, name text
    private int id, idAccount;
    private String email, name;

    public Login() {
    }

    public Login(int id, int idAccount, String email, String name) {
        this.id = id;
        this.idAccount = idAccount;
        this.email = email;
        this.name = name;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
