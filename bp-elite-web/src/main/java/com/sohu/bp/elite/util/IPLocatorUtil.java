package com.sohu.bp.elite.util;

import com.sohu.bp.utils.ResourcesUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.util.Hashtable;
import java.util.StringTokenizer;


public class IPLocatorUtil {

    // 一些固定常量，比如记录长度等等
    private static final int IP_RECORD_LENGTH = 7;
    private static final byte AREA_FOLLOWED = 0x01;
    private static final byte NO_AREA = 0x2;
    // 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
    private final Hashtable ipCache;
    // 为提高效率而采用的临时变量
    private final IPLocation loc;
    // private final byte[] buf;//不需要了
    private final byte[] b4;
    private final byte[] b3;
    // 随机文件访问类
    private RandomAccessFile ipFile;
    // 内存映射文件
    private MappedByteBuffer mbb;
    // 起始地区的开始和结束的绝对偏移
    private long ipBegin, ipEnd;

    /**
     * 默认的定位器
     */
    public IPLocatorUtil() {
        this("ip.data");
    }

    public IPLocatorUtil(String fileName) {
        ipCache = new Hashtable();
        loc = new IPLocation();
//  buf = new byte[100];//不需要了，这里初始化不好!
        b4 = new byte[4];
        b3 = new byte[3];
        try {
            File f = ResourcesUtil.getResourceFile(fileName);
            ipFile = new RandomAccessFile(f.getAbsoluteFile(), "r");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果打开文件成功，读取文件头信息
        if (ipFile != null) {
            try {
                ipBegin = readLong4(0);
                ipEnd = readLong4(4);
                if (ipBegin == -1 || ipEnd == -1) {
                    ipFile.close();
                    ipFile = null;
                }
            } catch (IOException e) {
                System.out.println("IP地址信息文件格式有错误，IP显示功能将无法使用");
                ipFile = null;
            }
        }
    }

    /**
     * 从内存映射文件的offset位置开始的3个字节读取一个int
     *
     * @param offset
     * @return
     */
    private int readInt3(int offset) {
        mbb.position(offset);
        return mbb.getInt() & 0x00FFFFFF;
    }

    /**
     * 从内存映射文件的当前位置开始的3个字节读取一个int
     *
     * @return
     */
    private int readInt3() {
        return mbb.getInt() & 0x00FFFFFF;
    }

    /**
     * 根据ip找地理位置
     *
     * @param ip ip地质
     * @return 地理位置
     */
    public String getLocation(byte[] ip) {
        // 检查ip地址文件是否正常
        if (ipFile == null)
            return "";
        // 保存ip，转换ip字节数组为字符串形式
        String ipStr = getIpStringFromBytes(ip);
        // 先检查cache中是否已经包含有这个ip的结果，没有再搜索文件
        if (ipCache.containsKey(ipStr)) {
            IPLocation loc = (IPLocation) ipCache.get(ipStr);
            return loc.location;
        } else {
            IPLocation loc = getIPLocation(ip);
            ipCache.put(ipStr, loc.getCopy());
            return loc.location;
        }
    }

    private byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try {
            ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            //  LogFactory.log("从ip的字符串形式得到字节数组形式报错", Level.ERROR, e);
        }
        return ret;
    }

    /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    private String getIpStringFromBytes(byte[] ip) {
        StringBuilder sb = new StringBuilder();
        sb.delete(0, sb.length());
        sb.append(ip[0] & 0xFF);
        sb.append('.');
        sb.append(ip[1] & 0xFF);
        sb.append('.');
        sb.append(ip[2] & 0xFF);
        sb.append('.');
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }

    /**
     * 根据某种编码方式将字节数组转换成字符串
     *
     * @param b        字节数组
     * @param offset   要转换的起始位置
     * @param len      要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    private String getString(byte[] b, int offset, int len,
                             String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }

    /**
     * 根据IP得到地理位置
     *
     * @param ip IP的字符串形式
     * @return 地理位置
     */
    public String getLocation(String ip) {
        return getLocation(getIpByteArrayFromString(ip));
    }

