import entity.User;
import entity.UsersFirstList;
import handle.MenuHandle;
import handle.UsersFirstListHandle;
import view.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersFirstListHandle usersFirstListHandle = new UsersFirstListHandle();
        ArrayList<UsersFirstList> usersFirstLists = new ArrayList<>();
       /* Tao cac tai khoan ban dau*/
        int n = usersFirstListHandle.inputNumberOfUserFirstList(scanner);
            for (int i = 0; i<n  ; i++) {
                UsersFirstList users = usersFirstListHandle.usersFirstListInput(scanner, i, usersFirstLists);
                usersFirstLists.add(users);
            }
      /*  Bat dau chuong trinh*/
        MenuHandle menuHandle = new MenuHandle();
        Menu menu = new Menu();
        menuHandle.menuChoice(menu.menuFirst(scanner), scanner, usersFirstLists);
    }
}