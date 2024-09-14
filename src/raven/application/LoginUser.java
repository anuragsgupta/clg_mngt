/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.application;

/**
 *
 * @author anurag
 */
public class LoginUser {

    private static String username, Id;
    private static String branch;
    private static String role;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginUser.username = username;
    }

    public static String getBranch() {
        return branch;
    }

    public static void setBranch(String branch) {
        LoginUser.branch = branch;
    }

    public static String getRole() {
        return role;
    }

    public static String getId() {
        return Id;
    }

    public static void setRole(String role) {
        LoginUser.role = role;
    }

    static void setId(String IDString) {
        LoginUser.Id = IDString;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public LoginUser(String username, String branch, String role) {
        setUsername(username);
        setBranch(branch);
        setRole(role);
    }

}
