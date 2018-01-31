package com.polytech.dsl.ssl.main;

import com.polytech.dsl.ssl.dsl.GroovyDSL;

import java.io.File;

public class GroovyMain {

    public static void main(String[] args) {
        GroovyDSL dsl = new GroovyDSL();

        if(args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing path to the Groovy script file");
        }
    }
}
