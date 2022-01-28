import Second_Part.InvalidException;
import Second_Part.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DualOperations ropes = new DualOperations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the path of your tries file: ");
        String path = scanner.nextLine();
        Trie trie = new Trie(path);
        while (true){
            String input = scanner.nextLine();
            if(input.compareToIgnoreCase("status")==0){
                ropes.status();
            }else if(input.contains("new")){
                StringBuilder stringBuilder = new StringBuilder(input);
                int firstQuotation = stringBuilder.indexOf("\"");
                int secondQuotation = stringBuilder.lastIndexOf("\"");
                String newOne=stringBuilder.substring(firstQuotation+1,secondQuotation);
                Rope rope = new Rope();
                rope.newRope(newOne);
                ropes.getRopes().add(rope);
            }else if(input.contains("index")){
                String check = input.replaceAll(" ",",");
                List<Integer> inputs = checkNumber(check.substring(6));
                System.out.println(ropes.index(inputs.get(0),inputs.get(1)));
            }else if (input.contains("concat")){
                String check = input.replaceAll(" ",",");
                List<Integer> inputs = checkNumber(check.substring(7));
                ropes.concat(inputs.get(0),inputs.get(1));
            }else if(input.contains("insert")){
                String check = input.replaceAll(" ",",");
                List<Integer> inputs = checkNumber(check.substring(7));
                ropes.insert(inputs.get(0),inputs.get(1),inputs.get(2));
            }else if(input.contains("split")){
                String check = input.replaceAll(" ",",");
                List<Integer> inputs = checkNumber(check.substring(6));
                ropes.split(inputs.get(0),inputs.get(1));
            }else if(input.contains("delete")){
                String check = input.replaceAll(" ",",");
                List<Integer> inputs = checkNumber(check.substring(7));
                ropes.delete(inputs.get(0),inputs.get(1),inputs.get(2));
            }else if(input.contains("autocomplete")){
                input=input.replaceAll(" ","");
                trie.autocomplete(input.charAt(12));
            }else if (input.matches("[0-9]+")){
                try {
                    String newRope = trie.getList(Integer.parseInt(input));
                    Rope trieRope = new Rope();
                    trieRope.newRope(newRope);
                    ropes.getRopes().add(trieRope);
                } catch (InvalidException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static List<Integer> checkNumber(String input){
        List<Integer> numbers = new ArrayList<>();
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                sb.append(c);
            }
            if (c==','||c==chars[chars.length-1]){
                String number = sb.toString();
                sb.setLength(0);
                numbers.add(Integer.valueOf(number));
            }
        }
        return numbers;
    }

}
