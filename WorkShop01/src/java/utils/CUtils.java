/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author ho huy
 */
public class CUtils {
    
    public static String ERROR_PAGE = "error.jsp";
    
    public static String error(HttpServletRequest request, String errorMsg){
        request.setAttribute("errorMsg", errorMsg);
        return ERROR_PAGE;
    }
    
    public static int toInt(String str){
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception e) {
        }
        return -1;
    }
    
}
