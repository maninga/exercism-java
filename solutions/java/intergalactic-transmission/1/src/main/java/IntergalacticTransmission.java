import java.util.ArrayList;
import java.util.List;

public class IntergalacticTransmission {

    public static List<Integer> getTransmitSequence(List<Integer> message) {
        int n = 7;
        
        List<Integer> outputBytes = new ArrayList<>();
        if (message == null) return outputBytes;

        long inputBuffer = 0;
        int inputBufferBits = 0;
        long outputBuffer = 0;
        int outputBufferBits = 0;

        int dataMask = (1 << n) - 1;
        int encodedBlockSize = n + 1;

        for (Integer byteObj : message) {
            // Null safety: silently skip null values to prevent NullPointerException
            if (byteObj == null) continue; 
            int currentByte = byteObj; 

            // 1. Push 8 bits of the current byte into the input buffer
            inputBuffer = (inputBuffer << 8) | (currentByte & 0xFF);
            inputBufferBits += 8;

            // 2. Extract as many n-bit blocks as possible
            while (inputBufferBits >= n) {
                int shift = inputBufferBits - n;
                int dataBlock = (int) ((inputBuffer >>> shift) & dataMask);
                
                // Clear consumed bits from the input buffer
                inputBufferBits -= n;
                inputBuffer &= (1L << inputBufferBits) - 1;

                // 3. Compute the parity bit
                int bitCount = Integer.bitCount(dataBlock);
                int parityBit = (bitCount % 2 == 0) ?  0 : 1;
                
                // 4. Combine data and parity (Data on the left, Parity on the right)
                int encodedBlock = (dataBlock << 1) | parityBit;

                // 5. Push the encoded block into the output buffer
                outputBuffer = (outputBuffer << encodedBlockSize) | encodedBlock;
                outputBufferBits += encodedBlockSize;

                // 6. Extract full 8-bit bytes from the output buffer
                while (outputBufferBits >= 8) {
                    int outputShift = outputBufferBits - 8;
                    outputBytes.add((int) ((outputBuffer >>> outputShift) & 0xFF));
                    outputBufferBits -= 8;
                    outputBuffer &= (1L << outputBufferBits) - 1;
                }
            }
        }

        // Remaining bits in the input buffer (final incomplete block)
        if (inputBufferBits > 0) {
            int dataBlock = (int) (inputBuffer << (n - inputBufferBits)) & dataMask;
            int bitCount = Integer.bitCount(dataBlock);
            int parityBit = (bitCount % 2 == 0) ?  0 : 1;
            int encodedBlock = (dataBlock << 1) | parityBit;

            outputBuffer = (outputBuffer << encodedBlockSize) | encodedBlock;
            outputBufferBits += encodedBlockSize;
        }

        // Output buffer padding when it contains bits that do not form a full byte
        if (outputBufferBits > 0) {
            outputBytes.add((int) (outputBuffer << (8 - outputBufferBits)) & 0xFF);
        }

        return outputBytes;
    }

    public static List<Integer> decodeSequence(List<Integer> sequence) {
        int n = 7;

        List<Integer> messageBytes = new ArrayList<>();
        if (sequence == null) return messageBytes;

        long inputBuffer = 0;
        int inputBufferBits = 0;
        long outputBuffer = 0;
        int outputBufferBits = 0;

        int encodedBlockSize = n + 1;
        int encodedBlockMask = (1 << encodedBlockSize) - 1;
        int dataMask = (1 << n) - 1;

        for (Integer byteObj : sequence) {
            if (byteObj == null) continue; // Null safety
            int currentByte = byteObj;

            inputBuffer = (inputBuffer << 8) | (currentByte & 0xFF);
            inputBufferBits += 8;

            // Extract blocks of size (n + 1) bits
            while (inputBufferBits >= encodedBlockSize) {
                int shift = inputBufferBits - encodedBlockSize;
                int encodedBlock = (int) ((inputBuffer >>> shift) & encodedBlockMask);

                inputBufferBits -= encodedBlockSize;
                inputBuffer &= (1L << inputBufferBits) - 1;

                // Separate data and the received parity bit
                int dataBlock = (encodedBlock >>> 1) & dataMask;
                int receivedParity = encodedBlock & 1;

                // Validate local parity
                int bitCount = Integer.bitCount(dataBlock);
                int expectedParity = (bitCount % 2 == 0) ? 0 : 1;

                if (receivedParity != expectedParity) {
                    throw new IllegalArgumentException("Parity error detected: the sequence is corrupted!");
                }

                // Push only the clean data block into the output buffer
                outputBuffer = (outputBuffer << n) | dataBlock;
                outputBufferBits += n;

                // Reconstruct original 8-bit bytes
                while (outputBufferBits >= 8) {
                    int outputShift = outputBufferBits - 8;
                    messageBytes.add((int) ((outputBuffer >>> outputShift) & 0xFF));
                    outputBufferBits -= 8;
                    outputBuffer &= (1L << outputBufferBits) - 1;
                }
            }
        }
        
        // Note: Any remaining bits in outputBufferBits are just the padding
        // zeros added during encoding, so they are safely ignored at this stage.

        return messageBytes;
    }

}
