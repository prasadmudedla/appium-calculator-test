package com.util.misc.string;

import org.apache.commons.lang3.StringUtils;

public class StringContent
{
	
	public static boolean isString1EqualToString2IgnoreCase(
			String string1,
			String string2)
	{
		
		return StringUtils.equalsIgnoreCase(
				string2,
				string1);
		
	}
	
}
