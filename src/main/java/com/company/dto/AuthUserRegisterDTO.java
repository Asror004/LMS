package com.company.dto;

public record AuthUserRegisterDTO(String username, String password, String confirmPassword, Integer languageId) {
}
