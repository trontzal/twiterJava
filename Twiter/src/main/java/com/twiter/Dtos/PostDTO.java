package com.twiter.Dtos;

import java.time.LocalDateTime;

public record PostDTO(Long id, String usuario, LocalDateTime fecha, String texto, Integer numeroRetweets) {
}
