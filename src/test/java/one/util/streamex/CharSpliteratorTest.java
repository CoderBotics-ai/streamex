/*
 * Copyright 2015, 2024 StreamEx contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.util.streamex;

import org.junit.Test;

import java.util.Arrays;
import java.util.Spliterator;

import static one.util.streamex.TestHelpers.*;
import static org.junit.Assert.*;

/**
 * @author Tagir Valeev
 */
public class CharSpliteratorTest {
    @Test
    public void testBasics() {
        CharSpliterator spliterator = new CharSpliterator("abcd,efgh", ',', false);
        assertTrue(spliterator.hasCharacteristics(Spliterator.ORDERED));
        assertTrue(spliterator.hasCharacteristics(Spliterator.NONNULL));
        assertFalse(spliterator.hasCharacteristics(Spliterator.SORTED));
        assertFalse(spliterator.hasCharacteristics(Spliterator.SIZED));
        assertEquals(9, spliterator.estimateSize());
        assertTrue(spliterator.tryAdvance(a -> {
        }));
        assertEquals(4, spliterator.estimateSize());
        assertTrue(spliterator.tryAdvance(a -> {
        }));
        assertEquals(0, spliterator.estimateSize());
    }

    @Test
    public void testSpliterator() {
        // Empty string is processed differently by CharSpliterator, but this is
        // fixed in StreamEx.split
        checkSpliterator("split", Arrays.asList(), () -> new CharSpliterator("", ',', true));
        checkSpliterator("split", Arrays.asList(""), () -> new CharSpliterator("", ',', false));
        withRandom(r -> {
            String[] inputs = { ",", "abcd,e,f,gh,,,i,j,kl,,,,,,", ",", "abcdasdfgsdfgsdfgsdfgsdfgsdgdfsgs",
                    "abcdasdfgsdfgsdfgsdfgsdfgsdgdfsgs,", "abcdasdfgs,dfgsdfgsdfgsdfgsdgdfsgs",
                    "abcd,e,f,gh,,,i,j,kl,,,,,,x", "abcd,e,f,gh,,,i,j,kl,,,,,,x,",
                    IntStreamEx.of(r, 0, 3).limit(r.nextInt(1000) + 1).elements(new int[] { ',', 'a', 'b' }).charsToString() };
            for (String input : inputs) {
                checkSpliterator(input, Arrays.asList(input.split(",")), () -> new CharSpliterator(input, ',', true));
                checkSpliterator(input, Arrays.asList(input.split(",", -1)), () -> new CharSpliterator(input, ',', false));
            }
        });
    }
    
    @Test
    public void testTrySplit() {
        String input = "a,b,c,d,e,f,g,h";
        CharSpliterator spliterator = new CharSpliterator(input, ',', false);
        assertEquals(-1, spliterator.getExactSizeIfKnown());
        assertEquals(15, spliterator.estimateSize());
        Spliterator<String> prefix = spliterator.trySplit();
        assertEquals(7, prefix.estimateSize());
        assertEquals(7, spliterator.estimateSize());
        prefix = spliterator.trySplit();
        assertEquals(3, prefix.estimateSize());
        assertEquals(3, spliterator.estimateSize());
        consumeElement(spliterator, "g");
        consumeElement(spliterator, "h");
        consumeElement(prefix, "e");
        consumeElement(prefix, "f");
    }
}
