package agut_giralt.androidpractreversi.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jaume on 20/04/17.
 */

public class GameBoard implements Parcelable {
    public static final Creator<GameBoard> CREATOR = new Creator<GameBoard>() {
        @Override
        public GameBoard createFromParcel(Parcel in) {
            return new GameBoard(in);
        }

        @Override
        public GameBoard[] newArray(int size) {
            return new GameBoard[size];
        }
    };
    boolean timeEnd = false;
    // CELL FREE = 0 / CELL PLAYER = 1 / CELL COMPUTER = 2
    private int turn = 1;
    private int size;
    private int[][] gameBoard;
    private List<Integer> UserCells;
    private List<Integer> ComputerCells;
    private List<Integer> PossibleCells = new ArrayList<>();
    private HashMap<Integer, List<Integer>> cellsToChange = new HashMap<>();
    private CountDown timer;

    public GameBoard(int size) {
        this.size = size;
        this.gameBoard = new int[size][size];
    }

    private GameBoard(Parcel in) {
        turn = in.readInt();
        size = in.readInt();
    }

    public void initGameBoard(boolean withTime, int time) {
        UserCells = new ArrayList<>();
        ComputerCells = new ArrayList<>();
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                this.gameBoard[x][y] = 0;
            }
        }
        firstMoves();
        getPositionsPossible();
        if (withTime) {
            timer = new CountDown(time * Variables.SEGON, Variables.SEGON, this);
            timer.start();
        }
    }

    private void firstMoves() {
        int halfSize = this.size / 2;
        this.gameBoard[halfSize - 1][halfSize - 1] = 1;
        this.gameBoard[halfSize - 1][halfSize] = 2;
        this.gameBoard[halfSize][halfSize - 1] = 2;
        this.gameBoard[halfSize][halfSize] = 1;
        UserCells.add((halfSize - 1) * size + (halfSize - 1));
        ComputerCells.add((halfSize - 1) * size + (halfSize));
        ComputerCells.add((halfSize * size) + (halfSize - 1));
        UserCells.add((halfSize * size) + halfSize);
    }

    void fillCell(int position) {
        if (this.turn == 1) {
            if (this.ComputerCells.contains(position)) {
                this.ComputerCells.remove(ComputerCells.indexOf(position));
            }
            if (!this.UserCells.contains(position)) {
                this.UserCells.add(position);
                gameBoard[position / size][position % size] = Variables.PLAYER1;
            }
        } else {
            if (this.UserCells.contains(position)) {
                this.UserCells.remove(UserCells.indexOf(position));
            }
            if (!this.ComputerCells.contains(position)) {
                this.ComputerCells.add(position);
                gameBoard[position / size][position % size] = Variables.PLAYER2;
            }
        }
    }

    void changeTurn() {
        if (turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }
    }

    private int otherPlayer() {
        if (turn == 1) return 2;
        else return 1;
    }

    List<Integer> getPositionsUser() {
        return UserCells;
    }

    List<Integer> getPositionsComputer() {
        return ComputerCells;
    }

    List<Integer> getCellsToChange(int position) {
        return cellsToChange.get(position);
    }

    int getTime() {
        return (int) timer.getTime();
    }

    List<Integer> getPositionsPossibleCells() {
        return PossibleCells;
    }

    void getPositionsPossible() {
        PossibleCells.clear();
        cellsToChange.clear();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isValidMovement(i, j, turn)) {
                    PossibleCells.add((i * size) + j);
                }
            }
        }
    }

    private boolean isValidMovement(int row, int column, int player) {
        if (row == 0 && column == 0) {
            return isEmptyCell(row, column) && (directionEast(row, column, player) ||
                    directionSouthEast(row, column, player) || directionSouth(row, column, player));
        } else if (row == 0 && column == size - 1) {
            return isEmptyCell(row, column) && (directionWest(row, column, player) ||
                    directionSouthWest(row, column, player) || directionSouth(row, column, player));

        } else if (row == size - 1 && column == 0) {
            return isEmptyCell(row, column) && (directionNorth(row, column, player) ||
                    directionNorthEast(row, column, player) || directionEast(row, column, player));

        } else if (row == size - 1 && column == size - 1) {
            return isEmptyCell(row, column) && (directionNorth(row, column, player) ||
                    directionNorthWest(row, column, player) || directionWest(row, column, player));

        } else if (row == 0) {
            return isEmptyCell(row, column) && (directionEast(row, column, player) ||
                    directionSouthEast(row, column, player) || directionSouth(row, column, player) ||
                    directionSouthWest(row, column, player) || directionWest(row, column, player));

        } else if (row == size - 1) {
            return isEmptyCell(row, column) && (directionEast(row, column, player) ||
                    directionNorthWest(row, column, player) || directionNorth(row, column, player) ||
                    directionNorthEast(row, column, player) || directionWest(row, column, player));

        } else if (column == 0) {
            return isEmptyCell(row, column) && (directionEast(row, column, player) ||
                    directionSouthEast(row, column, player) || directionSouth(row, column, player) ||
                    directionNorthEast(row, column, player) || directionNorth(row, column, player));

        } else if (column == size - 1) {
            return isEmptyCell(row, column) && (directionWest(row, column, player) ||
                    directionSouthWest(row, column, player) || directionSouth(row, column, player) ||
                    directionNorthWest(row, column, player) || directionNorth(row, column, player));

        } else {
            return isEmptyCell(row, column) && (directionWest(row, column, player) ||
                    directionSouthWest(row, column, player) || directionSouth(row, column, player) ||
                    directionNorthWest(row, column, player) || directionNorth(row, column, player)
                    || directionNorthEast(row, column, player) || directionEast(row, column, player)
                    || directionSouthEast(row, column, player));
        }
    }

    private boolean isEmptyCell(int row, int column) {
        return gameBoard[row][column] == 0;
    }

    private boolean directionNorthWest(int row, int column, int player) {
        boolean condition1 = gameBoard[row - 1][column - 1] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int x = row, y = column; x >= 0 && y >= 0 && !find; x--, y--) {
            path.add(x * size + y);
            if (gameBoard[x][y] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionNorthEast(int row, int column, int player) {
        boolean condition1 = gameBoard[row - 1][column + 1] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int x = row, y = column; x >= 0 && y < size && !find; x--, y++) {
            path.add(x * size + y);
            if (gameBoard[x][y] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionNorth(int row, int column, int player) {
        boolean condition1 = gameBoard[row - 1][column] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int x = row; x >= 0 && !find; x--) {
            path.add(x * size + column);
            if (gameBoard[x][column] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionSouthWest(int row, int column, int player) {
        boolean condition1 = gameBoard[row + 1][column - 1] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int x = row, y = column; x < size && y >= 0 && !find; x++, y--) {
            path.add(x * size + y);
            if (gameBoard[x][y] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionWest(int row, int column, int player) {
        boolean condition1 = gameBoard[row][column - 1] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int y = column; y >= 0 && !find; y--) {
            path.add(row * size + y);
            if (gameBoard[row][y] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionSouth(int row, int column, int player) {
        boolean condition1 = gameBoard[row + 1][column] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int x = row; x < size && !find; x++) {
            path.add(x * size + column);
            if (gameBoard[x][column] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionSouthEast(int row, int column, int player) {
        boolean condition1 = gameBoard[row + 1][column + 1] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int x = row, y = column; x < size && y > size && !find; x++, y++) {
            path.add(x * size + y);
            if (gameBoard[x][y] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private boolean directionEast(int row, int column, int player) {
        boolean condition1 = gameBoard[row][column + 1] == otherPlayer();
        boolean condition2 = false;
        List<Integer> path = new ArrayList<>();
        boolean find = false;
        for (int y = column; y < size && !find; y++) {
            path.add(row * size + y);
            if (gameBoard[row][y] == player && condition1) {
                condition2 = true;
                createPath(path, row * size + column);
                find = true;
            }
        }
        return condition1 && condition2;
    }

    private void createPath(List<Integer> path, int initialPosition) {
        if (cellsToChange.containsKey(initialPosition)) {
            List<Integer> data = cellsToChange.get(initialPosition);
            cellsToChange.put(initialPosition, joinList(data, path));
        } else {
            cellsToChange.put(initialPosition, path);
        }
    }

    private List<Integer> joinList(List<Integer> data, List<Integer> path) {
        for (int x = 0; x < path.size(); x++) {
            if (!data.contains(x)) {
                data.add(path.get(x));
            }
        }
        return data;
    }

    boolean isEnd() {
        return size * size - getPositionsUser().size() - getPositionsComputer().size() == 0;

    }

    //For save and recuperate the instances
    //http://androcode.es/2012/12/trabajando-con-parcelables/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(gameBoard);
        parcel.writeInt(size);
        parcel.writeInt(turn);
        parcel.writeList(UserCells);
        parcel.writeList(ComputerCells);
    }
}
