public class MakeBottomNum {
    void newNum() {
        for (int i = 1; i <= UtilVar.MAP_W; i++) {
            for (int j = 1; j <= UtilVar.MAP_H; j++) {
                if (UtilVar.BottomData[i][j] == -1) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (UtilVar.BottomData[k][l] >= 0) {
                                UtilVar.BottomData[k][l]++;
                            }
                        }
                    }
                }
            }
        }
    }
}
