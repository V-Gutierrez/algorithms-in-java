package BloomFilter;

public class BloomFilterTest {

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = new BloomFilter<>(1000, 75);

        bloomFilter.add("Victor");
        bloomFilter.add("Gutierrez");

        System.out.println(bloomFilter.isPossiblyContained("Victor"));
        System.out.println(bloomFilter.isPossiblyContained("G"));
        System.out.println(bloomFilter.isPossiblyContained("Vic"));
        System.out.println(bloomFilter.isPossiblyContained("Gutierrez"));
    }
}
