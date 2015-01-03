package com.epam.star;


import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    static class MyThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println("MyThread крутится !");
            }
        }
    }

    public static void main(String[] args) throws SQLException, ParseException {
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++){
//                    System.out.println("Thread крутится !");
//                }
//            }
//        });
//        MyThread myThread = new MyThread();
//
//        try {
//            thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        thread.start();
//        myThread.run();

    }
}
