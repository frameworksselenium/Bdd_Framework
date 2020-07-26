package com.open.hotel.utils.html;

public class HtmlManager {

	private HtmlManager(){}
	private static final HtmlManager _instance = new HtmlManager();
	private HtmlLog _log;

	public static HtmlLog init(){
		if(_instance._log==null){
			_instance._log = new HtmlLog();
		}
		return _instance._log;
	}

}
