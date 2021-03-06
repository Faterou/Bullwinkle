/*
  Copyright 2014-2016 Sylvain Hallé
  Laboratoire d'informatique formelle
  Université du Québec à Chicoutimi, Canada

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package ca.uqac.lif.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * File read/write operations.
 * @author sylvain
 *
 */
public class FileReadWrite
{
	/**
	 * Writes a string to a file. The method ends the program
	 * if some IOException is thrown.
	 * @param filename The filename to write to
	 * @param contents The file's contents
	 * @throws IOException If the writing fails
	 */
	public static void writeToFile(String filename, String contents)  throws IOException
	{
		OutputStreamWriter out = null;
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(filename);
			out = new OutputStreamWriter(fos, "UTF-8");
			out.write(contents);
		}
		catch (java.io.IOException e)
		{
			throw e;
		}
		finally
		{
			if (out != null)
				out.close();
			if (fos != null)
				fos.close();
		}
	}

	/**
	 * Reads a file and puts its contents in a string
	 * @param filename The name (and path) of the file to read
	 * @return The file's contents, and empty string if the file
	 * does not exist
	 * @throws IOException If the reading fails
	 */
	public static String readFile(String filename) throws IOException
	{
		File f = new File(filename);
		return readFile(f);
	}

	/**
	 * Reads a file and puts its contents in a string
	 * @param f The file to read
	 * @return The file's contents, and empty string if the file
	 * does not exist
	 * @throws IOException If the reading fails
	 */
	public static /* @NonNull */ String readFile(File f) throws IOException
	{
		Scanner scanner = null;
		StringBuilder out = new StringBuilder();
		try
		{
			scanner = new java.util.Scanner(f);
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				out.append(line).append(System.getProperty("line.separator"));
			}
		}
		catch (java.io.IOException e)
		{
			throw e;
		}
		finally
		{
			if (scanner != null)
				scanner.close();
		}
		return out.toString();
	}

	/**
	 * Return the base name of a file name. For example, the
	 * base name of "/home/sylvain/abcd.txt" is "abcd"
	 * @param s A string containing only the filename (not the
	 * previous path!)
	 * @return The filename stripped of its extension
	 */
	public static String baseName(String s)
	{
		return s.replaceFirst("[.][^.]+$", "");
	}

	/**
	 * Same as above, but with f a file instead of a string containing
	 * the filename
	 * @param f A file to get the base name
	 * @return The base name
	 */
	public static String baseName(File f)
	{
		return baseName(f.getName());
	}

}
