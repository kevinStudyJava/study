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

public enum TEliteFollowType implements org.apache.thrift.TEnum {
  ELITE_USER(1),
  ELITE_TAG(2);

  private final int value;

  private TEliteFollowType(int value) {
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
  public static TEliteFollowType findByValue(int value) { 
    switch (value) {
      case 1:
        return ELITE_USER;
      case 2:
        return ELITE_TAG;
      default:
        return null;
    }
  }
}
