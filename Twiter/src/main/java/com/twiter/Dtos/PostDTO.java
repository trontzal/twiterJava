package com.twiter.Dtos;

import java.util.Date;

public record PostDTO(Long id, String usuario, String texto, Long numeroRetweets, Date fecha) {
}
