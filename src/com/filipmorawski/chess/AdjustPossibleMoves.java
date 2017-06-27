package com.filipmorawski.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

abstract class AdjustPossibleMoves {
    int color;
    ArrayList<String> possibleMoves;

    abstract void adjustMoves(String name, String position);

}
