import java.util.HashSet;
import java.util.Set;

class Cell {
    int x, y;
    Cell(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell c = (Cell) obj;
            return c.x == x && c.y == y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}

public class GameOfLife {
    private static final int[][] neighbors = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
    
    public static Set<Cell> nextGeneration(Set<Cell> aliveCells) {
        Set<Cell> newAliveCells = new HashSet<>();
        Set<Cell> deadNeighbors = new HashSet<>();
        
        for (Cell cell : aliveCells) {
            int aliveNeighbors = 0;
            for (int[] n : neighbors) {
                Cell neighbor = new Cell(cell.x + n[0], cell.y + n[1]);
                if (aliveCells.contains(neighbor)) {
                    aliveNeighbors++;
                } else {
                    deadNeighbors.add(neighbor);
                }
            }
            if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                newAliveCells.add(cell);  // live cell survives
            }
        }
        
        for (Cell deadCell : deadNeighbors) {
            int aliveNeighbors = 0;
            for (int[] n : neighbors) {
                if (aliveCells.contains(new Cell(deadCell.x + n[0], deadCell.y + n[1]))) {
                    aliveNeighbors++;
                }
            }
            if (aliveNeighbors == 3) {
                newAliveCells.add(deadCell);  // dead cell becomes alive
            }
        }
        
        return newAliveCells;
    }

    public static void main(String[] args) {
        Set<Cell> aliveCells = new HashSet<>();
        aliveCells.add(new Cell(1, 1));
        aliveCells.add(new Cell(1, 0));
        aliveCells.add(new Cell(1, 2));

        System.out.println("Next generation:");
        Set<Cell> nextGen = nextGeneration(aliveCells);
        for (Cell cell : nextGen) {
            System.out.println(cell.x + "," + cell.y);
        }
    }
}
