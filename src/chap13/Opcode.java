package chap13;
import stone.StoneException;

public class Opcode {
    public static final byte ICONST = 1;
    public static final byte BCONST =2;
    public static final byte SCONST = 3;
    public static final byte MOVE = 4;
    public static final byte GMOVE = 5;
    public static final byte IFZERO = 6;
    public static final byte GOTO = 7;
    public static final byte CALL = 8;
    public static final byte RETURN = 9;
    public static final byte SAVE = 10;
    public static final byte RESTORE = 11;
    public static final byte NEG = 12;
    public static final byte ADD = 13;
    public static final byte SUB = 14;
    public static final byte MUL = 15;
    public static final byte DIV = 16;
    public static final byte REM = 17;
    public static final byte EQUAL = 18;
    public static final byte MORE = 19;
    public static final byte LESS = 20;

    public static byte encodeRegister(int reg) {
        if (reg > StoneVM.NUM_OF_REG) 
            throw new StoneException("too many registers required");
        else
            return (byte) - (reg + 1);
    }
    public static int decodeRegister(byte operand) {return - 1 - operand;}
    public static byte encodeOffset(int offset) {
        if (offset > Byte.MAX_VALUE)
            throw new StoneException("too big byte offset");
        else
            return (byte) offset;
    }
    public static short encodeShortOffset(int offset) {
        if (offset < Short.MIN_VALUE || Short.MAX_VALUE < offset)
            throw new StoneException("too big short offset");
        else
            return (short) offset;
    }
    public static int decodeOffset(byte operand) { return operand;}
    public static boolean isRegister(byte operand) { return operand < 0;}
    public static boolean isOffset(byte operand) { return operand >= 0;}
}