# Caesar-Cipher-and-Vigenere-Cipher
This Java project provides implementations of Caesar Cipher, Caesar Cracker, Vigenere Cipher, and Vigenere Breaker. These ciphers are used for encryption and decryption of text messages.

This is a Coursera course assignment. Course: Java Programming: Arrays, Lists, and Structured Data https://www.coursera.org/learn/java-programming-arrays-lists-data?specialization=object-oriented-programming

## Features
1. Caesar Cipher: Simple substitution cipher that replaces each letter in the plaintext by a letter some fixed number of positions down the alphabet.
2. Caesar Cracker: Decrypts Caesar Cipher encrypted messages by finding the most common character and assuming it is the encrypted version of a known common character (e.g., 'e' in English).
3. Vigenere Cipher: More advanced substitution cipher that uses multiple Caesar ciphers in sequence with different shift values.
4. Vigenere Breaker: Decrypts Vigenere Cipher encrypted messages in different languages by slicing the encrypted message into multiple Caesar ciphers and analyzing the frequency of characters in each slice.

## Usage
Create a new instance of CaesarCipher with a specific key:
```java
CaesarCipher cipher = new CaesarCipher(3);
```
Encrypt and decrypt a message using Caesar Cipher:
```java
String encrypted = cipher.encrypt("Hello, World!");
String decrypted = cipher.decrypt(encrypted);
```
Crack a Caesar Cipher encrypted message:
```java
CaesarCracker cracker = new CaesarCracker();
String decrypted = cracker.decrypt(encrypted);
```
Create a new instance of VigenereCipher with a specific key array:
```java
int[] key = {17, 14, 12, 4};
VigenereCipher vcipher = new VigenereCipher(key);
```
Encrypt and decrypt a message using Vigenere Cipher:
```java
String vencrypted = vcipher.encrypt("Hello, World!");
String vdecrypted = vcipher.decrypt(vencrypted);
```
Use Vigenere Breaker to decrypt a Vigenere Cipher encrypted message:
```java
VigenereBreaker vbreaker = new VigenereBreaker();
vbreaker.breakVigenere(); // Reads from file resources and prints the decrypted message and the language used.
```
