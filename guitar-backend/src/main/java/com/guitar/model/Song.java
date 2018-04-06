package com.guitar.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;


@Data
@AllArgsConstructor
@Builder(builderMethodName = "songBuilder")
@NoArgsConstructor
@Document(collection = "songs")
public class Song {

    @Id
    private String id;

    @NonNull
    private String title;


    private String author;

    private String strimming;

    @NonNull
    private String lyrics;

    @NonNull
    private String chords;

    @NonNull
    private String necessaryChords;

    @NonNull
    private String level;

    @NonNull
    private String songNameInTheDatabase;


    public Song(String title, String author, String strimming, String lyrics, String chords,String necessaryChords,String level,String songNameInTheDatabase) {
        this.id=UUID.randomUUID().toString();
        this.title=title;
        this.author=author;
        this.strimming=strimming;
        this.lyrics=lyrics;
        this.chords=chords;
        this.necessaryChords=necessaryChords;
        this.level=level;
        this.songNameInTheDatabase=songNameInTheDatabase;
    }

    public static SongBuilder builder() {
        return songBuilder().id(UUID.randomUUID().toString());
    }

}
