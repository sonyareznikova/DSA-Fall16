/**
 * Created by Sonya on 21.11.16.
 */
public class VertexObject<T> implements Comparable<VertexObject<T>> {

    T vertexData;
    VertexObject<T> previous;
    Double distanceFromPrevious;

    public VertexObject(T vertexData) {
        this.vertexData = vertexData;
        this.previous = null;
        this.distanceFromPrevious = 0.0;
    }

    public T getVertexData() {
        return vertexData;
    }

    public void setVertexData(T vertexData) {
        this.vertexData = vertexData;
    }

    public VertexObject<T> getPrevious() {
        return previous;
    }

    public void setPrevious(VertexObject<T> previous) {
        this.previous = previous;
    }

    public Double getDistanceFromPrevious() {
        return distanceFromPrevious;
    }

    public void setDistanceFromPrevious(Double distanceFromPrevious) {
        this.distanceFromPrevious = distanceFromPrevious;
    }

    @Override
    /*public int compareTo(VertexObject<T> otherVertex) {
        int distanceDiff = this.distanceFromPrevious.compareTo(otherVertex.distanceFromPrevious);
        if (distanceDiff != 0) return distanceDiff;
        else return 0;
    }*/
    public int compareTo(VertexObject<T> otherVertex) {
        int distanceDiff = this.distanceFromPrevious.compareTo(otherVertex.distanceFromPrevious);
        if (distanceDiff != 0) return distanceDiff;
        else return 0;
    }

}
