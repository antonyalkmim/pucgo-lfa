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

    private void shouldAcceptDFALanguage(String word) {
        Automata automata = AutomataFactory.createNonDeterministicAutomata();

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
        Automata automata = AutomataFactory.createNonDeterministicAutomata();

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

    private void shouldAcceptNFALanguage(String word) {
        Automata automata = AutomataFactory.createNonDeterministicAutomata();

        automata.getSymbols().addAll(Arrays.asList('a', 'b', 'c'));

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
    public void test14() {
        shouldAcceptNFALanguage("abc");
    }

    @Test
    public void test15() {
        shouldAcceptNFALanguage("def");
    }

    private void shouldNotAcceptNFALanguage(String word) {
        Automata automata = AutomataFactory.createNonDeterministicAutomata();

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
    public void test16() {
        shouldNotAcceptNFALanguage("a");
    }

    @Test
    public void test17() {
        shouldNotAcceptNFALanguage("ab");
    }

    @Test
    public void test18() {
        shouldNotAcceptNFALanguage("abcd");
    }

    @Test
    public void test19() {
        shouldNotAcceptNFALanguage("d");
    }

    @Test
    public void test20() {
        shouldNotAcceptNFALanguage("de");
    }

    @Test
    public void test21() {
        shouldNotAcceptNFALanguage("defa");
    }

    @Test
    public void test22() {
        shouldNotAcceptNFALanguage("abcd");
    }

    private void shouldAcceptNFALanguage1(String word) {
        Automata automata = AutomataFactory.createNonDeterministicAutomata();

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

}
