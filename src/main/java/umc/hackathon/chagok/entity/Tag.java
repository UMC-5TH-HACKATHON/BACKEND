package umc.hackathon.chagok.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Tag extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
