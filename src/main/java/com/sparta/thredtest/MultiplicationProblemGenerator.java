package com.sparta.thredtest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MultiplicationProblemGenerator {
    public static void generateProblems(String filePath, int numProblems) throws IOException {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < numProblems; i++) {
                int a = random.nextInt(9) + 1;
                int b = random.nextInt(9) + 1;
                writer.write(a + " x " + b + " = ?\n");
            }
        }
    }
}
