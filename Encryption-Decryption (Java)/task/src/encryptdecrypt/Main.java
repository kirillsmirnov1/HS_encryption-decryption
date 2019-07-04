package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    private static String mode;
    private static String data;
    private static int key;
    private static String in;
    private static String out;

    private static String result;

    public static void main(String[] args) {

        parseArgs(args);
        checkArgs();
        calculateResult();
        writeOutResult();
    }

    private static void writeOutResult() {
        if(out.isBlank()){
            System.out.println(result);
        } else {
            try(FileWriter fileWriter = new FileWriter(new File(out))) {
                fileWriter.write(result);
                fileWriter.flush();
            } catch (IOException e) {
                System.out.println("Error writing result");
                exit(1);
            }
        }

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

        if(!in.isBlank()){
            try(Scanner scanner = new Scanner(new File(in))) {
                data = scanner.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                exit(1);
            }
        }

        if(in.isBlank() && data.isBlank()){
            Scanner scanner = new Scanner(System.in);
            data = scanner.nextLine();
            key = scanner.nextInt();
        }

        if(data.isBlank()){
            System.out.println("Data doesn't have a value");
            exit(1);
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
                case "-in":
                    in = args[i+1];
                    break;
                case "-out":
                    out = args[i+1];
                    break;
            }
        }
    }

    static char convert(char input, int key){
        return (char)(input + key);
    }
}
