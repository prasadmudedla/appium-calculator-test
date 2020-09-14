package com.util.misc.url;

import java.net.URL;

public class URLRetrieve
{

	public static URL retrieveFileURLForWorkingDirectoryFromRelativePathStringOfFile(
			String relativePathStringOfFile)
	{
		
		URL url=
				null;
		
		
		
		String absolutePathStringOfWorkingDirectory=
				null;
		
		try
		{
			
			absolutePathStringOfWorkingDirectory=
					System.getProperty(
							"user.dir");
			
			url=
					new URL(
							"file:///" +
							absolutePathStringOfWorkingDirectory +
							relativePathStringOfFile);
			
		}
		catch(
				Exception exception)
		{
			
			exception.printStackTrace();
			
		}
		
		
		
		return url;

	}
	
}
