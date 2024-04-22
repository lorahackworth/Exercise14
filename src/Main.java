import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("JSON with gson example");
        serializeSimple();
        deserializeSimple();
    }

    static void serializeSimple(){
        Todos losdias = new Todos("Walk the dog",false, 0, 3, "dog");
        Todos losmamas = new Todos("Pay the bills", false, 1, 1, "bills");
        ArrayList<Todos> todosList = new ArrayList<>();
        todosList.add(losdias);
        todosList.add(losmamas);

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data.json")){
            gson.toJson(todosList,writer);
        } catch (IOException e) {e.printStackTrace();}
    }

    static void deserializeSimple(){
        try (FileReader reader = new FileReader("data.json")){
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Todos>>(){}.getType();
            ArrayList<Todos> classList = new Gson().fromJson(jsonElement, listType);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

class Simple{
    private String name;
    private String email;
    private int age;
    private boolean isDeveloper;

    public Simple(String name, String email, int age, boolean isDeveloper) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


class Todos{
    private String body;
    private Boolean isDone;
    private int id;
    private int priority;
    private String title;

    public Todos(String body, Boolean isDone, int id, int priority, String title) {
        this.body = body;
        this.isDone = isDone;
        this.id = id;
        this.priority = priority;
        this.title = title;
    }
}