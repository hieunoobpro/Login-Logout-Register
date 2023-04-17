package handle;
import entity.UsersFirstList;
import view.Menu;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuHandle {
  /*  Man hinh dang nhap, dang ky*/
    public void menuChoice(int choice, Scanner scanner, ArrayList<UsersFirstList> arrayList) {
        Menu menu = new Menu();
        ArrayList<UsersFirstList> Lists;
        try {
            if(choice == 1) {
                /*   Dang nhap*/
                login(scanner, arrayList);
            }
            else if(choice ==2) {
                /*  Dang ky*/
                Lists = register(scanner, arrayList);
                menuChoice(menu.menuFirst(scanner), scanner, Lists);
            }
            else {
                System.out.println("Nhập linh tinh, cho dăng nhập lại");
                menuChoice(menu.menuFirst(scanner), scanner, arrayList);
            }
        }catch (InputMismatchException e){
            System.out.println("Nhập số nguyên cơ mà");
            scanner.nextLine();
            menuChoice(menu.menuFirst(scanner),scanner, arrayList);
        }

        }
      /*man hinh login*/
    public void login(Scanner scanner, ArrayList<UsersFirstList> arrayList) {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        Menu menu = new Menu();
        boolean b = false;
        for (UsersFirstList user : arrayList) {
            if (MenuHandle.isUsernameTaken(username, arrayList) && MenuHandle.isPasswordTaken(password, arrayList)) {/*
                in ra menu cua user*/
                try {
                    int option = menu.menuLogin(scanner, username);
                    if (option == 1) {
                        /*       doi Username*/
                        changUsername(scanner, arrayList);
                        menuChoice(menu.menuFirst(scanner), scanner, arrayList);
                    }
                    if (option == 2) {
                        /*  doi email*/
                        changeEmail(scanner, arrayList, b);
                        menuChoice(menu.menuFirst(scanner), scanner, arrayList);
                    }
                    if (option == 3) {
                        /*           doi mat khau*/
                        changePassword(scanner, arrayList, b);
                        menuChoice(menu.menuFirst(scanner), scanner, arrayList);
                    }
                    if (option == 4) {
                        menuChoice(menu.menuFirst(scanner), scanner, arrayList);
                    }
                    if (option == 0) {
                        break;
                    }
                    else {
                        System.out.println("Nhập linh tinh, cho dăng nhập lại");
                        menuChoice(menu.menuFirst(scanner), scanner, arrayList);
                    }
                }catch (InputMismatchException e){
                    System.out.println("Nhập số nguyên cơ mà, đăng nhập lại đi");
                    menuChoice(menu.menuFirst(scanner), scanner, arrayList);
                }
            }
   /*         neu username hoac password sai thi in ra*/
            else {
                try {
                    System.out.println("Nhập lựa chọn của bạn: ");
                    int option = menu.menuLoginfail(scanner);
                    if(option == 1) {
                        /*  quay lai man hinh dang nhap*/
                        menuChoice(menu.menuFirst(scanner),scanner, arrayList);
                    }
                    else if(option == 2)  {
                        /*    Sua mat khau qua email*/
                        checkEmail(scanner, arrayList);
                    }
                    else {
                        System.out.println("Nhập linh tinh, cho dăng nhập lại");
                        login(scanner, arrayList);
                    }
                }catch (InputMismatchException e){
                    System.out.println("Nhập số nguyên cơ mà");
                    menuChoice(menu.menuFirst(scanner),scanner, arrayList);
                }
                }
            }
        }
/*    Doi username*/
    public void changUsername(Scanner scanner, ArrayList<UsersFirstList> arrayList) {
        System.out.print("Nhập username mới: ");
        String newUserName = scanner.next();
        for (UsersFirstList user : arrayList) {
            if (MenuHandle.isUsernameTaken(newUserName, arrayList)) {
                System.out.println("Username đã tồn tại!");
            } else {
                user.setUserName(newUserName);
                System.out.println("Đổi username thành công!");
            }
        }
    }
    public void changeEmail(Scanner scanner, ArrayList<UsersFirstList> arrayList, boolean b) {
        System.out.println("Nhập email muốn đổi sang theo đúng cú pháp: ");
        do {
            String reEmail = scanner.nextLine();
            for (UsersFirstList user : arrayList) {
                if (isValidEmail(reEmail) && !isEmailTaken(reEmail, arrayList)) {
                    user.setEmail(reEmail);
                    System.out.println("Sua email thanh công!");
                    b = false;
                } else {
                    System.out.println("Email không hợp lệ hoặc đã tồn tại!");
                    b = true;
                }
            }
        } while (b == true);
    }
    public void changePassword(Scanner scanner, ArrayList<UsersFirstList> arrayList, boolean b){
        System.out.print("Đổi password theo đúng cú pháp: ");
        do {
            String rePassword = scanner.nextLine();
            for (UsersFirstList user : arrayList) {
                if (!isValidPassword(rePassword)) {
                    System.out.println("Password không hợp lệ!");
                    b = true;
                } else {
                    b = false;
                    user.setPassword(rePassword);
                }
            }
        } while (b == true);
    }
    public ArrayList<UsersFirstList> register(Scanner scanner, ArrayList<UsersFirstList> arrayList){
        boolean b = false;
        String password;
        String rePassword;
        String username;
   /*     nhap username moi*/
        do {
            System.out.print("Tạo username mới: ");
            scanner.nextLine();
            username = scanner.nextLine();
            if(MenuHandle.isUsernameTaken(username, arrayList)){
                System.out.println("Username đã tồn tại");
                b = true;
            }
            else if(!MenuHandle.isUsernameTaken(username, arrayList)) {
                b = false;
            }
        }while(b == true);

      /*  Nhap password moi, kiem tra password*/
        do{
            do{
        System.out.print("Tạo password mới: ");
        password = scanner.nextLine();
            if (!isValidPassword(password)) {
                System.out.println("Password không hợp lệ!");
                b = true;
            }
            else {
                b = false;
            }
            }while (b);
        System.out.println("Nhập lại password: ");
        rePassword = scanner.nextLine();
        if(!password.equals(rePassword)){
            System.out.println("Password nhập lại sai, nhập lại Password từ đầu");
        }
        } while (!password.equals(rePassword));
       /* nhap email moi*/
        String email = null;
        do {
            System.out.println("Nhập email");
            email = scanner.nextLine();
            if(!isValidEmail(email) || isEmailTaken(email, arrayList)){
                System.out.println("Email không hợp lệ hoặc đã tồn tại!");
                email = scanner.nextLine();
                    b = true;
            }
                else b = false;
        } while (b);
        UsersFirstList user1 = new UsersFirstList(username, email, password);
        arrayList.add(user1);
        System.out.println("Đăng ký thành công!");
        return arrayList;
    }
   /* Doi mat khau theo email*/
    private void checkEmail(Scanner scanner,ArrayList<UsersFirstList> userList) {
        Menu menu = new Menu();
        System.out.print("Nhập email của tài khoản: ");
        String inputEmail = scanner.nextLine();
        scanner.nextLine();
        for (UsersFirstList user : userList) {
            if (!MenuHandle.isEmailTaken(inputEmail, userList)) {
                System.out.println("Password mới: ");
                String newPassword = scanner.nextLine();
                user.setPassword(newPassword);
                System.out.println("Đổi password thành công");
            }
            else if (MenuHandle.isEmailTaken(inputEmail, userList)) {
                System.out.println("Không tồn tại email");
                }
            }
        menuChoice(menu.menuFirst(scanner), scanner, userList);
        }
/*    kiem tra dieu kien nhap cua email*/
    static boolean isValidEmail(String email) {
        String regex = "^[a-z][a-zA-Z0-9\\-_]+@[a-zA-z]{2,}(\\.[a-zA-Z]+)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
  /*  kiem tra su ton tai cua email*/
    static boolean isEmailTaken(String email, ArrayList<UsersFirstList> userList) {
        for (UsersFirstList user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
/*    kiem tra dieu kien cua password*/
    static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[.,-_;!@#$%^&+=])(?=\\S+$).{7,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
/*    kiem tra su ton tai cua username*/
    static boolean isUsernameTaken(String username, ArrayList<UsersFirstList> userList) {
        for (UsersFirstList user : userList) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
/*    kiem tra su ton tai cua password*/
    static boolean isPasswordTaken(String password, ArrayList<UsersFirstList> userList) {
        for (UsersFirstList user : userList) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
