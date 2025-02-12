package MerkleTree;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

    /**
     * The hash function takes a string input, computes its SHA-256 hash value,
     * and returns the hexadecimal representation of the hash.
     *
     * @param data Thank you for providing the code snippet for hashing data
     * using SHA-256. To assist you further, could you please provide the
     * specific data that you would like to hash using the `hash` method?
     * @return The `hash` method returns a hexadecimal representation of the
     * SHA-256 hash of the input `data` string.
     */
    public static String hash(String data) {
        try {
            MessageDigest digestInstance = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digestInstance.digest(data.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append(0);
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The function `getMerkleRoot` calculates the Merkle root hash of a list of
     * transactions using a binary tree structure.
     *
     * @param transactions A list of transactions represented as strings.
     * @return The `getMerkleRoot` method returns the Merkle root hash of a list
     * of transactions.
     */
    public static String getMerkleRoot(List<String> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return "";
        }

        List<String> tempList = new ArrayList<>();

        for (String tx : transactions) {
            tempList.add(hash(tx));
        }

        while (tempList.size() > 1) {
            List<String> newList = new ArrayList<>();

            for (int i = 0; i < tempList.size(); i += 2) {
                String left = tempList.get(i);

                String right = (i + 1 < tempList.size()) ? tempList.get(i + 1) : left;

                String combinedHash = hash(left + right);

                newList.add(combinedHash);
            }

            tempList = newList;
        }

        return tempList.get(0);
    }
}
