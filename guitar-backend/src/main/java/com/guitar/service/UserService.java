package com.guitar.service;


import com.guitar.model.User;
import com.guitar.repository.jpa.UserRepository;
import com.guitar.web.CustomException;
import com.guitar.web.ErrorInfo;
import com.guitar.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UserDTO userToDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .nick(user.getNick())
                .email(user.getEmail())
                .password(user.getPassword())
                .level(user.getLevel())
                .learnedSongs(user.getLearnedSongs())
                .songsLearnedInThisLevel(user.getSongsLearnedInThisLevel())
                .build();
    }

    public User dtoToUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .nick(userDTO.getNick())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .level(userDTO.getLevel())
                .learnedSongs(userDTO.getLearnedSongs())
                .songsLearnedInThisLevel(userDTO.getSongsLearnedInThisLevel())
                .build();

    }

    public UserDTO getUserWithNick(String nick) throws CustomException {
        return userRepository.findOneByNick(nick)
                .map(this::userToDto)
                .orElseThrow(() -> new CustomException(ErrorInfo.USER_NOT_FOUND));
    }


    public UserDTO getUserWithName(String name) throws CustomException {
        return userRepository.findOneByName(name)
                .map(this::userToDto)
                .orElseThrow(() -> new CustomException(ErrorInfo.USER_NOT_FOUND));
    }

    public UserDTO getUserWithID(String id) throws CustomException {
        return userRepository.findOneById(id)
                .map(this::userToDto)
                .orElseThrow(() -> new CustomException(ErrorInfo.USER_NOT_FOUND));
    }

    public UserDTO getUserWithEmail(String email) throws CustomException {
        return userRepository.findOneById(email)
                .map(this::userToDto)
                .orElseThrow(() -> new CustomException(ErrorInfo.USER_NOT_FOUND));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
    }

    public void createUser(UserDTO userDTO) throws CustomException{

        User user= dtoToUser(userDTO);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<User> userInTheDatabase = userRepository.findOneByEmail(user.getEmail());
        if(userInTheDatabase.isPresent()){
            throw new CustomException(ErrorInfo.MAIL_ALREADY_EXISTS);
        }

        userInTheDatabase = userRepository.findOneByNick(user.getNick());
        if(userInTheDatabase.isPresent()){
            throw new CustomException(ErrorInfo.NICK_ALREADY_EXISTS);
        }
        userRepository.save(user);
    }

    public void updateUser(UserDTO userDTO) throws CustomException{
        Optional<User> retrievedUser = userRepository.findOneByNick(userDTO.getNick());

        retrievedUser.orElseThrow(() -> new CustomException(ErrorInfo.USER_NOT_FOUND));

        retrievedUser.ifPresent(user -> {
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setLevel(userDTO.getLevel());
            user.setLearnedSongs(userDTO.getLearnedSongs());
            user.setSongsLearnedInThisLevel(userDTO.getSongsLearnedInThisLevel());
            userRepository.save(user);
        });
    }

    public UserDTO updateLevel(UserDTO userDTO){
        int userLevel=Integer.parseInt(userDTO.getLevel());
        int songsLearnedInThisLevel=userDTO.getSongsLearnedInThisLevel();
        songsLearnedInThisLevel++;
        if(songsLearnedInThisLevel==3){
            songsLearnedInThisLevel=0;
            userLevel++;
        }
        userDTO.setLevel(Integer.toString(userLevel));
        userDTO.setSongsLearnedInThisLevel(songsLearnedInThisLevel);
        return userDTO;
    }

}