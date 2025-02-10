package BloomFilter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Random;

/* 
 * O que é um Bloom Filter?

É uma estrutura de dados probabilística que serve para testar a existência de um elemento em um conjunto, de maneira eficiente em termos de memória e tempo.
	•	Ele pode dizer com certeza se um item NÃO está no conjunto.
	•	Mas pode ter falsos positivos (ele pode dizer que um item está no conjunto quando não está).

Como funciona?
	1.	Criamos um array de bits.
	2.	Quando adicionamos um elemento, ele passa por múltiplas funções de hash, que marcam posições no array de bits.
	3.	Quando verificamos um elemento, recalculamos os hashes e verificamos se todas as posições estão marcadas.
	•	Se todas as posições estão preenchidas, o item pode estar presente (mas não há 100% de certeza).
	•	Se qualquer posição não está preenchida, então sabemos que o item não está presente.
 * 
 */
/**
 * A BloomFilter is a probabilistic data structure that is used to test whether
 * an element is a member of a set. It is highly space-efficient but allows for
 * a small probability of false positives.
 *
 * @param <T> the type of elements to be stored in the BloomFilter
 */
public class BloomFilter<T> {

    private final int size;
    private final BitSet bitSet;
    private final int hashFunctions;
    private final MessageDigest md;

    public BloomFilter(int size, int hashFunctions) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = hashFunctions;

        try {
            this.md = MessageDigest.getInstance(
                    "MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found!");
        }
    }

    private int[] getHashes(T element) {
        byte[] bytes = element.toString().getBytes();

        md.update(bytes);

        byte[] digest = md.digest();

        int[] hashes = new int[hashFunctions];

        Random random = new Random(new String(digest).hashCode());

        for (int i = 0; i < hashFunctions; i++) {
            hashes[i] = Math.abs((random.nextInt(size)));
        }

        return hashes;
    }

    public void add(T element) {
        for (int hash : getHashes(element)) {
            bitSet.set(hash, true);
        }
    }

    public boolean isPossiblyContained(T element) {
        for (int hash : getHashes(element)) {
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }
}
