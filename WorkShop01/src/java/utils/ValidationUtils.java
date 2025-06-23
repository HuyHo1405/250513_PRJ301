/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author ho huy
 */
public class ValidationUtils {
    
    private static String PHONE_REGEX = "";
    private static String EMAIL_REGEX = "";
    
    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }
    
    public static boolean isInvalidId(int id){
        return id == -1;
    }
    
    public static boolean isInvalidEmail(String email){
        return isNullOrEmpty(email) || !email.matches(EMAIL_REGEX);
    }
    
    public static boolean isInvalidPhone(String phone){
        return isNullOrEmpty(phone) || !phone.matches(PHONE_REGEX);
    }
    
}
