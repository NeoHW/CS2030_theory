class Route {
    private final String origin;
    private final String destination;
    private final String distance;
    
    // Answer q1(a) below. Do not remove this comment.
    Route(String origin, String destination, String distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }




    // Answer q1(b) below. Do not remove this comment.
    Route(String origin, String destination) {
        this.origin = origin.toLowerCase();
        this.destination = destination.toLowerCase();
        this.distance = "";
    }



    // Answer q1(c) below. Do not remove this comment.
    String getDistance() {
        return this.distance;
    }




    // Answer q2(d) below. Do not remove this comment.
    public String toString() {
        return this.origin + " --> " + this.destination;
    }




} // end of class Route. Do not remove this comment.
