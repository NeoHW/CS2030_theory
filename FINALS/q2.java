import java.util.Optional;

class City {
    // Answer q2(a) below. Do not remove this comment.
    private final ImList<Route> routes;

    City() {
        this.routes = new ImList<>();
    }

    City(ImList<Route> routes) {
        this.routes = routes;
    }

    // Answer q2(b) below. Do not remove this comment.
    City updateRoute(Route route) {
        ImList<Route> newRoutes = this.routes.add(route);
        return new City(newRoutes);
    }




    // Answer q2(c) below. Do not remove this comment.
    Optional<String> getDistance(Route route) {
        return routeGetDistance(route).equals("")
            ? Optional.<String>empty()
            : Optional.<String>of(routeGetDistance(route));
    }

    String routeGetDistance(Route route) {
        return route.getDistance();
    }




} // end of class City. Do not remove this comment.
