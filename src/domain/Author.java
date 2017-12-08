package domain;

public class Author extends BaseEntity<Integer> {

    private String name;
    private String surName;
    private int year;

    public Author(){
    }

    public Author(String name, String surName, int year) {
        this.name = name;
        this.surName = surName;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
