package com.guitar.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private String id;

    private String name;

    private String surname;

    private String nick;

    private String email;

    private String level;

    @JsonProperty(access = WRITE_ONLY)
    private String password;

    private List<String> learnedSongs;

    private int songsLearnedInThisLevel;
}
