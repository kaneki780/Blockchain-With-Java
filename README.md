**Blockchain in Java**

Welcome to the Blockchain in Java project! This project aims to provide a simple implementation of a blockchain and cryptocurrency system using Java. This README file will guide you through the project structure, its components, and the cryptographic concepts involved.

### What is a Blockchain? 

A blockchain is just a chain/list of blocks. Each block in the blockchain will have its own digital fingerprint, contain digital fingerprint of the previous block, and have some data ( this data could be transactions for example ).

![Pasted image 20240414230832](https://github.com/kaneki780/Blockchain-With-Java/assets/111025359/299e03d4-1c7a-4eb8-9ff3-7effec5e295d)

**Each block doesn’t just contain the hash of the block before it, but its own hash is in part, calculated from the previous hash**. If the previous block’s data is changed then the previous block’s hash will change ( since it is calculated in part, by the data) in turn affecting all the hashes of the blocks there after. **Calculating and comparing the hashes allow us to see if a blockchain is invalid.**

What does this mean ? …Changing any data in this list, will change the signature and **break the chain**.


### Hash Function

A hash function is a mathematical algorithm that takes an input (or 'message') and returns a fixed-size string of bytes. In this project, we use the SHA-256 hash function, which generates a unique 256-bit hash value for each input. Hash functions are essential in blockchain technology for creating digital fingerprints of data, ensuring data integrity, and securing transactions.
![Screenshot 2024-04-14 232516](https://github.com/kaneki780/Blockchain-With-Java/assets/111025359/c9c7004e-c774-41f6-85bd-af1c851cec84)


### Functioning of `mineBlock()` Method

The `mineBlock()` method in the `Block` class is responsible for mining a new block by finding a suitable nonce value that satisfies the proof-of-work consensus algorithm. The **mineBlock()** method takes in an int called difficulty, this is the number of 0’s they must solve for. Low difficulty like 1 or 2 can be solved nearly instantly on most computers. Here's how it works:

1. Calculate the Merkle root: The Merkle root is a cryptographic hash of all transactions included in the block. It ensures that the block's transactions are valid and unaltered.
    
2. Set the target hash: Determine the target hash by generating a string with a specified difficulty level (number of leading zeros).
    
3. Mine the block: Iterate through nonce values and calculate the block's hash until a hash is found that meets the target difficulty level. This process requires computational work (mining), hence the term "proof of work."
    
4. Update the block: Once a suitable hash is found, update the block's hash and nonce.
    
5. Print success: Print a message indicating that the block has been successfully mined.
![Pasted image 20240414232612](https://github.com/kaneki780/Blockchain-With-Java/assets/111025359/6b67430f-d9dc-479a-a85e-02ea854caa62)

### Functioning of `isChainValid()` Method

The `isChainValid()` Boolean method in the `Blockchain` class validates the integrity of the blockchain by checking each block's hash and the previous block's hash. Here's how it works:

1. Loop through all blocks: Iterate through each block in the blockchain.
    
2. Check block hashes: For each block, compare the registered hash with the calculated hash. Ensure that the hash variable is equal to the calculated hash.
    
3. Check previous block's hash: Also, verify that the previous block's hash matches the previousHash variable of the current block.
    
4. Check proof of work: Verify that each block's hash meets the target difficulty level set by the proof-of-work algorithm.
    
5. Verify transactions: Validate each block's transactions to ensure they are correctly signed and the inputs and outputs balance.
    
6. Ensure blockchain integrity: If all checks pass, the blockchain is deemed valid.
    


### Wallet

In crypto-currencies, coin ownership is transfered on the Blockchain as transactions, participants have an address which funds can be sent to and from. **In their basic form wallets can just store these addresses, most wallets however, are also software able to make new transactions on the Blockchain.**
![Pasted image 20240414231629](https://github.com/kaneki780/Blockchain-With-Java/assets/111025359/818112e9-e6c1-4198-bba2-4b2dd90351d1)

Cryptographic Concepts

#### SHA-256 (Secure Hash Algorithm 256-bit)

SHA-256 is a cryptographic hash function used to generate unique fixed-size hash values from input data. In this project, SHA-256 is applied to various data, including block contents, transaction details, and public keys.

#### ECDSA (Elliptic Curve Digital Signature Algorithm)

ECDSA is a cryptographic algorithm used for digital signatures, providing a way to verify the authenticity and integrity of messages. In this project, ECDSA is used to generate and verify digital signatures for transactions, ensuring that transactions are secure and tamper-proof.

#### Key Pair Generation

Key pair generation involves generating a public-private key pair using cryptographic algorithms. In this project, ECDSA is used to generate key pairs for wallet addresses. The public key is used to receive funds, while the private key is used to sign transactions and prove ownership of funds.

**_What are the public and private keys for ?_**

For our **_‘blockchain’_** the _public key_ will act as our address. It’s OK to share this public key with others to receive payment. Our private key is used to **_sign_** our transactions, so that nobody can spend our blockcoins other than the owner of the private key. **Users will have to keep their private key Secret !** We also send our public key along with the transaction and it can be used to verify that our signature is valid and data has not been tampered with.

# Transactions & Signatures

Each transaction will carry a certain amount of data:

- The public key(address) of the sender of funds.
- The public key(address) of the receiver of funds.
- The value/amount of funds to be transferred.
- Inputs, which are references to previous transactions that prove the sender has funds to send.
- Outputs, which shows the amount relevant addresses received in the transaction. ( These outputs are referenced as inputs in new transactions )
- A cryptographic signature, that proves the owner of the address is the one sending this transaction and that the data hasn’t been changed. ( for example: preventing a third party from changing the amount sent )

## What is the purpose of signatures and how do they work ?

**_Signatures_** perform **two** very important tasks on our blockchain: Firstly, they **allow only the owner** to spend **their coins**, secondly, they prevent others from **tampering with their submitted transaction** before a new block is mined (at the point of entry).

We created two wallets, _walletA_ and _walletB_ then printed _walletA_’s private and public keys. Generated a _Transaction_ and signed it using _walletA_’s private key.

# Inputs & Outputs 1: How crypto currency is owned…

For you to own 1 bitcoin, you have to receive 1 Bitcoin. The ledger doesn’t really add one bitcoin to you and minus one bitcoin from the sender, the sender referenced that he/she previously received one bitcoin, then a transaction output was created showing that 1 Bitcoin was sent to your address. (Transaction inputs are references to previous transaction outputs.).

![Pasted image 20240414232037](https://github.com/kaneki780/Blockchain-With-Java/assets/111025359/1a323f4c-9306-4aee-b38c-bb3eaf5f9a70)

### Java Security Classes

Java provides various security classes and libraries for implementing cryptographic algorithms and ensuring secure communication. In this project, the following Java security classes are used:

- `MessageDigest`: Used for applying cryptographic hash functions like SHA-256.
- `KeyPairGenerator`: Used for generating public-private key pairs for ECDSA.
- `Signature`: Used for creating and verifying digital signatures using ECDSA.
- `SecureRandom`: Used for generating secure random numbers, essential for cryptographic operations.
  

Integration of BouncyCastleProvider and GsonBuilder

In the Blockchain in Java project, the BouncyCastleProvider and GsonBuilder are utilized to enhance the security and facilitate JSON serialization/deserialization, respectively.

BouncyCastleProvider
The BouncyCastleProvider is a cryptographic service provider (provider) that implements various cryptographic algorithms and protocols. In this project, it is added to the security providers using the line:

Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

This line ensures that the Bouncy Castle cryptographic library is available for use in the project. Bouncy Castle provides support for additional cryptographic algorithms beyond those included in the default Java Cryptography Extension (JCE), making it a valuable addition for implementing secure cryptographic operations.

GsonBuilder
The GsonBuilder class is part of the Google Gson library, which is used for JSON serialization and deserialization in Java. In this project, GsonBuilder is imported using the line:


import com.google.gson.GsonBuilder;

GsonBuilder provides a flexible and customizable way to configure Gson instances for JSON processing. It allows you to set various options such as pretty printing, date format, field naming policy, and exclusion strategies.

In the project, GsonBuilder is used in the StringUtility class to serialize Java objects to JSON format using the getJson() method. This method is employed for generating JSON representations of objects, particularly for debugging purposes and generating readable output.

Usage in the Project
BouncyCastleProvider: The BouncyCastleProvider is added to the security providers in the main method of the Blockchain class. This ensures that the Bouncy Castle cryptographic algorithms and services are available throughout the project for tasks such as cryptographic hashing and digital signature generation.

GsonBuilder: The GsonBuilder is utilized in the StringUtility class to provide a convenient way to generate JSON representations of objects. This functionality is used, for example, in printing detailed information about transactions and blocks during the execution of the project's main method.

By integrating BouncyCastleProvider and GsonBuilder into the project, the Blockchain in Java project benefits from enhanced cryptographic capabilities and streamlined JSON processing, contributing to its functionality, security, and ease of use.

# Working
![Screenshot 2024-04-15 052731](https://github.com/kaneki780/Blockchain-With-Java/assets/111025359/c62401bd-6f07-4cbd-9086-fe3852f7bbf0)


### Conclusion

Thank you for exploring the Blockchain in Java project! If you have any questions or feedback, please don't hesitate to reach out to me. Happy coding!

