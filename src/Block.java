import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {

    public volatile String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message.
    public long timeStamp; //as number of milliseconds since 1/1/1970.
    public AtomicInteger nonce = new AtomicInteger(0); // Atomic for thread safety

    // Block Constructor.
    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); // Making sure we do this after we set the other values.
    }

    // Calculate new hash based on block contents.
    public synchronized String calculateHash() {
        return StringUtility.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce.get()) +
                        merkleRoot
        );
    }

    // Multithreaded mining process with thread safety.
    public void mineBlock(int difficulty) {
        merkleRoot = StringUtility.getMerkleRoot(transactions);
        String target = StringUtility.getDificultyString(difficulty); // Create a string with difficulty * "0"

        ExecutorService executor = Executors.newFixedThreadPool(4); // create 4 threads

        for (int i = 0; i < 4; i++) {
            executor.submit(() -> {
                while (!hash.substring(0, difficulty).equals(target)) {
                    synchronized (this) {
                        nonce.incrementAndGet();
                        hash = calculateHash();
                    }
                }
                System.out.println("Block Mined!!! : " + hash);
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }
    }

    // Add transactions to this block.
    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if (!"0".equals(previousHash)) {
            if (!transaction.processTransaction()) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }

        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
