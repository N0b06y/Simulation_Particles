package lib;

public class Friend {
    String name;
    int age;
    
    public Friend(String name, int age){
        this.name = name;
        this.age = age;
    }
    void printValues(){
        System.out.println("Name:"+this.name);
        System.out.println("Age:"+this.age);
    }
}
