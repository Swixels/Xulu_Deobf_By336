package com.elementars.eclient.util;

import java.math.*;
import java.lang.reflect.*;
import org.apache.commons.lang3.*;

public class NumberUtils
{
    public static long max(long llllllllllllllllllIlIIIIlIIIIIII, final long llllllllllllllllllIlIIIIIlllllll, final long llllllllllllllllllIlIIIIIllllllI) {
        if (llllllllllllllllllIlIIIIIlllllll > llllllllllllllllllIlIIIIlIIIIIII) {
            llllllllllllllllllIlIIIIlIIIIIII = llllllllllllllllllIlIIIIIlllllll;
        }
        if (llllllllllllllllllIlIIIIIllllllI > llllllllllllllllllIlIIIIlIIIIIII) {
            llllllllllllllllllIlIIIIlIIIIIII = llllllllllllllllllIlIIIIIllllllI;
        }
        return llllllllllllllllllIlIIIIlIIIIIII;
    }
    
    public static BigInteger createBigInteger(final String llllllllllllllllllIlIIIlIIlllIII) {
        if (llllllllllllllllllIlIIIlIIlllIII == null) {
            return null;
        }
        int llllllllllllllllllIlIIIlIIllIlll = 0;
        int llllllllllllllllllIlIIIlIIllIllI = 10;
        boolean llllllllllllllllllIlIIIlIIllIlIl = false;
        if (llllllllllllllllllIlIIIlIIlllIII.startsWith("-")) {
            llllllllllllllllllIlIIIlIIllIlIl = true;
            llllllllllllllllllIlIIIlIIllIlll = 1;
        }
        if (llllllllllllllllllIlIIIlIIlllIII.startsWith("0x", llllllllllllllllllIlIIIlIIllIlll) || llllllllllllllllllIlIIIlIIlllIII.startsWith("0X", llllllllllllllllllIlIIIlIIllIlll)) {
            llllllllllllllllllIlIIIlIIllIllI = 16;
            llllllllllllllllllIlIIIlIIllIlll += 2;
        }
        else if (llllllllllllllllllIlIIIlIIlllIII.startsWith("#", llllllllllllllllllIlIIIlIIllIlll)) {
            llllllllllllllllllIlIIIlIIllIllI = 16;
            ++llllllllllllllllllIlIIIlIIllIlll;
        }
        else if (llllllllllllllllllIlIIIlIIlllIII.startsWith("0", llllllllllllllllllIlIIIlIIllIlll) && llllllllllllllllllIlIIIlIIlllIII.length() > llllllllllllllllllIlIIIlIIllIlll + 1) {
            llllllllllllllllllIlIIIlIIllIllI = 8;
            ++llllllllllllllllllIlIIIlIIllIlll;
        }
        final BigInteger llllllllllllllllllIlIIIlIIllIlII = new BigInteger(llllllllllllllllllIlIIIlIIlllIII.substring(llllllllllllllllllIlIIIlIIllIlll), llllllllllllllllllIlIIIlIIllIllI);
        return llllllllllllllllllIlIIIlIIllIlIl ? llllllllllllllllllIlIIIlIIllIlII.negate() : llllllllllllllllllIlIIIlIIllIlII;
    }
    
    public static short toShort(final String llllllllllllllllllIlIIIllIlIIIIl) {
        return toShort(llllllllllllllllllIlIIIllIlIIIIl, (short)0);
    }
    
    public static short max(short llllllllllllllllllIlIIIIIllIlllI, final short llllllllllllllllllIlIIIIIlllIIII, final short llllllllllllllllllIlIIIIIllIllll) {
        if (llllllllllllllllllIlIIIIIlllIIII > llllllllllllllllllIlIIIIIllIlllI) {
            llllllllllllllllllIlIIIIIllIlllI = llllllllllllllllllIlIIIIIlllIIII;
        }
        if (llllllllllllllllllIlIIIIIllIllll > llllllllllllllllllIlIIIIIllIlllI) {
            llllllllllllllllllIlIIIIIllIlllI = llllllllllllllllllIlIIIIIllIllll;
        }
        return llllllllllllllllllIlIIIIIllIlllI;
    }
    
    public static Short createShort(final String llllllllllllllllllIlIIIlIlIIIlll) {
        if (llllllllllllllllllIlIIIlIlIIIlll == null) {
            return null;
        }
        return Short.valueOf(llllllllllllllllllIlIIIlIlIIIlll);
    }
    
    public static float max(final float llllllllllllllllllIlIIIIIlIlIIll, final float llllllllllllllllllIlIIIIIlIlIIlI, final float llllllllllllllllllIlIIIIIlIlIIIl) {
        return Math.max(Math.max(llllllllllllllllllIlIIIIIlIlIIll, llllllllllllllllllIlIIIIIlIlIIlI), llllllllllllllllllIlIIIIIlIlIIIl);
    }
    
    public static float max(final float... llllllllllllllllllIlIIIIllIIIIlI) {
        validateArray(llllllllllllllllllIlIIIIllIIIIlI);
        float llllllllllllllllllIlIIIIllIIIIll = llllllllllllllllllIlIIIIllIIIIlI[0];
        for (int llllllllllllllllllIlIIIIllIIIlIl = 1; llllllllllllllllllIlIIIIllIIIlIl < llllllllllllllllllIlIIIIllIIIIlI.length; ++llllllllllllllllllIlIIIIllIIIlIl) {
            if (Float.isNaN(llllllllllllllllllIlIIIIllIIIIlI[llllllllllllllllllIlIIIIllIIIlIl])) {
                return Float.NaN;
            }
            if (llllllllllllllllllIlIIIIllIIIIlI[llllllllllllllllllIlIIIIllIIIlIl] > llllllllllllllllllIlIIIIllIIIIll) {
                llllllllllllllllllIlIIIIllIIIIll = llllllllllllllllllIlIIIIllIIIIlI[llllllllllllllllllIlIIIIllIIIlIl];
            }
        }
        return llllllllllllllllllIlIIIIllIIIIll;
    }
    
    public static double max(final double llllllllllllllllllIlIIIIIlIlllII, final double llllllllllllllllllIlIIIIIlIllllI, final double llllllllllllllllllIlIIIIIlIllIlI) {
        return Math.max(Math.max(llllllllllllllllllIlIIIIIlIlllII, llllllllllllllllllIlIIIIIlIllllI), llllllllllllllllllIlIIIIIlIllIlI);
    }
    
