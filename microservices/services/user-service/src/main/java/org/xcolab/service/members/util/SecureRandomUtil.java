package org.xcolab.service.members.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SecureRandomUtil {
    private static final int _BUFFER_SIZE = 2048;
    private static final byte[] _bytes;
    private static final AtomicInteger _index = new AtomicInteger();
    private static final Random _random = new SecureRandom();
    private static final AtomicBoolean _reloadingFlag = new AtomicBoolean();
    private static long _gapSeed;

    static {
        _bytes = new byte[_BUFFER_SIZE];
        _random.nextBytes(_bytes);
        _gapSeed = _random.nextLong();
    }

    public SecureRandomUtil() {
    }

    public static boolean nextBoolean() {
        byte b = nextByte();
        return b >= 0;
    }

    public static byte nextByte() {
        int index = _index.getAndIncrement();
        return index < _BUFFER_SIZE ? _bytes[index] : (byte) ((int) _reload());
    }

    private static long _reload() {
        if (_reloadingFlag.compareAndSet(false, true)) {
            _random.nextBytes(_bytes);
            _index.set(0);
            _reloadingFlag.set(false);
        }

        int offset = _index.get() % (_BUFFER_SIZE - 7);
        long l = BigEndianCodec.getLong(_bytes, offset) ^ _gapSeed;
        _gapSeed = l;
        return l;
    }

    public static double nextDouble() {
        int index = _index.getAndAdd(8);
        return index + 7 < _BUFFER_SIZE ? BigEndianCodec.getDouble(_bytes, index) : Double.longBitsToDouble(_reload());
    }

    public static float nextFloat() {
        int index = _index.getAndAdd(4);
        return index + 3 < _BUFFER_SIZE ? BigEndianCodec.getFloat(_bytes, index)
                : Float.intBitsToFloat((int) _reload());
    }

    public static int nextInt() {
        int index = _index.getAndAdd(4);
        return index + 3 < _BUFFER_SIZE ? BigEndianCodec.getInt(_bytes, index) : (int) _reload();
    }

    public static long nextLong() {
        int index = _index.getAndAdd(8);
        return index + 7 < _BUFFER_SIZE ? BigEndianCodec.getLong(_bytes, index) : _reload();
    }
}
