    package com.posmosalimos.geulgwi.domain.tag.entity;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
    import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
    import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
    import jakarta.persistence.*;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.util.ArrayList;
    import java.util.List;


    @Entity
    @Getter
    @NoArgsConstructor
    public class Tag {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long tagSeq;
        private String backColor;
        private String fontColor;
        private String value;
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @Enumerated(EnumType.STRING)
        private TagType type;

        @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
        private List<GeulgwiTag> geulgwiTags = new ArrayList<>();

        @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
        private List<UserTag> userTags = new ArrayList<>();

        @Builder
        public Tag(String backColor, String fontColor, String value, String tagType) {
            this.backColor = backColor;
            this.fontColor = fontColor;
            this.value = value;
            this.type = TagType.from(tagType);
        }
    }
