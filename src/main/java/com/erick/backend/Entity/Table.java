package com.erick.backend.Entity;

public class Table {

    private char [][] table;

    public Table() {
        this.table = new char [9][9];
    }


    public char [][] getTable() {
        return table;
    }

    public void setTable(char [][] table) {
        this.table = table;
    }

}

