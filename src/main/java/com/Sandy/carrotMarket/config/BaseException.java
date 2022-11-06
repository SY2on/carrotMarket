package com.Sandy.carrotMarket.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private com.Sandy.carrotMarket.config.BaseResponseStatus status;
}
