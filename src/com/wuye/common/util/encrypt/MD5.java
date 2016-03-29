package com.wuye.common.util.encrypt;

import java.lang.reflect.Array;
import java.security.MessageDigest;

/**
 * =============================================================<br>
 * 版权： 物业软件 版权所有 (c) 2002 - 2005.<br>
 * 文件： com.wuye.common.numeric<br>
 * 所含类： MD5<br>
 * 编写人员：jerry<br>
 * 创建日期：2004-11-04<br>
 * 功能说明：md5的加密 <br>
 * 更新记录：<br>
 * 日期 作者 内容<br>
 * =============================================================<br>
 * <br>
 * =============================================================<br>
 */

public class MD5 {
    /*
     * 下面这些S11-S44实际上是一个4*4的矩阵，在原始的C实现中是用#define 实现的， 这里把它们实现成为static
     * final是表示了只读，切能在同一个进程空间内的多个 Instance间共享
     */
    static final int S11 = 7;

    static final int S12 = 12;

    static final int S13 = 17;

    static final int S14 = 22;

    static final int S21 = 5;

    static final int S22 = 9;

    static final int S23 = 14;

    static final int S24 = 20;

    static final int S31 = 4;

    static final int S32 = 11;

    static final int S33 = 16;

    static final int S34 = 23;

    static final int S41 = 6;

    static final int S42 = 10;

    static final int S43 = 15;

    static final int S44 = 21;

    static final byte[] PADDING = {-128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0};

    /*
     * 下面的三个成员是MD5计算过程中用到的3个核心数据，在原始的C实现中 被定义到MD5_CTX结构中
     */
    private final long[] state = new long[4]; // state (ABCD)

    private final long[] count = new long[2]; // number of bits, modulo 2^64
    // (lsb

    // first)

    private final byte[] buffer = new byte[64]; // input buffer

    /**
     * digestHexStr是MD5的唯一一个公共成员，是最新一次计算结果的 16进制ASCII表示.
     */
    public String digestHexStr;

    /*
     * digest,是最新一次计算结果的2进制内部表示，表示128bit的MD5值.
     */
    private final byte[] digest = new byte[16];

    /**
     * getMD5ofStr是类MD5最主要的公共方法，入口参数是你想要进行MD5变换的字符串.
     * 返回的是变换完的结果，这个结果是从公共成员digestHexStr取得的．
     *
     * @param inbuf 想要进行MD5变换的字符串
     * @return 是变换完的结果
     */

    public String getMD5ofStr(final String inbuf) {
        this.md5Init();
        this.md5Update(inbuf.getBytes(), inbuf.length());
        this.md5Final();
        this.digestHexStr = "";
        for (int i = 0; i < 16; i++) {
            this.digestHexStr += MD5.byteHEX(this.digest[i]);
        }
        return this.digestHexStr;

    }

    /**
     * 这是MD5这个类的标准构造函数，JavaBean要求有一个public的并且没有参数的构造函数.
     *
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-04 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */

    public MD5() {
        this.md5Init();

        return;
    }

    /**
     * md5Init是一个初始化函数，初始化核心变量，装入标准的幻数.
     *
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2004-11-04 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void md5Init() {
        this.count[0] = 0L;
        this.count[1] = 0L;
        // /* Load magic initialization constants.

        this.state[0] = 0x67452301L;
        this.state[1] = 0xefcdab89L;
        this.state[2] = 0x98badcfeL;
        this.state[3] = 0x10325476L;

        return;
    }

    /**
     * F, G, H ,I 是4个基本的MD5函数，在原始的MD5的C实现中，由于它们是.
     * 简单的位运算，可能出于效率的考虑把它们实现成了宏，在java中，我们把它们 实现成了private方法，名字保持了原来C中的
     *
     * @param x long
     * @param y long
     * @param z long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期::2004-11-04 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long F(long x, final long y, final long z) {
        return (x & y) | ((~x) & z);

    }

    /**
     * @param x long
     * @param y long
     * @param z long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long G(final long x, final long y, long z) {
        return (x & z) | (y & (~z));

    }

    /**
     * @param x long
     * @param y long
     * @param z long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long H(final long x, final long y, final long z) {
        return x ^ y ^ z;
    }

    /**
     * @param x long
     * @param y long
     * @param z long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long I(final long x, final long y, long z) {
        return y ^ (x | (~z));
    }

    /**
     * FF,GG,HH和II将调用F,G,H,I进行近一步变换 FF, GG, HH, and II transformations for
     * rounds 1, 2, 3, and 4. Rotation is separate from addition to prevent
     * recomputation.
     *
     * @param a  long
     * @param b  long
     * @param c  long
     * @param d  long
     * @param x  long
     * @param s  long
     * @param ac long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long FF(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += this.F(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }

    /**
     * @param a  long
     * @param b  long
     * @param c  long
     * @param d  long
     * @param x  long
     * @param s  long
     * @param ac long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long GG(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += this.G(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }

    /**
     * @param a  long
     * @param b  long
     * @param c  long
     * @param d  long
     * @param x  long
     * @param s  long
     * @param ac long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long HH(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += this.H(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }

    /**
     * @param a  long
     * @param b  long
     * @param c  long
     * @param d  long
     * @param x  long
     * @param s  long
     * @param ac long
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private long II(long a, final long b, final long c, final long d, final long x, final long s, final long ac) {
        a += this.I(b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }

    /**
     * md5Update是MD5的主计算过程，inbuf是要变换的字节串，inputlen是长度，这个
     * 函数由getMD5ofStr调用，调用之前需要调用md5init，因此把它设计成private的.
     *
     * @param inbuf    要变换的字节串
     * @param inputLen 输入长度
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void md5Update(final byte[] inbuf, final int inputLen) {

        int i, index, partLen;
        final byte[] block = new byte[64];
        index = (int) (this.count[0] >>> 3) & 0x3F;
        // /* Update number of bits */
        if ((this.count[0] += (inputLen << 3)) < (inputLen << 3)) {
            this.count[1]++;
        }
        this.count[1] += (inputLen >>> 29);

