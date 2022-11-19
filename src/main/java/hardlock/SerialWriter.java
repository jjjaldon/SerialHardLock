/*
 * Copyright © 2004-2022 SerialHardLock
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package hardlock;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Sacrifice
 */
public final class SerialWriter implements Runnable
{
	private final OutputStream _outputStream;
	
	public SerialWriter(OutputStream out)
	{
		_outputStream = out;
	}
	
	public OutputStream get_outputStream()
	{
		return _outputStream;
	}
	
	@Override
	public void run()
	{
		try
		{
			int c = 0;
			while ((c = System.in.read()) > -1)
			{
				_outputStream.write(c);
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
}