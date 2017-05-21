/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sohu.bp.elite.model;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TUserIdListResult implements org.apache.thrift.TBase<TUserIdListResult, TUserIdListResult._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TUserIdListResult");

  private static final org.apache.thrift.protocol.TField USER_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("userIds", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField TOTAL_FIELD_DESC = new org.apache.thrift.protocol.TField("total", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TUserIdListResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TUserIdListResultTupleSchemeFactory());
  }

  public List<Long> userIds; // required
  public long total; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_IDS((short)1, "userIds"),
    TOTAL((short)2, "total");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // USER_IDS
          return USER_IDS;
        case 2: // TOTAL
          return TOTAL;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TOTAL_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_IDS, new org.apache.thrift.meta_data.FieldMetaData("userIds", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.TOTAL, new org.apache.thrift.meta_data.FieldMetaData("total", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TUserIdListResult.class, metaDataMap);
  }

  public TUserIdListResult() {
    this.total = 0L;

  }

  public TUserIdListResult(
    List<Long> userIds,
    long total)
  {
    this();
    this.userIds = userIds;
    this.total = total;
    setTotalIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TUserIdListResult(TUserIdListResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetUserIds()) {
      List<Long> __this__userIds = new ArrayList<Long>();
      for (Long other_element : other.userIds) {
        __this__userIds.add(other_element);
      }
      this.userIds = __this__userIds;
    }
    this.total = other.total;
  }

  public TUserIdListResult deepCopy() {
    return new TUserIdListResult(this);
  }

  @Override
  public void clear() {
    this.userIds = null;
    this.total = 0L;

  }

  public int getUserIdsSize() {
    return (this.userIds == null) ? 0 : this.userIds.size();
  }

  public java.util.Iterator<Long> getUserIdsIterator() {
    return (this.userIds == null) ? null : this.userIds.iterator();
  }

  public void addToUserIds(long elem) {
    if (this.userIds == null) {
      this.userIds = new ArrayList<Long>();
    }
    this.userIds.add(elem);
  }

  public List<Long> getUserIds() {
    return this.userIds;
  }

  public TUserIdListResult setUserIds(List<Long> userIds) {
    this.userIds = userIds;
    return this;
  }

  public void unsetUserIds() {
    this.userIds = null;
  }

  /** Returns true if field userIds is set (has been assigned a value) and false otherwise */
  public boolean isSetUserIds() {
    return this.userIds != null;
  }

  public void setUserIdsIsSet(boolean value) {
    if (!value) {
      this.userIds = null;
    }
  }

  public long getTotal() {
    return this.total;
  }

  public TUserIdListResult setTotal(long total) {
    this.total = total;
    setTotalIsSet(true);
    return this;
  }

  public void unsetTotal() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTAL_ISSET_ID);
  }

  /** Returns true if field total is set (has been assigned a value) and false otherwise */
  public boolean isSetTotal() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTAL_ISSET_ID);
  }

  public void setTotalIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTAL_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case USER_IDS:
      if (value == null) {
        unsetUserIds();
      } else {
        setUserIds((List<Long>)value);
      }
      break;

    case TOTAL:
      if (value == null) {
        unsetTotal();
      } else {
        setTotal((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_IDS:
      return getUserIds();

    case TOTAL:
      return Long.valueOf(getTotal());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case USER_IDS:
      return isSetUserIds();
    case TOTAL:
      return isSetTotal();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TUserIdListResult)
      return this.equals((TUserIdListResult)that);
    return false;
  }

  public boolean equals(TUserIdListResult that) {
    if (that == null)
      return false;

    boolean this_present_userIds = true && this.isSetUserIds();
    boolean that_present_userIds = true && that.isSetUserIds();
    if (this_present_userIds || that_present_userIds) {
      if (!(this_present_userIds && that_present_userIds))
        return false;
      if (!this.userIds.equals(that.userIds))
        return false;
    }

    boolean this_present_total = true;
    boolean that_present_total = true;
    if (this_present_total || that_present_total) {
      if (!(this_present_total && that_present_total))
        return false;
      if (this.total != that.total)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(TUserIdListResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    TUserIdListResult typedOther = (TUserIdListResult)other;

    lastComparison = Boolean.valueOf(isSetUserIds()).compareTo(typedOther.isSetUserIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userIds, typedOther.userIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTotal()).compareTo(typedOther.isSetTotal());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotal()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.total, typedOther.total);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TUserIdListResult(");
    boolean first = true;

    sb.append("userIds:");
    if (this.userIds == null) {
      sb.append("null");
    } else {
      sb.append(this.userIds);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("total:");
    sb.append(this.total);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TUserIdListResultStandardSchemeFactory implements SchemeFactory {
    public TUserIdListResultStandardScheme getScheme() {
      return new TUserIdListResultStandardScheme();
    }
  }

  private static class TUserIdListResultStandardScheme extends StandardScheme<TUserIdListResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TUserIdListResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list34 = iprot.readListBegin();
                struct.userIds = new ArrayList<Long>(_list34.size);
                for (int _i35 = 0; _i35 < _list34.size; ++_i35)
                {
                  long _elem36; // required
                  _elem36 = iprot.readI64();
                  struct.userIds.add(_elem36);
                }
                iprot.readListEnd();
              }
              struct.setUserIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOTAL
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.total = iprot.readI64();
              struct.setTotalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TUserIdListResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.userIds != null) {
        oprot.writeFieldBegin(USER_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, struct.userIds.size()));
          for (long _iter37 : struct.userIds)
          {
            oprot.writeI64(_iter37);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TOTAL_FIELD_DESC);
      oprot.writeI64(struct.total);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TUserIdListResultTupleSchemeFactory implements SchemeFactory {
    public TUserIdListResultTupleScheme getScheme() {
      return new TUserIdListResultTupleScheme();
    }
  }

  private static class TUserIdListResultTupleScheme extends TupleScheme<TUserIdListResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TUserIdListResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetUserIds()) {
        optionals.set(0);
      }
      if (struct.isSetTotal()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetUserIds()) {
        {
          oprot.writeI32(struct.userIds.size());
          for (long _iter38 : struct.userIds)
          {
            oprot.writeI64(_iter38);
          }
        }
      }
      if (struct.isSetTotal()) {
        oprot.writeI64(struct.total);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TUserIdListResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list39 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.userIds = new ArrayList<Long>(_list39.size);
          for (int _i40 = 0; _i40 < _list39.size; ++_i40)
          {
            long _elem41; // required
            _elem41 = iprot.readI64();
            struct.userIds.add(_elem41);
          }
        }
        struct.setUserIdsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.total = iprot.readI64();
        struct.setTotalIsSet(true);
      }
    }
  }

}

