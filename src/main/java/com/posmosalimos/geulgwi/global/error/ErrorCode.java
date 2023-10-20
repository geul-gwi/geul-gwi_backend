package com.posmosalimos.geulgwi.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business-exception-test"),


    // 인증 && 인가
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈 값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 refresh token은 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 만료되었습니다."),
    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 access token이 아닙니다."),
    FORBIDDEN_ADMIN(HttpStatus.FORBIDDEN, "A-008", "관리자 Role이 아닙니다."),
    NOT_EQUAL_PASSWORD(HttpStatus.UNAUTHORIZED, "A-009", "Password가 일치하지 않습니다."),
    NOT_EQUAL_CODE(HttpStatus.UNAUTHORIZED, "A-010", "Email Code가 일치하지 않습니다."),

    // 회원
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST,  "U-001", "이미 가입된 회원입니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "U-002", "중복된 닉네임입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U-003", "존재하지 않는 회원입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U-004", "비밀번호가 일치하지 않습니다."),
    INVALID_ID(HttpStatus.BAD_REQUEST, "U-005", "올바르지 않은 타입의 ID 입니다."),

    // 글귀 챌린지
    KEYWORD_MISSING(HttpStatus.BAD_REQUEST, "C-001", "키워드가 누락되었습니다."),
    NOT_FOUND_CHALLENGE(HttpStatus.NOT_FOUND, "C-002", "챌린지를 찾을 수 없습니다."),
    NOT_EQUAL_MEMBER(HttpStatus.BAD_REQUEST, "C-003", "글 작성자와 요청자가 다릅니다."),

    // 태그
    TAG_NOT_FOUND(HttpStatus.BAD_REQUEST, "T-001", "일치하는 태그가 존재하지 않습니다."),
    TAG_CREATION_FAILED(HttpStatus.BAD_REQUEST, "T-002", "태그 생성에 실패하였습니다."),
    TAG_DELETION_FAILED(HttpStatus.BAD_REQUEST, "T-003", "태그 삭제에 실패하였습니다."),

    // 파일
    MULTIPART_FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "F-001", "Multipart File이 비어있습니다."),

    // 메시지
    MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "M-001", "메시지를 찾을 수 없습니다."),
    ;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
