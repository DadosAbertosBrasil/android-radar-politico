package br.edu.ifce.engcomp.francis.radarpolitico.models;

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

    public int getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }
}
