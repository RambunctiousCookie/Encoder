# Encoder
This is a simple tool that is able to encode plaintext to an obfuscated string, and decode an obfuscated string into plaintext. The cipher matches the specifications laid out in `Encoder (New) V1.pdf`. 

## Structure
The main OffsetCipher class implements the Cipher interface, including its interface methods. 
This is done intentionally for polymorphism and code reusability. It also adheres to the Strategy design pattern.

### Logic
- The basis of the cipher is that the given offset is converted into its index notation within the reference table.
- This tool generates Mapper dictionaries for the respective strings based on the given offset.
- Finally, the Mapper(s) are used in conjunction with stringbuilders to encode or decode the given text.

### OffsetCipher
- Designed to be modular
  - Allow user to set their own custom reference table (using instanced `setReferenceTable()`)
- Performs logical checks before carrying out processes.
  - i.e whether the first character/offset is a valid offset within the reference table.
- Applies a simple Modulo strategy to wrap around the reference table where necessary.

### JUnit Tests
- There are three tests in total.
- Two are designed in emulation of the examples provided in `Encoder (New) V1.pdf` (namely, using the strings "HELLO WORLD" and the letters B and F, respectively).
- The third test is a robustness test whereby it loops x times, generates a new random string each time, and asserts the correct return value each time.
  - How many times it runs can be adjusted using the `testRounds` variable in the `@BeforeAll setup()` method.

