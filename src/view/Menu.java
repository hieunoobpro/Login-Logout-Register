package view;

import entity.UsersFirstList;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
  /*  menu sau khi login thanh cong*/
    public int menuLogin(Scanner scanner, String username){
        System.out.println("Chào mừng " + username + ", bạn có thể thực hiện các công việc sau:");
        System.out.println("1 - Thay đổi username");
        System.out.println("2 - Thay đổi email");
        System.out.println("3 - Thay đổi mật khẩu");
        System.out.println("4 - Đăng xuất");
        System.out.println("0 - Thoát chương trình");
        return scanner.nextInt();
    }
  /*  menu dau tien hien ra*/
    public int menuFirst(Scanner scanner){
        System.out.println("1 - Đăng nhập ");
        System.out.println("2 - Đăng ký ");
        return scanner.nextInt();
    }
/*    menu khi login that bai*/
    public int menuLoginfail(Scanner scanner){
        System.out.println("Sai username hoặc password");
        System.out.println("1 - Đăng nhập lại");
        System.out.println("2 - Quên mật khẩu");
        return scanner.nextInt();
    }
}
