package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        String input = scanner.nextLine();
        int key = scanner.nextInt();

        String result;

        switch (operation){
            case "enc":
                result = encrypt(input, key);
                break;
            case "dec":
                result = encrypt(input, -key);
                break;
            default:
                result = "Wrong key";
        }
        System.out.println(result);
    }

    private static String encrypt(String input, int key) {
        char[] inputAsCharArray = input.toCharArray();

        for(int i = 0; i < inputAsCharArray.length; ++i){
            inputAsCharArray[i] = convert(inputAsCharArray[i], key);
        }

        return new String(inputAsCharArray);
    }


    static char convert(char input, int key){
        return (char)(input + key);
    }
}
