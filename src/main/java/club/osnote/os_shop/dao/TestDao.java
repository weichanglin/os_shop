package club.osnote.os_shop.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @Value("李四：")
    private String author;

    public String sayhello(String name) {
        return author+"hello "+name;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
