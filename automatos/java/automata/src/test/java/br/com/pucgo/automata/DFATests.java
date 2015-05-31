package br.com.pucgo.automata;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by danfma on 22/05/15.
 */
public class DFATests {

    private void shouldAcceptDFALanguage(String word) {

        DFA automata = AutomataFactory.createDFA();

        automata.getSymbols().add('0');
        automata.getSymbols().add('1');

        State q0 = automata.addState("Q0");
        State q1 = automata.addState("Q1", true);

        automata.setStart(q0);

        automata.addTransition(q0, '0', q0);
        automata.addTransition(q0, '1', q1);
        automata.addTransition(q1, '0', q0);
        automata.addTransition(q1, '1', q1);

        assertTrue(automata.accept(word));
    }


    @Test
    public void test1() {
        shouldAcceptDFALanguage("01");
    }

    @Test
    public void test2() {
        shouldAcceptDFALanguage("01");
    }

    @Test
    public void test3() {
        shouldAcceptDFALanguage("011");
    }

    @Test
    public void test4() {
        shouldAcceptDFALanguage("0101");
    }

    @Test
    public void test5() {
        shouldAcceptDFALanguage("1");
    }

    @Test
    public void test6() {
        shouldAcceptDFALanguage("11");
    }

    @Test
    public void test7() {
        shouldAcceptDFALanguage("1101");
    }

    private void shouldNotAcceptDFALanguage(String word) {
        DFA automata = AutomataFactory.createDFA();

        automata.getSymbols().add('0');
        automata.getSymbols().add('1');

        State q0 = automata.addState("Q0");
        State q1 = automata.addState("Q1", true);

        automata.setStart(q0);

        automata.addTransition(q0, '0', q0);
        automata.addTransition(q0, '1', q1);
        automata.addTransition(q1, '0', q0);
        automata.addTransition(q1, '1', q1);

        assertFalse(automata.accept(word));
    }

    @Test
    public void test8() {
        shouldNotAcceptDFALanguage("0");
    }

    @Test
    public void test9() {
        shouldNotAcceptDFALanguage("010");
    }

    @Test
    public void test10() {
        shouldNotAcceptDFALanguage("01010");
    }

    @Test
    public void test11() {
        shouldNotAcceptDFALanguage("10");
    }

    @Test
    public void test12() {
        shouldNotAcceptDFALanguage("00");
    }

    @Test
    public void test13() {
        shouldNotAcceptDFALanguage("11010");
    }

}
