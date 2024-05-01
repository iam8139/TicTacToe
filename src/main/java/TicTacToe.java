import org.game.service.MatrixGame;
import org.game.serviceImpl.TicToeToeImpl;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MatrixGame game = new TicToeToeImpl();

        // Select username
        System.out.println("Enter username of 1st user: ");
        String user1 = sc.next();

        System.out.println("Enter username of 2st user: ");
        String user2 = sc.next();

        game.setUser(user1, user2);

        while (game.getCount() < 9) {
            if (game.getCount() % 2 == 0) {
                System.out.println(user1 + " It's your turn. Select your box: ");
            } else {
                System.out.println(user2 + " It's your turn. Select your box: ");
            }

            try {
                int input = sc.nextInt();
                game.mark(input);
                if (game.won(input)){
                    if ((game.getCount()-1)%2==0) System.out.println("Congratulations " + user1 + ". You Won...");
                    else System.out.println("Congratulations " + user2 + ". You Won...");
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Please Enter a valid number between 1-9");
                break;
            } finally {
                System.out.println("Do you want to check the current game status: ");
                String status = sc.next();
                if (status.equalsIgnoreCase("Y")) game.checkStatus();
                else if (!status.equalsIgnoreCase("N")) System.out.println("Invalid Input::Operation Suspended...");
            }
        }
    }
}
