package ru.jesus.login.loginserver.mmocore;

/**
 * @version 2.0
 * @author JesusXD
 * */
abstract class HeaderHandler<T extends MMOConnection<T>, H extends HeaderHandler<T, H>>
{
	private final H _subHeaderHandler;
	private final HeaderInfo<T> _headerInfoReturn = new HeaderInfo<T>();

	HeaderHandler(H subHeaderHandler) {
		_subHeaderHandler = subHeaderHandler;
	}

	final H getSubHeaderHandler()
	{
		return _subHeaderHandler;
	}

	final boolean isChildHeaderHandler()
	{
		return getSubHeaderHandler() == null;
	}

	protected final HeaderInfo<T> getHeaderInfoReturn()
	{
		return _headerInfoReturn;
	}
}