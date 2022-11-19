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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.logging.Logger;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

/**
 * This version of the TwoWaySerialComm example makes use of the SerialPortEventListener to avoid polling.
 * @author Sacrifice
 */
public final class TwoWaySerialComm
{
	public static final Logger LOG = Logger.getLogger(TwoWaySerialComm.class.getName());
	
	public TwoWaySerialComm()
	{
		super();
	}
	
	public static void main(String[] args)
	{
		try
		{
			// Send Check Message
			new TwoWaySerialComm().connect(HardLockSettings.PORT_NAME);
			final OutputStream dataToSend = new OutputStream()
			{
				@Override
				public void write(int b) throws IOException
				{
					final byte[] data = HardLockSettings.MESSAGE_TO_SEND.getBytes("UTF-8");
					Base64.getEncoder().encodeToString(data);
				}
			};
			// Receive Check Message
			final InputStream dataToReceive = new InputStream()
			{
				@Override
				public int read() throws IOException
				{
					final byte[] data = HardLockSettings.MESSAGE_TO_RECEIVE.getBytes("UTF-8");
					Base64.getEncoder().encodeToString(data);
					return 0;
				}
			};
			new Thread(new SerialWriter(dataToSend)).start();
			Thread.sleep(500);
			new SerialReader(dataToReceive);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void connect(String portName) throws Exception
	{
		final CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (commPortIdentifier.isCurrentlyOwned())
		{
			LOG.warning("Error: Port is currently in use");
		}
		else
		{
			final CommPort commPort = commPortIdentifier.open(this.getClass().getName(), HardLockSettings.TIME_OUT);
			if (commPort instanceof SerialPort)
			{
				final SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(HardLockSettings.BAUD_RATE, HardLockSettings.DATA_BITS, HardLockSettings.STOP_BITS, HardLockSettings.PARITY_BITS);
				final InputStream inputStream = serialPort.getInputStream();
				final OutputStream outputStream = serialPort.getOutputStream();
				new Thread(new SerialWriter(outputStream)).start();
				serialPort.addEventListener(new SerialReader(inputStream));
				serialPort.notifyOnDataAvailable(true);
			}
			else
			{
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}
}