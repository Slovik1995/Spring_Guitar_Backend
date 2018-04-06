package com.guitar;

import com.guitar.model.Level;
import com.guitar.security.SecurityUtil;
import com.guitar.service.SongService;
import com.guitar.service.UserService;
import com.guitar.web.CustomException;
import com.guitar.web.dto.SongDTO;
import com.guitar.web.dto.StringDTO;
import com.guitar.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class Controller {

    private final UserService userService;
    private final SongService songService;

    @Autowired
    public Controller(UserService userService, SongService songService) {
        this.userService = userService;
        this.songService = songService;
    }


    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/getLearnedSongs",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handlelearnedSongs(HttpServletRequest request, HttpServletResponse response) {
        String user="";
        try {
            String message = request.getReader().readLine();
            user=message.split(",")[0].split(":")[1].split("}")[0];
            user=user.substring(1);
            user=user.substring(0,user.length()-1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        UserDTO userDTO = null;
        try {
            userDTO = userService.getUserWithName(user);
        } catch (CustomException e) {
           e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(new StringDTO(userDTO.getLearnedSongs().toString()), HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/checkSong",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleCheck(HttpServletRequest request, HttpServletResponse response) {
        String user="";
        String songId="";
        try {
            String message = request.getReader().readLine();
            user=message.split(",")[0].split(":")[1].split("}")[0];
            user=user.substring(1);
            user=user.substring(0,user.length()-1);
            songId=message.split(",")[1].split(":")[1].split("}")[0];
            songId=songId.substring(1);
            songId=songId.substring(0,songId.length()-1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        UserDTO userDTO = null;
        try {
            userDTO = userService.getUserWithName(user);
        } catch (CustomException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        //System.out.println(songService.getSongWithId(songId));
        userDTO.getLearnedSongs().add(new String(songId));
        userDTO=userService.updateLevel(userDTO);
        System.out.println(userDTO);
        try {
            userService.updateUser(userDTO);
        } catch (CustomException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(new StringDTO(userDTO.getLearnedSongs().toString()), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/song/{id}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleSong(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="id") String id) {

        SongDTO song = null;
        try {
            song = songService.getSongWithId(id);
        } catch (CustomException e) {
     //       e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/photo1/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getUserAvatar(@PathVariable("id") String userId) throws IOException {
        URL url = this.getClass().getResource("/photo.jpg");
        System.out.println("mypath: "+url.toString());
        File file = new File(url.getFile());
        if (!file.isFile()) {
            System.out.println("dupa");
           // log.error("Cannot open file: {} is not a file", file.getPath());
            throw new IllegalArgumentException("Resource is not a file");
        }

        System.out.println("przed avatar");
        FileSystemResource avatar = new FileSystemResource(file);
        System.out.println("po avatar");
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(Files.probeContentType(Paths.get(avatar.getPath()))))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + avatar.getFilename() + "\"")
                .body(avatar);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/loggedUser", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleLoggedUser() {
        SecurityUtil securityUtil = new SecurityUtil();
        String userName = securityUtil.getCurrentUser();
        System.out.println("userName: "+userName);
        UserDTO userDTO = null;
        try {
            userDTO = userService.getUserWithName(userName);
        } catch (CustomException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        userDTO.setPassword("");
        System.out.println("LLLLL: "+userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/learnChords/{level}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleChords(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="level") String level) {
        System.out.println("makarena");
         StringDTO stringDTO = new Level().getLevel(level);
         return new ResponseEntity<>(stringDTO, HttpStatus.OK);
    }



    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/player", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handlePlayer() {
        SongDTO song = null;
        try {
            song = songService.getSongWithTitleAndAuthor("So shy", "Joan");
        } catch (CustomException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/hello", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handle() {
        System.out.println("AAA");
        SongDTO song = null;
        try {
            song = songService.getSongWithTitleAndAuthor("So shy", "Joan");
        } catch (CustomException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
        //String str="HELLO BOYS";
        //return "{\"success\":1}";
        //return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/songslist", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listSongs() {
        System.out.println("SSSSS");
        List<SongDTO> songs = songService.getAllSongsWithoutLyrics();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/songslist/{level}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listSongs(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="level") String level) {
        System.out.println("SSSSSongslistlevel");
        List<SongDTO> songs = songService.getAllSongsWithoutLyrics(level);
        for(int i=1; i<Integer.parseInt(level);i++) {
            songs.addAll(songService.getAllSongsWithoutLyrics(Integer.toString(i)));
        }
            return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/doLogging", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleLogin() {
        System.out.println("XDXDXDXDXDXDXD====================XDXD");
        List<SongDTO> songs = songService.getAllSongsWithoutLyrics();
        return new ResponseEntity<>(songs, HttpStatus.OK);

    }



    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value="/play/{id}",method=RequestMethod.GET)
    public void playAudio(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="id") String id){
       // if(!id.contains("adventures"))
       //     return;
        String nameOfTheSong = "/"+id+".mp3";
        System.out.println(nameOfTheSong);
        URL songUrl = this.getClass().getResource(nameOfTheSong);


        File songFile = new File(songUrl.getFile());
        FileInputStream fileIS;
        byte[] songBuffer=null;
        try {
            fileIS = new FileInputStream(songFile);
            songBuffer= new byte[fileIS.available()];
            fileIS.read(songBuffer);
            fileIS.close();
        } catch (FileNotFoundException e) {
            // TODO
            e.printStackTrace();
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        System.out.println("przed setContentType");
        response.setContentType("audio/mp3");
        System.out.println("po setContentType");
        try{
            System.out.println("przed getoutputstream");
            response.getOutputStream().write(songBuffer);
            System.out.println("po getoutputstream");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /*
    @ResponseBody
    @RequestMapping(value="/greeting",method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> greeting() {
        System.out.println("AAAAAAA");
    LinkedList<String> eventDTOs = new LinkedList<String>();
    eventDTOs.add("Ala");
        return  new ResponseEntity<>(eventDTOs, HttpStatus.OK);
    }

    @RequestMapping(value="/hello2")
    public ModelAndView doit() {
        ModelAndView model = new ModelAndView("AdmissionForm");
        model.addObject("name", "pietrek");
        System.out.println("AAAAAAA");
        return model;
    }
*/

}
