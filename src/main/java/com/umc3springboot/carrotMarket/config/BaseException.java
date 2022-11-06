package com.umc3springboot.carrotMarket.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private com.umc3springboot.carrotMarket.config.BaseResponseStatus status;
}
