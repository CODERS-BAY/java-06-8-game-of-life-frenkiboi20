package com.codersbay;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int amountGen = 0;

        System.out.print("How many generations: ");
        amountGen = sc.nextInt();

        System.out.println("\nSTART generating...");

        int[][] exploder = new int[20][20];
        for (int i = 0; i < exploder.length; i++) {
            for (int j = 0; j < exploder.length; j++) {
                exploder[i][j] = 0;

                exploder[7][7] = 1;
                exploder[7][9] = 1;
                exploder[7][11] = 1;

                exploder[8][7] = 1;
                exploder[9][7] = 1;
                exploder[10][7] = 1;
                exploder[11][7] = 1;

                exploder[8][11] = 1;
                exploder[9][11] = 1;
                exploder[10][11] = 1;
                exploder[11][11] = 1;

                exploder[11][9] = 1;

                if (exploder[i][j] == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }

        int[][] nextGen = newGen(exploder);

        for (int i = 1; i < amountGen; i++) {

            nextGen = newGen(nextGen);
            Thread.sleep(1000);
        }

        System.out.println("Generation building completed.");

    }

    public static int[][] newGen(int field[][]) {

        int[][] gen = new int[field.length][field.length];

        for (int x = 1; x < gen.length - 1; x++) {
            for (int y = 1; y < gen.length - 1; y++) {

                int neighbor = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        neighbor += field[x + i][y + j];
                    }
                }

                neighbor -= field[x][y];

                // Regeln
                // Wenn 1 Zelle lebt, und weniger als 2 lives (1) hat, stirbt sie an Einsamkeit
                if ((field[x][y] == 1) && (neighbor < 2)) {
                    gen[x][y] = 0;
                }
                // Wenn 1 Zelle lebt, und mehr als 3 lives (1) hat, stirbt sie an Überbevölkerung
                else if ((field[x][y] == 1) && (neighbor > 3)) {
                    gen[x][y] = 0;
                }
                // Wenn Zelle tot (0) ist, und genau 3 lives (1) hat, wird sie wiedergeboren
                else if ((field[x][y] == 0) && (neighbor == 3)) {
                    gen[x][y] = 1;
                }
                // Ansonsten übernimm den Zustand der vorherigen Generation
                else {
                    gen[x][y] = field[x][y];
                }
            }
        }

        System.out.println("Next Generation");

        for (int i = 0; i < gen.length; i++) {
            for (int j = 0; j < gen.length; j++) {
                if (gen[i][j] == 0)
                    System.out.print(" . ");
                else
                    System.out.print(" * ");
            }
            System.out.println();
        }

        return gen;

    }
}





