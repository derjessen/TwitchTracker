package hello.twitchDB;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class Twitchbackup {

    @Id
    private String id;

    private String name;

    private String game_id;
    private List<Stats> stats;

    public Twitchbackup() {}

    public Twitchbackup(String name, String game_id) {
        this.name = name;
        this.game_id = game_id;
    }

    // Getter setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

}
