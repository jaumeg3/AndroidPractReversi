package agut_giralt.androidpractreversi.utils;

import java.util.List;

/**
 * Created by Nil Agut and Jaume Giralt.
 */

class ArtificialIntelligence {
    private static int size8[][] = {
            {50, -1, 5, 2, 2, 5, -1, 50},
            {-1, -10, 1, 1, 1, 1, -10, -1},
            {5, 1, 1, 1, 1, 1, 1, 5},
            {2, 1, 1, 0, 0, 1, 1, 2},
            {2, 1, 1, 0, 0, 1, 1, 2},
            {5, 1, 1, 1, 1, 1, 1, 5},
            {-1, -10, 1, 1, 1, 1, -10, -1},
            {50, -1, 5, 2, 2, 5, -1, 50}
    };

    private static int size6[][] = {
            {50, -1, 5, 5, -1, 50},
            {-1, -10, 1, 1, -10, -1},
            {5, 1, 0, 0, 1, 5},
            {5, 1, 0, 0, 1, 5},
            {-1, -10, 1, 1, -10, -1},
            {50, -1, 5, 5, -1, 50}
    };

    private static int size4[][] = {
            {50, -10, -10, 50},
            {-10, 0, 0, -10},
            {-10, 0, 0, -10},
            {50, -10, -10, 50}
    };

    private int[][] actual;
    private int size;

    ArtificialIntelligence(int size) {
        this.size = size;
        switch (this.size) {
            case 4:
                this.actual = size4;
                break;
            case 6:
                this.actual = size6;
                break;
            case 8:
                this.actual = size8;
                break;
        }
    }

    int getBestMovement(List<Integer> positions) {
        int bestValue = -50;
        int bestPosition = -1;
        for (int x = 0; x < positions.size(); x++) {
            if (this.actual[positions.get(x) / size][positions.get(x) % size] > bestValue) {
                bestPosition = positions.get(x);
                bestValue = this.actual[positions.get(x) / size][positions.get(x) % size];
            }
        }
        return bestPosition;
    }
}