    private static boolean withDecimalsParsing(final String llllllllllllllllllIIllllllllllIl, final int llllllllllllllllllIIlllllllllIIl) {
        int llllllllllllllllllIIlllllllllIll = 0;
        for (int llllllllllllllllllIIlllllllllllI = llllllllllllllllllIIlllllllllIIl; llllllllllllllllllIIlllllllllllI < llllllllllllllllllIIllllllllllIl.length(); ++llllllllllllllllllIIlllllllllllI) {
            final boolean llllllllllllllllllIIllllllllllll = llllllllllllllllllIIllllllllllIl.charAt(llllllllllllllllllIIlllllllllllI) == '.';
            if (llllllllllllllllllIIllllllllllll) {
                ++llllllllllllllllllIIlllllllllIll;
            }
            if (llllllllllllllllllIIlllllllllIll > 1) {
                return false;
            }
            if (!llllllllllllllllllIIllllllllllll && !Character.isDigit(llllllllllllllllllIIllllllllllIl.charAt(llllllllllllllllllIIlllllllllllI))) {
                return false;
            }
        }
        return true;
    }
    
    public static int min(final int... llllllllllllllllllIlIIIlIIIlllII) {
        validateArray(llllllllllllllllllIlIIIlIIIlllII);
        int llllllllllllllllllIlIIIlIIIlllIl = llllllllllllllllllIlIIIlIIIlllII[0];
        for (int llllllllllllllllllIlIIIlIIIlllll = 1; llllllllllllllllllIlIIIlIIIlllll < llllllllllllllllllIlIIIlIIIlllII.length; ++llllllllllllllllllIlIIIlIIIlllll) {
            if (llllllllllllllllllIlIIIlIIIlllII[llllllllllllllllllIlIIIlIIIlllll] < llllllllllllllllllIlIIIlIIIlllIl) {
                llllllllllllllllllIlIIIlIIIlllIl = llllllllllllllllllIlIIIlIIIlllII[llllllllllllllllllIlIIIlIIIlllll];
            }
        }
        return llllllllllllllllllIlIIIlIIIlllIl;
    }
    
    public static float min(final float llllllllllllllllllIlIIIIlIIIllII, final float llllllllllllllllllIlIIIIlIIIlIll, final float llllllllllllllllllIlIIIIlIIIIlll) {
        return Math.min(Math.min(llllllllllllllllllIlIIIIlIIIllII, llllllllllllllllllIlIIIIlIIIlIll), llllllllllllllllllIlIIIIlIIIIlll);
    }
    
    @Deprecated
    public static boolean isNumber(final String llllllllllllllllllIlIIIIIlIIlIll) {
        return isCreatable(llllllllllllllllllIlIIIIIlIIlIll);
    }
    
    public static byte max(byte llllllllllllllllllIlIIIIIllIIlIl, final byte llllllllllllllllllIlIIIIIllIIlll, final byte llllllllllllllllllIlIIIIIllIIllI) {
        if (llllllllllllllllllIlIIIIIllIIlll > llllllllllllllllllIlIIIIIllIIlIl) {
            llllllllllllllllllIlIIIIIllIIlIl = llllllllllllllllllIlIIIIIllIIlll;
        }
        if (llllllllllllllllllIlIIIIIllIIllI > llllllllllllllllllIlIIIIIllIIlIl) {
            llllllllllllllllllIlIIIIIllIIlIl = llllllllllllllllllIlIIIIIllIIllI;
        }
        return llllllllllllllllllIlIIIIIllIIlIl;
    }
    
