package com.vivekgupta;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            if(args.length == 0) {
                throw new Exception("No file provided");
            }

            Analyzer analyzer = new Analyzer(args[0]);
            System.out.println(analyzer.analyze());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
