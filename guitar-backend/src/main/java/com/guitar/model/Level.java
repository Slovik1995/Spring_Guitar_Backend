package com.guitar.model;

import com.guitar.web.dto.StringDTO;

public class Level {

    private final String level1Chords="A7|Adur|Amoll";
    private final String level2Chords="Amoll7|C7|C7+";
    private final String level3Chords="Cdur|D7|Ddur";
    private final String level4Chords="Dmoll|E7|Edur";
    private final String level5Chords="Emoll|Emoll7|F7+";

    public StringDTO getLevel(String level){
        String json="null";
        switch(level){
            case "1":
                json="{\"level\":1,\"thisLevelChords\":"+level1Chords+",\"previousLevelsChords\":null}";
                break;
            case "2":
                json="{\"level\":2,\"thisLevelChords\":"+level2Chords+",\"previousLevelsChords\":"+level1Chords+"}";
                break;
            case "3":
                json="{\"level\":3,\"thisLevelChords\":"+level3Chords+",\"previousLevelsChords\":"+level1Chords+"|"+level2Chords+"}";
                break;
            case "4":
                json="{\"level\":4,\"thisLevelChords\":"+level4Chords+",\"previousLevelsChords\":"+level1Chords+"|"+level2Chords+"|"+level3Chords+"}";
                break;
            case "5":
                json="{\"level\":5,\"thisLevelChords\":"+level5Chords+",\"previousLevelsChords\":"+level1Chords+"|"+level2Chords+"|"+level3Chords+"|"+level4Chords+"}";
                break;
        }
        System.out.println("|||||||||||||||||||  "+json);
        return new StringDTO(json);
    }
}
