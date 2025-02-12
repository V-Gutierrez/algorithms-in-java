package ConsistentHashing;

import java.util.*;

/* 
 * O que é Consistent Hashing?
  *
 *  O Consistent Hashing é um algoritmo que permite distribuir objetos entre servidores/nós de maneira eficiente e minimizando redistribuições quando um novo nó entra ou sai do sistema.
 */
public class ConsistentHashing<T> {

    public static class HashFunction {

        public int hash(String key) {
            return key.hashCode() & 0x7fffffff; // Keeps it positive - hexadecimal representation of the largest 32bit signed integer 
        }
    }

    private final TreeMap<Integer, T> circle = new TreeMap<>();
    private final int numberOfReplicas;
    private final HashFunction hashFunction;

    public ConsistentHashing(int numberOfReplicas, HashFunction hashFunction) {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunction = hashFunction;
    }

    public void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hashFunction.hash(node.toString() + i);
            circle.put(hash, node);
        }
    }

    public void removeNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = hashFunction.hash(node.toString() + i);

            circle.remove(hash);
        }
    }

    public T getNode(String key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = hashFunction.hash(key);
        Map.Entry<Integer, T> entry = circle.ceilingEntry(hash);

        if (entry == null) {
            return circle.firstEntry().getValue();
        }

        return entry.getValue();
    }
}
