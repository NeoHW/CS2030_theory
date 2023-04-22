// (a) writing annoymous inner class for biFunction

BiFunction<Character, String, Integer> = 
    new BiFunction<Character, String, Integer>() {
            Integer apply(Character i, String j) {
                return (i + j).length();
            }
    };

// (b) writing annoymous inner class for biFunction
BiFunction<String, Integer, String> = 
    new BiFunction<String, Integer, String> {
        String apply(String x, Integer y) {
            return String.format("%s:%d", x, y);
        }
    };

//(c)
