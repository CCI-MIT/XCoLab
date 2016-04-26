package org.xcolab.service.members.util;

//copied from com.liferay.portal.kernel.io
public final  class BigEndianCodec {
    private BigEndianCodec() {
    }

    public static boolean getBoolean(byte[] bytes, int index) {
        return bytes[index] != 0;
    }

    public static char getChar(byte[] bytes, int index) {
        return (char)((bytes[index] << 8) + (bytes[index + 1] & 255));
    }

    public static double getDouble(byte[] bytes, int index) {
        return Double.longBitsToDouble(getLong(bytes, index));
    }

    public static float getFloat(byte[] bytes, int index) {
        return Float.intBitsToFloat(getInt(bytes, index));
    }

    public static int getInt(byte[] bytes, int index) {
        return (bytes[index] << 24) + ((bytes[index + 1] & 255) << 16) + ((bytes[index + 2] & 255) << 8) + (bytes[index + 3] & 255);
    }

    public static long getLong(byte[] bytes, int index) {
        return ((long)bytes[index] << 56) + (((long)bytes[index + 1] & 255L) << 48) + (((long)bytes[index + 2] & 255L) << 40) + (((long)bytes[index + 3] & 255L) << 32) + (((long)bytes[index + 4] & 255L) << 24) + (((long)bytes[index + 5] & 255L) << 16) + (((long)bytes[index + 6] & 255L) << 8) + ((long)bytes[index + 7] & 255L);
    }

    public static short getShort(byte[] bytes, int index) {
        return (short)((bytes[index] << 8) + (bytes[index + 1] & 255));
    }

    public static void putBoolean(byte[] bytes, int index, boolean b) {
        bytes[index] = (byte)(b?1:0);
    }

    public static void putChar(byte[] bytes, int index, char c) {
        bytes[index] = (byte)(c >>> 8);
        bytes[index + 1] = (byte)c;
    }

    public static void putDouble(byte[] bytes, int index, double d) {
        putLong(bytes, index, Double.doubleToLongBits(d));
    }

    public static void putFloat(byte[] bytes, int index, float f) {
        putInt(bytes, index, Float.floatToIntBits(f));
    }

    public static void putInt(byte[] bytes, int index, int i) {
        bytes[index] = (byte)(i >>> 24);
        bytes[index + 1] = (byte)(i >>> 16);
        bytes[index + 2] = (byte)(i >>> 8);
        bytes[index + 3] = (byte)i;
    }

    public static void putLong(byte[] bytes, int index, long l) {
        bytes[index] = (byte)((int)(l >>> 56));
        bytes[index + 1] = (byte)((int)(l >>> 48));
        bytes[index + 2] = (byte)((int)(l >>> 40));
        bytes[index + 3] = (byte)((int)(l >>> 32));
        bytes[index + 4] = (byte)((int)(l >>> 24));
        bytes[index + 5] = (byte)((int)(l >>> 16));
        bytes[index + 6] = (byte)((int)(l >>> 8));
        bytes[index + 7] = (byte)((int)l);
    }

    public static void putShort(byte[] bytes, int index, short s) {
        bytes[index] = (byte)(s >>> 8);
        bytes[index + 1] = (byte)s;
    }
}
