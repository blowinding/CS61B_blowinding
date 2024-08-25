package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static final int SIZE = 4;

    private static final int HEXWIDTH = SIZE + 2 * (SIZE - 1);
    private static final int HEXHEIGHT = SIZE * 2;

    private static final int HEXUPPERLEN = SIZE;
    private static final int HEXLEFTLEN = 2;

    public static class OutOfTileBoundsException extends RuntimeException {
        public OutOfTileBoundsException() {
            super("Out of tile bounds");
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.TREE;
            case 2 -> Tileset.GRASS;
            case 3 -> Tileset.SAND;
            case 4 -> Tileset.MOUNTAIN;
            default -> Tileset.NOTHING;
        };
    }

    /**
     * draw one hex, which located at left upper corner
     * @param x left upper corner x
     * @param y left upper corner y
     */
    private static void addHexagon(int x, int y, TETile[][] tile) {
        if (x < 0 || x + HEXWIDTH > WIDTH || y < 0 || y + HEXHEIGHT > HEIGHT) {
            throw new OutOfTileBoundsException();
        }
        TETile random = randomTile();
        int start = (HEXWIDTH - HEXUPPERLEN) / 2;
        int end = start + HEXUPPERLEN;
        int i = 0;
        for (; i < (HEXHEIGHT - HEXLEFTLEN) / 2; i++) {
            for(int j = start; j < end; j++) {
                tile[x+j][y+i] = random;
            }
            start -= 1;
            end += 1;
        }
        for (; i < (HEXHEIGHT - HEXLEFTLEN) / 2 + HEXLEFTLEN; i++) {
            for(int j = start; j < end; j++) {
                tile[x+j][y+i] = random;
            }
        }
        for(; i < HEXHEIGHT; i++) {
            start += 1;
            end -= 1;
            for(int j = start; j < end; j++) {
                tile[x+j][y+i] = random;
            }
        }
    }

    private static void addHexagonLine(int x, int y, int num, TETile[][] tile) {
        for (int i = 0; i < num; i++) {
            addHexagon(x, y, tile);
            y += HEXHEIGHT;
        }
    }

    private static void fillInHex(TETile[][] tile) {
        addHexagonLine(0, HEXHEIGHT, 3, tile);
        addHexagonLine((HEXWIDTH + HEXUPPERLEN) / 2, HEXHEIGHT / 2, 4, tile);
        addHexagonLine(2 * (HEXWIDTH + HEXUPPERLEN) / 2, 0, 5, tile);
        addHexagonLine(3 * (HEXWIDTH + HEXUPPERLEN) / 2, HEXHEIGHT / 2, 4, tile);
        addHexagonLine(4 * (HEXWIDTH + HEXUPPERLEN) / 2, HEXHEIGHT, 3, tile);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexWorld[x][y] = Tileset.NOTHING;
            }
        }
        fillInHex(hexWorld);

        ter.renderFrame(hexWorld);
    }
}
