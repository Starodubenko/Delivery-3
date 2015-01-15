package com.epam.star;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Random;

public class Main {

    private static Random rnd = new Random();


    public static void main(String[] args) throws SQLException, ParseException {

        System.out.println(rnd.nextInt(999999));

    }
}
