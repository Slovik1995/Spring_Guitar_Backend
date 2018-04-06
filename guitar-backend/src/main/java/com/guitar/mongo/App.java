package com.guitar.mongo;

import com.guitar.model.Song;
import com.guitar.service.SongService;
import com.guitar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class App {
    UserService us;
    SongService songService;

    @Autowired
    public App(UserService us,SongService songService) {
        this.us = us;
        this.songService=songService;
    }

    public void go(){


        Song s1 = Song.builder()
               // .id("1") ///bez tego
                .title("So shy")
                .author("Joan")
                .strimming("UDUUDU")
                .chords("0.001*A7|3*Adur|6*Amoll|9*Amoll7|12*C7|18*C7+|25*Cdur|")
                .level("1")
                .necessaryChords("A,B,C,D")
                .songNameInTheDatabase("soshy")
                .lyrics("You don’t stand out from the crowd\n" +
                        "You always have an average smile\n" +
                        "\n" +
                        "Your mind is fine\n" +
                        "\n" +
                        "I wanna cut your stress\n" +
                        "I wanna cut your doubts\n" +
                        "\n" +
                        "Be brave man\n" +
                        "\n" +
                        "Your eyes,\n" +
                        "They always see the ground\n" +
                        "And the wind,\n" +
                        "Always blows your hair apart\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "But I always catch your stare\n" +
                        "In a moment when you turn\n" +
                        "\n" +
                        "Ooo, I’m watching your first daring step, watching more of these daring moves\n" +
                        "\n" +
                        "Ooo, I’m watching your first daring step, watching more of these daring moves\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Now, all the world knows your guise\n" +
                        "And everyone needs your gaze\n" +
                        "\n" +
                        "They love your courage\n" +
                        "\n" +
                        "And you’re standing at crossroads\n" +
                        "And you must choose the one\n" +
                        "\n" +
                        "It's your time\n" +
                        "\n" +
                        "The one is like you used to be\n" +
                        "The new is like the dream\n" +
                        "\n" +
                        "So, what do you think?\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Both are messing with your head\n" +
                        "There ain’t a proper way\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Ooo, I’m watching you giving up, watching more of these painful moves\n" +
                        "\n" +
                        "Ooo, I’m watching you giving up, watching more of these painful moves ")
                .build();

        Song s2 = Song.builder()
              //  .id("2")
                .title("Living On The Island")
                .author("Julia Pietrucha")
                .strimming("UDUDD")
                .chords("0.001*D7|3*Ddur|6*Dmoll|9*E7|12*Edur|18*Emoll|25*Emoll7|")
                .necessaryChords("A,c,E,H")
                .level("1")
                .songNameInTheDatabase("livingontheisland")
                .lyrics("Living on the island\n" +
                        "Where the sun shines bright\n" +
                        "And the people smile\n" +
                        "That's what I need\n" +
                        "So have some sympathy\n" +
                        "For me\n" +
                        "Why don't we move\n" +
                        "But how are we gonna grow without our roots\n" +
                        "\n" +
                        "I couldn't find the place where I belong\n" +
                        "for so long\n" +
                        "So I decided to just build a home with you\n" +
                        "and I'm happy here\n" +
                        "every time I think of\n" +
                        "\n" +
                        "Living on the island\n" +
                        "Where the sun shines bright\n" +
                        "And the people smile\n" +
                        "That's what I need\n" +
                        "So have some sympathy\n" +
                        "My dear\n" +
                        "Why don't we move\n" +
                        "But how are we gonna grow without our roots ")
                .build();

        Song s3 = Song.builder()
                .id("3")
                .title("What do I know")
                .author("Ed Sheeran")
                .strimming("DDUDUD")
                .chords("0.001*F7+|3*G7|6*Gdur|9*H7|12*Adur|18*C7|25*E7|")
                .necessaryChords("C,h,D,e")
                .level("1")
                .songNameInTheDatabase("whatdoiknow")
                .lyrics("[Verse 1]\n" +
                        "Ain't got a soapbox I can stand upon\n" +
                        "But God gave me a stage, a guitar and a song\n" +
                        "My daddy told me, \"Son, don't you get involved in\n" +
                        "Politics, religions or other people's quotes\"\n" +
                        "\n" +
                        "[Bridge 1]\n" +
                        "I'll paint the picture, let me set the scene\n" +
                        "I know when I have children they will know what it means\n" +
                        "And I pass on these things my family's given to me\n" +
                        "Just love and understanding, positivity\n" +
                        "\n" +
                        "[Chorus]\n" +
                        "We could change this whole world with a piano\n" +
                        "Add a bass, some guitar, grab a beat\n" +
                        "And away we go\n" +
                        "I'm just a boy with a one-man show\n" +
                        "No university, no degree\n" +
                        "But lord knows everybody's talking 'bout exponential growth\n" +
                        "And the stock market crashing in their portfolios\n" +
                        "While I'll be sitting here with a song I wrote\n" +
                        "Sing, love could change the world in a moment\n" +
                        "But what do I know?\n" +
                        "Love can change the world in a moment\n" +
                        "But what do I know?\n" +
                        "Love can change the world in a moment\n" +
                        "\n" +
                        "[Verse 2]\n" +
                        "The revolution's coming, it's a minute away\n" +
                        "I saw people marching in the streets today\n" +
                        "You know we are made up of love and hate\n" +
                        "But both of them are balanced on a razor blade\n" +
                        "\n" +
                        "[Bridge 2]\n" +
                        "I'll paint the picture, let me set the scene\n" +
                        "I know, I'm all for people following their dreams\n" +
                        "Just re-remember life is more than fittin' in your jeans\n" +
                        "It's love and understanding, positivity\n" +
                        "\n" +
                        "[Chorus]\n" +
                        "We could change this whole world with a piano\n" +
                        "Add a bass, some guitar, grab a beat\n" +
                        "And away we go\n" +
                        "I'm just a boy with a one-man show\n" +
                        "No university, no degree\n" +
                        "But lord knows everybody's talking 'bout exponential growth\n" +
                        "And the stock market crashing in their portfolios\n" +
                        "While I'll be sitting here with a song I wrote\n" +
                        "Sing, love could change the world in a moment\n" +
                        "But what do I know?\n" +
                        "Love can change the world in a moment\n" +
                        "But what do I know?\n" +
                        "Love can change the world in a moment\n" +
                        "\n" +
                        "\n" +
                        "[Bridge 3]\n" +
                        "I'll paint the picture, let me set the scene\n" +
                        "You know, the future's in the hands of you and me\n" +
                        "So let's all get together, we can alle be free\n" +
                        "Spread love and understanding, positivity\n" +
                        "\n" +
                        "[Chorus]\n" +
                        "We could change this whole world with a piano\n" +
                        "Add a bass, some guitar, grab a beat\n" +
                        "And away we go\n" +
                        "I'm just a boy with a one-man show\n" +
                        "No university, no degree\n" +
                        "But lord knows everybody's talking 'bout exponential growth\n" +
                        "And the stock market crashing in their portfolios\n" +
                        "While I'll be sitting here with a song I wrote\n" +
                        "Sing, love could change the world in a moment\n" +
                        "But what do I know?\n" +
                        "Love can change the world in a moment\n" +
                        "But what do I know?\n" +
                        "Love can change the world in a moment")
                .build();

        Song s4 = Song.builder()
           //     .id("4")
                .title("Throw the fear")
                .author("Tom Rosenthal")
                .strimming("DDUD")
                .chords("0.001*C|3*A|6*A|9*C|12*D|18*D|25*B|")
                .necessaryChords("A,c,E,H")
                .level("2")
                .songNameInTheDatabase("throwthefear")
                .lyrics("I'll be the one at the foot of the mountain\n" +
                        "Shouting upwards 'will you remember my name?'\n" +
                        "Attended your birth on the dark of a Tuesday\n" +
                        "I'll tell you something I'll never be quite the same\n" +
                        "Welcome to the world it's a pretty funky mess, love\n" +
                        "Where boys think they are gods but they are nothing of the sort\n" +
                        "So rise and fight and punch till their ears hear the truth, love\n" +
                        "Or get your beds together and just build a massive fort\n" +
                        "\n" +
                        "Throw the fear\n" +
                        "Throw the fear\n" +
                        "Let the day become the year\n" +
                        "You're alive\n" +
                        "I'm alive\n" +
                        "We're a-la-la-la-live\n" +
                        "If you go\n" +
                        "If you go\n" +
                        "Take all the heart with you\n" +
                        "History\n" +
                        "It's all gone\n" +
                        "Make the new\n" +
                        "Make the new\n" +
                        "\n" +
                        "under your skin is an unknown power\n" +
                        "There's not a man who can match a mighty dame\n" +
                        "You won't solve your problems in the minutes or the hours\n" +
                        "Build your own castle and build it in your name\n" +
                        "The importance of just keeping going, perserverance will be your greatest friend\n" +
                        "Don't die before your dead, keep watering the plants, love\n" +
                        "In the end the end is just the end\n" +
                        "\n" +
                        "Throw the fear\n" +
                        "Throw the fear\n" +
                        "Let the day become the year\n" +
                        "You're alive\n" +
                        "I'm alive\n" +
                        "We're a-la-la-la-live\n" +
                        "If you go\n" +
                        "If you go\n" +
                        "Take all the heart with you\n" +
                        "History\n" +
                        "It's all gone\n" +
                        "Make the new\n" +
                        "Make the new\n" +
                        "\n" +
                        "I'll be the one at the base of the tall tree\n" +
                        "Loving you for all my lonely life\n" +
                        "A burst of the light in the heart of the darkness\n" +
                        "Take it on, enjoy the big surprise\n" +
                        "\n" +
                        "Throw the fear\n" +
                        "Throw the fear\n" +
                        "Let the day become the year\n" +
                        "You're alive\n" +
                        "I'm alive\n" +
                        "We're a-la-la-la-live\n" +
                        "If you go\n" +
                        "If you go\n" +
                        "Take all the heart with you\n" +
                        "History\n" +
                        "It's all gone\n" +
                        "Make the new\n" +
                        "Make the new ")
                .build();

        Song s5 = Song.builder()
                .id("5")
                .title("Touching heaven")
                .author("Johnnyswim")
                .strimming("UUUD")
                .chords("0.001*B|3*A|6*B|9*C|12*D|18*A|25*B|")
                .necessaryChords("A,c,E,H")
                .level("2")
                .songNameInTheDatabase("touchingheaven")
                .lyrics("All I see are angels\n" +
                        "All these streets are gold\n" +
                        "That lead to you\n" +
                        "If touching love is touching God\n" +
                        "No wonder I'm in heaven, when I'm holding you\n" +
                        "Simple as a song, a melody repeating in this heart\n" +
                        "I don't know when the chorus came\n" +
                        "Where I heard it first, or when it started\n" +
                        "\n" +
                        "I won't be holding my breath for chariots\n" +
                        "I'm not just waiting for skies to part\n" +
                        "You've been my glimpse of Kingdom right from the start\n" +
                        "And you got me touching heaven, ooo\n" +
                        "Got me touching heaven, ooo\n" +
                        "\n" +
                        "So tell me what you're dreaming, tell me what it is\n" +
                        "Your dream come true\n" +
                        "I don't need a map to find a hidden treasure\n" +
                        "There, ain't no hiding you\n" +
                        "\n" +
                        "I won't be holding my breath for chariots\n" +
                        "I'm not just waiting for skies to part\n" +
                        "You've been my glimpse of Kingdom right from the start\n" +
                        "And you got me touching heaven, ooo\n" +
                        "Got me touching heaven, ooo\n" +
                        "Got me touching heaven\n" +
                        "\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "Yes you are\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "Yes you are\n" +
                        "Hallelujah\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "Yeah\n" +
                        "And you got me touching heaven, ooo\n" +
                        "You're my hallelujah, ooo\n" +
                        "You got me\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "Yes you are\n" +
                        "Hallelujah, hallelujah\n" +
                        "\n" +
                        "If touching love is touching God\n" +
                        "No wonder I'm in heaven when I'm holding you\n" +
                        "\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "You're my hallelujah\n" +
                        "Yes you are\n" +
                        "\n" +
                        "And you got me touching heaven")
                .build();


        Song s6 = Song.builder()
                .id("6")
                .title("Can't help falling in love")
                .author("Haley Reinhart")
                .strimming("UDUDD")
                .chords("0.001*A|3*B|6*C|9*C|12*D|18*A|25*C|")
                .necessaryChords("A,c,E,H")
                .level("2")
                .songNameInTheDatabase("canthelpfallinginlove")
                .lyrics("Wise men say only fools rush in\n" +
                        "but I can't help falling in love with you\n" +
                        "Shall I stay\n" +
                        "would it be a sin\n" +
                        "If I can't help falling in love with you\n" +
                        "\n" +
                        "Like a river flows surely to the sea\n" +
                        "Darling so it goes\n" +
                        "some things are meant to be\n" +
                        "take my hand, take my whole life too\n" +
                        "for I can't help falling in love with you\n" +
                        "\n" +
                        "Like a river flows surely to the sea\n" +
                        "Darling so it goes\n" +
                        "some things are meant to be\n" +
                        "take my hand, take my whole life too\n" +
                        "for I can't help falling in love with you\n" +
                        "for I can't help falling in love with you")
                .build();


        Song s7 = Song.builder()
                .id("7")
                .title("Is This Love")
                .author("The Schwings Band")
                .strimming("DUDUDD")
                .chords("0.001*C|3*A|6*B|9*C|12*D|18*A|25*C|")
                .necessaryChords("A,c,E,H")
                .level("3")
                .songNameInTheDatabase("isthislove")
                .lyrics("I wanna love you and treat you right\n" +
                        "I wanna love you, every day and every night\n" +
                        "We'll be together with a roof right over our heads\n" +
                        "We'll share the shelter of my single bed\n" +
                        "We'll share the same room, oh, Jah provide the bread\n" +
                        "\n" +
                        "Is this love, is this love, is this love\n" +
                        "Is this love that I'm feeling\n" +
                        "Is this love, is this love, is this love\n" +
                        "Is this love that I'm feeling\n" +
                        "\n" +
                        "I wanna know, wanna know, wanna know now\n" +
                        "I got to know, got to know, got to know now\n" +
                        "\n" +
                        "I'm willing and able\n" +
                        "So I throw my cards on your table\n" +
                        "I wanna love you\n" +
                        "I wanna love you and treat\n" +
                        "love and treat you right\n" +
                        "I wanna love you every day and every night\n" +
                        "We'll be together with a roof right over our heads\n" +
                        "We'll share the shelter of my single bed\n" +
                        "We'll share the same room,oh Jah provide the bread\n" +
                        "\n" +
                        "Is this love, is this love, is this love\n" +
                        "Is this love that I'm feeling\n" +
                        "Is this love, is this love, is this love\n" +
                        "Is this love that I'm feeling\n" +
                        "Oh yes I know, yes I know, yes I know now\n" +
                        "Oh yes I know, yes I know, yes I know now\n" +
                        "\n" +
                        "I'm willing and able\n" +
                        "So I throw my cards on your table\n" +
                        "See I wanna love you,\n" +
                        "I wanna love you and treat ya\n" +
                        "love and treat you right\n" +
                        "I wanna love you every day and every night\n" +
                        "We'll be together with a roof right over our heads\n" +
                        "We'll share the shelter of my single bed\n" +
                        "We'll share the same room, Jah provide the bread\n" +
                        "We'll share the shelter of my single bed ")
                .build();


                ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation =
                (MongoOperations) context.getBean("mongoTemplate");


        Query searchQuery2 = new Query(Criteria.where("id").exists(true));
        mongoOperation.remove(searchQuery2, Song.class);

        mongoOperation.save(s1, "songs");
        mongoOperation.save(s2, "songs");
        mongoOperation.save(s3, "songs");
        mongoOperation.save(s4, "songs");
        mongoOperation.save(s5, "songs");
        mongoOperation.save(s6, "songs");
        mongoOperation.save(s7, "songs");


        //  Query findSongQuery = new Query();
        //  findSongQuery.addCriteria(Criteria.where("title").is("So shy"));
        //  Song song1 = mongoOperation.findOne(findSongQuery, Song.class, "songs");
        //  findSongQuery.addCriteria(Criteria.where("title").is("So shy").where("author").is("Joan"));
        //  Song song1 = mongoOperation.findOne(findSongQuery, Song.class, "songs");
        //  System.out.println(song1);

        //   Song son = songService.dtoToSong(songService.getSongWithTitleAndAuthor("So shy","Joan"));
        //  System.out.println(son);



        //   List<SongDTO> son = songService.getAllSongsWithChords("eBcA");
        //     System.out.println(son.size());


        //   Query searchQuery2 = new Query(Criteria.where("id").exists(true));
        //   mongoOperation.remove(searchQuery2, Song.class);



        /*
        User u1 = User.builder().name("Piotr").surname("Boski").nick("slovik").email("piotr@gmail.com").password("123456").build();
        User u2 = User.builder().name("Michal").surname("Kowalski").nick("misiek").email("michal@gmail.com").password("234567").build();
        User u3 = User.builder().name("Artek").surname("Nowak").nick("aris").email("artek@gmail.com").password("345678").build();
        User u4 = User.builder().name("Anna").surname("Wanna").nick("keri").email("anna@gmail.com").password("456789").build();

        User u5 = User.builder().name("Michal").surname("Kowalski").nick("misiek").email("misiura@gmail.com").password("abcdefg").build();

        us.createUser(us.userToDto(u1));
        us.createUser(us.userToDto(u2));
        us.createUser(us.userToDto(u3));
        us.createUser(us.userToDto(u4));

        us.updateUser(us.userToDto(u5));

        System.out.println(us.getAllUsers());
*/

        /*
        System.out.println("AAAAA");
        if(us==null)
            System.out.println("no nie");
        us.createUser();
        System.out.println("BBBBBB");
        Optional<com.guitar.model.User> user = us.getUserWithName("Karol");
        if(user.isPresent()){
            System.out.println(user);
        }
        System.out.println("CCCCCC");
*/
    }


    /*

        // For Annotation
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation =
                (MongoOperations) ctx.getBean("mongoTemplate");

        // Case1 - insert a user, put "tableA" as collection name
        System.out.println("Case 1...");
        User userA = new User("1000", "apple", 54, new Date());
        mongoOperation.save(userA, "tableA");

        // find
        Query findUserQuery = new Query();
        findUserQuery.addCriteria(Criteria.where("ic").is("1000"));
        User userA1 = mongoOperation.findOne(findUserQuery, User.class, "tableA");
        System.out.println(userA1);

        // Case2 - insert a user, put entity as collection name
        System.out.println("Case 2...");
        User userB = new User("2000", "orange", 64, new Date());
        mongoOperation.save(userB);

        // find
        User userB1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(64)), User.class);
        System.out.println(userB1);

        // Case3 - insert a list of users
        System.out.println("Case 3...");
        User userC = new User("3000", "metallica", 34, new Date());
        User userD = new User("4000", "metallica", 34, new Date());
        User userE = new User("5000", "metallica", 34, new Date());
        List<User> userList = new ArrayList<User>();
        userList.add(userC);
        userList.add(userD);
        userList.add(userE);
        mongoOperation.insert(userList, User.class);

        // find
        List<User> users = mongoOperation.find(
                new Query(Criteria.where("name").is("metallica")),
                User.class);

        for (User temp : users) {
            System.out.println(temp);
        }

        //save vs insert
        System.out.println("Case 4...");
        User userD1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(64)), User.class);
        userD1.setName("new name");
        userD1.setAge(100);

        //E11000 duplicate key error index, _id existed
        //mongoOperation.insert(userD1);
        mongoOperation.save(userD1);
        User userE1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(100)), User.class);
        System.out.println(userE1);
    */


//====================================================================================
/*
    UserService us;

    @Autowired
    public App(UserService us) {
        this.us = us;
    }

    public void go(){

        User u1 = User.builder().name("Piotr").surname("Boski").nick("slovik").email("piotr@gmail.com").password("123456").build();
        User u2 = User.builder().name("Michal").surname("Kowalski").nick("misiek").email("michal@gmail.com").password("234567").build();
        User u3 = User.builder().name("Artek").surname("Nowak").nick("aris").email("artek@gmail.com").password("345678").build();
        User u4 = User.builder().name("Anna").surname("Wanna").nick("keri").email("anna@gmail.com").password("456789").build();

        User u5 = User.builder().name("Michal").surname("Kowalski").nick("misiek").email("misiura@gmail.com").password("abcdefg").build();

        us.createUser(us.userToDto(u1));
        us.createUser(us.userToDto(u2));
        us.createUser(us.userToDto(u3));
        us.createUser(us.userToDto(u4));

        us.updateUser(us.userToDto(u5));

        System.out.println(us.getAllUsers());



        System.out.println("AAAAA");
        if(us==null)
            System.out.println("no nie");
        us.createUser();
        System.out.println("BBBBBB");
        Optional<com.guitar.model.User> user = us.getUserWithName("Karol");
        if(user.isPresent()){
            System.out.println(user);
        }
        System.out.println("CCCCCC");

    }




        // For Annotation
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation =
                (MongoOperations) ctx.getBean("mongoTemplate");

        // Case1 - insert a user, put "tableA" as collection name
        System.out.println("Case 1...");
        User userA = new User("1000", "apple", 54, new Date());
        mongoOperation.save(userA, "tableA");

        // find
        Query findUserQuery = new Query();
        findUserQuery.addCriteria(Criteria.where("ic").is("1000"));
        User userA1 = mongoOperation.findOne(findUserQuery, User.class, "tableA");
        System.out.println(userA1);

        // Case2 - insert a user, put entity as collection name
        System.out.println("Case 2...");
        User userB = new User("2000", "orange", 64, new Date());
        mongoOperation.save(userB);

        // find
        User userB1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(64)), User.class);
        System.out.println(userB1);

        // Case3 - insert a list of users
        System.out.println("Case 3...");
        User userC = new User("3000", "metallica", 34, new Date());
        User userD = new User("4000", "metallica", 34, new Date());
        User userE = new User("5000", "metallica", 34, new Date());
        List<User> userList = new ArrayList<User>();
        userList.add(userC);
        userList.add(userD);
        userList.add(userE);
        mongoOperation.insert(userList, User.class);

        // find
        List<User> users = mongoOperation.find(
                new Query(Criteria.where("name").is("metallica")),
                User.class);

        for (User temp : users) {
            System.out.println(temp);
        }

        //save vs insert
        System.out.println("Case 4...");
        User userD1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(64)), User.class);
        userD1.setName("new name");
        userD1.setAge(100);

        //E11000 duplicate key error index, _id existed
        //mongoOperation.insert(userD1);
        mongoOperation.save(userD1);
        User userE1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(100)), User.class);
        System.out.println(userE1);
    */

}

