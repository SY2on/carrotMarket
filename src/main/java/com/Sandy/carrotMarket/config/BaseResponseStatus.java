package com.Sandy.carrotMarket.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    NOT_FOUND_USER(false,2001,"해당 유저를 찾을 수 없습니다."),
    NOT_FOUND_POST(false,2002, "해당 게시물을 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY(false,2003,"해당 카테고리를 찾을 수 없습니다."),
    NOT_FOUND_ANY_POST(false,2004,"게시물이 존재하지 않습니다."),

    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),


    // 5000 : 필요시 만들어서 쓰세요



    // 6000 : 필요시 만들어서 쓰세요


    CHECK_ERROR(false,7777,"test중입니다");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
