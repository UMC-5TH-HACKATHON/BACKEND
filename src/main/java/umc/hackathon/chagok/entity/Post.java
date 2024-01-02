package umc.hackathon.chagok.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String content;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tag> tagList = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public void setTagList(List<Tag> tagList){
        this.tagList = tagList;

        tagList.forEach(tag -> {
            tag.setPost(this);
        });
    }
}
