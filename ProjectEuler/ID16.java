import java.util.*;
import java.io.*;
import java.math.*;

class Main
{
	public static void main(String[] args)
	{
		BigInteger value = BigInteger.valueOf(2).pow(1000);

		value = getDigitSum(value);

		System.out.println(value);
	}

	private static BigInteger getDigitSum(BigInteger value) {
		String[] digits = value.toString().split("");

		BigInteger ret = BigInteger.valueOf(0);
		for (int i = 0; i < digits.length; ++i) {
			if (digits[i] != null && !digits[i].equals(""))
				ret = ret.add(new BigInteger(digits[i]));
		}

		return ret;
	}
}
