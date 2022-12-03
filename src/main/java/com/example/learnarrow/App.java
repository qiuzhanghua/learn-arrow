package com.example.learnarrow;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.util.TransferPair;

// https://arrow.apache.org/cookbook/java/index.html

public class App {
    public static void main(String[] args) {
        try (
                BufferAllocator allocator = new RootAllocator();
                IntVector intVector = new IntVector("intVector", allocator)
        ) {
            intVector.allocateNew(3);
            intVector.set(0, 1);
            intVector.set(1, 2);
            intVector.set(2, 3);
            intVector.setValueCount(3);
            System.out.println(intVector);
        }

        try (BufferAllocator allocator = new RootAllocator();
             IntVector vector = new IntVector("intVector", allocator)) {
            for (int i = 0; i < 10; i++) {
                vector.setSafe(i, i);
            }
            vector.setValueCount(10);

            TransferPair tp = vector.getTransferPair(allocator);
            tp.splitAndTransfer(0, 5);
            try (IntVector sliced = (IntVector) tp.getTo()) {
                System.out.println(sliced);
            }

            tp = vector.getTransferPair(allocator);
            // copy 6 elements from index 2
            tp.splitAndTransfer(2, 6);
            try (IntVector sliced = (IntVector) tp.getTo()) {
                System.out.println(sliced);
            }
        }

    }
}
