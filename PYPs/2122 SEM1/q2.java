import java.util.List;

abstract class TrafficLight {
    private final String light;
    private final TrafficLight nextLight;

    TrafficLight(String light) {
        this(light, new ImList<>());
    }

    TrafficLight(String light, TrafficLight nexLight) {
        this.light = light;
        this.nextLight = nexLight;
    }

    TrafficLight(String light, ImList<TrafficLight> lights) {
        this.light = light;
        TrafficLight lastLight = this;
        for (int i = lights.size() - 1; i >= 0; i--) {
            lastLight = lights.get(i).setNext(lastLight);
        }
        this.nextLight = lastLight;
    }

    abstract TrafficLight setNext(TrafficLight nextLight);

    TrafficLight toggle() {
        return nextLight;
    }

    @Override
    public String toString() {
        return light;
    }
}


// subclasses redlight means trafficLight is the parent!
class RedLight extends TrafficLight {
    RedLight() {
        super("Red");
    }

    RedLight(ImList<TrafficLight> lights) {
        super("Red", lights);
    }

    RedLight(TrafficLight nextLight) {
        super("Red", nextLight);
    }

    RedLight setNext(TrafficLight nextLight) {
        return new RedLight(nextLight);
    }
}

class GreenLight extends TrafficLight {
    
    GreenLight() {
        super("Green");
    }

    GreenLight(ImList<TrafficLight> lights) {
        super("Green", lights);
    }

    GreenLight(TrafficLight nextLight) {
        super("Green", nextLight);
    }

    GreenLight setNext(TrafficLight nextLight) {
        return new GreenLight(nextLight);
    }
}

// b
// jshell: toggling(new RedLight(), 5)

// c

class AmberLight extends TrafficLight {
    
    AmberLight() {
        super("Amber");
    }

    AmberLight(ImList<TrafficLight> lights) {
        super("Amber", lights);
    }

    AmberLight(TrafficLight nextLight) {
        super("Amber", nextLight);
    }

    AmberLight setNext(TrafficLight nextLight) {
        return new AmberLight(nextLight);
    }
}

public static void main(String[] args) {
    // the correct ans is these few lines of code
    TrafficLight light = new RedLight(new ImList<TrafficLight>(List.of(new GreenLight(), new AmberLight())));
    for (int i = 0; i < 10; i++) {
        System.out.println(light);
        light = light.toggle();
    }
    // ans ends 

    System.out.println();
    // to show how u can change the order
    TrafficLight light2 = new RedLight(new ImList<TrafficLight>(List.of(
            new AmberLight(), new GreenLight(),
            new AmberLight(), new RedLight(),
            new AmberLight(), new GreenLight())));
    for (int i = 0; i < 10; i++) {
        System.out.println(light2);
        light2 = light2.toggle();
    }
}