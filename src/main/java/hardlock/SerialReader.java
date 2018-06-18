/* Copyright (C) 2018 Sacrifice
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package hardlock;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * Handles the input coming from the serial port. A new line character is treated as the end of a block in this example.
 * @author Sacrifice
 */
public final class SerialReader implements SerialPortEventListener
{
	private final InputStream inputStream;
	private final byte[] buffer = new byte[1024];
	
	public SerialReader(InputStream in)
	{
		this.inputStream = in;
	}
	
	@Override
	public void serialEvent(SerialPortEvent arg0)
	{
		int data;
		try
		{
			int len = 0;
			while ((data = inputStream.read()) > -1)
			{
				if (data == '\n')
				{
					break;
				}
				buffer[len++] = (byte) data;
			}
			System.out.print(new String(buffer, 0, len));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public InputStream get_inputStream()
	{
		return inputStream;
	}
}