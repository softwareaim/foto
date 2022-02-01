package com.foto.PruebaFoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class PruebaFotoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PruebaFotoApplication.class, args);

    }
}


