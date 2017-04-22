package agut_giralt.androidpractreversi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nil and Jaume.
 * This class will save the positions as a grid in order to simplify
 * other classes
 */

public class Movements implements Parcelable {
    public static final Creator<Movements> CREATOR = new Creator<Movements>() {
        @Override
        public Movements createFromParcel(Parcel in) {
            return new Movements(in);
        }

        @Override
        public Movements[] newArray(int size) {
            return new Movements[size];
        }
    };
    private int row;
    private int column;

    public Movements(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //For save and recuperate the instances
    //http://androcode.es/2012/12/trabajando-con-parcelables/

    protected Movements(Parcel in) {
        row = in.readInt();
        column = in.readInt();
    }

    public int getPosition(int size) {
        return row * size + column;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(row);
        parcel.writeInt(column);
    }
}
