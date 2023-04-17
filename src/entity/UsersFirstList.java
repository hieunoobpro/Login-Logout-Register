package entity;

import java.util.ArrayList;

/*Đây là danh sách được tạo trước khi bắt dầu*/
public class UsersFirstList extends User {
    private int id;
    private  static int autoid;
    public UsersFirstList(String userName, String email, String password) {
        super(userName, email, password);
        this.id=++autoid;
    }

}
