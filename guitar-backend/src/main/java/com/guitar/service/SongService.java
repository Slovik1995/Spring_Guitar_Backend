package com.guitar.service;

import com.guitar.model.Song;
import com.guitar.repository.mongo.SongRepository;
import com.guitar.web.CustomException;
import com.guitar.web.ErrorInfo;
import com.guitar.web.dto.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongDTO songToDTO(Song song){
        return SongDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .author(song.getAuthor())
                .strimming(song.getStrimming())
                .lyrics(song.getLyrics())
                .chords(song.getChords())
                .necessaryChords(song.getNecessaryChords())
                .level(song.getLevel())
                .songNameInTheDatabase(song.getSongNameInTheDatabase())
                .build();
    }

    public SongDTO songToDTOWithoutLyrics(Song song){
        return SongDTO.builder()
                .id(song.getId())
                .title(song.getTitle())
                .author(song.getAuthor())
                .strimming(song.getStrimming())
                .lyrics("")
                .chords(song.getChords())
                .necessaryChords(song.getNecessaryChords())
                .level(song.getLevel())
                .songNameInTheDatabase(song.getSongNameInTheDatabase())
                .build();
    }

    public Song dtoToSong(SongDTO songDTO){
        return Song.builder()
                .id(songDTO.getId())
                .title(songDTO.getTitle())
                .author(songDTO.getAuthor())
                .strimming(songDTO.getStrimming())
                .lyrics(songDTO.getLyrics())
                .level(songDTO.getLevel())
                .chords(songDTO.getChords())
                .necessaryChords(songDTO.getNecessaryChords())
                .songNameInTheDatabase(songDTO.getSongNameInTheDatabase())
                .build();
    }

    public SongDTO getSongWithTitleAndAuthor(String title, String author) throws CustomException {
        return songRepository.findOneByTitleAndAuthor(title, author)
                .map(this::songToDTO)
                .orElseThrow(() -> new CustomException(ErrorInfo.SONG_NOT_FOUND));
    }

    public SongDTO getSongWithId(String id) throws CustomException{
        return songRepository.findOneById(id)
                .map(this::songToDTO)
                .orElseThrow(()->new CustomException(ErrorInfo.SONG_NOT_FOUND));
    }


    public List<SongDTO> getAllSongsWithChords(String chords) {
        return songRepository.findAllByChords(chords)
                .stream()
                .map(this::songToDTO)
                .collect(Collectors.toList());
    }


    public List<SongDTO> getAllSongs() {
        return songRepository.findAll()
                .stream()
                .map(this::songToDTO)
                .collect(Collectors.toList());
    }
    public List<SongDTO> getAllSongsWithoutLyrics() {
        return songRepository.findAll()
                .stream()
                .map(this::songToDTOWithoutLyrics)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getAllSongsWithoutLyrics(String level) {
        return songRepository.findAllByLevel(level)
                .stream()
                .map(this::songToDTOWithoutLyrics)
                .collect(Collectors.toList());
    }

    public void createSong(SongDTO songDTO) throws CustomException{

        Song song= dtoToSong(songDTO);

        Optional<Song> songInTheDatabase = songRepository.findOneByTitleAndAuthor(song.getTitle(),song.getAuthor());
            if(songInTheDatabase.isPresent()){
                throw new CustomException(ErrorInfo.SONG_ALREADY_EXISTS);
        };

        songRepository.save(song);
    }

    public void updateSong(SongDTO songDTO) throws CustomException{
        Optional<Song> retrievedSong = songRepository.findOneByTitleAndAuthor(songDTO.getTitle(),songDTO.getAuthor());

        retrievedSong.orElseThrow(() -> new CustomException(ErrorInfo.SONG_NOT_FOUND));

        retrievedSong.ifPresent(song -> {
            song.setStrimming(songDTO.getStrimming());
            song.setLyrics(songDTO.getLyrics());
            song.setChords(songDTO.getChords());
            song.setLevel(songDTO.getLevel());
            song.setNecessaryChords(songDTO.getNecessaryChords());
            song.setSongNameInTheDatabase(songDTO.getSongNameInTheDatabase());
            songRepository.save(song);
        });
    }
}
