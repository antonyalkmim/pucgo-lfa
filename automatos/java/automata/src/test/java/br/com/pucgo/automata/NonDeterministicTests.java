package br.com.pucgo.automata;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testes para automatos não-determinísticos.
 *
 * Created by danfma on 22/11/14.
 */
public class NonDeterministicTests {


/*
    private void shouldAcceptNFALanguage(String word) {
        NFA automata = AutomataFactory.createNFA();

        automata.getSymbols().addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));

        State s0 = automata.addState("S0");
        State s1 = automata.addState("S1");
        State s2 = automata.addState("S2");
        State s3 = automata.addState("S3");
        State s4 = automata.addState("S4", true);

        automata.setStart(s0);

        automata.addTransition(s0, automata.getEpsilon(), s1);
        automata.addTransition(s1, 'a', s2);
        automata.addTransition(s2, 'b', s3);
        automata.addTransition(s3, 'c', s4);

        State s5 = automata.addState("S5");
        State s6 = automata.addState("S6");
        State s7 = automata.addState("S7");
        State s8 = automata.addState("S8", true);

        automata.addTransition(s0, automata.getEpsilon(), s5);
        automata.addTransition(s5, 'd', s6);
        automata.addTransition(s6, 'e', s7);
        automata.addTransition(s7, 'f', s8);

        assertTrue(automata.accept(word));
    }

    @Test
    public void test2() {
        shouldAcceptNFALanguage("def");
    }
*/
    private void shouldAcceptNFALanguage2(String word) {
        NFA automata = AutomataFactory.createNFA();

        /*
        automata.getSymbols().addAll(Arrays.asList('0', '1'));

        State s0 = automata.addState("S0");
        State s1 = automata.addState("S1", true);

        automata.setStart(s0);

        automata.addTransition(s0, '0', s0, s1);
        automata.addTransition(s0, '1', s1);
        automata.addTransition(s1, '0', s0);
        automata.addTransition(s1, '1', s0);
        */

        automata.getSymbols().addAll(Arrays.asList('0'));

        State s0 = automata.addState("S0");
        State s1 = automata.addState("S1");
        State s2 = automata.addState("S2", true);

        automata.setStart(s0);

        automata.addTransition(s0, '0', s1);
        automata.addTransition(s0, automata.getEpsilon(), s2);
        automata.addTransition(s2, automata.getEpsilon(), s1);

        assertTrue(automata.accept(word));
    }

    @Test
    public void test1() {
        shouldAcceptNFALanguage2("0");
    }
    /*
    private void shouldNotAcceptNFALanguage(String word) {
        NFA automata = AutomataFactory.createNFA();

        automata.getSymbols().addAll(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'));

        State s0 = automata.addState("S0");
        State s1 = automata.addState("S1");
        State s2 = automata.addState("S2");
        State s3 = automata.addState("S3");
        State s4 = automata.addState("S4");

        automata.setStart(s0);

        automata.addTransition(s0, automata.getEpsilon(), s1);
        automata.addTransition(s1, 'a', s2);
        automata.addTransition(s2, 'b', s3);
        automata.addTransition(s3, 'c', s4);

        State s5 = automata.addState("S5");
        State s6 = automata.addState("S6");
        State s7 = automata.addState("S7");
        State s8 = automata.addState("S8");

        automata.addTransition(s0, automata.getEpsilon(), s5);
        automata.addTransition(s5, 'd', s6);
        automata.addTransition(s6, 'e', s7);
        automata.addTransition(s7, 'f', s8);

        assertFalse(automata.accept(word));
    }

    @Test
    public void test3() {
        shouldNotAcceptNFALanguage("a");
    }

    @Test
    public void test4() {
        shouldNotAcceptNFALanguage("ab");
    }

    @Test
    public void test5() {
        shouldNotAcceptNFALanguage("abcd");
    }

    @Test
    public void test6() {
        shouldNotAcceptNFALanguage("d");
    }

    @Test
    public void test7() {
        shouldNotAcceptNFALanguage("de");
    }

    @Test
    public void test8() {
        shouldNotAcceptNFALanguage("defa");
    }

    @Test
    public void test9() {
        shouldNotAcceptNFALanguage("abcd");
    }

    private void shouldAcceptNFALanguage1(String word) {
        Automata automata = AutomataFactory.createNFA();

        automata.getSymbols().addAll(Arrays.asList('a', 'b', 'c'));

        State s0 = automata.addState("S0");
        State s1 = automata.addState("S1", true);
        State s2 = automata.addState("S2", true);

        automata.setStart(s0);

        automata.addTransition(s0, 'a', s0);
        automata.addTransition(s0, 'c', s1);
        automata.addTransition(s0, automata.getEpsilon(), s2);
        automata.addTransition(s1, 'c', s1);
        automata.addTransition(s2, 'b', s1, s2);

        assertTrue(automata.accept(word));
    }

    @Test
    public void test23() {
        shouldAcceptNFALanguage1("c");
    }


    @Test
    public void test24() {
        shouldAcceptNFALanguage1("ac");
    }

    @Test
    public void test25() {
        shouldAcceptNFALanguage1("b");
    }

    @Test
    public void test26() {
        shouldAcceptNFALanguage1("cc");
    }

    @Test
    public void test27() {
        shouldAcceptNFALanguage1("abc");
    }
    */
}
