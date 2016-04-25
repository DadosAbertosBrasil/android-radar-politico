package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers;

import android.os.Parcel;
import android.os.Parcelable;

/*
   Created by francisco on 21/04/16.
  */
public class MyParcellable implements Parcelable {
    private int mData;

    public static final Parcelable.Creator <MyParcellable>CREATOR = new Parcelable.Creator<MyParcellable>(){
        public MyParcellable createFromParcel (Parcel in){
        return new MyParcellable(in);
    }

        public MyParcellable[] newArray (int size){
        return new MyParcellable[size];
        }
    };

    private MyParcellable(Parcel in) {
        mData = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
}
           