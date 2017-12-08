package parser.models;

import java.util.ArrayList;

public class Database {

    public static Database instance = null;

    private String name;
    private String userName;
    private String userPass;
    private String url;
    private String driver;

    private ArrayList<Table> tables;

    private Database() {
        tables = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", url='" + url + '\'' +
                ", driver='" + driver + "\'\n" +
                ", tables=" + tables +
                '}';
    }
}
