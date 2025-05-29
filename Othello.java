import java.util.Scanner;
public class Othello {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // to get input from players
        char[][] grid = createGrid();
        int turn = 1;
        int placed = 0;
        String player = "White";
        boolean winner = false;
//while loop to play the ga6
// 4
// me until either there is a winner or the board is full
        while (turn < 70 && placed <60){
            boolean validPlay = false;
            int playCol = -1;
            int playRow = -1;
            while(!validPlay){
                displayGrid(grid);
                System.out.print("Player " + player + ", choose a column: ");
                playCol = in.nextInt(); // receive input from player
                System.out.print("Player " + player + ", choose a row: ");
                playRow = in.nextInt(); // receive input from player
//validate play
                validPlay = validateSpace(grid, playCol, playRow, player) && isFlip(grid, playCol, playRow, player);
            }
// place the token
            flipPointN(grid,playCol, playRow, player);
            flipPointS(grid,playCol, playRow, player);
            flipPointE(grid,playCol, playRow, player);
            flipPointW(grid,playCol, playRow, player);
            flipPointNW(grid,playCol, playRow, player);
            flipPointSW(grid,playCol, playRow, player);
            flipPointNE(grid,playCol, playRow, player);
            flipPointSE(grid,playCol, playRow, player);
            placeToken(grid, playCol, playRow, player);
//determine if there is a winner
            winner = isWinner(grid, player);
//switch players
            if (player.equals("White")){
                player = "Black";
            }else{
                player = "White";
            }
            if (validPlay){
                placed++;
            }
            turn++;


        }
        displayGrid(grid);
        if (turn > 70 || placed == 60){
            int countW = 0;
            int countB = 0;
            for (int i = 0; i < grid.length; i++){
                for (int j = 0; j < grid[i].length; j++){
                    if (grid[i][j] == 'W'){
                        countW++;
                    }
                    else if (grid[i][j] == 'B'){
                        countB++;
                    }
                }
            }
            if (countW > countB){
                System.out.println("White won!");
            }
            else if (countB > countW){
                System.out.println("Black won!");
            }
            else if (countW == countB){
                System.out.println("Tie game");
            }
        }
    }

    public static char[][] createGrid(){
        char[][] grid = new char[8][8];
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                grid[row][col] = ' ';
            }
        }
        grid[3][3] = 'W';
        grid[3][4] = 'B';
        grid[4][3] = 'B';
        grid[4][4] = 'W';
        return grid;
    }
    public static void displayGrid(char[][] grid){
        System.out.println("Welcome to Othello!");
        System.out.print("     ");
        for (int col = 0; col < grid[0].length; col++){
            System.out.print(col + 1);
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 0; i <36; i++){
            System.out.print('-');
        }
        System.out.println();
        for (int row = 0; row < grid.length; row++){
            System.out.print(row + 1);
            System.out.print(" ");
            for (int col = 0; col < grid[0].length; col++){
                System.out.print(" | ");
                System.out.print(grid[row][col]);
            }
            System.out.print(" | ");
            System.out.println();
            for (int i = 0; i < 36; i ++){
                System.out.print('-');
            }
            System.out.println();
        }
    }

    public static boolean validateSpace(char[][] grid, int playCol, int playRow, String player) {
        if (playCol < 1 || playCol > 8 || playRow < 1 || playRow > 8){
            return false;
        }
        else if (grid[playRow-1][playCol-1] != ' '){
            return false;
        }
        if (player.equals("White")){
            int [][] surrounding = new int [3][3];
            if (playCol == 1 && playRow == 1) {
                if (grid[playRow-1][playCol] != 'B' && grid[playRow][playCol] != 'B' && grid[playRow][playCol-1] != 'B'){
                    return false;
                }
            }
            else if (playCol == 8 && playRow == 8) {
                if (grid[playRow - 2][playCol-1] != 'B' && grid[playRow-2][playCol-2] != 'B' && grid[playRow-1][playCol-2] != 'B') {
                    return false;
                }
            }
            else if (playCol == 1 && playRow == 8) {
                if (grid[playRow-1][playCol] != 'B' && grid[playRow-2][playCol] != 'B' && grid[playRow-2][playCol-1] != 'B') {
                    return false;
                }
            }
            else if (playCol == 8 && playRow == 1) {
                if(grid[playRow-1][playCol-2] != 'B' && grid[playRow][playCol-2] != 'B' && grid[playRow-1][playCol-1] != 'B'){
                    return false;
                }
            }
            else if (playCol == 1){
                if (grid[playRow-2][playCol-1] != 'B' && grid[playRow-2][playCol] != 'B'
                        && grid[playRow-1][playCol] != 'B'
                        && grid[playRow][playCol-1] != 'B' && grid[playRow][playCol] != 'B'){
                    return false;
                }
            }
            else if (playCol == 8) {
                if (grid[playRow-2][playCol-2] != 'B' && grid[playRow-2][playCol-1] != 'B'
                        && grid[playRow-1][playCol-2] != 'B'
                        && grid[playRow][playCol-2] != 'B' && grid[playRow][playCol-1] != 'B'){
                    return false;
                }
            }
            else if ((playRow == 1 || (grid[playRow-2][playCol-2] != 'B' && grid[playRow-2][playCol-1] != 'B' && grid[playRow-2][playCol] != 'B'))
                    && grid[playRow-1][playCol-2] != 'B' && grid[playRow-1][playCol] != 'B'
                    && (playRow == 8 || (grid[playRow][playCol-2] != 'B' && grid[playRow][playCol-1] != 'B' && grid[playRow][playCol] != 'B'))){
                return false;
            }
        }

        if (player.equals("Black")){
            if (playCol == 1 && playRow == 1) {
                if (grid[playRow-1][playCol] != 'W' && grid[playRow][playCol] != 'W' && grid[playRow][playCol-1] != 'W'){
                    return false;
                }
            }
            else if (playCol == 8 && playRow == 8) {
                if (grid[playRow - 2][playCol-1] != 'W' && grid[playRow-2][playCol-2] != 'W' && grid[playRow-1][playCol-2] != 'W') {
                    return false;
                }
            }
            else if (playCol == 1 && playRow == 8) {
                if (grid[playRow-1][playCol] != 'W' && grid[playRow-2][playCol] != 'W' && grid[playRow-2][playCol-1] != 'W') {
                    return false;
                }
            }
            else if (playCol == 8 && playRow == 1) {
                if(grid[playRow-1][playCol-2] != 'W' && grid[playRow][playCol-2] != 'W' && grid[playRow-1][playCol-1] != 'W'){
                    return false;
                }
            }
            else if (playCol == 1){
                if (grid[playRow-2][playCol-1] != 'W' && grid[playRow-2][playCol] != 'W'
                        && grid[playRow-1][playCol] != 'W'
                        && grid[playRow][playCol-1] != 'W' && grid[playRow][playCol] != 'W'){
                    return false;
                }
            }
            else if (playCol == 8) {
                if (grid[playRow-2][playCol-2] != 'W' && grid[playRow-2][playCol-1] != 'W'
                        && grid[playRow-1][playCol-2] != 'W'
                        && grid[playRow][playCol-2] != 'W' && grid[playRow][playCol-1] != 'W'){
                    return false;
                }
            }
            else if ((playRow == 1 || (grid[playRow-2][playCol-2] != 'W' && grid[playRow-2][playCol-1] != 'W' && grid[playRow-2][playCol] != 'W'))
                    && grid[playRow-1][playCol-2] != 'W' && grid[playRow-1][playCol] != 'W'
                    && (playRow == 8 || (grid[playRow][playCol-2] != 'W' && grid[playRow][playCol-1] != 'W' && grid[playRow][playCol] != 'W'))){
                return false;
            }
        }

        return true;
    }

    public static boolean flipPointN(char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            if (playRow != 1 && grid[playRow-2][playCol-1] == 'B'){
                int k = playRow-1;
                while (k >= 0 && flipped == false){
                    if (grid[k][playCol-1] == 'W'){
                        flipped = true;
                    }
                    k--;
                }
                if (flipped == true){
                    for (int i = k+1; i < playRow; i++){
                        grid[i][playCol-1] = 'W';
                    }
                }
            }
        }
        else if (player.equals("Black")){
            if (playRow != 1 && grid[playRow-2][playCol-1] == 'W'){
                int k = playRow-1;
                while (k >= 0 && flipped == false){
                    if (grid[k][playCol-1] == 'B'){
                        flipped = true;
                    }
                    k--;
                }
                if (flipped == true){
                    for (int i = k+1; i < playRow; i++){
                        grid[i][playCol-1] = 'B';
                    }
                }
            }
        }
        return flipped;
    }
    public static boolean flipPointS(char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            if (playRow != 8 && grid[playRow][playCol-1] == 'B'){
                int k = playRow-1;
                while (k <= 7 && flipped == false){
                    if (grid[k][playCol-1] == 'W'){
                        flipped = true;
                    }
                    k++;
                }
                if (flipped == true){
                    for (int i = k-1; i >= playRow; i--){
                        grid[i][playCol-1] = 'W';
                    }
                }
            }
        }
        else if (player.equals("Black")){
            if (playRow != 8 && grid[playRow][playCol-1] == 'W'){
                int k = playRow-1;
                while (k <= 7 && flipped == false){
                    if (grid[k][playCol-1] == 'B'){
                        flipped = true;
                    }
                    k++;
                }
                if (flipped == true){
                    for (int i = k-1; i >= playRow; i--){
                        grid[i][playCol-1] = 'B';
                    }
                }
            }
        }
        return flipped;
    }

    public static boolean flipPointE(char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            if (playCol != 8 && grid[playRow-1][playCol] == 'B'){
                int k = playCol-1;
                while (k <= 7 && flipped == false){
                    if (grid[playRow -1][k] == 'W'){
                        flipped = true;
                    }
                    k++;
                }
                if (flipped == true){
                    for (int i = k-1; i >= playCol; i--){
                        grid[playRow -1][i] = 'W';
                    }
                }
            }
        }
        else if (player.equals("Black")){
            if (playCol != 8 && grid[playRow-1][playCol] == 'W'){
                int k = playCol-1;
                while (k <= 7 && flipped == false){
                    if (grid[playRow -1][k] == 'B'){
                        flipped = true;
                    }
                    k++;
                }
                if (flipped == true){
                    for (int i = k-1; i >= playCol; i--){
                        grid[playRow -1][i] = 'B';
                    }
                }
            }
        }
        return flipped;
    }
    public static boolean flipPointW(char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            if (playCol != 1 && grid[playRow-1][playCol-2] == 'B'){
                int k = playCol-1;
                while (k >= 0 && flipped == false){
                    if (grid[playRow -1][k] == 'W'){
                        flipped = true;
                    }
                    k--;
                }
                if (flipped == true){
                    for (int i = k+1; i < playCol; i++){
                        grid[playRow -1][i] = 'W';
                    }
                }
            }
        }
        else if (player.equals("Black")){
            if (playCol != 1 && grid[playRow-1][playCol-2] == 'W'){
                int k = playCol-1;
                while (k >= 0 && flipped == false){
                    if (grid[playRow -1][k] == 'B'){
                        flipped = true;
                    }
                    k--;
                }
                if (flipped == true){
                    for (int i = k+1; i < playCol; i++){
                        grid[playRow -1][i] = 'B';
                    }
                }
            }
        }
        return flipped;
    }
    public static boolean isFlip(char[][] grid, int playCol, int playRow, String player){
        if (flipPointN(grid, playCol, playRow, player) == true || flipPointE(grid, playCol, playRow, player) == true
                || flipPointW(grid, playCol, playRow, player) == true || flipPointS(grid, playCol, playRow, player) == true
                || flipPointNW(grid, playCol, playRow, player) == true || flipPointSW(grid, playCol, playRow, player) == true
                || flipPointNE(grid, playCol, playRow, player) == true || flipPointSE(grid, playCol, playRow, player) == true){
            return true;
        }
        return false;
    }

    public static boolean flipPointNW (char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            int k = playRow-2;
            int j = playCol-2;
            if ((playRow != 1 && playCol != 1) && grid[playRow-2][playCol-2] == 'B'){
                while (k >= 0 && j >= 0 && flipped == false){
                    if (grid[k][j] == 'W'){
                        flipped = true;
                    }
                    k--;
                    j--;
                }
            }
            if (flipped == true){
                int i = k+1;
                int n = j+1;
                while (i < playRow && n < playCol){
                    grid[i][n] = 'W';
                    i ++;
                    n++;
                }
            }
        }
        if (player.equals("Black")){
            int k = playRow-2;
            int j = playCol-2;
            if ((playRow != 1 && playCol != 1) && grid[playRow-2][playCol-2] == 'W'){
                while (k >= 0 && j >= 0 && flipped == false){
                    if (grid[k][j] == 'B'){
                        flipped = true;
                    }
                    k--;
                    j--;
                }
            }
            if (flipped == true){
                int i = k+1;
                int n = j+1;
                while (i < playRow && n < playCol){
                    grid[i][n] = 'B';
                    i ++;
                    n++;
                }
            }
        }
        return flipped;
    }

    public static boolean flipPointSW (char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            int k = playRow;
            int j = playCol-2;
            if ((playRow != 8 && playCol != 1) && grid[playRow][playCol-2] == 'B'){
                while (k <=7 && j >= 0 && flipped == false){
                    if (grid[k][j] == 'W'){
                        flipped = true;
                    }
                    k++;
                    j--;
                }
            }
            if (flipped == true){
                int i = k-1;
                int n = j+1;
                while (i >= playRow && n < playCol){
                    grid[i][n] = 'W';
                    i --;
                    n++;
                }
            }
        }
        if (player.equals("Black")){
            int k = playRow;
            int j = playCol-2;
            if ((playRow != 8 && playCol != 1) && grid[playRow][playCol-2] == 'W'){
                while (k <=7 && j >= 0 && flipped == false){
                    if (grid[k][j] == 'B'){
                        flipped = true;
                    }
                    k++;
                    j--;
                }
            }
            if (flipped == true){
                int i = k-1;
                int n = j+1;
                while (i >= playRow && n < playCol){
                    grid[i][n] = 'B';
                    i --;
                    n++;
                }
            }
        }
        return flipped;
    }

    public static boolean flipPointNE (char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            int k = playRow-2;
            int j = playCol;
            if ((playRow != 1 && playCol != 8) && grid[playRow-2][playCol] == 'B'){
                while (k >= 0 && j <= 7 && flipped == false){
                    if (grid[k][j] == 'W'){
                        flipped = true;
                    }
                    k--;
                    j++;
                }
            }
            if (flipped == true){
                int i = k+1;
                int n = j-1;
                while (i < playRow && n >= playCol){
                    grid[i][n] = 'W';
                    i ++;
                    n--;
                }
            }
        }
        if (player.equals("Black")){
            int k = playRow-2;
            int j = playCol;
            if ((playRow != 1 && playCol != 8) && grid[playRow-2][playCol] == 'W'){
                while (k >= 0 && j <= 7 && flipped == false){
                    if (grid[k][j] == 'B'){
                        flipped = true;
                    }
                    k--;
                    j++;
                }
            }
            if (flipped == true){
                int i = k+1;
                int n = j-1;
                while (i < playRow && n >= playCol){
                    grid[i][n] = 'B';
                    i ++;
                    n--;
                }
            }
        }
        return flipped;
    }

    public static boolean flipPointSE (char[][] grid, int playCol, int playRow, String player){
        boolean flipped = false;
        if (player.equals("White")){
            int k = playRow;
            int j = playCol;
            if ((playRow != 8 && playCol != 8) && grid[playRow][playCol] == 'B'){
                while (k <= 7 && j <= 7 && flipped == false){
                    if (grid[k][j] == 'W'){
                        flipped = true;
                    }
                    k++;
                    j++;
                }
            }
            if (flipped == true){
                int i = k-1;
                int n = j-1;
                while (i >= playRow && n >= playCol){
                    grid[i][n] = 'W';
                    i --;
                    n--;
                }
            }
        }
        if (player.equals("Black")){
            int k = playRow;
            int j = playCol;
            if ((playRow != 8 && playCol != 8) && grid[playRow][playCol] == 'W'){
                while (k <= 7 && j <= 7 && flipped == false){
                    if (grid[k][j] == 'B'){
                        flipped = true;
                    }
                    k++;
                    j++;
                }
            }
            if (flipped == true){
                int i = k-1;
                int n = j-1;
                while (i >= playRow && n >= playCol){
                    grid[i][n] = 'B';
                    i --;
                    n--;
                }
            }
        }
        return flipped;
    }


    public static void placeToken (char[][] grid, int playCol, int playRow, String player) {
        if (player.equals("White")){
            grid[playRow-1][playCol-1] = 'W';
        }
        else if (player.equals("Black")){
            grid[playRow-1][playCol-1] = 'B';
        }
    }
    public static boolean isWinner (char[][] grid, String player){
        return false;
    }

}

