package com.hemsteam.hems.handlers;

import org.jetbrains.annotations.Nullable;

public class DataBaseHelper {

    private static DataBaseHelper dataBaseHelper;

    private DataBaseHelper() {
        //TODO: init
    }

    public static DataBaseHelper getInstance() {
        if (dataBaseHelper == null)
            dataBaseHelper = new DataBaseHelper();
        return dataBaseHelper;
    }

    @Nullable
    public String getPasswordHashByUsername(String userName) {
        if (/*用户不存在*/false) {
            return null;
        } else {
            return new String("44a096ad3826989684abd961f3c8f6cee31f9e80d2a93cbbc01e91a1d493cee0");//用户密码的哈希
        }
    }

    public boolean resigsterNewUser(String username, String passwordHash) {
        if (/*用户存在*/false) {
            return false;
        } else {
            //保存新用户
            return true;
        }
    }
}
