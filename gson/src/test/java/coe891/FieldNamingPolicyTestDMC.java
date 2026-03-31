package com.google.gson;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldNamingPolicyTestDMC {

  // C1: Content of first character

  @Test
  public void upperCase_emptyString() {
    assertEquals("", FieldNamingPolicy.upperCaseFirstLetter(""));
  }

  @Test
  public void upperCase_lowercaseFirst() {
    assertEquals("Text", FieldNamingPolicy.upperCaseFirstLetter("text"));
  }

  @Test
  public void upperCase_uppercaseFirst() {
    assertEquals("Text", FieldNamingPolicy.upperCaseFirstLetter("Text"));
  }

  @Test
  public void upperCase_noLetters() {
    assertEquals("123", FieldNamingPolicy.upperCaseFirstLetter("123"));
  }

  // C2: Position of first character (BVA)

  @Test
  public void upperCase_letterAtIndex0() {
    assertEquals("Foo", FieldNamingPolicy.upperCaseFirstLetter("foo"));
  }

  @Test
  public void upperCase_letterAtIndex1() {
    assertEquals("_Foo", FieldNamingPolicy.upperCaseFirstLetter("_foo"));
  }

  @Test
  public void upperCase_noLetterFound() {
    assertEquals("999", FieldNamingPolicy.upperCaseFirstLetter("999"));
  }

  // C3: String length (BVA)

  @Test
  public void upperCase_length1Lowercase() {
    assertEquals("A", FieldNamingPolicy.upperCaseFirstLetter("a"));
  }

  @Test
  public void upperCase_length1NonLetter() {
    assertEquals("9", FieldNamingPolicy.upperCaseFirstLetter("9"));
  }
}
