package cn.szxys.Entity;

import cn.szxys.pub.UserSexEnum;

/**
 * Created by Shinewa on 2017/7/15.
 */
public class UserEntity {


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userSex=" + userSex +
                '}';
    }

    public UserEntity(String userName, String passWord, UserSexEnum userSex) {
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
    }

    public UserEntity(Long ID, String UserName, String UserSex, String Password) {
        this.id = ID;
        this.userName = UserName;
        this.passWord = Password;
        this.userSex = UserSexEnum.valueOf(UserSex);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    Long id;
    String userName;
    String passWord;
    UserSexEnum userSex;
}
