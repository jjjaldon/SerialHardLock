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

import gnu.io.SerialPort;

/**
 * @author Sacrifice
 */
public class HardLockSettings
{
	public static final int TIME_OUT = 2000;
	public static final int BAUD_RATE = 19200;
	public static final int DATA_BITS = SerialPort.DATABITS_8;
	public static final int STOP_BITS = SerialPort.STOPBITS_1;
	public static final int PARITY_BITS = SerialPort.PARITY_NONE;
	public static final String PORT_NAME = "COM3";
	public static final String MESSAGE_TO_SEND = "Copyright (C) 2018 Sacrifice"; // Q29weXJpZ2h0IChDKSAyMDE4IFNhY3JpZmljZQ==
	protected static final String MESSAGE_TO_RECEIVE = "GNU General Public License"; // R05VIEdlbmVyYWwgUHVibGljIExpY2Vuc2U=
}
