package com.posmosalimos.geulgwi.api.message.send.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
        private String title;
        private String content;
        private Long senderSeq;
        private Long receiverSeq;

        @Getter @Setter
        @Builder @NoArgsConstructor @AllArgsConstructor
        public static class Response {
                private Long messageSeq;
                private String title;
                private String content;
                private Long senderSeq;
                private String senderNickname;
                private String profile;
                private Long receiverSeq;
                private String receiverNickname;
                private String regDate;

        }
}
