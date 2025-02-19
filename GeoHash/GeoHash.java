
public class GeoHash {

    private static final char[] BASE32_CHARS = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final java.util.Map<Character, Integer> BASE32_MAP = new java.util.HashMap<>();

    static {
        for (int i = 0; i < BASE32_CHARS.length; i++) {
            BASE32_MAP.put(BASE32_CHARS[i], i);
        }
    }

    public static String encode(double latitude, double longitude, int precision) {
        double[] latInterval = {-90.0, 90.0};
        double[] lonInterval = {-180.0, 180.0};

        StringBuilder geohash = new StringBuilder();

        boolean isEven = true;

        int bit = 0;
        int ch = 0;

        while (geohash.length() < precision) {
            double mid;

            if (isEven) {
                mid = (lonInterval[0] + lonInterval[1]) / 2;

                if (longitude > mid) {
                    ch |= (1 << (4 - bit));

                    lonInterval[0] = mid;
                } else {
                    lonInterval[1] = mid;
                }
            } else {
                mid = (latInterval[0] + latInterval[1]) / 2;

                if (latitude > mid) {
                    ch |= (1 << 4 - bit);

                    latInterval[0] = mid;
                } else {
                    latInterval[1] = mid;
                }
            }

            isEven = !isEven;

            if (bit < 4) {
                bit++;
            } else {
                geohash.append(BASE32_CHARS[ch]);

                bit = 0;
                ch = 0;
            }
        }

        return geohash.toString();
    }

    public static double[] decode(String geohash) {

        double[] latInterval = {-90.0, 90.0};
        double[] lonInterval = {-180.0, 180.0};

        boolean isEven = true;

        for (int i = 0; i < geohash.length(); i++) {
            char c = geohash.charAt(i);

            int value = BASE32_MAP.get(c);

            for (int mask = 16; mask != 0; mask >>= 1) {

                if (isEven) {

                    double mid = (lonInterval[0] + lonInterval[1]) / 2;

                    if ((value & mask) != 0) {
                        lonInterval[0] = mid;
                    } else {
                        lonInterval[1] = mid;
                    }
                } else {

                    double mid = (latInterval[0] + latInterval[1]) / 2;
                    if ((value & mask) != 0) {
                        latInterval[0] = mid;
                    } else {
                        latInterval[1] = mid;
                    }
                }

                isEven = !isEven;
            }
        }

        double latitude = (latInterval[0] + latInterval[1]) / 2;
        double longitude = (lonInterval[0] + lonInterval[1]) / 2;

        return new double[]{latitude, longitude};
    }
}
