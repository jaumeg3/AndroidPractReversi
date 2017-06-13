package agut_giralt.androidpractreversi.utils;

/**
 * Created by jaume on 13/06/17.
 */

public class Game {

    private String username;
    private String time;
    private String position;

    public Game(String username, String time, String position){
        this.username = username;
        this.time = time;
        this.position = position;
    }

    public String getUsername(){
        return username;
    }

    public String getTime(){
        return time;
    }

    public String getPosition(){
        return position;
    }

    public Game getAll() {
        return this;
    }
}
