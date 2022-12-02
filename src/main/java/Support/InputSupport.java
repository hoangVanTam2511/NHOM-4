/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

/**
 *
 * @author Admin
 */
public class InputSupport {
     public static boolean checkNullLength(String input) {
        if (input == null) {
            return false;
        }
        if (input.trim().length() == 0) {
            return false;
        }
        return true;
    }
    public static boolean checkUnsignedInt(String input){
        if(!checkRegexInt(input)){
            return false;
        }
        if(Long.parseLong(input)<=0){
            return false;
        }
        return true;
    }
    private static boolean checkRegexInt(String input){
        String regex ="^[0-9]+$";
        if(!input.matches(regex)){
            return false;
        }
        return true;
    }
}
