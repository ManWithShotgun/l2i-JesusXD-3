/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ru.catssoftware.gameserver.network.serverpackets;

import ru.catssoftware.gameserver.model.L2Character;

/**
 * @author -Wooden-
 */
public class ExFishingStartCombat extends L2GameServerPacket
{
	private static final String	_S__FE_27_EXFISHINGSTARTCOMBAT	= "[S] FE:27 ExFishingStartCombat [ddd ccc]";
	private L2Character			_activeChar;
	private int					_time, _hp;
	private int					_lureType, _deceptiveMode, _mode;

	public ExFishingStartCombat(L2Character character, int time, int hp, int mode, int lureType, int deceptiveMode)
	{
		_activeChar = character;
		_time = time;
		_hp = hp;
		_mode = mode;
		_lureType = lureType;
		_deceptiveMode = deceptiveMode;
	}

	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0x15);

		writeD(_activeChar.getObjectId());
		writeD(_time);
		writeD(_hp);
		writeC(_mode); // mode: 0 = resting, 1 = fighting
		writeC(_lureType); // 0 = newbie lure, 1 = normal lure, 2 = night lure
		writeC(_deceptiveMode); // Fish Deceptive Mode: 0 = no, 1 = yes
	}

	@Override
	public String getType()
	{
		return _S__FE_27_EXFISHINGSTARTCOMBAT;
	}
}