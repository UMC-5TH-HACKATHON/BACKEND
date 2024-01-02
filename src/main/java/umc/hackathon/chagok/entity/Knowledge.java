package umc.hackathon.chagok.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Knowledge extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    private String content;

    private String imgUrl;
}
