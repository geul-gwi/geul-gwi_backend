package com.posmosalimos.geulgwi.domain.user.entity;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@Getter
public class UserTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTagSeq;

    @ManyToOne
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tagSeq")
    private Tag tag;

    @Builder
    public UserTag(User user, Tag tag) {
        this.user = user;
        this.tag = tag;
    }

    public void update(User user, Tag tag) {
        this.user = user;
        this.tag = tag;
    }
}
