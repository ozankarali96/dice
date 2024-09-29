package org.example;

import java.util.Scanner;

public class DiceGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Rule: Check for target number of rounds
        System.out.println("Please enter the target number of rounds (1-99):");
        int targetRounds = 0;

        while (true) {
            try {
                targetRounds = Integer.parseInt(scanner.nextLine());
                if (targetRounds >= 1 && targetRounds <= 99) {
                    break;
                } else {
                    System.out.println("Please enter a number between 1 and 99.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Initialize players' total points
        int totalPoints1 = 0, totalPoints2 = 0, totalPoints3 = 0;

        // Store results
        String[][] results = new String[targetRounds][7];

        // 2. Rule: Repeat the game for the specified number of rounds
        for (int round = 1; round <= targetRounds; round++) {
            // Input dice values from the user
            int dice1 = getValidDiceValue(scanner, round, 1);
            int dice2 = getValidDiceValue(scanner, round, 2);
            int dice3 = getValidDiceValue(scanner, round, 3);

            // 4. Rule: If all players roll the same number
            if (dice1 == dice2 && dice2 == dice3) {
                totalPoints1 += dice1;
                totalPoints2 += dice2;
                totalPoints3 += dice3;
            }
            // 5. Rule: If all players roll different numbers
            else if (dice1 != dice2 && dice2 != dice3 && dice1 != dice3) {
                totalPoints1 += dice1;
                totalPoints2 += dice2;
                totalPoints3 += dice3;
            }
            // 6. Rule: If two players roll the same number
            else {
                if (dice1 == dice2) {
                    if (dice3 < dice1) {
                        totalPoints1 += 2 * dice1;
                        totalPoints2 += 2 * dice2;
                        totalPoints3 += dice3;
                    } else {
                        totalPoints1 += dice1;
                        totalPoints2 += dice2;
                        totalPoints3 += dice3;
                    }
                } else if (dice1 == dice3) {
                    if (dice2 < dice1) {
                        totalPoints1 += 2 * dice1;
                        totalPoints2 += dice2;
                        totalPoints3 += dice3;
                    } else {
                        totalPoints1 += dice1;
                        totalPoints2 += dice2;
                        totalPoints3 += dice3;
                    }
                } else {
                    if (dice2 < dice3) {
                        totalPoints1 += dice1;
                        totalPoints2 += 2 * dice2;
                        totalPoints3 += 2 * dice3;
                    } else {
                        totalPoints1 += dice1;
                        totalPoints2 += dice2;
                        totalPoints3 += 2 * dice3;
                    }
                }
            }

            // Store results
            results[round - 1][0] = String.valueOf(round);
            results[round - 1][1] = String.valueOf(dice1);
            results[round - 1][2] = String.valueOf(dice2);
            results[round - 1][3] = String.valueOf(dice3);
            results[round - 1][4] = String.valueOf(totalPoints1);
            results[round - 1][5] = String.valueOf(totalPoints2);
            results[round - 1][6] = String.valueOf(totalPoints3);

            // 7. Rule: Print results
            printCurrentRoundResults(round, dice1, dice2, dice3, totalPoints1, totalPoints2, totalPoints3);
        }

        // Print all results
        printAllResults(results);

        // Determine the winner
        declareWinner(totalPoints1, totalPoints2, totalPoints3);
    }

    private static int getValidDiceValue(Scanner scanner, int round, int diceNumber) {
        int diceValue = 0;
        while (true) {
            System.out.println("Enter value for Dice " + diceNumber + " in Round " + round + " (1-6):");
            try {
                diceValue = Integer.parseInt(scanner.nextLine());
                if (diceValue >= 1 && diceValue <= 6) {
                    break;
                } else {
                    System.out.println("Dice values must be between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for dice value.");
            }
        }
        return diceValue;
    }

    private static void printCurrentRoundResults(int round, int dice1, int dice2, int dice3, int totalPoints1, int totalPoints2, int totalPoints3) {
        System.out.println("----------------------------------------------------");
        System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |\n", "Round", "Dice1", "Dice2", "Dice3", "Total1", "Total2", "Total3");
        System.out.println("----------------------------------------------------");
        System.out.printf("| %-5d | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d |\n", round, dice1, dice2, dice3, totalPoints1, totalPoints2, totalPoints3);
        System.out.println("----------------------------------------------------");
    }

    private static void printAllResults(String[][] results) {
        System.out.println("\nAll Results:");
        System.out.println("----------------------------------------------------");
        System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |\n", "Round", "Dice1", "Dice2", "Dice3", "Total1", "Total2", "Total3");
        System.out.println("----------------------------------------------------");
        for (String[] result : results) {
            System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s |\n", result[0], result[1], result[2], result[3], result[4], result[5], result[6]);
        }
        System.out.println("----------------------------------------------------");
    }

    private static void declareWinner(int totalPoints1, int totalPoints2, int totalPoints3) {
        System.out.println("\nWinner:");
        if (totalPoints1 > totalPoints2 && totalPoints1 > totalPoints3) {
            System.out.println("Player 1 wins!");
        } else if (totalPoints2 > totalPoints1 && totalPoints2 > totalPoints3) {
            System.out.println("Player 2 wins!");
        } else if (totalPoints3 > totalPoints1 && totalPoints3 > totalPoints2) {
            System.out.println("Player 3 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
