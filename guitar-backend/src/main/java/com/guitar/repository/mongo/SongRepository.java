package com.guitar.repository.mongo;

import com.guitar.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface SongRepository extends MongoRepository<Song, String> {


    Optional<Song> findOneById(String id);

    Optional<Song> findOneByTitleAndAuthor(String title,String author);

    List<Song> findAllByChords(String chords);

    List<Song> findAllByLevel(String level);


}
