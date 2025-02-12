package MerkleTree;

import java.util.ArrayList;
import java.util.List;

public class MerkleTreeTest {

    public static void main(String[] args) {
        // Cria uma lista de exemplo de transações ou dados
        List<String> transactions = new ArrayList<>();
        transactions.add("tx1");  // Adiciona a transação "tx1"
        transactions.add("tx2");  // Adiciona a transação "tx2"
        transactions.add("tx3");  // Adiciona a transação "tx3"
        transactions.add("tx4");  // Adiciona a transação "tx4"

        // Calcula a Merkle Root utilizando a lista de transações
        String merkleRoot = MerkleTree.getMerkleRoot(transactions);

        transactions.add("tx5");  // Adiciona a transação "tx5"

        String recreatedMerkleRoot = MerkleTree.getMerkleRoot(transactions);

        // Exibe a Merkle Root no console
        System.out.println("Merkle Root: " + merkleRoot);
        System.out.println("Recreated Merkle Root: " + recreatedMerkleRoot);
    }
}
