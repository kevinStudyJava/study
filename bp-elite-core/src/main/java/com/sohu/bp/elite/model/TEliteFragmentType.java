/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sohu.bp.elite.model;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TEliteFragmentType implements org.apache.thrift.TEnum {
  NAVLABEL(1),
  SLOGAN(2);

  private final int value;

  private TEliteFragmentType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TEliteFragmentType findByValue(int value) { 
    switch (value) {
      case 1:
        return NAVLABEL;
      case 2:
        return SLOGAN;
      default:
        return null;
    }
  }
}
