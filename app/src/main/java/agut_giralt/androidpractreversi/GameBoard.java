package agut_giralt.androidpractreversi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
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
    // CELL FREE = 0 / CELL PLAYER = 1 / CELL COMPUTER = 2
    public int turn = 1;
    private int size;
    private int[][] gameBoard;
    private List<Movements> UserCells;
    private List<Movements> ComputerCells;

    public GameBoard(int size) {
        this.size = size;
        this.gameBoard = new int[size][size];
    }

    protected GameBoard(Parcel in) {
        size = in.readInt();
        UserCells = in.createTypedArrayList(Movements.CREATOR);
        ComputerCells = in.createTypedArrayList(Movements.CREATOR);
        turn = in.readInt();
    }

    public void initGameBoard() {
        UserCells = new ArrayList<Movements>();
        ComputerCells = new ArrayList<Movements>();
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                this.gameBoard[x][y] = 0;
            }
        }
        firstMoves();
    }

    private void firstMoves() {
        int halfSize = this.size / 2;
        this.gameBoard[halfSize - 1][halfSize - 1] = 1;
        this.gameBoard[halfSize - 1][halfSize] = 2;
        this.gameBoard[halfSize][halfSize - 1] = 2;
        this.gameBoard[halfSize][halfSize] = 1;
        UserCells.add(new Movements(halfSize - 1, halfSize - 1));
        ComputerCells.add(new Movements(halfSize - 1, halfSize));
        ComputerCells.add(new Movements(halfSize, halfSize - 1));
        UserCells.add(new Movements(halfSize, halfSize));
    }

    public void fillCell(int position) {
        this.gameBoard[position / this.size][position % this.size] = this.turn;
        Movements movement = new Movements(position / this.size, position % this.size);
        if (this.turn == 1) {
            if (this.ComputerCells.contains(movement)) {
                this.ComputerCells.remove(movement);
            }
            this.UserCells.add(movement);
        } else {
            if (this.UserCells.contains(movement)) {
                this.UserCells.remove(movement);
            }
            this.ComputerCells.add(movement);
        }
    }

    public List<Movements> getUserCells() {
        return this.UserCells;
    }

    public List<Integer> getPositionsUser() {
        List<Integer> arrayList = new ArrayList<Integer>();
        for (int x = 0; x < UserCells.size(); x++) {
            Movements temporal = UserCells.get(x);
            arrayList.add(temporal.getPosition(size));
        }
        return arrayList;
    }

    //For save and recuperate the instances
    //http://androcode.es/2012/12/trabajando-con-parcelables/

    public List<Integer> getPositionsComputer() {
        List<Integer> arrayList = new ArrayList<Integer>();
        for (int x = 0; x < ComputerCells.size(); x++) {
            Movements temporal = ComputerCells.get(x);
            arrayList.add(temporal.getPosition(size));
        }
        return arrayList;
    }

    public List<Movements> getComputerCells() {
        return this.ComputerCells;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(gameBoard);
        parcel.writeInt(size);
        parcel.writeInt(turn);
        parcel.writeTypedList(UserCells);
        parcel.writeTypedList(ComputerCells);
    }
}
