package model.dao;

import model.dto.Post;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();

        posts.add(new Post(
                1,
                "Ramen in Shibuya",
                "Tried the famous tonkotsu ramen today. So rich and creamy!",
                "mogumogu_user1",
                "assets/img/ramen.jpg",
                Timestamp.valueOf("2025-05-01 12:30:00")
        ));

        posts.add(new Post(
                2,
                "Strawberry Parfait",
                "Too pretty to eat... but I did anyway",
                "mogumogu_user2",
                "assets/img/parfait.jpg",
                Timestamp.valueOf("2025-05-02 15:45:00")
        ));

        posts.add(new Post(
                3,
                "Homemade Bento",
                "My first attempt at making tamagoyaki!",
                "mogumogu_user3",
                "assets/img/bento.jpg",
                Timestamp.valueOf("2025-05-03 10:15:00")
        ));

        return posts;
    }
}
