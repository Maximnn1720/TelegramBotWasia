package app.entities;

public class User {
    private String name;
    private String password;

    public User(){

    }
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    public void SetName(String name){
        this.name = name;
    }
    public String GetName(){
        return this.name;
    }
    public void SetPassword(String password){
        this.password = password;
    }
    public String GetPassword(String password){
        return this.password;
    }
    @Override
    public String toString(){
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
