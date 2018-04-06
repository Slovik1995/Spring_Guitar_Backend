package com.guitar.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder(builderMethodName = "hiddenUserBuilder")
@NoArgsConstructor
@Document(collection = "users")
public class MongoUser {

    @Id
    private String id;


    @Indexed
    private String ic;


    private String name;


    private int age;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;

    public static MongoUserBuilder builder() {
        return hiddenUserBuilder()
                .id(UUID.randomUUID().toString());
    }

    public MongoUser(String ic, String name, int age,Date createdDate) {
        this.id =UUID.randomUUID().toString();
        this.ic = ic;
        this.name=name;
        this.age=age;
        this.createdDate=createdDate;
    }


}