        partLen = 64 - index;

        // Transform as many times as possible.
        if (inputLen >= partLen) {
            this.md5Memcpy(this.buffer, inbuf, index, 0, partLen);
            this.md5Transform(this.buffer);

            for (i = partLen; i + 63 < inputLen; i += 64) {

                this.md5Memcpy(block, inbuf, 0, i, 64);
                this.md5Transform(block);
            }
            index = 0;

        } else {
            i = 0;
        }
        // /* Buffer remaining input */
        this.md5Memcpy(this.buffer, inbuf, index, i, inputLen - i);

    }

    /**
     * md5Final整理和填写输出结果.
     *
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void md5Final() {
        final byte[] bits = new byte[8];
        int index, padLen;

        // /* Save number of bits */
        this.Encode(bits, this.count, 8);

        // /* Pad out to 56 mod 64.
        index = (int) (this.count[0] >>> 3) & 0x3f;
        padLen = (index < 56) ? (56 - index)
                : (120 - index);
        this.md5Update(MD5.PADDING, padLen);

        // /* Append length (before padding) */
        this.md5Update(bits, 8);

        // /* Store state in digest */
        this.Encode(this.digest, this.state, 16);

    }

    /**
     * md5Memcpy是一个内部使用的byte数组的块拷贝函数，从input的inpos开始把len长度的
     * 字节拷贝到output的outpos位置开始.
     *
     * @param output byte[]
     * @param input  byte[]
     * @param outpos int
     * @param inpos  int
     * @param len    int
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void md5Memcpy(final byte[] output, final byte[] input, final int outpos, final int inpos, final int len) {
        int i;

        for (i = 0; i < len; i++) {
            output[outpos + i] = input[inpos + i];
        }
    }

    /**
     * md5Transform是MD5核心变换程序，有md5Update调用，block是分块的原始字节.
     *
     * @param block byte
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void md5Transform(final byte[] block) {
        long a = this.state[0], b = this.state[1], c = this.state[2], d = this.state[3];
        final long[] x = new long[16];

        this.Decode(x, block, 64);
        
        /* Round 1 */
        a = this.FF(a, b, c, d, x[0], MD5.S11, 0xd76aa478L); /* 1 */
        d = this.FF(d, a, b, c, x[1], MD5.S12, 0xe8c7b756L); /* 2 */
        c = this.FF(c, d, a, b, x[2], MD5.S13, 0x242070dbL); /* 3 */
        b = this.FF(b, c, d, a, x[3], MD5.S14, 0xc1bdceeeL); /* 4 */
        a = this.FF(a, b, c, d, x[4], MD5.S11, 0xf57c0fafL); /* 5 */
        d = this.FF(d, a, b, c, x[5], MD5.S12, 0x4787c62aL); /* 6 */
        c = this.FF(c, d, a, b, x[6], MD5.S13, 0xa8304613L); /* 7 */
        b = this.FF(b, c, d, a, x[7], MD5.S14, 0xfd469501L); /* 8 */
        a = this.FF(a, b, c, d, x[8], MD5.S11, 0x698098d8L); /* 9 */
        d = this.FF(d, a, b, c, x[9], MD5.S12, 0x8b44f7afL); /* 10 */
        c = this.FF(c, d, a, b, x[10], MD5.S13, 0xffff5bb1L); /* 11 */
        b = this.FF(b, c, d, a, x[11], MD5.S14, 0x895cd7beL); /* 12 */
        a = this.FF(a, b, c, d, x[12], MD5.S11, 0x6b901122L); /* 13 */
        d = this.FF(d, a, b, c, x[13], MD5.S12, 0xfd987193L); /* 14 */
        c = this.FF(c, d, a, b, x[14], MD5.S13, 0xa679438eL); /* 15 */
        b = this.FF(b, c, d, a, x[15], MD5.S14, 0x49b40821L); /* 16 */
        
        /* Round 2 */
        a = this.GG(a, b, c, d, x[1], MD5.S21, 0xf61e2562L); /* 17 */
        d = this.GG(d, a, b, c, x[6], MD5.S22, 0xc040b340L); /* 18 */
        c = this.GG(c, d, a, b, x[11], MD5.S23, 0x265e5a51L); /* 19 */
        b = this.GG(b, c, d, a, x[0], MD5.S24, 0xe9b6c7aaL); /* 20 */
        a = this.GG(a, b, c, d, x[5], MD5.S21, 0xd62f105dL); /* 21 */
        d = this.GG(d, a, b, c, x[10], MD5.S22, 0x2441453L); /* 22 */
        c = this.GG(c, d, a, b, x[15], MD5.S23, 0xd8a1e681L); /* 23 */
        b = this.GG(b, c, d, a, x[4], MD5.S24, 0xe7d3fbc8L); /* 24 */
        a = this.GG(a, b, c, d, x[9], MD5.S21, 0x21e1cde6L); /* 25 */
        d = this.GG(d, a, b, c, x[14], MD5.S22, 0xc33707d6L); /* 26 */
        c = this.GG(c, d, a, b, x[3], MD5.S23, 0xf4d50d87L); /* 27 */
        b = this.GG(b, c, d, a, x[8], MD5.S24, 0x455a14edL); /* 28 */
        a = this.GG(a, b, c, d, x[13], MD5.S21, 0xa9e3e905L); /* 29 */
        d = this.GG(d, a, b, c, x[2], MD5.S22, 0xfcefa3f8L); /* 30 */
        c = this.GG(c, d, a, b, x[7], MD5.S23, 0x676f02d9L); /* 31 */
        b = this.GG(b, c, d, a, x[12], MD5.S24, 0x8d2a4c8aL); /* 32 */
        
        /* Round 3 */
        a = this.HH(a, b, c, d, x[5], MD5.S31, 0xfffa3942L); /* 33 */
        d = this.HH(d, a, b, c, x[8], MD5.S32, 0x8771f681L); /* 34 */
        c = this.HH(c, d, a, b, x[11], MD5.S33, 0x6d9d6122L); /* 35 */
        b = this.HH(b, c, d, a, x[14], MD5.S34, 0xfde5380cL); /* 36 */
        a = this.HH(a, b, c, d, x[1], MD5.S31, 0xa4beea44L); /* 37 */
        d = this.HH(d, a, b, c, x[4], MD5.S32, 0x4bdecfa9L); /* 38 */
        c = this.HH(c, d, a, b, x[7], MD5.S33, 0xf6bb4b60L); /* 39 */
        b = this.HH(b, c, d, a, x[10], MD5.S34, 0xbebfbc70L); /* 40 */
        a = this.HH(a, b, c, d, x[13], MD5.S31, 0x289b7ec6L); /* 41 */
        d = this.HH(d, a, b, c, x[0], MD5.S32, 0xeaa127faL); /* 42 */
        c = this.HH(c, d, a, b, x[3], MD5.S33, 0xd4ef3085L); /* 43 */
        b = this.HH(b, c, d, a, x[6], MD5.S34, 0x4881d05L); /* 44 */
        a = this.HH(a, b, c, d, x[9], MD5.S31, 0xd9d4d039L); /* 45 */
        d = this.HH(d, a, b, c, x[12], MD5.S32, 0xe6db99e5L); /* 46 */
        c = this.HH(c, d, a, b, x[15], MD5.S33, 0x1fa27cf8L); /* 47 */
        b = this.HH(b, c, d, a, x[2], MD5.S34, 0xc4ac5665L); /* 48 */
        
        /* Round 4 */
        a = this.II(a, b, c, d, x[0], MD5.S41, 0xf4292244L); /* 49 */
        d = this.II(d, a, b, c, x[7], MD5.S42, 0x432aff97L); /* 50 */
        c = this.II(c, d, a, b, x[14], MD5.S43, 0xab9423a7L); /* 51 */
        b = this.II(b, c, d, a, x[5], MD5.S44, 0xfc93a039L); /* 52 */
        a = this.II(a, b, c, d, x[12], MD5.S41, 0x655b59c3L); /* 53 */
        d = this.II(d, a, b, c, x[3], MD5.S42, 0x8f0ccc92L); /* 54 */
        c = this.II(c, d, a, b, x[10], MD5.S43, 0xffeff47dL); /* 55 */
        b = this.II(b, c, d, a, x[1], MD5.S44, 0x85845dd1L); /* 56 */
        a = this.II(a, b, c, d, x[8], MD5.S41, 0x6fa87e4fL); /* 57 */
        d = this.II(d, a, b, c, x[15], MD5.S42, 0xfe2ce6e0L); /* 58 */
        c = this.II(c, d, a, b, x[6], MD5.S43, 0xa3014314L); /* 59 */
        b = this.II(b, c, d, a, x[13], MD5.S44, 0x4e0811a1L); /* 60 */
        a = this.II(a, b, c, d, x[4], MD5.S41, 0xf7537e82L); /* 61 */
        d = this.II(d, a, b, c, x[11], MD5.S42, 0xbd3af235L); /* 62 */
        c = this.II(c, d, a, b, x[2], MD5.S43, 0x2ad7d2bbL); /* 63 */
        b = this.II(b, c, d, a, x[9], MD5.S44, 0xeb86d391L); /* 64 */

        this.state[0] += a;
        this.state[1] += b;
        this.state[2] += c;
        this.state[3] += d;

    }

    /**
     * Encode把long数组按顺序拆成byte数组，因为java的long类型是64bit的， 只拆低32bit，以适应原始C实现的用途.
     *
     * @param output byte[]
     * @param input  byte[]
     * @param len    int
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void Encode(final byte[] output, final long[] input, final int len) {
        int i, j;

        for (i = 0, j = 0; j < len; i++, j += 4) {
            output[j] = (byte) (input[i] & 0xffL);
            output[j + 1] = (byte) ((input[i] >>> 8) & 0xffL);
            output[j + 2] = (byte) ((input[i] >>> 16) & 0xffL);
            output[j + 3] = (byte) ((input[i] >>> 24) & 0xffL);
        }
    }

    /**
     * Decode把byte数组按顺序合成成long数组，因为java的long类型是64bit的，
     * 只合成低32bit，高32bit清零，以适应原始C实现的用途.
     *
     * @param output long[]
     * @param input  byte[]
     * @param len    int
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    private void Decode(final long[] output, final byte[] input, final int len) {
        int i, j;

        for (i = 0, j = 0; j < len; i++, j += 4) {
            output[i] = MD5.b2iu(input[j]) | (MD5.b2iu(input[j + 1]) << 8) | (MD5.b2iu(input[j + 2]) << 16)
                    | (MD5.b2iu(input[j + 3]) << 24);

        }
        return;
    }

    /**
     * b2iu是我写的一个把byte按照不考虑正负号的原则的＂升位＂程序，因为java没有unsigned运算.
     *
     * @param b byte
     * @return long
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static long b2iu(final byte b) {
        return b < 0 ? b & 0x7F + 128
                : b;
    }

    /**
     * byteHEX()，用来把一个byte类型的数转换成十六进制的ASCII表示，
     * 因为java中的byte的toString无法实现这一点，我们又没有C语言中的 sprintf(outbuf,"%02X",ib).
     *
     * @param ib byte
     * @return 十六进制的ASCII
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static String byteHEX(final byte ib) {
        final char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        final char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        final String s = new String(ob);
        return s;
    }

    /**
     * .
     *
     * @param array
     * @return
     * @author zfz
     * 2011-2-22 zfz
     */
    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
        }
        return sb.toString();
    }

    /**
     * .
     *
     * @param message
     * @return
     * @author zfz
     * 2011-2-22 zfz
     */
    public static String encrypt(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("ISO8859-1")));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @param args String
     * @author: jerry
     * @修改记录： ==============================================================<br>
     * 日期:2007-9-20 jerry 创建方法，并实现其功能
     * ==============================================================<br>
     */
    public static void main(final String[] args) {

        final MD5 m = new MD5();
        if (Array.getLength(args) == 0) { // 如果没有参数，执行标准的Test Suite

            System.out.println("MD5 Test suite:");
            System.out.println("MD5(\"\"):" + m.getMD5ofStr(""));
            System.out.println("MD5(\"a\"):" + m.getMD5ofStr("a"));
            System.out.println("MD5(\"abc\"):" + m.getMD5ofStr("abc"));
            System.out.println("MD5(\"message digest\"):" + m.getMD5ofStr("message digest"));
            System.out.println("MD5(\"abcdefghijklmnopqrstuvwxyz\"):" + m.getMD5ofStr("abcdefghijklmnopqrstuvwxyz"));
            System.out.println("123=" + m.getMD5ofStr("123"));
            System.out.println("MD5(\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\"):"
                    + m.getMD5ofStr("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"));
        } else {
            System.out.println("MD5(" + args[0] + ")=" + m.getMD5ofStr(args[0]));
        }
    }

}
