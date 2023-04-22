// part c and d are wrong (nextLight attribute and setNextLight wrong)

abstract class TrafficLight {
    private final String color;
    protected final TrafficLight nextLight;

    TrafficLight(String color) {
        this.color = color;
        this.nextLight = null;
    }

    TrafficLight(String color, TrafficLight nextLight) {
        this.color = color;
        this.nextLight = nextLight;
    }

    abstract TrafficLight toggle();

    abstract TrafficLight setNextLight(TrafficLight next);

    @Override
    public String toString() {
        return this.color;
    }
}

// subclasses redlight means trafficLight is the parent!
class RedLight extends TrafficLight {
    
    RedLight() {
        super("red");
    }

    RedLight(TrafficLight next) {
        super("red", next);
    }
    
    TrafficLight setNextLight(TrafficLight next) {
        return new RedLight(next);
    }

    @Override
    TrafficLight toggle() {
        return nextLight;
    }
}

class GreenLight extends TrafficLight {
    
    GreenLight() {
        super("green");
    }

    GreenLight(TrafficLight next) {
        super("green", next);
    }

    TrafficLight setNextLight(TrafficLight next) {
        return new GreenLight(next);
    }

    @Override
    TrafficLight toggle() {
        return nextLight;
    }
}

// b
// jshell: toggling(new RedLight(), 5)

// c

class AmberLight extends TrafficLight {
    
    AmberLight() {
        super("amber");
    }

    AmberLight(TrafficLight next) {
        super("amber", next);
    }

    TrafficLight setNextLight(TrafficLight next) {
        return new AmberLight(next);
    }
    
    @Override
    TrafficLight toggle() {
        return nextLight;
    }
}

//d 
// jshell:
// TrafficLight t = new RedLight();
// t = t.setNextLight(new AmberLight().setNextLight(new GreenLight().setNextLight(t)));
// toggling(t, 7);