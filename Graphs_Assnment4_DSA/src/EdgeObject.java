/**
 * Created by Sonya on 20.11.16.
 */
public class EdgeObject {
    Double distance;
    Double time;
    Double cost;

    public EdgeObject(Double dist, Double t, Double c) {
        this.distance = dist;
        this.time = t;
        this.cost = c;
    }
    public EdgeObject() {
        this.distance = null;
        this.time = null;
        this.cost = null;
    }

    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public Double getTime() {
        return time;
    }
    public void setTime(Double time) {
        this.time = time;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
}
