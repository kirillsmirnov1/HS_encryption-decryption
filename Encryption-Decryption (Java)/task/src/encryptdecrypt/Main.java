package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String input = "we found a treasure!";

        for(Character ch : input.toCharArray()){
            System.out.print(convert(ch));
        }

        System.out.println();
    }

    static char convert(char input){
        if(input >= 'a' && input <= 'z'){
            int output = input - 'a';
            output = output + (int)((12.5d - output) * 2d);
            return (char)(output + 'a');
        } else {
            return input;
        }
    }
}
