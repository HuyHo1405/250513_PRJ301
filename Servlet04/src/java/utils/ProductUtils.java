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
public class ProductUtils {

    //static fields
    private static final String PRODUCT_ID_REGEX = "^[A-Z]-\\d{3}$";
    private static final String SIZE_REGEX = "^(S|M|L)$";
    private static final String STATUS_REGEX = "^[01]$";

    //
    //data validation
    private static boolean validateRgx(HttpServletRequest request, String name, String regex) {
        String str = request.getParameter(name);
        if (str != null) {
            return str.matches(regex);
        }
        return false;
    }

    public static boolean validateProductId(HttpServletRequest request, String id) {
        return validateRgx(request, id, PRODUCT_ID_REGEX);
    }

    public static boolean validateSize(HttpServletRequest request, String id) {
        return validateRgx(request, id, SIZE_REGEX);
    }

    public static boolean validateStatus(HttpServletRequest request, String id) {
        return validateRgx(request, id, STATUS_REGEX);
    }

    public static boolean validateStr(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        return str != null && !str.trim().isEmpty();
    }

    public static boolean validatePositiveDouble(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (param == null || param.isEmpty()) {
            return false;
        }
        try {
            double n = Double.parseDouble(param);
            return n >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
