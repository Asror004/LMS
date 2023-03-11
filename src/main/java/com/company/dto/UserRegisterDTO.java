package com.company.dto;

public record UserRegisterDTO(String username, String password, String confirmPassword, Integer languageId) {
}
