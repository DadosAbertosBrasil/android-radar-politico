package br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.helpers;

/**
 * Created by francisco on 12/03/16.
 */
public class IndexPath {
    private int section;
    private int row;

    public IndexPath(int section, int row) {
        this.section = section;
        this.row = row;
    }

    public IndexPath() {
    }

    public int getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
