package hello.twitchDB;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GamesRepository extends MongoRepository<Twitchbackup, String> {
    public List<Twitchbackup> findViewersByName(String name);
    public Twitchbackup findGameByName(String name);
    public Twitchbackup findStatsByName(String name);
}