    /**
     * 根据IP得到地区名
     *
     * @param ip ip的字节数组形式
     * @return 地区名字符串
     */
    public String getArea(byte[] ip) {
        // 检查ip地址文件是否正常
        if (ipFile == null)
            return "";
        // 保存ip，转换ip字节数组为字符串形式
        String ipStr = getIpStringFromBytes(ip);
        // 先检查cache中是否已经包含有这个ip的结果，没有再搜索文件
        if (ipCache.containsKey(ipStr)) {
            IPLocation loc = (IPLocation) ipCache.get(ipStr);
            return loc.area;
        } else {
            IPLocation loc = getIPLocation(ip);
            ipCache.put(ipStr, loc.getCopy());
            return loc.area;
        }
    }

    /**
     * 根据IP得到地区名
     *
     * @param ip IP的字符串形式
     * @return 地区名字符串
     */
    public String getArea(String ip) {
        return getArea(getIpByteArrayFromString(ip));
    }

    /**
     * 根据ip搜索ip信息文件，得到IPLocation结构，所搜索的ip参数从类成员ip中得到
     *
     * @param ip 要查询的IP
     * @return IPLocation结构
     */
    private IPLocation getIPLocation(byte[] ip) {
        IPLocation info = null;
        long offset = locateIP(ip);
        if (offset != -1)
            info = getIPLocation(offset);
        if (info == null) {
            info = new IPLocation();
            info.location = "未知位置";
            info.area = "未知地区";
        }
        return info;
    }

