// Write the entire class for q3(b) below. Do not remove this comment.
import java.util.List;

class UpdateRoute extends Commmand<City> {

    private static final int PARAM_POSITION_START_LOCATION = 0;
    private static final int PARAM_POSITION_END_LOCATION = 1;
    private static final int PARAM_POSITION_DISTANCE = 2;
    private static final String MESG_UPDATED = 
        "Route from %s to %s with distance %skm updated";

    private final List<String> list;

    UpdateRoute(List<String> list) {
        this.list = list;
    }

    public City execute(City city) {
        String newStartLocation = list.get(PARAM_POSITION_START_LOCATION);
        String newEndLocation = list.get(PARAM_POSITION_END_LOCATION);
        String distance = list.get(PARAM_POSITION_DISTANCE);

        Route other = new Route(newStartLocation, newEndLocation, distance);

        String s = String.format(MESG_UPDATED, newStartLocation, newEndLocation, distance);
        System.out.println(s);

        return city.updateRoute(other);
    }
}