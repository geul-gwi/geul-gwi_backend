package com.posmosalimos.geulgwi.api.notice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeDTO {

    private String type;
    private Long noticeSeq;
    private Long fromUser;
    private String nickname;
    private String profile;
    private Long friendSeq;
    private Long messageSeq;
    private Long geulgwiSeq;
    private Long geulgwiLikeSeq;
    private Long challengeSeq;
    private Long challengeLikeSeq;
    private String regDate;
    private String checked;
}