    public static Number createNumber(final String llllllllllllllllllIlIIIlIllllIII) throws NumberFormatException {
        if (llllllllllllllllllIlIIIlIllllIII == null) {
            return null;
        }
        if (StringUtils.isBlank((CharSequence)llllllllllllllllllIlIIIlIllllIII)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        final String[] llllllllllllllllllIlIIIlIlllIlll = { "0x", "0X", "-0x", "-0X", "#", "-#" };
        int llllllllllllllllllIlIIIlIlllIllI = 0;
        for (final String llllllllllllllllllIlIIIllIIIlIlI : llllllllllllllllllIlIIIlIlllIlll) {
            if (llllllllllllllllllIlIIIlIllllIII.startsWith(llllllllllllllllllIlIIIllIIIlIlI)) {
                llllllllllllllllllIlIIIlIlllIllI += llllllllllllllllllIlIIIllIIIlIlI.length();
                break;
            }
        }
        if (llllllllllllllllllIlIIIlIlllIllI > 0) {
            char llllllllllllllllllIlIIIllIIIlIII = '\0';
            for (int llllllllllllllllllIlIIIllIIIlIIl = llllllllllllllllllIlIIIlIlllIllI; llllllllllllllllllIlIIIllIIIlIIl < llllllllllllllllllIlIIIlIllllIII.length(); ++llllllllllllllllllIlIIIllIIIlIIl) {
                llllllllllllllllllIlIIIllIIIlIII = llllllllllllllllllIlIIIlIllllIII.charAt(llllllllllllllllllIlIIIllIIIlIIl);
                if (llllllllllllllllllIlIIIllIIIlIII != '0') {
                    break;
                }
                ++llllllllllllllllllIlIIIlIlllIllI;
            }
            final int llllllllllllllllllIlIIIllIIIIlll = llllllllllllllllllIlIIIlIllllIII.length() - llllllllllllllllllIlIIIlIlllIllI;
            if (llllllllllllllllllIlIIIllIIIIlll > 16 || (llllllllllllllllllIlIIIllIIIIlll == 16 && llllllllllllllllllIlIIIllIIIlIII > '7')) {
                return createBigInteger(llllllllllllllllllIlIIIlIllllIII);
            }
            if (llllllllllllllllllIlIIIllIIIIlll > 8 || (llllllllllllllllllIlIIIllIIIIlll == 8 && llllllllllllllllllIlIIIllIIIlIII > '7')) {
                return createLong(llllllllllllllllllIlIIIlIllllIII);
            }
            return createInteger(llllllllllllllllllIlIIIlIllllIII);
        }
        else {
            final char llllllllllllllllllIlIIIlIlllIlIl = llllllllllllllllllIlIIIlIllllIII.charAt(llllllllllllllllllIlIIIlIllllIII.length() - 1);
            final int llllllllllllllllllIlIIIlIlllIIIl = llllllllllllllllllIlIIIlIllllIII.indexOf(46);
            final int llllllllllllllllllIlIIIlIlllIIII = llllllllllllllllllIlIIIlIllllIII.indexOf(101) + llllllllllllllllllIlIIIlIllllIII.indexOf(69) + 1;
            String llllllllllllllllllIlIIIlIlllIlII = null;
            String llllllllllllllllllIlIIIlIlllIIll = null;
            if (llllllllllllllllllIlIIIlIlllIIIl > -1) {
                if (llllllllllllllllllIlIIIlIlllIIII > -1) {
                    if (llllllllllllllllllIlIIIlIlllIIII < llllllllllllllllllIlIIIlIlllIIIl || llllllllllllllllllIlIIIlIlllIIII > llllllllllllllllllIlIIIlIllllIII.length()) {
                        throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
                    }
                    final String llllllllllllllllllIlIIIllIIIIllI = llllllllllllllllllIlIIIlIllllIII.substring(llllllllllllllllllIlIIIlIlllIIIl + 1, llllllllllllllllllIlIIIlIlllIIII);
                }
                else {
                    final String llllllllllllllllllIlIIIllIIIIlII = llllllllllllllllllIlIIIlIllllIII.substring(llllllllllllllllllIlIIIlIlllIIIl + 1);
                }
                final String llllllllllllllllllIlIIIllIIIIlIl = getMantissa(llllllllllllllllllIlIIIlIllllIII, llllllllllllllllllIlIIIlIlllIIIl);
            }
            else {
                if (llllllllllllllllllIlIIIlIlllIIII > -1) {
                    if (llllllllllllllllllIlIIIlIlllIIII > llllllllllllllllllIlIIIlIllllIII.length()) {
                        throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
                    }
                    final String llllllllllllllllllIlIIIllIIIIIll = getMantissa(llllllllllllllllllIlIIIlIllllIII, llllllllllllllllllIlIIIlIlllIIII);
                }
                else {
                    llllllllllllllllllIlIIIlIlllIlII = getMantissa(llllllllllllllllllIlIIIlIllllIII);
                }
                llllllllllllllllllIlIIIlIlllIIll = null;
            }
            if (!Character.isDigit(llllllllllllllllllIlIIIlIlllIlIl) && llllllllllllllllllIlIIIlIlllIlIl != '.') {
                String llllllllllllllllllIlIIIlIlllllIl = null;
                if (llllllllllllllllllIlIIIlIlllIIII > -1 && llllllllllllllllllIlIIIlIlllIIII < llllllllllllllllllIlIIIlIllllIII.length() - 1) {
                    final String llllllllllllllllllIlIIIllIIIIIlI = llllllllllllllllllIlIIIlIllllIII.substring(llllllllllllllllllIlIIIlIlllIIII + 1, llllllllllllllllllIlIIIlIllllIII.length() - 1);
                }
                else {
                    llllllllllllllllllIlIIIlIlllllIl = null;
                }
                final String llllllllllllllllllIlIIIlIlllllll = llllllllllllllllllIlIIIlIllllIII.substring(0, llllllllllllllllllIlIIIlIllllIII.length() - 1);
                final boolean llllllllllllllllllIlIIIlIllllllI = isAllZeros(llllllllllllllllllIlIIIlIlllIlII) && isAllZeros(llllllllllllllllllIlIIIlIlllllIl);
                switch (llllllllllllllllllIlIIIlIlllIlIl) {
                    case 'S': {
                        if (llllllllllllllllllIlIIIlIlllIIll == null && llllllllllllllllllIlIIIlIlllllIl == null) {
                            if (llllllllllllllllllIlIIIlIlllllll.charAt(0) != '-' || !isDigits(llllllllllllllllllIlIIIlIlllllll.substring(1))) {
                                if (!isDigits(llllllllllllllllllIlIIIlIlllllll)) {
                                    throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
                                }
                            }
                            try {
                                return createShort(llllllllllllllllllIlIIIlIlllllll);
                            }
                            catch (NumberFormatException ex) {
                                return createBigInteger(llllllllllllllllllIlIIIlIlllllll);
                            }
                        }
                        throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
                    }
                    case 'L':
                    case 'l': {
                        if (llllllllllllllllllIlIIIlIlllIIll == null && llllllllllllllllllIlIIIlIlllllIl == null) {
                            if (llllllllllllllllllIlIIIlIlllllll.charAt(0) != '-' || !isDigits(llllllllllllllllllIlIIIlIlllllll.substring(1))) {
                                if (!isDigits(llllllllllllllllllIlIIIlIlllllll)) {
                                    throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
                                }
                            }
                            try {
                                return createLong(llllllllllllllllllIlIIIlIlllllll);
                            }
                            catch (NumberFormatException ex2) {
                                return createBigInteger(llllllllllllllllllIlIIIlIlllllll);
                            }
                        }
                        throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
                    }
                    case 'F':
                    case 'f': {
                        try {
                            final Float llllllllllllllllllIlIIIllIIIIIIl = createFloat(llllllllllllllllllIlIIIlIllllIII);
                            if (!llllllllllllllllllIlIIIllIIIIIIl.isInfinite() && (llllllllllllllllllIlIIIllIIIIIIl != 0.0f || llllllllllllllllllIlIIIlIllllllI)) {
                                return llllllllllllllllllIlIIIllIIIIIIl;
                            }
                        }
                        catch (NumberFormatException ex3) {}
                    }
                    case 'D':
                    case 'd': {
                        try {
                            final Double llllllllllllllllllIlIIIllIIIIIII = createDouble(llllllllllllllllllIlIIIlIllllIII);
                            if (!llllllllllllllllllIlIIIllIIIIIII.isInfinite() && (llllllllllllllllllIlIIIllIIIIIII.floatValue() != 0.0 || llllllllllllllllllIlIIIlIllllllI)) {
                                return llllllllllllllllllIlIIIllIIIIIII;
                            }
                        }
                        catch (NumberFormatException ex4) {}
                        try {
                            return createBigDecimal(llllllllllllllllllIlIIIlIlllllll);
                        }
                        catch (NumberFormatException ex5) {}
                        break;
                    }
                }
                throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIllllIII).append(" is not a valid number.")));
            }
            String llllllllllllllllllIlIIIlIlllIIlI = null;
            if (llllllllllllllllllIlIIIlIlllIIII > -1 && llllllllllllllllllIlIIIlIlllIIII < llllllllllllllllllIlIIIlIllllIII.length() - 1) {
                final String llllllllllllllllllIlIIIlIlllllII = llllllllllllllllllIlIIIlIllllIII.substring(llllllllllllllllllIlIIIlIlllIIII + 1, llllllllllllllllllIlIIIlIllllIII.length());
            }
            else {
                llllllllllllllllllIlIIIlIlllIIlI = null;
            }
            if (llllllllllllllllllIlIIIlIlllIIll == null && llllllllllllllllllIlIIIlIlllIIlI == null) {
                try {
                    return createInteger(llllllllllllllllllIlIIIlIllllIII);
                }
                catch (NumberFormatException ex6) {
                    try {
                        return createLong(llllllllllllllllllIlIIIlIllllIII);
                    }
                    catch (NumberFormatException ex7) {
                        return createBigInteger(llllllllllllllllllIlIIIlIllllIII);
                    }
                }
            }
            final boolean llllllllllllllllllIlIIIlIllIllll = isAllZeros(llllllllllllllllllIlIIIlIlllIlII) && isAllZeros(llllllllllllllllllIlIIIlIlllIIlI);
            try {
                final Float llllllllllllllllllIlIIIlIllllIlI = createFloat(llllllllllllllllllIlIIIlIllllIII);
                final Double llllllllllllllllllIlIIIlIllllIIl = createDouble(llllllllllllllllllIlIIIlIllllIII);
                if (!llllllllllllllllllIlIIIlIllllIlI.isInfinite() && (llllllllllllllllllIlIIIlIllllIlI != 0.0f || llllllllllllllllllIlIIIlIllIllll) && llllllllllllllllllIlIIIlIllllIlI.toString().equals(llllllllllllllllllIlIIIlIllllIIl.toString())) {
                    return llllllllllllllllllIlIIIlIllllIlI;
                }
                if (!llllllllllllllllllIlIIIlIllllIIl.isInfinite() && (llllllllllllllllllIlIIIlIllllIIl != 0.0 || llllllllllllllllllIlIIIlIllIllll)) {
                    final BigDecimal llllllllllllllllllIlIIIlIllllIll = createBigDecimal(llllllllllllllllllIlIIIlIllllIII);
                    if (llllllllllllllllllIlIIIlIllllIll.compareTo(BigDecimal.valueOf(llllllllllllllllllIlIIIlIllllIIl)) == 0) {
                        return llllllllllllllllllIlIIIlIllllIIl;
                    }
                    return llllllllllllllllllIlIIIlIllllIll;
                }
            }
            catch (NumberFormatException ex8) {}
            return createBigDecimal(llllllllllllllllllIlIIIlIllllIII);
        }
    }
    
    public static int max(int llllllllllllllllllIlIIIIIllllIlI, final int llllllllllllllllllIlIIIIIllllIIl, final int llllllllllllllllllIlIIIIIllllIII) {
        if (llllllllllllllllllIlIIIIIllllIIl > llllllllllllllllllIlIIIIIllllIlI) {
            llllllllllllllllllIlIIIIIllllIlI = llllllllllllllllllIlIIIIIllllIIl;
        }
        if (llllllllllllllllllIlIIIIIllllIII > llllllllllllllllllIlIIIIIllllIlI) {
            llllllllllllllllllIlIIIIIllllIlI = llllllllllllllllllIlIIIIIllllIII;
        }
        return llllllllllllllllllIlIIIIIllllIlI;
    }
    
    public static float toFloat(final String llllllllllllllllllIlIIIlllIIIIII, final float llllllllllllllllllIlIIIllIllllll) {
        if (llllllllllllllllllIlIIIlllIIIIII == null) {
            return llllllllllllllllllIlIIIllIllllll;
        }
        try {
            return Float.parseFloat(llllllllllllllllllIlIIIlllIIIIII);
        }
        catch (NumberFormatException llllllllllllllllllIlIIIlllIIIIIl) {
            return llllllllllllllllllIlIIIllIllllll;
        }
    }
    
    public static double toDouble(final String llllllllllllllllllIlIIIllIlllIlI) {
        return toDouble(llllllllllllllllllIlIIIllIlllIlI, 0.0);
    }
    
    public static float min(final float... llllllllllllllllllIlIIIIlllllIII) {
        validateArray(llllllllllllllllllIlIIIIlllllIII);
        float llllllllllllllllllIlIIIIlllllIIl = llllllllllllllllllIlIIIIlllllIII[0];
        for (int llllllllllllllllllIlIIIIlllllIll = 1; llllllllllllllllllIlIIIIlllllIll < llllllllllllllllllIlIIIIlllllIII.length; ++llllllllllllllllllIlIIIIlllllIll) {
            if (Float.isNaN(llllllllllllllllllIlIIIIlllllIII[llllllllllllllllllIlIIIIlllllIll])) {
                return Float.NaN;
            }
            if (llllllllllllllllllIlIIIIlllllIII[llllllllllllllllllIlIIIIlllllIll] < llllllllllllllllllIlIIIIlllllIIl) {
                llllllllllllllllllIlIIIIlllllIIl = llllllllllllllllllIlIIIIlllllIII[llllllllllllllllllIlIIIIlllllIll];
            }
        }
        return llllllllllllllllllIlIIIIlllllIIl;
    }
    
    public static long toLong(final String llllllllllllllllllIlIIIlllIlIIIl) {
        return toLong(llllllllllllllllllIlIIIlllIlIIIl, 0L);
    }
    
    public static byte toByte(final String llllllllllllllllllIlIIIllIlIllIl) {
        return toByte(llllllllllllllllllIlIIIllIlIllIl, (byte)0);
    }
    
    public static int toInt(final String llllllllllllllllllIlIIIlllIllllI) {
        return toInt(llllllllllllllllllIlIIIlllIllllI, 0);
    }
    
    public static long toLong(final String llllllllllllllllllIlIIIlllIIllII, final long llllllllllllllllllIlIIIlllIIlIll) {
        if (llllllllllllllllllIlIIIlllIIllII == null) {
            return llllllllllllllllllIlIIIlllIIlIll;
        }
        try {
            return Long.parseLong(llllllllllllllllllIlIIIlllIIllII);
        }
        catch (NumberFormatException llllllllllllllllllIlIIIlllIIllIl) {
            return llllllllllllllllllIlIIIlllIIlIll;
        }
    }
    
    public static BigDecimal createBigDecimal(final String llllllllllllllllllIlIIIlIIlIllIl) {
        if (llllllllllllllllllIlIIIlIIlIllIl == null) {
            return null;
        }
        if (StringUtils.isBlank((CharSequence)llllllllllllllllllIlIIIlIIlIllIl)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (llllllllllllllllllIlIIIlIIlIllIl.trim().startsWith("--")) {
            throw new NumberFormatException(String.valueOf(new StringBuilder().append(llllllllllllllllllIlIIIlIIlIllIl).append(" is not a valid number.")));
        }
        return new BigDecimal(llllllllllllllllllIlIIIlIIlIllIl);
    }
    
    public static double max(final double... llllllllllllllllllIlIIIIllIIllIl) {
        validateArray(llllllllllllllllllIlIIIIllIIllIl);
        double llllllllllllllllllIlIIIIllIIllII = llllllllllllllllllIlIIIIllIIllIl[0];
        for (int llllllllllllllllllIlIIIIllIIlllI = 1; llllllllllllllllllIlIIIIllIIlllI < llllllllllllllllllIlIIIIllIIllIl.length; ++llllllllllllllllllIlIIIIllIIlllI) {
            if (Double.isNaN(llllllllllllllllllIlIIIIllIIllIl[llllllllllllllllllIlIIIIllIIlllI])) {
                return Double.NaN;
            }
            if (llllllllllllllllllIlIIIIllIIllIl[llllllllllllllllllIlIIIIllIIlllI] > llllllllllllllllllIlIIIIllIIllII) {
                llllllllllllllllllIlIIIIllIIllII = llllllllllllllllllIlIIIIllIIllIl[llllllllllllllllllIlIIIIllIIlllI];
            }
        }
        return llllllllllllllllllIlIIIIllIIllII;
    }
    
    public static double toDouble(final String llllllllllllllllllIlIIIllIllIlII, final double llllllllllllllllllIlIIIllIllIIIl) {
        if (llllllllllllllllllIlIIIllIllIlII == null) {
            return llllllllllllllllllIlIIIllIllIIIl;
        }
        try {
            return Double.parseDouble(llllllllllllllllllIlIIIllIllIlII);
        }
        catch (NumberFormatException llllllllllllllllllIlIIIllIllIlIl) {
            return llllllllllllllllllIlIIIllIllIIIl;
        }
    }
    
    public static float toFloat(final String llllllllllllllllllIlIIIlllIIIlIl) {
        return toFloat(llllllllllllllllllIlIIIlllIIIlIl, 0.0f);
    }
    
    public static int toInt(final String llllllllllllllllllIlIIIlllIlIllI, final int llllllllllllllllllIlIIIlllIlIlll) {
        if (llllllllllllllllllIlIIIlllIlIllI == null) {
            return llllllllllllllllllIlIIIlllIlIlll;
        }
        try {
            return Integer.parseInt(llllllllllllllllllIlIIIlllIlIllI);
        }
        catch (NumberFormatException llllllllllllllllllIlIIIlllIllIIl) {
            return llllllllllllllllllIlIIIlllIlIlll;
        }
    }
    
    static {
        LONG_ZERO = 0L;
        LONG_ONE = 1L;
        LONG_MINUS_ONE = -1L;
        INTEGER_ZERO = 0;
        INTEGER_ONE = 1;
        INTEGER_MINUS_ONE = -1;
        SHORT_ZERO = 0;
        SHORT_ONE = 1;
        SHORT_MINUS_ONE = -1;
        BYTE_ZERO = 0;
        BYTE_ONE = 1;
        BYTE_MINUS_ONE = -1;
        DOUBLE_ZERO = 0.0;
        DOUBLE_ONE = 1.0;
        DOUBLE_MINUS_ONE = -1.0;
        FLOAT_ZERO = 0.0f;
        FLOAT_ONE = 1.0f;
        FLOAT_MINUS_ONE = -1.0f;
    }
    
    public static int min(int llllllllllllllllllIlIIIIlIlIllIl, final int llllllllllllllllllIlIIIIlIlIllll, final int llllllllllllllllllIlIIIIlIlIlIll) {
        if (llllllllllllllllllIlIIIIlIlIllll < llllllllllllllllllIlIIIIlIlIllIl) {
            llllllllllllllllllIlIIIIlIlIllIl = llllllllllllllllllIlIIIIlIlIllll;
        }
        if (llllllllllllllllllIlIIIIlIlIlIll < llllllllllllllllllIlIIIIlIlIllIl) {
            llllllllllllllllllIlIIIIlIlIllIl = llllllllllllllllllIlIIIIlIlIlIll;
        }
        return llllllllllllllllllIlIIIIlIlIllIl;
    }
    
    public static int compare(final byte llllllllllllllllllIIllllllIIIlll, final byte llllllllllllllllllIIllllllIIIIlI) {
        return llllllllllllllllllIIllllllIIIlll - llllllllllllllllllIIllllllIIIIlI;
    }
    
    public static short min(final short... llllllllllllllllllIlIIIlIIIlIIll) {
        validateArray(llllllllllllllllllIlIIIlIIIlIIll);
        short llllllllllllllllllIlIIIlIIIlIlII = llllllllllllllllllIlIIIlIIIlIIll[0];
        for (int llllllllllllllllllIlIIIlIIIlIllI = 1; llllllllllllllllllIlIIIlIIIlIllI < llllllllllllllllllIlIIIlIIIlIIll.length; ++llllllllllllllllllIlIIIlIIIlIllI) {
            if (llllllllllllllllllIlIIIlIIIlIIll[llllllllllllllllllIlIIIlIIIlIllI] < llllllllllllllllllIlIIIlIIIlIlII) {
                llllllllllllllllllIlIIIlIIIlIlII = llllllllllllllllllIlIIIlIIIlIIll[llllllllllllllllllIlIIIlIIIlIllI];
            }
        }
        return llllllllllllllllllIlIIIlIIIlIlII;
    }
    
    public static double min(final double llllllllllllllllllIlIIIIlIIlIIlI, final double llllllllllllllllllIlIIIIlIIlIIIl, final double llllllllllllllllllIlIIIIlIIlIIll) {
        return Math.min(Math.min(llllllllllllllllllIlIIIIlIIlIIlI, llllllllllllllllllIlIIIIlIIlIIIl), llllllllllllllllllIlIIIIlIIlIIll);
    }
    
    public static long max(final long... llllllllllllllllllIlIIIIlllIllll) {
        validateArray(llllllllllllllllllIlIIIIlllIllll);
        long llllllllllllllllllIlIIIIllllIIII = llllllllllllllllllIlIIIIlllIllll[0];
        for (int llllllllllllllllllIlIIIIllllIIlI = 1; llllllllllllllllllIlIIIIllllIIlI < llllllllllllllllllIlIIIIlllIllll.length; ++llllllllllllllllllIlIIIIllllIIlI) {
            if (llllllllllllllllllIlIIIIlllIllll[llllllllllllllllllIlIIIIllllIIlI] > llllllllllllllllllIlIIIIllllIIII) {
                llllllllllllllllllIlIIIIllllIIII = llllllllllllllllllIlIIIIlllIllll[llllllllllllllllllIlIIIIllllIIlI];
            }
        }
        return llllllllllllllllllIlIIIIllllIIII;
    }
    
    private static void validateArray(final Object llllllllllllllllllIlIIIIlIllllIl) {
        if (llllllllllllllllllIlIIIIlIllllIl == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        Validate.isTrue(Array.getLength(llllllllllllllllllIlIIIIlIllllIl) != 0, "Array cannot be empty.", new Object[0]);
    }
    
    public static byte min(byte llllllllllllllllllIlIIIIlIIllIll, final byte llllllllllllllllllIlIIIIlIIlllIl, final byte llllllllllllllllllIlIIIIlIIlllII) {
        if (llllllllllllllllllIlIIIIlIIlllIl < llllllllllllllllllIlIIIIlIIllIll) {
            llllllllllllllllllIlIIIIlIIllIll = llllllllllllllllllIlIIIIlIIlllIl;
        }
        if (llllllllllllllllllIlIIIIlIIlllII < llllllllllllllllllIlIIIIlIIllIll) {
            llllllllllllllllllIlIIIIlIIllIll = llllllllllllllllllIlIIIIlIIlllII;
        }
        return llllllllllllllllllIlIIIIlIIllIll;
    }
    
    public static Integer createInteger(final String llllllllllllllllllIlIIIlIlIIIIIl) {
        if (llllllllllllllllllIlIIIlIlIIIIIl == null) {
            return null;
        }
        return Integer.decode(llllllllllllllllllIlIIIlIlIIIIIl);
    }
    
    public static byte toByte(final String llllllllllllllllllIlIIIllIlIlIII, final byte llllllllllllllllllIlIIIllIlIIlIl) {
        if (llllllllllllllllllIlIIIllIlIlIII == null) {
            return llllllllllllllllllIlIIIllIlIIlIl;
        }
        try {
            return Byte.parseByte(llllllllllllllllllIlIIIllIlIlIII);
        }
        catch (NumberFormatException llllllllllllllllllIlIIIllIlIlIIl) {
            return llllllllllllllllllIlIIIllIlIIlIl;
        }
    }
    
    public static boolean isParsable(final String llllllllllllllllllIlIIIIIIIlIlIl) {
        if (StringUtils.isEmpty((CharSequence)llllllllllllllllllIlIIIIIIIlIlIl)) {
            return false;
        }
        if (llllllllllllllllllIlIIIIIIIlIlIl.charAt(llllllllllllllllllIlIIIIIIIlIlIl.length() - 1) == '.') {
            return false;
        }
        if (llllllllllllllllllIlIIIIIIIlIlIl.charAt(0) == '-') {
            return llllllllllllllllllIlIIIIIIIlIlIl.length() != 1 && withDecimalsParsing(llllllllllllllllllIlIIIIIIIlIlIl, 1);
        }
        return withDecimalsParsing(llllllllllllllllllIlIIIIIIIlIlIl, 0);
    }
    
    public static Float createFloat(final String llllllllllllllllllIlIIIlIlIIlIlI) {
        if (llllllllllllllllllIlIIIlIlIIlIlI == null) {
            return null;
        }
        return Float.valueOf(llllllllllllllllllIlIIIlIlIIlIlI);
    }
    
    public static short toShort(final String llllllllllllllllllIlIIIllIIlllII, final short llllllllllllllllllIlIIIllIIllIll) {
        if (llllllllllllllllllIlIIIllIIlllII == null) {
            return llllllllllllllllllIlIIIllIIllIll;
        }
        try {
            return Short.parseShort(llllllllllllllllllIlIIIllIIlllII);
        }
        catch (NumberFormatException llllllllllllllllllIlIIIllIIlllIl) {
            return llllllllllllllllllIlIIIllIIllIll;
        }
    }
    
    public static short min(short llllllllllllllllllIlIIIIlIlIIlII, final short llllllllllllllllllIlIIIIlIlIIIll, final short llllllllllllllllllIlIIIIlIlIIIlI) {
        if (llllllllllllllllllIlIIIIlIlIIIll < llllllllllllllllllIlIIIIlIlIIlII) {
            llllllllllllllllllIlIIIIlIlIIlII = llllllllllllllllllIlIIIIlIlIIIll;
        }
        if (llllllllllllllllllIlIIIIlIlIIIlI < llllllllllllllllllIlIIIIlIlIIlII) {
            llllllllllllllllllIlIIIIlIlIIlII = llllllllllllllllllIlIIIIlIlIIIlI;
        }
        return (short)llllllllllllllllllIlIIIIlIlIIlII;
    }
    
    public static boolean isDigits(final String llllllllllllllllllIlIIIIIlIIlllI) {
        return StringUtils.isNumeric((CharSequence)llllllllllllllllllIlIIIIIlIIlllI);
    }
    
    public static long min(long llllllllllllllllllIlIIIIlIlllIIl, final long llllllllllllllllllIlIIIIlIllIlIl, final long llllllllllllllllllIlIIIIlIllIlII) {
        if (llllllllllllllllllIlIIIIlIllIlIl < llllllllllllllllllIlIIIIlIlllIIl) {
            llllllllllllllllllIlIIIIlIlllIIl = llllllllllllllllllIlIIIIlIllIlIl;
        }
        if (llllllllllllllllllIlIIIIlIllIlII < llllllllllllllllllIlIIIIlIlllIIl) {
            llllllllllllllllllIlIIIIlIlllIIl = llllllllllllllllllIlIIIIlIllIlII;
        }
        return llllllllllllllllllIlIIIIlIlllIIl;
    }
    
    public static short max(final short... llllllllllllllllllIlIIIIllIlllll) {
        validateArray(llllllllllllllllllIlIIIIllIlllll);
        short llllllllllllllllllIlIIIIllIllllI = llllllllllllllllllIlIIIIllIlllll[0];
        for (int llllllllllllllllllIlIIIIlllIIIII = 1; llllllllllllllllllIlIIIIlllIIIII < llllllllllllllllllIlIIIIllIlllll.length; ++llllllllllllllllllIlIIIIlllIIIII) {
            if (llllllllllllllllllIlIIIIllIlllll[llllllllllllllllllIlIIIIlllIIIII] > llllllllllllllllllIlIIIIllIllllI) {
                llllllllllllllllllIlIIIIllIllllI = llllllllllllllllllIlIIIIllIlllll[llllllllllllllllllIlIIIIlllIIIII];
            }
        }
        return llllllllllllllllllIlIIIIllIllllI;
    }
    
    private static boolean isAllZeros(final String llllllllllllllllllIlIIIlIlIIlllI) {
        if (llllllllllllllllllIlIIIlIlIIlllI == null) {
            return true;
        }
        for (int llllllllllllllllllIlIIIlIlIlIIII = llllllllllllllllllIlIIIlIlIIlllI.length() - 1; llllllllllllllllllIlIIIlIlIlIIII >= 0; --llllllllllllllllllIlIIIlIlIlIIII) {
            if (llllllllllllllllllIlIIIlIlIIlllI.charAt(llllllllllllllllllIlIIIlIlIlIIII) != '0') {
                return false;
            }
        }
        return llllllllllllllllllIlIIIlIlIIlllI.length() > 0;
    }
    
    public static long min(final long... llllllllllllllllllIlIIIlIIlIIlIl) {
        validateArray(llllllllllllllllllIlIIIlIIlIIlIl);
        long llllllllllllllllllIlIIIlIIlIIllI = llllllllllllllllllIlIIIlIIlIIlIl[0];
        for (int llllllllllllllllllIlIIIlIIlIlIII = 1; llllllllllllllllllIlIIIlIIlIlIII < llllllllllllllllllIlIIIlIIlIIlIl.length; ++llllllllllllllllllIlIIIlIIlIlIII) {
            if (llllllllllllllllllIlIIIlIIlIIlIl[llllllllllllllllllIlIIIlIIlIlIII] < llllllllllllllllllIlIIIlIIlIIllI) {
                llllllllllllllllllIlIIIlIIlIIllI = llllllllllllllllllIlIIIlIIlIIlIl[llllllllllllllllllIlIIIlIIlIlIII];
            }
        }
        return llllllllllllllllllIlIIIlIIlIIllI;
    }
    
    public static byte min(final byte... llllllllllllllllllIlIIIlIIIIlIlI) {
        validateArray(llllllllllllllllllIlIIIlIIIIlIlI);
        byte llllllllllllllllllIlIIIlIIIIlIll = llllllllllllllllllIlIIIlIIIIlIlI[0];
        for (int llllllllllllllllllIlIIIlIIIIllIl = 1; llllllllllllllllllIlIIIlIIIIllIl < llllllllllllllllllIlIIIlIIIIlIlI.length; ++llllllllllllllllllIlIIIlIIIIllIl) {
            if (llllllllllllllllllIlIIIlIIIIlIlI[llllllllllllllllllIlIIIlIIIIllIl] < llllllllllllllllllIlIIIlIIIIlIll) {
                llllllllllllllllllIlIIIlIIIIlIll = llllllllllllllllllIlIIIlIIIIlIlI[llllllllllllllllllIlIIIlIIIIllIl];
            }
        }
        return llllllllllllllllllIlIIIlIIIIlIll;
    }
    
    public static int compare(final short llllllllllllllllllIIllllllIlIIII, final short llllllllllllllllllIIllllllIIllll) {
        if (llllllllllllllllllIIllllllIlIIII == llllllllllllllllllIIllllllIIllll) {
            return 0;
        }
        return (llllllllllllllllllIIllllllIlIIII < llllllllllllllllllIIllllllIIllll) ? -1 : 1;
    }
    
    public static boolean isCreatable(final String llllllllllllllllllIlIIIIIIllIIII) {
        if (StringUtils.isEmpty((CharSequence)llllllllllllllllllIlIIIIIIllIIII)) {
            return false;
        }
        final char[] llllllllllllllllllIlIIIIIIlllIll = llllllllllllllllllIlIIIIIIllIIII.toCharArray();
        int llllllllllllllllllIlIIIIIIlllIlI = llllllllllllllllllIlIIIIIIlllIll.length;
        boolean llllllllllllllllllIlIIIIIIlllIIl = false;
        boolean llllllllllllllllllIlIIIIIIlllIII = false;
        boolean llllllllllllllllllIlIIIIIIllIlll = false;
        boolean llllllllllllllllllIlIIIIIIllIlIl = false;
        final int llllllllllllllllllIlIIIIIIllIIll = (llllllllllllllllllIlIIIIIIlllIll[0] == '-' || llllllllllllllllllIlIIIIIIlllIll[0] == '+') ? 1 : 0;
        final boolean llllllllllllllllllIlIIIIIIllIIlI = llllllllllllllllllIlIIIIIIllIIll == 1 && llllllllllllllllllIlIIIIIIlllIll[0] == '+';
        if (llllllllllllllllllIlIIIIIIlllIlI > llllllllllllllllllIlIIIIIIllIIll + 1 && llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIll] == '0') {
            if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIll + 1] == 'x' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIll + 1] == 'X') {
                int llllllllllllllllllIlIIIIIIllllll = llllllllllllllllllIlIIIIIIllIIll + 2;
                if (llllllllllllllllllIlIIIIIIllllll == llllllllllllllllllIlIIIIIIlllIlI) {
                    return false;
                }
                while (llllllllllllllllllIlIIIIIIllllll < llllllllllllllllllIlIIIIIIlllIll.length) {
                    if ((llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllll] < '0' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllll] > '9') && (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllll] < 'a' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllll] > 'f') && (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllll] < 'A' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllll] > 'F')) {
                        return false;
                    }
                    ++llllllllllllllllllIlIIIIIIllllll;
                }
                return true;
            }
            else if (Character.isDigit(llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIll + 1])) {
                for (int llllllllllllllllllIlIIIIIIllllIl = llllllllllllllllllIlIIIIIIllIIll + 1; llllllllllllllllllIlIIIIIIllllIl < llllllllllllllllllIlIIIIIIlllIll.length; ++llllllllllllllllllIlIIIIIIllllIl) {
                    if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllIl] < '0' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllllIl] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        --llllllllllllllllllIlIIIIIIlllIlI;
        int llllllllllllllllllIlIIIIIIllIIIl;
        for (llllllllllllllllllIlIIIIIIllIIIl = llllllllllllllllllIlIIIIIIllIIll; llllllllllllllllllIlIIIIIIllIIIl < llllllllllllllllllIlIIIIIIlllIlI || (llllllllllllllllllIlIIIIIIllIIIl < llllllllllllllllllIlIIIIIIlllIlI + 1 && llllllllllllllllllIlIIIIIIllIlll && !llllllllllllllllllIlIIIIIIllIlIl); ++llllllllllllllllllIlIIIIIIllIIIl) {
            if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] >= '0' && llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] <= '9') {
                llllllllllllllllllIlIIIIIIllIlIl = true;
                llllllllllllllllllIlIIIIIIllIlll = false;
            }
            else if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == '.') {
                if (llllllllllllllllllIlIIIIIIlllIII || llllllllllllllllllIlIIIIIIlllIIl) {
                    return false;
                }
                llllllllllllllllllIlIIIIIIlllIII = true;
            }
            else if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'e' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'E') {
                if (llllllllllllllllllIlIIIIIIlllIIl) {
                    return false;
                }
                if (!llllllllllllllllllIlIIIIIIllIlIl) {
                    return false;
                }
                llllllllllllllllllIlIIIIIIlllIIl = true;
                llllllllllllllllllIlIIIIIIllIlll = true;
            }
            else {
                if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] != '+' && llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] != '-') {
                    return false;
                }
                if (!llllllllllllllllllIlIIIIIIllIlll) {
                    return false;
                }
                llllllllllllllllllIlIIIIIIllIlll = false;
                llllllllllllllllllIlIIIIIIllIlIl = false;
            }
        }
        if (llllllllllllllllllIlIIIIIIllIIIl >= llllllllllllllllllIlIIIIIIlllIll.length) {
            return !llllllllllllllllllIlIIIIIIllIlll && llllllllllllllllllIlIIIIIIllIlIl;
        }
        if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] >= '0' && llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] <= '9') {
            return !SystemUtils.IS_JAVA_1_6 || !llllllllllllllllllIlIIIIIIllIIlI || llllllllllllllllllIlIIIIIIlllIII;
        }
        if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'e' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'E') {
            return false;
        }
        if (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == '.') {
            return !llllllllllllllllllIlIIIIIIlllIII && !llllllllllllllllllIlIIIIIIlllIIl && llllllllllllllllllIlIIIIIIllIlIl;
        }
        if (!llllllllllllllllllIlIIIIIIllIlll && (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'd' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'D' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'f' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'F')) {
            return llllllllllllllllllIlIIIIIIllIlIl;
        }
        return (llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'l' || llllllllllllllllllIlIIIIIIlllIll[llllllllllllllllllIlIIIIIIllIIIl] == 'L') && llllllllllllllllllIlIIIIIIllIlIl && !llllllllllllllllllIlIIIIIIlllIIl && !llllllllllllllllllIlIIIIIIlllIII;
    }
    
    public static int compare(final long llllllllllllllllllIIllllllIlIllI, final long llllllllllllllllllIIllllllIlIlll) {
        if (llllllllllllllllllIIllllllIlIllI == llllllllllllllllllIIllllllIlIlll) {
            return 0;
        }
        return (llllllllllllllllllIIllllllIlIllI < llllllllllllllllllIIllllllIlIlll) ? -1 : 1;
    }
    
    public static Long createLong(final String llllllllllllllllllIlIIIlIIllllll) {
        if (llllllllllllllllllIlIIIlIIllllll == null) {
            return null;
        }
        return Long.decode(llllllllllllllllllIlIIIlIIllllll);
    }
    
    public static int compare(final int llllllllllllllllllIIlllllllIllII, final int llllllllllllllllllIIlllllllIIllI) {
        if (llllllllllllllllllIIlllllllIllII == llllllllllllllllllIIlllllllIIllI) {
            return 0;
        }
        return (llllllllllllllllllIIlllllllIllII < llllllllllllllllllIIlllllllIIllI) ? -1 : 1;
    }
    
    public static int max(final int... llllllllllllllllllIlIIIIlllIIllI) {
        validateArray(llllllllllllllllllIlIIIIlllIIllI);
        int llllllllllllllllllIlIIIIlllIIlll = llllllllllllllllllIlIIIIlllIIllI[0];
        for (int llllllllllllllllllIlIIIIlllIlIIl = 1; llllllllllllllllllIlIIIIlllIlIIl < llllllllllllllllllIlIIIIlllIIllI.length; ++llllllllllllllllllIlIIIIlllIlIIl) {
            if (llllllllllllllllllIlIIIIlllIIllI[llllllllllllllllllIlIIIIlllIlIIl] > llllllllllllllllllIlIIIIlllIIlll) {
                llllllllllllllllllIlIIIIlllIIlll = llllllllllllllllllIlIIIIlllIIllI[llllllllllllllllllIlIIIIlllIlIIl];
            }
        }
        return llllllllllllllllllIlIIIIlllIIlll;
    }
    
    private static String getMantissa(final String llllllllllllllllllIlIIIlIlIlIllI, final int llllllllllllllllllIlIIIlIlIllIIl) {
        final char llllllllllllllllllIlIIIlIlIllIII = llllllllllllllllllIlIIIlIlIlIllI.charAt(0);
        final boolean llllllllllllllllllIlIIIlIlIlIlll = llllllllllllllllllIlIIIlIlIllIII == '-' || llllllllllllllllllIlIIIlIlIllIII == '+';
        return llllllllllllllllllIlIIIlIlIlIlll ? llllllllllllllllllIlIIIlIlIlIllI.substring(1, llllllllllllllllllIlIIIlIlIllIIl) : llllllllllllllllllIlIIIlIlIlIllI.substring(0, llllllllllllllllllIlIIIlIlIllIIl);
    }
    
    public static Double createDouble(final String llllllllllllllllllIlIIIlIlIIIlII) {
        if (llllllllllllllllllIlIIIlIlIIIlII == null) {
            return null;
        }
        return Double.valueOf(llllllllllllllllllIlIIIlIlIIIlII);
    }
    
    public static byte max(final byte... llllllllllllllllllIlIIIIllIlIllI) {
        validateArray(llllllllllllllllllIlIIIIllIlIllI);
        byte llllllllllllllllllIlIIIIllIlIlIl = llllllllllllllllllIlIIIIllIlIllI[0];
        for (int llllllllllllllllllIlIIIIllIlIlll = 1; llllllllllllllllllIlIIIIllIlIlll < llllllllllllllllllIlIIIIllIlIllI.length; ++llllllllllllllllllIlIIIIllIlIlll) {
            if (llllllllllllllllllIlIIIIllIlIllI[llllllllllllllllllIlIIIIllIlIlll] > llllllllllllllllllIlIIIIllIlIlIl) {
                llllllllllllllllllIlIIIIllIlIlIl = llllllllllllllllllIlIIIIllIlIllI[llllllllllllllllllIlIIIIllIlIlll];
            }
        }
        return llllllllllllllllllIlIIIIllIlIlIl;
    }
    
    public static double min(final double... llllllllllllllllllIlIIIlIIIIIIll) {
        validateArray(llllllllllllllllllIlIIIlIIIIIIll);
        double llllllllllllllllllIlIIIlIIIIIIlI = llllllllllllllllllIlIIIlIIIIIIll[0];
        for (int llllllllllllllllllIlIIIlIIIIIlII = 1; llllllllllllllllllIlIIIlIIIIIlII < llllllllllllllllllIlIIIlIIIIIIll.length; ++llllllllllllllllllIlIIIlIIIIIlII) {
            if (Double.isNaN(llllllllllllllllllIlIIIlIIIIIIll[llllllllllllllllllIlIIIlIIIIIlII])) {
                return Double.NaN;
            }
            if (llllllllllllllllllIlIIIlIIIIIIll[llllllllllllllllllIlIIIlIIIIIlII] < llllllllllllllllllIlIIIlIIIIIIlI) {
                llllllllllllllllllIlIIIlIIIIIIlI = llllllllllllllllllIlIIIlIIIIIIll[llllllllllllllllllIlIIIlIIIIIlII];
            }
        }
        return llllllllllllllllllIlIIIlIIIIIIlI;
    }
    
    private static String getMantissa(final String llllllllllllllllllIlIIIlIlIlllll) {
        return getMantissa(llllllllllllllllllIlIIIlIlIlllll, llllllllllllllllllIlIIIlIlIlllll.length());
    }
}
