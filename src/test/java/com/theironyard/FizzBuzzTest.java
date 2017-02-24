package com.theironyard;

import net.doughughes.testifier.exception.CannotAccessMethodException;
import net.doughughes.testifier.exception.CannotFindMethodException;
import net.doughughes.testifier.exception.CannotInvokeMethodException;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.test.TestifierTest;
import net.doughughes.testifier.util.Invoker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings("unchecked")
public class FizzBuzzTest extends TestifierTest{

    @Test
    public void testAsMapOf30() {
        /* arrange */

        /* act */
        HashMap<String, ArrayList<Integer>> result = null;
        try {
            result = (HashMap<String, ArrayList<Integer>>) Invoker.invokeStatic(FizzBuzz.class, "asMap", 30);
        } catch (Throwable e) {
            fail(e.getMessage());
        }

        /* assert */
        assertThat("The map returned from asMap() should contain a key named 'fizz'",
                result.containsKey("fizz"), equalTo(true));
        assertThat("The 'fizz' key should contain 3, 6, 9, 12, 18, 21, 24, and 27",
                result.get("fizz"), equalTo(new ArrayList<>(Arrays.asList(3, 6, 9, 12, 18, 21, 24, 27))));

        assertThat("The map returned from asMap() should contain a key named 'buzz'",
                result.containsKey("buzz"), equalTo(true));
        assertThat("The 'buzz' key should contain 5, 10, 20, and 25",
                result.get("buzz"), equalTo(new ArrayList<>(Arrays.asList(5, 10, 20, 25))));

        assertThat("The map returned from asMap() should contain a key named 'fizzbuzz'",
                result.containsKey("fizzbuzz"), equalTo(true));
        assertThat("The 'fizzbuzz' key should contain 15, and 30",
                result.get("fizzbuzz"), equalTo(new ArrayList<>(Arrays.asList(15, 30))));

    }

    @Test
    public void testAsMapReturnsHashMapOfStringMappedToArrayOfInteger(){
        /* arrange */

        /* act */
        String source = null;
        try {
            source = codeWatcher.getMainSourceCodeService().getDescriptionOfMethod("asMap", int.class);
        } catch (CannotFindMethodException e) {
            fail(e.getMessage());
        }

        /* assert */
        assertThat("The asMap() method should return a HashMap Of Arrays of Integers keyed by String values.",
                source, RegexMatcher.matches("^.*?StaticModifier ReferenceType ClassOrInterfaceType\\[HashMap\\] ReferenceType ClassOrInterfaceType\\[String\\] ReferenceType ClassOrInterfaceType\\[ArrayList\\] ReferenceType ClassOrInterfaceType\\[Integer\\] Parameter.*?$"));
    }
}