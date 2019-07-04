package encryptdecrypt;

import java.util.Scanner;

public class Main {

    private static String mode;
    private static String data;
    private static int key;

    private static String result;

    public static void main(String[] args) {

        parseArgs(args);
        checkArgs();
        calculateResult();

        System.out.println(result);
    }

    private static void calculateResult() {
        switch (mode){
            case "enc":
                result = encrypt(data, key);
                break;
            case "dec":
                result = encrypt(data, -key);
                break;
            default:
                result = "Wrong key";
        }
    }

    private static void checkArgs() {
        if(mode.isBlank()){
            mode = "enc";
        }

        if(data.isBlank()){
            Scanner scanner = new Scanner(System.in);
            data = scanner.nextLine();
            key = scanner.nextInt();
        }
    }

    private static String encrypt(String input, int key) {
        char[] inputAsCharArray = input.toCharArray();

        for(int i = 0; i < inputAsCharArray.length; ++i){
            inputAsCharArray[i] = convert(inputAsCharArray[i], key);
        }

        return new String(inputAsCharArray);
    }

    private static void parseArgs(String[] args){
        for(int i = 0; i < args.length; i += 2){
            switch (args[i]){
                case "-mode":
                    mode = args[i+1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i+1]);
                    break;
                case "-data":
                    data = args[i+1];
                    break;
            }
        }
    }

    static char convert(char input, int key){
        return (char)(input + key);
    }
}
