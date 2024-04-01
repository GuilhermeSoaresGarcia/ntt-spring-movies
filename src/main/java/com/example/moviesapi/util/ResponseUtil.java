package com.example.moviesapi.util;

import com.example.moviesapi.dto.ResultDTO;

public final class ResponseUtil {
  public static ResultDTO getResult(final String message, final Integer statusCode, Object data) {
    return new ResultDTO(message, statusCode, data);
  }
}