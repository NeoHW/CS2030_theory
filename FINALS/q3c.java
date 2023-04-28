// Write the entire class for q3(c) below. Do not remove this comment.

import java.util.List;
import java.util.Optional;

class GetDistance extends Commmand<City> {

    private static final int PARAM_POSITION_START_LOCATION = 0;
    private static final int PARAM_POSITION_END_LOCATION = 1;
    private static final String MESG_NO_ROUTE = "No route exists from %s to %s!";
    private static final String MESG_DISTANCE = "Distance from %s to %s is %s";

    private final List<String> list;

    GetDistance(List<String> list) {
        this.list = list;
    }

    public City execute(City city) {
        String newStartLocation = list.get(PARAM_POSITION_START_LOCATION);
        String newEndLocation = list.get(PARAM_POSITION_END_LOCATION);

        Route other = new Route(newStartLocation, newEndLocation);
        Optional<String> os = city.getDistance(other);

        String s = os.map(x -> 
            String.format(MESG_DISTANCE, newStartLocation, newEndLocation, x))
            .orElse(String.format(MESG_NO_ROUTE, newStartLocation, newEndLocation)
        );

        System.out.println(s);

        return city;
    }
}