package com.example.olderman.dungeon;

/**
 * Created by w526_000 on 04.05.2017.
 */
public class Ramecek {
    //data rámu - rám[0] : rohy od levého horního po směru hod. ruč ;rám[1][0] vodorovné ohraničení; rám[2][0] : horizontální ohraničení
    public static char[][][] data = {
            {{'+', '+', '+', '+'}, {'-'}, {'|'}},
            {{'@', '@', '@', '@'}, {'#'}, {'#'}},
            {{'#', '#', '#', '#'}, {'='}, {'|'}},
            {{'=', '=', '=', '='}, {'='}, {' '}},
            {{'!', '!', '!', '!'}, {'-'}, {'!'}},
            {{'■', '■', '■', '■'}, {' '}, {'■'}},
            {{'█', '█', '█', '█'}, {' '}, {'█'}},
            {{'║', '║', '║', '║'}, {' '}, {'║'}},






    };

    public static final int TYP_PLUS = 0;
    public static final int TYP_HASHTAG = 1;
    public static final int TYP_ROVNASE = 2;
    public static final int TYP_ROVNASE_BEZESTRAN = 3;
    public static final int TYP_VYKRICNIK = 4;
    public static final int TYP_CTVEREC = 5;
    public static final int TYP_VEL_CTVEREC = 6;
    public static final int TYP_DVOJTA_CARA = 7;



    private static boolean platne(int typ) {
        return typ > -1 && typ < data.length;
    }

    public static String vytvor(char[][] ram, String text) {
        String[] radek = text.split("\\v"); //rozdeli text na pole po radcich
        int nejdelsiRadek = 0;
        for (String item : radek) { //najdu delku nejdelsiho radku
            if (item.length() > nejdelsiRadek) nejdelsiRadek = item.length();
        }
        for (int i = 0; i < radek.length; i++) {
            while (radek[i].length() < nejdelsiRadek) {
                radek[i] += " ";
            }
        }
        StringBuilder sb = new StringBuilder();
//prvni radek
        sb.append(ram[0][0]);
        for (int i = 0; i < nejdelsiRadek + 2; i++) {
            sb.append(ram[1][0]);
        }
        sb.append(ram[0][1] + "\n");
//ostatni radky
        for (int i = 0; i < radek.length; i++) {
            sb.append(ram[2][0] + " ");
            sb.append(radek[i]);
            sb.append(" " + ram[2][0] + "\n");
        }
//posledni radek
        sb.append(ram[0][3]);
        for (int i = 0; i < nejdelsiRadek + 2; i++) {
            sb.append(ram[1][0]);
        }
        sb.append(ram[0][2]);

        return sb.toString();
    }

    public static String vytvor(int typ, String text) {
        return vytvor(data[typ], text);
        /*if (!platne(typ)){
            throw new IllegalArgumentException(typ + " is not a valid frame type");
        }
        String[] radek = text.split("\\v"); //rozdeli text na pole po radcich
        int nejdelsiRadek = 0;
        for (String item : radek) { //najdu delku nejdelsiho radku
            if (item.length() > nejdelsiRadek) nejdelsiRadek = item.length();
        }
        for (int i = 0;i<radek.length;i++){
            while (radek[i].length()<nejdelsiRadek ){
                radek[i] += " ";
            }
        }
        StringBuilder sb = new StringBuilder();
        char[][] ram = data[typ]; //skopiruji si vybrany ram
//prvni radek
        sb.append(ram[0][0]);
        for (int i = 0; i < nejdelsiRadek + 2; i++) {
            sb.append(ram[1][0]);
        }
        sb.append(ram[0][1] + "\n");
//ostatni radky
        for (int i = 0; i < radek.length; i++) {
            sb.append(ram[2][0] + " ");
            sb.append(radek[i]);
            sb.append(" " + ram[2][0] + "\n");
        }
//posledni radek
        sb.append(ram[0][2]);
        for (int i = 0; i < nejdelsiRadek + 2; i++) {
            sb.append(ram[1][0]);
        }
        sb.append(ram[0][3]);

        return sb.toString();*/
    }

}
