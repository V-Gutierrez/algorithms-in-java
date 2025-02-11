package ConsistentHashing;

public class ConsistentHashingTest {

    public static void main(String[] args) {
        ConsistentHashing<String> hashRing = new ConsistentHashing<>(3, new ConsistentHashing.HashFunction());

        hashRing.addNode("Node1");
        hashRing.addNode("Node2");
        hashRing.addNode("Node3");
        hashRing.addNode("Node4");

        System.out.println("Key1 is assigned to " + hashRing.getNode("Key1"));
        System.out.println("Key2 is assigned to " + hashRing.getNode("Key2"));
        System.out.println("Key3 is assigned to " + hashRing.getNode("Key3"));

        hashRing.removeNode("Node2");

        System.out.println(hashRing.getNode("Key1"));

        hashRing.removeNode("Node1");

        System.out.println(hashRing.getNode("Key1"));

    }
}
