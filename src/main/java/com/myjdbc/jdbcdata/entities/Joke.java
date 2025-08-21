package com.myjdbc.jdbcdata.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myjdbc.jdbcdata.enums.JokeType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Joke {
    @JsonProperty("type")
    private JokeType type;
    private String setup;
    private String punchline;
    private int id;

    public static void main(String[] args) {
        System.out.println(JokeType.GENERAL.getValue());
    }

    @Override
    public boolean equals(Object o) {
        return Objects.equals(o, this);
    }

//    @Override
//    public boolean equals(Object o) {
//
//        if (this == o) return true;
//        if (!(o instanceof Joke)) return false;
//        Joke joke = (Joke) o;
//        return getId() == joke.getId() && getType() == joke.getType() && Objects.equals(getSetup(), joke.getSetup()) && Objects.equals(getPunchline(), joke.getPunchline());
//    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getSetup(), getPunchline(), getId());
    }
}
