public class Cow {
  int position;
  int weight;
  boolean direction;
  boolean stopped = false;
  Cow left;
  Cow right;
  Cow(int position, int weight, boolean direction) {
    this.position = position;
    this.weight = weight;
    this.direction = direction;
  }
}