package com.inmonst.testtask.bulletin_board.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "adverts")
@Getter
@Setter
@NoArgsConstructor
public class Advert {
    private static final long serialVersionUID =  43L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "img_url")
    String img_url;
    @Column(name = "text", nullable = false)
    String text;
    @Column(name = "published_on", nullable = false)
    private String publishedOn;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;




    public Advert(String title, String text) {
        super();
        this.text = text;
        this.title = title;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        this.publishedOn = formatter.format(date);
    }
}
