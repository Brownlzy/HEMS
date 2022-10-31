package com.hemsteam.hems.handlers;

import com.hemsteam.hems.utils.Log;
import com.hemsteam.hems.utils.SHA256;

import java.util.Date;

public class Account {
    public static final int RESIGSTER_SUCCESSFUL = 0;
    public static final int RESIGSTER_PASSWORD_UNANIMOUS = 1;
    public static final int RESIGSTER_USERNAME_EXISTS = 2;
    public static final int RESIGSTER_USERNAME_INVALID = 3;
    public static final int RESIGSTER_PASSWORD_INVALID = 4;
    public static final int LOGIN_SUCCESSFUL = 0;
    public static final int LOGIN_USERNAME_INVALID = 1;
    public static final int LOGIN_PASSWORD_INVALID = 2;
    public static final int LOGIN_USERNAME_NOT_EXISTS = 3;
    public static final int LOGIN_PASSWORD_ERROR = 4;
    private static String user;
    private static int year;
    private static int month;

    public static String getPage() {
        return page;
    }

    public static void setPage(String page) {
        Account.page = page;
    }

    private static String page;

    public static void setYearMonth(int y, int m) {
        year = y;
        month = m;
        Log.i(Account.class, "year:" + y + " month:" + m);
    }

    public static int getYear() {
        return year;
    }

    public static int getMonth() {
        return month;
    }

    private static Account account;

    private Account() {
        user = "admin";
    }

    public static Account getInstance() {
        if (account == null)
            account = new Account();
        return account;
    }

    public static String getUser() {
        if (account == null) account = new Account();
        return user;
    }

    public int login(String un, String pw) {
        //用户名和密码不合规
        if (!isUsernameValid(un)) return LOGIN_USERNAME_INVALID;
        if (!isPasswordValid(pw)) return LOGIN_PASSWORD_INVALID;
        String pwHash = DataBaseHelper.getInstance().getPasswordHashByUsername(un);
        //用户名不存在
        if (pwHash == null) return LOGIN_USERNAME_NOT_EXISTS;
        //密码不匹配
        if (!pwHash.equals(SHA256.getSHA256StrJava("HEMS" + pw))) return LOGIN_PASSWORD_ERROR;
        //登录成功
        user = un;
        setYearMonth(new Date().getYear() + 1900, new Date().getMonth() + 1);
        //DataBaseHelper.getInstance().repairSummary();
        return LOGIN_SUCCESSFUL;
    }

    public int resigster(String un, String pw1, String pw2) {
        //用户名和密码不合规
        if (!isUsernameValid(un)) return RESIGSTER_USERNAME_INVALID;
        if (!isPasswordValid(pw1)) return RESIGSTER_PASSWORD_INVALID;
        //两次输入密码不匹配
        if (!pw1.equals(pw2)) return RESIGSTER_PASSWORD_UNANIMOUS;
        //用户已存在
        if (!DataBaseHelper.getInstance().resigsterNewUser(un, SHA256.getSHA256StrJava("HEMS" + pw1)))
            return RESIGSTER_USERNAME_EXISTS;
        //注册成功
        return RESIGSTER_SUCCESSFUL;
    }

    public boolean changPassword(String pw, String npw1, String npw2) {
        if (login(getUser(), pw) == LOGIN_SUCCESSFUL) {
            if (!isPasswordValid(pw)) return false;
            if (!npw1.equals(npw2)) return false;
            if (!DataBaseHelper.getInstance().changePassword(getUser(), SHA256.getSHA256StrJava("HEMS" + npw1)))
                return false;
            return true;
        } else
            return false;
    }

    private boolean isUsernameValid(String name) {
        return name.length() >= 4;
    }

    private boolean isPasswordValid(String pass) {
        return pass.length() >= 4;
    }
}
