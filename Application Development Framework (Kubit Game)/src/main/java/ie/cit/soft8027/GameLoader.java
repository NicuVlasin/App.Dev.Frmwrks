package ie.cit.soft8027;

import ie.cit.soft8027.domain.Player;
import ie.cit.soft8027.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class GameLoader {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlayerRepository playerRepository;


    @PostConstruct
    public void loadData() throws IOException {
        List<Player> all = playerRepository.findAll();
        //if there are no users in DB execute the data script
        if(all == null || all.isEmpty()){
            loadDataScript();
        }
    }

    private void loadDataScript() throws IOException {
        File file = new ClassPathResource("initial-data.sql").getFile();
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String str = new String(data, "UTF-8");
        jdbcTemplate.execute(str);
        System.out.println(file);
    }
}