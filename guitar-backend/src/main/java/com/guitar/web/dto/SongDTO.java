package com.guitar.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SongDTO {

    private String id;

    private String title;

    private String author;

    private String strimming;

    private String lyrics;

    private String chords;

    private String necessaryChords;

    private String level;

    private String songNameInTheDatabase;
}
