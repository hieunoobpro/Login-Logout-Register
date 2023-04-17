package handle;
import entity.UsersFirstList;

import java.util.ArrayList;
import java.util.Scanner;

public class UsersFirstListHandle {
    /*    Nhap thong tin cho cac tai khoan dau tien truoc khi vao chuong trinh*/
    public UsersFirstList usersFirstListInput(Scanner scanner, int i, ArrayList<UsersFirstList> arrayList) {
        boolean b = false;
        String email;
        String password;
        String name;
        do {
            System.out.println("Nhập username thứ " + (i + 1));
            name = scanner.nextLine();
           /* kiem tra username da ton tai chua*/
            if(MenuHandle.isUsernameTaken(name, arrayList)){
                System.out.println("Username đã tồn tại");
                b = true;
            }
            else if(!MenuHandle.isUsernameTaken(name, arrayList)) {
                b = false;
            }
        }while(b == true);
        do {
            System.out.println("Nhập password: ");
            password =scanner.nextLine();
          /*  kiem tra dieu kien cua password*/
            if (!MenuHandle.isValidPassword(password)) {
                System.out.println("Password không hợp lệ!");
                b = true;
            }
            else if (MenuHandle.isValidPassword(password)){
                b = false;
            }
        }while (b);
        do {
            System.out.println("Nhập email: ");
            email = scanner.nextLine();
     /*       kiem tra dieu kien va su ton tai cua email*/
            if (!MenuHandle.isValidEmail(email) || MenuHandle.isEmailTaken(email,arrayList)) {
                System.out.println("Email không hợp lệ hoặc đã tồn tại!");
                b = true;
                }
            else if (MenuHandle.isValidPassword(email) || !MenuHandle.isEmailTaken(email,arrayList)){
                b = false;
            }
        } while (b == true);
        return new UsersFirstList(name, email, password);
        }
        public Integer inputNumberOfUserFirstList(Scanner scanner){
            int n = 0;
            do {
                try {
                    System.out.println("Nhập số lượng tài khoản ban đầu( phải có ít nhất 1 tài khoản): ");
                    n = Integer.parseInt(scanner.nextLine());
                }
                catch (NumberFormatException e){
                    System.out.println("Nhập số nguyên cơ mà");
                }
            }while (n<=0);
            return n;
        }
}
