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


