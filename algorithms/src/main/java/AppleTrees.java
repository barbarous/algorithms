import java.util.*;

/**
 * 2 people need to harvest apples in garden of trees 1...N
 * 1st man harvest K trees
 * 2nd man harvest L trees
 * they should not intersect and have to harvest as much as possible.
 * <p>
 * e.g. [2,7,4,8,2] K=2 and L=2
 * 1st harvests [7,4]
 * 2nd harvests [8,2]
 * <p>
 * return 7+4+8+2
 */
class AppleTrees {

    List<DailyHarvest> dailyHarvests = new ArrayList<>();

    public int solution(List<Integer> garden, int firstRange, int secondRange) {

        walkThroughTheGarden(garden, firstRange, secondRange);
        Collections.reverse(new ArrayList(garden));
        walkThroughTheGarden(garden, firstRange, secondRange);

        return dailyHarvests.stream().distinct().peek(e -> e.print()).max(Comparator.comparing(DailyHarvest::getHarvest)).map(e -> e.getHarvest()).orElse(-1);
    }

    void walkThroughTheGarden(List<Integer> garden, int firstRange, int secondRange) {
        for (int x = 0; x + firstRange <= garden.size(); x++) {
            SubRange subRange1 = new SubRange(x, firstRange, garden);
            for (int y = 0; y + secondRange <= garden.size(); y++) {
                SubRange subRange2 = new SubRange(y, secondRange, garden);
                if (!subRange1.isIntersectedWith(subRange2)) {
                    dailyHarvests.add(new DailyHarvest(subRange1, subRange2));
                }
            }
        }
    }
}

class SubRange {

    public int firstPosition;
    public int rangeSize;
    public List<Integer> garden;

    SubRange(int firstPosition, int rangeSize, List<Integer> garden) {
        this.rangeSize = rangeSize;
        this.firstPosition = firstPosition;
        this.garden = garden;
    }

    boolean isIntersectedWith(SubRange range) {

        if (firstPosition < range.firstPosition && endPosition() < range.firstPosition ||
                firstPosition > range.endPosition() && endPosition() > range.endPosition()
        ) return false;
        return true;
    }

    int getApplesCount() {
        return garden.subList(firstPosition, firstPosition + rangeSize).stream().mapToInt(e -> e).sum();
    }

    int endPosition() {
        return firstPosition + rangeSize - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubRange subRange = (SubRange) o;
        return firstPosition == subRange.firstPosition &&
                rangeSize == subRange.rangeSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPosition, rangeSize);
    }
}

class DailyHarvest {
    DailyHarvest(SubRange firstHarvest, SubRange secondHarvest) {
        this.firstHarvest = firstHarvest;
        this.secondHarvest = secondHarvest;
    }

    public SubRange firstHarvest;
    public SubRange secondHarvest;

    Integer getHarvest() {
        return firstHarvest.getApplesCount() + secondHarvest.getApplesCount();
    }

    void print() {
        System.out.println("[" + firstHarvest.firstPosition + ".." + firstHarvest.endPosition() + "] [" + secondHarvest.firstPosition + ".." + secondHarvest.endPosition() + "] [" + getHarvest() + "]");
    }

    @Override
    public boolean equals(Object harvest){
        if (harvest == null){
            return false;
        }
        if (!(harvest instanceof DailyHarvest)){
            return false;
        }

        if (firstHarvest.equals(((DailyHarvest) harvest).firstHarvest) &&
                secondHarvest.equals(((DailyHarvest) harvest).secondHarvest)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return firstHarvest.firstPosition + secondHarvest.firstPosition;
    }

}