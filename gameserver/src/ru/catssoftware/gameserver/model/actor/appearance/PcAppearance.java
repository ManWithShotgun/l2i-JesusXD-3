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
package ru.catssoftware.gameserver.model.actor.appearance;

import ru.catssoftware.gameserver.model.actor.instance.L2PcInstance;

public class PcAppearance
{
	private L2PcInstance	_owner;
	private byte			_face;
	private byte			_hairColor;
	private byte			_hairStyle;
	private boolean			_sex;					//Female true(1)
	private boolean			_displayName;
	/** true if  the player is invisible */
	private boolean			_invisible	= false;
	/** The current visisble name of this palyer, not necessarily the real one */
	private String			_visibleName;
	/** The current visisble title of this palyer, not necessarily the real one */
	private String			_visibleTitle;
	/** The hexadecimal Color of players name (white is 0xFFFFFF) */
	private int				_nameColor	= 0xFFFFFF;
	/** The hexadecimal Color of players title (light blue is 0xFFFF77) */
	private int				_titleColor	= 0xFFFF77;

	public PcAppearance(byte face, byte hColor, byte hStyle, boolean sex)
	{
		_face = face;
		_hairColor = hColor;
		_hairStyle = hStyle;
		_sex = sex;
	}

	/**
	 * @param visibleName The visibleName to set.
	 */
	public final void setVisibleName(String visibleName)
	{
		_visibleName = visibleName;
	}

	/**
	 * @return Returns the visibleName.
	 */
	public final String getVisibleName()
	{
		if (_visibleName == null)
			return this.getOwner().getName();
		else if (_displayName)
			return _visibleName;

		return "";
	}

	/**
	 * @param visibleTitle The visibleTitle to set.
	 */
	public final void setVisibleTitle(String visibleTitle)
	{
		_visibleTitle = visibleTitle;
	}

	/**
	 * @return Returns the visibleTitle.
	 */
	public final String getVisibleTitle()
	{
		if (_visibleTitle == null)
			return this.getOwner().getTitle();
		else if (_displayName)
			return _visibleTitle;

		return "";
	}

	public final byte getFace()
	{
		return _face;
	}

	/**
	 * @param byte value
	 */
	public final void setFace(int value)
	{
		_face = (byte) value;
	}

	public final byte getHairColor()
	{
		return _hairColor;
	}

	/**
	 * @param byte value
	 */
	public final void setHairColor(int value)
	{
		_hairColor = (byte) value;
	}

	public final byte getHairStyle()
	{
		return _hairStyle;
	}

	/**
	 * @param byte value
	 */
	public final void setHairStyle(int value)
	{
		_hairStyle = (byte) value;
	}

	public final boolean getSex()
	{
		return _sex;
	}

	/**
	 * @param boolean isfemale
	 */
	public final void setSex(boolean isfemale)
	{
		_sex = isfemale;
	}

	public void setInvisible()
	{
		_invisible = true;
	}

	public void setVisible()
	{
		_invisible = false;
	}

	public boolean isInvisible()
	{
		return _invisible;
	}

	public int getNameColor()
	{
		return _nameColor;
	}

	public void setNameTitleColor(int name, int title)
	{
		/* name */
		_nameColor = name;
		
		/* title */
		if (_owner._event!=null && _owner._event.isRunning())
			_eventTitleColor = title;
		else
			_titleColor = title;
		getOwner().broadcastFullInfo();
	}

	public void setNameColor(int nameColor)
	{
		_nameColor = nameColor;
		getOwner().broadcastFullInfo();
	}

	public void setNameColor(int red, int green, int blue)
	{
		setNameColor( (red & 0xFF) + ((green & 0xFF) << 8) + ((blue & 0xFF) << 16));
	}

	private int _eventTitleColor = 0;
	public int getTitleColor()
	{
		if(_owner._event!=null && _owner._event.isRunning()) {
			if(_eventTitleColor==0)
				_eventTitleColor = _titleColor;
			return _eventTitleColor;
		}
		return _titleColor;
	}

	public void setTitleColor(int titleColor)
	{
		if(_owner._event!=null && _owner._event.isRunning())
			_eventTitleColor = titleColor;
		else
			_titleColor = titleColor;
		getOwner().broadcastFullInfo();
	}

	public void setTitleColor(int red, int green, int blue)
	{
		_titleColor = (red & 0xFF) + ((green & 0xFF) << 8) + ((blue & 0xFF) << 16);
		getOwner().broadcastFullInfo();
	}

	/**
	 * @param owner The owner to set.
	 */
	public void setOwner(L2PcInstance owner)
	{
		_owner = owner;
	}

	/**
	 * @return Returns the owner.
	 */
	public L2PcInstance getOwner()
	{
		return _owner;
	}

	public boolean getDisplayName()
	{
		return _displayName;
	}

	public void setDisplayName(boolean b)
	{
		_displayName = b;
	}
}