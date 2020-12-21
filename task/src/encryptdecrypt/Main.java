package encryptdecrypt;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = args.length;
//        String[] fake = {"-mode", "enc",
//                "-key", "5",
//                "-in", "in.txt",
//                "-out", "output.txt"};

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < length; i = i + 2) {
            map.put(args[i], args[i + 1]);
        }

        String command = map.get("-mode") != null ? map.get("-mode") : "enc";
        int key = map.get("-key") != null ? Integer.parseInt(map.get("-key")) : 0;


        String data = "";

        if (map.get("-in") != null && map.get("-data") == null) {
            data = getDataFromFile(map.get("-in"));
        } else if (map.get("-data") != null) {
            data = getDataFromScanner();
        }

        String output = "";

        if (command.equals("enc")) {
            output = encrypt(data, key);
        } else if (command.equals("dec")) {
            output = decrypt(data, key);
        }

        if (map.get("-out") == null) {
            System.out.println(output);
        } else {
            exportData(output, map.get("-out"));
        }
    }

    private static void exportData(String data, String s) {
        try (FileWriter fileWriter = new FileWriter(s)) {
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getDataFromScanner() {
        String data = scanner.nextLine();
        System.out.println(data);
        return data;

    }

    private static String getDataFromFile(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader reader = new FileReader(s);
            int charAsNumber = reader.read();
            while (charAsNumber != -1) {
                char character = (char) charAsNumber;
                stringBuilder.append(character);
                System.out.print(character);
                charAsNumber = reader.read();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static String encrypt(String input, int number) {

        StringBuilder stringBuilder = new StringBuilder();

        char[] inputCharArray = input.toCharArray();

        for (int i = 0; i < inputCharArray.length; i++) {
            char newCharacter = (char) (input.charAt(i) + number);

            stringBuilder.append(newCharacter);

        }

        return stringBuilder.toString();
    }

    private static String decrypt(String input, int number) {

        StringBuilder stringBuilder = new StringBuilder();

        char[] inputCharArray = input.toCharArray();

        for (int i = 0; i < inputCharArray.length; i++) {
            char newCharacter = (char) (input.charAt(i) - number);

            stringBuilder.append(newCharacter);
        }
        return stringBuilder.toString();
    }

}