    /**
     * 从offset位置读取4个字节为一个long，因为java为big-endian格式，所以没办法 用了这么一个函数来做转换
     *
     * @param offset
     * @return 读取的long值，返回-1表示读取文件失败
     */
    private long readLong4(long offset) {
        long ret = 0;
        try {
            ipFile.seek(offset);
            ret |= (ipFile.readByte() & 0xFF);
            ret |= ((ipFile.readByte() << 8) & 0xFF00);
            ret |= ((ipFile.readByte() << 16) & 0xFF0000);
            ret |= ((ipFile.readByte() << 24) & 0xFF000000);
            return ret;
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * 从offset位置读取3个字节为一个long，因为java为big-endian格式，所以没办法 用了这么一个函数来做转换
     *
     * @param offset
     * @return 读取的long值，返回-1表示读取文件失败
     */
    private long readLong3(long offset) {
        long ret = 0;
        try {
            ipFile.seek(offset);
            ipFile.readFully(b3);
            ret |= (b3[0] & 0xFF);
            ret |= ((b3[1] << 8) & 0xFF00);
            ret |= ((b3[2] << 16) & 0xFF0000);
            return ret;
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * 从当前位置读取3个字节转换成long
     *
     * @return
     */
    private long readLong3() {
        long ret = 0;
        try {
            ipFile.readFully(b3);
            ret |= (b3[0] & 0xFF);
            ret |= ((b3[1] << 8) & 0xFF00);
            ret |= ((b3[2] << 16) & 0xFF0000);
            return ret;
        } catch (IOException e) {
            return -1;
        }
    }

    /**
     * 从offset位置读取四个字节的ip地址放入ip数组中，读取后的ip为big-endian格式，但是
     * 文件中是little-endian形式，将会进行转换
     *
     * @param offset
     * @param ip
     */
    private void readIP(long offset, byte[] ip) {
        try {
            ipFile.seek(offset);
            ipFile.readFully(ip);
            byte temp = ip[0];
            ip[0] = ip[3];
            ip[3] = temp;
            temp = ip[1];
            ip[1] = ip[2];
            ip[2] = temp;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 从offset位置读取四个字节的ip地址放入ip数组中，读取后的ip为big-endian格式，但是
     * 文件中是little-endian形式，将会进行转换
     *
     * @param offset
     * @param ip
     */
    private void readIP(int offset, byte[] ip) {
        mbb.position(offset);
        mbb.get(ip);
        byte temp = ip[0];
        ip[0] = ip[3];
        ip[3] = temp;
        temp = ip[1];
        ip[1] = ip[2];
        ip[2] = temp;
    }

    /**
     * 把类成员ip和beginIp比较，注意这个beginIp是big-endian的
     *
     * @param ip      要查询的IP
     * @param beginIp 和被查询IP相比较的IP
     * @return 相等返回0，ip大于beginIp则返回1，小于返回-1。
     */
    private int compareIP(byte[] ip, byte[] beginIp) {
        for (int i = 0; i < 4; i++) {
            int r = compareByte(ip[i], beginIp[i]);
            if (r != 0)
                return r;
        }
        return 0;
    }

    /**
     * 把两个byte当作无符号数进行比较
     *
     * @param b1
     * @param b2
     * @return 若b1大于b2则返回1，相等返回0，小于返回-1
     */
    private int compareByte(byte b1, byte b2) {
        if ((b1 & 0xFF) > (b2 & 0xFF)) // 比较是否大于
            return 1;
        else if ((b1 ^ b2) == 0)// 判断是否相等
            return 0;
        else
            return -1;
    }

    /**
     * 这个方法将根据ip的内容，定位到包含这个ip位置地区的记录处，返回一个绝对偏移 方法使用二分法查找。
     *
     * @param ip 要查询的IP
     * @return 如果找到了，返回结束IP的偏移，如果没有找到，返回-1
     */
    private long locateIP(byte[] ip) {
        long m = 0;
        int r;
        // 比较第一个ip项
        readIP(ipBegin, b4);
        r = compareIP(ip, b4);
        if (r == 0)
            return ipBegin;
        else if (r < 0)
            return -1;
        // 开始二分搜索
        for (long i = ipBegin, j = ipEnd; i < j; ) {
            m = getMiddleOffset(i, j);
            readIP(m, b4);
            r = compareIP(ip, b4);
            // log.debug(getIpStringFromBytes(b));
            if (r > 0)
                i = m;
            else if (r < 0) {
                if (m == j) {
                    j -= IP_RECORD_LENGTH;
                    m = j;
                } else
                    j = m;
            } else
                return readLong3(m + 4);
        }
        // 如果循环结束了，那么i和j必定是相等的，这个记录为最可能的记录，但是并非
        // 肯定就是，还要检查一下，如果是，就返回结束地址区的绝对偏移
        m = readLong3(m + 4);
        readIP(m, b4);
        r = compareIP(ip, b4);
        if (r <= 0)
            return m;
        else
            return -1;
    }

    /**
     * 得到begin偏移和end偏移中间位置记录的偏移
     *
     * @param begin
     * @param end
     * @return
     */
    private long getMiddleOffset(long begin, long end) {
        long records = (end - begin) / IP_RECORD_LENGTH;
        records >>= 1;
        if (records == 0)
            records = 1;
        return begin + records * IP_RECORD_LENGTH;
    }

    /**
     * 给定一个ip位置地区记录的偏移，返回一个IPLocation结构
     *
     * @param offset
     * @return
     */
    private IPLocation getIPLocation(long offset) {
        try {
            // 跳过4字节ip
            ipFile.seek(offset + 4);
            // 读取第一个字节判断是否标志字节
            byte b = ipFile.readByte();
            if (b == AREA_FOLLOWED) {
                // 读取位置偏移
                long locationOffset = readLong3();
                // 跳转至偏移处
                ipFile.seek(locationOffset);
                // 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
                b = ipFile.readByte();
                if (b == NO_AREA) {
                    loc.location = readString(readLong3());
                    ipFile.seek(locationOffset + 4);
                } else
                    loc.location = readString(locationOffset);
                // 读取地区标志
                loc.area = readArea(ipFile.getFilePointer());
            } else if (b == NO_AREA) {
                loc.location = readString(readLong3());
                loc.area = readArea(offset + 8);
            } else {
                loc.location = readString(ipFile.getFilePointer() - 1);
                loc.area = readArea(ipFile.getFilePointer());
            }
            return loc;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @param offset
     * @return
     */
    private IPLocation getIPLocation(int offset) {
        // 跳过4字节ip
        mbb.position(offset + 4);
        // 读取第一个字节判断是否标志字节
        byte b = mbb.get();
        if (b == AREA_FOLLOWED) {
            // 读取位置偏移
            int locationOffset = readInt3();
            // 跳转至偏移处
            mbb.position(locationOffset);
            // 再检查一次标志字节，因为这个时候这个地方仍然可能是个重定向
            b = mbb.get();
            if (b == NO_AREA) {
                loc.location = readString(readInt3());
                mbb.position(locationOffset + 4);
            } else
                loc.location = readString(locationOffset);
            // 读取地区标志
            loc.area = readArea(mbb.position());
        } else if (b == NO_AREA) {
            loc.location = readString(readInt3());
            loc.area = readArea(offset + 8);
        } else {
            loc.location = readString(mbb.position() - 1);
            loc.area = readArea(mbb.position());
        }
        return loc;
    }

    /**
     * 从offset偏移开始解析后面的字节，读出一个地区名
     *
     * @param offset
     * @return 地区名字符串
     * @throws IOException
     */
    private String readArea(long offset) throws IOException {
        ipFile.seek(offset);
        byte b = ipFile.readByte();
        if (b == 0x01 || b == 0x02) {
            long areaOffset = readLong3(offset + 1);
            if (areaOffset == 0)
                return "未知地区";
            else
                return readString(areaOffset);
        } else
            return readString(offset);
    }

    /**
     * @param offset
     * @return
     */
    private String readArea(int offset) {
        mbb.position(offset);
        byte b = mbb.get();
        if (b == 0x01 || b == 0x02) {
            int areaOffset = readInt3();
            if (areaOffset == 0)
                return "未知地区";
            else
                return readString(areaOffset);
        } else
            return readString(offset);
    }

    /**
     * 从offset偏移处读取一个以0结束的字符串
     *
     * @param offset
     * @return 读取的字符串，出错返回空字符串
     */
    private String readString(long offset) {
        try {
            ipFile.seek(offset);
            //int i;
            //for (i = 0, buf[i] = ipFile.readByte(); buf[i] != 0; buf[++i] = ipFile.readByte());
            //上面的写法读取数据如果超过100个字节就会报数组越界异常
            int i = 0;
            byte[] buf = new byte[256];
            while ((buf[i] = ipFile.readByte()) != 0) {
                ++i;
                if (i >= buf.length) {
                    byte[] tmp = new byte[i + 100];
                    System.arraycopy(buf, 0, tmp, 0, i);
                    buf = tmp;
                }
            }
            /////////
            if (i != 0)
                return getString(buf, 0, i, "GBK");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * 从内存映射文件的offset位置得到一个0结尾字符串
     *
     * @param offset
     * @return
     */
    private String readString(int offset) {
        try {
            mbb.position(offset);
            //int i;
            //for (i = 0, buf[i] = mbb.get(); buf[i] != 0; buf[++i] = mbb.get());
            int i = 0;
            byte[] buf = new byte[256];
            while ((buf[i] = mbb.get()) != 0) {
                ++i;
                if (i >= buf.length) {
                    byte[] tmp = new byte[i + 100];
                    System.arraycopy(buf, 0, tmp, 0, i);
                    buf = tmp;
                }
            }
            if (i != 0)
                return getString(buf, 0, i, "GBK");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Deprecated
    public String getAddress(String ip) {
        String location = getLocation(ip).equals(" CZ88.NET") ? ""
                : getLocation(ip);
        String area = getArea(ip).equals(" CZ88.NET") ? "" : getArea(ip);
        String address = location + " " + area;
        return address.trim();
    }

    /**
     * <pre>
     * 用来封装ip相关信息，目前只有两个字段，ip所在的位置和地区
     * </pre>
     */
    private class IPLocation {
        public String location;
        public String area;

        public IPLocation() {
            location = area = "";
        }

        public IPLocation getCopy() {
            IPLocation ret = new IPLocation();
            ret.location = location;
            ret.area = area;
            return ret;
        }
    }
}