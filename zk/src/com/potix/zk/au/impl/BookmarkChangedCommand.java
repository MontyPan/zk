/* BookmarkChangedCommand.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Mon May 29 19:34:36     2006, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package com.potix.zk.au.impl;

import com.potix.lang.Objects;

import com.potix.zk.mesg.MZk;
import com.potix.zk.ui.sys.DesktopCtrl;
import com.potix.zk.ui.UiException;
import com.potix.zk.ui.event.Events;
import com.potix.zk.ui.event.BookmarkEvent;
import com.potix.zk.au.AuRequest;

/**
 * Used by {@link AuRequest} to implement generic command
 * that does nothing but posting an {@link BookmarkEvent}.
 * 
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.2 $ $Date: 2006/05/29 15:00:33 $
 */
public class BookmarkChangedCommand extends AuRequest.Command {
	public BookmarkChangedCommand(String evtnm, boolean skipIfEverError) {
		super(evtnm, skipIfEverError);
	}

	//-- super --//
	protected void process(AuRequest request) {
		final String[] data = request.getData();
		if (data == null || data.length != 1)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA,
				new Object[] {Objects.toString(data), this});
		final String nm = data[0];
		((DesktopCtrl)request.getDesktop()).setBookmarkByClient(nm);
		Events.postEvent(new BookmarkEvent(getId(), nm));
	}
}
